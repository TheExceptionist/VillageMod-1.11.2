package net.theexceptionist.main.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.theexceptionist.main.entity.EntityVillagerMerchant;

public class EntityAITravelToDest extends EntityAIBase{
	EntityVillagerMerchant merchant;
	private int phase, currentPhase;
	
	public EntityAITravelToDest(EntityVillagerMerchant merchant){
		this.merchant = merchant;
	}
	
	@Override
	public boolean shouldExecute() {
		if(merchant.isTraveling() && merchant.world.isDaytime() && currentPhase < phase){
			phase = ((int) merchant.getDistanceSq(merchant.getDestination().getCenter()))/75;
			currentPhase = 0;
			//System.out.println(phase);
			return true;
		}else{
			return false;
		}
	}
	
	public void startExecuting()
    {
		System.out.println("Traveling"+this.merchant.posX+" "+this.merchant.posY+" "+this.merchant.posZ);
        this.merchant.getNavigator().tryMoveToXYZ(this.merchant.getDestination().getCenter().getX()/phase, this.merchant.getDestination().getCenter().getY(),this.merchant.getDestination().getCenter().getZ()/phase,0.5D);
        currentPhase++;
    }
	
	public void updateTask()
    {
    	//if(this.waitTime > 0){
    		//waitTime--;
    	//System.out.println("Following");
    	//}else
    		if(	this.merchant.getNavigator().noPath()){
    	//	System.out.println("Following New Path");
    			this.merchant.getNavigator().tryMoveToXYZ(this.merchant.getDestination().getCenter().getX()/phase * currentPhase, this.merchant.getDestination().getCenter().getY(),this.merchant.getDestination().getCenter().getZ()/phase * currentPhase,0.5D);
    			currentPhase++;
    		}
    }

}
