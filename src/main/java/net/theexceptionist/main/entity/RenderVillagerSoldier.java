package net.theexceptionist.main.entity;

	import net.minecraft.client.model.ModelIllager;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theexceptionist.main.Ref;

	@SideOnly(Side.CLIENT)
	public class RenderVillagerSoldier extends RenderLiving<EntityVillager>
	{
	    /*private static final ResourceLocation VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/villager.png");
	    private static final ResourceLocation FARMER_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/farmer.png");
	    private static final ResourceLocation LIBRARIAN_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/librarian.png");
	    private static final ResourceLocation PRIEST_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/priest.png");
	    private static final ResourceLocation SMITH_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/smith.png");
	    private static final ResourceLocation BUTCHER_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/butcher.png");
*/
		private static final ResourceLocation VILLAGER_SOLDIER_TEXTURE = new ResourceLocation(Ref.MODID,"textures/entity/villager/soldier.png");
		private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREA = new ResourceLocation(Ref.MODID,"textures/entity/villager/soldier_a.png");
		private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREB = new ResourceLocation(Ref.MODID,"textures/entity/villager/soldier_b.png");
		private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREC = new ResourceLocation(Ref.MODID,"textures/entity/villager/soldier_c.png");
		private static final ResourceLocation VILLAGER_SOLDIER_TEXTURED = new ResourceLocation(Ref.MODID,"textures/entity/villager/soldier_d.png");
		
		
	    public RenderVillagerSoldier(RenderManager renderManagerIn)
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
	    	String name = "";
	    	EntityVillagerSoldier soldier;
	    	if(entity instanceof EntityVillagerSoldier){
	    		soldier = ((EntityVillagerSoldier)entity);
	    		type = soldier.getType();
	    		name = soldier.getUsename();
	    		soldier.setUsename(name);
	    		soldier.initSpecialAI();//.setArmor();
	    		soldier.applyEntityAttributesAgain();
	    		//System.out.println(entity+" 2 "+name+" : "+type);
	    	}
	    	
	    	
	    	
	    	
	    	
	    	if(type == 0){
	    		return VILLAGER_SOLDIER_TEXTURE;
	    	}else if(type == 1){
	    		//Peasant
	    		//System.out.println(entity+" 2 "+name+" : "+type);
	    		return VILLAGER_SOLDIER_TEXTUREA;
	    	}else if(type == 2){
	    		//Milita
	    		return VILLAGER_SOLDIER_TEXTUREB;
	    	}else if(type == 3){
	    		//Mer
	    		return VILLAGER_SOLDIER_TEXTUREC;
	    	}else if(type == 4){
	    		//Man at
	    		return VILLAGER_SOLDIER_TEXTURED;
	    	}else{
	    		return VILLAGER_SOLDIER_TEXTURE;
	    	}
	    	
	    	//String n =
	        
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

