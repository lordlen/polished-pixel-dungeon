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

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Enchanting;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Stylus;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.InventoryScroll;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfEnchantment;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTitledMessage;
import com.watabou.noosa.audio.Sample;

public class ScrollOfEnchantment extends ExoticScroll {
	
	{
		icon = ItemSpriteSheet.Icons.SCROLL_ENCHANT;

		unique = true;

		talentFactor = 2f;
	}

	protected static boolean identifiedByUse = false;
	
	@Override
	public void doRead() {
		if (!isKnown()) {
			identify();
			curItem = detach(curUser.belongings.backpack);
			identifiedByUse = true;
		} else {
			identifiedByUse = false;
		}
		GameScene.selectItem( itemSelector );
	}
	
	public void enchant(Weapon weapon) {
		final Weapon.Enchantment enchants[] = new Weapon.Enchantment[3];
		
		Class<? extends Weapon.Enchantment> existing = weapon.enchantment != null ? weapon.enchantment.getClass() : null;
		enchants[0] = Weapon.Enchantment.randomCommon( existing );
		enchants[1] = Weapon.Enchantment.randomUncommon( existing );
		enchants[2] = Weapon.Enchantment.random( existing, enchants[0].getClass(), enchants[1].getClass());
		
		detachOnUse();
		GameScene.show(new WndEnchantSelect(weapon, enchants[0], enchants[1], enchants[2]));
	}
	
	public void enchant(Armor armor) {
		final Armor.Glyph glyphs[] = new Armor.Glyph[3];
		
		glyphs[0] = Armor.Glyph.randomCommon( armor.armorGlyphClass(), armor.sealGlyphClass() );
		glyphs[1] = Armor.Glyph.randomUncommon( armor.armorGlyphClass(), armor.sealGlyphClass() );
		glyphs[2] = Armor.Glyph.random( armor.armorGlyphClass(), armor.sealGlyphClass(), glyphs[0].getClass(), glyphs[1].getClass());
		
		detachOnUse();
		GameScene.show(new WndGlyphSelect(armor, null, glyphs[0], glyphs[1], glyphs[2]));
	}
	
	public void enchant(BrokenSeal seal) {
		final Armor.Glyph glyphs[] = new Armor.Glyph[3];
		
		glyphs[0] = Armor.Glyph.randomCommon( seal.armorGlyphClass(), seal.sealGlyphClass() );
		glyphs[1] = Armor.Glyph.randomUncommon( seal.armorGlyphClass(), seal.sealGlyphClass() );
		glyphs[2] = Armor.Glyph.random( seal.armorGlyphClass(), seal.sealGlyphClass(), glyphs[0].getClass(), glyphs[1].getClass());
		
		detachOnUse();
		GameScene.show(new WndGlyphSelect(null, seal, glyphs[0], glyphs[1], glyphs[2]));
	}

	public static boolean enchantable( Item item ){
		return (item instanceof MeleeWeapon || item instanceof SpiritBow || item instanceof Armor || item instanceof BrokenSeal);
	}

	private void confirmCancelation() {
		GameScene.show( new WndOptions(new ItemSprite(this),
				Messages.titleCase(name()),
				Messages.get(InventoryScroll.class, "warning"),
				Messages.get(InventoryScroll.class, "yes"),
				Messages.get(InventoryScroll.class, "no") ) {
			@Override
			protected void onSelect( int index ) {
				switch (index) {
					case 0:
						curUser.spendAndNext( TIME_TO_READ );
						identifiedByUse = false;
						break;
					case 1:
						GameScene.selectItem(itemSelector);
						break;
				}
			}
			public void onBackPressed() {}
		} );
	}
	
	private void detachOnUse() {
		if (!identifiedByUse) {
			curItem.detach(curUser.belongings.backpack);
		}
		identifiedByUse = false;
	}
	
	protected WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(ScrollOfEnchantment.class, "inv_title");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return Belongings.Backpack.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return enchantable(item);
		}
		
		@Override
		public void onSelect(final Item item) {
			
			if (item instanceof Weapon){
				
				enchant( (Weapon) item );
			
			} else if (item instanceof Armor) {
				Armor armor = (Armor)item;
				BrokenSeal seal = armor.checkSeal();
				
				if(seal == null) {
					enchant(armor);
				}
				else if(Armor.runic == 0) {
					enchant(seal);
				}
				else {
					String armorGlyph;
					if(!armor.cursedKnown && (armor.glyph() == null || armor.hasCurseGlyph())) {
						armorGlyph = Messages.get(Stylus.class, "unknown");
					}
					else if(armor.glyph() != null) {
						armorGlyph = armor.glyph().name();
					}
					else {
						armorGlyph = Messages.get(Stylus.class, "none");
					}
					String sealGlyph = seal.glyph() != null ? seal.glyph().name() : Messages.get(Stylus.class, "none");
					
					GameScene.show(new WndOptions(
							new ItemSprite(ScrollOfEnchantment.this),
							Messages.titleCase(new ScrollOfEnchantment().name()),
							Messages.get(Stylus.class, "choose_desc"),
							"Armor: " + armorGlyph,
							"Seal: " + sealGlyph) {
						
						@Override
						protected void onSelect(int index) {
							if(index == 0) 	enchant(armor);
							else 			enchant(seal);
							
							super.onSelect(index);
						}
						
						@Override
						public void onBackPressed() {
							super.onBackPressed();
							GameScene.selectItem(itemSelector);
						}
					});
				}
			} else if (item instanceof BrokenSeal) {
				if (Armor.runic == 0) {
					GLog.w(Messages.get(Stylus.class, "no_runic"));
					GameScene.selectItem(itemSelector);
				}
				else {
					enchant( (BrokenSeal) item );
				}
			} else if (identifiedByUse){
				((ScrollOfEnchantment)curItem).confirmCancelation();
			}
		}
	};

	public static class WndEnchantSelect extends WndOptions {

		private static Weapon wep;
		private static Weapon.Enchantment[] enchantments;

		//used in PixelScene.restoreWindows
		public WndEnchantSelect(){
			this(wep, enchantments[0], enchantments[1], enchantments[2]);
		}

		public WndEnchantSelect(Weapon wep, Weapon.Enchantment ench1,
		                           Weapon.Enchantment ench2, Weapon.Enchantment ench3){
			super(new ItemSprite(new ScrollOfEnchantment()),
					Messages.titleCase(new ScrollOfEnchantment().name()),
					Messages.get(ScrollOfEnchantment.class, "weapon"),
					ench1.name(),
					ench2.name(),
					ench3.name(),
					Messages.get(ScrollOfEnchantment.class, "cancel"));
			this.wep = wep;
			enchantments = new Weapon.Enchantment[3];
			enchantments[0] = ench1;
			enchantments[1] = ench2;
			enchantments[2] = ench3;

			WndGlyphSelect.arm = null;
			WndGlyphSelect.seal = null;
		}

		@Override
		protected void onSelect(int index) {
			if (index < 3) {
				wep.enchant(enchantments[index]);
				GLog.p(Messages.get(StoneOfEnchantment.class, "weapon"));
				((ScrollOfEnchantment)curItem).readAnimation();

				Sample.INSTANCE.play( Assets.Sounds.READ );
				Enchanting.show(curUser, wep);
			} else {
				GameScene.show(new WndConfirmCancel());
			}
		}

		@Override
		protected boolean hasInfo(int index) {
			return index < 3;
		}

		@Override
		protected void onInfo( int index ) {
			GameScene.show(new WndTitledMessage(
					Icons.get(Icons.INFO),
					Messages.titleCase(enchantments[index].name()),
					enchantments[index].desc()));
		}

		@Override
		public void onBackPressed() {
			//do nothing, reader has to cancel
		}

	}

	public static class WndGlyphSelect extends WndOptions {

		private static Armor arm;
		private static BrokenSeal seal;
		private static Armor.Glyph[] glyphs;

		//used in PixelScene.restoreWindows
		public WndGlyphSelect() {
			this(arm, seal, glyphs[0], glyphs[1], glyphs[2]);
		}

		public WndGlyphSelect(Armor arm, BrokenSeal seal, Armor.Glyph glyph1, Armor.Glyph glyph2, Armor.Glyph glyph3) {
			super(new ItemSprite(new ScrollOfEnchantment()),
					Messages.titleCase(new ScrollOfEnchantment().name()),
					Messages.get(ScrollOfEnchantment.class, arm != null ? "armor" : "seal"),
					glyph1.name(),
					glyph2.name(),
					glyph3.name(),
					Messages.get(ScrollOfEnchantment.class, "cancel"));
			
			this.arm = arm;
			this.seal = seal;
			glyphs = new Armor.Glyph[3];
			glyphs[0] = glyph1;
			glyphs[1] = glyph2;
			glyphs[2] = glyph3;

			WndEnchantSelect.wep = null;
		}

		@Override
		protected void onSelect(int index) {
			if (index < 3) {
				if(arm != null) {
					arm.inscribe(glyphs[index]);
					GLog.p(Messages.get(StoneOfEnchantment.class, "armor"));
					Enchanting.show(curUser, arm);
				}
				else if(seal != null) {
					seal.inscribe(glyphs[index]);
					GLog.p(Messages.get(StoneOfEnchantment.class, "seal"));
					Enchanting.show(curUser, seal);
				}
				
				((ScrollOfEnchantment) curItem).readAnimation();
				Sample.INSTANCE.play(Assets.Sounds.READ);
				updateQuickslot();
			} else {
				GameScene.show(new WndConfirmCancel());
			}
		}

		@Override
		protected boolean hasInfo(int index) {
			return index < 3;
		}

		@Override
		protected void onInfo(int index) {
			GameScene.show(new WndTitledMessage(
					Icons.get(Icons.INFO),
					Messages.titleCase(glyphs[index].name()),
					glyphs[index].desc()));
		}

		@Override
		public void onBackPressed() {
			//do nothing, reader has to cancel
		}

	}

	public static class WndConfirmCancel extends WndOptions{

		public WndConfirmCancel(){
			super(new ItemSprite(new ScrollOfEnchantment()),
					Messages.titleCase(new ScrollOfEnchantment().name()),
					Messages.get(ScrollOfEnchantment.class, "cancel_warn"),
					Messages.get(ScrollOfEnchantment.class, "cancel_warn_yes"),
					Messages.get(ScrollOfEnchantment.class, "cancel_warn_no"));
		}

		@Override
		protected void onSelect(int index) {
			super.onSelect(index);
			if (index == 1){
				if (WndEnchantSelect.wep != null) {
					GameScene.show(new WndEnchantSelect());
				} else {
					GameScene.show(new WndGlyphSelect());
				}
			} else {
				WndEnchantSelect.wep = null;
				WndEnchantSelect.enchantments = null;
				WndGlyphSelect.arm = null;
				WndGlyphSelect.glyphs = null;
			}
		}

		@Override
		public void onBackPressed() {
			//do nothing
		}
	}
}
