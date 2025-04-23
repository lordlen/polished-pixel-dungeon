package com.shatteredpixel.shatteredpixeldungeon.actors;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum DamageProperty {

    RESISTED,
    PHYSICAL,
    MAGIC,
    IGNORE_SHIELDS,
    IGNORE_IMMUNITIES,
    SPECIAL,

    //unused for now
    INDIRECT;

    public static final HashSet<DamageProperty> IGNORE_ALL      = new HashSet<>(Arrays.asList(IGNORE_IMMUNITIES, IGNORE_SHIELDS));
    public static final HashSet<DamageProperty> DEFAULT         = new HashSet<>(Arrays.asList(RESISTED, INDIRECT));
    public static final HashSet<DamageProperty> DEFAULT_ATTACK  = new HashSet<>(Arrays.asList(RESISTED, PHYSICAL));
    public static final HashSet<DamageProperty> DEFAULT_ZAP     = new HashSet<>(Arrays.asList(RESISTED, MAGIC));

}
