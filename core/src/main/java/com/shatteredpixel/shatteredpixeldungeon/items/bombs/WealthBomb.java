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

package com.shatteredpixel.shatteredpixeldungeon.items.bombs;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.WealthDrop;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Bundle;
import com.watabou.utils.Reflection;

public class WealthBomb extends Bomb implements WealthDrop<Bomb> {
    
    {
        image = ItemSpriteSheet.BOMB_HOLDER;
    }
    
    @Override
    public void updateStats() {
        //nothing to update
    }
    
    @Override
    protected void onThrow(int cell) {
        if(!lightingFuse) {
            WealthDrop.vanishVFX(cell);
        }
        else {
            bomb.rowDrop = true;
            bomb.onThrow(cell);
        }
    }
    
    private Bomb bomb = null;
    @Override
    public Bomb item() {
        return bomb;
    }
    @Override
    public void setItem(Bomb item) {
        this.bomb = item;
    }
    
    Decay decay = null;
    @Override
    public Decay decay() {
        return decay;
    }
    public void setDecay(Decay decay) {
        this.decay = decay;
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
        if(bomb != null) bundle.put( WEALTH_ITEM, bomb.getClass() );
    }
    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle(bundle);
        set(bundle.getClass( WEALTH_ITEM ));
    }
    
}
