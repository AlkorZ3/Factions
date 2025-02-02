package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.cmd.type.TypeSortMPlayer;
import com.massivecraft.factions.comparator.ComparatorMPlayerInactivity;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.pager.Pager;
import com.massivecraft.massivecore.pager.Stringifier;
import com.massivecraft.massivecore.util.TimeDiffUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class CmdFactionsStatus extends FactionsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdFactionsStatus()
	{
		// Parameters
		this.addParameter(Parameter.getPage());
		this.addParameter(TypeFaction.get(), "faction", "you").setDesc("the faction whose status to see");
		this.addParameter(TypeSortMPlayer.get(), "sort", "time").setDesc("sort mplayers by rank, power or last active time?");
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int page = this.readArg();
		Faction faction = this.readArg(msenderFaction);
		Comparator<MPlayer> sortedBy = this.readArg(ComparatorMPlayerInactivity.get());
		
		// Sort list
		final List<MPlayer> mplayers = faction.getMPlayers();
		mplayers.sort(sortedBy);
		
		// Pager Create
		String title = Txt.parse("<i>Status of %s<i>.", faction.describeTo(msender, true));
		final Pager<MPlayer> pager = new Pager<>(this, title, page, mplayers, (Stringifier<MPlayer>) (mplayer, index) -> {
			// Name
			String displayName = mplayer.getNameAndSomething(msender.getColorTo(mplayer).toString(), "");
			int length = 15 - displayName.length();
			length = length <= 0 ? 1 : length;
			String whiteSpace = Txt.repeat(" ", length);

			// Power
			double currentPower = mplayer.getPower();
			double maxPower = mplayer.getPowerMax();
			String color;
			double percent = currentPower / maxPower;

			if (percent > 0.75)
			{
				color = "<green>";
			}
			else if (percent > 0.5)
			{
				color = "<yellow>";
			}
			else if (percent > 0.25)
			{
				color = "<rose>";
			}
			else
			{
				color = "<red>";
			}

			String power = Txt.parse("<art>Power: %s%.0f<gray>/<green>%.0f", Txt.parse(color), currentPower, maxPower);

			// Time
			long lastActiveMillis = mplayer.getLastActivityMillis() - System.currentTimeMillis();
			LinkedHashMap<TimeUnit, Long> activeTimes = TimeDiffUtil.limit(TimeDiffUtil.unitcounts(lastActiveMillis, TimeUnit.getAllButMillis()), 3);
			String lastActive = mplayer.isOnline(msender) ? Txt.parse("<lime>Online right now.") : Txt.parse("<i>Last active: " + TimeDiffUtil.formatedMinimal(activeTimes, "<i>"));

			return Txt.parse("%s%s %s %s", displayName, whiteSpace, power, lastActive);
		});
		
		
		// Pager Message
		pager.message();
	}
	
}
