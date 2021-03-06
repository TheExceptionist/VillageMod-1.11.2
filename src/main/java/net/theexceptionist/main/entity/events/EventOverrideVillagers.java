package net.theexceptionist.main.entity.events;

import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class EventOverrideVillagers {
	@SubscribeEvent
	public void initMapGen(InitMapGenEvent event)
	{
		Random rand = new Random();
		if(event.getType().VILLAGE == event.getType()){
			MapGenVillage instance = (MapGenVillage) event.getOriginalGen();
			ReflectionHelper.setPrivateValue(MapGenVillage.class, instance, 9 + rand.nextInt(6), 2);
			ReflectionHelper.setPrivateValue(MapGenVillage.class, instance, 3 + rand.nextInt(5), 3);
			//System.out.println("Values Set");
	    	//event.setResult(value);
			
		}
		
	}
	
	@SubscribeEvent
	public void overrideVillageBlocks(BiomeEvent.GetVillageBlockID event){
		//Biomes.PLAINS, Biomes.DESERT, Biomes.SAVANNA, Biomes.TAIGA
		if (event.getBiome() == Biomes.TAIGA || event.getBiome() == Biomes.MUTATED_TAIGA || event.getBiome() == Biomes.TAIGA_HILLS)
        {
            if (event.getOriginal().getBlock() == Blocks.LOG || event.getOriginal().getBlock() == Blocks.LOG2)
            {
            	event.setReplacement(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
            	event.setResult(Event.Result.DENY);
            }

            if (event.getOriginal().getBlock() == Blocks.PLANKS)
            {
            	event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE));
            	event.setResult(Event.Result.DENY);
            }
            
            if(Blocks.GRASS_PATH== event.getOriginal().getBlock()){
				event.setReplacement(Blocks.COBBLESTONE.getDefaultState());//.withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK));//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
				event.setResult(Event.Result.DENY);
			}

            if (event.getOriginal().getBlock() == Blocks.OAK_STAIRS)
            {
            	event.setReplacement(Blocks.SPRUCE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));
            	event.setResult(Event.Result.DENY);
            }

            if (event.getOriginal().getBlock() == Blocks.OAK_FENCE)
            {
            	event.setReplacement(Blocks.SPRUCE_FENCE.getDefaultState());
            	event.setResult(Event.Result.DENY);
            }
        }
		if(event.getBiome() == Biomes.REDWOOD_TAIGA_HILLS || event.getBiome() == Biomes.REDWOOD_TAIGA || event.getBiome() == Biomes.FROZEN_OCEAN ||event.getBiome() == Biomes.FROZEN_OCEAN || event.getBiome() == Biomes.ICE_MOUNTAINS || event.getBiome() == Biomes.MUTATED_ICE_FLATS || event.getBiome() == Biomes.ICE_PLAINS || event.getBiome() == Biomes.MUTATED_TAIGA_COLD || event.getBiome() == Biomes.COLD_TAIGA || event.getBiome() == Biomes.COLD_BEACH || event.getBiome() == Biomes.COLD_TAIGA_HILLS){
			if(Blocks.LOG == event.getOriginal().getBlock() || event.getOriginal().getBlock() == Blocks.LOG2){
				event.setReplacement(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.COBBLESTONE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE_SMOOTH));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.PLANKS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE));
				event.setResult(Event.Result.DENY);    
			}
			
			if(Blocks.OAK_FENCE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.SPRUCE_FENCE.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_STAIRS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.SPRUCE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));;
				event.setResult(Event.Result.DENY);
			}
			
			/*if(Blocks.STONE_STAIRS == event.getOriginal()){
				//event.setReplacement(Blocks.SPRUCE_FENCE.getDefaultState());
			}*/
			if(Blocks.GRASS_PATH == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.STONEBRICK.getDefaultState());//.withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK));//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.GRAVEL == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.STONEBRICK.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			/*if(Blocks.DOUBLE_STONE_SLAB.getDefaultState() == event.getOriginal()){
				//event.setReplacement(Blocks.SPRUCE_FENCE.getDefaultState());
			}*/
			
			/*if(Blocks.STONEBRICK.getDefaultState() == event.getOriginal()){
				//event.setReplacement(Blocks.SPRUCE_FENCE.getDefaultState());
			}*/
			//event.setResult(Event.Result.DENY);
		}
		if(event.getBiome() == Biomes.BIRCH_FOREST ||event.getBiome() == Biomes.BIRCH_FOREST_HILLS || event.getBiome() == Biomes.FOREST || event.getBiome() == Biomes.FOREST_HILLS || event.getBiome() == Biomes.MUTATED_BIRCH_FOREST || event.getBiome() == Biomes.MUTATED_BIRCH_FOREST || event.getBiome() == Biomes.MUTATED_FOREST){
			if(Blocks.LOG == event.getOriginal().getBlock() || event.getOriginal().getBlock() == Blocks.LOG2){
				event.setReplacement(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH));//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.COBBLESTONE== event.getOriginal().getBlock()){
				event.setReplacement(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH));//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.PLANKS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_FENCE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.BIRCH_FENCE.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_STAIRS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.BIRCH_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));;
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.STONE_STAIRS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.BIRCH_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));;
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.GRASS_PATH == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH));//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.GRAVEL == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.GRASS_PATH.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.DOUBLE_STONE_SLAB == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH));//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.STONEBRICK == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH));
				event.setResult(Event.Result.DENY);
			}
		}
		if(event.getBiome() == Biomes.MUSHROOM_ISLAND || event.getBiome() == Biomes.MUSHROOM_ISLAND_SHORE || event.getBiome() == Biomes.DEEP_OCEAN ||event.getBiome() == Biomes.OCEAN || event.getBiome() == Biomes.RIVER || event.getBiome() == Biomes.SWAMPLAND){
			if(Blocks.LOG == event.getOriginal().getBlock() || event.getOriginal().getBlock() == Blocks.LOG2){
				event.setReplacement(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK));//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.COBBLESTONE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK));//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.PLANKS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_FENCE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.OAK_FENCE.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_STAIRS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));;
				event.setResult(Event.Result.DENY);
			}
			
			/*if(Blocks.STONE_STAIRS == event.getOriginal()){
				//event.setReplacement(Blocks.BIRCH_STAIRS.getDefaultState());
			}*/
			
			if(Blocks.GRASS_PATH == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK));//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
				event.setResult(Event.Result.DENY);
			}
			
			
			if(Blocks.GRAVEL == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.DOUBLE_STONE_SLAB == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK));//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.STONEBRICK == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK));
				event.setResult(Event.Result.DENY);
			}
		}
		if(event.getBiome() == Biomes.EXTREME_HILLS || event.getBiome() == Biomes.EXTREME_HILLS_EDGE || event.getBiome() == Biomes.EXTREME_HILLS_WITH_TREES ||event.getBiome() == Biomes.STONE_BEACH || event.getBiome() == Biomes.MUTATED_EXTREME_HILLS || event.getBiome() == Biomes.MUTATED_EXTREME_HILLS_WITH_TREES){
			if(Blocks.LOG == event.getOriginal().getBlock() || event.getOriginal().getBlock() == Blocks.LOG2){
				event.setReplacement(Blocks.STONE.getDefaultState());//.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK));//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.COBBLESTONE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.STONE.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.PLANKS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_FENCE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.COBBLESTONE_WALL.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_STAIRS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.STONE_STAIRS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.GRASS_PATH == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.GRAVEL.getDefaultState());//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.DOUBLE_STONE_SLAB == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.BRICK_BLOCK.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			/*if(Blocks.STONEBRICK.getDefaultState() == event.getOriginal()){
				//event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK));
	          }*/
		}
		if(event.getBiome() == Biomes.JUNGLE || event.getBiome() == Biomes.JUNGLE_EDGE || event.getBiome() == Biomes.JUNGLE_HILLS ||event.getBiome() == Biomes.MUTATED_JUNGLE || event.getBiome() == Biomes.MUTATED_JUNGLE_EDGE){
			if(Blocks.LOG.getDefaultState() == event.getOriginal().getBlock() || event.getOriginal().getBlock() == Blocks.LOG2){
				event.setReplacement(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE));//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.COBBLESTONE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.MOSSY_COBBLESTONE.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.PLANKS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_FENCE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.JUNGLE_FENCE.getDefaultState());//.withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));;
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_STAIRS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.JUNGLE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));;
				event.setResult(Event.Result.DENY);
			}
			
			/*if(Blocks.STONE_STAIRS == event.getOriginal()){
				//event.setReplacement(Blocks.BRICK_STAIRS.getDefaultState());
			}*/
			if(Blocks.GRASS_PATH == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.MOSSY_COBBLESTONE.getDefaultState());//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.GRAVEL == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.MOSSY_COBBLESTONE.getDefaultState());//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.DOUBLE_STONE_SLAB == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.STONEBRICK == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY));
				event.setResult(Event.Result.DENY);
			}
		}
		if(event.getBiome() == Biomes.MESA || event.getBiome() == Biomes.MESA_CLEAR_ROCK || event.getBiome() == Biomes.MESA_ROCK ||event.getBiome() == Biomes.MUTATED_MESA || event.getBiome() == Biomes.MESA_CLEAR_ROCK || event.getBiome() == Biomes.MESA_ROCK){
			if(Blocks.LOG == event.getOriginal().getBlock() || event.getOriginal().getBlock() == Blocks.LOG2){
				event.setReplacement(Blocks.HARDENED_CLAY.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.COBBLESTONE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.HARDENED_CLAY.getStateFromMeta(1));//.withProperty(BlockHardenedClay.VARIANT, BlockHardenedClay.));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.PLANKS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.HARDENED_CLAY.getStateFromMeta(9));//.withProperty(BlockHardenedClay.VARIANT, BlockHardenedClay.));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_FENCE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.COBBLESTONE_WALL.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_STAIRS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));;
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.STONE_STAIRS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));;
				event.setResult(Event.Result.DENY);
			}
			
			/*if(Blocks.GRAVEL.getDefaultState() == event.getOriginal()){
				//event.setReplacement(Blocks.CLAY.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
	        }*/
			if(Blocks.GRASS_PATH == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.BRICK_BLOCK.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.DOUBLE_STONE_SLAB == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.HARDENED_CLAY.getStateFromMeta(8));//.withProperty(BlockHardenedClay.VARIANT, BlockHardenedClay.));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.STONEBRICK == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.HARDENED_CLAY.getStateFromMeta(12));//.withProperty(BlockHardenedClay.VARIANT, BlockHardenedClay.));
				event.setResult(Event.Result.DENY);
			}
		}
		
		if(event.getBiome() == Biomes.MUTATED_ROOFED_FOREST || event.getBiome() == Biomes.ROOFED_FOREST){
			if(Blocks.LOG == event.getOriginal().getBlock() || event.getOriginal().getBlock() == Blocks.LOG2){
				event.setReplacement(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockNewLog.LOG_AXIS, event.getOriginal().getValue(BlockNewLog.LOG_AXIS)));//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.COBBLESTONE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK));//.withProperty(BlockNewLog.LOG_AXIS, event.getOriginal().getValue(BlockNewLog.LOG_AXIS)));//event.setResult(Event.Result.DENY);
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.PLANKS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK));//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));//event.setResult(Event.Result.DENY);
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_FENCE == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.DARK_OAK_FENCE.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.OAK_STAIRS== event.getOriginal().getBlock()){
				event.setReplacement(Blocks.DARK_OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.STONE_STAIRS == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.DARK_OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.GRAVEL == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.GRASS_PATH.getDefaultState());
				event.setResult(Event.Result.DENY);
			}
			
			if(Blocks.DOUBLE_STONE_SLAB == event.getOriginal().getBlock()){
				event.setReplacement(Blocks.DOUBLE_WOODEN_SLAB.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK));//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
				event.setResult(Event.Result.DENY);
			}
			
			/*if(Blocks.STONEBRICK.getDefaultState() == event.getOriginal()){
				//event.setReplacement(Blocks.HARDENED_CLAY.getStateFromMeta(12));//.withProperty(BlockHardenedClay.VARIANT, BlockHardenedClay.));
				 }*/
		}
		
		if(event.getBiome() == Biomes.MUTATED_SAVANNA || event.getBiome() == Biomes.MUTATED_SAVANNA_ROCK || event.getBiome() == Biomes.SAVANNA || event.getBiome() == Biomes.SAVANNA_PLATEAU){
			if (event.getOriginal().getBlock() == Blocks.LOG || event.getOriginal().getBlock() == Blocks.LOG2)
            {
				event.setReplacement(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS)));
				event.setResult(Event.Result.DENY);
            }

            if (event.getOriginal().getBlock() == Blocks.PLANKS)
            {
            	event.setReplacement( Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA));
            	event.setResult(Event.Result.DENY);
            }

            if (event.getOriginal().getBlock() == Blocks.OAK_STAIRS)
            {
            	event.setReplacement(Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));
            	event.setResult(Event.Result.DENY);
            }

            if (event.getOriginal().getBlock() == Blocks.COBBLESTONE)
            {
            	event.setReplacement(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
            	event.setResult(Event.Result.DENY);
            }

            if (event.getOriginal().getBlock() == Blocks.OAK_FENCE)
            {
            	event.setReplacement( Blocks.ACACIA_FENCE.getDefaultState());
            	event.setResult(Event.Result.DENY);
            }
		}
		
if(event.getBiome() == Biomes.BEACH || event.getBiome() == Biomes.DESERT_HILLS || event.getBiome() == Biomes.DESERT || event.getBiome() == Biomes.MUTATED_DESERT){
	  if (event.getOriginal().getBlock() == Blocks.LOG || event.getOriginal().getBlock() == Blocks.LOG2)
      {
		  event.setReplacement( Blocks.SANDSTONE.getDefaultState());
		  event.setResult(Event.Result.DENY);
      }

      if (event.getOriginal().getBlock() == Blocks.COBBLESTONE)
      {
    	  event.setReplacement( Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.getMetadata()));
    	  event.setResult(Event.Result.DENY);
      }

      if (event.getOriginal().getBlock() == Blocks.PLANKS)
      {
    	  event.setReplacement(  Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()));
    	  event.setResult(Event.Result.DENY);
      }

      if (event.getOriginal().getBlock() == Blocks.OAK_STAIRS)
      {
    	  event.setReplacement( Blocks.SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));
    	  event.setResult(Event.Result.DENY);
      }

      if (event.getOriginal().getBlock() == Blocks.STONE_STAIRS)
      {
    	  event.setReplacement( Blocks.SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));
    	  event.setResult(Event.Result.DENY);
      }

      if (event.getOriginal().getBlock() == Blocks.GRAVEL)
      {
    	  event.setReplacement( Blocks.SANDSTONE.getDefaultState());
    	  event.setResult(Event.Result.DENY);
      }
		}
	}
}
