package com.shatteredpixel.shatteredpixeldungeon.actors;

import java.util.Arrays;
import java.util.HashSet;

public enum DamageProperty {

    OBEYS_IMMUNITIES,
    RESISTED,
    PHYSICAL,
    MAGIC,
    IGNORE_SHIELDS,

    //unused for now
    INDIRECT;

    public static final HashSet<DamageProperty> EMPTY             = new HashSet<>();
    public static final HashSet<DamageProperty> DEFAULT           = new HashSet<>(Arrays.asList(OBEYS_IMMUNITIES, RESISTED, INDIRECT));
    public static final HashSet<DamageProperty> DEFAULT_ATTACK    = new HashSet<>(Arrays.asList(OBEYS_IMMUNITIES, RESISTED, PHYSICAL));
}
