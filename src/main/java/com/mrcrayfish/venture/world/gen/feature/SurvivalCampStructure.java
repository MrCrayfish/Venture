package com.mrcrayfish.venture.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import com.mrcrayfish.venture.world.gen.feature.structure.SurvivalCamp;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Function;

/**
 * Author: MrCrayfish
 */
public class SurvivalCampStructure extends Structure<SurvivalCampConfig>
{
    public SurvivalCampStructure(Function<Dynamic<?>, ? extends SurvivalCampConfig> configFactory)
    {
        super(configFactory);
    }

    @Override
    public boolean func_225558_a_(BiomeManager manager, ChunkGenerator<?> generator, Random rand, int chunkX, int chunkZ, Biome biome)
    {
        if(generator.hasStructure(biome, this))
        {
            ((SharedSeedRandom) rand).setLargeFeatureSeedWithSalt(generator.getSeed(), chunkX, chunkZ, 0xF00D);
            SurvivalCampConfig config = generator.getStructureConfig(biome, this);
            return config != null && rand.nextInt(config.chance) == 0;
        }
        return false;
    }

    @Nullable
    @Override
    public BlockPos findNearest(World worldIn, ChunkGenerator<? extends GenerationSettings> chunkGenerator, BlockPos pos, int radius, boolean p_211405_5_)
    {
        return super.findNearest(worldIn, chunkGenerator, pos, radius, p_211405_5_);
    }

    @Override
    public IStartFactory getStartFactory()
    {
        return SurvivalCampStart::new;
    }

    @Override
    public String getStructureName()
    {
        return this.getRegistryName().toString();
    }

    @Override
    public int getSize()
    {
        return 1;
    }

    public static class SurvivalCampStart extends StructureStart
    {
        public SurvivalCampStart(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bounds, int references, long seed)
        {
            super(structure, chunkX, chunkZ, bounds, references, seed);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager manager, int chunkX, int chunkZ, Biome biome)
        {
            int posX = chunkX << 4;
            int posZ = chunkZ << 4;
            int height1 = generator.func_222532_b(posX + 3, posZ + 3, Heightmap.Type.OCEAN_FLOOR_WG);
            int height2 = generator.func_222532_b(posX + 13, posZ + 3, Heightmap.Type.OCEAN_FLOOR_WG);
            int height3 = generator.func_222532_b(posX + 3, posZ + 13, Heightmap.Type.OCEAN_FLOOR_WG);
            int height4 = generator.func_222532_b(posX + 13, posZ + 13, Heightmap.Type.OCEAN_FLOOR_WG);
            if(height1 == height2 && height1 == height3 && height1 == height4 && height1 >= generator.getSeaLevel())
            {
                BlockPos pos = new BlockPos(posX + 3, 90, posZ + 3);
                Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
                this.components.add(new SurvivalCamp.Piece(manager, pos, rotation, Mirror.NONE));
                this.recalculateStructureSize();
            }
        }

        @Override
        public BlockPos getPos()
        {
            return new BlockPos((this.getChunkPosX() << 4) + 3, 0, (this.getChunkPosZ() << 4) + 3);
        }

        @Override
        public void func_225565_a_(IWorld p_225565_1_, ChunkGenerator<?> p_225565_2_, Random p_225565_3_, MutableBoundingBox p_225565_4_, ChunkPos p_225565_5_)
        {
            super.func_225565_a_(p_225565_1_, p_225565_2_, p_225565_3_, p_225565_4_, p_225565_5_);
        }
    }
}
