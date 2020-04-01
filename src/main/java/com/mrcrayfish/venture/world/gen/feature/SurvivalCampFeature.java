package com.mrcrayfish.venture.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import com.mrcrayfish.venture.Reference;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

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
        for(pos = pos.up(); worldIn.isAirBlock(pos) && pos.getY() > 3; pos = pos.down());

        if (!IS_GRASS.test(worldIn.getBlockState(pos)))
        {
            return false;
        }

        WorldGenRegion genRegion = (WorldGenRegion) worldIn;
        Template template = genRegion.getWorld().getSaveHandler().getStructureTemplateManager().getTemplate(SURVIVAL_CAMP_TEMPLATE);
        if(template != null)
        {
            template.addBlocksToWorld(worldIn, pos, new PlacementSettings().setCenterOffset(new BlockPos(5, 1, 5)).setRotation(Rotation.randomRotation(rand)).setMirror(Mirror.values()[rand.nextInt(Mirror.values().length)]));
        }
        return true;
    }
}
