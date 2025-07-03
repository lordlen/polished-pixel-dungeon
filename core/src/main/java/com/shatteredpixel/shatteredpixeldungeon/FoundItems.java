package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Torch;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Pasty;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;

public class FoundItems {
    private static final HashMap<Class<?>, Pair> floors = new HashMap<>();
    
    public static void reset() {
        for(Class<?> cls : Generator.Category.POTION.classes) {
            floors.put(cls, new Pair());
        }
        for(Class<?> cls : Generator.Category.SCROLL.classes) {
            floors.put(cls, new Pair());
        }
        
        floors.put(Food.class, new Pair());
        floors.put(Torch.class, new Pair());
    }
    static Class<?> getEquivalent(Class<?> cls) {
        if(floors.containsKey(cls)) {
            return cls;
        }
        else if(ExoticPotion.exoToReg.containsKey(cls)) {
            return ExoticPotion.exoToReg.get(cls);
        }
        else if(ExoticScroll.exoToReg.containsKey(cls)) {
            return ExoticScroll.exoToReg.get(cls);
        }
        else if(cls == Pasty.class) {
            return Food.class;
        }
        
        return cls;
    }

    public static void add(Class<?> cls, int floor) {
        Pair pair = floors.get(getEquivalent(cls));
        if(pair == null) return;

        if(pair.first == 0) {
            pair.first = floor;
        }
        else if(pair.second == 0) {
            pair.second = floor;
        }
        else {
            pair.first = pair.second;
            pair.second = floor;
        }
    }

    public static String getDesc(Class<?> cls) {
        Pair pair = floors.get(getEquivalent(cls));
        if(pair == null) return "";

        if (Scroll.class.isAssignableFrom(cls)
            && Scroll.getKnown().contains(ScrollOfUpgrade.class))
            return "";

        if(pair.first != 0 && pair.second != 0) {
            return "\n\nThis item type was last found on _floors " + pair.first + " and " + pair.second + "_.";
        }
        else if(pair.first != 0) {
            return "\n\nThis item type was last found on _floor " + pair.first + "_.";
        }
        else return "";
    }

    public static void store(Bundle bundle) {
        for(Class<?> cls : floors.keySet()) {
            Bundle pair = new Bundle();
            floors.get(cls).storeInBundle(pair);

            bundle.put(cls.getName(), pair);
        }
    }

    public static void restore(Bundle bundle) {
        reset();
        for(Class<?> cls : floors.keySet()) {
            if(bundle.contains(cls.getName())) {
                Bundle pair = bundle.getBundle(cls.getName());
                floors.get(cls).restoreFromBundle(pair);
            }
        }
    }

    public static class Pair implements Bundlable {
        public int first;
        public int second;

        public Pair() {
            this.first = 0;
            this.second = 0;
        }

        public static final String FIRST = "first";
        public static final String SECOND = "second";

        @Override
        public void storeInBundle(Bundle bundle) {
            bundle.put(FIRST, first);
            bundle.put(SECOND, second);
        }

        @Override
        public void restoreFromBundle(Bundle bundle) {
            first = bundle.getInt(FIRST);
            second = bundle.getInt(SECOND);
        }
    }
}
