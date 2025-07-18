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

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Berserk;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Momentum;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.AuraOfProtection;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.BodyForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.HolyWard;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.LifeLinkSpell;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.AntiEntropy;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Bulk;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Corrosion;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Displacement;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Metabolism;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Multiplicity;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Overgrowth;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Stench;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Affection;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Brimstone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Camouflage;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Entanglement;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Flow;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Obfuscation;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Potential;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Repulsion;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Stone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Swiftness;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Thorns;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Viscosity;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfArcana;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ParchmentScrap;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ShardOfOblivion;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Arrays;

public class Armor extends EquipableItem {

	protected static final String AC_DETACH		= "DETACH";
	protected static final String AC_SWAP_GLYPH	= "SWAP_GLYPH";
	
	public enum Augment {
		EVASION (2f , -1f),
		DEFENSE (-2f, 1f),
		NONE	(0f   ,  0f);
		
		private float evasionFactor;
		private float defenceFactor;
		
		Augment(float eva, float df){
			evasionFactor = eva;
			defenceFactor = df;
		}
		
		public int evasionFactor(int level){
			return Math.round((2 + level) * evasionFactor);
		}
		
		public int defenseFactor(int level){
			return Math.round((2 + level) * defenceFactor);
		}
	}
	
	public Augment augment = Augment.NONE;
	
	public Glyph glyph;
	protected BrokenSeal seal;
	
	public boolean glyphHardened = false;
	public boolean curseInfusionBonus = false;
	public boolean masteryPotionBonus = false;
	
	public int tier;
	
	private static final int USES_TO_ID = 10;
	private float usesLeftToID = USES_TO_ID;
	private float availableUsesToID = USES_TO_ID/2f;
	
	public Armor( int tier ) {
		this.tier = tier;
	}
	
	private static final String USES_LEFT_TO_ID = "uses_left_to_id";
	private static final String AVAILABLE_USES  = "available_uses";
	private static final String GLYPH			= "glyph";
	private static final String GLYPH_HARDENED	= "glyph_hardened";
	private static final String CURSE_INFUSION_BONUS = "curse_infusion_bonus";
	private static final String MASTERY_POTION_BONUS = "mastery_potion_bonus";
	private static final String AUGMENT			= "augment";
	private static final String SEAL            = "seal";
	private static final String OLD_GLYPH		= "old_glyph";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( USES_LEFT_TO_ID, usesLeftToID );
		bundle.put( AVAILABLE_USES, availableUsesToID );
		bundle.put( GLYPH, glyph());
		bundle.put( GLYPH_HARDENED, glyphHardened );
		bundle.put( CURSE_INFUSION_BONUS, curseInfusionBonus );
		bundle.put( MASTERY_POTION_BONUS, masteryPotionBonus );
		bundle.put( AUGMENT, augment);
		bundle.put( SEAL, seal);
		bundle.put( OLD_GLYPH, oldGlyph);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		usesLeftToID = bundle.getInt( USES_LEFT_TO_ID );
		availableUsesToID = bundle.getInt( AVAILABLE_USES );
		inscribe((Glyph) bundle.get(GLYPH), true);
		glyphHardened = bundle.getBoolean(GLYPH_HARDENED);
		curseInfusionBonus = bundle.getBoolean( CURSE_INFUSION_BONUS );
		masteryPotionBonus = bundle.getBoolean( MASTERY_POTION_BONUS );
		augment = bundle.getEnum(AUGMENT, Augment.class);
		seal = (BrokenSeal)bundle.get(SEAL);
		if(seal != null) seal.armor = this;
		oldGlyph = (Glyph) bundle.get(OLD_GLYPH);
	}

	@Override
	public void reset() {
		super.reset();
		usesLeftToID = USES_TO_ID;
		availableUsesToID = USES_TO_ID/2f;
		//armor can be kept in bones between runs, the seal cannot.
		seal = null;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		if(seal != null) {
			if (Armor.runic == 1) {
				actions.add(AC_DETACH);
				actions.add(AC_SWAP_GLYPH);
			}
			else {
				actions.add(AC_DETACH);
			}
		}
		return actions;
	}
	
	@Override
	public String defaultAction() {
		if(seal != null && Armor.runic == 1) {
			return AC_SWAP_GLYPH;
		}
		else return super.defaultAction();
	}
	
	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_DETACH) && seal != null){
			detachSeal(true);
		}
		else if (action.equals(AC_SWAP_GLYPH) && seal != null){
			swapSealGlyph();
		}
	}

	@Override
	public boolean collect(Bag container) {
		if(super.collect(container)){
			Glyph active = activeGlyph();
			if (Dungeon.hero != null && Dungeon.hero.isAlive() && isIdentified() && active != null){
				Catalog.setSeen(active.getClass());
				Statistics.itemTypesDiscovered.add(active.getClass());
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Item identify(boolean byHero) {
		Glyph active = activeGlyph();
		if (active != null && byHero && Dungeon.hero != null && Dungeon.hero.isAlive()){
			Catalog.setSeen(active.getClass());
			Statistics.itemTypesDiscovered.add(active.getClass());
		}
		return super.identify(byHero);
	}

	public void setIDReady(){
		usesLeftToID = -1;
	}

	public boolean readyToIdentify(){
		return !isIdentified() && usesLeftToID <= 0;
	}

	@Override
	public boolean doEquip( Hero hero ) {

		// 15/25% chance
		if (hero.heroClass != HeroClass.CLERIC && hero.hasTalent(Talent.HOLY_INTUITION)
				&& cursed && !cursedKnown
				&& Random.Int(20) < 1 + 2*hero.pointsInTalent(Talent.HOLY_INTUITION)){
			cursedKnown = true;
			GLog.p(Messages.get(this, "curse_detected"));
			return false;
		}
		
		detach(hero.belongings.backpack);
		
		Armor oldArmor = hero.belongings.armor;
		if (hero.belongings.armor == null || hero.belongings.armor.doUnequip( hero, true, false )) {
			
			hero.belongings.armor = this;
			
			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.n( Messages.get(Armor.class, "equip_cursed") );
			}
			
			((HeroSprite)hero.sprite).updateArmor();
			activate(hero);
			Talent.onItemEquipped(hero, this);
			hero.spend( timeToEquip( hero ) );
			
			BrokenSeal oldSeal = oldArmor != null ? oldArmor.checkSeal() : null;
			if (oldSeal != null && checkSeal() == null) {
				GameScene.show(new WndOptions(
						new ItemSprite(ItemSpriteSheet.SEAL),
						Messages.titleCase(oldSeal.title()),
						Messages.get(Armor.class, "seal_transfer"),
						Messages.get(Armor.class, "seal_transfer_yes"),
						Messages.get(Armor.class, "seal_transfer_no")) {
					@Override
					protected void onSelect(int index) {
						super.onSelect(index);
						if (index == 0){
							oldArmor.detachSeal();
							Armor.this.affixSeal(oldSeal, true);
						}
						
						hero.next();
						super.hide();
					}
					@Override
					public void hide() {
						//do nothing, must press button
					}
				});
			} else {
				hero.next();
			}
			
			return true;
			
		} else {
			
			collect( hero.belongings.backpack );
			return false;
			
		}
	}

	@Override
	public void activate(Char ch) {
		super.activate(ch);
		if (seal != null) Buff.affect(ch, BrokenSeal.WarriorShield.class).setArmor(this);
	}
	
	public BrokenSeal checkSeal(){
		return seal;
	}
	
	public void affixSeal(BrokenSeal seal) {
		affixSeal(seal, false);
	}
	
	public void affixSeal(BrokenSeal seal, boolean operate) {
		this.seal = seal;
		seal.armor = this;
		
		if (seal.level() > 0){
			//doesn't trigger upgrading logic such as affecting curses/glyphs
			int newLevel = trueLevel()+1;
			level(newLevel);
			Badges.validateItemLevelAquired(this);
		}
		if (isEquipped(Dungeon.hero)){
			Buff.affect(Dungeon.hero, BrokenSeal.WarriorShield.class).setArmor(this);
		}
		
		if(operate) {
			GLog.p(Messages.get(BrokenSeal.class, "affix"));
			Dungeon.hero.sprite.operate(Dungeon.hero.pos);
			Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
			
			seal.detach(Dungeon.hero.belongings.backpack);
		}
		
		updateQuickslot();
	}
	
	public BrokenSeal detachSeal() {
		return detachSeal(false);
	}
	
	public BrokenSeal detachSeal(boolean operate) {
		if(seal == null) return null;
		
		BrokenSeal detaching = seal;
		seal = null;
		detaching.armor = null;
		
		BrokenSeal.WarriorShield sealBuff = Dungeon.hero.buff(BrokenSeal.WarriorShield.class);
		if (sealBuff != null) sealBuff.setArmor(null);
		
		if (detaching.level() > 0){
			level(trueLevel() - 1);
		}
		
		if (Armor.runic == 0){
			oldGlyph = null;
		}
		
		if(operate) {
			GLog.i( Messages.get(Armor.class, "detach_seal") );
			Dungeon.hero.sprite.operate(Dungeon.hero.pos);
			Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
			
			detaching.glyphChosen = false;
			if (!detaching.collect()){
				Dungeon.level.drop(detaching, Dungeon.hero.pos);
			}
		}
		
		updateQuickslot();
		return detaching;
	}
	
	private void swapSealGlyph() {
		seal.glyphChosen = !seal.glyphChosen;
		
		GLog.i( Messages.get(Armor.class, "swap_glyph") );
		Dungeon.hero.sprite.operate(Dungeon.hero.pos);
		Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
		
		updateQuickslot();
	}
	
	public void transfer() {
		if (seal != null && oldGlyph != null) {
			seal.inscribe(glyph());
			seal.curseInfusionBonus = curseInfusionBonus;
			seal.glyphChosen = true;
			
			curseInfusionBonus = false;
			inscribe(oldGlyph instanceof SealTransfer ? null : oldGlyph, true);
			oldGlyph = null;
			
			updateQuickslot();
		}
	}
	
	private Glyph oldGlyph;
	
	//dummy glyph to let us know we should transfer
	private static class SealTransfer extends Glyph { public int proc(Armor armor, Char attacker, Char defender, int damage) { return 0; } public ItemSprite.Glowing glowing() { return null; } }

	@Override
	public boolean doUnequip( Hero hero, boolean collect, boolean single ) {
		if (super.doUnequip( hero, collect, single )) {

			hero.belongings.armor = null;
			((HeroSprite)hero.sprite).updateArmor();

			BrokenSeal.WarriorShield sealBuff = hero.buff(BrokenSeal.WarriorShield.class);
			if (sealBuff != null) sealBuff.setArmor(null);

			return true;

		} else {

			return false;

		}
	}
	
	@Override
	public boolean isEquipped( Hero hero ) {
		return hero != null && hero.belongings.armor() == this;
	}

	public final int DRMax(){
		return DRMax(buffedLvl());
	}

	public int DRMax(int lvl){
		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
			return 1 + tier + lvl + augment.defenseFactor(lvl);
		}

		int max = tier * (2 + lvl) + augment.defenseFactor(lvl);
		if (lvl > max){
			return ((lvl - max)+1)/2;
		} else {
			return max;
		}
	}

	public final int DRMin(){
		return DRMin(buffedLvl());
	}

	public int DRMin(int lvl){
		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
			return 0;
		}

		int max = DRMax(lvl);
		if (lvl >= max){
			return (lvl - max);
		} else {
			return lvl;
		}
	}
	
	public float evasionFactor( Char owner, float evasion ){
		
		if (hasGlyph(Stone.class, owner) && !Stone.testingEvasion()){
			return 0;
		}
		
		if (owner instanceof Hero){
			int aEnc = STRReq() - ((Hero) owner).STR();
			if (aEnc > 0) evasion /= Math.pow(1.5, aEnc);
			
			Momentum momentum = owner.buff(Momentum.class);
			if (momentum != null){
				evasion += momentum.evasionBonus(((Hero) owner).lvl, Math.max(0, -aEnc));
			}
		}
		
		return evasion + augment.evasionFactor(buffedLvl());
	}
	
	public float speedFactor( Char owner, float speed ){
		
		if (owner instanceof Hero) {
			int aEnc = STRReq() - ((Hero) owner).STR();
			if (aEnc > 0) speed /= Math.pow(1.2, aEnc);
		}
		
		return speed;
		
	}
	
	@Override
	public int level() {
		int level = super.level();
		//TODO warrior's seal upgrade should probably be considered here too
		// instead of being part of true level
		if (curseInfusionBonus && glyph() == activeGlyph()) level += 1 + level/6;
		if (seal != null && seal.curseInfusion()) level += 1 + level/6;
		
		return level;
	}
	
	@Override
	public Item upgrade() {
		return upgrade( false );
	}
	
	public Item upgrade( boolean preserve ) {
		
		if (activeGlyph() != null && !preserve) {
			//chance to lose harden buff is 10/20/40/80/100% when upgrading from +6/7/8/9/10
			if (glyphHardened) {
				if (level() >= 6 && Random.Float(10) < Math.pow(2, level()-6)){
					glyphHardened = false;
				}

			//chance to remove curse is a static 33%
			} else if (hasCurseGlyph()){
				if (Random.Int(3) == 0) inscribe(null);

			//otherwise chance to lose glyph is 10/20/40/80/100% when upgrading from +4/5/6/7/8
			} else {

				//the chance from +4/5, and then +6 can be set to 0% with metamorphed runic transference
				int lossChanceStart = 4;
				if (Armor.runic > 0 && Dungeon.hero.heroClass != HeroClass.WARRIOR){
					lossChanceStart += 1+Armor.runic;
				}

				if (level() >= lossChanceStart && Random.Float(10) < Math.pow(2, level()-4)) {
					inscribe(null);
				}
			}
		}
		
		cursed = false;

		if (seal != null && seal.level() == 0)
			seal.upgrade();

		return super.upgrade();
	}
	
	public int proc( Char attacker, Char defender, int damage ) {
		Glyph active = activeGlyph();

		if (defender.buff(MagicImmune.class) == null) {
			Glyph trinityGlyph = null;
			if (Dungeon.hero.buff(BodyForm.BodyFormBuff.class) != null){
				trinityGlyph = Dungeon.hero.buff(BodyForm.BodyFormBuff.class).glyph();
				if (active != null && trinityGlyph != null && trinityGlyph.getClass() == active.getClass()){
					trinityGlyph = null;
				}
			}

			if (defender instanceof Hero && isEquipped((Hero) defender)
					&& defender.buff(HolyWard.HolyArmBuff.class) != null){
				if (active != null &&
						(((Hero) defender).subClass == HeroSubClass.PALADIN || hasCurseGlyph())){
					damage = active.proc( this, attacker, defender, damage );
				}
				if (trinityGlyph != null){
					damage = trinityGlyph.proc( this, attacker, defender, damage );
				}
				int blocking = ((Hero) defender).subClass == HeroSubClass.PALADIN ? 3 : 1;
				damage -= Math.round(blocking * Glyph.genericProcChanceMultiplier(defender));

			} else {
				Glyph extra = extraGlyph();
				
				if (active != null) {
					damage = active.proc(this, attacker, defender, damage);
				}
				if(extra != null && extra != active) {
					damage = extra.proc(this, attacker, defender, damage);
				}
				if (trinityGlyph != null){
					damage = trinityGlyph.proc( this, attacker, defender, damage );
				}
				//so that this effect procs for allies using this armor via aura of protection
				if (defender.alignment == Dungeon.hero.alignment
						&& Dungeon.hero.buff(AuraOfProtection.AuraBuff.class) != null
						&& (Dungeon.level.distance(defender.pos, Dungeon.hero.pos) <= 2 || defender.buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null)
						&& Dungeon.hero.buff(HolyWard.HolyArmBuff.class) != null) {
					int blocking = Dungeon.hero.subClass == HeroSubClass.PALADIN ? 3 : 1;
					damage -= Math.round(blocking * Glyph.genericProcChanceMultiplier(defender));
				}
			}
			damage = Math.max(damage, 0);
		}
		
		if (!levelKnown && defender == Dungeon.hero) {
			float uses = Math.min( availableUsesToID, Talent.itemIDSpeedFactor(Dungeon.hero, this) );
			availableUsesToID -= uses;
			usesLeftToID -= uses;
			if (usesLeftToID <= 0) {
				if (ShardOfOblivion.passiveIDDisabled()){
					if (usesLeftToID > -1){
						GLog.p(Messages.get(ShardOfOblivion.class, "identify_ready"), name());
					}
					setIDReady();
				} else {
					identify();
					GLog.p(Messages.get(Armor.class, "identify"));
					Badges.validateItemLevelAquired(this);
				}
			}
		}
		
		return damage;
	}
	
	@Override
	public void onHeroGainExp(float levelPercent, Hero hero) {
		levelPercent *= Talent.itemIDSpeedFactor(hero, this);
		if (!levelKnown && isEquipped(hero) && availableUsesToID <= USES_TO_ID/2f) {
			//gains enough uses to ID over 0.5 levels
			availableUsesToID = Math.min(USES_TO_ID/2f, availableUsesToID + levelPercent * USES_TO_ID);
		}
	}
	
	@Override
	public String name() {
		if (HolyWard.HolyArmBuff.active(this)) {
			return Messages.get(HolyWard.class, "glyph_name", super.name());
		} else {
			//warrior seal doesn't need to interact with cleric holy armor
			
			boolean main = displayGlyph();
			boolean extra = extraGlyph() != null;
			
			if(main && extra) {
				return extraGlyph().suffix( activeGlyph().name( super.name() ) );
			}
			else if(main) {
				return activeGlyph().name( super.name() );
			}
			else if(extra) {
				return extraGlyph().name( super.name() );
			}
			else {
				return super.name();
			}
		}
	}
	
	@Override
	public String info() {
		String info = super.info();
		
		if (levelKnown) {

			info += "\n\n" + Messages.get(Armor.class, "curr_absorb", tier, DRMin(), DRMax(), STRReq());
			
			if (Dungeon.hero != null && STRReq() > Dungeon.hero.STR()) {
				info += " " + Messages.get(Armor.class, "too_heavy");
			}
		} else {
			info += "\n\n" + Messages.get(Armor.class, "avg_absorb", tier, DRMin(0), DRMax(0), STRReq(0));

			if (Dungeon.hero != null && STRReq(0) > Dungeon.hero.STR()) {
				info += " " + Messages.get(Armor.class, "probably_too_heavy");
			}
		}

		switch (augment) {
			case EVASION:
				info += " " + Messages.get(Armor.class, "evasion");
				break;
			case DEFENSE:
				info += " " + Messages.get(Armor.class, "defense");
				break;
			case NONE:
		}
		
		Glyph active = activeGlyph();
		
		if(seal == null || Armor.runic == 0) {
			if (HolyWard.HolyArmBuff.active(this)){
				info += "\n\n" + Messages.capitalize(Messages.get(Armor.class, "inscribed", Messages.get(HolyWard.class, "glyph_name", Messages.get(Glyph.class, "glyph"))));
				info += " " + Messages.get(HolyWard.class, "glyph_desc");
			} else if (displayGlyph()) {
				info += "\n\n" + Messages.capitalize(Messages.get(Armor.class, "inscribed", active.name()));
				if (glyphHardened) info += " " + Messages.get(Armor.class, "glyph_hardened");
				info += " " + active.desc();
			} else if (glyphHardened){
				info += "\n\n" + Messages.get(Armor.class, "no_glyph_hardened");
			}
			
			if (seal != null) {
				info += "\n\n" + Messages.get(Armor.class, "seal_attached", seal.maxShield(tier, level()));
			}
		}
		else {
			if(glyph() != null && (!glyph.curse() || cursedKnown)) {
				if(!seal.overwriteGlyph()) {
					info += "\n\n" + Messages.capitalize(Messages.get(Armor.class, "inscribed", glyph().name()));
					info += " " + glyph().desc();
				} else {
					info += "\n\n" + Messages.capitalize(Messages.get(Armor.class, "inscribed_inactive", glyph().name()));
				}
			}
			
			if(seal.glyph() == null) {
				info += "\n\n" + Messages.get(Armor.class, "seal_attached", seal.maxShield(tier, level()));
			} else {
				if(seal.glyph() == active || seal.glyph() == extraGlyph()) {
					info += "\n\n" + Messages.get(Armor.class, "seal_attached_active", seal.maxShield(tier, level()), seal.glyph().name());
					info += " " + seal.glyph().desc();
				} else {
					info += "\n\n" + Messages.get(Armor.class, "seal_attached_inactive", seal.maxShield(tier, level()), seal.glyph().name());
				}
			}
			
			if (glyphHardened){
				info += "\n\n" + Messages.get(Armor.class, "generic_hardened");
			}
		}
		
		if (cursed && isEquipped( Dungeon.hero )) {
			info += "\n\n" + Messages.get(Armor.class, "cursed_worn");
		} else if (cursedKnown && cursed) {
			info += "\n\n" + Messages.get(Armor.class, "cursed");
		} else if (!isIdentified() && cursedKnown){
			if (hasCurseGlyph()) {
				info += "\n\n" + Messages.get(Armor.class, "weak_cursed");
			} else {
				info += "\n\n" + Messages.get(Armor.class, "not_cursed");
			}
		}
		
		return info;
	}

	@Override
	public Emitter emitter() {
		if (seal == null) return super.emitter();
		Emitter emitter = new Emitter();
		emitter.pos(ItemSpriteSheet.film.width(image)/2f + 2f, ItemSpriteSheet.film.height(image)/3f);
		emitter.fillTarget = false;
		emitter.pour(Speck.factory( Speck.RED_LIGHT ), 0.6f);
		return emitter;
	}

	@Override
	public Item random() {
		//+0: 75% (3/4)
		//+1: 20% (4/20)
		//+2: 5%  (1/20)
		int n = 0;
		if (Random.Int(4) == 0) {
			n++;
			if (Random.Int(5) == 0) {
				n++;
			}
		}
		level(n);

		//we use a separate RNG here so that variance due to things like parchment scrap
		//does not affect levelgen
		Random.pushGenerator(Random.Long());

			//30% chance to be cursed
			//15% chance to be inscribed
			float effectRoll = Random.Float();
			if (effectRoll < 0.3f * ParchmentScrap.curseChanceMultiplier()) {
				inscribe(Glyph.randomCurse());
				cursed = true;
			} else if (effectRoll >= 1f - (0.15f * ParchmentScrap.enchantChanceMultiplier())){
				inscribe();
			}

		Random.popGenerator();

		return this;
	}

	public int STRReq(){
		return STRReq(level());
	}

	public int STRReq(int lvl){
		int req = STRReq(tier, lvl);
		if (masteryPotionBonus){
			req -= 2;
		}
		return req;
	}

	protected static int STRReq(int tier, int lvl){
		lvl = Math.max(0, lvl);

		//strength req decreases at +1,+3,+6,+10,etc.
		return (8 + Math.round(tier * 2)) - (int)(Math.sqrt(8 * lvl + 1) - 1)/2;
	}
	
	@Override
	public int value() {
		if (seal != null) return 0;

		int price = 20 * tier;
		if (hasGoodGlyph()) {
			price *= 1.5;
		}
		if (cursedKnown && (cursed || hasCurseGlyph())) {
			price /= 2;
		}
		if (levelKnown && level() > 0) {
			price *= (level() + 1);
		}
		if (price < 1) {
			price = 1;
		}
		return price;
	}
	
	
	// *** GLYPH ***
	public Glyph glyph() {
		return glyph;
	}
	
	public void glyph(Glyph glyph) {
		this.glyph = glyph;
		if (glyph == null || !glyph.curse()) curseInfusionBonus = false;
	}
	
	public Glyph activeGlyph() {
		if(seal != null && seal.overwriteGlyph()) {
			return seal.glyph();
		}
		else return glyph();
	}
	
	public Glyph extraGlyph() {
		if(seal != null && Armor.runic == 2) {
			return seal.glyph();
		}
		else return null;
	}
	
	public Armor inscribe() {
		return inscribe(false);
	}
	
	public Armor inscribe(boolean force) {

		Class<? extends Glyph> oldGlyphClass = glyph() != null ? glyph().getClass() : null;
		Class<? extends Glyph> oldSealGlyphClass = seal != null && seal.glyph() != null ? seal.glyph().getClass() : null;
		Glyph gl = Glyph.random( oldGlyphClass, oldSealGlyphClass );

		return inscribe( gl, force );
	}
	
	public Armor inscribe( Glyph glyph ) {
		return inscribe(glyph, false);
	}
	
	public Armor inscribe( Glyph glyph, boolean force ) {
		if (seal != null && !force) {
			if (seal.overwriteGlyph()) {
				seal.inscribe(glyph);
				return this;
			}
			
			else if(Armor.runic == 0) {
				oldGlyph = this.glyph == null ? new SealTransfer() : this.glyph;
			}
		}
		
		this.glyph(glyph);
		
		if (glyph != null && isIdentified() && Dungeon.hero != null
			&& Dungeon.hero.isAlive() && Dungeon.hero.belongings.contains(this)) {
			
			Catalog.setSeen(glyph.getClass());
			Statistics.itemTypesDiscovered.add(glyph.getClass());
		}
		return this;
	}
	
	public Class<? extends Armor.Glyph> armorGlyphClass() {
		return glyph() != null ? glyph().getClass() : null;
	}
	
	public Class<? extends Armor.Glyph> sealGlyphClass() {
		return (checkSeal() != null && checkSeal().glyph() != null) ? checkSeal().glyph().getClass() : null;
	}

	public boolean hasGlyph(Class<?extends Glyph> type, Char owner) {
		if (owner.buff(MagicImmune.class) != null) {
			return false;
		}
		else if (owner == Dungeon.hero
				&& HolyWard.HolyArmBuff.active(this)) {
			return false;
		}
		else if (owner.buff(BodyForm.BodyFormBuff.class) != null
				&& owner.buff(BodyForm.BodyFormBuff.class).glyph() != null
				&& owner.buff(BodyForm.BodyFormBuff.class).glyph().getClass().equals(type)){
			return true;
		}
		else {
			return (activeGlyph() != null && activeGlyph().getClass() == type) || (extraGlyph() != null && extraGlyph().getClass() == type);
		}
	}

	//these are not used to process specific glyph effects, so magic immune doesn't affect them
	public boolean hasGoodGlyph(){
		return activeGlyph() != null && !activeGlyph().curse();
	}

	public boolean hasCurseGlyph(){
		return activeGlyph() != null && activeGlyph().curse();
	}
	
	public boolean hasCurseGlyph(boolean seal){
		if(seal) {
			return this.seal != null && this.seal.glyph() != null && this.seal.glyph().curse();
		}
		else {
			return glyph() != null && glyph().curse();
		}
	}
	
	public boolean curseInfusion() {
		return  (curseInfusionBonus && glyph() == activeGlyph()) ||
				(seal != null && seal.curseInfusion());
	}
	
	public boolean displayGlyph() {
		return activeGlyph() != null && (!activeGlyph().curse() || cursedKnown || (Armor.runic > 0 && seal != null && seal.overwriteGlyph()));
	}

	@Override
	public ItemSprite.Glowing glowing() {
		if (HolyWard.HolyArmBuff.active(this)){
			return HolyWard.HolyArmBuff.HOLY;
		} else {
			return displayGlyph() ? activeGlyph().glowing() : null;
		}
	}
	
	
	//we cache this since it gets called a lot
	public static int runic = 0;
	public static void cacheRunic(int points) {
		runic = points;
	}
	
	public static abstract class Glyph implements Bundlable {
		
		public static final Class<?>[] common = new Class<?>[]{
				Viscosity.class, Brimstone.class, Affection.class, Potential.class, Obfuscation.class };

		public static final Class<?>[] uncommon = new Class<?>[]{
				Thorns.class, Repulsion.class, Flow.class, Entanglement.class, Swiftness.class };

		public static final Class<?>[] rare = new Class<?>[]{
				Stone.class, Camouflage.class, AntiMagic.class };

		public static final float[] typeChances = new float[]{
				50, //12.5% each
				40, //6.67% each
				10  //3.33% each
		};

		public static final Class<?>[] curses = new Class<?>[]{
				AntiEntropy.class, Corrosion.class, Displacement.class, Metabolism.class,
				Multiplicity.class, Stench.class, Overgrowth.class, Bulk.class
		};
		
		public abstract int proc( Armor armor, Char attacker, Char defender, int damage );

		protected float procChanceMultiplier( Char defender ){
			return genericProcChanceMultiplier( defender );
		}

		public static float genericProcChanceMultiplier( Char defender ){
			float multi = RingOfArcana.enchantPowerMultiplier(defender);

			Berserk berserk = defender.buff(Berserk.class);
			if (berserk != null) {
				multi += berserk.enchantBoost(true);
			}

			if (Dungeon.hero.alignment == defender.alignment
					&& Dungeon.hero.buff(AuraOfProtection.AuraBuff.class) != null
					&& (Dungeon.level.distance(defender.pos, Dungeon.hero.pos) <= 2 || defender.buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null)){
				multi += 0.25f + 0.25f*Dungeon.hero.pointsInTalent(Talent.AURA_OF_PROTECTION);
			}

			return multi;
		}
		
		public String name() {
			if (!curse())
				return name( Messages.get(this, "glyph") );
			else
				return name( Messages.get(Item.class, "curse"));
		}
		
		public String name( String armorName ) {
			return Messages.get(this, "name", armorName);
		}
		
		public String suffix( String armorName ) {
			return Messages.get(this, "name_suffix", armorName);
		}

		public String desc() {
			return Messages.get(this, "desc");
		}

		public boolean curse() {
			return false;
		}
		
		@Override
		public void restoreFromBundle( Bundle bundle ) {
		}

		@Override
		public void storeInBundle( Bundle bundle ) {
		}
		
		public abstract ItemSprite.Glowing glowing();

		@SuppressWarnings("unchecked")
		public static Glyph random( Class<? extends Glyph> ... toIgnore ) {
			switch(Random.chances(typeChances)){
				case 0: default:
					return randomCommon( toIgnore );
				case 1:
					return randomUncommon( toIgnore );
				case 2:
					return randomRare( toIgnore );
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Glyph randomCommon( Class<? extends Glyph> ... toIgnore ){
			ArrayList<Class<?>> glyphs = new ArrayList<>(Arrays.asList(common));
			glyphs.removeAll(Arrays.asList(toIgnore));
			if (glyphs.isEmpty()) {
				return random();
			} else {
				return (Glyph) Reflection.newInstance(Random.element(glyphs));
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Glyph randomUncommon( Class<? extends Glyph> ... toIgnore ){
			ArrayList<Class<?>> glyphs = new ArrayList<>(Arrays.asList(uncommon));
			glyphs.removeAll(Arrays.asList(toIgnore));
			if (glyphs.isEmpty()) {
				return random();
			} else {
				return (Glyph) Reflection.newInstance(Random.element(glyphs));
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Glyph randomRare( Class<? extends Glyph> ... toIgnore ){
			ArrayList<Class<?>> glyphs = new ArrayList<>(Arrays.asList(rare));
			glyphs.removeAll(Arrays.asList(toIgnore));
			if (glyphs.isEmpty()) {
				return random();
			} else {
				return (Glyph) Reflection.newInstance(Random.element(glyphs));
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Glyph randomCurse( Class<? extends Glyph> ... toIgnore ){
			ArrayList<Class<?>> glyphs = new ArrayList<>(Arrays.asList(curses));
			glyphs.removeAll(Arrays.asList(toIgnore));
			if (glyphs.isEmpty()) {
				return random();
			} else {
				return (Glyph) Reflection.newInstance(Random.element(glyphs));
			}
		}
		
	}
}
