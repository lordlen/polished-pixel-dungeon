package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.PlateArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfFeatherFall;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Glaive;
import com.watabou.utils.Reflection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Debug {

    //SHOULD ALWAYS BE SET TO FALSE ON OFFICIAL RELEASES.
    public static final boolean DEBUG_MODE = false;

    public static final int Starting_Floor = 1;
    public static final int Starting_HeroLevel = 1;
    public static final int Starting_Str = 10;

    private static boolean ActOnLoad = false;
    private static ArrayList<Class<?extends Item>> Starting_Items;
    static {
        Starting_Items = new ArrayList<>(Arrays.asList(
                PotionOfHaste.class, PotionOfMindVision.class, PotionOfInvisibility.class, ElixirOfFeatherFall.class,
                ScrollOfMagicMapping.class, ScrollOfTeleportation.class, ScrollOfUpgrade.class,
                TimekeepersHourglass.class
        ));
    }
    public static void Starting_Bag() {
        if(!DEBUG_MODE || Starting_Items == null) return;

        for(Class<?extends Item> itemType : Debug.Starting_Items) {
             DebugCollect(itemType);
        }

        DebugCollect(Glaive.class, 10, 1);
        DebugCollect(PlateArmor.class, 10, 1);
    }

    public static void LoadGame() {
        if(!Debug.DEBUG_MODE || !Debug.ActOnLoad) return;

        Starting_Bag();
    }


    public static void DebugCollect(Class<?extends Item> itemType) {
        DebugCollect(itemType, 0, 99);
    }
    public static void DebugCollect(Class<?extends Item> itemType, int level) {
        DebugCollect(itemType, level, 1);
    }
    public static void DebugCollect(Class<?extends Item> itemType, int level, int quantity) {
        if(!DEBUG_MODE) return;

        Item i = Reflection.newInstance(itemType);
        if(i == null) return;

        i.quantity(i.stackable ? quantity : 1);
        i.identify();
        i.level(level);
        i.collect();
    }
}
