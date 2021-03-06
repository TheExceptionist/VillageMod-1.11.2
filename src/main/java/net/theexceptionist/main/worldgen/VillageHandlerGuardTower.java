package net.theexceptionist.main.worldgen;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class VillageHandlerGuardTower implements IVillageCreationHandler 
	{ 
	public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) 
	{ 
		//System.out.println("Registered");
	return new StructureVillagePieces.PieceWeight(VillageComponentGuardTower.class, 17, MathHelper.getInt(random, 0 + i, 1 + i)); //Play around with these numbers! 
	} 

	public Class getComponentClass() 
	{ 
		return VillageComponentGuardTower.class; 
	} 

	@Override
	public Village buildComponent(PieceWeight villagePiece, Start startPiece,
			List<StructureComponent> pieces, Random random, int p1, int p2,
			int p3, EnumFacing facing, int p5) {
		// TODO Auto-generated method stub
		//System.out.println("Attempting to spawn village barracks");
		return VillageComponentGuardTower.createPiece(startPiece, pieces, random, p1, p2, p3, facing, p5);
	} 
}

