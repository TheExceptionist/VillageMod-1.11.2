package net.theexceptionist.main.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIHangAroundFence extends EntityAIBase{
	private EntityVillager villager;
	private World world;
	private BlockPos fence;
	private int timer;
	
	public EntityAIHangAroundFence(EntityVillager villager, World world){
		this.villager = villager;
		this.world = world;
		timer = world.rand.nextInt(100) + 100;
		this.setMutexBits(1);
	}
	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub
		//return false;
	
		for(int x = (int) villager.posX - 4; x < 4 + (int) villager.posX; x++){
			for(int y = (int) villager.posY - 4; y < 4 + (int) villager.posY; y++){
				for(int z = (int) villager.posZ - 4; z < 4 + (int) villager.posZ; z++){
					BlockPos block = new BlockPos(x, y, z);
					
					if(world.getBlockState(block).getBlock() == Blocks.LEAVES|| world.getBlockState(block).getBlock() == Blocks.LEAVES2 || world.getBlockState(block).getBlock() == Blocks.OAK_FENCE || world.getBlockState(block).getBlock() == Blocks.SPRUCE_FENCE || world.getBlockState(block).getBlock() == Blocks.BIRCH_FENCE || world.getBlockState(block).getBlock() == Blocks.COBBLESTONE_WALL || world.getBlockState(block).getBlock() == Blocks.DARK_OAK_FENCE || world.getBlockState(block).getBlock() == Blocks.ACACIA_FENCE || world.getBlockState(block).getBlock() == Blocks.JUNGLE_FENCE){
						
						if(this.villager.getRNG().nextFloat() < 0.02F){
							fence = block;
						
						
						
						/*int i =0;
						while(world.getBlockState(new BlockPos(ladder.getX(), ladder.getY() + i, ladder.getZ())).getBlock() == Blocks.LADDER){
							ladder = new BlockPos(ladder.getX(), ladder.getY() + i, ladder.getZ());
							i++;
						}*/
						return true;
						}else{
							return false;
						}
					}
				}	
			}
		}
		
		return false;
	}


	    /**
	     * Returns whether an in-progress EntityAIBase should continue executing
	     */
	    public boolean continueExecuting()
	    {
	        return this.timer >= 0;
	    }

	    /**
	     * Execute a one shot task or start executing a continuous task
	     */
	    public void startExecuting()
	    {
	        this.timer = 200 + this.villager.getRNG().nextInt(200);
	    }

	    /**
	     * Updates the task
	     */
	    public void updateTask()
	    {
	        --this.timer;
	    }
	
	/*public boolean continueExecuting()
    {
		if(timer > 0){
			return true;
		}
	else if(this.villager.world.rand.nextInt(100) < 20){
        	return false;
        }
        else 
        {
        	timer = 100 + this.world.rand.nextInt(100);
          //  float f = this.theEntity.width + 4.0F;
            return true;//this.theEntity.getDistanceSq(this.doorInfo.getDoorBlockPos()) > (double)(f * f);
        }
    }

    /**155 73 122
     * Execute a one shot task or start executing a continuous task
     */
  /*  public void startExecuting()
    {
    	//System.out.println(villager+" is Using a Ladder at: "+ladder.getX()+" "+ ladder.getY() +" "+ ladder.getZ());
		
        this.villager.getNavigator().tryMoveToXYZ(fence.getX(), fence.getY(), fence.getZ(), 0.3D);
        timer--;
        //System.out.println(this.villager+" is hanging around the fence"+timer);
    }*/
}
