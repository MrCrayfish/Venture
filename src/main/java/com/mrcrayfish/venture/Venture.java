package com.mrcrayfish.venture;

import com.google.common.collect.ImmutableList;
import com.mrcrayfish.venture.init.ModFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.treedecorator.LeaveVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Author: MrCrayfish
 */
@Mod(Reference.MOD_ID)
public class Venture
{
    public static final HugeTreeFeatureConfig HUGE_TREE_CONFIG = (new HugeTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()), new SimpleBlockStateProvider(Blocks.JUNGLE_LEAVES.getDefaultState()))).baseHeight(10).func_227283_b_(20).func_227282_a_(ImmutableList.of(new TrunkVineTreeDecorator(), new LeaveVineTreeDecorator())).setSapling((net.minecraftforge.common.IPlantable) Blocks.JUNGLE_SAPLING).build();

    public Venture()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModFeatures.REGISTER.register(bus);
        bus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        Biomes.PLAINS.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ModFeatures.SURVIVAL_CAMP.get().func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_TOP_SOLID_HEIGHTMAP.func_227446_a_(new ChanceConfig(40))));
        Biomes.SUNFLOWER_PLAINS.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ModFeatures.SURVIVAL_CAMP.get().func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_TOP_SOLID_HEIGHTMAP.func_227446_a_(new ChanceConfig(40))));
        Biomes.SAVANNA.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ModFeatures.SURVIVAL_CAMP.get().func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_TOP_SOLID_HEIGHTMAP.func_227446_a_(new ChanceConfig(40))));
        //.func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG))
    }
}
