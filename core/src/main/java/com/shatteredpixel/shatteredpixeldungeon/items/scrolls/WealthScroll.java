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

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.WealthDrop;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Bundle;

public class WealthScroll extends Scroll implements WealthDrop<Scroll, WealthScroll> {

	{
		image = ItemSpriteSheet.MYSTERY_SCROLL;
	}

	@Override
	public void doRead() {
		scroll.anonymize();
		scroll.doRead();
	}

	@Override
	public void updateStats() {
		//talents actually dont work at all, since the scroll is anonymized, might change in the future though.
		talentFactor = scroll.talentFactor * 0.5f;
		talentChance = scroll.talentChance;
	}

	@Override
	protected void onThrow(int cell) {
		vanishVFX(cell);
	}


	private Scroll scroll = null;
	@Override
	public Scroll item() {
		return scroll;
	}
	@Override
	public void setItem(Scroll item) {
		this.scroll = item;
	}

	@Override
	public Item identify( boolean byHero ) {
		return scroll.identify(byHero);
	}
	@Override
	public boolean isKnown() {
		return valid() && scroll.isKnown();
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

	private static final String SCROLL = "scroll";
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		if(scroll != null) bundle.put( SCROLL, scroll.getClass() );
	}
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		set(bundle.getClass( SCROLL ));
	}
}
