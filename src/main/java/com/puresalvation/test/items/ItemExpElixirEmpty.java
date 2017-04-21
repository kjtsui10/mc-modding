package com.puresalvation.test.items;

import com.puresalvation.test.Reference;
import com.puresalvation.test.TestMod;
import com.puresalvation.test.init.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemExpElixirEmpty extends Item {
	
	// [VARIABLES]
	private final int EXP_AMT = 100;
	
	// [CONSTRUCTOR]
	public ItemExpElixirEmpty() 
	{
		setUnlocalizedName(Reference.TestItems.EXP_ELIXIR_EMPTY.getUnlocalizedName());
		setRegistryName(Reference.TestItems.EXP_ELIXIR_EMPTY.getRegistryName());
		setCreativeTab(TestMod.modTab);
	}
	
	// [METHODS]
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		ItemStack elixirs = playerIn.getHeldItem(handIn);
		
		// Create a new full elixir if the player has enough exp to give
		if (playerIn.experienceTotal > EXP_AMT)
		{			
			worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			removeExperience(playerIn, EXP_AMT);
			return new ActionResult(EnumActionResult.SUCCESS, this.turnEmptyElixirIntoFull(elixirs, playerIn, new ItemStack(ModItems.exp_elixir_full)));
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	
	
	/* Customer Helper Method
	 * 
	 * Removes EXP from the player and updates the GUI to reflect that
	 */
	protected void removeExperience(EntityPlayer player, int takeawayAmount)
	{
		int amount = 0;
		
		// Make sure the take away amount isn't more than the player's total experience
		if (player.experienceTotal - takeawayAmount > 0)
		{
			amount = player.experienceTotal - takeawayAmount;
		}
		
		player.experience = 0.0F;
		player.experienceLevel = 0;
		player.experienceTotal = 0;
		player.addExperience(amount);
	}
	
	
	
	/* Custom Helper Method
	 * 
	 * Turn the empty elixir into a full elixir 
	 */
	protected ItemStack turnEmptyElixirIntoFull(ItemStack emptyElixirs, EntityPlayer player, ItemStack fullElixir)
	{
		emptyElixirs.shrink(1);
		
		if (emptyElixirs.isEmpty())
		{
			return fullElixir;
		}
		else
		{
			if(!player.inventory.addItemStackToInventory(fullElixir))
			{
				player.dropItem(fullElixir, false);
			}
			return emptyElixirs;
		}
	}
	
}
