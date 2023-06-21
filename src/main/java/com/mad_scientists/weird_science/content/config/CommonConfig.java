package com.mad_scientists.weird_science.content.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Double> PROPULSION_GEL_XZ_MODIFIER;
    public static final ForgeConfigSpec.ConfigValue<Double> PROPULSION_GEL_Y_MODIFIER;


    static {
        BUILDER.push("Common Configs for Weird Science");
        PROPULSION_GEL_XZ_MODIFIER = BUILDER.comment("How much faster you move in the X and Z directions when on Propulsion Gel. 1.0 is normal movement speed. Keep in mind the speed increases by this value every time you collide with a new block of Propulsion Gel.")
                .define("Propulsion Gel X + Z Modifier", 1.1);
        PROPULSION_GEL_Y_MODIFIER = BUILDER.comment("How much faster you move in the Y direction when jumping on to a wall of Propulsion Gel. 1.0 is normal movement speed. Keep in mind the speed increases by this value every time you collide with a new block of Propulsion Gel.")
                .define("Propulsion Gel Y Modifier", 1.25);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
