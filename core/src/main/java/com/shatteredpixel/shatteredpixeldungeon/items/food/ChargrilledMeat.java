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

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class ChargrilledMeat extends Food {

	{
		image = ItemSpriteSheet.STEAK;
		energy = Hunger.HUNGRY/1.25f;
	}
	
	@Override
	public int value() {
		return 15 * quantity;
	}
	
	public static Food cook( int quantity ) {
		ChargrilledMeat result = new ChargrilledMeat();
		result.quantity = quantity;
		return result;
	}

	public static class oneMeat extends Recipe.SimpleRecipe{
		{
			inputs =  new Class[]{MysteryMeat.class};
			inQuantity = new int[]{1};

			cost = 1;

			output = ChargrilledMeat.class;
			outQuantity = 1;
		}
	}

	public static class twoMeat extends Recipe.SimpleRecipe{
		{
			inputs =  new Class[]{MysteryMeat.class};
			inQuantity = new int[]{2};

			cost = 2;

			output = ChargrilledMeat.class;
			outQuantity = 2;
		}
	}

	public static class threeMeat extends Recipe.SimpleRecipe{
		{
			inputs =  new Class[]{MysteryMeat.class};
			inQuantity = new int[]{3};

			cost = 2;

			output = ChargrilledMeat.class;
			outQuantity = 3;
		}
	}
}
