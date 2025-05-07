package com.shatteredpixel.shatteredpixeldungeon.items.potions;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.watabou.noosa.ColorBlock;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Reflection;

public interface WealthDrop<T extends Item, C extends WealthDrop<T, C>> {
    T item();
    void setItem(T item);

    default C set(Class<? extends T> type) {
        setItem(Reflection.newInstance(type));
        if(!valid()) return null;

        updateStats();
        updateVisuals();

        return (C)this;
    }

    default boolean valid() {
        return this instanceof Item && item() != null;
    }

    default boolean matches(Item item) {
        return
                valid()
                && item.getClass() == getClass()
                && ((WealthDrop<?, ?>) item).valid()
                && ((WealthDrop<?, ?>) item).item().isSimilar(item())
                ;
    }

    default void updateVisuals() {
        if(!valid()) return;

        if(item().isIdentified()) {
            boolean update = ((Item)this).image != item().image;

            ((Item)this).image = item().image;
            ((Item)this).icon = item().icon;

            if(update) Item.updateQuickslot();
        }
    }

    void updateStats();

    default void afterCollect() {
        if(Dungeon.hero != null) {
            if(valid() && Dungeon.hero.belongings.contains((Item)this)) {
                Buff.append(Dungeon.hero, Decay.class, 100f).item = (Item)this;
            }
        } else {
//            Callback callback = new Callback() {
//                @Override
//                public void call() {
//                    if(valid() && Dungeon.hero.belongings.contains((Item)WealthDrop.this)) {
//                        Buff.append(Dungeon.hero, Decay.class, 100f).item = (Item)WealthDrop.this;
//                    }
//                }
//            };
//            Dungeon.runAfterLoad(callback);
        }
    }
    default void afterDetach() {
        if(decay() != null) decay().detach();
    }

    default void afterThrow(int cell) {
        Heap heap = Dungeon.level.heaps.get(cell);
        if(heap != null && valid()) heap.items.remove((Item) this);
    }
    default void afterDrop(Hero hero) {
        if(valid()) {
            hero.spendAndNext(Item.TIME_TO_DROP);
            ((Item)this).detachAll(hero.belongings.backpack);
        }
    }

    default Decay decay() {
        for(Decay decay : Dungeon.hero.buffs(Decay.class)) {
            if(decay.item == this) return decay;
        }
        return null;
    }


    static void onId() {
        if(Dungeon.level == null || Dungeon.hero == null) return;

        for(Item item : Dungeon.hero.belongings) {
            if(item instanceof WealthDrop<?, ?>) {
                ((WealthDrop<?, ?>) item).updateVisuals();
            }
        }

        for(Heap heap : Dungeon.level.heaps.valueList()) {
            for(Item item : heap.items) {
                if(item instanceof WealthDrop<?, ?>) {
                    ((WealthDrop<?, ?>) item).updateVisuals();
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

    class Decay extends FlavourBuff {
        {
            type = buffType.NEUTRAL;
            actPriority = HERO_PRIO+1;
        }
        Item item = null;

        @Override
        public boolean act() {
            item.detach(Dungeon.hero.belongings.backpack);
            if(item.quantity() > 0) {
                spend(100f);
                return true;
            }
            else return super.act();
        }

        private static final String ITEM	= "item";

        @Override
        public void storeInBundle( Bundle bundle ) {
            super.storeInBundle( bundle );
            if(item != null) bundle.put( ITEM, item );
        }

        @Override
        public void restoreFromBundle( Bundle bundle ) {
            super.restoreFromBundle( bundle );
            //item = (Item)bundle.get(ITEM);

            if(bundle.get(ITEM) != null) {
                Callback callback = new Callback() {
                    @Override
                    public void call() {
                        for(Item item : Dungeon.hero.belongings) {
                            if(item.isSimilar((Item)bundle.get(ITEM))) {
                                Decay.this.item = item;
                                break;
                            }
                        }
                    }
                };

                Dungeon.runAfterLoad(callback);
            }
        }
    }
}
