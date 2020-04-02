package com.mrcrayfish.venture;

import com.mrcrayfish.venture.init.ModFeatures;
import com.mrcrayfish.venture.world.gen.feature.HugeOreFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
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
        Biome.BIOMES.forEach(biome -> {
            ConfiguredFeature<HugeOreFeatureConfig, ? extends Structure<HugeOreFeatureConfig>> feature = ModFeatures.HUGE_ORE.get().func_225566_b_(new HugeOreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 50, 200));
            biome.func_226711_a_(feature);
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, feature.func_227228_a_(Placement.COUNT_DEPTH_AVERAGE.func_227446_a_(new DepthAverageConfig(1, 24, 4))));
        });
    }
}
