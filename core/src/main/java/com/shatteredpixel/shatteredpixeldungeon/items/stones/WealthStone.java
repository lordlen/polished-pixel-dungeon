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
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.WealthDrop;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class WealthStone extends Runestone implements WealthDrop<Runestone, WealthStone> {

	{
		image = ItemSpriteSheet.STONE_HOLDER;
	}

	@Override
	protected void activate(int cell) {
		stone.anonymous = true;
		stone.activate(cell);
		stone.anonymous = false;
	}

	@Override
	public void updateStats() {
		//stone.talentFactor *= 0.5f;

		//talentFactor = stone.talentFactor;
		//talentChance = stone.talentChance;
	}

	@Override
	public String defaultAction() {
		return stone.defaultAction();
	}
	@Override
	public ArrayList<String> actions(Hero hero) {
		return stone.actions(hero);
	}
	public String actionName(String action, Hero hero){
		return stone.actionName(action, hero);
	}
	@Override
	public void execute(Hero hero, String action) {
		super.execute(hero, action);
		if (action.equals(InventoryStone.AC_USE) && hero.buff(MagicImmune.class) == null){
			activate(curUser.pos);
		}
	}

	@Override
	protected void onThrow(int cell) {
		if (stone instanceof InventoryStone ||
			Dungeon.hero.buff(MagicImmune.class) != null ||
			(Dungeon.level.pit[cell] && Actor.findChar(cell) == null)) {

			WealthDrop.vanishVFX(cell);
			return;
		}
		super.onThrow(cell);
	}


	private Runestone stone = null;
	@Override
	public Runestone item() {
		return stone;
	}
	@Override
	public void setItem(Runestone item) {
		this.stone = item;
	}

	@Override
	public Item identify( boolean byHero ) {
		if(valid()) stone.identify(byHero);
		return this;
	}
	@Override
	public boolean isSimilar(Item item) {
		return matches(item);
	}

	@Override
	public boolean doPickUp(Hero hero, int pos) {
		return onPickUp(super.doPickUp(hero, pos));
	}
	@Override
	public boolean collect(Bag container) {
		return afterCollect(super.collect(container));
	}
	@Override
	public void doDrop( Hero hero ) {
		onDrop(hero);
	}
	@Override
	protected void onDetach() {
		super.onDetach();
		afterDetach();
	}

	@Override
	public int value() {
		return 0;
	}
	@Override
	public int energyVal() {
		return 0;
	}

	@Override
	public String name() {
		return dropName();
	}
	@Override
	public String desc() {
		return dropDesc();
	}

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		if(stone != null) bundle.put( WEALTH_ITEM, stone.getClass() );
	}
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		set(bundle.getClass( WEALTH_ITEM ));
	}
}
