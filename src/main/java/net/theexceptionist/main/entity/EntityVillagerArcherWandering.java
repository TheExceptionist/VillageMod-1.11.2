package net.theexceptionist.main.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.theexceptionist.main.entity.ai.EntityAIAttackWithBow;

import com.google.common.base.Predicate;

public class EntityVillagerArcherWandering extends EntityVillagerArcher implements IRangedAttackMob{
	private int armour = 2, attack = 8;
	
	public EntityVillagerArcherWandering(World worldIn) {
		super(worldIn);
		this.title  = "Hunter - "+this.getRandomName();
		this.setCustomNameTag(title);
	}
	
	protected void initEntityAI()
    {
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOW));
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
		//this.areAdditionalTasksSet = true;
		
		this.tasks.addTask(0, new EntityAISwimming(this));
		//if(this.world.rand.nextInt(2) == 0){
			 this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 60, 10.0F));
		//	}else{
		//		this.tasks.addTask(2, new EntityAIAttackWithBow(this, 1.0D, 60, 10.0F));
		//	}
        //this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
    //    this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
    //    this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
      //  this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        //this.tasks.addTask(5, new EntityAILookAtVillager(this));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
       // this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
        //this.areAdditionalTasksSet = true;
        
        
       // this.targetTasks.addTask(1, new EntityAIGuardPost(this, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
            }
        }));
        
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 10, false, true, new Predicate<Entity>()
                {
                    public boolean apply(@Nullable Entity p_apply_1_)
                    {
                    	//System.out.println(p_apply_1_);
                    	if(!(p_apply_1_ instanceof AbstractHorse)){
                    	//	System.out.println(p_apply_1_+" Working");
                    		return true;
                    	}else{
                    		return false;
                    	}
                    }
                }));
    }
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.setProfession(1);
        
//        EntityHorse horse = new EntityHorse(this.world);
//        horse.setPosition(this.posX, this.posY, this.posZ);
//        this.world.spawnEntity(horse);
//        this.startRiding(horse);
    }

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target,
			float distanceFactor) {
		//if(!this.world.isRemote){
		//System.out.println("Attacking");
		EntityArrow entityarrow = this.getArrow(distanceFactor);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        entityarrow.setThrowableHeading(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        //System.out.println(entityarrow);
        //if(!this.world.isRemote){
        	this.world.spawnEntity(entityarrow);
      //  }
		//}
	}
	
	 @Override
	 protected void updateAITasks()
	    {
		
		 super.updateAITasks();
		// System.out.println(this.getAttackTarget()+" - "+this);
		 if(this.getAttackTarget() instanceof EntityVillager){
			 this.setAttackTarget(null);
		//	 System.out.println(this.getAttackTarget()+" - "+this);
		 }
	    }
	
	protected EntityArrow getArrow(float p_190726_1_)
    {
			EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
	        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
	        return entitytippedarrow;
		
    }
	
	@Override
	protected boolean canDespawn(){
		return true;
	};
}
