package net.theexceptionist.main.entity;

import java.util.ArrayList;

import javax.annotation.Nullable;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHarvestFarmland;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.theexceptionist.main.entity.ai.EntityAIAttackBackExclude;
import net.theexceptionist.main.quests.Quest;

import com.google.common.base.Predicate;

public class EntityVillagerCaptain extends EntityVillagerSoldier{
	private int attack = 10, armor = 6;
	private boolean areAdditionalTasksSet;
	public boolean wasSpawned = false;
	public BlockPos spawnPos; 
	private int typeCaptain;
	private ArrayList<Quest> quests = new ArrayList<Quest>();

	public EntityVillagerCaptain(World worldIn) {
		this(worldIn, worldIn.rand.nextInt(3));
	}
	
	public EntityVillagerCaptain(World worldIn, int type) {
		super(worldIn);
		this.typeCaptain = type;
		
		if(getTypeCaptain() == 0){
			this.title = "Captain - "+this.getRandomName();
		}else if(getTypeCaptain() == 1){
			this.title = "Sergeant - "+this.getRandomName();
		}else if(getTypeCaptain() == 2){
			this.title = "Royal Guard - "+this.getRandomName();
		}
		this.setCustomNameTag(title);
	}
	
	public void setActiveQuest(Quest q){
		quests.add(q);
	}
	
	/*public void setRandomType(){
		this.setTypeCaptain(this.world.rand.nextInt(3));
		
		/*if(this.world.getBiome(this.village.getCenter()) == Biomes.TAIGA || this.world.getBiome(this.village.getCenter()) == Biomes.MUTATED_TAIGA || this.world.getBiome(this.village.getCenter()) == Biomes.TAIGA_HILLS || this.world.getBiome(this.village.getCenter()) == Biomes.REDWOOD_TAIGA_HILLS || this.world.getBiome(this.village.getCenter()) == Biomes.REDWOOD_TAIGA || this.world.getBiome(this.village.getCenter()) == Biomes.FROZEN_OCEAN ||this.world.getBiome(this.village.getCenter()) == Biomes.FROZEN_OCEAN || this.world.getBiome(this.village.getCenter()) == Biomes.ICE_MOUNTAINS || this.world.getBiome(this.village.getCenter()) == Biomes.MUTATED_ICE_FLATS || this.world.getBiome(this.village.getCenter()) == Biomes.ICE_PLAINS || this.world.getBiome(this.village.getCenter()) == Biomes.MUTATED_TAIGA_COLD || this.world.getBiome(this.village.getCenter()) == Biomes.COLD_TAIGA || this.world.getBiome(this.village.getCenter()) == Biomes.COLD_BEACH || this.world.getBiome(this.village.getCenter()) == Biomes.COLD_TAIGA_HILLS){
			type = 3;
		}*/
		
		/*if(getTypeCaptain() == 0){
			this.title = "Captain";
		}else if(getTypeCaptain() == 1){
			this.title = "Sergeant";
		}else if(getTypeCaptain() == 2){
			this.title = "Royal Guard";
		}
		this.setCustomNameTag(title);
	}*/
	protected void initSpecialAI(){
		if(this.getTypeCaptain() == 0){
			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.DIAMOND_AXE));
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
			}else if(this.getTypeCaptain() == 1){
			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.DIAMOND_SWORD));
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));	
		}else if(this.getTypeCaptain() == 2){
			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.DIAMOND_SWORD));
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));		
		}
	}
	
	protected void initEntityAI()
    {

		
		this.areAdditionalTasksSet = true;
		
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        //this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        //this.tasks.addTask(5, new EntityAILookAtVillager(this));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
       // this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
        //this.areAdditionalTasksSet = true;
        
        
       // this.targetTasks.addTask(1, new EntityAIDefendVillage(this));
        //this.targetTasks.addTask(1, new EntityAIGuardPost(this, true));
        this.targetTasks.addTask(3, new EntityAIAttackBackExclude(this, false, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
            }
        }));
    }
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
		ItemStack item;
		for(int i = 0; i < quests.size(); i++){
			if(quests.get(i).isCompleted()){
				item = quests.get(i).getReward();
			}
		}
       /* ItemStack itemstack = player.getHeldItem(hand);
        boolean flag = itemstack.getItem() == Items.title_TAG;

        if (flag)
        {
            itemstack.interactWithEntity(player, this, hand);
            return true;
        }
        else if (!this.holdingSpawnEggOfClass(itemstack, this.getClass()) && this.isEntityAlive() && !this.isTrading() && !this.isChild())
        {
            if (this.buyingList == null)
            {
                this.populateBuyingList();
            }

            if (hand == EnumHand.MAIN_HAND)
            {
                player.addStat(StatList.TALKED_TO_VILLAGER);
            }

            if (!this.world.isRemote && !this.buyingList.isEmpty())
            {
                this.setCustomer(player);
                player.displayVillagerTradeGui(this);
            }
            else if (this.buyingList.isEmpty())
            {
                return super.processInteract(player, hand);
            }

            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }*/
		return false;
    }

	
	@Override
	public MerchantRecipeList getRecipes(EntityPlayer player)
    {
        if (this.buyingList == null)
        {
            //this.populateBuyingList();
        }

        return null;
    }

	
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        if(this.getTypeCaptain() == 0){
	        	//Captain
		        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.50D);
		        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
		        this.attack = 20;
		        this.armor = 5;
	        }else if(this.getTypeCaptain() == 1){
	        	//Sergeant
	        	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
		        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(140.0D);
		        this.attack = 5;
		        this.armor = 20;
	        }else if(this.getTypeCaptain() == 2){
	        	//Guard
	        	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40D);
		        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
		        this.attack = 10;
		        this.armor = 10;
	        }
	        this.setProfession(1);
	    }
	 
	 protected void applyEntityAttributesAgain()
	    {
	      //  super.applyEntityAttributes();
	        if(this.getTypeCaptain() == 0){
	        	//Captain
		        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.50D);
		        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
		        this.attack = 20;
		        this.armor = 5;
	        }else if(this.getTypeCaptain() == 1){
	        	//Sergeant
	        	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
		        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(140.0D);
		        this.attack = 5;
		        this.armor = 20;
	        }else if(this.getTypeCaptain() == 2){
	        	//Guard
	        	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40D);
		        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
		        this.attack = 10;
		        this.armor = 10;
	        }
	      //  this.setProfession(1);
	    }
	 
	 protected void damageEntity(DamageSource damageSrc, float damageAmount)
	    {
		 super.damageEntity(damageSrc, damageAmount -= armor);
		// System.out.println("Working");
	       /* if (!this.isEntityInvulnerable(damageSrc))
	        {
	            damageAmount = net.minecraftforge.common.ForgeHooks.onLivingHurt(this, damageSrc, damageAmount);
	            if (damageAmount <= 0) return;
	            damageAmount = net.minecraftforge.common.ISpecialArmor.ArmorProperties.applyArmor(this, inventory.armorInventory, damageSrc, damageAmount);
	            if (damageAmount <= 0) return;
	            damageAmount = this.applyPotionDamageCalculations(damageSrc, damageAmount);
	            float f = damageAmount;
	            damageAmount = Math.max(damageAmount - this.getAbsorptionAmount(), 0.0F);
	            this.setAbsorptionAmount(this.getAbsorptionAmount() - (f - damageAmount));

	            if (damageAmount != 0.0F)
	            {
	                this.addExhaustion(damageSrc.getHungerDamage());
	                float f1 = this.getHealth();
	                this.setHealth(this.getHealth() - damageAmount);
	                this.getCombatTracker().trackDamage(damageSrc, f1, damageAmount);

	                if (damageAmount < 3.4028235E37F)
	                {
	                    this.addStat(StatList.DAMAGE_TAKEN, Math.round(damageAmount * 10.0F));
	                }
	                }
	            }*/
	    }
	 
	 /*for(Object task : entity.tasks.taskEntries.toArray())
			{
				 EntityAIBase ai = ((EntityAITaskEntry) task).action;
				 if(ai instanceof EntityAIMoveThroughVillage || ai instanceof EntityAIHurtByTarget || ai instanceof EntityAINearestAttackableTarget)
					 entity.tasks.removeTask(ai);		 
			}*/
	 
	 @Override
	 protected void updateAITasks()
	    {
		 super.updateAITasks();
		 	if(this.getAttackTarget() instanceof EntityVillager){
				 this.setAttackTarget(null);
			 }
		  
		 
		 for(Object task : this.tasks.taskEntries.toArray())
			{
				 EntityAIBase ai = ((EntityAITaskEntry) task).action;
				 if(ai instanceof EntityAIHarvestFarmland)
					 this.tasks.removeTask(ai);	
				 //System.out.println("Removed");
			}
		 
		 if (--this.homeCheckTimer <= 0)
	        {
	            this.homeCheckTimer = 70 + this.rand.nextInt(50);
	            this.village = this.world.getVillageCollection().getNearestVillage(new BlockPos(this), 32);

	            if (this.village == null)
	            {
	                this.detachHome();
	            }
	            else
	            {
	                BlockPos blockpos = this.village.getCenter();
	                this.setHomePosAndDistance(blockpos, (int)((float)this.village.getVillageRadius() * 0.6F));
	            }
	        }
	    }
	

	 public boolean attackEntityAsMob(Entity entityIn)
	    {
		 //System.out.println(this.getHeldItemMainhand().getItem().toString());
	        float f = (float)this.attack ;//+( (ItemSword)this.getHeldItemMainhand().getItem()).getDamageVsEntity();
	        int i = 0;
	        
	      //  System.out.println(this+" "+attack+" Previous Damage");

	        if (entityIn instanceof EntityLivingBase)
	        {
	            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
	            i += EnchantmentHelper.getKnockbackModifier(this);
	            //this.getHeldItemMainhand().get;
	         //   System.out.println(this+" "+attack+" New Damage");
	        }

	        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

	        if (flag)
	        {
	            if (i > 0 && entityIn instanceof EntityLivingBase)
	            {
	                ((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
	                this.motionX *= 0.6D;
	                this.motionZ *= 0.6D;
	            }

	            int j = EnchantmentHelper.getFireAspectModifier(this);
	            j++;
	            if (j > 0)
	            {
	                entityIn.setFire(j * 4);
	            }

	            if (entityIn instanceof EntityPlayer)
	            {
	                EntityPlayer entityplayer = (EntityPlayer)entityIn;
	                ItemStack itemstack = this.getHeldItemMainhand();
	                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

	                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem() instanceof ItemAxe && itemstack1.getItem() == Items.SHIELD)
	                {
	                    float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

	                    if (this.rand.nextFloat() < f1)
	                    {
	                        entityplayer.getCooldownTracker().setCooldown(Items.SHIELD, 100);
	                        this.world.setEntityState(entityplayer, (byte)30);
	                    }
	                }
	            }

	            this.applyEnchantments(this, entityIn);
	        }

	        return flag;
	    }


	public void setSpawnPoint(double d, double k, double e) {
		this.spawnPos = new BlockPos(d, k, e);
	}

	public int getTypeCaptain() {
		return typeCaptain;
	}

	public void setTypeCaptain(int typeCaptain) {
		this.typeCaptain = typeCaptain;
	}

}
