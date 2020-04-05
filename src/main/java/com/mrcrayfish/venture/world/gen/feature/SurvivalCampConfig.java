package com.mrcrayfish.venture.world.gen.feature;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;

/**
 * Author: MrCrayfish
 */
public class SurvivalCampConfig implements IFeatureConfig
{
    public final int chance;

    public SurvivalCampConfig(int chance)
    {
        this.chance = chance;
    }

    @Override
    public <T> Dynamic<T> serialize(DynamicOps<T> ops)
    {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("chance"), ops.createInt(this.chance))));
    }

    public static SurvivalCampConfig deserialize(Dynamic<?> dynamic)
    {
        int chance = dynamic.get("chance").asInt(0);
        return new SurvivalCampConfig(chance);
    }
}
