package net.theexceptionist.main;

import java.util.Arrays;
import java.util.List;

import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import net.theexceptionist.main.entity.EntityMerchantGuard;
import net.theexceptionist.main.entity.EntityVillagerAlchemist;
import net.theexceptionist.main.entity.EntityVillagerArcher;
import net.theexceptionist.main.entity.EntityVillagerArcherWandering;
import net.theexceptionist.main.entity.EntityVillagerArrow;
import net.theexceptionist.main.entity.EntityVillagerCaptain;
import net.theexceptionist.main.entity.EntityVillagerGuardian;
import net.theexceptionist.main.entity.EntityVillagerHorse;
import net.theexceptionist.main.entity.EntityVillagerKnight;
import net.theexceptionist.main.entity.EntityVillagerMage;
import net.theexceptionist.main.entity.EntityVillagerMerchant;
import net.theexceptionist.main.entity.EntityVillagerSoldier;
import net.theexceptionist.main.entity.EntityVillagerSoldierWandering;
import net.theexceptionist.main.worldgen.VillageComponentAlchemyHut;
import net.theexceptionist.main.worldgen.VillageComponentBarrackSmall;
import net.theexceptionist.main.worldgen.VillageComponentBarracks;
import net.theexceptionist.main.worldgen.VillageComponentFence;
import net.theexceptionist.main.worldgen.VillageComponentGuardTower;
import net.theexceptionist.main.worldgen.VillageComponentSmallHouseWithDoor;
import net.theexceptionist.main.worldgen.VillageComponentStable;
import net.theexceptionist.main.worldgen.VillageComponentVillageFort;
import net.theexceptionist.main.worldgen.VillageComponentWizardTower;
import net.theexceptionist.main.worldgen.VillageHandlerAlchemyHut;
import net.theexceptionist.main.worldgen.VillageHandlerBarracks;
import net.theexceptionist.main.worldgen.VillageHandlerBarracksSmall;
import net.theexceptionist.main.worldgen.VillageHandlerFence;
import net.theexceptionist.main.worldgen.VillageHandlerFort;
import net.theexceptionist.main.worldgen.VillageHandlerGuardTower;
import net.theexceptionist.main.worldgen.VillageHandlerSmallHouseWithDoor;
import net.theexceptionist.main.worldgen.VillageHandlerStable;
import net.theexceptionist.main.worldgen.VillageHandlerWizardTower;

@Mod(modid = Ref.MODID, version = Ref.VERSION, name = Ref.NAME)

//@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Main
{
	@SidedProxy(clientSide = Ref.CLIENT_PROXY, serverSide = Ref.COMMON_PROXY)
	public static CommonProxy proxy;
	public static Main instance;
	 @EventHandler
	    public void preInit(FMLPreInitializationEvent event)
	    {
			/*Field[] fieldList = ChunkProviderOverworld.class.getDeclaredFields();
			for (int i = 0; i < fieldList.length; i++) {
			        System.out.println("[" + i + "] " + fieldList[ i ]);
			}*/
			
			//FMLCommonHandler.instance().exitJava(0, true);
	    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.initEvents();
    	proxy.registerRenderers();
    	instance = this;
    	
    	List<Biome> villageBiomes = Arrays.<Biome>asList(new Biome[] {Biomes.BEACH, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.COLD_BEACH, Biomes.COLD_TAIGA, Biomes.COLD_TAIGA_HILLS, Biomes.DEEP_OCEAN,
    		Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_EDGE, Biomes.EXTREME_HILLS_WITH_TREES, Biomes.FOREST, Biomes.FOREST_HILLS, Biomes.FROZEN_RIVER, Biomes.FROZEN_OCEAN, Biomes.ICE_MOUNTAINS, Biomes.ICE_PLAINS,
    		Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.MESA, Biomes.MESA_CLEAR_ROCK, Biomes.MESA_ROCK, Biomes.MUSHROOM_ISLAND, Biomes.MUSHROOM_ISLAND_SHORE, Biomes.MUTATED_BIRCH_FOREST, Biomes.PLAINS, Biomes.DESERT, Biomes.SAVANNA, Biomes.TAIGA, Biomes.OCEAN,
    		Biomes.MUTATED_BIRCH_FOREST, Biomes.MUTATED_BIRCH_FOREST_HILLS, Biomes.MUTATED_DESERT, Biomes.MUTATED_EXTREME_HILLS, Biomes.MUTATED_EXTREME_HILLS_WITH_TREES, Biomes.MUTATED_FOREST, Biomes.MUTATED_ICE_FLATS, Biomes.MUTATED_JUNGLE, Biomes.MUTATED_MESA, Biomes.MUTATED_MESA, Biomes.MUTATED_MESA_CLEAR_ROCK,
    		Biomes.MUTATED_MESA_ROCK, Biomes.MUTATED_PLAINS, Biomes.MUTATED_REDWOOD_TAIGA, Biomes.MUTATED_REDWOOD_TAIGA_HILLS, Biomes.MUTATED_ROOFED_FOREST, Biomes.MUTATED_SAVANNA, Biomes.MUTATED_SAVANNA_ROCK, Biomes.MUTATED_SWAMPLAND, Biomes.MUTATED_TAIGA, Biomes.MUTATED_TAIGA_COLD, Biomes.REDWOOD_TAIGA, Biomes.REDWOOD_TAIGA_HILLS, Biomes.RIVER
    		,Biomes.RIVER, Biomes.ROOFED_FOREST, Biomes.SAVANNA_PLATEAU, Biomes.STONE_BEACH, Biomes.SWAMPLAND, Biomes.TAIGA_HILLS});
    	
    	addVillagePiece(VillageComponentBarracks.class, "ViBR"); 
    	addVillageCreationHandler(new VillageHandlerBarracks()); 
    	addVillagePiece(VillageComponentBarrackSmall.class, "ViGb"); 
    	addVillageCreationHandler(new VillageHandlerBarracksSmall()); 
    	addVillagePiece(VillageComponentFence.class, "ViFE"); 
    	addVillageCreationHandler(new VillageHandlerFence()); 
    	addVillagePiece(VillageComponentGuardTower.class, "ViTW"); 
    	addVillageCreationHandler(new VillageHandlerGuardTower()); 
    	addVillagePiece(VillageComponentStable.class, "ViST"); 
    	addVillageCreationHandler(new VillageHandlerStable()); 
    	addVillagePiece(VillageComponentSmallHouseWithDoor.class, "ViSHD"); 
    	addVillageCreationHandler(new VillageHandlerSmallHouseWithDoor()); 
    	
    	addVillagePiece(VillageComponentWizardTower.class, "ViWW"); 
    	addVillageCreationHandler(new VillageHandlerWizardTower()); 
    	addVillagePiece(VillageComponentAlchemyHut.class, "ViAL"); 
    	addVillageCreationHandler(new VillageHandlerAlchemyHut()); 
    	addVillagePiece(VillageComponentVillageFort.class, "ViVF"); 
    	addVillageCreationHandler(new VillageHandlerFort()); 
    	//distance;
        //private final int minTownSeparation;
    	
    	//VillagerProfession SOLDIER = new VillagerProfession("Soldier", "minecraft:textures/entity/zombie_villager/zombie_villager.png");
    	
    	// ReflectionHelper.setPrivateValue(MapGenVillage.class, new WorldType("Test").getChunkGenerator(Minecraft.getMinecraft().world, Minecraft.getMinecraft().world.getWorldInfo().getGeneratorOptions()), 8, 2);
    	//ReflectionHelper.setPrivateValue(MapGenVillage.class, , 1, 3);
    	  //ReflectionHelper.setPrivateValue(classToAccess, instance, value, fieldIndex);
    	//System.out.println(ReflectionHelper.getPrivateValue(MapGenVillage.class, null));
    	//Biomes.
    	for(int i = 0; i < villageBiomes.size(); i++){
    		BiomeManager.addVillageBiome(villageBiomes.get(i), true);
    		//MapGenStruct
    	}
    	
    	createEntity(EntityVillagerSoldier.class, 1513, "villager_soldier", 161425, 1582224);
    	createEntity(EntityVillagerCaptain.class, 1514, "villager_captain", 561425, 5582224);
    	createEntity(EntityVillagerSoldierWandering.class, 1515, "villager_wander", 155525, 1555524);
    	createEntity(EntityVillagerArcher.class, 1516, "villager_archer", 345895, 1985323);
    	createEntity(EntityVillagerArcherWandering.class, 1517, "villager_archer_wander", 945895, 1985323);
    	createEntity(EntityVillagerKnight.class, 1518, "villager_knight", 745895, 9985323);
    	createEntity(EntityVillagerHorse.class, 1519, "villager_horse", 645895, 9985323);
    	createEntity(EntityVillagerMage.class, 1520, "villager_mage", 745895, 8985323);
    	createEntity(EntityVillagerGuardian.class, 1521, "villager_guardian", 645895, 7985323);
    	createEntity(EntityVillagerAlchemist.class, 1522, "villager_alchemist", 545895, 6985323);
    	createEntity(EntityVillagerMerchant.class, 1523, "villager_merchant", 445895, 5985323);
    	createEntity(EntityMerchantGuard.class, 1524, "villager_merchant_guard", 345895, 4985323);
    	
    	EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID, "villager_arrow"), EntityVillagerArrow.class, "entity_villager_arrow", 1, instance,1, 1, false);
    	
    	//ReflectionHelper.findField(, fieldNames)
    	//FMLCommonHandler.instance().exitJava(0, true);
    	//System.exit(0);
    	//VillagerRegistry.
        // some example code
    }
    
    public void postnit(FMLPostInitializationEvent event)
    {

    }
    
    public static void createEntity(Class entityClass, int ID, String entityName, int solidColor, int spotColor){

    	EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID+":"+entityName), entityClass, entityName, ID, instance, 128, 1, true);
    	EntityRegistry.registerEgg(new ResourceLocation(Ref.MODID+":"+entityName),  solidColor, spotColor);
    }

    
    public static void addVillagePiece(Class c, String s) 
    { 
	    try 
	    { 
	    MapGenStructureIO.registerStructureComponent(c, s);
	    } 
	    catch (Exception localException) {} 
	    } 
	
	    public static void addVillageCreationHandler(IVillageCreationHandler v) 
	    { 
	    VillagerRegistry.instance().registerVillageCreationHandler(v); 
	    //VillagerRegistry.instance().
	    
    }

}
