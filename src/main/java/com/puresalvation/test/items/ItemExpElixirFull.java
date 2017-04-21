package com.puresalvation.test.items;

import com.puresalvation.test.Reference;
import com.puresalvation.test.TestMod;
import com.puresalvation.test.init.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemExpElixirFull extends Item {
	
	// [VARIABLES]
	private final int EXP_AMT = 100;

	// [CONSTRUCTOR]
	public ItemExpElixirFull() 
	{
		setUnlocalizedName(Reference.TestItems.EXP_ELIXIR_FULL.getUnlocalizedName());
		setRegistryName(Reference.TestItems.EXP_ELIXIR_FULL.getRegistryName());
		setCreativeTab(TestMod.modTab);
	}
	
	// [METHODS]
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		ItemStack elixirs = playerIn.getHeldItem(handIn);
		
		// Add exp to the player when they "drink" the elixir
		playerIn.addExperience(EXP_AMT);
		worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
		
		return new ActionResult(EnumActionResult.SUCCESS, this.turnFullElixirIntoEmpty(elixirs, playerIn, new ItemStack(ModItems.exp_elixir_empty)));
		//return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	
	/* Custom Helper Method
	 * 
	 * Turn the empty elixir into a full elixir 
	 */
	protected ItemStack turnFullElixirIntoEmpty(ItemStack fullElixirs, EntityPlayer player, ItemStack emptyElixir)
	{
		fullElixirs.shrink(1);
		
		if (fullElixirs.isEmpty())
		{
			return emptyElixir;
		}
		else
		{
			if(!player.inventory.addItemStackToInventory(emptyElixir))
			{
				player.dropItem(emptyElixir, false);
			}
			return fullElixirs;
		}
	}
	
}
