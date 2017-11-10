package net.theexceptionist.main.worldgen;

import java.util.List;
import java.util.Random;

import com.jcraft.jorbis.Block;

import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;
import net.theexceptionist.main.entity.EntityVillagerSoldier;

public class VillageComponentBarrackSmall extends StructureVillagePieces.Village
    {
        private boolean isRoofAccessible;
		private int villagersSpawned;

        public VillageComponentBarrackSmall()
        {
        }

        public VillageComponentBarrackSmall(StructureVillagePieces.Start start, int p_i45566_2_, Random rand, StructureBoundingBox p_i45566_4_, EnumFacing facing)
        {
            super(start, p_i45566_2_);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45566_4_;
            this.isRoofAccessible = rand.nextBoolean();
        }

        public static VillageComponentBarrackSmall createPiece(StructureVillagePieces.Start start, List<StructureComponent> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 4, 5, 4, facing);
            return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new VillageComponentBarrackSmall(start, p_175858_7_, rand, structureboundingbox, facing);
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

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 3, 0);
            }
            
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);


            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
            IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
            IBlockState iblockstate4 = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            
            IBlockState iblockstate5 = this.getBiomeSpecificBlockState(Blocks.DOUBLE_STONE_SLAB.getDefaultState());
            IBlockState iblockstate6 = this.getBiomeSpecificBlockState(Blocks.STONEBRICK.getDefaultState());
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 4, iblockstate5, iblockstate5, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 1, 4, iblockstate5, iblockstate5, false);
            this.setBlockState(worldIn, iblockstate5, 1, 0, 0, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 1, 0, 3, iblockstate1, iblockstate1, false);
            
            this.setBlockState(worldIn, iblockstate6, 1, 1, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.BED.getDefaultState(), 1, 1, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.BED.getDefaultState(), 1, 1, 2, structureBoundingBoxIn);
            
            //this.setBlockState(worldIn, Blocks.CHEST.getDefaultState(), 1, 1, 3, structureBoundingBoxIn);
            this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 1, 1, 3, LootTableList.CHESTS_VILLAGE_BLACKSMITH);
            
            this.setBlockState(worldIn, iblockstate6, 1, 1, 4, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 1, 2, 4, iblockstate5, iblockstate5, false);
            this.setBlockState(worldIn, iblockstate5, 2, 0, 0, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 1, 2, 0, 3, iblockstate1, iblockstate1, false);
            this.setBlockState(worldIn, iblockstate5, 2, 0, 4, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 2, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 2, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 2, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 2, 2, 4, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 3, 0, 2, 3, 4, iblockstate5, iblockstate5, false);
            this.setBlockState(worldIn, iblockstate5, 3, 0, 0, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 1, 3, 0, 3, iblockstate1, iblockstate1, false);
            this.setBlockState(worldIn, iblockstate5, 3, 0, 4, structureBoundingBoxIn);
            
           // this.setBlockState(worldIn, Blocks.OAK_DOOR.getDefaultState(), 3, 1, 4, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 3, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 2, 4, structureBoundingBoxIn);
            
            //this.setBlockState(worldIn, iblockstate2, 3, 0, -1, structureBoundingBoxIn);
            
            
            this.setBlockState(worldIn, iblockstate5, 1, 0, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 3, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 2, 4, structureBoundingBoxIn);
          
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 0, 3, 3, 0, iblockstate5, iblockstate5, false);
            this.setBlockState(worldIn, iblockstate6, 3, 3, 4, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 4, 0, 3, 4, 4, iblockstate5, iblockstate5, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 0, 4, 0, 4, iblockstate5, iblockstate5, false);
            this.setBlockState(worldIn, iblockstate5, 4, 1, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 4, 1, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 1, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 4, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 2, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 4, 2, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 4, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 2, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 4, 3, 0, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 3, 0, 4, 3, 4, iblockstate5, iblockstate5, false);
            
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 0, 1, 2, 4, iblockstate5, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            
            /*
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 0, 4, 4, 4, iblockstate3, iblockstate3, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 3, 4, 3, iblockstate1, iblockstate1, false);
            this.setBlockState(worldIn, iblockstate, 0, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 3, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 3, 4, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 4, 3, 3, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 4, 3, 3, 4, iblockstate1, iblockstate1, false);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 4, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 2, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 3, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 3, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 3, 1, 0, structureBoundingBoxIn);

           */ if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR)
            {
                this.setBlockState(worldIn, iblockstate2, 2, 0, -1, structureBoundingBoxIn);

                if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH)
                {
                    this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
                }
            }

            
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
           this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 0, EnumFacing.NORTH);
           this.placeTorch(worldIn, EnumFacing.UP, 4, 4, 2, structureBoundingBoxIn);

         //  this.setBlockState(worldIn, Blocks.OAK_DOOR.getDefaultState(), 2, 1, 0, structureBoundingBoxIn);
          // this.setBlockState(worldIn, Blocks.OAK_DOOR.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
           this.setBlockState(worldIn, iblockstate6, 3, 3, 0, structureBoundingBoxIn);
           
           this.setBlockState(worldIn, iblockstate6, 3, 1, 0, structureBoundingBoxIn);
           this.setBlockState(worldIn, iblockstate6, 3, 2, 0, structureBoundingBoxIn);
           

            for (int j = 0; j < 4; ++j)
            {
                for (int i = 0; i < 4; ++i)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, i, 6, j, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, iblockstate, i, -1, j, structureBoundingBoxIn);
                }
            }

            if(!worldIn.isRemote){
            this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1 + randomIn.nextInt(3));
            }
            
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

                    EntityVillagerSoldier entityvillager = new EntityVillagerSoldier(worldIn, worldIn.rand.nextInt(5));;
                    entityvillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                    entityvillager.setSpawnPoint((double)j + 0.5D, (double)k, (double)l + 0.5D);
                    entityvillager.setProfession(null);
                    
                    entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
                    worldIn.spawnEntity(entityvillager);
                }
            }
        }
    
}
