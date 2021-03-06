package net.theexceptionist.main.worldgen;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.theexceptionist.main.entity.EntityVillagerKnight;

public class VillageComponentStable extends StructureVillagePieces.Village
    {
        private boolean isRoofAccessible;
		private int villagersSpawned;

        public VillageComponentStable()
        {
        }

        public VillageComponentStable(StructureVillagePieces.Start start, int p_i45566_2_, Random rand, StructureBoundingBox p_i45566_4_, EnumFacing facing)
        {
            super(start, p_i45566_2_);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45566_4_;
            this.isRoofAccessible = rand.nextBoolean();
        }

        public static VillageComponentStable createPiece(StructureVillagePieces.Start start, List<StructureComponent> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 8, 5, 8, facing);
            return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new VillageComponentStable(start, p_175858_7_, rand, structureboundingbox, facing);
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
        	//System.out.println("Generating"+this.getXWithOffset(0, 0)+" "+this.getZWithOffset(0, 0));
            if (this.averageGroundLvl < 0)
            {
                this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.averageGroundLvl < 0)
                {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 3 , 0);
            }
            
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 8, 5, 8, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);


            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
            IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
            IBlockState iblockstate4 = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            
            IBlockState iblockstate5 = this.getBiomeSpecificBlockState(Blocks.DOUBLE_STONE_SLAB.getDefaultState());
            IBlockState iblockstate6 = this.getBiomeSpecificBlockState(Blocks.STONEBRICK.getDefaultState());
            
            
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 7, 0, 7, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 6, 0, 6, iblockstate1, iblockstate1, false);

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 7, 1, 7, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 6, 1, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 7, 2, 7, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 1, 6, 2, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 0, 7, 3, 7, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 1, 6, 3, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 0, 7, 4, 7, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 6, 4, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 0, 7, 5, 7, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 1, 6, 5, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 5, 3, 5, 5, 5, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 0, 5, 4, 0, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

/*this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 3, 1, 0, EnumFacing.NORTH);
            
            this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn,5, 1, 0, EnumFacing.NORTH);*/
            
//            this.fillWithBlocks(worldIn, structureBoundingBoxIn, randomIn.nextInt(6)+1, 1, randomIn.nextInt(6)+1,  randomIn.nextInt(2)+4, 1, randomIn.nextInt(2)+4, iblockstate4, iblockstate4, false);
//            this.fillWithBlocks(worldIn, structureBoundingBoxIn, randomIn.nextInt(6)+1, 1, randomIn.nextInt(6)+1, randomIn.nextInt(2)+4, 1,  randomIn.nextInt(2)+4, iblockstate4, iblockstate4, false);
//            this.fillWithBlocks(worldIn, structureBoundingBoxIn, randomIn.nextInt(6)+1, 1, randomIn.nextInt(6)+1,  randomIn.nextInt(2)+4, 1,  randomIn.nextInt(2)+4, iblockstate4, iblockstate4, false);
//            this.fillWithBlocks(worldIn, structureBoundingBoxIn, randomIn.nextInt(6)+1, 1, randomIn.nextInt(6)+1,  randomIn.nextInt(2)+4, 1,  randomIn.nextInt(2)+4, iblockstate4, iblockstate4, false);
//            
//            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3+randomIn.nextInt(3), 1, 3+randomIn.nextInt(3), 3+randomIn.nextInt(3), 1, 3+randomIn.nextInt(3), Blocks.TORCH.getDefaultState(), Blocks.TORCH.getDefaultState(), false);
//            
            
            /*this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 1, 7, 3, 7, iblockstate, iblockstate, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 0, 8, 0, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 0, 1, 7, 0, 4, iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 3, 0, 8, 3, 5, iblockstate6, iblockstate6, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 3, 1, 7, 3, 4, iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 0, 4, 2, 0, iblockstate6, iblockstate6, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 0, 1, 5, 2, 1, iblockstate1, iblockstate1, false);
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 5, 8, 2, 5, iblockstate6, iblockstate6, false);
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 0, 4, 7, 2, 4, iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 1, 4, 2, 1, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 3, 4, 2, 3, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 4, 4, 2, 4, iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 1, 2, 0, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 0, 2, 2, 0, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 0, 3, 2, 0, iblockstate1, iblockstate1, false);
            
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 1, 4, iblockstate1, iblockstate1, false);
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 1, 0, 2, 4, iblockstate1, iblockstate1, false);
            
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 4, 3, 1, 4, iblockstate1, iblockstate1, false);
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 1, 3, 2, 4, iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 1, 5, 2, 1, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 1, 7, 2, 1, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 4, 5, 2, 4, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 4, 7, 2, 4, iblockstate4, iblockstate4, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 4, 7, 1, 7, iblockstate4, iblockstate4, false);
            this.setBlockState(worldIn, iblockstate4, 7, 1, 7, structureBoundingBoxIn);

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 7, 1, 1, 7, iblockstate4, iblockstate4, false);
            this.setBlockState(worldIn, iblockstate4, 1, 2, 7, structureBoundingBoxIn);

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 7, 1, 1, 6, iblockstate4, iblockstate4, false);
            
            this.placeTorch(worldIn, EnumFacing.UP, 5, 1, 3, structureBoundingBoxIn);
            this.placeTorch(worldIn, EnumFacing.UP, 2, 1, 7, structureBoundingBoxIn);
            
            
            this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 6, 1, 4, EnumFacing.NORTH);
            
            this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn,4, 1, 2, EnumFacing.NORTH);*/
            
//            if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR)
//            {
//                this.setBlockState(worldIn, iblockstate2, 2, 0, -1, structureBoundingBoxIn);
//
//                if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH)
//                {
//                    this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
//                }
//            }

            
            /*if (this.isRoofAccessible)
            {
                this.setBlockState(worldIn, iblockstate4, 0, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 1, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 2, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 3, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 0, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 1, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 2, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 3, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 0, 5, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 0, 5, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 0, 5, 3, structureBoundingBoxIn);
            }

            if (this.isRoofAccessible)
            {
                IBlockState iblockstate5 = Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH);
                this.setBlockState(worldIn, iblockstate5, 3, 1, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate5, 3, 2, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate5, 3, 3, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate5, 3, 4, 3, structureBoundingBoxIn);
            }*/

           // this.placeTorch(worldIn, EnumFacing.NORTH, 2, 3, 1, structureBoundingBoxIn);
           
           

            for (int j = 0; j < 8; ++j)
            {
                for (int i = 0; i < 8; ++i)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, i, 6, j, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, iblockstate, i, -1, j, structureBoundingBoxIn);
                }
            }

            this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1 + randomIn.nextInt(3));
            return true;
        }
        
        protected void spawnVillagers(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, int count)
        {
            if (this.villagersSpawned < count)
            {
                for (int i = villagersSpawned; i < count; ++i)
                {
                    int j = this.getXWithOffset(x + i, z);
                    int k = this.getYWithOffset(y);
                    int l = this.getZWithOffset(x + i, z);

                    if (!structurebb.isVecInside(new BlockPos(j, k, l)))
                    {
                        break;
                    }

                    ++this.villagersSpawned;

                    EntityVillagerKnight entityvillager = new EntityVillagerKnight(worldIn);
                    entityvillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                    entityvillager.setSpawnPoint((double)j + 0.5D, (double)k, (double)l + 0.5D);
                    entityvillager.setProfession(null);
                    
                    entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
                    worldIn.spawnEntity(entityvillager);
                }
            }
        }
    
}
