package com.mrcrayfish.venture.init;

import com.mrcrayfish.venture.Reference;
import com.mrcrayfish.venture.world.gen.feature.HugeOreFeatureConfig;
import com.mrcrayfish.venture.world.gen.feature.HugeOreStructure;
import com.mrcrayfish.venture.world.gen.feature.SurvivalCampStructure;
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

    public static final RegistryObject<SurvivalCampStructure> SURVIVAL_CAMP = REGISTER.register("survival_camp", () -> new SurvivalCampStructure(NoFeatureConfig::deserialize));
    public static final RegistryObject<HugeOreStructure> HUGE_ORE = REGISTER.register("huge_ore", () -> new HugeOreStructure(HugeOreFeatureConfig::deserialize));
}
