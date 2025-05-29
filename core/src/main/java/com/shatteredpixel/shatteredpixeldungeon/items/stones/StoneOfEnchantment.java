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

package com.shatteredpixel.shatteredpixeldungeon.items.stones;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Enchanting;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Stylus;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfEnchantment;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;

public class StoneOfEnchantment extends InventoryStone {
	
	{
		preferredBag = Belongings.Backpack.class;
		image = ItemSpriteSheet.STONE_ENCHANT;

		unique = true;
	}

	@Override
	protected boolean usableOnItem(Item item) {
		return ScrollOfEnchantment.enchantable(item);
	}
	
	@Override
	protected void onItemSelected(Item item) {
		if (item instanceof Weapon) {
			( (Weapon) item ).enchant();
		}
		else if (item instanceof Armor) {
			Armor armor = (Armor)item;
			BrokenSeal seal = armor.checkSeal();
			
			if(seal == null) {
				armor.inscribe(true);
			}
			else if(!Armor.runic()) {
				seal.inscribe();
			}
			else {
				GameScene.show(new WndOptions(
						new ItemSprite(StoneOfEnchantment.this),
						Messages.titleCase(new StoneOfEnchantment().name()),
						Messages.get(Stylus.class, "choose_desc"),
						"Armor: " + (armor.glyph() != null ? armor.glyph().name() : "none"),
						"Seal: " + (seal.glyph() != null ? seal.glyph().name() : "none")) {
					
					@Override
					protected void onSelect(int index) {
						if (!anonymous) {
							curItem.detach(curUser.belongings.backpack);
							Catalog.countUse(StoneOfEnchantment.class);
							Talent.onRunestoneUsed(curUser, curUser.pos, StoneOfEnchantment.class);
						}
						
						if(index == 0) {
							armor.inscribe(true);
							doAnimation(armor);
						}
						else {
							seal.inscribe();
							doAnimation(seal);
						}
						
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
				( (BrokenSeal) item ).inscribe();
			}
		}
		
		if (!anonymous) {
			curItem.detach(curUser.belongings.backpack);
			Catalog.countUse(getClass());
			Talent.onRunestoneUsed(curUser, curUser.pos, getClass());
		}
		
		doAnimation(item);
		
	}
	
	private void doAnimation(Item item) {
		curUser.sprite.emitter().start( Speck.factory( Speck.LIGHT ), 0.1f, 5 );
		Enchanting.show( curUser, item );
		
		if (item instanceof Weapon) {
			GLog.p(Messages.get(this, "weapon"));
		} else if(item instanceof Armor) {
			GLog.p(Messages.get(this, "armor"));
		} else if(item instanceof BrokenSeal) {
			GLog.p(Messages.get(this, "seal"));
		}
		
		useAnimation();
	}
	
	@Override
	public int value() {
		return 30 * quantity;
	}

	@Override
	public int energyVal() {
		return 5 * quantity;
	}

}
