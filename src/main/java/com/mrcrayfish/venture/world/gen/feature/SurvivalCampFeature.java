package com.mrcrayfish.venture.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import com.mrcrayfish.venture.Reference;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;

import java.util.Random;
import java.util.function.Function;

/**
 * Author: MrCrayfish
 */
public class SurvivalCampFeature extends Feature<NoFeatureConfig>
{
    private static final ResourceLocation SURVIVAL_CAMP_TEMPLATE = new ResourceLocation(Reference.MOD_ID, "survival_camp");
    private static final BlockStateMatcher IS_GRASS = BlockStateMatcher.forBlock(Blocks.GRASS);

    public SurvivalCampFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory)
    {
        super(configFactory);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        for(pos = pos.up(); (worldIn.isAirBlock(pos) || worldIn.getBlockState(pos).getMaterial().isReplaceable()) && pos.getY() > 3; pos = pos.down());

        if(worldIn.getBlockState(pos).getBlock() != Blocks.GRASS_BLOCK || worldIn.getBlockState(pos.east(10)).getBlock() != Blocks.GRASS_BLOCK || worldIn.getBlockState(pos.south(10)).getBlock() != Blocks.GRASS_BLOCK)
        {
            return false;
        }

        WorldGenRegion genRegion = (WorldGenRegion) worldIn;
        Template template = genRegion.getWorld().getSaveHandler().getStructureTemplateManager().getTemplate(SURVIVAL_CAMP_TEMPLATE);
        if(template != null)
        {
            System.out.println(pos);
            template.addBlocksToWorld(worldIn, pos, new PlacementSettings());
        }
        return true;
    }
}
