/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2025 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist.PPD;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpiritHawk;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.ImpShopkeeper;
import com.shatteredpixel.shatteredpixeldungeon.effects.BadgeBanner;
import com.shatteredpixel.shatteredpixeldungeon.items.Ankh;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Stylus;
import com.shatteredpixel.shatteredpixeldungeon.items.Torch;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.LeatherArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Brimstone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Swiftness;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.EtherealChains;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.SandalsOfNature;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.WoollyBomb;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.food.HoneyedMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Pasty;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.AquaBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.CausticBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.InfernalBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.ShockingBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfArcana;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFuror;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfWealth;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfAntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfDivination;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfMetamorphosis;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Alchemize;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.TelekineticGrab;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAugmentation;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.MossyClump;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.SaltCube;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.TrinketCatalyst;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorrosion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorruption;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfTransfusion;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.curses.Explosive;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blooming;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Vampiric;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Dirk;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Glaive;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greataxe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.HandAxe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Longsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RoundShield;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RunicBlade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sai;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Scimitar;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sickle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WarHammer;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WarScythe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Whip;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ForceCube;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.Tomahawk;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.ShockingDart;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.ChangesScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BruteSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CrabSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.DM300Sprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.EarthGuardianSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ElementalSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GhostSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GolemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GuardSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HermitCrabSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ImpSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.NecromancerSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.PrismaticSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShopkeeperSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SpectralNecromancerSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.WandmakerSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.TalentIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.ChangeButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.ChangeInfo;
import com.watabou.noosa.Image;
import com.watabou.utils.Reflection;

import java.util.ArrayList;

public class v1_0_X_PPDChanges {
	
	static void addVersion(ArrayList<ChangeInfo> changeInfos, String title) {
		ChangeInfo version = new ChangeInfo(title, true, null);
		version.hardlight(Window.TITLE_COLOR);
		
		changeInfos.add(version);
		currentSection = null;
	}
	
	static final String NEW 	= "new";
	static final String CHANGES = "changes";
	static final String BUFFS 	= "buffs";
	static final String NERFS 	= "nerfs";
	static final String EMPTY 	= "";
	
	static ChangeInfo currentSection = null;
	static void addSection(ArrayList<ChangeInfo> changeInfos, String title) {
		ChangeInfo section = new ChangeInfo(title.matches(EMPTY) ? "" : Messages.get(ChangesScene.class, title), false, null);
		
		switch (title) {
			case BUFFS:
				section.hardlight(CharSprite.POSITIVE);
				break;
			case NERFS:
				section.hardlight(CharSprite.NEGATIVE);
				break;
			case CHANGES:
				section.hardlight(CharSprite.NEUTRAL);
				break;
			case NEW:
				section.hardlight(Window.POLISHED_COLOR);
				break;
		}
		
		changeInfos.add(section);
		currentSection = section;
	}
	
	static void addChange(Image icon, String title, String description) {
		if(currentSection != null) {
			currentSection.addButton( new ChangeButton(icon, title, description) );
		}
	}
	
	static void addChange(Icons icon, String title, String description) {
		addChange(Icons.get(icon), title, description);
	}
	
	static void addChange(MobSprite mob, String title, String description) {
		addChange(new Image(mob), title, description);
	}
	
	static void addChange(Item item, String title, String description) {
		addChange(new ItemSprite(item), title, description);
	}
	
	static void addChange(Talent talent, String title, String description) {
		addChange(new TalentIcon(talent), title, description);
	}
	
	static BuffIcon buffIcon(int icon) {
		return new BuffIcon(icon, true);
	}
	
	static ItemSprite itemIcon(int icon) {
		return new ItemSprite(icon);
	}
	
	static Image tileIcon(String asset, int x, int y) {
		return new Image(asset, x, y, 16, 16);
	}
	
	static Image heroIcon(HeroClass heroClass, int armorTier) {
		return HeroSprite.avatar(heroClass, armorTier);
	}
	
	static Image QOL() {
		Image qol = Icons.get(Icons.SEED);
		qol.hardlight(1f, 1.5f, 0.67f);
		return qol;
	}
	
	static Image BUGFIX() {
		return new Image(Assets.Sprites.SPINNER, 144, 0, 16, 16);
	}
	
	static Image MISC() {
		return Icons.get(Icons.CATALOG);
	}
	
	static Image GAMEPLAY() {
		return Icons.get(Icons.CHANGES);
	}
	
	
	
	public static void add_v1_0_Changes( ArrayList<ChangeInfo> changeInfos ) {
		addVersion(changeInfos, "v1.0.0");
		
		
		addSection(changeInfos, NEW);
		// ***
		addChange(Icons.PPD, "Polished Pixel Dungeon",
				"- New icon/app name.\n" +
						"- New file save location.\n" +
						"\n" +
						"- Updated title scene.\n" +
						"- Credits updated.\n" +
						"\n" +
						"- UI button color changed to purple.\n" +
						"- Changed data tab to polished tab in settings menu.\n" +
						"\n" +
						"Removed unlock requirements.\n" +
						"Added option to skip intro.\n" +
						"\n" +
						"Replaced various references to Shattered / Evan."
		);
		
		ClothArmor cloth = new ClothArmor();
		cloth.inscribe(Reflection.newInstance(Swiftness.class));
		addChange(cloth, "Swiftness Rework",
				"_Removed:_ No longer grants passive speed.\n" +
						"\n" +
						"_Instead,_ has a chance to give stamina on-hit. Both the chance and duration scale with armor level."
		);
		
		addChange(new TimekeepersHourglass(), "Hourglass rework",
				"_New:_ Now generates _X+1_ turns of debt during time freeze, _X_ being the number of turns used.\n" +
						"\n" +
						"Once time freeze ends, a timer will start. After _X_ turns, the player will become slowed by 50%. The duration of this slow is based on the debt generated."
		);
		
		addChange(new EtherealChains(), "Chains Rework",
				"_Removed:_ Can no longer pull you into walls.\n" +
						"\n" +
						"_Instead,_ can pull the player into any enemy-adjacent tile. This action is instant.\n" +
						"\n" +
						"_New:_ Pulling an enemy cripples them based on distance. This action is not instant."
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(new GhostSprite(), "Ghost Quest",
				"Can now only spawn on floors 2/3 (33%/66%).\n" +
						"\n" +
						"Floor 3 quest has a 50/50 chance of picking Trickster/Great crab.\n" +
						"\n" +
						"Reward upgrade distribution smoothed out, more likely to receive +1 equipment compared to low/high rolls."
		);
		
		addChange(new WandmakerSprite(), "Wandmaker Quest",
				"Can now only spawn on floors 2/3 (33%/66%)."
		);
		
		addChange(Icons.PREFS, "New QoL Settings",
				"- New setting: _Input Block:_\n" +
						"Blocks all input after spotting an enemy for the first time; stepping on a trap; or being targeted by an ability. Prevents missclicks.\n" +
						"Activated by default.\n" +
						"\n" +
						"- New setting: _Auto Harvest:_\n" +
						"Automatically picks up all harvested items from trampled grass.\n" +
						"Activated by default.\n" +
						"\n" +
						"- New setting: _Quick Transitions:_\n" +
						"Speeds up transitions when moving between floors/regions.\n" +
						"Disabled by default.\n" +
						"\n" +
						"- New setting: _Action Buffering:_\n" +
						"Allows you to buffer quickslot actions, such as shooting your bow, while the game's animations are still playing. Ensures a smoother experience.\n" +
						"Set to 100ms by default."
		);
		// ***
		
		addSection(changeInfos, BUFFS);
		// ***
		addChange(buffIcon(BuffIndicator.OOZE), "Ooze and Hex",
				"_- Ooze:_\n" +
						"Damage in sewers: 0.5 -> 0.75\n" +
						"\n" +
						"_- Hex:_\n" +
						"Reduction in acc/eva: 20% -> 25%"
		);
		// ***
		
		addSection(changeInfos, NERFS);
		// ***
		addChange(buffIcon(BuffIndicator.FIRE), "Burning and Bleeding",
				"_- Burning:_\n" +
						"Now deals less base damage, and scales slower.\n" +
						"Duration decreased.\n" +
						"\n" +
						"_- Bleeding:_\n" +
						"Variance reduced, damage ticks down more consistently."
		);
		
		addChange(tileIcon(Assets.Environment.TILES_SEWERS, 176, 16), "Chasm Kills",
				"Killing enemies via chasms doesn't grant experience anymore."
		);
		
		addChange(new CrabSprite(), "Crab and Guard",
				"_- Crab:_\n" +
						"Movement speed: 2 -> 1.5\n" +
						"\n" +
						"_- Guard:_\n" +
						"No longer instantly attacks after pulling."
		);
		// ***
		
		
		currentSection = null;
	}
	
	public static void add_v1_1_Changes( ArrayList<ChangeInfo> changeInfos ) {
		addVersion(changeInfos, "v1.1.0");
		
		
		addSection(changeInfos, NEW);
		// ***
		BuffIcon speed = buffIcon(BuffIndicator.HASTE);
		speed.hardlight(0f, 0.25f, 1f);
		addChange(speed, "Speed Buff",
				"New buff: _Speed!_\n" +
						"Works like a slower Haste, granting 2x speed to its target."
		);
		
		addChange(new NecromancerSprite(), "Necromancer Rework",
				"AI improvements: can’t be cheesed into doorways as easily anymore.\n" +
						"It's skeleton summon will follow your last known location.\n" +
						"\n" +
						"_New:_ Now always heals AND buffs at the same time!\n" +
						"Grants Speed _(New!)_ on the first zap, then Adrenaline.\n" +
						"\n" +
						"All of Necromancer's abilities got a 1-2 turn cooldown.\n" +
						"\n" +
						"_- Spectral Necromancer_ also got affected by some of these changes."
		);
		
		addChange(Icons.CHALLENGE_COLOR, "Champions Rework",
				"_New:_ The game now tells you which champion type spawned.\n" +
						"\n" +
						"_Antimagic:_\n" +
						"50% dmg reduction -> 40%\n" +
						"\n" +
						"Now also immune to Wand of Corrosion damage.\n" +
						"Now also resistant to wands of Blastwave, Regrowth and Corruption effects.\n" +
						"No longer immune to Shocking\n" +
						"\n" +
						"_Projecting:_\n" +
						"_New:_ Its range now has a 1 turn cooldown after landing a hit.\n" +
						"\n" +
						"_Growing:_\n" +
						"Base strength: 20% -> 25%\n" +
						"Scaling: +2.5%/turn -> +3.33%/turn\n" +
						"\n" +
						"Evasion isn't boosted anymore.\n" +
						"\n" +
						"Once enough time passes, it will begin hunting you. When hunting, growing champions are able to track your location (unless invisible).\n" +
						"\n" +
						"_Blessed:_\n" +
						"4x accuracy -> 5x accuracy\n" +
						"4x evasion -> 3x evasion\n" +
						"\n" +
						"_Giant:_\n" +
						"80% damage reduction -> 40%\n" +
						"Increases up to 90% against enemies outside their room (the door counts as inside)."
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(new GhostSprite(), "Ghost Quest",
				"_- Quest Enemies_ won't spawn near the Hero anymore.\n" +
						"\n" +
						"_- Fetid Rat:_\n" +
						"Releases more stench gas on-hit.\n" +
						"\n" +
						"No longer applies Ooze against characters standing in water.\n" +
						"\n" +
						"_- Trickster:_\n" +
						"Movement speed: 1 -> 5/6\n" +
						"\n" +
						"Won't apply debuffs on the first hit anymore. Instead, landing successive darts builds up combo, which leads to stronger Poison, and then Burning.\n" +
						"\n" +
						"_- Great Crab:_\n" +
						"HP: 25 -> 20"
		);
		
		addChange(new SaltCube(), "Removed Items",
				"_- Salt Cube_ removed.\n" +
						"\n" +
						"_- Ring of Haste_ removed.\n" +
						"\n" +
						"_- Magic Missile_ removed from level generation (only Mage starts with it)."
		);
		
		addChange(GAMEPLAY(), "Gameplay Adjustments",
				"Necromancer's summon can be cancelled again by blocking it with an immovable unit (ex. Wand of Warding).\n" +
						"\n" +
						"Can no longer obtain tier 5 equipment on sewers.\n" +
						"\n" +
						"Eating delay: 3 turns -> 2 turns\n" +
						"Searching hunger: 6 turns -> 4 turns\n" +
						"\n" +
						"Entering starvation no longer instantly damages the Hero.\n" +
						"\n" +
						"When the Duelist gains max ability charges, she now also gains current ability charges.\n" +
						"\n" +
						"Water generation adjusted on most regions.\n" +
						"\n" +
						"Adjusted gold value of various items.\n" +
						"Adjusted energy value of various items."
		);
		
		addChange(QOL(), "Quality of Life",
				"- Improved pathfinding through dark tiles. Hero won't try to go around the whole level if there's a small undiscovered gap between tiles.\n" +
						"\n" +
						"- You can now press the wait/search button (or use hotkeys) to cancel movement.\n" +
						"\n" +
						"- When playing on landscape: You can now open/close your inventory, and swap between bags, while the game's animations are still playing.\n" +
						"\n" +
						"- Smoother camera zoom."
		);
		// ***
		
		addSection(changeInfos, BUFFS);
		// ***
		addChange(itemIcon(ItemSpriteSheet.EXOTIC_TURQUOISE), "Storm Clouds and Featherfall",
				"_- Storm Clouds:_\n" +
						"_New:_ Now applies Daze to characters inside (-50% acc/eva).\n" +
						"\n" +
						"_- Featherfall:_\n" +
						"Energy cost: 10 -> 8"
		);
		
		addChange(new StoneOfAugmentation(), "Damage Augment",
				"Attack delay: 1.66 -> 1.5\n" +
						"\n" +
						"Damage multiplier: 1.5 -> 1.45\n" +
						"\n" +
						"Less awkward to use, higher DPS, a bit worse at one-shots."
		);
		
		addChange(itemIcon(ItemSpriteSheet.RING_SAPPHIRE), "Ring of Tenacity",
				"_New:_ Displays current % reduction.\n" +
						"\n" +
						"Higher base reduction.\n" +
						"Scales based on missing HP faster, caps at 15% HP.\n" +
						"\n" +
						"Damage rounding more favourable to the player."
		);
		// ***
		
		addSection(changeInfos, NERFS);
		// ***
		addChange(new AquaBrew(), "Aqua Brew and Flow",
				"_- Aqua Brew:_\n" +
						"Energy cost: 8 -> 10\n" +
						"Output quantity: 8 -> 6\n" +
						"\n" +
						"_- Glyph of Flow:_\n" +
						"Movement speed on water: 2 + level/2 -> 1.25 + level/8"
		);
		
		addChange(new WandOfCorrosion(), "Wand of Corrosion and Blastwave",
				"_- Wand of Corrosion:_\n" +
						"Starting damage: 2 + level -> 2 + level/2 (rounded).\n" +
						"Lower base spread.\n" +
						"\n" +
						"_- Wand of Blastwave:_\n" +
						"Can only push enemies into chasms if hit directly.\n" +
						"\n" +
						"Knockback on direct hits: 3 + level -> 2 + level\n" +
						"\n" +
						"Anti-magic champions can't be pushed more than 1 tile by this wand. _(Champions Rework)_"
		);
		
		addChange(new HornOfPlenty(), "Horn of Plenty",
				"Snacking now only gives ~half duration on-eat talent effects.\n" +
						"\n" +
						"Eating will consume up to 3 stacks, instead of eating until you're full.\n" +
						"\n" +
						"Levels gained from pasty: 2 -> 1.5\n" +
						"Levels gained from meat pie: 3.5 -> 4\n" +
						"Levels gained from phantom meat: 2 -> 2.5"
		);
		// ***
		
		
		currentSection = null;
	}
	
	public static void add_v1_2_Changes( ArrayList<ChangeInfo> changeInfos ) {
		addVersion(changeInfos, "v1.2.0");
		
		
		addSection(changeInfos, NEW);
		// ***
		addChange(buffIcon(BuffIndicator.BRITTLE), "Brittle Debuff",
				"New debuff, _Brittle!_\n" +
						"Works like a weaker vulnerable + weakness:\n" +
						"Both weakens physical attacks, and increases physical damage taken, by 25%.\n" +
						"Unlike weakness/vulnerable, it doesn't count as a magic effect."
		);
		
		addChange(new WandOfCorruption(), "Wand of Corruption Rework",
				"Can now zap multiple times at once:\n" +
						"Zaps twice, at 3+ current charges\n" +
						"Zaps thrice, at 6+ current charges\n" +
						"\n" +
						"Now applies these debuffs:\n" +
						"_Minor:_\n" +
						"Brittle _(New!)_ (33%)\n" +
						"Hex (33%)\n" +
						"Charm (16%)\n" +
						"Cripple (16%)\n" +
						"— — — — — — — -\n" +
						"_Major:_\n" +
						"Amok (37.5%)\n" +
						"Daze (25%)\n" +
						"Terror (25%)\n" +
						"Slow (12.5%)\n" +
						"\n" +
						"Corrupted enemies no longer activate Swarm Intelligence.\n" +
						"\n" +
						"Adjusted which debuffs are considered Minor/Major:\n" +
						"_Soulmark:_ Major -> Minor\n" +
						"_Corrosion:_ Major -> Minor\n" +
						"_Hex:_ Major -> Minor\n" +
						"_Charm:_ Major -> Minor\n" +
						"_Brittle:_ Minor\n" +
						"— — — — — — — -\n" +
						"_Root:_ Minor -> Major\n" +
						"_Vertigo:_ Minor -> Major\n" +
						"_Blindness:_ Minor -> Major\n" +
						"_Terror:_ Minor -> Major"
		);
		
		addChange(heroIcon(HeroClass.HUNTRESS, 1), "Huntress Rework",
				"_New:_ The _Spirit Bow_ has 6 max charges. Shooting an enemy spends 1 charge, but shooting into an empty tile spends none.\n" +
						"\n" +
						"You can fully recharge your bow by targeting yourself, this action takes 2 turns. Alternatively, you can target yourself with a " +
						"_Telekinetic Grab_ to recharge in just 1.\n" +
						"\n" +
						"Augments change your bow's max charge. Damage augment lowers it to 5, speed augment increases it to 8.\n" +
						"\n" +
						"Speed and damage sniper specials cost 2 charges. Regular sniper specials only cost 1.\n" +
						"\n" +
						"_Durable Projectiles buffed:_ At +2, now also grants +1 max charge to your bow.\n" +
						"\n" +
						"_Nature’s Aid reworked:_ No longer activates when using plants.\n" +
						"Instead, activates after spending 7/5 bow charges. Numbers adjusted.\n" +
						"If gained by a different Hero, it will instead trigger when applying tipped darts.\n" +
						"\n" +
						"_IMPORTANT:_ To play the reworked version, you must activate the toggle at hero select screen!\n" +
						"If you don't like the new Huntress, you may turn off the toggle at any time, and continue playing the default version."
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(Icons.BUFFS, "Buff Display",
				"- Hunger's icon now shows saturation left, visuals improved.\n" +
						"- Hunger is now also displayed in Hero's info, below health.\n" +
						"\n" +
						"- Buff duration display/text is now more accurate.\n" +
						"\n" +
						"- Small buff icons now appear less cluttered when stacking multiple."
		);
		
		addChange(Icons.TARGET, "Target Indicators",
				"- Most target indicators: (Yog laser, Ripper leap, etc) no longer disappear until the ability is executed.\n" +
						"\n" +
						"- Ranged enemies now show an indicator of their position after shooting the Hero from fog."
		);
		
		addChange(MISC(), "Miscellaneous",
				"- Max number of notes: 5 -> 10\n" +
						"- Max number of save files: 6 -> 8\n" +
						"\n" +
						"- Various description improvements.\n" +
						"- Various UI cleanups."
		);
		
		addChange(BUGFIX(), "Bugfixes",
				"- Inconsistencies with gases turn order, acting before Hero can escape.\n" +
						"- Hero-created gases getting randomly delayed, enemies escaping unharmed.\n" +
						"\n" +
						"- Paralysis duration getting affected by slowing effects.\n" +
						"- Stasis immunity ending before hero's turn to act.\n" +
						"\n" +
						"- Force cube not applying sniper mark to main target.\n"
		);
		// ***
		
		addSection(changeInfos, BUFFS);
		// ***
		addChange(new ShockingDart(), "Tipped Darts",
				"_- Shocking Dart:_\n" +
						"_New:_ Also stuns enemies for 1 turn.\n" +
						"_New:_ Affects all characters in a 3x3 AoE.\n" +
						"\n" +
						"_- Displacing Dart:_\n" +
						"Teleport distance: 8-10 -> 9-11\n"
		);
		
		addChange(Talent.POINT_BLANK, "Point Blank and Liquid Nature",
				"_- Point Blank:_\n" +
						"Melee accuracy: 70%/90%/110% -> 75%/100%/125%\n" +
						"\n" +
						"_- Liquid Nature:_\n" +
						"Grass created: 4/6 -> 6/8"
		);
		// ***
		
		addSection(changeInfos, NERFS);
		// ***
		addChange(new Tomahawk(), "Thrown Weapons",
				"_- Tomahawk:_\n" +
						"Bleed: 60% of damage -> 47.5% + 1 base\n" +
						"\n" +
						"_- Bolas:_\n" +
						"Cripple duration: 10 turns -> 7 turns\n" +
						"\n" +
						"_- Blind Dart:_\n" +
						"Blind duration: 10 turns -> 7 turns"
		);
		
		addChange(Talent.FARSIGHT, "Farsight",
				"Vision range bonus: 25%/50%/75% -> 18%/36%/54%"
		);
		// ***
		
		
		currentSection = null;
	}
	
	public static void add_v1_3_Changes( ArrayList<ChangeInfo> changeInfos ) {
		addVersion(changeInfos, "v1.3.0");
		
		
		addSection(changeInfos, NEW);
		// ***
		addChange(buffIcon(BuffIndicator.SHADOWS), "Camouflage Rework",
				"New buff: _Camouflaged!_\n" +
						"Works like invisibility, except for adjacent characters: they'll still be able to see and attack you.\n" +
						"A Camouflaged character won't wake up enemies, even up close.\n" +
						"\n" +
						"_- Glyph of Camouflage:_\n" +
						"Now grants Camouflaged, instead of invisibility.\n" +
						"\n" +
						"_- Shadowmelded:_\n" +
						"Now works like Camouflaged.\n" +
						"\n" +
						"Now also extends torch duration.\n" +
						"\n" +
						"_New:_ Becomes disabled for 3 turns after breaking camouflage."
		);
		
		addChange(buffIcon(BuffIndicator.ELECTRIFIED), "Electricity Rework",
				"New debuff: _Electrified!_\n" +
						"Stuns like paralysis, but characters will break out of it _immediately_ after taking direct damage.\n" +
						"\n" +
						"Indirect sources, like gases and debuffs, can also end the effect, although much more slowly. The lower the HP, the faster.\n" +
						"\n" +
						"_- Electricity:_\n" +
						"Now applies _Electrified_ instead of Paralysis.\n" +
						"\n" +
						"Base damage: 2 -> 1"
		);
		
		DM300Sprite DM300 = new DM300Sprite();
		DM300.scale.set(PixelScene.align(0.75f));
		addChange(DM300, "DM300 Rework",
				"Can now ALWAYS tunnel through rock. When supercharged, this ability is faster.\n" +
						"\n" +
						"Adjusted pathing AI, breaks walls slower.\n" +
						"\n" +
						"Now ALWAYS knows where you are (unless invisible).\n" +
						"\n" +
						"No longer gains shielding when stepping into an exposed wire. Instead, it gains Adrenaline, and casts abilities faster.\n" +
						"\n" +
						"Can now only use abilities when it sees you, or when it finds a trickshot angle to hit you.\n" +
						"\n" +
						"Now shoots toxic gas in front of the player, cutting off their escape.\n" +
						"\n" +
						"_(Fixed)_ Toxic gas will no longer insta-tick.\n" +
						"\n" +
						"Hero's view distance set to 6.\n" +
						"\n" +
						"Lowered amount of wiring in arena.\n" +
						"Adjusted water generation.\n" +
						"Disabled amulet layout.\n" +
						"\n" +
						"Increased max HP.\n" +
						"Increased pylon max HP.\n" +
						"\n" +
						"Other small adjustments."
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(new RunicBlade(), "Runic Blade",
				"_Removed:_ No longer gets increased dmg scaling.\n" +
						"\n" +
						"_Instead,_ Gets an enchantment power boost: 10% + 10%/level"
		);
		
		addChange(new Stylus(), "Glyph/Enchantment Rarities",
				"_- Brimstone:_ Uncommon -> Common\n" +
						"_- Affection:_ Rare -> Common\n" +
						"\n" +
						"_- Swiftness:_ Common -> Uncommon\n" +
						"_- Thorns:_ Rare -> Uncommon\n" +
						"\n" +
						"_- Camouflage:_ Uncommon -> Rare\n" +
						"_- Stone:_ Uncommon -> Rare\n" +
						"\n" +
						"— — — — — — — — — — — — — — -\n" +
						"\n" +
						"_- Blocking:_ Uncommon -> Common\n" +
						"\n" +
						"_- Blazing:_ Common -> Uncommon"
		);
		
		addChange(BUGFIX(), "Bugfixes",
				"- (PC) Quick using items from a bag displaying on the wrong screen position.\n" +
						"\n" +
						"- Hunger search cost with cursed talisman + salt cube.\n" +
						"- Hunger gained by honeyed healing + salt cube.\n" +
						"\n" +
						"- Mobs with custom wander/aggro logic not using it on certain instances.\n" +
						"\n" +
						"- Hero's chasm fall damage higher than it should be.\n" +
						"\n" +
						"- Shuriken save/load.\n" +
						"\n" +
						"- Crashes from game not recognizing new content from future versions.\n" +
						"- Implemented crash handler."
		);
		// ***
		
		addSection(changeInfos, BUFFS);
		// ***
		LeatherArmor leather = new LeatherArmor();
		leather.inscribe(Reflection.newInstance(Brimstone.class));
		addChange(leather, "Brimstone Glyph",
				"Now generates shielding while burning, at ALL levels.\n" +
						"Scales better with upgrades, and glyph boosts."
		);
		
		Longsword longsword = new Longsword();
		longsword.enchant(Reflection.newInstance(Shocking.class));
		addChange(longsword, "Shocking and Chilling Enchantments",
				"_Shocking:_\n" +
						"AoE damage: 40% of original -> 50% of original\n" +
						"_New:_ Increases to 75% against enemies on water.\n" +
						"\n" +
						"_Chilling:_\n" +
						"Chill applied: 3 turns -> 4 turns\n" +
						"_New:_ Increased proc chance against enemies on water."
		);
		
		addChange(buffIcon(BuffIndicator.FROST), "Chill",
				"Chill now starts to decay on enemy's turn, duration more consistent.\n" +
						"Most sources are slightly buffed because of this."
		);
		// ***
		
		addSection(changeInfos, NERFS);
		// ***
		Glaive glaive = new Glaive();
		glaive.enchant(Reflection.newInstance(Vampiric.class));
		addChange(glaive, "Enchantments",
				"_- Vampiric:_\n" +
						"No longer activates when starving/stalling boss.\n" +
						"\n" +
						"_- Grim:_\n" +
						"Deals 3x damage against bosses instead of 50% current HP.\n" +
						"\n" +
						"_- Elastic:_\n" +
						"Knock back decreases with distance (nerfed for long range weapons)."
		);
		
		addChange(Icons.GRASS, "Grassy Levels",
				"Amount of grass reduced on all regions.\n" +
						"Dewdrop multiplier: 0.5x -> 0.33x"
		);
		// ***
		
		
		currentSection = null;
	}
	
	public static void add_v1_4_Changes( ArrayList<ChangeInfo> changeInfos ) {
		addVersion(changeInfos, "v1.4.0");
		
		
		addSection(changeInfos, NEW);
		// ***
		addChange(Icons.WELL_EMPTY, "Wells",
				"_- New:_ _Well of Change:_\n" +
						"Lets you either: transmute an item, or metamorph two random talents.\n" +
						"\n" +
						"_- Well of Health:_\n" +
						"_Removed:_ No longer restores hunger.\n" +
						"\n" +
						"Fills waterskin to: full -> half\n" +
						"Note that you can still fully bless ankhs by directly tossing them.\n" +
						"\n" +
						"_- Well of Awareness:_\n" +
						"_New:_ Now lets you ID two items.\n" +
						"Stepping into it now grants vision of mimics as well.\n" +
						"\n" +
						"Adjusted chances from 50/50 to:\n" +
						"40% Awareness; 40% Health; 20% Change"
		);
		
		addChange(heroIcon(HeroClass.WARRIOR, 6), "Berserker Rework",
				"_Removed:_ Rage mechanic.\n" +
						"\n" +
						"_New:_ The Berserker can shout a war cry after getting hit, provoking all enemies within 8 tiles and preparing to go on a rampage.\n" +
						"\n" +
						"During this state, the Berserker gains +30% physical damage, +100% accuracy, and a large upfront shield that scales based on his seal, and up to 2x " +
						"with missing health.\n" +
						"\n" +
						"Rampages last for 8 turns, but killing enemies prolongs the duration by 2 turns. Once it ends, all remaining shielding will vanish, and the Berserker " +
						"will have to wait 120 turns before going on a rampage again.\n" +
						"\n" +
						"\n" +
						"Reworked talents:\n" +
						"_- Last Stand:_\n" +
						"The Berserker gains 18%/30%/38% damage reduction when facing 3+ enemies at once.\n" +
						"\n" +
						"_- Undying Rage:_\n" +
						"When taking fatal damage, the Berserker refuses to die. He will automatically begin a rampage, and become immortal for 3/5/7 turns. At the end of this effect, " +
						"he'll also restore 2%/4%/6% of his max HP + 1.2%/2.4%/3.6% per enemy killed during the rage. This ability has a cooldown of 1.5 hero levels.\n" +
						"\n" +
						"_- Enraged Catalyst:_\n" +
						"Glyphs and Enchantments trigger more often the lower the Berserker's health, up to +40%/80%/120% for Glyphs, and +20%/40%/60% for Enchantments. You will " +
						"always get the max boost while rampaging."
		);
		
		addChange(heroIcon(HeroClass.ROGUE, 3), "Rogue Talents",
				"_- Foresight_ removed.\n" +
						"_- Wide Search_ removed.\n" +
						"\n" +
						"- New talent: _Rogue's Expertise:_\n" +
						"_+1:_ The Rogue's search: gets +1 radius, becomes 2x faster, and consumes 4x less hunger.\n" +
						"— — — — — — — -\n" +
						"_+2:_ On top of the benefits from +1, the Rogue can also immediately spot any secret doors on his field of view.\n" +
						"\n" +
						"Replaces _Foresight._\n" +
						"\n" +
						"- New talent: _Smart Escape:_\n" +
						"The Rogue gains _1.5 turns/2 turns_ of haste whenever a trap is activated. Floors will generate _10%/20%_ more revealed traps.\nTraps that don't break can only grant haste up to 3 times.\n" +
						"\n" +
						"Replaces _Wide Search._"
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(new TrinketCatalyst(), "Trinkets and Catalyst",
				"Trinkets can no longer be energized, though you can still energize the catalyst.\n" +
						"\n" +
						"Catalyst energy value: 5 -> 6\n" +
						"\n" +
						"Catalyst can no longer spawn in toxic gas rooms."
		);
		
		addChange(Icons.BACKPACK_LRG, "Inventory Management",
				"Holster can now also carry torches and honeypots.\n" +
						"Note that items inside the holster will be protected from thieves.\n" +
						"\n" +
						"Pouch can now also carry blandfruits and berries.\n" +
						"\n" +
						"Shattered Pots are now energizable, for 2 energy.\n"
		);
		// ***
		
		addSection(changeInfos, BUFFS);
		// ***
		addChange(new MossyClump(), "Mossy Clump and Petrified Seed",
				"_- Mossy Clump:_\n" +
						"Energy cost: 10 + 5/level -> 8 + 4/level\n" +
						"Grassy/Water feeling ratio: 40/60 -> 50/50\n" +
						"\n" +
						"_- Petrified Seed:_\n" +
						"Seed and stone drop increase: 0%/8.33%/16.66%/25% -> 0%/10%/20%/30%\n" +
						"\n" +
						"Stone conversion ratio: 25%/46%/65%/80% -> 25%/45%/62.5%/77.5%"
		);
		
		addChange(new ChaliceOfBlood(), "Chalice of Blood",
				"Pricking's upgrade won't get cancelled when dying with an unblessed ankh anymore."
		);
		
		Ankh ankh = new Ankh();
		ankh.bless();
		addChange(ankh, "Blessed Ankhs",
				"Now also fully restores hunger, same as unblessed ankhs.\n" +
						"Heal: 1/4 max HP -> 1/3 max HP"
		);
		
		addChange(new BruteSprite(), "Gnoll Brute",
				"_New:_ Now takes a % of all ranged attack's damage as deferred (delayed). The % increases based on distance.\n" +
						"\n" +
						"Shield decay speed: 4 -> 3"
		);
		// ***
		
		addSection(changeInfos, NERFS);
		// ***
		addChange(heroIcon(HeroClass.MAGE, 1), "Mage",
				"_- Backup barrier:_\n" +
						"Shield: 3/5 -> 2/4\n" +
						"\n" +
						"_- Battery shield:_\n" +
						"Shield per charge: 4/6% max HP -> 3.5/5% max HP\n" +
						"\n" +
						"_- Warlock:_\n" +
						"Heal ratio: 0.4 -> 0.33\n" +
						"\n" +
						"No longer heals when starving.\n" +
						"Note that Soul Eater occurs AFTER the heal."
		);
		
		addChange(heroIcon(HeroClass.DUELIST, 6), "Duelist Talents",
				"_- Swift Equip:_\n" +
						"Renamed to _Swift Swap:_\n" +
						"Only activates when swapping from one weapon to another. This means you can avoid spending it by manually unequipping, then equipping.\n" +
						"\n" +
						"Swaps: 1/2 every 20 turns -> 1/2 every 25/35 turns\n" +
						"\n" +
						"_- Monastic Vigor:_\n" +
						"Empowered threshold: 100/80/60% -> 100/85/70%\n" +
						"\n" +
						"_- Unencumbered Spirit:_\n" +
						"Energy boost: 50/75/100% -> 40/60/80%"
		);
		
		addChange(heroIcon(HeroClass.CLERIC, 6), "Cleric Spells",
				"_- Searing Light:_\n" +
						"Bonus damage: 4/6 -> 2/3\n" +
						"\n" +
						"_- Recall Inscription:_\n" +
						"Turn window: 10/300 -> 10/50\n" +
						"\n" +
						"_- Sunray:_\n" +
						"Blind duration: 4/6 -> 3/5\n" +
						"\n" +
						"_- Bless:_\n" +
						"Bless duration: 6/10 -> 5/8\n" +
						"Shield/Heal: 10/15 -> 8/12\n" +
						"\n" +
						"_- Cleanse:_\n" +
						"Shielding: 10/20/30 -> 7/14/21\n" +
						"Turns of immunity: 3/5/7 -> 0/2/4\n" +
						"Note that +1 still removes current negative effects.\n" +
						"\n" +
						"_- Holy Lance:_\n" +
						"Charge cost: 4 -> 5\n" +
						"\n" +
						"_- Hallowed Ground:_\n" +
						"No longer shields the player.\n" +
						"\n" +
						"_- Smite:_\n" +
						"Enchant boost: +300% -> +100%\n" +
						"Bonus damage (min): 5 + level/2 -> 5 + level/4\n" +
						"Bonus damage (max): 10 + level -> 10 + level/2\n" +
						"\n" +
						"_- Lay On Hands:_\n" +
						"Shield/Heal: 10/15/20 -> 5/10/15\n"
		);
		// ***
		
		
		currentSection = null;
	}
	
	public static void add_v1_5_Changes( ArrayList<ChangeInfo> changeInfos ) {
		
		addVersion(changeInfos, "v1.5.8");
		
		addSection(changeInfos, NEW);
		// ***
		addChange(Icons.SHPX, "Shattered Pixel Dungeon 3.2",
				"Polished Pixel Dungeon got updated to the latest SPD version:\n" +
						"3.1.1 -> 3.2.0"
		);
		
		addChange(Talent.HOLD_FAST, "Hold Fast",
				"Since PPD berserker's shield doesn't decay with time, Hold Fast instead preserves 33%/67%/100% of remaining shield after rampage as regular shielding."
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(heroIcon(HeroClass.WARRIOR, 6), "Berserker",
				"Nerfs from SPD discarded, since they don't interact with PPD version.\n" +
						"Rampage accuracy boost shares its hit icon with swords dance."
		);
		
		addChange(new Tomahawk(), "Tomahawk",
				"Changes from PPD to Tomahawk discarded, now matches SPD 3.2"
		);
		
		addChange(Talent.PROJECTILE_MOMENTUM, "Projectile Momentum",
				"Backtracked buffs from SPD 3.2:" +
						"\n" +
						"Accuracy boost: 50%/100%/150% -> 33%/67%/100%"
		);
		// ***
		
		
		
		addVersion(changeInfos, "v1.5.7");
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(new Greataxe(), "Weapon Encumbrance",
				"Overweight weapons are no longer guaranteed to fail at surprise attacks.\n" +
						"\n" +
						"Instead, they now have a 50%/75%/87.5%... chance to fail (and do a regular attack instead), depending on the strength difference."
		);
		
		addChange(new EarthGuardianSprite(), "Allies",
				"_- Earth Guardian:_\n" +
						"Vision range: 8 -> 5\n" +
						"Armor: 3 + 3 * wand level -> 3 + 2 * wand level\n" +
						"\n" +
						"Armor doesn't decrease on FIMA (challenge) anymore.\n" +
						"Numbers are: 2 + wand level -> 3 + 2 * wand level\n" +
						"\n" +
						"_- Prismatic Image:_\n" +
						"Vision range: 8 -> 3\n" +
						"\n" +
						"Summons itself when an enemy is within:\n" +
						"4 tiles -> 3 tiles\n" +
						"Now tries to spawn before mob's turn to act.\n" +
						"\n" +
						"_- Scroll of Mirror Image:_\n" +
						"Images spawned: 2 -> 3"
		);
		
		addChange(GAMEPLAY(), "Gameplay Adjustments",
				"Main NPCs now show in fog. This includes:\n" +
						"Shopkeeper, Ghost, Wandmaker, Blacksmith, Imp.\n" +
						"\n" +
						"Crystal Mimic escape distance: 6 -> 7\n" +
						"(Now matches Thief's).\n" +
						"\n" +
						"Teleportation trap now shows where teleported items end up in fog.\n" +
						"\n" +
						"Game no longer prevents you from swapping with allies standing on traps.\n" +
						"\n" +
						"Non-Polished nature's aid no longer requires vision of the plant activated, just having it mapped."
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(Icons.DISPLAY_LAND, "UI and Camera",
				"_- Alchemy scene:_\n" +
						"Recipe slots:\n" +
						"Long tap / Right click on an item will show info.\n" +
						"(PC) Middle click on an item will remove it.\n" +
						"\n" +
						"Item selection bag:\n" +
						"Long tap / Right click on an item will open up the energize window.\n" +
						"\n" +
						"_- Item slots:_\n" +
						"Now prioritize displaying their custom note's title when hovered, instead of their item's name.\n" +
						"\n" +
						"_- CAMERA:_\n" +
						"Opening hero's tab using hotkeys, or tapping on the icon when camera intensity is set to low, no longer moves the camera.\n" +
						"\n" +
						"Increased zooming speed\n" +
						"Adjusted follow intensity."
		);
		
		addChange(Icons.BACKPACK_LRG, "Inventory Sorting",
				"Weapons, Armor and Throwies now sort themselves based on tier.\n" +
						"Within the same tier, items sort themselves based on level.\n" +
						"\n" +
						"Darts now always go after other Throwies.\n" +
						"Fixed order for bombs, honeypots, torches and seeds."
		);
		
		addChange(QOL(), "Quality of Life",
				"Tapping twice no longer cancels opening a chest/door.\n" +
						"Hero now interrupts movement after every step while affected by vertigo.\n" +
						"\n" +
						"Input block now triggers when hero gets hit from out of vision.\n" +
						"Input block now triggers on explosive's curse chat warning.\n" +
						"\n" +
						"Implemented warning for when hero tries to aqua brew on sentry room."
		);
		
		addChange(BUGFIX(), "Bugfixes",
				"- Wand of Lightning no longer considers flying enemies as standing on water (same as with shocking).\n" +
						"\n" +
						"- Explosive no longer shows warning messages on chat when characters other than hero use it.\n" +
						"\n" +
						"- Hero no longer tries to pick up items in fog."
		);
		// ***
		
		addSection(changeInfos, BUFFS);
		// ***
		addChange(new Stylus(), "Glyphs",
				"On-hit glyphs, and Glyph of Stone, now also activate against skeleton explosions.\n" +
						"\n" +
						"Glyph of Stone now also protects against magic attacks, instead of just disabling your evasion for no benefit."
		);
		
		ItemSprite shortsword = itemIcon(ItemSpriteSheet.SHORTSWORD);
		shortsword.glow(new ItemSprite.Glowing(0x000000));
		addChange(shortsword, "Explosive Curse",
				"Chat warning now guarantees the very next attack will cause an explosion.\n" +
						"\n" +
						"Average turns per explosion: 20 -> 21\n" +
						"Variance decreased."
		);
		
		addChange(new Pasty(), "Pasties and Alchemize",
				"_- Pasties:_\n" +
						"Generate every: 5 floors -> 4 floors\n" +
						"This ensures 1 per region.\n" +
						"\n" +
						"_- Alchemize:_\n" +
						"Base price per unit: 12.5 -> 10\n" +
						"Recipe energy cost: 2/1 -> 1/0"
		);
		
		addChange(Talent.NATURES_AID, "Nature's Aid (Polished)",
				"Activates every: 7/5 charges -> 6/5 charges"
		);
		// ***
		
		addSection(changeInfos, NERFS);
		// ***
		addChange(Talent.NATURES_BOUNTY, "Nature's Bounty",
				"Berries obtained: 4/6 -> 4/5"
		);
		// ***
		
		
		
		addVersion(changeInfos, "v1.5.6");
		
		addSection(changeInfos, NEW);
		// ***
		addChange(new SpiritHawk.HawkSprite(), "Directable Allies",
				"- All directable allies now share their vision with Hero.\n" +
						"\n" +
						"- Vision ranges adjusted:\n" +
						"Ghost: 0 tiles (just its own)\n" +
						"Shadow Clone: 3 tiles\n" +
						"Light Ally: 3 tiles\n" +
						"Spirit Hawk (base): 6 tiles -> 5 tiles\n" +
						"\n" +
						"- They will also display their current path for the player to see. The color of the path is associated with its command type.\n" +
						"\n" +
						"\n" +
						"- You can now choose where to initially summon them: any tile within a 5x5 area around you.\n" +
						"\n" +
						"- (PC) Making a command via quickslots works immediately now, instead of asking for confirmation.\n" +
						"\n" +
						"- You can now chain multiple commands by tapping the item twice, or by (PC) holding left shift.\n" +
						"\n" +
						"\n" +
						"- AI adjusted, removed some of their free will: will follow Hero's commands instead of randomly attacking mobs as often.\n" +
						"\n" +
						"- All Power of Many allies are preserved through floors now, not just intelligent ones.\n" +
						"\n" +
						"- Ghost HP scaling: +8/level -> +10/level\n" +
						"\n" +
						"\n" +
						"- Won't spam sound effects or clog the chat with commands anymore.\n" +
						"\n" +
						"- Hero will automatically path through them (by swapping positions) instead of getting blocked by their presence.\n" +
						"\n" +
						"- Added a buff icon for Spirit Hawk's lifetime"
		);
		
		addChange(itemIcon(ItemSpriteSheet.CURSED_CHAINS), "Cursed Artifact Sprites",
				"Added new sprites to all cursed artifacts."
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		RoundShield shield = new RoundShield();
		shield.enchant(Reflection.newInstance(Blocking.class));
		addChange(shield, "Blocking Rework",
				"- Shield strength: 2 + level -> 4 + level/2 (rounded)\n" +
						"- Proc chance: 10%/12%/14%... -> 18%/19.5%/21%...\n" +
						"\n" +
						"- Shielding now breaks after the first hit.\n" +
						"- Duration: 6 turns -> 5 turns"
		);
		
		Image arrow = Icons.ARROW.get();
		arrow.brightness(1.25f);
		addChange(arrow, "Resume Indicator",
				"On resume, all visible mobs will now get marked and won't interrupt the hero again until a new action is commanded.\n" +
						"\n" +
						"No longer disables damage interrupt, unless damage was the reason for the previous interruption.\n" +
						"\n" +
						"Fixed bugs that made it unresponsive, and some actions not being remembered."
		);
		
		addChange(QOL(), "Quality of Life",
				"- Various gases like toxic gas, corrosive gas, paralytic gas, among others:\n" +
						"Now update visuals faster, both spreading and fading more quickly.\n" +
						"\n" +
						"- When _Input Block_ is enabled, doors opening in fog in close proximity to hero will pause his movement.\n" +
						"\n" +
						"- Target Cycling (mainly on PC with TAB) now prioritizes closer enemies, shifting through targets in order.\n" +
						"\n" +
						"- Hero no longer avoids picking up items when all visible mobs are far away.\n" +
						"\n" +
						"- Some runestones now use auto-targeting."
		);
		
		addChange(BUGFIX(), "Bugfixes",
				"- Runestones no longer activate when thrown into a well of Transmutation.\n" +
						"\n" +
						"- Wait button in toolbar now correctly highlights when used with a hotkey.\n" +
						"\n" +
						"- Quickslot placeholders no longer show their quantity as 0."
		);
		// ***
		
		addSection(changeInfos, BUFFS);
		// ***
		addChange(new WandOfCorruption(), "Necromancer Build",
				"_- Wand of Corruption:_\n" +
						"Corrupting power: 3 + level/3 -> 4 + level/3\n" +
						"\n" +
						"_- Dried Rose:_\n" +
						"Wraith spawn rate when cursed: 1% per turn -> 2% per turn"
		);
		
		addChange(new WandOfTransfusion(), "Wand of Transfusion",
				"Self damage when shielding ally: 5% max HP -> 3% max HP\n" +
						"\n" +
						"Shield given to allies: self damage -> self damage + 3\n" +
						"Shield given can be stacked up to 3x now."
		);
		
		Sickle sickle = new Sickle();
		sickle.enchant(Reflection.newInstance(Blooming.class));
		addChange(sickle, "Blooming and Kinetic",
				"_- Blooming:_\n" +
						"Now generates an additional patch of furrowed grass.\n" +
						"\n" +
						"_- Kinetic:_\n" +
						"Decay speed: -2.5% per turn -> -2% per turn"
		);
		// ***
		
		addSection(changeInfos, NERFS);
		// ***
		addChange(new EtherealChains(), "Ethereal Chains and Horn of Plenty",
				"_- Ethereal Chains:_\n" +
						"Gains charges over time ~25% slower.\n" +
						"Charges gained by XP unchanged.\n" +
						"\n" +
						"_- Horn of Plenty:_\n" +
						"Food generation scaling: +50%/level -> +40%/level"
		);
		// ***
		
		
		
		addVersion(changeInfos, "v1.5.5");
		
		addSection(changeInfos, NEW);
		// ***
		addChange(Icons.SHPX, "Shattered Pixel Dungeon 3.1",
				"Polished Pixel Dungeon got updated to the latest SPD version:\n" +
						"3.0.2 -> 3.1.1"
		);
		
		addChange(new BrokenSeal(), "Runic Transference Rework",
				"Can now carry any glyph no matter the tier.\n" +
						"\n" +
						"Instead of overwriting the armor's glyph, both the seal and the armor carry completely separate glyphs.\n" +
						"\n" +
						"You may choose to swap between them as you please. Quickslotting the armor will let you do so.\n" +
						"\n" +
						"At +2, both glyphs are active at the same time!\n" +
						"\n" +
						"Reads like this:\n" +
						"_+1:_ The Warrior's broken seal can also carry glyphs. When attached to an armor, you can choose which one should be active.\n" +
						"— — — — — — — -\n" +
						"_+2:_ The Warrior's broken seal can also carry glyphs. Both the armor and the seal's glyph will be active at the same time, so long as they're different.\n" +
						"\n" +
						"Can now use curse infusion, stylus, etc. on the seal directly.\n" +
						"Curse infusing the seal will grant you an additional upgrade bonus!"
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(heroIcon(HeroClass.CLERIC, 6), "Cleric",
				"Backtracked some changes from the SPD 3.1 update:\n" +
						"\n" +
						"_- Guiding Light:_\n" +
						"Base damage: 2-8 -> 2-6\n" +
						"\n" +
						"_- Lay on Hands:_\n" +
						"Shield/Heal: 15/20/25 -> 10/15/20\n" +
						"\n" +
						"_- Aura of Protection:_\n" +
						"Damage resistance: 20%/30%/40% -> 15%/23%/30%\n" +
						"\n" +
						"_- Hallowed Ground:_\n" +
						"Root duration: 2 turns -> 1 turn\n" +
						"\n" +
						"_- Searing Light:_\n" +
						"Bonus damage already nerfed on Polished. Remains unchanged at +2/+3\n" +
						"\n" +
						"_- Hallowed Ground:_\n" +
						"Barrier cap: 30 -> no cap\n" +
						"\n" +
						"_- Flash:_\n" +
						"Charge cost: 2 -> 1\n" +
						"\n" +
						"_- Stasis:_\n" +
						"Charge cost: 2 -> 1\n" +
						"Duration: 150% -> 100%"
		);
		// ***
		
		addSection(changeInfos, BUFFS);
		// ***
		addChange(new Stylus(), "Glyphs",
				"All on-hit glyphs now also activate against magical attacks.\n" +
						"This only applies for enemy zaps, Hero sources like wands remain unchanged.\n" +
						"\n" +
						"_- Viscosity:_\n" +
						"Now also defers fall damage from chasms.\n" +
						"\n" +
						"_- Thorns:_\n" +
						"Against inorganic enemies, now deals upfront damage instead of trying to apply bleed.\n" +
						"This damage goes through shields, but can't drop the HP of an enemy below 1!"
		);
		
		addChange(new BrokenSeal(), "Broken Seal",
				"Backtracked some nerfs from the SPD 3.1 update:\n" +
						"\n" +
						"Cooldown: 150 turns -> 120 turns\n" +
						"Can be attached to cursed armor like before."
		);
		// ***
		
		addSection(changeInfos, NERFS);
		// ***
		addChange(new HermitCrabSprite(), "Hermit Crab",
				"HP: 25 -> 20"
		);
		// ***
		
		
		
		addVersion(changeInfos, "v1.5.4");
		
		// ***
		addSection(changeInfos, EMPTY);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(new Scimitar(), "Fast Weapons",
				"_- Scimitar:_\n" +
						"Attack speed boost: +25% -> +33%\n" +
						"Base damage: 3-16 -> 3-15\n" +
						"\n" +
						"_- Katana Rework:_\n" +
						"_Removed:_ No longer grants 0-3 defense.\n" +
						"Instead works like Scimitar, granting a +33% attack speed boost.\n" +
						"\n" +
						"_Stone Gauntlet Buff:_\n" +
						"Base damage: 5-15 -> 5-16"
		);
		
		addChange(new GuardSprite(), "Prison Enemies",
				"_- Guard:_\n" +
						"HP: 40 -> 45\n" +
						"Defense: 0-7 -> 0-6\n" +
						"Damage: 4-12 -> 3-12\n" +
						"\n" +
						"_- Necromancer:_\n" +
						"HP: 40 -> 45\n" +
						"Defense: 0-5 -> 0-3\n" +
						"\n" +
						"_- Necromancer's Skeleton:_\n" +
						"HP: 25 -> 20\n" +
						"Damage: 2-10 -> 2-12\n" +
						"\n" +
						"_- Thief:_\n" +
						"Damage: 1-10 -> 2-10\n" +
						"\n" +
						"Evasion: 12 -> 15, drops to 10 when carrying an item.\n" +
						"Accuracy: 12 -> 13, drops to 10 when carrying an item.\n" +
						"\n" +
						"Chance to steal: varies depending on inventory -> always 67%\n" +
						"Minimum distance to escape: 6 tiles -> 7 tiles\n"
		);
		// ***
		
		addSection(changeInfos, BUFFS);
		// ***
		addChange(new WarHammer(), "Accuracy Weapons",
				"_- Cudgel:_\n" +
						"Accuracy boost: +40% -> +50%\n" +
						"\n" +
						"_- Hand Axe:_\n" +
						"Accuracy boost: +32% -> +50%\n" +
						"\n" +
						"_- Mace:_\n" +
						"Accuracy boost: +28% -> +50%\n" +
						"\n" +
						"_- Battle Axe:_\n" +
						"Accuracy boost: +24% -> +50%\n" +
						"\n" +
						"_- War Hammer:_\n" +
						"Accuracy boost: +20% -> +50%"
		);
		
		addChange(heroIcon(HeroClass.DUELIST, 6), "Champion",
				"_Twin Upgrades_ became part of the Champion as a baseline:\n" +
						"Any weapon that's 2+ tiers lower will be boosted by default.\n" +
						"Upgrading the talent will instead make the effect stronger.\n" +
						"\n" +
						"_- Twin Upgrades:_\n" +
						"Max tier: 2 lower/1 lower/same tier ->\n" +
						"1 lower/same tier/1 higher\n" +
						"\n" +
						"_- Precise Assault:_\n" +
						"Accuracy on her next melee attack:\n" +
						"2x/5x/infinite -> 3x/infinite/infinite\n" +
						"\n" +
						"At +3, now also gains 3x accuracy on her SECOND next melee attack, within 5 turns.\n" +
						"\n" +
						"_- Varied Charge:_\n" +
						"Weapon charges regained: 0.17/0.33/0.5 -> 0.2/0.36/0.5"
		);
		// ***
		
		addSection(changeInfos, NERFS);
		// ***
		addChange(new WarScythe(), "War Scythe",
				"_- War Scythe:_\n" +
						"Accuracy penalty: -20% -> -25%"
		);
		
		addChange(heroIcon(HeroClass.DUELIST, 6), "Monk",
				"No longer gains energy when chasming enemies.\n" +
						"\n" +
						"_- Meditate:_\n" +
						"No longer grants artifact recharge (wand recharge untouched).\n" +
						"_New:_ Monk doesn't consume hunger while meditating.\n" +
						"\n" +
						"When empowered:\n" +
						"80% damage resistance -> 100% damage resistance\n" +
						"_Removed:_ No longer heals."
		);
		
		addChange(new Dirk(), "Weapon Abilities\nSneak and Guard",
				"_- Sneak:_\n" +
						"Isn't instantaneous anymore.\n" +
						"\n" +
						"Teleport distance:\n" +
						"- Assassin's Blade: 3 -> 2\n" +
						"- Dirk: 4 -> 2\n" +
						"- Dagger: 5 -> 3\n" +
						"\n" +
						"_- Guard:_\n" +
						"Duration multiplied by 2x.\n" +
						"\n" +
						"_New:_ has a limited number of blocks:\n" +
						"Round shield: 3 + level\n" +
						"Great shield: 2 + level"
		);
		// ***
		
		
		
		addVersion(changeInfos, "v1.5.3");
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(itemIcon(ItemSpriteSheet.CHEST), "Room's Loot",
				"_- Pit room (distant well):_\n" +
						"Now uses default chances for potions/scrolls/food, no longer \"steals\" purity/lullaby/pasties.\n" +
						"\n" +
						"_- Secret larder room:_\n" +
						"Food loot no longer scales with depth.\n" +
						"Instead, always contains 2 Chargrilled/Carpaccio meats.\n" +
						"\n" +
						"_- Altar and Crypt room:_\n" +
						"Chance of loot being +1 or higher: 70% -> 100%"
		);
		
		addChange(new Food(), "Food Drops",
				"Crabs, Albino rats, Spinners, Monks, and Senior monks:\n" +
						"Base drop rates increased.\n" +
						"\n" +
						"Similarly to Potions of Healing and other farmable resources, their rates now decrease after every drop.\n" +
						"Reaches 0% after 6 drops, counted separately for each individual enemy type."
		);
		
		addChange(itemIcon(ItemSpriteSheet.BONES), "Mob Drops",
				"_- DM100 scrolls:_\n" +
						"25% -> 20%\n" +
						"\n" +
						"_- Shock Elemental scrolls:_\n" +
						"25% -> 20%\n" +
						"\n" +
						"_- Succubus scrolls:_\n" +
						"33% -> 25%\n" +
						"\n" +
						"_- Warlock potions:_\n" +
						"50% -> 33%\n" +
						"\n" +
						"_- Scorpio potions:_\n" +
						"50% -> 33%\n" +
						"\n" +
						"_- Brute's gold:_\n" +
						"100% quantity -> 75% quantity"
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(Icons.STAIRS, "Level Feelings",
				"Level feelings now use deck system.\n" +
						"This means they have a lower chance of repeating.\n" +
						"\n" +
						"_- Dark Feeling:_\n" +
						"Vision multiplier: 0.625 -> 0.667\n" +
						"Can't drop vision below 2 tiles anymore.\n" +
						"Now also reduces torch duration to 0.667x (250 -> 167)."
		);
		
		addChange(new ImpSprite(), "Demon Halls Torches",
				"_- Imp shop:_\n" +
						"Torches sold: 3 -> 2\n" +
						"Torch cost: 200 -> 300\n" +
						"\n" +
						"_- Yog's floor:_\n" +
						"Now generates 1 torch at the entrance.\n" +
						"\n" +
						"Dark fist no longer weakens your torch if you have debuff immunity.\n" +
						"\n" +
						"_- Demon Halls:_\n" +
						"Torches generated on Into Darkness: 3 -> 1\n" +
						"The default amount, remains unchanged at 2.\n" +
						"\n" +
						"Generates an additional torch on large floors: when Into Darkness is on -> always"
		);
		
		addChange(new Torch(), "Fog of War and Exploration",
				"- Items thrown by Hero onto fog will be automatically revealed.\n" +
						"- Mob drops on fog will be automatically revealed.\n" +
						"\n" +
						"- All mimics remain visible in fog now, not just stealthy ones (Mimic Tooth).\n" +
						"\n" +
						"_- Crystal choice/path rooms:_\n" +
						"Half of the potions/scrolls are now guaranteed to generate next to the crystal door.\n" +
						"The other half tries to generate away from the door, and won't count for exploration score anymore.\n" +
						"\n" +
						"_- Goo boss floor:_\n" +
						"View distance set to a minimum of 3.\n" +
						"QoL: Goo will delay 1 turn when trying to walk into Hero from out of vision, when the fight hasn't started yet."
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(Icons.MAGNIFY, "Last Found Items",
				"Some items now show the last floors they were found at.\n" +
						"More specifically, unidentified potions, unidentified scrolls, food, and torches.\n" +
						"\n" +
						"This only accounts for level generated items, NOT mob drops or other sources."
		);
		
		addChange(Icons.TOXIC_GAS_ROOM, "Puzzle Landmarks",
				"Puzzle rooms now have unique floor landmarks upon discovery.\n" +
						"These landmarks are automatically removed once the room is \"solved\"."
		);
		
		addChange(Icons.SNAKE, "Mobs QoL",
				"- Added turn wheel display for mobs.\n" +
						"When examining a mob, you can now see their internal turn wheel.\n" +
						"\n" +
						"Shows what fraction of a turn is left until their turn to act.\n" +
						"If the total delay is over 1 turn, turns red.\n" +
						"\n" +
						"- Added a display for mob's buffs, set above their heads.\n" +
						"When a buff is about to decay next turn, turns red."
		);
		
		addChange(Icons.SCROLL_COLOR, "Notes QoL",
				"After holding the Journal button, you can now select an item dropped on the floor to quickly add/edit custom notes.\n" +
						"\n" +
						"(PC) There's now an additional option when right clicking an item (inventory/dropped on the floor), to quickly add/edit custom notes.\n" +
						"This option only appears for unidentified potions/scrolls/rings.\n" +
						"\n" +
						"(PC) When right clicking an item with a custom note, its name will now be replaced with the note's title.\n" +
						"\n" +
						"- New setting (PC): _Flip Right Click Menu:_\n" +
						"Inverts the order of actions on the right click menu.\n" +
						"Disabled by default.\n" +
						"\n" +
						"(PC) You can now press escape to exit the note's text editor.\n" +
						"When renaming a note to -empty- remove it instead.\n" +
						"\n" +
						"- New setting: _Discard IDed Notes:_\n" +
						"Automatically deletes notes for potions/scrolls/rings when identified.\n" +
						"Disabled by default."
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(Icons.DISPLAY_LAND, "Quickslots",
				"- New setting: _Extra Quickslots:_\n" +
						"Allows the player to adjust the number of quickslots.\n" +
						"\n" +
						"To make space for the additional ones, there's a new option at _Toolbar Settings:_\n" +
						"- Crop: Only show as many quickslots as the toolbar can fit.\n" +
						"- Swap: Use the swapper to alternate between halfs.\n" +
						"_- Stack:_ Stack the extra quickslots on a second layer, above the toolbar.\n" +
						"\n" +
						"(PC) You can now middle click a quickslot to clear its item.\n"
		);
		
		addChange(QOL(), "Quality of Life",
				"- Alchemize and Stone of Intuition now remain selected after using them.\n" +
						"\n" +
						"- Implemented blacksmith sound effect fadeout.\n" +
						"\n" +
						"- Auto-target will no longer lock onto unreachable enemies, or targets you haven't shot yet.\n" +
						"\n" +
						"- Hero's pane no longer repeatedly blinks after level transitions, when you have unspent talent points.\n" +
						"\n" +
						"- Default action for equippable items set to equip/unequip, as long as no other action is available.\n" +
						"\n" +
						"- When the waterskin has no dewdrops collected, its default action is now to be thrown.\n" +
						"\n" +
						"- Toxic imbue leftover immunity received a buff icon."
		);
		
		addChange(BUGFIX(), "Bugfixes",
				"- More buff duration consistency issues: sources of immunity, paralysis, corruption wand debuffs, and others, " +
						"now align themselves properly with the turn wheel.\n" +
						"\n" +
						"- Mob spawner becoming desynced, spawning mobs on partial turns.\n" +
						"\n" +
						"- Energizing items sometimes taking time.\n" +
						"\n" +
						"- Shroud fog asking for confirmation on throw.\n" +
						"\n" +
						"- Enemies not waking up when facing characters other than Hero.\n" +
						"\n" +
						"- Giant spinner blocking itself with its own web.\n" +
						"\n" +
						"- UI scale reseting after player adjust.\n" +
						"\n" +
						"- Game showing health bar of hidden mimics.\n" +
						"- Auto-target locking onto hidden mimics."
		);
		// ***
		
		
		
		addVersion(changeInfos, "v1.5.2");
		
		addSection(changeInfos, NEW);
		// ***
		addChange(itemIcon(ItemSpriteSheet.RING_AMETHYST), "Ring of Wealth Rework",
				"- No longer generates equippables, stones of enchantment and other permanent boosts. Exclusively gives the hero consumables.\n" +
						"\n" +
						"- Wealth drops now decay over time:\n" +
						"Items last for 200 + 50/level turns before decaying.\n" +
						"\n" +
						"- Trying to drop an item to the floor will evaporate it.\n" +
						"\n" +
						"- Wealth drops can't be energized, sold, or transmuted.\n" +
						"\n" +
						"- Item pool revamped:\n" +
						"Generates a bunch of new items, such as spells, elixirs, and bombs.\n" +
						"Probability table adjusted.\n" +
						"\n" +
						"- Every 5-7 drops, also generates alchemizes.\n" +
						"\n" +
						"- You can now quickslot the ring: while in toolbar, will show the lifetime of the oldest drop.\n" +
						"When tapping on it, will display all wealth drops on your inventory for ease of use."
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(new InfernalBrew(), "Infernal and Blizzard Brews",
				"Removed their recipes for simplicity.\n" +
						"Instead, they're now part of their elixir counterparts.\n" +
						"\n" +
						"Elixirs of Dragon's Blood and Icy Touch:\n" +
						"When thrown instead of drank, will create an infernal/blizzard gas respectively."
		);
		// ***
		
		addSection(changeInfos, BUFFS);
		// ***
		addChange(itemIcon(ItemSpriteSheet.RING_TOPAZ), "Ring of Furor and Elements",
				"_- Ring of Furor:_\n" +
						"Attack speed boost:\n" +
						"9.05%/level -> 10%/level\n" +
						"\n" +
						"_- Ring of Elements:_\n" +
						"Now also resists Rooted and Slowed."
		);
		
		addChange(new Alchemize(), "Alchemize",
				"Recipe: seed + stone + 2 energy -> seed/stone + 2/1 energy\n" +
						"Output quantity: 8 -> 12\n" +
						"\n" +
						"Base price in shops: 25 per unit -> 12.5 per unit\n" +
						"Amount sold in shops: 2-3 -> 8\n" +
						"\n" +
						"No longer takes time to use.\n" +
						"Can now energize itself."
		);
		
		addChange(new ShockingBrew(), "Alchemy Recipes",
				"_- Shocking Brew:_\n" +
						"Energy cost: 10 -> 6\n" +
						"Output: 1 -> 2\n" +
						"\n" +
						"_- Honeyed Healing:_\n" +
						"Energy cost: 4 -> 2\n" +
						"\n" +
						"_- Elixir of Might:_\n" +
						"Energy cost: 16 -> 12"
		);
		
		addChange(buffIcon(BuffIndicator.IMMUNITY), "Potion Effects",
				"_- Purity:_\n" +
						"Potion duration: 20 -> 60\n" +
						"Now also grants immunity to rockfall.\n" +
						"\n" +
						"_- Levitation:_\n" +
						"Potion duration: 20 -> 25\n" +
						"\n" +
						"_- Toxic Gas:_\n" +
						"Now spawns pre-expanded.\n" +
						"\n" +
						"_- Magical Sight:_\n" +
						"Potion duration: 50 -> 60\n" +
						"\n" +
						"_- Fire Imbue:_\n" +
						"Ignite chance: 50% -> 100%"
		);
		// ***
		
		addSection(changeInfos, NERFS);
		// ***
		addChange(new CausticBrew(), "Caustic Brew",
				"Energy cost: 1 -> 2"
		);
		// ***
		
		
		
		addVersion(changeInfos, "v1.5.1");
		
		addSection(changeInfos, NEW);
		// ***
		addChange(itemIcon(ItemSpriteSheet.EXOTIC_ISAZ), "Scrolls of Identify and Divination",
				"_- Scroll of Identify:_\n" +
						"Now also identifies all held copies of the selected item.\n" +
						"\n" +
						"_- Scroll of Divination:_\n" +
						"_New:_ Now lets the hero reveal any 1 item type of their choosing.\n" +
						"The other three will now always be: 1 random potion, 1 random scroll, and 1 random ring."
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(heroIcon(HeroClass.ROGUE, 1), "Cloak and Freerunner",
				"_- Cloak of Shadows:_\n" +
						"Turns to recharge: 45 -> 55\n" +
						"Scales with missing charges ~25% faster\n" +
						"\n" +
						"_- Projectile Momentum:_\n" +
						"Damage bonus: 15%/30%/45% -> 10%/20%/30%\n" +
						"\n" +
						"_- Evasive Armor:_\n" +
						"Now ALWAYS grants additional evasion, not just while freerunning."
		);
		
		addChange(Icons.CHALLENGE_COLOR, "Champions and Pharmacophobia",
				"_- Growing champions:_\n" +
						"Now spawn near the exit.\n" +
						"They will camp there until spotting an enemy/getting hit.\n" +
						"\n" +
						"_- Pharmacophobia:_\n" +
						"Poison total damage: ~40% max HP -> ~25% max HP"
		);
		
		addChange(GAMEPLAY(), "Gameplay Adjustments",
				"- Gladiator's Slam now deals bonus damage based on his seal's blocking power, instead of his armor's defense.\n" +
						"\n" +
						"- Huntress's Berries now drop a seed on the first eat, instead of the second one. They're also found more quickly/consistently.\n" +
						"\n" +
						"- Warrior's Iron Stomach now lasts through floor transitions: this means it can be used to tank chasm damage when combined with other tools.\n" +
						"\n" +
						"- Stone of Detect Magic no longer detects enchantments and glyphs as positive (since they're already visible anyway).\n" +
						"\n" +
						"- Initial floor mobs won't spawn as close to the entrance anymore.\n" +
						"\n" +
						"- Sacrificial Altar begins to spawn extra mobs earlier.\n" +
						"\n" +
						"- Unstable Brew can no longer roll Purity when thrown."
		);
		
		addChange(new ShopkeeperSprite(), "Shop Changes",
				"- Items sold to shopkeeper will be preserved through regions, you can use this for storage.\n" +
						"Only exception is the Imp who has a separate record.\n" +
						"\n" +
						"- Selling and recovering items on shops doesn't take time anymore.\n" +
						"- Items sold will now try to stack."
		);
		// ***
		
		addSection(changeInfos, BUFFS);
		// ***
		addChange(new BruteSprite(), "Gnoll Brute and Swarm of Flies",
				"_- Gnoll Brute:_\n" +
						"Now cleanses all its debuffs upon going berserk.\n" +
						"\n" +
						"_- Swarm of Flies:_\n" +
						"No longer shares burning/poison to its children."
		);
		
		addChange(new GolemSprite(), "Large Enemies",
				"Improved AI. They shouldn't get stuck on doors, trying to path to unreachable tiles, unless constantly attacked.\n" +
						"Instead, will stick to wandering inside their room.\n" +
						"\n" +
						"Golem teleport no longer gets randomly cancelled, having to restart from scratch."
		);
		// ***
		
		// ***
		addSection(changeInfos, EMPTY);
		// ***
		
		
		
		addVersion(changeInfos, "v1.5.0");
		
		addSection(changeInfos, NEW);
		// ***
		addChange(tileIcon(Assets.Environment.TERRAIN_FEATURES, 16, 96), "Blasting Trap",
				"New trap: _Blasting Trap!_\n" +
						"Works similarly to an explosive trap, but:\n" +
						"\n" +
						"- Explosion radius is 5x5.\n" +
						"- Deals 25% less damage.\n" +
						"\n" +
						"- Also destroys equippable items up to +2."
		);
		
		addChange(buffIcon(BuffIndicator.DISORIENTED), "Disoriented",
				"- New debuff: _Disoriented!_\n" +
						"When the Hero becomes disoriented, they temporarily lose their memory of the floor's layout.\n" +
						"\n" +
						"_- Warping Trap:_\n" +
						"Floor's layout isn't permanently forgotten when Hero steps on it anymore.\n" +
						"Instead, applies _Disoriented_ for a set duration.\n" +
						"\n" +
						"_- Unblessed Ankh:_\n" +
						"Floor's layout isn't permanently forgotten when Hero dies anymore.\n" +
						"Instead, applies _Disoriented_ for a set duration."
		);
		
		addChange(buffIcon(BuffIndicator.FURY), "Fury Buff",
				"- New buff: _Fury!_\n" +
						"Gives a +30% physical damage boost."
		);
		
		addChange(new HoneyedMeat(), "Honeyed Meat",
				"- New item: _Honeyed Meat!_\n" +
						"Recipe:\n" +
						"Mystery Meat + Shattered Pot + 3 energy\n" +
						"\n" +
						"Food value: 300 (same as Ration).\n" +
						"Additionally, heals the Hero for 2 + 5% max HP"
		);
		// ***
		
		addSection(changeInfos, CHANGES);
		// ***
		addChange(Icons.TRAPS_ROOM, "Trap Adjustments",
				"_- Gripping trap:_\n" +
						"Base bleed: 2 -> 1-2\n" +
						"\n" +
						"_- Warping Trap:_\n" +
						"Now only breaks if a character steps onto it.\n" +
						"\n" +
						"_- Traps room:_\n" +
						"Size: 6-8 -> 6-7\n" +
						"\n" +
						"Replaced some traps:\n" +
						"- Sewers:\n" +
						"Teleportation; Flock -> Warping; Worn Dart\n" +
						"\n" +
						"- Prison:\n" +
						"Explosive -> Blasting\n" +
						"\n" +
						"- Caves:\n" +
						"Flashing -> Guardian\n" +
						"\n" +
						"- City:\n" +
						"Warping; Disintegration -> Blasting; Guardian"
		);
		
		addChange(Icons.RED_SENTRY, "Red Sentry",
				"Can be debuffed now (frost, paralysis, etc).\n" +
						"\n" +
						"Now also shoots Hero on water tiles.\n" +
						"Max damage: 4 + depth -> 3 + depth"
		);
		
		addChange(new MysteryMeat(), "Meat Adjustments",
				"_- Mystery Meat:_\n" +
						"Chance for negative effect: 80% -> 100%\n" +
						"Replaced: Poison for 20% HT -> Daze for 10 turns\n" +
						"\n" +
						"_- Stewed Meat:_\n" +
						"Replaced all instances of Stewed Meat with Chargrilled Meat.\n" +
						"\n" +
						"_- Chargrilled Meat:_\n" +
						"Food value: 150 -> 240\n" +
						"\n" +
						"_- Frozen Carpaccio:_\n" +
						"Old effects:\n" +
						"- Invisibility (20%)\n" +
						"- Barkskin (20%)\n" +
						"- Heal (20%)\n" +
						"- Cleanse (20%)\n" +
						"- Nothing (20%)\n" +
						"— — — — — — — -\n" +
						"New effects:\n" +
						"- Invisibility (25%)\n" +
						"Duration: 20 -> 15\n" +
						"\n" +
						"- Barkskin (25%)\n" +
						"Formula adjusted.\n" +
						"\n" +
						"- New: Barrier (25%)\n" +
						"Replaces heal, shields for same amount.\n" +
						"\n" +
						"- New: Fury (25%)\n" +
						"Duration: 15 turns\n" +
						"\n" +
						"_- Phantom Meat:_\n" +
						"Adjusted its effects to match new Carpaccio.\n" +
						"Except it still cleanses debuffs like before!"
		);
		// ***
		
		
		
		currentSection = null;
	}
	
}
