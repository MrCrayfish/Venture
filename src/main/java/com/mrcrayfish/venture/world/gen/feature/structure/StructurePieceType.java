package com.mrcrayfish.venture.world.gen.feature.structure;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

/**
 * Author: MrCrayfish
 */
public interface StructurePieceType
{
    IStructurePieceType HUGE_ORE_PIECE = register(HugeOre.Piece::new, "venture:huge_ore_piece");

    static IStructurePieceType register(IStructurePieceType type, String key)
    {
        return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(key), type);
    }
}
