/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2024 Evan Debenham
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
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Honeypot;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
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
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
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

	private float triesToDrop = Float.MIN_VALUE;
	private int dropsToRare = Integer.MIN_VALUE;
	
	public String statsInfo() {
		if (isIdentified()){
			String info = Messages.get(this, "stats",
					Messages.decimalFormat("#.##", 100f * (Math.pow(1.20f, soloBuffedBonus()) - 1f)));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						Messages.decimalFormat("#.##", 100f * (Math.pow(1.20f, combinedBuffedBonus(Dungeon.hero)) - 1f)));
			}
			return info;
		} else {
			return Messages.get(this, "typical_stats", Messages.decimalFormat("#.##", 20f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.##", 100f * (Math.pow(1.2f, level+1)-1f)) + "%";
	}

	private static final String TRIES_TO_DROP = "tries_to_drop";
	private static final String DROPS_TO_RARE = "drops_to_rare";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(TRIES_TO_DROP, triesToDrop);
		bundle.put(DROPS_TO_RARE, dropsToRare);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		triesToDrop = bundle.getFloat(TRIES_TO_DROP);
		dropsToRare = bundle.getInt(DROPS_TO_RARE);
	}

	@Override
	protected RingBuff buff( ) {
		return new Wealth();
	}
	
	public static float dropChanceMultiplier( Char target ){
		return (float)Math.pow(1.20, getBuffedBonus(target, Wealth.class));
	}

	/*private static HashMap<Class<? extends Potion>, Float> potionChances = new HashMap<>();
	static{
		potionChances.put(UnstableBrew.class,       	5f);

		potionChances.put(PotionOfToxicGas.class,       4f);
		potionChances.put(PotionOfStormClouds.class,    4f);

		potionChances.put(CausticBrew.class,    		3f);
		potionChances.put(PotionOfLevitation.class,     3f);
		potionChances.put(AquaBrew.class,    			3f);

		potionChances.put(PotionOfPurity.class,         2f);
		potionChances.put(ShockingBrew.class,    		2f);
		potionChances.put(PotionOfLiquidFlame.class,    2f);
		potionChances.put(PotionOfFrost.class,          2f);

		potionChances.put(InfernalBrew.class,    		1f);
		potionChances.put(BlizzardBrew.class,			1f);
		potionChances.put(PotionOfMagicalSight.class,   1f);
		potionChances.put(PotionOfInvisibility.class,   1f);
	}

	private static HashMap<Class<? extends Scroll>, Float> scrollChances = new HashMap<>();
	static{
		scrollChances.put(ScrollOfMirrorImage.class,    4f);
		scrollChances.put(ScrollOfChallenge.class,    	3f);

		scrollChances.put(ScrollOfTeleportation.class,  2f);

		scrollChances.put(ScrollOfTerror.class,     	1f);
		scrollChances.put(ScrollOfAntiMagic.class,   	1f);
	}

	private static HashMap<Class<? extends Spell>, Float> spellChances = new HashMap<>();
	static{
		spellChances.put(UnstableSpell.class,    		4f);

		spellChances.put(TelekineticGrab.class,    		3f);
		spellChances.put(ReclaimTrap.class,    			3f);

		spellChances.put(BeaconOfReturning.class,    	2f);

		spellChances.put(PhaseShift.class,    			1f);
	}

	private static HashMap<Class<? extends Runestone>, Float> stoneChances = new HashMap<>();
	static{
		stoneChances.put(StoneOfFlock.class,    		3f);
		stoneChances.put(StoneOfFear.class,    			1f);
		stoneChances.put(StoneOfBlink.class,    		1f);
	}

	private static HashMap<Class<? extends Item>, Float> typeChances = new HashMap<>();
	static{
		typeChances.put(Potion.class,    				50f);
		typeChances.put(Scroll.class,    				20f);
		typeChances.put(Spell.class,    				15f);
		typeChances.put(Runestone.class,    			15f);
	}*/

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

		potionChances.put(InfernalBrew.class,    		1f);
		potionChances.put(BlizzardBrew.class,			1f);
		potionChances.put(PotionOfInvisibility.class,   1f);
		potionChances.put(PotionOfStamina.class,   		1f);
		potionChances.put(PotionOfParalyticGas.class,   1f);
		potionChances.put(ElixirOfDragonsBlood.class,   1f);
		potionChances.put(ElixirOfIcyTouch.class,   	1f);
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
		else return Reflection.newInstance(Gold.class);
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

		CounterBuff triesToDrop = target.buff(TriesToDropTracker.class);
		if (triesToDrop == null){
			triesToDrop = Buff.affect(target, TriesToDropTracker.class);
			triesToDrop.countUp( Random.NormalIntRange(1, 20) );
		}
		CounterBuff alchemizeLeft = target.buff(AlchemizeLeft.class);
		if (alchemizeLeft == null){
			alchemizeLeft = Buff.affect(target, AlchemizeLeft.class);
			alchemizeLeft.countUp( 2 );
		}
		CounterBuff alchemizeCounter = target.buff(AlchemizeCounter.class);
		if (alchemizeCounter == null){
			alchemizeCounter = Buff.affect(target, AlchemizeCounter.class);
			alchemizeCounter.countUp( Random.NormalIntRange(5, 7) );
		}

		//now handle reward logic
		ArrayList<Item> drops = new ArrayList<>();

		triesToDrop.countDown(tries);
		while ( triesToDrop.count() <= 0 ){
			Item i = null;

			if(alchemizeLeft.count() > 0) {
				if(alchemizeCounter.count() <= 0) {
					i = Reflection.newInstance(Alchemize.class).quantity(Random.NormalIntRange(3, 4));
					alchemizeCounter.countUp( Random.NormalIntRange(5, 7) );
					alchemizeLeft.countDown(1);
				} else {
					alchemizeCounter.countDown(1);
				}
			}

			if(i == null) {
				do {
					i = randomItem();
				} while (Challenges.isItemBlocked(i));
			}

			drops.add(i);
			latestDropTier = 1;
			triesToDrop.countUp( Random.NormalIntRange(1, 20) );
		}




		/*
		CounterBuff dropsToEquip = target.buff(DropsToEquipTracker.class);
		if (dropsToEquip == null){
			dropsToEquip = Buff.affect(target, DropsToEquipTracker.class);
			dropsToEquip.countUp( Random.NormalIntRange(5, 10) );
		}

		//now handle reward logic
		ArrayList<Item> drops = new ArrayList<>();

		triesToDrop.countDown(tries);
		while ( triesToDrop.count() <= 0 ){
			if (dropsToEquip.count() <= 0){
				int equipBonus = 0;

				//A second ring of wealth can be at most +1 when calculating wealth bonus for equips
				//This is to prevent using an upgraded wealth to farm another upgraded wealth and
				//using the two to get substantially more upgrade value than intended
				for (Wealth w : target.buffs(Wealth.class)){
					if (w.buffedLvl() > equipBonus){
						equipBonus = w.buffedLvl() + Math.min(equipBonus, 2);
					} else {
						equipBonus += Math.min(w.buffedLvl(), 2);
					}
				}

				Item i;
				do {
					i = genEquipmentDrop(equipBonus - 1);
				} while (Challenges.isItemBlocked(i));
				drops.add(i);
				dropsToEquip.countUp(Random.NormalIntRange(5, 10));
			} else {
				Item i;
				do {
					i = genConsumableDrop(bonus - 1);
				} while (Challenges.isItemBlocked(i));
				drops.add(i);
				dropsToEquip.countDown(1);
			}
			triesToDrop.countUp( Random.NormalIntRange(0, 20) );
		}
		 */
		
		return drops;
	}

	//used for visuals
	// 1/2/3 used for low/mid/high tier consumables
	// 3 used for +0-1 equips, 4 used for +2 or higher equips
	private static int latestDropTier = 0;

	public static void showFlareForBonusDrop( Visual vis ){
		if (vis == null || vis.parent == null) return;

		switch (latestDropTier){
			default:
				break; //do nothing
			case 1:
				new Flare(6, 20).color(0x00FF00, true).show(vis, 3f);
				break;
			case 2:
				new Flare(6, 24).color(0x00AAFF, true).show(vis, 3.33f);
				break;
			case 3:
				new Flare(6, 28).color(0xAA00FF, true).show(vis, 3.67f);
				break;
			case 4:
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

	private static Item genEquipmentDrop( int level ){
		Item result;
		//each upgrade increases depth used for calculating drops by 1
		int floorset = (Dungeon.depth + level)/5;
		switch (Random.Int(5)){
			default: case 0: case 1:
				Weapon w = Generator.randomWeapon(floorset, true);
				if (!w.hasGoodEnchant() && Random.Int(10) < level)      w.enchant();
				else if (w.hasCurseEnchant())                           w.enchant(null);
				result = w;
				break;
			case 2:
				Armor a = Generator.randomArmor(floorset);
				if (!a.hasGoodGlyph() && Random.Int(10) < level)        a.inscribe();
				else if (a.hasCurseGlyph())                             a.inscribe(null);
				result = a;
				break;
			case 3:
				result = Generator.randomUsingDefaults(Generator.Category.RING);
				break;
			case 4:
				result = Generator.random(Generator.Category.ARTIFACT);
				break;
		}
		//minimum level is 1/2/3/4/5/6 when ring level is 1/3/5/7/9/11
		if (result.isUpgradable()){
			int minLevel = (level+1)/2;
			if (result.level() < minLevel){
				result.level(minLevel);
			}
		}
		result.cursed = false;
		result.cursedKnown = true;
		if (result.level() >= 2) {
			latestDropTier = 4;
		} else {
			latestDropTier = 3;
		}
		return result;
	}

	public static void onLevelUp(Char target) {
		AlchemizeLeft alchemizeLeft = target.buff(AlchemizeLeft.class);
		if(getBuffedBonus(target, Wealth.class) > 0 && alchemizeLeft != null)
			alchemizeLeft.countUp(1);
	}

	public class Wealth extends RingBuff {

	}

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
