package com.mrcrayfish.venture;

import com.mrcrayfish.venture.init.ModFeatures;
import com.mrcrayfish.venture.init.ModStructurePieceType;
import com.mrcrayfish.venture.world.gen.feature.HugeOreFeatureConfig;
import com.mrcrayfish.venture.world.gen.feature.SurvivalCampConfig;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
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
        ModStructurePieceType.init();
        bus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        this.addSurvivalCamp(Biomes.PLAINS);
        this.addSurvivalCamp(Biomes.SUNFLOWER_PLAINS);
        this.addSurvivalCamp(Biomes.SAVANNA);
        this.addSurvivalCamp(Biomes.SAVANNA_PLATEAU);
        this.addSurvivalCamp(Biomes.BIRCH_FOREST);
        this.addSurvivalCamp(Biomes.DARK_FOREST);

        Biome.BIOMES.forEach(biome -> {
            ConfiguredFeature<HugeOreFeatureConfig, ? extends Structure<HugeOreFeatureConfig>> hugeOreFeature = ModFeatures.HUGE_ORE.get().func_225566_b_(new HugeOreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 50, 200));
            biome.func_226711_a_(hugeOreFeature);
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, hugeOreFeature.func_227228_a_(Placement.COUNT_DEPTH_AVERAGE.func_227446_a_(new DepthAverageConfig(1, 24, 4))));
        });
    }

    private void addSurvivalCamp(Biome biome)
    {
        ConfiguredFeature<SurvivalCampConfig, ? extends Structure<SurvivalCampConfig>> survivalCampFeature = ModFeatures.SURVIVAL_CAMP.get().func_225566_b_(new SurvivalCampConfig(10));
        biome.func_226711_a_(survivalCampFeature);
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, survivalCampFeature.func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }
}
