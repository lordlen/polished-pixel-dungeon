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

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Combo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Regeneration;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ShieldBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndUseItem;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class BrokenSeal extends Item {

	public static final String AC_AFFIX = "AFFIX";

	//only to be used from the quickslot, for tutorial purposes mostly.
	public static final String AC_INFO = "INFO_WINDOW";

	{
		image = ItemSpriteSheet.SEAL;

		cursedKnown = levelKnown = true;
		unique = true;
		bones = false;

		defaultAction = AC_INFO;
	}
	

	public static Armor armor;
	private Armor.Glyph glyph;
	public boolean glyphChosen = false;

	public boolean curseInfusionBonus = false;
	
	public boolean overwriteGlyph() {
		if(Dungeon.hero == null) return false;
		
		if(Armor.runic == 0) {
			return false;
		}
		else if(Armor.runic == 1)
			return glyphChosen;
		else
			return false;
	}
	
	public boolean curseInfusion() {
		if(!curseInfusionBonus) return false;
		return armor == null || armor.activeGlyph() == glyph() || armor.extraGlyph() == glyph();
	}

	public BrokenSeal inscribe( ) {
		Class<? extends Armor.Glyph> oldGlyphClass = glyph != null ? glyph.getClass() : null;
		Armor.Glyph gl = Armor.Glyph.random( oldGlyphClass, armorGlyphClass() );

		return inscribe( gl );
	}

	public BrokenSeal inscribe( Armor.Glyph glyph ) {
		if (glyph == null || !glyph.curse()) curseInfusionBonus = false;
		this.glyph = glyph;

		if (glyph != null) {
			Catalog.setSeen(glyph.getClass());
			Statistics.itemTypesDiscovered.add(glyph.getClass());
		}
		return this;
	}

	public Armor.Glyph glyph(){
		return glyph;
	}
	
	//these are not used to process specific glyph effects, so magic immune doesn't affect them
	public boolean hasGoodGlyph(){
		return glyph() != null && !glyph().curse();
	}
	
	public boolean hasCurseGlyph(){
		return glyph() != null && glyph().curse();
	}
	
	public Class<? extends Armor.Glyph> sealGlyphClass() {
		return glyph() != null ? glyph().getClass() : null;
	}
	
	public Class<? extends Armor.Glyph> armorGlyphClass() {
		return (armor != null && armor.glyph() != null) ? armor.glyph().getClass() : null;
	}

	public int maxShield( int armTier, int armLvl ){
		// 5-15, based on equip tier and iron will
		return 3 + 2*armTier + Dungeon.hero.pointsInTalent(Talent.IRON_WILL);
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return glyph != null ? glyph.glowing() : null;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions =  super.actions(hero);
		actions.add(AC_AFFIX);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_AFFIX)){
			curItem = this;
			GameScene.selectItem(armorSelector);
		} else if (action.equals(AC_INFO)) {
			GameScene.show(new WndUseItem(null, this));
		}
	}

	@Override
	public String name() {
		return glyph != null ? glyph.name( super.name() ) : super.name();
	}

	@Override
	public String info() {
		String info = super.info();
		if (glyph != null){
			info += "\n\n" + Messages.get(this, "inscribed", glyph.name());
			info += " " + glyph.desc();
		}
		return info;
	}
	
	@Override
	public int buffedVisiblyUpgraded() {
		return curseInfusionBonus ? buffedLvl()+1 : buffedLvl();
	}

	@Override
	//scroll of upgrade can be used directly once, same as upgrading armor the seal is affixed to then removing it.
	public boolean isUpgradable() {
		return level() == 0;
	}

	protected static WndBag.ItemSelector armorSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return  Messages.get(BrokenSeal.class, "prompt");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return Belongings.Backpack.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Armor;
		}

		@Override
		public void onSelect( Item item ) {
			BrokenSeal seal = (BrokenSeal) curItem;
			if (item instanceof Armor) {
				Armor arm = (Armor)item;

				if(Armor.runic == 1) {
					String armorGlyph;
					if(!arm.cursedKnown && (arm.glyph() == null || arm.glyph().curse())) {
						armorGlyph = Messages.get(Stylus.class, "unknown");
					}
					else if(arm.glyph() != null) {
						armorGlyph = arm.glyph().name();
					}
					else {
						armorGlyph = Messages.get(Stylus.class, "none");
					}
					String sealGlyph = seal.glyph() != null ? seal.glyph().name() : Messages.get(Stylus.class, "none");

					GameScene.show(new WndOptions(
							new ItemSprite(seal),
							Messages.get(BrokenSeal.class, "choose_title"),
							Messages.get(BrokenSeal.class, "choose_desc"),
							"Armor: " + armorGlyph,
							"Seal: " + sealGlyph) {

						@Override
						protected void onSelect(int index) {
							seal.glyphChosen = index == 1;
							
							arm.affixSeal(seal, true);
						}
					});
				}

				else {
					arm.affixSeal(seal, true);
				}
			}
		}
	};
	
	private static final String GLYPH 			= "glyph";
	// Armor handles itself
	
	private static final String GLYPH_CHOSEN 	= "glyph_chosen";
	private static final String CURSE_INFUSE 	= "curse_infuse";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(GLYPH, glyph);
		
		bundle.put(GLYPH_CHOSEN, glyphChosen);
		bundle.put(CURSE_INFUSE, curseInfusionBonus);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		inscribe((Armor.Glyph)bundle.get(GLYPH));
		
		glyphChosen = bundle.getBoolean(GLYPH_CHOSEN);
		curseInfusionBonus = bundle.getBoolean(CURSE_INFUSE);
	}

	public static class WarriorShield extends ShieldBuff {

		{
			type = buffType.POSITIVE;

			detachesAtZero = false;
			shieldUsePriority = 2;
		}

		private Armor armor;

		private int cooldown = 0;
		private int turnsSinceEnemies = 0;

		private static final int COOLDOWN_START = 120;

		@Override
		public int icon() {
			if (coolingDown() || shielding() > 0){
				return BuffIndicator.SEAL_SHIELD;
			} else {
				return BuffIndicator.NONE;
			}
		}

		@Override
		public void tintIcon(Image icon) {
			if (coolingDown() && shielding() == 0){
				icon.brightness(0.3f);
			} else {
				icon.resetColor();
			}
		}

		@Override
		public float iconFadePercent() {
			if (shielding() > 0){
				return GameMath.gate(0, 1f - shielding()/(float)maxShield(), 1);
			} else if (coolingDown()){
				return GameMath.gate(0, cooldown / (float)COOLDOWN_START, 1);
			} else {
				return 0;
			}
		}

		@Override
		public String iconTextDisplay() {
			if (shielding() > 0){
				return Integer.toString(shielding());
			} else if (coolingDown()){
				return Integer.toString(cooldown);
			} else {
				return "";
			}
		}

		@Override
		public String desc() {
			if (shielding() > 0){
				return Messages.get(this, "desc_active", shielding(), cooldown);
			} else {
				return Messages.get(this, "desc_cooldown", cooldown);
			}
		}

		private int cooldown = 0;
		private int turnsSinceEnemies = 0;

		private static int COOLDOWN_START = 150;

		@Override
		public int icon() {
			if (coolingDown() || shielding() > 0){
				return BuffIndicator.SEAL_SHIELD;
			} else {
				return BuffIndicator.NONE;
			}
		}

		@Override
		public void tintIcon(Image icon) {
			if (coolingDown() && shielding() == 0){
				icon.brightness(0.3f);
			} else {
				icon.resetColor();
			}
		}

		@Override
		public float iconFadePercent() {
			if (shielding() > 0){
				return GameMath.gate(0, 1f - shielding()/(float)maxShield(), 1);
			} else if (coolingDown()){
				return GameMath.gate(0, cooldown / (float)COOLDOWN_START, 1);
			} else {
				return 0;
			}
		}

		@Override
		public String iconTextDisplay() {
			if (shielding() > 0){
				return Integer.toString(shielding());
			} else if (coolingDown()){
				return Integer.toString(cooldown);
			} else {
				return "";
			}
		}

		@Override
		public String desc() {
			if (shielding() > 0){
				return Messages.get(this, "desc_active", shielding(), cooldown);
			} else {
				return Messages.get(this, "desc_cooldown", cooldown);
			}
		}

		@Override
		public synchronized boolean act() {
			if (cooldown > 0 && Regeneration.regenOn()){
				cooldown--;
			}

			if (shielding() > 0){
				if (Dungeon.hero.visibleEnemies() == 0 && Dungeon.hero.buff(Combo.class) == null){
					turnsSinceEnemies++;
					if (turnsSinceEnemies >= 5){
						if (cooldown > 0) {
							float percentLeft = shielding() / (float)maxShield();
							//max of 50% cooldown refund
							cooldown = Math.max(0, (int)(cooldown - COOLDOWN_START * (percentLeft / 2f)));
						}
						decShield(shielding());
					}
				} else {
					turnsSinceEnemies = 0;
				}
			}
			
			if (shielding() <= 0 && maxShield() <= 0 && cooldown == 0){
				detach();
			}
			
			spend(TICK);
			return true;
		}

		public synchronized void activate() {
			incShield(maxShield());
			cooldown = Math.max(0, cooldown+COOLDOWN_START);
			turnsSinceEnemies = 0;
		}

		public boolean coolingDown(){
			return cooldown > 0;
		}

		public void reduceCooldown(float percentage){
			cooldown -= Math.round(COOLDOWN_START*percentage);
			cooldown = Math.max(cooldown, -COOLDOWN_START);
		}

		public synchronized void setArmor(Armor arm){
			armor = arm;
		}

		public synchronized int maxShield() {
			//metamorphed iron will logic
			if (((Hero)target).heroClass != HeroClass.WARRIOR && ((Hero) target).hasTalent(Talent.IRON_WILL)){
				return ((Hero) target).pointsInTalent(Talent.IRON_WILL);
			}

			if (armor != null && armor.isEquipped((Hero)target) && armor.checkSeal() != null) {
				return armor.checkSeal().maxShield(armor.tier, armor.level());
			} else {
				return 0;
			}
		}
		
		public synchronized void berserkerRampage(float factor) {
			int shield = Math.round(factor * maxShield());
			incShield(shield);
			target.sprite.showStatusWithIcon( CharSprite.POSITIVE, Integer.toString(shield), FloatingText.SHIELDING );
			
			float percentLeft = shielding() / (float)maxShield();
			//max of 50% cooldown refund
			cooldown = (int)(COOLDOWN_START - COOLDOWN_START * (percentLeft / 2f));
			
			//make sure it doesn't prematurely end
			turnsSinceEnemies = -100;
		}

		public static final String COOLDOWN = "cooldown";
		public static final String TURNS_SINCE_ENEMIES = "turns_since_enemies";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(COOLDOWN, cooldown);
			bundle.put(TURNS_SINCE_ENEMIES, turnsSinceEnemies);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			if (bundle.contains(COOLDOWN)) {
				cooldown = bundle.getInt(COOLDOWN);
				turnsSinceEnemies = bundle.getInt(TURNS_SINCE_ENEMIES);

			//if we have shield from pre-3.1, have it last a bit
			} else if (shielding() > 0) {
				turnsSinceEnemies = -100;
			}
		}

		public static final String COOLDOWN = "cooldown";
		public static final String TURNS_SINCE_ENEMIES = "turns_since_enemies";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(COOLDOWN, cooldown);
			bundle.put(TURNS_SINCE_ENEMIES, turnsSinceEnemies);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			if (bundle.contains(COOLDOWN)) {
				cooldown = bundle.getInt(COOLDOWN);
				turnsSinceEnemies = bundle.getInt(TURNS_SINCE_ENEMIES);

			//if we have shield from pre-3.1, have it last a bit
			} else if (shielding() > 0) {
				turnsSinceEnemies = -100;
			}
		}
	}
}
