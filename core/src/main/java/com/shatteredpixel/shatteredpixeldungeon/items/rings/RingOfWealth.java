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

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CounterBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Honeypot;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.WealthDrop;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfExperience;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLevitation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfParalyticGas;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfPurity;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.WealthPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.AquaBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.BlizzardBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.CausticBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.InfernalBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.ShockingBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.UnstableBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfDragonsBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfIcyTouch;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfCorrosiveGas;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfDivineInspiration;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfDragonsBreath;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfMagicalSight;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfShroudingFog;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfSnapFreeze;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfStamina;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfStormClouds;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTerror;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.WealthScroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfAntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfMetamorphosis;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfSirensSong;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Alchemize;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.BeaconOfReturning;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.PhaseShift;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.ReclaimTrap;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Spell;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.TelekineticGrab;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.UnstableSpell;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.WealthSpell;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAggression;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfBlink;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfClairvoyance;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfDeepSleep;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfEnchantment;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfFear;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfFlock;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfShock;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.WealthStone;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ExoticCrystals;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndQuickBag;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Game;
import com.watabou.noosa.Visual;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.HashMap;

public class RingOfWealth extends Ring {

	{
		icon = ItemSpriteSheet.Icons.RING_WEALTH;
		buffClass = Wealth.class;
	}
	
	public static final String AC_OPEN = "OPEN";
	public Item quickUseItem;
	
	public static ArrayList<Item> getWealthDrops() {
		ArrayList<Item> drops = new ArrayList<>();
		for (Item item : Dungeon.hero.belongings) {
			if(item instanceof WealthDrop) drops.add(item);
		}
		
		return drops;
	}
	
	@Override
	public int targetingPos(Hero user, int dst) {
		if (quickUseItem != null){
			return quickUseItem.targetingPos(user, dst);
		} else {
			return super.targetingPos(user, dst);
		}
	}
	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		if(isKnown()) {
			actions.add(AC_OPEN);
		}
		return actions;
	}
	
	@Override
	public String defaultAction() {
		if(isKnown()) {
			return AC_OPEN;
		}
		else return super.defaultAction();
	}
	
	@Override
	public void execute(Hero hero, String action) {
		quickUseItem = null;
		super.execute(hero, action);
		
		if(action.equals(AC_OPEN)) {
			if(!getWealthDrops().isEmpty()) {
				Game.runOnRenderThread(() -> GameScene.show( new WndQuickBag( this ) ));
			}
			else {
				GLog.i(Messages.get(this, "no_drops"));
			}
		}
	}
	
	public static void setExtra(BitmapText extra) {
		WealthDrop.Decay latest = null;
		
		if(Dungeon.hero != null) {
			for(WealthDrop.Decay decay : Dungeon.hero.buffs(WealthDrop.Decay.class)) {
				if(latest == null || latest.cooldown() > decay.cooldown()) {
					latest = decay;
				}
			}
		}
		
		if(latest == null) {
			extra.text( null );
			extra.resetColor();
		}
		else {
			extra.text(latest.iconTextDisplay());
			extra.measure();
			
			//we use a simplified version to not call buff() on render thread a bunch, assume max is 200
			float percent = latest.cooldown() / latest.max;
			extra.hardlight(1f, percent, percent);
		}
	}
	
	public String statsInfo() {
		if (isIdentified()){
			String info = Messages.get(this, "stats",
					Messages.decimalFormat("#.##", dropGenRate(soloBuffedBonus())));

			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						Messages.decimalFormat("#.##", dropGenRate(combinedBuffedBonus(Dungeon.hero))));
			}
			return info;
		} else {
			return Messages.get(this, "typical_stats", Messages.decimalFormat("#.##", dropGenRate(1)));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.##", dropGenRate(level+1)) + "x";
	}

	public String upgradeStat2(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.##", dropGenRate(level+1)) + "x";
	}

	@Override
	protected RingBuff buff() {
		return new Wealth();
	}
	
	public static float dropChanceMultiplier( Char target ){
		//only alter on cursed rings
		return (float) Math.min(1, Math.pow(1.25, getBuffedBonus(target, Wealth.class)));
	}

	public static float dropGenRate(int buffedLvl) {
		return 1f + .25f * (buffedLvl-1);
	}

	private static HashMap<Class<? extends Potion>, Float> potionChances = new HashMap<>();
	static{
		potionChances.put(UnstableBrew.class,       	4f);

		potionChances.put(PotionOfToxicGas.class,       3f);
		potionChances.put(PotionOfStormClouds.class,    3f);
		potionChances.put(CausticBrew.class,    		3f);
		potionChances.put(PotionOfLevitation.class,     3f);
		potionChances.put(AquaBrew.class,    			3f);
		potionChances.put(ShockingBrew.class,    		3f);
		potionChances.put(PotionOfLiquidFlame.class,    3f);
		potionChances.put(PotionOfFrost.class,          3f);

		potionChances.put(PotionOfMagicalSight.class,   2f);
		potionChances.put(PotionOfSnapFreeze.class,   	2f);
		potionChances.put(PotionOfDragonsBreath.class,  2f);
		potionChances.put(PotionOfCorrosiveGas.class,   2f);
		potionChances.put(PotionOfShroudingFog.class,   2f);
		potionChances.put(PotionOfHaste.class,   		2f);
		potionChances.put(PotionOfPurity.class,         2f);

		potionChances.put(ElixirOfDragonsBlood.class,	1f);
		potionChances.put(ElixirOfIcyTouch.class,		1f);
		potionChances.put(PotionOfInvisibility.class,   1f);
		potionChances.put(PotionOfStamina.class,   		1f);
		potionChances.put(PotionOfParalyticGas.class,   1f);
	}

	private static HashMap<Class<? extends Scroll>, Float> scrollChances = new HashMap<>();
	static{
		scrollChances.put(ScrollOfMirrorImage.class,    3f);
		scrollChances.put(ScrollOfChallenge.class,    	3f);
		scrollChances.put(ScrollOfTeleportation.class,  3f);

		scrollChances.put(ScrollOfMagicMapping.class,   2f);
		scrollChances.put(ScrollOfRage.class,  		    2f);

		scrollChances.put(ScrollOfTerror.class,     	1f);
		scrollChances.put(ScrollOfRetribution.class,    1f);
		scrollChances.put(ScrollOfSirensSong.class,   	1f);
		scrollChances.put(ScrollOfAntiMagic.class,   	1f);
	}

	private static HashMap<Class<? extends Runestone>, Float> stoneChances = new HashMap<>();
	static{
		stoneChances.put(StoneOfFlock.class,    		3f);

		stoneChances.put(StoneOfClairvoyance.class,    	2f);
		stoneChances.put(StoneOfFear.class,    			2f);

		stoneChances.put(StoneOfShock.class,    		1f);
		stoneChances.put(StoneOfBlink.class,    		1f);
		stoneChances.put(StoneOfDeepSleep.class,    	1f);
		stoneChances.put(StoneOfAggression.class,    	1f);
	}

	private static HashMap<Class<? extends Spell>, Float> spellChances = new HashMap<>();
	static{
		spellChances.put(TelekineticGrab.class,    		3f);
		spellChances.put(ReclaimTrap.class,    			3f);

		spellChances.put(BeaconOfReturning.class,    	2f);
		spellChances.put(PhaseShift.class,    			2f);
		spellChances.put(UnstableSpell.class,    		2f);
	}

	private static HashMap<Class<? extends Item>, Float> typeChances = new HashMap<>();
	static{
		typeChances.put(Potion.class,    				40f);
		typeChances.put(Scroll.class,    				25f);
		typeChances.put(Runestone.class,    			20f);
		typeChances.put(Spell.class,    				15f);
	}

	private static HashMap<Class<? extends Item>, Integer> itemRarities = new HashMap<>();
	static{
		itemRarities.put(UnstableBrew.class,       		1);
		itemRarities.put(PotionOfToxicGas.class,       	1);
		itemRarities.put(PotionOfStormClouds.class,    	1);
		itemRarities.put(CausticBrew.class,    		   	1);
		itemRarities.put(PotionOfLevitation.class,		1);
		itemRarities.put(AquaBrew.class,    			1);
		itemRarities.put(ShockingBrew.class,    		1);
		itemRarities.put(PotionOfLiquidFlame.class,    	1);
		itemRarities.put(PotionOfFrost.class,          	1);



		itemRarities.put(PotionOfMagicalSight.class,   	2);
		itemRarities.put(PotionOfSnapFreeze.class,   	2);
		itemRarities.put(PotionOfDragonsBreath.class,	2);
		itemRarities.put(PotionOfCorrosiveGas.class,	2);
		itemRarities.put(PotionOfShroudingFog.class, 	2);
		itemRarities.put(PotionOfHaste.class,   		2);
		itemRarities.put(PotionOfPurity.class,         	2);

		itemRarities.put(ScrollOfMirrorImage.class,    	2);
		itemRarities.put(ScrollOfChallenge.class,		2);
		itemRarities.put(ScrollOfTeleportation.class,  	2);
		itemRarities.put(ScrollOfMagicMapping.class,   	2);
		itemRarities.put(ScrollOfRage.class,  		    2);

		itemRarities.put(StoneOfFlock.class,			2);
		itemRarities.put(StoneOfClairvoyance.class,		2);
		itemRarities.put(StoneOfFear.class,    			2);

		itemRarities.put(TelekineticGrab.class,    		2);
		itemRarities.put(ReclaimTrap.class,    			2);
		itemRarities.put(BeaconOfReturning.class,    	2);
		itemRarities.put(PhaseShift.class,    			2);
		itemRarities.put(UnstableSpell.class,    		2);
		
		
		
		itemRarities.put(ElixirOfDragonsBlood.class,   	3);
		itemRarities.put(ElixirOfIcyTouch.class,   		3);
		itemRarities.put(PotionOfInvisibility.class,   	3);
		itemRarities.put(PotionOfStamina.class,   		3);
		itemRarities.put(PotionOfParalyticGas.class,   	3);

		itemRarities.put(ScrollOfTerror.class,   		3);
		itemRarities.put(ScrollOfRetribution.class, 	3);
		itemRarities.put(ScrollOfSirensSong.class,   	3);
		itemRarities.put(ScrollOfAntiMagic.class,   	3);

		itemRarities.put(StoneOfShock.class,    		3);
		itemRarities.put(StoneOfBlink.class,    		3);
		itemRarities.put(StoneOfDeepSleep.class,    	3);
		itemRarities.put(StoneOfAggression.class,    	3);
	}

	public static Item randomItem() {
		Class<?> type = Random.chances(typeChances);

		if(type == Potion.class) {
			return randomPotion();
		}
		else if(type == Scroll.class) {
			return randomScroll();
		}
		else if(type == Spell.class) {
			return randomSpell();
		}
		else if(type == Runestone.class) {
			return randomStone();
		}
		else return Reflection.newInstance(Gold.class).quantity(1);
	}

	public static WealthPotion randomPotion() {
		WealthPotion drop = Reflection.newInstance(WealthPotion.class);
		drop.set(Random.chances(potionChances));
		if(drop.item() instanceof AquaBrew) drop.quantity(2);
		return drop;
	}

	public static WealthScroll randomScroll() {
		WealthScroll drop = Reflection.newInstance(WealthScroll.class);
		drop.set(Random.chances(scrollChances));
		return drop;
	}

	public static WealthSpell randomSpell() {
		WealthSpell drop = Reflection.newInstance(WealthSpell.class);
		drop.set(Random.chances(spellChances));
		if(drop.item() instanceof TelekineticGrab) drop.quantity(2);
		return drop;
	}

	public static WealthStone randomStone() {
		WealthStone drop = Reflection.newInstance(WealthStone.class);
		drop.set(Random.chances(stoneChances));
		return drop;
	}
	
	public static ArrayList<Item> tryForBonusDrop(Char target, int tries ){
		int bonus = getBuffedBonus(target, Wealth.class);
		if (bonus <= 0) return null;

		int max = Math.round(30f / dropGenRate(bonus));
		CounterBuff triesToDrop = 		Buff.count(target, TriesToDropTracker.class,
										Random.NormalIntRange(1, max));
		
		CounterBuff alchemizeLeft = 	Buff.count(target, AlchemizeLeft.class, 2);
		
		CounterBuff alchemizeCounter = 	Buff.count(target, AlchemizeCounter.class,
										Random.NormalIntRange(5, 7));

		//now handle reward logic
		ArrayList<Item> drops = new ArrayList<>();

		triesToDrop.countDown(tries);
		while ( triesToDrop.count() <= 0 ){
			triesToDrop.countUp( Random.NormalIntRange(1, max) );
			
			if(alchemizeLeft.count() > 0) {
				if(alchemizeCounter.count() <= 0) {
					
					Alchemize alchemize = Reflection.newInstance(Alchemize.class);
					alchemize.quantity(Random.NormalIntRange(3, 4) + bonus);
					drops.add(alchemize);
					
					alchemizeCounter.countUp( Random.NormalIntRange(5, 7) );
					alchemizeLeft.countDown(1);
				} else {
					alchemizeCounter.countDown(1);
				}
			}

			Item drop;
			do {
				drop = randomItem();
			} while (Challenges.isItemBlocked(drop));

			drops.add(drop);

			Integer rarity = itemRarities.get(drop.getClass());
			latestDropTier = Math.max(latestDropTier, rarity != null ? rarity : 0);
		}

		return drops;
	}

	//used for visuals
	// 1/2/3 used for low/mid/high tier consumables
	// 3 used for +0-1 equips, 4 used for +2 or higher equips
	private static int latestDropTier = 0;

	public static void showFlareForBonusDrop( Visual vis ){
		if (vis == null || vis.parent == null) return;

		switch (latestDropTier){
			case 0: default:
				new Flare(6, 20).color(0x00FF00, true).show(vis, 3f);
				break;
			case 1:
				new Flare(6, 24).color(0x00AAFF, true).show(vis, 3.33f);
				break;
			case 2:
				new Flare(6, 28).color(0xAA00FF, true).show(vis, 3.67f);
				break;
			case 3:
				new Flare(6, 32).color(0xFFAA00, true).show(vis, 4f);
				break;
		}
		latestDropTier = 0;
	}
	
	public static Item genConsumableDrop(int level) {
		float roll = Random.Float();
		//60% chance - 4% per level. Starting from +15: 0%
		if (roll < (0.6f - 0.04f * level)) {
			latestDropTier = 1;
			return genLowValueConsumable();
		//30% chance + 2% per level. Starting from +15: 60%-2%*(lvl-15)
		} else if (roll < (0.9f - 0.02f * level)) {
			latestDropTier = 2;
			return genMidValueConsumable();
		//10% chance + 2% per level. Starting from +15: 40%+2%*(lvl-15)
		} else {
			latestDropTier = 3;
			return genHighValueConsumable();
		}
	}

	private static Item genLowValueConsumable(){
		switch (Random.Int(4)){
			case 0: default:
				Item i = new Gold().random();
				return i.quantity(i.quantity()/2);
			case 1:
				return Generator.randomUsingDefaults(Generator.Category.STONE);
			case 2:
				return Generator.randomUsingDefaults(Generator.Category.POTION);
			case 3:
				return Generator.randomUsingDefaults(Generator.Category.SCROLL);
		}
	}

	private static Item genMidValueConsumable(){
		switch (Random.Int(6)){
			case 0: default:
				Item i = genLowValueConsumable();
				return i.quantity(i.quantity()*2);
			case 1:
				i = Generator.randomUsingDefaults(Generator.Category.POTION);
				if (!(i instanceof ExoticPotion)) {
					return Reflection.newInstance(ExoticPotion.regToExo.get(i.getClass()));
				} else {
					return Reflection.newInstance(i.getClass());
				}
			case 2:
				i = Generator.randomUsingDefaults(Generator.Category.SCROLL);
				if (!(i instanceof ExoticScroll)){
					return Reflection.newInstance(ExoticScroll.regToExo.get(i.getClass()));
				} else {
					return Reflection.newInstance(i.getClass());
				}
			case 3:
				return Random.Int(2) == 0 ? new UnstableBrew() : new UnstableSpell();
			case 4:
				return new Bomb();
			case 5:
				return new Honeypot();
		}
	}

	private static Item genHighValueConsumable(){
		switch (Random.Int(4)){
			case 0: default:
				Item i = genMidValueConsumable();
				if (i instanceof Bomb){
					return new Bomb.DoubleBomb();
				} else {
					return i.quantity(i.quantity()*2);
				}
			case 1:
				return new StoneOfEnchantment();
			case 2:
				return Random.Float() < ExoticCrystals.consumableExoticChance() ? new PotionOfDivineInspiration() : new PotionOfExperience();
			case 3:
				return Random.Float() < ExoticCrystals.consumableExoticChance() ? new ScrollOfMetamorphosis() : new ScrollOfTransmutation();
		}
	}

	public static void onLevelUp(Char target) {
		AlchemizeLeft alchemizeLeft = target.buff(AlchemizeLeft.class);
		if(getBuffedBonus(target, Wealth.class) > 0 && alchemizeLeft != null) {
			alchemizeLeft.countUp(1);
		}
	}

	public class Wealth extends RingBuff {}

	public static class TriesToDropTracker extends CounterBuff {
		{
			revivePersists = true;
		}
	}
	public static class AlchemizeLeft extends CounterBuff {
		{
			revivePersists = true;
		}
	}
	public static class AlchemizeCounter extends CounterBuff {
		{
			revivePersists = true;
		}
	}
}
