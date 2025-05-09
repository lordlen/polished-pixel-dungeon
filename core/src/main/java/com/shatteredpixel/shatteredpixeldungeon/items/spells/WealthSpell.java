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

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.WealthDrop;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;

public class WealthSpell extends Spell implements WealthDrop<Spell, WealthSpell> {

	{
		image = ItemSpriteSheet.SPELL_HOLDER;
	}

	@Override
	public void onCast(Hero hero) {
		Callback callback = new Callback() {
			@Override
			public void call() {
				detach(curUser.belongings.backpack);
			}
		};

		spell.anonymize();
		spell.onDetach = callback;
		spell.onCast(hero);
	}

	@Override
	public void updateStats() {
		//talents actually dont work at all, since the spell is anonymized, might change in the future though.
		talentFactor = spell.talentFactor * 0.5f;
		talentChance = spell.talentChance;
	}

	@Override
	protected void onThrow(int cell) {
		return;
	}


	private Spell spell = null;
	@Override
	public Spell item() {
		return spell;
	}
	@Override
	public void setItem(Spell item) {
		this.spell = item;
	}

	@Override
	public Item identify( boolean byHero ) {
		return spell.identify(byHero);
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

	private static final String SPELL = "spell";
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		if(spell != null) bundle.put( SPELL, spell.getClass() );
	}
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		set(bundle.getClass( SPELL ));
	}
}
