package com.mrcrayfish.venture.world.gen.feature.structure;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

/**
 * Author: MrCrayfish
 */
public class HugeOre
{
    public static class Piece extends StructurePiece
    {
        public Piece(BlockPos pos)
        {
            super(StructurePieceType.HUGE_ORE_PIECE, 0);
            this.boundingBox = new MutableBoundingBox(pos.getX(), pos.getY(), pos.getZ(), pos.getX(), pos.getY(), pos.getZ());
        }

        Piece(TemplateManager manager, CompoundNBT compound)
        {
            super(StructurePieceType.HUGE_ORE_PIECE, compound);
        }

        @Override
        protected void readAdditional(CompoundNBT compound) {}

        @Override
        public boolean func_225577_a_(IWorld world, ChunkGenerator<?> generator, Random rand, MutableBoundingBox bounds, ChunkPos chunkPos)
        {
            return true;
        }
    }
}
