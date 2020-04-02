package com.mrcrayfish.venture.world.gen.feature;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.OreFeatureConfig;

/**
 * Author: MrCrayfish
 */
public class HugeOreFeatureConfig extends OreFeatureConfig
{
    public int chance;

    public HugeOreFeatureConfig(FillerBlockType target, BlockState state, int size, int chance)
    {
        super(target, state, size);
        this.chance = chance;
    }

    public <T> Dynamic<T> serialize(DynamicOps<T> ops)
    {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("chance"), ops.createInt(this.chance), ops.createString("size"), ops.createInt(this.size), ops.createString("target"), ops.createString(this.target.func_214737_a()), ops.createString("state"), BlockState.serialize(ops, this.state).getValue())));
    }

    public static HugeOreFeatureConfig deserialize(Dynamic<?> dynamic)
    {
        int chance = dynamic.get("chance").asInt(0);
        int size = dynamic.get("size").asInt(0);
        OreFeatureConfig.FillerBlockType fillerBlockType = OreFeatureConfig.FillerBlockType.func_214736_a(dynamic.get("target").asString(""));
        BlockState state = dynamic.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
        return new HugeOreFeatureConfig(fillerBlockType, state, size, chance);
    }
}
