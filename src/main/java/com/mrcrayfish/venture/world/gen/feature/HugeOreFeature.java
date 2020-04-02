package com.mrcrayfish.venture.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.Random;
import java.util.function.Function;

/**
 * Author: MrCrayfish
 */
public class HugeOreFeature extends OreFeature
{
    public HugeOreFeature(Function<Dynamic<?>, ? extends OreFeatureConfig> configFactoryIn)
    {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, OreFeatureConfig config)
    {
        if(rand.nextInt(500) == 0)
        {
            System.out.println("Generating huge ore at " + pos);
            return super.place(worldIn, generator, rand, pos, config);
        }
        return false;
    }
}
