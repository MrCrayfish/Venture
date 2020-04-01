package com.mrcrayfish.venture.init;

import com.mrcrayfish.venture.Reference;
import com.mrcrayfish.venture.world.gen.feature.SurvivalCampFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Author: MrCrayfish
 */
public class ModFeatures
{
    public static final DeferredRegister<Feature<?>> REGISTER = new DeferredRegister<>(ForgeRegistries.FEATURES, Reference.MOD_ID);

    public static final RegistryObject<SurvivalCampFeature> SURVIVAL_CAMP = REGISTER.register("survival_camp", () -> new SurvivalCampFeature(NoFeatureConfig::deserialize));
}
