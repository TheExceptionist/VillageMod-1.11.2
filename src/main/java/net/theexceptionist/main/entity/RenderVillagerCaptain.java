package net.theexceptionist.main.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;
import net.theexceptionist.main.Ref;
//private static final ResourceLocation VILLAGER_CAPTAIN_TEXTURE = 
public class RenderVillagerCaptain extends RenderLiving<EntityVillager>
{
    /*private static final ResourceLocation VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/villager.png");
    private static final ResourceLocation FARMER_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/farmer.png");
    private static final ResourceLocation LIBRARIAN_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/librarian.png");
    private static final ResourceLocation PRIEST_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/priest.png");
    private static final ResourceLocation SMITH_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/smith.png");
    private static final ResourceLocation BUTCHER_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/butcher.png");
*/
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTURE = new ResourceLocation(Ref.MODID,"textures/entity/villager/captain.png");
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREA = new ResourceLocation(Ref.MODID,"textures/entity/villager/captain_a.png");
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREB = new ResourceLocation(Ref.MODID,"textures/entity/villager/captain_b.png");
	
	
	
    public RenderVillagerCaptain (RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelVillagerSoldier(0.0F), 0.5F);
        this.addLayer(new LayerCustomHead(this.getMainModel().bipedHead));
        this.addLayer(new LayerHeldItem(this));
    }

    public ModelVillagerSoldier getMainModel()
    {
        return (ModelVillagerSoldier)super.getMainModel();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityVillager entity)
	  {
    	int type = 0;
		if(entity instanceof EntityVillagerCaptain){
			EntityVillagerCaptain soldier = ((EntityVillagerCaptain)entity);
			type = soldier.getTypeCaptain();
			String name = soldier.getUsename();
			soldier.setUsename(name);
    		soldier.initSpecialAI();//.setArmor();
    		soldier.applyEntityAttributesAgain();
		}
	
		if(type == 0){
			return VILLAGER_SOLDIER_TEXTURE;
		}else if(type == 1){
			//Ser
			return VILLAGER_SOLDIER_TEXTUREA;
		}else if(type == 2){
			//Guar
			return VILLAGER_SOLDIER_TEXTUREB;
		}else {
			return VILLAGER_SOLDIER_TEXTURE;
		}
    }
        

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityVillager entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.9375F;

        if (entitylivingbaseIn.getGrowingAge() < 0)
        {
            f = (float)((double)f * 0.5D);
            this.shadowSize = 0.25F;
        }
        else
        {
            this.shadowSize = 0.5F;
        }

        GlStateManager.scale(f, f, f);
    }
}
