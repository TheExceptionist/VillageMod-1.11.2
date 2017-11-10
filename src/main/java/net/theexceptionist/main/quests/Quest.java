package net.theexceptionist.main.quests;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class Quest {
	private String name;
	private Entity target;
	private int amount;
	private boolean completed;
	private ItemStack reward;
	public static ArrayList<Quest> quests = new ArrayList<Quest>();
	
	public static Quest witchHunt = new Quest("Witch Hunt");
	public static Quest zombieHunt = new Quest("Zombie Hunt");
	public static Quest illagerHunt = new Quest("Skeleton Hunt");
	
	public Quest(String name){
		this.name = name;
	}
	
	public Quest(String name, Entity target){
		this.name = name;
		this.target = target;
	}
	
	public Quest(String name, Entity target, int amount){
		this.name = name;
		this.target = target;
		this.amount = amount;
	}


	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}

	public ItemStack getReward() {
		return reward;
	}
	
	public void setReward(ItemStack i) {
		this.reward = i;
	}
	
}
