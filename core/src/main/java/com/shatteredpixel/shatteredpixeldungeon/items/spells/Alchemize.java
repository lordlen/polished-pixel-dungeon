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

package com.shatteredpixel.shatteredpixeldungeon.items.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Shopkeeper;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndEnergizeItem;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndInfoItem;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTradeItem;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndUpgrade;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Alchemize extends InventorySpell {
	
	{
		preferredBag = Belongings.Backpack.class;
		image = ItemSpriteSheet.ALCHEMIZE;

		talentChance = 1/(float)Recipe.OUT_QUANTITY;
	}

	private static WndBag parentWnd;
	
	@Override
	public int value() {
		return (int)(12 * (quantity/(float)Recipe.OUT_QUANTITY));
	}

	@Override
	public int energyVal() {
		return (int)(3 * (quantity/(float)Recipe.OUT_QUANTITY));
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

		private static final int OUT_QUANTITY = 12;

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 1) return false;
            else return ingredients.get(0) instanceof Plant.Seed || ingredients.get(0) instanceof Runestone;
        }

		@Override
		public int cost(ArrayList<Item> ingredients) {
			if (ingredients.size() != 1) return 0;
			return ingredients.get(0) instanceof Plant.Seed ? 1 : 0;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			ingredients.get(0).quantity(ingredients.get(0).quantity()-1);
			return sampleOutput(null);
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new Alchemize().quantity(OUT_QUANTITY);
		}
	}
	
	@Override
	protected void onItemSelected(Item item) {
		if (parentWnd != null) {
			parentWnd = GameScene.selectItem(itemSelector);
		}
		GameScene.show( new WndAlchemizeItem( item ) );
	}
	
	@Override
	protected boolean usableOnItem(Item item) {
		return Shopkeeper.canSell(item) || item.energyVal() > 0;
	}
	
	@Override
	public void onUse(){
		super.onUse();
		curUser.spend(-1f);
		
		if(Dungeon.hero.belongings.contains(curItem)) {
			GameScene.selectItem(itemSelector);
		}
		
		if (parentWnd != null) {
			parentWnd.hide();
		}
	}
	
	public class WndAlchemizeItem extends WndInfoItem {

		private static final float GAP		= 2;
		private static final int BTN_HEIGHT	= 18;

		public WndAlchemizeItem(Item item) {
			super(item);

			float pos = height;

			if (Shopkeeper.canSell(item)) {
				if (item.quantity() == 1 || (item instanceof MissileWeapon && item.isUpgradable())) {

					if (item instanceof MissileWeapon && ((MissileWeapon) item).extraThrownLeft){
						RenderedTextBlock warn = PixelScene.renderTextBlock(Messages.get(WndUpgrade.class, "thrown_dust"), 6);
						warn.hardlight(CharSprite.WARNING);
						warn.maxWidth(this.width);
						warn.setPos(0, pos + GAP);
						add(warn);
						pos = warn.bottom();
					}

					RedButton btnSell = new RedButton(Messages.get(this, "sell", item.value())) {
						@Override
						protected void onClick() {
							WndTradeItem.sell(item);
							hide();
							onUse();
						}
					};
					btnSell.setRect(0, pos + GAP, width, BTN_HEIGHT);
					btnSell.icon(new ItemSprite(ItemSpriteSheet.GOLD));
					add(btnSell);

					pos = btnSell.bottom();

				} else {

					int priceAll = item.value();
					RedButton btnSell1 = new RedButton(Messages.get(this, "sell_1", priceAll / item.quantity())) {
						@Override
						protected void onClick() {
							WndTradeItem.sellOne(item);
							hide();
							onUse();
						}
					};
					btnSell1.setRect(0, pos + GAP, width, BTN_HEIGHT);
					btnSell1.icon(new ItemSprite(ItemSpriteSheet.GOLD));
					add(btnSell1);
					RedButton btnSellAll = new RedButton(Messages.get(this, "sell_all", priceAll)) {
						@Override
						protected void onClick() {
							WndTradeItem.sell(item);
							hide();
							onUse();
						}
					};
					btnSellAll.setRect(0, btnSell1.bottom() + 1, width, BTN_HEIGHT);
					btnSellAll.icon(new ItemSprite(ItemSpriteSheet.GOLD));
					add(btnSellAll);

					pos = btnSellAll.bottom();

				}
			}

			if (item.energyVal() > 0) {
				if (item.quantity() == 1) {

					RedButton btnEnergize = new RedButton(Messages.get(this, "energize", item.energyVal())) {
						@Override
						protected void onClick() {
							WndEnergizeItem.energizeAll(item);
							hide();
							onUse();
						}
					};
					btnEnergize.setRect(0, pos + GAP, width, BTN_HEIGHT);
					btnEnergize.icon(new ItemSprite(ItemSpriteSheet.ENERGY));
					add(btnEnergize);

					pos = btnEnergize.bottom();

				} else {

					int energyAll = item.energyVal();
					RedButton btnEnergize1 = new RedButton(Messages.get(this, "energize_1", energyAll / item.quantity())) {
						@Override
						protected void onClick() {
							WndEnergizeItem.energizeOne(item);
							hide();
							onUse();
						}
					};
					btnEnergize1.setRect(0, pos + GAP, width, BTN_HEIGHT);
					btnEnergize1.icon(new ItemSprite(ItemSpriteSheet.ENERGY));
					add(btnEnergize1);
					RedButton btnEnergizeAll = new RedButton(Messages.get(this, "energize_all", energyAll)) {
						@Override
						protected void onClick() {
							WndEnergizeItem.energizeAll(item);
							hide();
							onUse();
						}
					};
					btnEnergizeAll.setRect(0, btnEnergize1.bottom() + 1, width, BTN_HEIGHT);
					btnEnergizeAll.icon(new ItemSprite(ItemSpriteSheet.ENERGY));
					add(btnEnergizeAll);

					pos = btnEnergizeAll.bottom();

				}
			}

			resize( width, (int)pos );

		}
		
		@Override
		public void onBackPressed() {
			super.onBackPressed();
			GameScene.selectItem(itemSelector);
		}
	}
}
