package com.mrcrayfish.venture;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Author: MrCrayfish
 */
public class Config
{
    public static class Common
    {
        public final ForgeConfigSpec.IntValue survivalCampGenerateChance;

        Common(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Common configuration settings").push("common");
            {
                builder.comment("Structures").push("structures");
                {
                    this.survivalCampGenerateChance = builder.comment("The chance for a survival camp to generate. This value is assumed to be \"1 out of X\" to generate.").defineInRange("survivalCampGenerateChance", 10, 1, Integer.MAX_VALUE);
                }
                builder.pop();
            }
            builder.pop();
        }
    }

    static final ForgeConfigSpec commonSpec;
    public static final Common COMMON;

    static
    {
        final Pair<Common, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Config.Common::new);
        commonSpec = clientSpecPair.getRight();
        COMMON = clientSpecPair.getLeft();
    }
}
