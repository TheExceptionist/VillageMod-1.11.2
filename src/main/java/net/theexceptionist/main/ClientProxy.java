package net.theexceptionist.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.common.MinecraftForge;
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
import net.theexceptionist.main.entity.RenderMerchantGuard;
import net.theexceptionist.main.entity.RenderVillagerAlchemist;
import net.theexceptionist.main.entity.RenderVillagerArcher;
import net.theexceptionist.main.entity.RenderVillagerArrow;
import net.theexceptionist.main.entity.RenderVillagerCaptain;
import net.theexceptionist.main.entity.RenderVillagerGuardian;
import net.theexceptionist.main.entity.RenderVillagerKnight;
import net.theexceptionist.main.entity.RenderVillagerMage;
import net.theexceptionist.main.entity.RenderVillagerMerchant;
import net.theexceptionist.main.entity.RenderVillagerSoldier;
//import net.theexceptionist.events.VillageEventHandler;
import net.theexceptionist.main.entity.events.EventHandler;
import net.theexceptionist.main.entity.events.EventOverrideVillagers;
//import net.theexceptionist.main.entity.events.EventPlayerLogOut;

public class ClientProxy extends CommonProxy{
	public void registerRenderInformation(){
		
	}
	
	public void registerRenderers(){
		
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        
        renderManager.entityRenderMap.put(EntityVillagerSoldier.class, new RenderVillagerSoldier(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerSoldierWandering.class, new RenderVillagerSoldier(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerCaptain.class, new RenderVillagerCaptain(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerArcher.class, new RenderVillagerArcher(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerArcherWandering.class, new RenderVillagerArcher(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerKnight.class, new RenderVillagerKnight(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerHorse.class, new RenderHorse(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerMage.class, new RenderVillagerMage(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerGuardian.class, new RenderVillagerGuardian(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerAlchemist.class, new RenderVillagerAlchemist(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerArrow.class, new RenderVillagerArrow(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerMerchant.class, new RenderVillagerMerchant(renderManager));
        renderManager.entityRenderMap.put(EntityMerchantGuard.class, new RenderMerchantGuard(renderManager));
    
     //   RenderingRegistry.registerEntityRenderingHandler(EntityVillagerArrow.class, new RenderArrow());
        
        //float shadowSize = 0.5F;
		//RenderingRegistry.registerEntityRenderingHandler(EntityVillagerSoldier.class, new RenderVillagerSoldier(null));
		//MOBS
		//RenderingRegistry.registerEntityRenderingHandler(EntityHuman.class, new RenderHuman(new ModelBiped(), shadowSize));
		
	}
	
	public void initEvents(){
		MinecraftForge.EVENT_BUS.register(new EventHandler());	
		MinecraftForge.TERRAIN_GEN_BUS.register(new EventOverrideVillagers());	
		//VillageEventHandler handler = new VillageEventHandler();
		//MinecraftForge.EVENT_BUS.register(handler);
		//System.out.println("Init");
		//FMLCommonHandler.instance().bus().register(new EventPlayerLogOut());
	}
}
