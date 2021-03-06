package net.theexceptionist.main.entity;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.theexceptionist.main.entity.ai.EntityAIAttackBackExclude;
import net.theexceptionist.main.entity.ai.EntityAIAttackWithMagic;

import com.google.common.base.Predicate;
//import net.theexceptionist.main.entity.ai.EntityAIRoutedPatrol;

public class EntityVillagerMage extends EntityVillagerSoldier implements IRangedAttackMob{
	private int armour = 2, attack = 8;
	
	public EntityVillagerMage(World worldIn) {
		super(worldIn, false);
		this.title  = "Mage - "+this.getRandomName();
		this.setCustomNameTag(title);
	}
	
	protected void initEntityAI()
    {
		//this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOW));
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
		//this.areAdditionalTasksSet = true;
		
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new  EntityAIAttackWithMagic(this, 1.0D, 20, 15.0F));
        //this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
    //    this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        //this.tasks.addTask(5, new EntityAILookAtVillager(this));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
       // this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
        //this.areAdditionalTasksSet = true;
        
        
       // this.targetTasks.addTask(1, new EntityAIGuardPost(this, true));
        this.targetTasks.addTask(3, new EntityAIAttackBackExclude(this, false, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
            }
        }));
    }
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.setProfession(1);
        
//        EntityHorse horse = new EntityHorse(this.world);
//        setPosition(this.posX, this.posY, this.posZ);
//        this.world.spawnEntity(horse);
//        this.startRiding(horse);
    }
	
	@Override
	 protected void updateAITasks()
	    {
		
		 super.updateAITasks();
		 if(this.getAttackTarget() instanceof EntityVillager){
			 this.setAttackTarget(null);
		 }
	    }

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target,
			float distanceFactor) {
		if(!this.world.isRemote){
			List currentSpawns = this.world.getEntities(EntityVillagerGuardian.class, EntitySelectors.IS_ALIVE);
			if(this.world.rand.nextInt(100) <= 80){
				
					this.world.addWeatherEffect(new EntityVillagerLighting(this.world, target.posX, target.posY, target.posZ, true));
				
				}else {
				boolean cont = true;
				for(int i = 0; i < currentSpawns.size(); i++){
					EntityVillagerGuardian g = (EntityVillagerGuardian) currentSpawns.get(i);
					
					if(g.getMaster() == this){
						cont = false;
					}
				}
				
				if(cont){
				      this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GHAST_SCREAM, SoundCategory.NEUTRAL, 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
				       
					int amount = 1 + this.world.rand.nextInt(4);
					BlockPos spawn = new BlockPos(this.posX, this.posY, this.posZ);
					for(int i =0; i < amount; i++){
						EntityVillagerGuardian entityvillager = new EntityVillagerGuardian(this.world);
			            entityvillager.setLocationAndAngles(spawn.getX() + rand.nextInt(5),spawn.getY()  + rand.nextInt(5),spawn.getZ()  + rand.nextInt(5), 0.0F, 0.0F);
			            //entityvillager.setSpawnPoint(spawn.getX()  + rand.nextInt(5),spawn.getY()  + rand.nextInt(5),spawn.getZ()  + rand.nextInt(5));
			            entityvillager.setProfession(null); 
			            entityvillager.setMaster(this);
			            
			            //entityvillager.finalizeMobSpawn(this.world.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
			            this.world.spawnEntity(entityvillager);
					}
				}else{
				      this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.NEUTRAL, 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
				       
				}
			}
		}
	}
	

}
