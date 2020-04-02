package com.mrcrayfish.venture.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import com.mrcrayfish.venture.world.gen.feature.structure.HugeOre;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
import java.util.function.Function;

/**
 * Author: MrCrayfish
 */
public class HugeOreStructure extends Structure<HugeOreFeatureConfig>
{
    private OreFeature feature;

    public HugeOreStructure(Function<Dynamic<?>, ? extends HugeOreFeatureConfig> configFactoryIn)
    {
        super(configFactoryIn);
        this.feature = new OreFeature(configFactoryIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, HugeOreFeatureConfig config)
    {
        if(!worldIn.getWorldInfo().isMapFeaturesEnabled())
        {
            return false;
        }
        int chunkX = pos.getX() >> 4;
        int chunkZ = pos.getZ() >> 4;
        for(Long chunkLong : worldIn.getChunk(chunkX, chunkZ).getStructureReferences(this.getStructureName()))
        {
            ChunkPos chunkpos = new ChunkPos(chunkLong);
            StructureStart start = worldIn.getChunk(chunkpos.x, chunkpos.z).getStructureStart(this.getStructureName());
            if(start != null && start != StructureStart.DUMMY)
            {
                return this.feature.place(worldIn, generator, rand, new BlockPos(start.getPos().getX(), pos.getY(), start.getPos().getZ()), config);
            }
        }
        return false;
    }

    @Override
    public boolean func_225558_a_(BiomeManager manager, ChunkGenerator<?> generator, Random rand, int chunkX, int chunkZ, Biome biome)
    {
        if(generator.hasStructure(biome, this))
        {
            ((SharedSeedRandom) rand).setLargeFeatureSeedWithSalt(generator.getSeed(), chunkX, chunkZ, 0xF0000D);
            HugeOreFeatureConfig config = generator.getStructureConfig(biome, this);
            return config != null && rand.nextInt(config.chance) == 0;
        }
        return false;
    }

    @Override
    public IStartFactory getStartFactory()
    {
        return HugeOreStart::new;
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

    public static class HugeOreStart extends StructureStart
    {
        public HugeOreStart(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bounds, int references, long seed)
        {
            super(structure, chunkX, chunkZ, bounds, references, seed);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager manager, int chunkX, int chunkZ, Biome biome)
        {
            BlockPos pos = new BlockPos(chunkX * 16 + 9, 25, chunkZ * 16 + 9);
            this.components.add(new HugeOre.Piece(pos));
            this.recalculateStructureSize();
        }

        @Override
        public BlockPos getPos()
        {
            return new BlockPos((this.getChunkPosX() << 4) + 9, 0, (this.getChunkPosZ() << 4) + 9);
        }
    }
}
