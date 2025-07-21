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
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.EtherealChains;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.SandalsOfNature;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.WoollyBomb;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.food.HoneyedMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
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
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blooming;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Vampiric;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Dirk;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Glaive;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Longsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RoundShield;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RunicBlade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sai;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Scimitar;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sickle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WarHammer;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WarScythe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.Tomahawk;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.ShockingDart;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.ChangesScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BruteSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CrabSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.DM300Sprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ElementalSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GhostSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GuardSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HermitCrabSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ImpSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.NecromancerSprite;
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
						"Now also resistant to Wand of Blastwave and Regrowth effects.\n" +
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
						"No longer procs when starving/stalling boss.\n" +
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
						"_New:_ The Berserker can shout a war cry after taking a hit, provoking all enemies within 8 tiles and preparing to go on a rampage.\n" +
						"\n" +
						"During this state, the Berserker gains +30% physical damage, +100% accuracy, and a large upfront shield that scales based on his seal, and up to 2x " +
						"with missing health.\n" +
						"\n" +
						"Killing enemies prolongs the duration by 2 turns, but once it ends: all remaining shielding will vanish, and the Berserker must then wait 120 turns " +
						"before going on a rampage again.\n" +
						"\n" +
						"Reworked talents: _Last Stand,_ _Undying Rage,_ and _Enraged Catalyst._\n" +
						"Check the talent descriptions for more info!"
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
						"The Rogue gains _1.5 turns/2 turns_ of haste whenever a trap is activated in his vision. Floors will generate _10%/20%_ more revealed traps.\nTraps that don't break can only grant haste up to 3 times.\n" +
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
		
		addChange(new Ankh(), "Ankhs",
				"Dying by a _Chalice of Blood_ prick still grants the upgrade even if an ankh isn't blessed.\n" +
						"\n" +
						"_Blessed ankhs:_\n" +
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
						"- You can now chain multiple commands by tapping the item twice, or by: (PC) holding -Left Shift-\n" +
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
				"- Shield strength: 2 + level -> 3 + level/2 (rounded)\n" +
						"- Proc chance: 10%/12%/14%... -> 18%/19.5%/21%...\n" +
						"\n" +
						"- Shielding now breaks after first hit.\n" +
						"- Duration: 6 turns -> 5 turns"
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
		
		addChange(new WandOfLivingEarth(), "Ally Wands",
				"_- Wand of Living Earth:_\n" +
						"Guardian accuracy: same as Hero -> 1.5x Hero's\n" +
						"Guardian's defense doesn't decrease on FIMA (challenge) anymore.\n" +
						"\n" +
						"_- Wand of Transfusion:_\n" +
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
						"Bonus damage already nerfed on PPD. Remains unchanged at +2/+3\n" +
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
						"\n" +
						"_- Viscosity:_\n" +
						"Now also defers fall damage from chasms.\n" +
						"\n" +
						"_- Thorns:_\n" +
						"Against inorganic enemies, now deals upfront damage instead of trying to apply bleed.\n" +
						"This damage however can't drop the HP of an enemy below 1!"
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
						"Instead, works like a fast weapon, granting a +33% attack speed boost.\n" +
						"\n" +
						"_Stone Gauntlet Buff:_\n" +
						"Base damage: 5-15 -> 5-16"
		);
		
		addChange(new GuardSprite(), "Prison Enemies",
				"_- Guard:_\n" +
						"HP: 45 -> 40\n" +
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
				"-ADD DESCRIPTION IN GAME:\n" +
						"No longer gains energy when chasming enemies.\n" +
						"\n" +
						"_- Meditate:_\n" +
						"No longer grants artifact recharge (wand recharge untouched).\n" +
						"-ADD DESCRIPTION IN GAME:\n" +
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
						"-TO REMOVE:\n" +
						"Grants camouflage instead of invis.\n" +
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
						"Round shield: 3 + lvl\n" +
						"Great shield: 2 + lvl"
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
						"Goo will avoid walking into corners from out of vision while the fight hasn't started yet."
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
						"Generates a bunch of new items, such as phase shift, reclaim trap, elixir of dragon's blood, etc.\n" +
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
		addChange(itemIcon(ItemSpriteSheet.RING_TOPAZ), "Ring of Furor",
				"Attack speed boost:\n" +
						"9.05%/level -> 10%/level"
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
						"Bla bla bla...\n" +
						"\n" +
						"_- Scroll of Divination:_\n" +
						"Bla bla bla..."
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
						"- Warrior's Iron Stomach now lasts through floor transitions: this means it can be used to resist chasm damage if combined with other tools.\n" +
						"\n" +
						"- Stone of Detect Magic no longer detects enchantments and glyphs as positive (since they're already visible anyway).\n" +
						"\n" +
						"- Selling and recovering items on shops doesn't take time anymore.\n" +
						"- Items sold will now try to stack.\n" +
						"\n" +
						"- Initial floor mobs won't spawn as close to the entrance anymore.\n" +
						"\n" +
						"- Sacrificial Altar begins to spawn extra mobs earlier.\n" +
						"\n" +
						"- Unstable Brew can no longer roll Purity when thrown."
		);
		// ***
		
		addSection(changeInfos, BUFFS);
		// ***
		addChange(new BruteSprite(), "Brute",
				"_- Brute:_\n" +
						"Now cleanses all its debuffs upon going berserk."
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
						"Base damage: 2 -> 1-2\n" +
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
	
	
	
	
	public static void SPD_add_v1_4_Changes( ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v1.4", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.get(Icons.SHPX), "Developer Commentary",
				"_-_ Released October 4th, 2022\n" +
				"_-_ 90 days after Shattered v1.3.0\n" +
				"\n" +
				"I called v1.4.0 'the big patch' while it was in development, as the goal of the update was to try and clear through a backlog of smaller fixes and improvements that was starting to get very long.\n" +
				"\n" +
				"I've kept getting so many reports that future updates have been pretty fix-heavy too though. Some of Shattered's players are very, very dedicated to pushing the game to its limits, and so it sometimes feels like every fix I make just prompts these people to go find problems elsewhere.\n" +
				"\n" +
				"There was new content in v1.4.0 as well, most notably the addition of in-game lore! I've had lots of loose ideas for a proper backstory to Shattered for a long time, and v1.4.0 was the first update that started to put some of that into the actual game! Future lore has had to wait until after the addition of new heroes though."));

		changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.GUIDE_PAGE), "Lore Additions",
				"_30 pages of lore text have been added to the game, scattered around the regions of the dungeon!_\n\n" +
				"These pages are found through the dungeon and go into a new tab in the journal window. Each region contains 6 pages that make up a short story that gives more details about that region and the people who have been there before the player."));

		changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.RING_TOURMALINE), new RingOfArcana().trueName(),
				"_A new ring has been added that enhances enchantments and glyphs!_\n\n" +
				"The Ring of Arcana lets the player directly power up their enchantments and glyphs, instead of only being able to power them up by upgrading the item they are attached to. This can lead to be some really potent enchant/glyph effects at high ring levels."));

		changes.addButton(new ChangeButton(new SandalsOfNature(),
				"_The Sandals of Nature have been given a new active ability!_\n\n" +
				"Rather than just using the effect of earthroot, the footwear of nature now use the effect of the seed most recently fed to them! These effects can be triggered on nearby enemies instead of just on you, opening up a bunch of tactical potential for this artifact.\n\n" +
				"For balance, the amount of extra seeds/dew the footwear gives has been reduced, and 1 additional seed is needed for each upgrade level."));

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.WARRIOR, 0, 90, 12, 15), HeroSubClass.BERSERKER.title(),
				"_The Berserker's berserk ability is now manually activated, but has a much lower cooldown._\n\n" +
				"I'm doing this to try and make the subclass a bit more engaging, players can now trigger berserk at any point when they have 100% or more rage. Berserking still gives a big bonus shield, letting the Berserker survive normally fatal encounters. The lower the Berserker's health, the more shielding he gets.\n\n" +
				"The Berserker's talents have been adjusted as well:\n" +
				"_- Endless Rage_ Now grants bonuses to berserk duration and cooldown when above 100% rage.\n" +
				"_- Berserking Stamina_ has been replaced with _Deathless Fury_, which lets berserking automatically trigger just like before, but at a high cooldown cost."));

		changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.MASTERY), "Tutorial Additions",
				"_A short guided tutorial has been added at the start of the game for new players._\n\n" +
				"This tutorial guides the player through their first few actions, and encourages reading the game log and guidebook.\n\n" +
				"As part of this tutorial change, initial story texts and the guidebook have been slightly adjusted, and there is a new pop-up for controller players that explains how to use the in-game cursor."));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight(CharSprite.WARNING);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton( new TalentIcon(Talent.EMPOWERED_STRIKE), "T3 Talent Redesigns",
				"I've made some pretty significant changes to three T3 talents that were either unpopular or too simplistic:\n\n" +
				"_- Empowered Strike_ now gives a little less direct staff damage, but also boosts the staff's on-hit effect. This should make it more interesting while still encouraging staff melee play.\n" +
				"_- Excess Charge_ now triggers when the Mage's staff is zapped at full charge, instead of on-hit, but the barrier effect is a bit stronger. This should encourage a mix of staff melee and zapping, instead of just pure melee at full charge.\n\n" +
				"_- Bounty Hunter_ now increases enemy drop chance, instead of providing gold. This should make it more varied and interesting. The bonus also gets notably higher at high preparation charge, instead of scaling linearly."));

		changes.addButton(new ChangeButton( new ItemSprite(ItemSpriteSheet.LONGSWORD, new ItemSprite.Glowing(0x0000FF)), "Blocking Enchant Redesign",
				"Blocking has been slightly redesigned to provide a more visible benefit. Instead of always granting a little armor, the enchantment now has a chance to grant a larger shield.\n\n" +
				"I don't expect that this will make the enchantment significantly stronger or weaker, but it should feel more impactful."));

		changes.addButton(new ChangeButton(Icons.get(Icons.DISPLAY_LAND), "Landscape Hero Select",
				"Desktop and mobile landscape users will now see a new hero select screen that better makes use of screen real-estate."));

		changes.addButton(new ChangeButton(Icons.get(Icons.PREFS), Messages.get(ChangesScene.class, "misc"),
				"_Highlights:_\n" +
				"_-_ Daily runs can now be replayed for practice\n" +
				"_-_ Updated translations and translator credits\n" +
				"_-_ Added more achievements to Shattered on Google Play Games\n" +
				"_-_ The buff bar now condenses itself if many buffs are visible at once. This raises the limit of on-screen buffs to 15\n" +
				"\n" +
				"_Hero Actions:_\n" +
				"_-_ Waiting now always takes exactly 1 turn, regardless of hero speed\n" +
				"_-_ Grass the hero is standing on can now be trampled by selecting the hero's position\n" +
				"_-_ Hero now pauses before ascending/descending if enemies are nearby",

				"_Items:_\n" +
				"_-_ Wand of Disintegration no longer harms undiscovered neutral characters\n" +
				"_-_ Blooming enchant now tries to avoid placing grass on the hero\n" +
				"_-_ The scroll holder can now hold arcane resin\n" +
				"_-_ Rotberry plant now gives a small puff of toxic gas when trampled\n" +
				"_-_ Plants now trigger after time freeze ends, just like traps\n" +
				"_Allies & Enemies:_\n" +
				"_-_ Improved behavior of ally AI when told to hold a position\n" +
				"_-_ Goo's pump up attack now always gives the hero at least 1 action to react\n" +
				"_-_ DM-300 now knocks back during rockfall even if hero is 1 tile away\n" +
				"_-_ Slightly adjusted enemy stats on ascension to smooth out difficulty",

				"_UI/VFX:_\n" +
				"_-_ Throwing weapons now show their quantity in orange when one is about to break\n" +
				"_-_ Item boosts from potion of mastery or curse infusion now change the color of text in that item's item slot\n" +
				"_-_ Various minor UI improvements to hero select and rankings\n" +
				"_-_ Added sacrifical fire and demon spawners to the landmarks page\n" +
				"_-_ Added some ascension dialogue for the ghost hero\n" +
				"_-_ Slightly improved the marsupial rat attacking animation\n" +
				"_-_ Improved chains vfx, especially for prison guards\n" +
				"_-_ Added lullaby vfx to the stone of deep sleep\n" +
				"\n" +
				"_Rankings:_\n" +
				"_-_ Clarified description for boss score in rankings\n" +
				"_-_ Yog's laser now deducts score even if the hero dodges it\n" +
				"_-_ Goo no longer deducts score by healing in water",

				"_Levelgen:_\n" +
				"_-_ Items and enemies can no longer spawn in aquarium room water\n" +
				"_-_ Improved room merging logic in a few specific situations\n" +
				"\n" +
				"_Controls:_\n" +
				"_-_ Added a copy and paste button to text input windows\n" +
				"_-_ Adjusted default controller bindings slightly\n" +
				"_-_ The 'switch enemy' keybind now also switches tabs on tabbed windows\n" +
				"_-_ On desktop, the game now attempts to keep mouse and controller pointer potions in sync\n" +
				"_-_ Added a setting to adjust camera follow intensity\n" +
				"_-_ The controller pointer can now pan the game camera\n" +
				"_-_ Heroes can now be renamed individually"));

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.SPINNER, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				"Fixed the following bugs:\n\n" +
				"_Highlights:_\n" +
				"_-_ Victory and Champion badges not being awarded in some cases\n" +
				"_-_ Various rare crash and hang bugs\n" +
				"_-_ Various minor visual/textual errors\n" +
				"\n" +
				"_Allies & Enemies:_\n" +
				"_-_ Characters rarely managing to enter eternal fire\n" +
				"_-_ Summons from guardian traps counting as regular statues in some cases\n" +
				"_-_ Rare cases where ranged allies would refuse to target nearby enemies\n" +
				"_-_ Various rare cases where characters might stack on each other\n" +
				"_-_ Albino rats causing bleed when hitting for 0 damage\n" +
				"_-_ Necromancers being able to summon through crystal doors\n" +
				"_-_ Giant necromancers summoning skeletons into doorways\n" +
				"_-_ Goo Immediately using its pump up attack if a previous pump up was interrupted by sleep",

				"_Items pt.1:_\n" +
				"_-_ Honeypots not reacting correctly to being teleported\n" +
				"_-_ Rare cases where lost inventory and items on stairs could softlock the game\n" +
				"_-_ Hero armor transferring rarely deleting the Warrior's broken seal\n" +
				"_-_ Scrolls of Mirror Image not identifying in rare cases\n" +
				"_-_ Various incorrect interactions between kinetic/viscosity and damage mitigating effects\n" +
				"_-_ Wand of Fireblast sometimes not igniting adjacent item or barricades\n" +
				"_-_ Ring of Furor not affecting Sniper special abilities\n" +
				"_-_ Cursed rings of force still heavily buffing melee attacks\n" +
				"_-_ Armband not breaking invisibility\n" +
				"_-_ Various quirks with charge spending on timekeeper's hourglass\n" +
				"_-_ Stones of aggression working much more effectively than intended\n" +
				"_-_ Chalice of Blood benefitting from recharging while hero is starving\n" +
				"_-_ Cases where explosive curse would create explosions at the wrong location",

				"_Items pt.2:_\n" +
				"_-_ Additional cases where magical spellbook could generate scrolls of lullaby" +
				"_-_ Heavy boomerangs getting an accuracy penalty when returning\n" +
				"_-_ Rare consistency errors in potion of might buff description\n" +
				"_-_ Death to aqua blast counting as death to a geyser trap\n" +
				"_-_ Reading spellbook not spending a turn if the scroll was cancelled\n" +
				"_-_ Screen orientation changes cancelling the scroll of enchantment\n" +
				"_-_ Magical infusion incorrectly clearing curses on wands and rings\n" +
				"_-_ Projecting weapons not reaching through webs in rare cases\n" +
				"_-_ Multiplicity glyph duplicating NPCs in rare cases\n" +
				"_-_ Rare cases where potion of healing talents wouldn't trigger\n" +
				"_-_ Cursed horn of plenty affecting non-food items\n" +
				"_-_ Being able to self-target with cursed wands in rare cases\n" +
				"_-_ Some thrown weapons triggering traps as Tengu jumps\n" +
				"_-_ Magic resistance not applying to some cursed wand effects",

				"_Effects:_\n" +
				"_-_ Invisibility effects not working when applied to enemies\n" +
				"_-_ Rare cases where giant enemies couldn't attack\n" +
				"_-_ 13th armor ability incorrectly clearing champion enemy buffs\n" +
				"_-_ Exploits where the gladiator could build combo on ally characters\n" +
				"_-_ Cases where piranhas could live for a turn on land\n" +
				"_-_ Errors with wild magic or flurry and knockback effects\n" +
				"_-_ Magical Sight not making the hero immune to blindness\n" +
				"_-_ Targeting logic sometimes being incorrect on armor abilities\n" +
				"_-_ Shadow clone not benefiting from certain glyphs\n" +
				"_-_ Knockback effects paralyzing dead characters\n" +
				"_-_ Caves boss arena not displacing all items on the tile that caves in\n" +
				"_-_ Recharging effects sometimes getting an extra action on game load\n" +
				"_-_ Exploits during ascension challenge that let players still use shops\n" +
				"_-_ Elastic and battlemage blast wave ability conflicting with each other",

				"_Misc:_\n" +
				"_-_ Dailies using seeds that are also user-enterable\n" +
				"_-_ Confusing text when a weapon or armor is partly uncursed\n" +
				"_-_ 'No Weapons in His Presence' badge not stating that ring of force counts as a weapon\n" +
				"_-_ Various cases where the friendly fire badge was not correctly awarded\n" +
				"_-_ Controller axis mapping issues on Android\n" +
				"_-_ Various rare fog of war errors when the hero was knocked a high distance\n" +
				"_-_ Rare cases where items would not correctly appear in the rankings screen\n" +
				"_-_ Prizes from sacrifice rooms not always being the same with the same dungeon seed\n" +
				"_-_ Rare crashes with radial inventory selector\n" +
				"_-_ Boss health bar not appearing in rare cases\n" +
				"_-_ Buff icons sometimes going outside of character info windows\n" +
				"_-_ Death by necromancer summoning damage not producing a record in rankings\n" +
				"_-_ Some users seeing rankings dates in local format, instead of international"));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight(CharSprite.POSITIVE);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.RING_AMETHYST), new RingOfWealth().trueName(),
				"I'm making a few improvements to the ring of wealth, mainly to make it more worth upgrading:\n\n" +
				"_-_ Now gives a rare drop every 0-20 kills, up from every 0-25\n" +
				"_-_ Now gives an equipment drop every 5-10 rare drops, down from every 4-8\n" +
				"_-_ Equipment drops are now guaranteed to be at least level 1/2/3/4/5/6 at ring level 1/3/5/7/9/11, up from 1/3/6/10/15/21\n\n" +
				"To limit the effectiveness of farming for a long time to stack up two highly upgraded rings of wealth, the level for equipment drops is based on your most powerful wealth ring, and a second one can only boost the level by another +1 at most."));

		changes.addButton(new ChangeButton( new WandOfTransfusion(),
				"I'm boosting the wand of transfusion's damage scaling versus undead enemies slightly:\n\n" +
				"_-_ Damage vs. undead scaling up to 1-2 per level, from 0.5-1"));

		changes.addButton(new ChangeButton( new TelekineticGrab(),
				"I'm enhancing the value of telekinetic grab a bit for users with multiple thrown weapons:\n\n" +
				"_-_ Now grabs all items at a location or stuck to an enemy, not just the first one."));

		changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.SHORTSWORD, new ItemSprite.Glowing( 0x000000 )), "Annoying Curse",
				"A very critical buff has been given to the annoying curse:\n\n" +
				"_-_ Added 5 new regular dialogue lines, for 10 total\n" +
				"_-_ Added 3 additional new lines that trigger rarely"));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight(CharSprite.NEGATIVE);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.EXOTIC_GYFU), new ScrollOfAntiMagic().trueName(),
				"Antimagic now also suppresses the positive effects of rings and artifacts while it is applied to the hero." ));

	}

	public static void SPD_add_v1_3_Changes( ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v1.3", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.get(Icons.SHPX), "Developer Commentary",
				"_-_ Released July 6th, 2022\n" +
				"_-_ 105 days after Shattered v1.2.0\n\n" +
				"v1.3.0 was largely a followup update to v1.2, with an emphasis on adding features that better fleshed out the game for all the new players on desktop platforms.\n" +
				"\n" +
				"The headline feature for this update was the addition of custom seeds, daily runs, and a new scoring system for them to make use of. Prior to this update the scoring system was totally based on depth reached and treasure collected, rather than actual performance during the run. A better score system gave players a better way to measure their performance, against themselves or other players.\n" +
				"\n" +
				"v1.3.0 also introduced a new much harder ascension challenge. Prior to this ascending was mostly a chore with no real increase to difficulty. With more challenge and a score bonus for doing it, players finally had a reason to try to ascend instead of ending the game on floor 26."));

		Image ic;
		ic = Icons.get(Icons.SEED);
		ic.hardlight(1f, 1.5f, 0.67f);
		changes.addButton( new ChangeButton(ic, "Seeded Runs!",
				"_It's now possible to enter a custom seed when starting a new game!_\n\n" +
				"Seeds are used to determine dungeon generation, and two runs with the same seed and game version will produce the exact same dungeon to play though.\n\n" +
				"If you don't enter a custom seed, the game will use a random one to generate a random dungeon, just like it did prior to this update.\n\n" +
				"Note that only players who have won at least once can enter custom seeds, and games with custom seeds are not eligible to appear in rankings."));

		ic = Icons.get(Icons.CALENDAR);
		ic.hardlight(0.5f, 1f, 2f);
		changes.addButton( new ChangeButton(ic, "Daily Runs!",
				"_Every day there is a specific seeded run that's available to all players!_\n\n" +
				"The daily run makes it easy to compete again friends or other folks on the internet, without having to coordinate and share a specific seed.\n\n" +
				"The game does keep track of your previous daily scores, but at the moment there is no leaderboard. You must win at least once to have access to daily runs."));

		changes.addButton( new ChangeButton(BadgeBanner.image( Badges.Badge.HIGH_SCORE_2.image ), "New Score System!",
				"_The game's scoring system has been overhauled to go along with seeded runs and dailies!_\n\n" +
				"The score system now factors in a bunch of new criteria like exploration, performance during boss fights, quest completions, and enabled challenges. This should make score a much better measure of player performance.\n\n" +
				"A score breakdown page has also been added to the rankings screen. This page even works for old games, and retroactively applies the challenge bonus!"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.AMULET), "Harder Ascension Route!",
				"_A bunch of adjustments have been made to the ascension route to make it a proper challenge!_\n\n" +
				"Enemies will get much stronger as you ascend, and it's impossible to teleport back up or flee and avoid all combat. Expect to have to work a little bit more for an ascension win!"));

		changes.addButton( new ChangeButton(Icons.get(Icons.DISPLAY), "UI/UX Improvements!",
				"_Several UI and UX Improvements have been made for desktop and mobile users_\n\n" +
				"_-_ The settings menu has been adjusted with a few new and rearranged options.\n" +
				"_-_ Added radial menus for controller users, and redid default controller bindings.\n" +
				"_-_ Added a quickslot swapper option for mobile portrait users.\n" +
				"_-_ Keyboard and controller key bindings now have separate windows\n" +
				"_-_ Added a few new key/button bindings actions"));

		changes.addButton( new ChangeButton(BadgeBanner.image( Badges.Badge.BOSS_CHALLENGE_5.image ), "New Badges!",
				"_14 new badges have been added to the game!_\n\n" +
				"_-_ Five of these badges are 'high score' badges, meant to tie into the new score system.\n" +
				"_-_ Another five of these badges are 'boss challenge' badges, which each require you to defeat a boss in a particular way.\n" +
				"_-_ Four new 'cause of death' badges have also been added, which should be a little trickier than the existing ones.\n\n" +
				"Several of these badges are on the harder end, in particular the final high score and boss challenge badge should be a real challenge, even for veteran players."));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.SHORTSWORD, new ItemSprite.Glowing(0x000000)), "Curse Redesigns",
				"_Three of the most annoying curses have been replaced or redesigned, and two more have been adjusted._\n\n" +
				"_- Fragile_ has been replaced by _explosive,_ which builds power and then explodes!\n" +
				"_- Wayward_ has been redesigned to sometimes apply an accuracy reducing debuff, instead of always reducing accuracy.\n" +
				"_- Exhausting_ has been replaced by _dazzling,_ which can blind both the attacker and defender.\n\n" +
				"_Anti-entropy_ and _sacrifice_ have also been nerfed (i.e. made less harsh), look at the nerfs section for more details on that."));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight(CharSprite.WARNING);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.EXOTIC_ODAL), Messages.get(ScrollOfMetamorphosis.class, "name"),
				"The scroll of metamorphosis has been adjusted to allow more of the game's talents to work with its effect.\n\n" +
				"Several talents that were previously exempt from being chosen by the scroll now have alternative effects that let them be used by any hero.\n\n" +
				"These alternative effects only appear when getting these talents via metamorphosis."));

		changes.addButton( new ChangeButton(BadgeBanner.image( Badges.Badge.MONSTERS_SLAIN_5.image ), "Badge Changes",
				"I'm making several changes to existing badges, based on feedback and data from Steam players:\n\n" +
				"_-_ Several gold tier badges have been bumped up to platinum tier to better reflect their difficulty\n" +
				"_-_ The 'grim reaper' badge has been bumped up to gold tier, from silver\n\n" +
				"_-_ The master and grandmaster 'monsters hunter' and 'treasure hunter' badges have been made more difficult\n" +
				"_-_ The alchemist badges have been rebalanced to start out easier and end up harder\n\n" +
				"_-_ The 'dungeoneer' badges have been made easier to unlock with wins or games played. The master and grandmaster versions still require a lot of games played though."));

		changes.addButton( new ChangeButton(new BuffIcon(BuffIndicator.TARGETED, true), "Buff and Spell Icons",
				"Several buffs have been given icons when they didn't have any, or have had their icons adjusted to prevent icon duplication. This should improve buff clarity in a few cases, and ensure that two active buffs can never have the exact same icon (recolored icons are still present though).\n\n" +
				"A few new overhead spell effects have been added as well."));

		changes.addButton(new ChangeButton(Icons.get(Icons.PREFS), Messages.get(ChangesScene.class, "misc"),
				"_-_ Updated translations, translator credits, and added a new language: Dutch!\n" +
				"_-_ Made the Huntress a bit easier to unlock again\n" +
				"_-_ Dreamfoil has been renamed to Mageroyal, to better fit its lack of sleeping functionality since 1.2\n" +
				"_-_ Updated various code dependencies\n" +
				"_-_ Made major internal changes in prep for quest improvements in v1.4\n" +
				"_-_ Added a slight delay to chasm jump confirmation window, to prevent mistaps\n" +
				"_-_ Progress is now shown for badges that need to be unlocked with multiple heroes\n" +
				"_-_ Multiple unlocked badges can now be shown at once\n" +
				"_-_ Various minor tweaks to item and level generation to support seeded runs\n" +
				"_-_ Keys now appear on top of other items in pit rooms",

				"_-_ Large floors now spawn two torches with the 'into darkness' challenge enabled\n" +
				"_-_ Blazing champions no longer explode if they are killed by chasms\n" +
				"_-_ Red sentries no longer fire on players with lost inventories\n" +
				"_-_ Wards and Sentries are now immune to sleep, vertigo and fear\n" +
				"_-_ Characters with guaranteed dodges (e.g. spirit hawk) can now evade Yog's laser beam\n" +
				"_-_ Boss health bars have been expanded to show current health and active buffs/debuffs.\n" +
				"_-_ The Changes scene has been expanded on large enough displays. This is the first of several UI expansions I'd like to make over time."));

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.SPINNER, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				"Fixed\n" +
				"_-_ Various minor textual and visual bugs\n" +
				"_-_ Final boss's summons being slightly weaker than intended when badder bosses is enabled\n" +
				"_-_ Great crab not blocking right after loading a save\n" +
				"_-_ Exploits that could force DM-300 to dig outside of its arena\n" +
				"_-_ Various 'cause of death' badges not being awarded if death occurred with an ankh.\n" +
				"_-_ Wraiths from spectral necromancers not always dying when the necromancer dies\n" +
				"_-_ The mystical charge talent giving more charge than intended\n" +
				"_-_ Ring of might HP bonus not applying in specific cases\n" +
				"_-_ Stones of blink disappearing if they fail to teleport\n" +
				"_-_ Beacon of returning not working at all in boss arenas\n" +
				"_-_ Earthen guardian not being immune to poison, gas, and bleed\n" +
				"_-_ Transmogrified enemies awarding exp when the effect ends\n" +
				"_-_ Gateway traps being able to teleport containers"));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight(CharSprite.POSITIVE);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.CROWN), "Armor Ability Buffs",
				"_- Endure_ damage bonus increased to 1/2 of damage taken from 1/3\n\n" +
				"_- Wild Magic_ base wand boost and max boost increased by 1\n" +
				"_- Fire Everything_ now has a 25% chance per point to let a wand be usable 3 times\n" +
				"_- Conserved Magic_ no longer lets a wand be usable 3 times, now grants a chance for wild magic to take 0 turns instead\n\n" +
				"_- Elemental power_ boost per point up to 25%, from 20%\n" +
				"_- Reactive Barrier_ shielding per point up to 2.5, from 2, and max targets now increases by 1 per point.\n\n" +
				"_- Shadow Clone_ now costs 35 energy, down from 50. Initial HP down to 80 from 100\n" +
				"_- Shadow Blade_ damage per point up to 8%, from 7.5%\n" +
				"_- Cloned Armor_ armor per point down to 12%, from 15%\n\n" +
				"_- Eagle Eye_ now grants 9 and 10 vision range at 3 and 4 points\n" +
				"_- Go for the Eyes_ now cripples at ranks 3 and 4\n" +
				"_- Swift Spirit_ now grants 2/4/6/8 dodges, up from 2/3/4/5"));

		changes.addButton( new ChangeButton(new TalentIcon(Talent.WAND_PRESERVATION), Talent.WAND_PRESERVATION.title(),
				"Only one isolated talent change in this update:\n\n" +
				"_- Wand Preservation_ chance to preserve at +1 reverted to 67% from 50%, still grants 1 arcane resin if it fails to preserve"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.CRYSTAL_KEY), "Crystal Path Rooms",
				"Loot from crystal path rooms (the rooms with a sequence of 3 crystal doors) has been buffed to make their value closer to other crystal key rooms."));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.CLEANSING_DART), "Alchemy Item Buffs",
				"I'm giving woolly bomb's a big buff to help make them more distinct from other sheep-spawning items:\n\n" +
				"_- Woolly Bombs_ now summon sheep for 200 turns, or 20 turns during boss fights, up from 12-16 turns. However, sheep no longer totally prevent bosses from summoning minions.\n\n" +
				"I've given some alternative functions to three darts that would previously only help allies:\n\n" +
				"_- Holy Dart_ turns of bless reverted to 30 from 100, now heavily damages undead or demonic enemies, instead of blessing them\n\n" +
				"_- Adrenaline Dart_ turns of adrenaline reverted to 10 from 30, now cripples enemies for 5 turns, instead of giving them adrenaline\n\n" +
				"_- Cleansing Dart_ now clears positive buffs from enemies, and causes them to start wandering (note that they may immediately re-aggro if they are close enough)"));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight(CharSprite.NEGATIVE);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.CROWN), "Armor Ability Nerfs",
				"Along with several armor ability buffs, two have received energy cost nerfs, mainly to reduce the uptime of positioning/escape abilities. Some compensation buffs have been given for the energy cost change as well:\n\n" +
				"_- Heroic Leap_ energy cost up to 35 from 25\n" +
				"_- Body Slam_ now adds 1-4 base damage per point in talent\n" +
				"_- Impact Wave_ now applies vulnerable for 5 turns, up from 3\n" +
				"_- Double jump_ energy cost reduction increased by 20%\n\n" +
				"_- Smoke Bomb_ energy cost up to 50 from 35\n" +
				"_- Smoke Bomb_ max range up to 10 from 6"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.ARMOR_LEATHER, new ItemSprite.Glowing(0x000000)), "Curse Nerfs",
				"Two of the most harsh curses have been nerfed (i.e. made better for the player):\n\n" +
				"_- Anti-Entropy_ now spreads less fire to the player, and freezes all adjacent tiles instead of just the enemy.\n\n" +
				"_- Sacrifice_ now more heavily scales on current HP, bleeding for a bit more at high health, and very little at medium to low health."));

		changes.addButton( new ChangeButton(new Image(new ElementalSprite.Fire()), "Floor 16 Adjustments",
				"Floor 16's spawn rates have been adjusted:\n\n" +
				"Ghouls up to 60% from 40%\n" +
				"Elementals down to 20% from 40%\n" +
				"Warlocks unchanged at 20%\n\n" +
				"This is to help smooth over a slight difficulty spike on that floor."));

	}

	public static void SPD_add_v1_2_Changes( ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v1.2", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.get(Icons.SHPX), "Developer Commentary",
				"_-_ Released March 23rd, 2022\n" +
				"_-_ 103 days after Shattered v1.1.0\n\n" +
				"v1.2.0 focused on a number of improvements to try and make Shattered play just as well for desktop users as it does for phone users. While these changes weren't perfect right at launch, they played a major role in making Shattered feel like more than a mobile port and helped it have a successful launch on Steam.\n" +
				"\n" +
				"One big change for desktop users that's external to the game itself was native executables. Previously Shattered's desktop version required a separate installation of Java to run. After v1.2.0, I started making versions of Shattered with built-in Java instead. This meant that most users could just download the game and run it, much nicer.\n" +
				"\n" +
				"v1.2.0 also included a variety of little additions and tweaks. Most of them are self-explanatory, but the badge additions in particular were timed with the game's release on Steam so they could tie into steamworks achievements and give newer players a little more to do."));

		changes.addButton( new ChangeButton(Icons.get(Icons.DISPLAY_LAND), "Desktop Enhancements and Steam Release!",
				"_Shattered Pixel Dungeon has received a bunch of new features in preparation for its release on Steam!_\n\n" +
				"These features include:\n" +
				"_-_ A new main UI for larger displays, which places the inventory in the main game screen\n" +
				"_-_ Full controller support, including button bindings and an analog stick cursor.\n" +
				"_-_ Better keyboard controls, including combining keys to move diagonally.\n" +
				"_-_ Better mouse support, including hover tooltips and right-click menus.\n" +
				"_-_ Two additional quickslots on the new UI, and on mobile UI if there is enough space.\n" +
				"_-_ Integration with Steamworks for achievements and cloud sync.\n\n" +
				"Users on mobile devices will be able to benefit from most of these features as well! (some feature require a large enough display)"));

		changes.addButton( new ChangeButton(new Image(Assets.Environment.TILES_SEWERS, 176, 48, 16, 16 ), "Special Rooms Additions!",
				"_Six new special rooms have been added!_\n\n" +
				"Two of these rooms (and one existing room) use new crystal doors, which let you see through them before you find a crystal key to unlock them.\n\n" +
				"Three of these rooms include new terrain hazards, which will require the right tools to get past.\n\n" +
				"The final new room is the sacrificial room from the original Pixel Dungeon! It returns with a few tweaks to its mechanics and loot (sorry, no scroll of wipe out)."));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.ARTIFACT_ARMBAND), "Armband Rework!",
				"_The Master Thieves' Armband has been reworked!_\n\n" +
				"This rework focuses on giving the armband usefulness outside of shops. You can now use it to steal from enemies as well as shopkeepers, and it gains charge as you gain exp, instead of when you collect gold."));

		changes.addButton( new ChangeButton(BadgeBanner.image(Badges.Badge.MONSTERS_SLAIN_5.image), "New Badges!",
				"_Badges now have names, and 8 new badges have been added!_\n\n" +
				"These new badges are all part of the existing series badges (e.g. defeat X enemies), and primarily exist around the gold badge level.\n\n" +
				"The 'games played' badges have also been adjusted to unlock either on a large number of games played, or a smaller number of games won."));

		changes.addButton( new ChangeButton(Icons.get(Icons.AUDIO), "New Boss Music!",
				"_Each of the game's five bosses now have their own music track!_\n\n" +
				"Just as before, these tracks are all composed by Kristjan Harristo, check the about scene for more details on them.\n\n" +
				"All of the boss tracks take cues from the region tracks, but add enough to be more than simple remixes."));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight(CharSprite.WARNING);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.PREFS), Messages.get(ChangesScene.class, "misc"),
				"_-_ Reduced cases of multiple rooms that require a solution potion per floor\n" +
				"_-_ Reduced the huntress unlock requirement\n" +
				"_-_ Adjusted the secrets level feeling to be less harsh\n\n" +
				"_-_ Added a 'new game' and 'menu' button after game over\n" +
				"_-_ Made the blinking behaviour of the journal button easier to notice\n" +
				"_-_ quickslot placement is now preserved when items are transmuted\n" +
				"_-_ Improved the depth display to include icons for level feelings\n" +
				"_-_ Added an icon next to depth display showing enabled challenges\n" +
				"_-_ Made surprise attack VFX a bit more obvious\n" +
				"_-_ Improved the resilience of the game's save system\n\n" +
				"_-_ Added a new language: Galician!\n" +
				"_-_ Removed the Catalan translation as it was below 70% complete"));

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.SPINNER, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				"Fixed:\n" +
				"_-_ Various rare cases of save corruption on Android\n" +
				"_-_ Various minor textual and visual errors\n\n" +
				"_-_ Various rare cases where the hero could perform two actions at once\n" +
				"_-_ Rare cases where the Freerunner could gain momentum while freerunninng\n" +
				"_-_ Gladiator's parry move not cancelling invisibility or time freeze\n" +
				"_-_ Assassinate killing enemies right after they were corrupted by a corrupting weapon\n" +
				"_-_ Player being able to self-target with assassinate ability\n" +
				"_-_ Exploits involving corruption and the 13th armor ability\n\n" +
				"_-_ Various rare cases where giant enemies could enter enclosed spaces\n" +
				"_-_ On-hit effects still triggering when the great crab blocks\n" +
				"_-_ Corruption debuff affecting smoke bomb decoy\n" +
				"_-_ Character mind vision persisting after a character dies\n" +
				"_-_ Dwarf King not being targeted by wands or thrown weapons while on his throne",

				"_-_ Pharmacophobia challenge incorrectly blocking some alchemy recipes\n" +
				"_-_ Unidentified wands being usable in alchemy\n" +
				"_-_ Wild energy spell not cancelling invisibility or time freeze\n" +
				"_-_ Various rare bugs with the timekeeper's hourglass\n" +
				"_-_ Various bugs with the potion of dragon's breath\n" +
				"_-_ Artifact recharging not charging the horn of plenty in some cases when it should\n" +
				"_-_ Some items rarely not being consumed when they should be\n" +
				"_-_ Arcane catalysts not being able to be turned into energy\n" +
				"_-_ Fog of War not properly updating when warp beacon is used\n" +
				"_-_ Very rare cases where dried rose becomes unusable",

				"_-_ Rare cases where lullaby scrolls were generated by the Unstable Spellbook\n" +
				"_-_ Ring of might health boost not being affected by lost inventory debuff\n" +
				"_-_ Items that spawn identified counting as being IDed by the player\n" +
				"_-_ Some sources of artifact recharging affecting cursed artifacts\n" +
				"_-_ Blacksmith not refusing to work with cursed items in specific cases\n" +
				"_-_ An exploit where unblessed ankhs could be used with a lost inventory\n\n" +
				"_-_ Layout issues with the loot indicator\n" +
				"_-_ Floor 5 entrance rooms sometimes being smaller than intended\n" +
				"_-_ Red flash effects stacking on each other in some cases\n" +
				"_-_ Game forgetting previous window size when maximized and restarted\n" +
				"_-_ Cases where ghoul sprites could become glitched\n" +
				"_-_ Cases where heroic energy talent would use the wrong name/icon\n" +
				"_-_ Curse status of quickslot items not showing in rankings"));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight(CharSprite.POSITIVE);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.FIRE_BOMB), "Recipe Cost Reductions",
				"I've made a bunch of cost adjustments to alchemy recipes to help counteract energy becoming more expensive after v1.1.0:\n\n" +
				"_- Bomb Recipe_ energy costs down across the board\n\n" +
				"_- Infernal, Blizzard, and Caustic Brew_ energy costs down by 1\n\n" +
				"_- Telekinetic Grab_ energy cost down to 2 from 4, liquid metal cost reduced to 10 from 15\n" +
				"_- Phase Shift_ energy cost down to 4 from 6\n" +
				"_- Wild Energy_ energy cost down to 4 from 6\n" +
				"_- Beacon of Returning_ energy cost down to 6 from 8\n" +
				"_- Summon Elemental_ energy cost down to 6 from 8\n" +
				"_- Alchemize_ energy cost down to 2 from 3"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.ENERGY), "Alchemy Buffs",
				"Several recipes have also been buffed, in addition to the cost reductions:\n\n" +
				"_- Scroll of Foresight_ duration up to 400 from 250\n" +
				"_- Scroll of Dread_ now grants 1/2 exp for defeated enemies\n" +
				"_- Potion of Shrouding Fog_ gas quantity increased by 50%\n\n" +
				"_-_ Items and effects which create water now douse fire\n\n" +
				"_- Caustic Brew_ damage per turn increased by 1\n" +
				"_- Infernal and Blizzard Brew_ now start their gas in a 3x3 AOE\n" +
				"_- Shocking Brew_ AOE up to 7x7 from 5x5\n\n" +
				"_- Phase Shift_ now stuns whatever it teleports\n" +
				"_- Summon Elemental_ quantity up to 5 from 3, elemental's stats now scale with depth, and elementals can be re-summoned\n" +
				"_- Aqua Blast_ now acts like a geyser trap, quantity down to 8 from 12\n" +
				"_- Reclaim Trap_ quantity up to 4 from 3\n" +
				"_- Curse Infusion_ now boosts highly levelled gear by more than +1, quantity up to 4 from 3.\n" +
				"_- Recycle_ quantity up to 12 from 8, cost up to 8 from 6"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.ROT_DART), "Dart Buffs",
				"While they don't tie into v1.1.0's energy changes in particular, I am also handing out several buffs to tipped darts:\n\n" +
				"_- Rot Dart_ uses increased to 5 from 1\n" +
				"_- Adrenaline Dart_ duration up to 30 from 10\n" +
				"_- Shocking Dart_ damage now slightly scales with depth\n" +
				"_- Poison Dart_ damage scaling increased\n" +
				"_- Sleep Dart_ is now _Cleansing Dart_, makes allies immune to debuffs for several turns\n" +
				"_- Holy Dart_ duration up to 100 from 30\n" +
				"_- Displacing Dart_ now much more consistently teleports enemies away"));

		changes.addButton( new ChangeButton(new TalentIcon(Talent.LIGHT_CLOAK.icon()), "Talent Buffs",
				"I'm handing out a few buffs to help better balance the Mage's T2 talents and the Rogue's class-based T3 talents. I'm also making one bugfix that counts as a buff:\n\n" +
				"_- Energizing Upgrade_ charge boost up to 4/6, from 3/5\n" +
				"_- Wand Preservation_ chance at +1 reduced to 50%, but now grants 1 arcane resin if it fails to preserve\n" +
				"_- Wand Preservation_ max uses up to 5 from 3\n" +
				"_- Empowering Scrolls_ now grants +3 on the next 1/2/3 wand zaps\n\n" +
				"_- Light Cloak_ charging rate boosted to 25%/50%/75%, from 17%/33%/50%\n\n" +
				"_- Shared Upgrades_ bugfixed to give the bonus damage stated in the description, instead of slightly less."));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight(CharSprite.NEGATIVE);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.MAGIC_INFUSE), "Alchemy Nerfs",
				"v1.2.0 is mostly about alchemy buffs, but a few alchemy items have had their power reduced as well:\n\n" +
				"_- Magical Infusion_ energy cost up to 4 from 3\n" +
				"_- Holy Bomb_ bonus damage reduced to 50% from 67%\n" +
				"_- Goo Blob and Metal Shard_ energy value reduced to 3\n" +
				"_- Alchemize_ quantity in shops reduced by 1\n\n" +
				"While not a direct alchemy item nerf, I've also made some of the final bosses' fists less susceptible to certain mechanics:\n" +
				"_- Soiled Fist_ is now immune to burning, but the grass it generates still burns\n" +
				"_- Burning Fist_ is now immune to freezing, but it can still be chilled\n" +
				"_- Rotting and Rusted Fists_ now take less damage from retribution, grim, and psionic blast"));

		changes.addButton( new ChangeButton( new Image(Assets.Environment.TERRAIN_FEATURES, 112, 112, 16, 16), "Dreamfoil",
				"Dreamfoil has always had great utility as a debuff-cleanser, and with the recent addition of stones of deep sleep its enemy sleeping functionality was feeling a bit unnecessary:\n\n" +
				"_- Dreamfoil_ no longer puts enemies into magical sleep\n\n" +
				"Sleep darts (made from dreamfoil) have also been changed into cleansing darts to go along with this change. These darts will make an ally temporarily immune to harmful effects."));

		changes.addButton( new ChangeButton(new TalentIcon(Talent.SHIELD_BATTERY.icon()), "Talent Nerfs",
				"I'm making a few talent nerfs to better balance the Mage's T2 talents, and to pull in the power of the Berserker a little:\n\n" +
				"_- Shield Battery_ shielding per charge down to 4%/6%, from 5%/7.5%\n\n" +
				"_- Endless Rage_ max rage boost reduced to 10%/20%/30% from 15%/30%/45%\n" +
				"_- Enraged Catalyst_ proc rate boost reduced to 15%/30%/45% from 17%/33%/50%"));

	}

	public static void SPD_add_v1_1_Changes( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("v1.1", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.get(Icons.SHPX), "Developer Commentary",
				"_-_ Released December 10th, 2021\n" +
				"_-_ 115 days after Shattered v1.0.0\n\n" +
				"v1.1.0 was the start of Shattered's alchemy system actually doing the job it was supposed to do way back in v0.7.0. Previously alchemical energy sort of sat on top of the alchemy system and acted as a hard cap on how many 'powerful' recipes the player could use in a run. Thanks to v1.1.0's changes, energy replaced specific ingredients in a huge number of recipes, which made the system massively more flexible. Lots of specific alchemy items also received big buffs or redesigns, making them much more worthwhile to create.\n" +
				"\n" +
				"This update also included new region-specific music tracks composed by Kristjan Haaristo! Kristjan was actually a fan of Shattered before working on the music and contacted me about potentially adding new tracks. I feel extremely fortunate to have been able to work with people that are just as passionate about Shattered as I am."));

		changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.ENERGY), "Alchemical Energy Overhaul",
				"_The role of Alchemical Energy in the alchemy system has been totally overhauled!_\n\n" +
				"Energy is now a resource that the player carries with themselves, like gold. The game also generates much less energy for free, but more can be created by scrapping consumable items.\n\n" +
				"Many recipes have been adjusted to compensate for this. Exotic potions and scrolls now require energy instead of seeds/stones, and several of them have been buffed or totally redesigned (see buffs and changes sections for more details).\n\n" +
				"Other recipes have received relatively minor changes for now (mostly energy cost tweaks), but I'll likely be giving them more attention soon in future updates.\n\n" +
				"This repositions energy as the primary driving force for alchemy, and should make the system both more flexible and better at recycling consumables the player doesn't want into ones that they do."));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.SUMMON_ELE), "New and Reworked Spells",
				"While this update mostly focused changes on exotic potions and scrolls, there are _two new spells, and one totally redesigned spell:_\n\n" +
				"_Summon Elemental_ requires fresh embers and an arcane catalyst. It can be used to summon a friendly elemental to fight for you, and can even be powered up with other items!\n\n" +
				"_Telekinetic Grab_ requires some liquid metal and an arcane catalyst. It can be used to grab items remotely, even thrown items that are stuck to an enemy!\n\n" +
				"_Alchemize_ has been totally redesigned. It now only requires an arcane catalyst, and is used to convert items into gold or alchemical energy on the go. I'm really hoping this spell helps with inventory management.\n\n" +
				"Because of the redesign to alchemize, the merchant's beacon and magical porter are made mostly redundant and have been removed from the game. Shops now sell a few uses of alchemize instead."));

		changes.addButton( new ChangeButton(Icons.get(Icons.AUDIO), "more new music!",
				"_The game now has a music track for each of the five dungeon regions!_\n\n" +
				"Just like the remastered tracks from v1.0.0, they are all composed by Kristjan Harristo, check the about scene for more details on them.\n\n" +
				"Each of these tracks use a similar variable looping method to the sewers track, to try and reduce repetitiveness.\n\n" +
				"There have also been some small tweaks made to the existing sewers and title theme tracks."));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight(CharSprite.WARNING);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.EXOTIC_ISAZ), "Exotic Reworks",
				"Several exotic potions and scrolls have been redesigned to be more powerful and worth using:\n\n" +
				"_- Potion of Holy Furor_ is now _Potion of Divine Inspiration_, which gives bonus talent points.\n" +
				"_- Potion of Adrenaline Surge_ is now _Potion of Mastery_, which reduces the strength requirement of one item by 2.\n\n" +
				"_- Scroll of Petrification_ is now _Scroll of Dread_, which causes enemies to flee the dungeon entirely.\n" +
				"_- Scroll of Affection_ is now _Scroll of Siren's Song_, which permanently makes an enemy into an ally.\n" +
				"_- Scroll of Confusion_ is now _Scroll of Challenge_, which attracts enemies but creates an arena where you take reduced damage.\n" +
				"_- Scroll of Polymorph_ is now _Scroll of Metamorphosis_, which lets you swap out a talent to one from another class." ));

		changes.addButton(new ChangeButton(Icons.get(Icons.PREFS), Messages.get(ChangesScene.class, "misc"),
				"_-_ Item drops and special room spawns are now more consistent. Getting loads of the same item is now much less likely.\n" +
				"_-_ Items present on boss floors are now preserved if the hero is revived from an unblessed ankh\n" +
				"_-_ Teleport mechanics now work on boss levels\n" +
				"_-_ Traps that teleport no longer work on items in chests or similar containers\n" +
				"_-_ Rewards from piranha and trap rooms now always appear in chests\n" +
				"\n" +
				"_-_ Tipped darts can now be transmuted and recycled\n" +
				"_-_ Thrown weapons no longer stick to allies\n" +
				"_-_ Liquid metal production from upgraded thrown weapons now caps at +3",

				"_-_ Updated game icons on Android and Desktop platforms\n" +
				"_-_ Tabs in rankings and hero info windows now use icons, not text\n" +
				"_-_ 'potions cooked' badge and stats are now 'items crafted'\n" +
				"\n" +
				"_-_ Newborn elementals no longer have a ranged attack\n" +
				"\n" +
				"Various small improvements for iOS Devices:\n" +
				"_-_ Game can now run at higher framerates than 60\n" +
				"_-_ Ingame UI elements now move inward if notched devices are used in landscape\n" +
				"_-_ There is now an option to override silent mode\n" +
				"\n" +
				"_-_ Updated translations and translator credits"));

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.SPINNER, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				"Fixed:\n" +
				"_-_ Various minor/rare visual and textual errors\n" +
				"_-_ Cases where pausing/resuming the game at precise moments would cancel animations or attacks\n" +
				"_-_ Endure damage reduction applying after some specific other damage-reducing effects\n" +
				"_-_ Unblessed ankh resurrection windows disappearing in some cases\n" +
				"_-_ Lucky enchantment rarely not trigger in some cases\n" +
				"_-_ Artifacts spawning upgraded from golden mimics\n" +
				"_-_ Unblessed ankh revival cancelling corpse dust curse\n" +
				"_-_ Unstable spellbook letting the player select unidentified scrolls\n" +
				"_-_ Desktop version not working correctly with FreeBSD\n" +
				"_-_ Liquid metal being usable on darts\n" +
				"_-_ Teleportation working on immovable characters in some cases\n" +
				"_-_ Various quirks with thrown weapon durability\n" +
				"_-_ Rare cases where ghouls would get many extra turns when reviving\n" +
				"_-_ Magical infusion not preserving curses on armor\n" +
				"_-_ Vertigo and teleportation effects rarely interfering",

				"_-_ Layout issues in the hero info window with long buff names\n" +
				"_-_ Cursed wands being usable to create arcane resin\n" +
				"_-_ Unblessed ankh revival rarely causing crashes or placing the player on hazards\n" +
				"_-_ Some glyphs not working for armored statues or the ghost hero\n" +
				"_-_ Various oddities with inferno gas logic\n" +
				"_-_ Spirit bow having negative damage values in rare cases\n" +
				"_-_ Artifact recharging buff working on cursed artifacts\n" +
				"_-_ Scrolls of upgrade revealing whether unidentified rings/wands were cursed\n" +
				"_-_ Ring of Might not updating hero health total in rare cases\n" +
				"_-_ Specific cases where darts would not recognize an equipped crossbow\n" +
				"_-_ Cap on regrowth wand being affect by level boosts\n" +
				"_-_ Some on-hit effects not triggering on ghost or armored statues",

				"_-_ Rare errors when gateway traps teleported multiple things at once\n" +
				"_-_ Various rare errors when multiple inputs were given in the same frame\n" +
				"_-_ Fog of War errors in Tengu's arena\n" +
				"_-_ Rare errors with sheep spawning items and traps\n" +
				"_-_ Various rare crash bugs\n" +
				"_-_ Various minor textual and visual errors\n" +
				"_-_ Gateway traps rarely teleporting immovable characters\n" +
				"_-_ Monks never losing focus if attacked out of hero vision range\n" +
				"_-_ Wild magic continuing to activate if the hero dies during it\n" +
				"_-_ Specific cases where guidebook windows could be stacked\n" +
				"_-_ Remove curse stating nothing was cleansed when it removed the degrade debuff"));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight(CharSprite.POSITIVE);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.EXOTIC_AMBER), "Exotic Buffs",
				"Some exotic potions and scrolls have received more minor buffs, and not total redesigns:\n\n" +
				"_- Potions of Storm Clouds, Shrouding Fog, and Corrosion_ initial gas AOE up to 3x3 from 1x1\n" +
				"_- Potion of Shrouding Fog_ now only blocks enemy vision\n" +
				"_- Potion of Corrosion_ starting damage increased by 1\n" +
				"_- Potion of Magical Sight_ vision range up to 12 from 8\n" +
				"_- Potion of Cleansing_ now applies debuff immunity for 5 turns\n\n" +
				"_- Scroll of Foresight_ now increases detection range to 8 (from 2), but lasts 250 turns (from 600)\n" +
				"_- Scroll of Prismatic Image_ hp +2 and damage +20%"));

		changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.ARTIFACT_TOOLKIT), "Artifact Buffs",
				"The _Alchemist's Toolkit_ has received some minor changes to go along with the energy system adjustments:\n" +
				"_-_ Toolkit indirectly buffed by energy now being more valuable\n" +
				"_-_ Energy required to level up toolkit halved, kit can now be levelled anywhere\n" +
				"_-_ Toolkit warmup is now based on time, and gets faster as it levels up\n" +
				"_-_ Toolkit can now be used when enemies are near\n\n" +
				"The _Horn of Plenty_ is getting a change to increase its flexibility, and to make it better synergize with food eating talents:\n" +
				"_-_ The horn now has a 'snack' option that always consumes 1 charge\n" +
				"_-_ To counterbalance this, the total number of charges and charge speed have been halved, but each charge is worth twice as much as before.\n\n" +
				"I'm giving a mild buff to the _Dried Rose_ to fix an odd inconsistency where it was better to kill the ghost off than let them heal:\n" +
				"_-_ Ghost HP regen doubled, to match the roses recharge speed (500 turns to full HP)"));

		changes.addButton( new ChangeButton( new Image(Assets.Sprites.WARRIOR, 0, 90, 12, 15), HeroSubClass.BERSERKER.title(),
				"The berserker is getting a small QOL buff to make it easier to hold onto rage in combat:\n\n" +
				"_-_ Rage now starts expiring after not taking damage for 2 turns, instead of immediately."));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.CROWN, null), "Talent and Ability Buffs",
				"Talent and ability balance is becoming more stable now, but I've still got a few buffs to hand out, some are quite significant:\n\n" +
				"_- Wild Magic_ Charge cost reduced to 25, from 35.\n" +
				"_- Spirit Hawk_ Duration up to 100 turns, from 60.\n\n" +
				"_- Empowering Scrolls_ now lasts for 2 wand zaps, up from 1.\n" +
				"_- Light Cloak_ now grants 16.6% charge speed per rank, up from 13.3%\n" +
				"_- Shrug it Off_ now caps damage taken at 20% at +4, up from 25%."));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight(CharSprite.NEGATIVE);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new MagesStaff(),
				"The reduction to the Mage's starting melee damage in v1.0.0 had a good effect on his early game winrate, but it's still notably higher than other heroes. So, I'm nudging his early melee power down one more time:\n\n" +
				"_- Mage's Staff_ base damage reduced to 1-6 from 1-7."));

		changes.addButton( new ChangeButton( new Image(Assets.Sprites.ROGUE, 0, 90, 12, 15), HeroSubClass.ASSASSIN.title(),
				"The Assassin is doing very well right now, especially after the power boost he can receive from smoke bomb or death mark. I'm scaling back his core power a little to try and reign him in a bit:\n\n" +
				"_-_ Preparation bonus damage at power level 1/2/3/4 reduced to 10/20/35/50%, from 15/30/45/60%"));

		changes.addButton( new ChangeButton(new TalentIcon(Talent.DOUBLE_JUMP.icon()), Talent.DOUBLE_JUMP.title(),
				"Just one talent/ability nerf this time! I'm scaling double jump back a bit to put it more in line with the other heroic leap talents:\n\n" +
				"_-_ Charge cost reduction now caps at 50%, down from 60%\n" +
				"_-_ The warrior must now jump again within 3 turns, down from 5\n\n" +
				"I'll likely making more balance tweaks (including nerfs) to abilities and talents in the future, but at the moment double jump is the only major standout."));

	}

	public static void SPD_add_v1_0_Changes( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("v1.0", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.get(Icons.SHPX), "Developer Commentary",
				"_-_ Released August 17th, 2021\n" +
				"_-_ 71 days after Shattered v0.9.3\n" +
				"_-_ 316 days after Shattered v0.9.0\n" +
				"_-_ A bit more than 7 years after v0.1.0!\n" +
				"\n" +
				"_That's right, the big v1.0.0!_ In this update Shattered switched to the industry standard _major.minor.patch_ version naming scheme. Prior to this Shattered's first version number was always 0, and patches were appended as letters. Rather than jumping all the way from v0.9.3 to v10.0.0, I opted to reset Shattered back to v1.0.0 for this update. This was done both for tidiness, and to signal another new era in Shattered's development.\n" +
				"\n" +
				"I actually originally intended for this update to be v0.9.4, but a requirement by Apple forced me to change the versioning scheme just before the full release. In retrospect, I'm extremely glad that this update became v1.0.0. While it wasn't the largest update content-wise, I can think of no better time for v1.0 than the update where Shattered first released as a paid product."));

		changes.addButton( new ChangeButton(Icons.get(Icons.DISPLAY_PORT), "iOS Release!",
				"_Shattered Pixel Dungeon is now available on the iOS App Store!_\n\n" +
				"After years of requests, Shattered is finally available on Apple devices! The iOS version of the game will release in lockstep with the Android version moving forward, with some small variance due to different update approval processes.\n\n" +
				"Note that the iOS version costs $5, but comes with some supporter features built-in. I have no plans to make any changes to the monetization of the Android version."));

		changes.addButton( new ChangeButton(Icons.get(Icons.AUDIO), "new music!",
				"_The game's music tracks has been remastered!_\n\n" +
				"The new music is composed by Kristjan Harristo, check the about scene for more details on them. Currently we have only replaced the existing tracks, but we are working on tracks for each of the dungeons regions as well!\n\n" +
				"The new in-game track in particular is also an experiment in variable music looping. The track has an intro and a main segment and can play the main segment once or twice before looping back to the intro. This makes the track notably less repetative, and we intend to use similar techniques in other tracks."));

		changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.LIQUID_METAL), "new alchemy recipes!",
				"Two new alchemy recipes have been added! They're focused on helping you recycle thrown weapons and wands that you don't want to use.\n\n" +
				"_Liquid metal_ lets you sacrifice thrown weapons to repair other ones.\n\n" +
				"_Arcane resin_ lets you sacrifice a wand to upgrade other low level wands.\n\n" +
				"A new page has been added to the alchemy guide for these recipes, and it's now possible to find later guidebook pages in the prison."));

		changes.addButton(new ChangeButton(new Image(new Image(Assets.Environment.TERRAIN_FEATURES, 64, 64, 16, 16)), "new traps",
				"Two new traps have been added! They are both less common traps that have a higher potential to be helpful.\n\n" +
				"_Geyser traps_ convert surrounding terrain to water and throw back anything near them.\n\n" +
				"_Gateway traps_ are a special teleportation trap which never expire, and always teleport to the same location.\n\n" +
				"All teleportation traps now also affect characters and items next to them."));

		changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.MASTERY), "new player experience improvements",
				"_The adventurer's guidebook is now the Tome of Dungeon Mastery!_\n\n" +
				"This is partly as a reference to the tome of mastery, which I removed in the previous update, and partly because the game's tutorial functionality has been improved.\n\n" +
				"Guidebook pages are now a bit shorter and more plentiful, and some of them are now given to the player right at the start of the game. These automatic pages are suggested to the player to read at crucial moments. This way the guidebook does a better job of highlighting info right when it's needed."));

		changes.addButton(new ChangeButton(new Image(new SpectralNecromancerSprite()), "spectral necromancers",
				"A new rare variant has been added for necromancers: _Spectral Necromancers!_\n\n" +
				"These necromancers don't care for skeletons, and prefer to summon a bunch of wraiths instead! Dealing with their horde might be tricky, but you'll be rewarded with a scroll of remove curse."));

		changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.ANKH), "new ankh mechanics",
				"_Regular Ankhs_ have been totally redesigned, and now give the player a chance to save all of their equipment! Be careful though, you'll have to fight your way back to your lost gear.\n\n" +
				"_Blessed Ankhs_ have received comparatively minor changes. In addition to the resurrection effect, these ankhs now also give the player 3 turns of invulnerability. This should help give players a moment to heal up after being revived."));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight(CharSprite.WARNING);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.STONE_FEAR), "runestones",
				"All Scrolls now produce 2 runestones, instead of some scrolls producing 3. The stones that used to be given in higher quantities have received buffs in compensation:\n\n" +
				"_- Stone of Intuition_ can now be used a second time if the guess was correct.\n" +
				"_- Stone of Flock_ AOE up to 5x5 from 3x3, sheep duration increased slightly.\n" +
				"_- Stone of Deepened Sleep_ is now stone of deep sleep, instantly puts one enemy into magical sleep.\n" +
				"_- Stone of Clairvoyance_ AOE up to 20x20, from 12x12.\n" +
				"_- Stone of Aggression_ duration against enemies up 5, now works on bosses, and always forces attacking.\n" +
				"_- Stone of Affection_ is now stone of fear, it fears one target for 20 turns."));

		changes.addButton(new ChangeButton(Icons.get(Icons.PREFS), Messages.get(ChangesScene.class, "misc"),
				"_-_ Various tech and stability improvements.\n" +
				"_-_ Increased the minimum supported Android version to 4.0, from 2.3.\n" +
				"_-_ Game versions that use github for update checking can now opt-in to beta updates within the game.\n\n" +

				"_-_ Item renaming functionality has been moved to within the item info window.\n" +
				"_-_ Various minor UI improvements to the intro, welcome and about scenes.\n" +
				"_-_ Adjusted settings windows, removed some unnecessary elements.\n" +
				"_-_ Added info buttons to the scroll of enchantment window\n"+
				"_-_ Armor with the warrior's seal on it now states max shielding.\n" +
				"_-_ Bonus strength is now shown separately from base strength.\n\n" +

				"_-_ Improved the exit visuals on floor 10.\n" +
				"_-_ Becoming magic immune now also cleanses existing magical buffs and debuffs.\n" +
				"_-_ Traps that spawn visible or that never deactivate can no longer appear in enclosed spaces"));

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.SPINNER, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				"Fixed:\n" +
				"_-_ Various rare crash bugs\n" +
				"_-_ Various minor visual and text errors\n" +
				"_-_ damage warn triggering when hero gains HP from being hit\n" +
				"_-_ various rare bugs involving pitfall traps\n" +
				"_-_ disarming traps opening chests\n" +
				"\n" +
				"_-_ various minor errors with electricity effects\n" +
				"_-_ soul mark not working properly on low HP enemies with shielding\n" +
				"_-_ various rare errors with shadows buff\n" +
				"_-_ errors with time freeze and inter-floor teleportation mechanics\n" +
				"_-_ rooted characters not being immune to knockback effects\n" +
				"_-_ time stasis sometimes not preventing harmful effects in its last turn.\n" +
				"\n" +
				"_-_ wands losing max charge on save/load in rare cases\n" +
				"_-_ magical infusion clearing curses\n" +
				"_-_ dewdrops stacking on each other in rare cases\n" +
				"_-_ exploding skeletons not being blocked by transfusion shield in rare cases\n" +
				"_-_ rare incorrect interactions between swiftthistle and golden lotus\n" +
				"_-_ Rings not being renamable if they weren't IDed",

				"_-_ statues not becoming aggressive when debuffed\n" +
				"_-_ swapping places with allies reducing momentum\n" +
				"_-_ DK minions dropping imp quest tokens\n" +
				"_-_ giant succubi teleporting into enclosed spaces\n" +
				"_-_ spectral blades being blocked by allies\n" +
				"_-_ Spirit Hawk and Shadow Clone being corruptible\n" +
				"_-_ Rogue's body replacement ally being vulnerable to various AI-related debuffs\n" +
				"_-_ some ranged enemies becoming frozen if they were attacked from out of their vision\n" +
				"\n" +
				"_-_ gladiator combos dealing much more damage than intended in certain cases\n" +
				"_-_ magical charge and scroll empower interacting incorrectly\n" +
				"_-_ magical sight not working with farsight talent\n" +
				"_-_ perfect copy talent giving very slightly more HP than intended\n" +
				"_-_ wild magic using cursed wands as if they're normal"));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight(CharSprite.POSITIVE);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.CROWN, null), "Armor Ability Buffs pt.1",
				"Based on balance data and feedback, I'm making a bunch of buffs and adjustments to armor abilities and their related talents!\n\n" +
				"_- Endure_ bonus damage conversion rate up to 1/3 from 1/4.\n\n" +
				"_- Striking Wave_ effectiveness increased by 20%.\n" +
				"_- Shock Force_ now actually adds 20% damage per level as stated. Previously it only added 15%.\n\n" +
				"_- Wild Magic_ now boosts wand levels, instead of overriding them.\n" +
				"_- Conserved Magic_ now has a chance to give each wand a 3rd shot.\n" +
				"_- Conserved Magic_ charge cost reduction down to 33/55/70/80% from 44/69/82/90%.\n\n" +
				"_- Elemental Blast_ base damage increased to 15-25 from 10-20.\n" +
				"_- Elemental Power_ now boosts power by 20% per level, up from 15%.\n\n" +
				"_- Remote Beacon_ range per level increased to 4, from 3."));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.CROWN, null), "Armor Ability Buffs pt.2",
				"_- Shadow Clone_ now follows the hero at 2x speed.\n" +
				"_- Shadow Blade_ damage per level increased to 7.5% from 6.25%.\n" +
				"_- Cloned Armor_ armor per level increased to 15% from 12.5%.\n\n" +
				"_- Spirit Hawk_ evasion, accuracy, and duration increased by 20%.\n" +
				"_- Swift Spirit_ now gives 2/3/4/5 dodges, up from 1/2/3/4.\n" +
				"_- Go for the Eyes_ now gives 2/4/6/8 turns of blind, up from 2/3/4/5.\n\n" +
				"_- Spirit Blades_ effectiveness increased by 20%."));

		changes.addButton( new ChangeButton(new WoollyBomb(),
				"As stones of flock were buffed, I thought it was only fair to give woolly bombs some compensation buffs as well:\n\n" +
				"_-_ AOE size up to 9x9 from 5x5\n" +
				"_-_ Sheep duration up to 12-16 from 8-16"));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight(CharSprite.NEGATIVE);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new MagesStaff(),
				"The Mage continues to do too well in the early game since the talent changes in v0.9.1. Rather than weakening his talents and other magical abilities more, I've decided to make him more reliant on them instead by reducing his melee damage.\n\n" +
				"_- Mage's Staff_ base damage reduced to 1-7 from 1-8."));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.CROWN, null), "Armor Ability Nerfs",
				"I focused mostly on buffs this update, but a few abilities and talents do need to be scaled back a little:\n\n" +
				"_- Double Jump_ charge cost reduction down to 20/36/50/60%, from 24/42/56/67%.\n\n" +
				"_- Telefrag_ self damage increased to a flat 5 per level.\n\n" +
				"_- Smoke Bomb_ max range reduced to 6 tiles from 8.\n" +
				"_- Body Replacement_ armor reduced to 1-3 per level, from 1-5.\n" +
				"_- Hasty Retreat_ turns of haste/invis reduced to 1/2/3/4 from 2/3/4/5\n" +
				"_- Shadow Step_ charge cost reduction down to 20/36/50/60%, from 24/42/56/67%.\n\n" +
				"_- Double Mark_ charge cost reduction down to 30/50/65/75%, from 33/55/70/80%.\n\n" +
				"_- 13th armor ability_ now only lasts for 6 turns, but also no longer prevents EXP or item drops. I'm trying to retain the ability's core theme while making it a bit less effective at totally removing enemies.\n" +
				"_- resistance talent_ damage reduction reduced to 10/19/27/35%, from 10/20/30/40%."));

	}

}
