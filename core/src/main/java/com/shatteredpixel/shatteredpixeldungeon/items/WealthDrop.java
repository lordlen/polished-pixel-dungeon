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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.WealthPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfWealth;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Reflection;

public interface WealthDrop<T extends Item> {
    T item();
    void setItem(T item);
    
    //we cache this for performance
    Decay decay();
    void setDecay(Decay decay);

    default void set(Class<? extends T> type) {
        setItem(Reflection.newInstance(type));
        item().Polished_wealthDrop = this;
        
        updateStats();
        updateVisuals();
    }

    default boolean valid() {
        return this instanceof Item && item() != null;
    }

    default Item th() {
        return (Item)this;
    }

    default boolean matches(Item item) {
        return
                valid()
                && item.getClass() == getClass()
                && ((WealthDrop<?>) item).valid()
                && ((WealthDrop<?>) item).item().isSimilar(item())
                ;
    }

    default void updateVisuals() {
        if(!valid()) return;

        boolean update = th().image != item().image || th().icon != item().icon;

        th().icon = item().icon;
        if(item().isIdentified()) {
            th().image = item().image;
        }

        if(update) Item.updateQuickslot();
    }

    void updateStats();

    default boolean onPickUp(boolean picked) {
        if(picked) Dungeon.hero.spend(-Item.TIME_TO_PICK_UP);
        return picked;
    }
    default boolean afterCollect(boolean collected) {
        if(!collected) return false;

        if(!Dungeon.Polished.loading && Dungeon.hero != null && this instanceof Item) {
            setDecay(Buff.append(Dungeon.hero, Decay.class, decayTimer()));
            decay().item = th();
            decay().max = (int)decay().cooldown();
        }
        return true;
    }
    default void afterDetach() {
        if(decay() != null) decay().detach();
    }

    default void onDrop(Hero hero) {
        if(valid()) {
            hero.spendAndNext(Item.TIME_TO_DROP);
            th().detachAll(hero.belongings.backpack);
            vanishVFX(hero.pos);
        }
    }

    default void wealthDetach(Bag container) {
        if(valid() && item().quantity() <= 1) {
            th().detach(container);
        }
    }

    default String dropName() {
        return valid() ?
                item().trueName() + Messages.get(WealthDrop.class, "suffix") :
                Messages.get(WealthDrop.class, "name_default");
    }

    default String dropDesc() {
        String desc = valid() && item().isIdentified() ? item().desc() : Messages.get(this, "desc");
        if(decay() != null) desc += "\n\n" + Messages.get(WealthDrop.class, "decay", decay().iconTextDisplay());

        return desc;
    }

    default String dropExtra() {
        return decay() != null ? decay().iconTextDisplay() : null;
    }

    default void dropColor(BitmapText text) {
        if(decay() != null) {
            float percent = decay().cooldown() / decay().max;
            text.hardlight(1f, percent, percent);
        }
    }


    String WEALTH_ITEM = "wealth_item";

    static void vanishVFX(int cell) {
        Sample.INSTANCE.play(Assets.Sounds.PUFF);
        CellEmitter.get( cell ).burst( Speck.factory( Speck.STEAM ), 10 );
    }

    static int decayTimer() {
        int lvl = Math.max(0, Ring.getBuffedBonus(Dungeon.hero, RingOfWealth.Wealth.class) - 1);
        return 200 + 50*lvl;
    }

    static void onId() {
        if(Dungeon.level == null || Dungeon.hero == null) return;

        for(Item item : Dungeon.hero.belongings) {
            if(item instanceof WealthDrop<?>) {
                ((WealthDrop<?>) item).updateVisuals();
            }
        }

        for(Heap heap : Dungeon.level.heaps.valueList()) {
            for(Item item : heap.items) {
                if(item instanceof WealthDrop<?>) {
                    ((WealthDrop<?>) item).updateVisuals();
                }
            }
        }
    }

    static void backgroundColoring(ColorBlock bg) {
        int r = 13;
        int g = -50;
        int b = 200;
        float light = 4.75f;

        bg.ra = r * light / 255f;
        bg.ga = g * light / 255f;
        bg.ba = b * light / 255f;
    }

    static void refreshIndicators() {
        if(Dungeon.hero.buff(Decay.class) != null) {
            Item.updateQuickslot();
        }
        
        //disabled for now
        /*for (Decay decay : Dungeon.hero.buffs(Decay.class)) {
            if (decay.cooldown() <= 25 && decay.warning && decay.item != null) {
                decay.warning = false;
                GLog.w(Messages.get(WealthDrop.class, "warning", decay.item.name()));
            }
            Item.updateQuickslot();
        }*/
    }

    class Decay extends FlavourBuff {
        
        {
            actPriority = HERO_PRIO+1;
            revivePersists = true;
        }

        //unused for now
        boolean warning = true;

        Item item = null;
        public int max = 200;

        @Override
        public boolean act() {
            if(item == null) return super.act();
            
            int slot = Dungeon.quickslot.getSlot(item);
            item.detach(Dungeon.hero.belongings.backpack);
            vanishVFX(Dungeon.hero.pos);

            if(Dungeon.hero.belongings.contains(item)) {
                spend(decayTimer());
                return true;
            }
            else {
                if(slot != -1) Dungeon.quickslot.clearSlot(slot);
                return super.act();
            }
        }

        private static final String ITEM    = "item";
        private static final String MAX     = "max";

        @Override
        public void storeInBundle( Bundle bundle ) {
            super.storeInBundle( bundle );
            if(item != null) bundle.put( ITEM, item );
            bundle.put( MAX, max );
        }

        @Override
        public void restoreFromBundle( Bundle bundle ) {
            super.restoreFromBundle( bundle );
            max = bundle.getInt( MAX );
            
            if(bundle.get( ITEM ) != null) {
                Dungeon.Polished.runAfterLoad(() -> {
                    
                    for(Item i : Dungeon.hero.belongings) {
                        if(i.isSimilar( (Item) bundle.get( ITEM ) )) {
                            item = i;
                            ((WealthDrop<?>) item).setDecay(this);
                            break;
                        }
                    }
                    
                });
            }
        }
    }
}
