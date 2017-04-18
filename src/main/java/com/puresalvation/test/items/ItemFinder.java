package com.puresalvation.test.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.puresalvation.test.Reference;
import com.puresalvation.test.TestMod;
import com.puresalvation.test.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFinder extends Item 
{
	// [INSTANCE VARIABLES]
	private final int RANGE = 10;
	private final int HEIGHT_RANGE = 5;
	private World world;
	
	// [CONSTRUCTOR] 
	public ItemFinder()
	{
		setUnlocalizedName(Reference.TestItems.FINDER.getUnlocalizedName());
		setRegistryName(Reference.TestItems.FINDER.getRegistryName());
		setCreativeTab(TestMod.modTab);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		BlockPos ppos = playerIn.getPosition();
		BlockPos.MutableBlockPos tmpPos = new BlockPos.MutableBlockPos();
		ArrayList<BlockPos> positions = new ArrayList<BlockPos>(); 
		double posX, posY, posZ; // will used to get the position right in front of the player
		double d0, d1, d2; // will be used for randomized numbers
		Random rand = new Random();
		
		// Store position of the player
		int px = ppos.getX(), py = ppos.getY(), pz = ppos.getZ();
		
		
		for (int x = -RANGE; x < RANGE; x++)
		{
			for (int y = -HEIGHT_RANGE; y < HEIGHT_RANGE; y++)
			{
				for (int z = -RANGE; z < RANGE; z++)
				{
					// Skip this iteration if y-position is above/below world height limits
					if (py + y <= 0 || py + y >= worldIn.getActualHeight())
						continue;
					
					// Get the block at the current block position
					tmpPos.setPos(x + px, y + py, z + pz); 
					Block block = worldIn.getBlockState(tmpPos).getBlock();
					
					if (block == Blocks.DIAMOND_ORE) // If current block is diamond ore
					{
						// Store this in the arraylist
						positions.add(new BlockPos(x + px, y + py, z + pz));
					}
				}
			}
		}
		
		System.out.println("*** FOUND " + positions.size() + " DIAMOND ORE(S) ***");
		
		if (positions.size() > 0)
		{
			// Using Vector Math, get the position right in front of the player
			posX = (playerIn.getLookVec().xCoord * 0.8) + playerIn.posX;
			posY = (playerIn.getLookVec().yCoord * 0.5) + playerIn.posY + 1.5; 
			posZ = (playerIn.getLookVec().zCoord * 0.8) + playerIn.posZ;
			
			// Signal that the Finder found something! (Sounds, Particles, etc.)
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			for (BlockPos bp : positions) // spawn as many particles as diamond ores found
			{
				// Spawn a particle in front of the player's eyes
				worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, 
						rand.nextGaussian() * 0.1D + posX, 
						rand.nextGaussian() * 0.05D + posY, 
						rand.nextGaussian() * 0.1D + posZ, 
						rand.nextGaussian() * 1.8D, 
						rand.nextGaussian() * 1.2D + 3.0D, 
						rand.nextGaussian() * 1.8D, 
						new int[0]);
			}
			
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
