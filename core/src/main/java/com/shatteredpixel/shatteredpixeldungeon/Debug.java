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

package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.EnergyCrystal;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Stylus;
import com.shatteredpixel.shatteredpixeldungeon.items.Torch;
import com.shatteredpixel.shatteredpixeldungeon.items.Waterskin;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.MailArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfFeatherFall;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.PhaseShift;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfBlink;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfShock;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingKnife;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingStone;
import com.watabou.utils.DeviceCompat;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("PointlessBooleanExpression")
public class Debug {

    //SHOULD ALWAYS BE SET TO FALSE ON OFFICIAL RELEASES.
    public static final boolean DEBUG_MODE = DeviceCompat.isDebug();


    private static final boolean DebuggingStats = false
    ;
    //                                                                  Debug  /  Default
    public static final float Spawn_Multiplier = DebuggingStats ?       .635f   : 1;
    public static final float Respawn_Multiplier = DebuggingStats ?     0f      : 1;

    public static final int Starting_Floor = DebuggingStats ?           6       : 1;
    public static final int Starting_HeroLevel = DebuggingStats ?       15      : 1;
    public static final int Starting_Str = DebuggingStats ?             16      : 10;
    public static final int Starting_HP = DebuggingStats ?              2000    : 20;
    
    private static final boolean ActOnStart = false || DebuggingStats;
    private static final boolean ActOnLoad = false;
    
    private static final ArrayList<Class<?extends Item>> Starting_Items;
    static {
        //Testing items
        Starting_Items = new ArrayList<>(Arrays.asList(
        
        ));



        if(DebuggingStats && true) {
            Starting_Items.addAll(Arrays.asList(
                PotionOfMindVision.class, PotionOfInvisibility.class, PotionOfHaste.class, ElixirOfFeatherFall.class,
                ScrollOfMagicMapping.class, ScrollOfUpgrade.class,
                StoneOfBlink.class, StoneOfBlast.class, StoneOfShock.class,
                Food.class, EnergyCrystal.class
            ));
        }
    }
    
    public static void Starting_Bag() {
        if(!DEBUG_MODE || Starting_Items == null) return;

        for(Class<?extends Item> itemType : Starting_Items) {
            DebugCollect(itemType);
        }
        if(Dungeon.isChallenged(Challenges.DARKNESS)) {
            DebugCollect(Torch.class);
        }
        
        
    }
    
    public static void LoadGame() {
        if(!DEBUG_MODE || !ActOnLoad) return;
        
        //Hero.Polished.Debug_UpdateStats(Starting_HeroLevel, Starting_Str);
        //Starting_Bag();
        
        
    }
    
    
    public static void StartGame() {
        if(!DEBUG_MODE || !ActOnStart) return;
        
        Starting_Bag();
        SetQuickslots();
        
        Hero.Polished.Debug_UpdateStats(Starting_HeroLevel, Starting_Str);
        MeleeWeapon.Charger charger = Dungeon.hero.buff(MeleeWeapon.Charger.class);
        if(charger != null) charger.gainCharge(charger.chargeCap() - charger.charges);
    }
    
    
    public static<T extends Item> T DebugCollect(Class<T> itemType) {
        return DebugCollect(itemType, 0, 99, null);
    }
    public static<T extends Item> T DebugCollect(Class<T> itemType, int level) {
        return DebugCollect(itemType, level, 99, null);
    }
    public static<T extends Item> T DebugCollect(Class<T> itemType, int level, int quantity) {
        return DebugCollect(itemType, level, quantity, null);
    }
    public static<T extends Item, E> T DebugCollect(Class<T> itemType, Class<E> enchant) {
        return DebugCollect(itemType, 0, 1, enchant);
    }
    public static<T extends Item, E> T DebugCollect(Class<T> itemType, int level, int quantity, Class<E> enchant) {
        if(!DEBUG_MODE) return null;
        Item i = Reflection.newInstance(itemType);
        if(i == null) return null;

        if(i instanceof EnergyCrystal) {
            Dungeon.energy += i.quantity();
            return null;
        }

        i.quantity(i.stackable ? quantity : 1);
        i.identify();
        i.level(i.isUpgradable() ? level : 0);

        if(enchant != null) {
            if(Weapon.Enchantment.class.isAssignableFrom(enchant) && i instanceof Weapon) {
                ((Weapon) i).enchant( (Weapon.Enchantment) Reflection.newInstance(enchant) );
            }
            if(Armor.Glyph.class.isAssignableFrom(enchant) && i instanceof Armor) {
                ((Armor) i).inscribe( (Armor.Glyph) Reflection.newInstance(enchant) );
            }
        }

        if(i instanceof Wand) {
            ((Wand) i).gainCharge(level);
        }
        if(i instanceof Artifact) {
            ((Artifact) i).DEBUG_maxCharge();
        }

        i.collect();
        return (T)i;
    }


    private static void SetQuickslots() {
        List<Class<? extends Item>> quickslot = Arrays.asList(
                StoneOfBlast.class, ScrollOfMagicMapping.class, PotionOfMindVision.class, PotionOfInvisibility.class, PotionOfHaste.class, ElixirOfFeatherFall.class
        );
        
        ArrayList<Item> items = new ArrayList<>();
        boolean contains = true;
        for (Class<? extends Item> cls : quickslot) {
            Item item = Dungeon.hero.belongings.getItem(cls);
            if(item != null) items.add(item);
            else {
                contains = false;
                break;
            }
        }
        
        if(DebuggingStats && contains) {
            Waterskin waterskin = Dungeon.hero.belongings.getItem(Waterskin.class);
            if(waterskin != null) waterskin.detachAll(Dungeon.hero.belongings.backpack);
            
            int index = 1;
            for(Item item : items) {
                if(index < QuickSlot.SIZE) {
                    Dungeon.quickslot.setSlot(index, item);
                    index++;
                }
            }
        }
        else if(DebuggingStats) {
            Waterskin waterskin = Dungeon.hero.belongings.getItem(Waterskin.class);
            ThrowingStone stones = Dungeon.hero.belongings.getItem(ThrowingStone.class);
            ThrowingKnife knives = Dungeon.hero.belongings.getItem(ThrowingKnife.class);
            
            Dungeon.quickslot.clearItem(waterskin);
            Dungeon.quickslot.clearItem(stones);
            Dungeon.quickslot.clearItem(knives);
        }
    }
}
