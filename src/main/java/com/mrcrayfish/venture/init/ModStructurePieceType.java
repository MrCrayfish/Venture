package com.mrcrayfish.venture.init;

import com.mrcrayfish.venture.world.gen.feature.structure.HugeOre;
import com.mrcrayfish.venture.world.gen.feature.structure.SurvivalCamp;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

/**
 * Author: MrCrayfish
 */
public class ModStructurePieceType
{
    public static final IStructurePieceType SURVIVAL_CAMP = register(SurvivalCamp.Piece::new, "venture:survival_camp");
    public static final IStructurePieceType HUGE_ORE_PIECE = register(HugeOre.Piece::new, "venture:huge_ore_piece");

    public static void init() {} //Force static fields to initialize

    private static IStructurePieceType register(IStructurePieceType type, String key)
    {
        return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(key), type);
    }
}
