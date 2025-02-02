package com.massivecraft.factions.entity;

import com.massivecraft.factions.event.EventFactionsCreateFlags;
import com.massivecraft.massivecore.Named;
import com.massivecraft.massivecore.Prioritized;
import com.massivecraft.massivecore.Registerable;
import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.comparator.ComparatorSmart;
import com.massivecraft.massivecore.predicate.PredicateIsRegistered;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;

public class MFlag extends Entity<MFlag> implements Prioritized, Registerable, Named
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public final static transient String ID_OPEN = "open";
	public final static transient String ID_MONSTERS = "monsters";
	public final static transient String ID_ANIMALS = "animals";
	public final static transient String ID_POWERLOSS = "powerloss";
	public final static transient String ID_POWERGAIN = "powergain";
	public final static transient String ID_PVP = "pvp";
	public final static transient String ID_FRIENDLYFIRE = "friendlyfire";
	public final static transient String ID_EXPLOSIONS = "explosions";
	public final static transient String ID_OFFLINEEXPLOSIONS = "offlineexplosions";
	public final static transient String ID_FIRESPREAD = "firespread";
	public final static transient String ID_ENDERGRIEF = "endergrief";
	public final static transient String ID_ZOMBIEGRIEF = "zombiegrief";
	public final static transient String ID_PERMANENT = "permanent";
	public final static transient String ID_PEACEFUL = "peaceful";
	public final static transient String ID_INFPOWER = "infpower";
	public final static transient String ID_FLY = "fly";
	public final static transient String ID_TAXKICK = "taxkick";
	public final static transient String ID_IMMORTAL = "immortal";
	
	public final static transient int PRIORITY_OPEN = 1_000;
	public final static transient int PRIORITY_MONSTERS = 2_000;
	public final static transient int PRIORITY_ANIMALS = 3_000;
	public final static transient int PRIORITY_POWERLOSS = 4_000;
	public final static transient int PRIORITY_POWERGAIN = 5_000;
	public final static transient int PRIORITY_PVP = 6_000;
	public final static transient int PRIORITY_FRIENDLYFIRE = 7_000;
	public final static transient int PRIORITY_EXPLOSIONS = 8_000;
	public final static transient int PRIORITY_OFFLINEEXPLOSIONS = 9_000;
	public final static transient int PRIORITY_FIRESPREAD = 10_000;
	public final static transient int PRIORITY_ENDERGRIEF = 11_000;
	public final static transient int PRIORITY_ZOMBIEGRIEF = 12_000;
	public final static transient int PRIORITY_PERMANENT = 13_000;
	public final static transient int PRIORITY_PEACEFUL = 14_000;
	public final static transient int PRIORITY_INFPOWER = 15_000;
	public final static transient int PRIORITY_FLY = 16_000;
	public final static transient int PRIORITY_TAXKICK = 17_000;
	public final static transient int PRIORITY_IMMORTAL = 18_000;
	
	// -------------------------------------------- //
	// META: CORE
	// -------------------------------------------- //
	
	public static MFlag get(Object oid)
	{
		return MFlagColl.get().get(oid);
	}
	
	public static List<MFlag> getAll()
	{
		return getAll(Bukkit.isPrimaryThread());
	}
	
	public static List<MFlag> getAll(boolean sync)
	{
		setupStandardFlags();
		new EventFactionsCreateFlags(!sync).run();
		return MFlagColl.get().getAll(PredicateIsRegistered.get(), ComparatorSmart.get());
	}
	
	public static void setupStandardFlags()
	{
		getFlagOpen();
		getFlagMonsters();
		getFlagAnimals();
		getFlagPowerloss();
		getFlagPowergain();
		getFlagPvp();
		getFlagFriendlyire();
		getFlagExplosions();
		getFlagOfflineexplosions();
		getFlagFirespread();
		getFlagEndergrief();
		getFlagZombiegrief();
		getFlagPermanent();
		getFlagPeaceful();
		getFlagInfpower();
		getFlagFly();
		getFlagTaxKick();
		getFlagImmortal();
	}
	
	public static MFlag getFlagOpen() { return getCreative(PRIORITY_OPEN, ID_OPEN, ID_OPEN, "Can the faction be joined without an invite?", "Anyone can join. No invite required.", "An invite is required to join.", false, true, true); }
	public static MFlag getFlagMonsters() { return getCreative(PRIORITY_MONSTERS, ID_MONSTERS, ID_MONSTERS, "Can monsters spawn in this territory?", "Monsters can spawn in this territory.", "Monsters can NOT spawn in this territory.", false, true, true); }
	public static MFlag getFlagAnimals() { return getCreative(PRIORITY_ANIMALS, ID_ANIMALS, ID_ANIMALS, "Can animals spawn in this territory?", "Animals can spawn in this territory.", "Animals can NOT spawn in this territory.", true, true, true); }
	public static MFlag getFlagPowerloss() { return getCreative(PRIORITY_POWERLOSS, ID_POWERLOSS, ID_POWERLOSS, "Is power lost on death in this territory?", "Power is lost on death in this territory.", "Power is NOT lost on death in this territory.", true, false, true); }
	public static MFlag getFlagPowergain() { return getCreative(PRIORITY_POWERGAIN, ID_POWERGAIN, ID_POWERGAIN, "Can power be gained in this territory?", "Power can be gained in this territory.", "Power is NOT gained in this territory.", true, false, true); }
	public static MFlag getFlagPvp() { return getCreative(PRIORITY_PVP, ID_PVP, ID_PVP, "Can you PVP in territory?", "You can PVP in this territory.", "You can NOT PVP in this territory.", true, false, true); }
	public static MFlag getFlagFriendlyire() { return getCreative(PRIORITY_FRIENDLYFIRE, ID_FRIENDLYFIRE, ID_FRIENDLYFIRE, "Can friends hurt eachother in this territory?", "Friendly fire is on here.", "Friendly fire is off here.", false, false, true); }
	public static MFlag getFlagExplosions() { return getCreative(PRIORITY_EXPLOSIONS, ID_EXPLOSIONS, ID_EXPLOSIONS, "Can explosions occur in this territory?", "Explosions can occur in this territory.", "Explosions can NOT occur in this territory.", true, false, true); }
	public static MFlag getFlagOfflineexplosions() { return getCreative(PRIORITY_OFFLINEEXPLOSIONS, ID_OFFLINEEXPLOSIONS, ID_OFFLINEEXPLOSIONS, "Can explosions occur if faction is offline?", "Explosions if faction is offline.", "No explosions if faction is offline.", false, false, true); }
	public static MFlag getFlagFirespread() { return getCreative(PRIORITY_FIRESPREAD, ID_FIRESPREAD, ID_FIRESPREAD, "Can fire spread in territory?", "Fire can spread in this territory.", "Fire can NOT spread in this territory.", true, false, true); }
	public static MFlag getFlagEndergrief() { return getCreative(PRIORITY_ENDERGRIEF, ID_ENDERGRIEF, ID_ENDERGRIEF, "Can endermen grief in this territory?", "Endermen can grief in this territory.", "Endermen can NOT grief in this territory.", false, false, true); }
	public static MFlag getFlagZombiegrief() { return getCreative(PRIORITY_ZOMBIEGRIEF, ID_ZOMBIEGRIEF, ID_ZOMBIEGRIEF, "Can zombies break doors in this territory?", "Zombies can break doors in this territory.", "Zombies can NOT break doors in this territory.", false, false, true); }
	public static MFlag getFlagPermanent() { return getCreative(PRIORITY_PERMANENT, ID_PERMANENT, ID_PERMANENT, "Is the faction immune to deletion?", "The faction can NOT be deleted.", "The faction can be deleted.", false, false, true); }
	public static MFlag getFlagPeaceful() { return getCreative(PRIORITY_PEACEFUL, ID_PEACEFUL, ID_PEACEFUL, "Is the faction in truce with everyone?", "The faction is in truce with everyone.", "The faction relations work as usual.", false, false, true); }
	public static MFlag getFlagInfpower() { return getCreative(PRIORITY_INFPOWER, ID_INFPOWER, ID_INFPOWER, "Does the faction have infinite power?", "The faction has infinite power.", "The faction power works as usual.", false, false, true); }
	public static MFlag getFlagFly() { return getCreative(PRIORITY_FLY, ID_FLY, ID_FLY, "Is flying allowed in faction territory?", "Players can fly in faction territory.", "Players can not fly in faction territory.", false, false, true); }
	public static MFlag getFlagTaxKick() { return getCreative(PRIORITY_TAXKICK, ID_TAXKICK, ID_TAXKICK, "Are players kicked for not paying taxes?", "Members are kicked for not paying taxes.", "Members are not kicked for not paying taxes.",  false, true, true); }
	public static MFlag getFlagImmortal() { return getCreative(PRIORITY_IMMORTAL, ID_IMMORTAL, ID_IMMORTAL, "Are players immortal in this territory?", "Players are immortal in this territory.", "Players are NOT immortal in this territory.",  false, false, true); }
	
	public static MFlag getCreative(int priority, String id, String name, String desc, String descYes, String descNo, boolean standard, boolean editable, boolean visible)
	{
		MFlag ret = MFlagColl.get().get(id, false);
		if (ret != null)
		{
			ret.setRegistered(true);
			return ret;
		}
		
		ret = new MFlag(priority, name, desc, descYes, descNo, standard, editable, visible);
		MFlagColl.get().attach(ret, id);
		ret.setRegistered(true);
		ret.sync();
		
		return ret;
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public MFlag load(MFlag that)
	{
		this.priority = that.priority;
		this.name = that.name;
		this.desc = that.desc;
		this.descYes = that.descYes;
		this.descNo = that.descNo;
		this.standard = that.standard;
		this.editable = that.editable;
		this.visible = that.visible;
		
		return this;
	}
	
	// -------------------------------------------- //
	// TRANSIENT FIELDS (Registered)
	// -------------------------------------------- //
	
	private transient boolean registered = false;
	public boolean isRegistered() { return this.registered; }
	public void setRegistered(boolean registered) { this.registered = registered; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// The sort priority. Low values appear first in sorted lists.
	// 1 is high up, 99999 is far down.
	// Standard Faction flags use "thousand values" like 1000, 2000, 3000 etc to allow adding new flags inbetween.
	// So 1000 might sound like a lot but it's actually the priority for the first flag.
	private int priority = 0;
	@Override public int getPriority() { return this.priority; }
	public MFlag setPriority(int priority) { this.priority = priority; this.changed(); return this; }
	
	// The name of the flag. According to standard it should be fully lowercase just like the flag id.
	// In fact the name and the id of all standard flags are the same.
	// I just added the name in case anyone feel like renaming their flags for some reason.
	// Example: "monsters"
	private String name = "defaultName";
	@Override public String getName() { return this.name; }
	public MFlag setName(String name) { this.name = name; this.changed(); return this; }
	
	// The flag function described as a question.
	// Example: "Can monsters spawn in this territory?"
	private String desc = "defaultDesc";
	public String getDesc() { return this.desc; }
	public MFlag setDesc(String desc) { this.desc = desc; this.changed(); return this; }
	
	// The flag function described when true.
	// Example: "Monsters can spawn in this territory."
	private String descYes = "defaultDescYes";
	public String getDescYes() { return this.descYes; }
	public MFlag setDescYes(String descYes) { this.descYes = descYes; this.changed(); return this; }
	
	// The flag function described when false.
	// Example: "Monsters can NOT spawn in this territory."
	private String descNo = "defaultDescNo";
	public String getDescNo() { return this.descNo; }
	public MFlag setDescNo(String descNo) { this.descNo = descNo; this.changed(); return this; }
	
	// What is the standard (aka default) flag value?
	// This value will be set for factions from the beginning.
	// Example: false (per default monsters do not spawn in faction territory)
	private boolean standard = true;
	public boolean isStandard() { return this.standard; }
	public MFlag setStandard(boolean standard) { this.standard = standard; this.changed(); return this; }
	
	// Is this flag editable by players?
	// With this we mean standard non administrator players.
	// All flags can be changed using /f override.
	// Example: true (if players want to turn mob spawning on I guess they should be able to)
	private boolean editable = false;
	public boolean isEditable() { return this.editable; }
	public MFlag setEditable(boolean editable) { this.editable = editable; this.changed(); return this; }
	
	// Is this flag visible to players?
	// With this we mean standard non administrator players.
	// All flags can be seen using /f override.
	// Some flags can be rendered meaningless by settings in Factions or external plugins.
	// Say we set "editable" to false and "standard" to true for the "open" flag to force all factions being open.
	// In such case we might want to hide the open flag by setting "visible" false.
	// If it can't be changed, why bother showing it?
	// Example: true (yeah we need to see this flag)
	private boolean visible = true;
	public boolean isVisible() { return this.visible; }
	public MFlag setVisible(boolean visible) { this.visible = visible; this.changed(); return this; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public MFlag()
	{
		// No argument constructor for GSON
	}
	
	public MFlag(int priority, String name, String desc, String descYes, String descNo, boolean standard, boolean editable, boolean visible)
	{
		this.priority = priority;
		this.name = name;
		this.desc = desc;
		this.descYes = descYes;
		this.descNo = descNo;
		this.standard = standard;
		this.editable = editable;
		this.visible = visible;
	}
	
	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
	public boolean isInteresting(boolean value)
	{
		if ( ! this.isVisible()) return false;
		if (this.isEditable()) return true;
		return this.isStandard() != value;
	}
	
	public String getStateDesc(boolean value, boolean withValue, boolean monospaceValue, boolean withName, boolean withDesc, boolean specificDesc)
	{
		// Create
		List<String> ret = new MassiveList<>();
		
		// Fill
		if (withValue) ret.add(getStateValue(value, monospaceValue));
		if (withName) ret.add(this.getStateName());
		if (withDesc) ret.add(this.getStateDescription(value, specificDesc));
		
		// Return
		return Txt.implode(ret, " ");
	}
	
	private static String getStateValue(boolean value, boolean monoSpace)
	{
		String yes = "<g>YES";
		String no = monoSpace ? "<b>NOO" : "<b>NO";
		
		return Txt.parse(value ? yes : no);
	}
	
	private String getStateName()
	{
		return this.getStateColor().toString() + this.getName();
	}
	
	private ChatColor getStateColor()
	{
		// Is special?
		if (!this.isVisible()) return ChatColor.GRAY;
		if (this.isEditable()) return ChatColor.LIGHT_PURPLE;
		
		// Return normal
		return ChatColor.AQUA;
	}
	
	private String getStateDescription(boolean value, boolean specific)
	{
		// Create
		String desc = this.getDesc();
		
		// Is specific?
		if (specific) desc = value ? this.getDescYes() : this.getDescNo();
		
		// Return
		return Txt.parse("<i>%s", desc);
	}
	
}
