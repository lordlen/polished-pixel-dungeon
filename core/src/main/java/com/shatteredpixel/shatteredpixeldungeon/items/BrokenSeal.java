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

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Regeneration;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ShieldBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndUseItem;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

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
	

	public Armor armor;
	private Armor.Glyph glyph;
	public boolean glyphChosen = false;

	public boolean curseInfusionBonus = false;
	
	public boolean overwriteGlyph() {
		int points = Dungeon.hero.pointsInTalent(Talent.RUNIC_TRANSFERENCE);
		
		if(points == 0) {
			return glyph != null;
		}
		else if(points == 1)
			return glyphChosen;
		else
			return false;
	}

	public BrokenSeal inscribe( ) {
		Class<? extends Armor.Glyph> oldGlyphClass = glyph != null ? glyph.getClass() : null;
		Class<? extends Armor.Glyph> oldArmorGlyphClass = armor != null && armor.glyph() != null ? armor.glyph().getClass() : null;
		Armor.Glyph gl = Armor.Glyph.random( oldGlyphClass, oldArmorGlyphClass );

		return inscribe( gl );
	}

	public BrokenSeal inscribe( Armor.Glyph glyph ) {
		if (glyph == null || !glyph.curse()) curseInfusionBonus = false;
		this.glyph = glyph;

		//so once we upgrade the talent, this gets chosen by default
		if(Dungeon.hero != null && !Dungeon.hero.hasTalent(Talent.RUNIC_TRANSFERENCE)) glyphChosen = true;
		if(glyph != null && glyph.curse()) glyphChosen = true;

		if (glyph != null) {
			Catalog.setSeen(glyph.getClass());
			Statistics.itemTypesDiscovered.add(glyph.getClass());
		}
		return this;
	}

	public Armor.Glyph glyph(){
		return glyph;
	}

	public int maxShield( int armTier, int armLvl ){
		return armTier + armLvl + Dungeon.hero.pointsInTalent(Talent.IRON_WILL);
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
				Armor armor = (Armor)item;

				if(Dungeon.hero.pointsInTalent(Talent.RUNIC_TRANSFERENCE) == 1) {

					GameScene.show(new WndOptions(
							new ItemSprite(seal),
							Messages.get(BrokenSeal.class, "choose_title"),
							Messages.get(BrokenSeal.class, "choose_desc"),
							"Armor: " + (armor.glyph() != null ? armor.glyph().name() : "none"),
							"Seal: " + (seal.glyph() != null ? seal.glyph().name() : "none")) {

						@Override
						protected void onSelect(int index) {
							seal.glyphChosen = index == 1;

							GLog.p(Messages.get(BrokenSeal.class, "affix"));
							Dungeon.hero.sprite.operate(Dungeon.hero.pos);
							Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
							armor.affixSeal(seal);
							seal.detach(Dungeon.hero.belongings.backpack);
						}
					});
				}

				else {
					GLog.p(Messages.get(BrokenSeal.class, "affix"));
					Dungeon.hero.sprite.operate(Dungeon.hero.pos);
					Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
					armor.affixSeal(seal);
					seal.detach(Dungeon.hero.belongings.backpack);
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
			detachesAtZero = false;
		}

		private Armor armor;
		private float partialShield;

		@Override
		public synchronized boolean act() {
			if (Regeneration.regenOn() && shielding() < maxShield()) {
				partialShield += 1/30f;
			}
			
			while (partialShield >= 1){
				incShield();
				partialShield--;
			}
			
			if (shielding() <= 0 && maxShield() <= 0){
				detach();
			}
			
			spend(TICK);
			return true;
		}
		
		public synchronized void supercharge(int maxShield){
			if (maxShield > shielding()){
				setShield(maxShield);
			}
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
	}
}
