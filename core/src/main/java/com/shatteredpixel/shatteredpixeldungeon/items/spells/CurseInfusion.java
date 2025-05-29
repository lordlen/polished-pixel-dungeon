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

package com.shatteredpixel.shatteredpixeldungeon.items.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Stylus;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.MetalShard;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class CurseInfusion extends InventorySpell {
	
	{
		image = ItemSpriteSheet.CURSE_INFUSE;

		talentChance = 1/(float)Recipe.OUT_QUANTITY;
	}
	
	private void curseEnchant(Weapon w) {
		//if we are freshly applying curse infusion, don't replace an existing curse
		if (w.enchantment == null || !w.hasCurseEnchant() || w.curseInfusionBonus) {
			w.enchant(Weapon.Enchantment.randomCurse(w.enchantment != null ? w.enchantment.getClass() : null));
		}
		w.cursed = true;
		w.cursedKnown = true;
		w.curseInfusionBonus = true;
		
		if (w instanceof MagesStaff){
			((MagesStaff) w).updateWand(true);
		}
	}
	
	private void curseEnchant(Armor a) {
		//if we are freshly applying curse infusion, don't replace an existing curse
		if (a.glyph() == null || !a.hasCurseGlyph(false) || a.curseInfusionBonus) {
			a.inscribe(Armor.Glyph.randomCurse(a.armorGlyphClass(), a.sealGlyphClass()), true);
		}
		a.cursed = true;
		a.cursedKnown = true;
		a.curseInfusionBonus = true;
	}
	
	private void curseEnchant(BrokenSeal s) {
		//if we are freshly applying curse infusion, don't replace an existing curse
		if (s.glyph() == null || !s.hasCurseGlyph() || s.curseInfusionBonus) {
			s.inscribe(Armor.Glyph.randomCurse(s.armorGlyphClass(), s.sealGlyphClass()));
		}
		s.curseInfusionBonus = true;
	}
	
	@Override
	protected boolean usableOnItem(Item item) {
		return ((item instanceof EquipableItem && !(item instanceof MissileWeapon)) || item instanceof Wand || item instanceof BrokenSeal);
	}

	@Override
	protected void onItemSelected(Item item) {
		
		if (item instanceof MeleeWeapon || item instanceof SpiritBow) {
			curseEnchant( (Weapon) item );
		}
		else if (item instanceof Armor){
			Armor armor = (Armor)item;
			BrokenSeal seal = armor.checkSeal();
			
			if(seal == null) {
				curseEnchant(armor);
			}
			else if(!Armor.runic()) {
				curseEnchant(seal);
				armor.cursed = true;
				armor.cursedKnown = true;
			}
			else {
				GameScene.show(new WndOptions(
						new ItemSprite(CurseInfusion.this),
						Messages.titleCase(new CurseInfusion().name()),
						Messages.get(Stylus.class, "choose_desc"),
						"Armor: " + (armor.glyph() != null ? armor.glyph().name() : "none"),
						"Seal: " + (seal.glyph() != null ? seal.glyph().name() : "none")) {
					
					@Override
					protected void onSelect(int index) {
						if(index == 0) {
							curseEnchant(armor);
						}
						else {
							curseEnchant(seal);
						}
						
						CellEmitter.get(curUser.pos).burst(ShadowParticle.UP, 5);
						Sample.INSTANCE.play(Assets.Sounds.CURSED);
						
						onUse();
						Badges.validateItemLevelAquired(armor);
						updateQuickslot();
						
						super.onSelect(index);
					}
					
					@Override
					public void onBackPressed() {
						super.onBackPressed();
						GameScene.selectItem(itemSelector);
					}
				});
				
				return;
			}
		}
		else if (item instanceof BrokenSeal) {
			if (!Armor.runic()) {
				GLog.w(Messages.get(Stylus.class, "no_runic"));
				
				GameScene.selectItem(itemSelector);
				return;
			}
			else {
				curseEnchant( (BrokenSeal) item );
			}
		}
		else if (item instanceof Wand){
			item.cursed = true;
			((Wand) item).curseInfusionBonus = true;
			((Wand) item).updateLevel();
		}
		else if (item instanceof RingOfMight){
			item.cursed = true;
			curUser.updateHT(false);
		}
		
		CellEmitter.get(curUser.pos).burst(ShadowParticle.UP, 5);
		Sample.INSTANCE.play(Assets.Sounds.CURSED);
		
		onUse();
		Badges.validateItemLevelAquired(item);
		updateQuickslot();
	}
	
	@Override
	public int value() {
		return (int)(60 * (quantity/(float)Recipe.OUT_QUANTITY));
	}

	@Override
	public int energyVal() {
		return (int)(12 * (quantity/(float)Recipe.OUT_QUANTITY));
	}
	
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		private static final int OUT_QUANTITY = 4;
		
		{
			inputs =  new Class[]{ScrollOfRemoveCurse.class, MetalShard.class};
			inQuantity = new int[]{1, 1};
			
			cost = 6;
			
			output = CurseInfusion.class;
			outQuantity = OUT_QUANTITY;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Catalog.countUse(MetalShard.class);
			return super.brew(ingredients);
		}
	}
}
