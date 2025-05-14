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
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Reflection;

public interface WealthDrop<T extends Item, C extends WealthDrop<T, C>> {
    T item();
    void setItem(T item);

    default C set(Class<? extends T> type) {
        setItem(Reflection.newInstance(type));
        if(!valid()) return null;

        item().wealthDrop = this;
        updateStats();
        updateVisuals();

        return (C)this;
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
                && ((WealthDrop<?, ?>) item).valid()
                && ((WealthDrop<?, ?>) item).item().isSimilar(item())
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

        if(Dungeon.hero != null) {
            if(valid() && Dungeon.hero.belongings.contains(th())) {
                Buff.append(Dungeon.hero, Decay.class, decayTimer()).item = th();
            }
        }/* else {
            Callback callback = () -> {
                if(valid() && Dungeon.hero.belongings.contains((Item)WealthDrop.this)) {
                    Buff.append(Dungeon.hero, Decay.class, 200f).item = (Item)WealthDrop.this;
                }
            };
            Dungeon.runAfterLoad(callback);
        }*/

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
        //Wealth potions already detach by themselves
        if(valid() && item().quantity() <= 1 && !(this instanceof WealthPotion)) {
            th().detach(container);
        }
    }

    default Decay decay() {
        for(Decay decay : Dungeon.hero.buffs(Decay.class)) {
            if(decay.item == this) return decay;
        }
        return null;
    }

    default String dropName() {
        return item().trueName() + Messages.get(WealthDrop.class, "suffix");
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
            float percent = decay().cooldown() / decayTimer();
            text.hardlight(1f, percent, percent);
        }
    }


    String WEALTH_ITEM = "wealth_item";

    static void vanishVFX(int cell) {
        Sample.INSTANCE.play(Assets.Sounds.PUFF);
        CellEmitter.get( cell ).burst( Speck.factory( Speck.STEAM ), 10 );
    }

    static int decayTimer() {
        int lvl = Math.max(Ring.getBuffedBonus(Dungeon.hero, RingOfWealth.Wealth.class) - 1, 0);
        return 200 + 40*lvl;
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
            revivePersists = true;
        }
        Item item = null;

        @Override
        public boolean act() {
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

        private static final String ITEM	= "item";

        @Override
        public void storeInBundle( Bundle bundle ) {
            super.storeInBundle( bundle );
            if(item != null) bundle.put( ITEM, item );
        }

        @Override
        public void restoreFromBundle( Bundle bundle ) {
            super.restoreFromBundle( bundle );

            if(bundle.get(ITEM) != null) {
                Callback callback = () -> {
                    for(Item item : Dungeon.hero.belongings) {
                        if(item.isSimilar((Item)bundle.get(ITEM))) {
                            Decay.this.item = item;
                            break;
                        }
                    }
                };

                Dungeon.runAfterLoad(callback);
            }
        }
    }
}
