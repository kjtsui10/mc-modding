package com.puresalvation.test.items;

import com.puresalvation.test.Reference;
import com.puresalvation.test.TestMod;
import com.puresalvation.test.entity.EntityShuriken;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemShuriken extends Item {

	// [CONSTRUCTOR]
	public ItemShuriken()
	{
		setUnlocalizedName(Reference.TestItems.SHURIKEN.getUnlocalizedName());
		setRegistryName(Reference.TestItems.SHURIKEN.getRegistryName());
		setCreativeTab(TestMod.modTab);
	}
	

	// [METHODS]
	
	/*
	 * Item is NOT repairable in an anvil
	 */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) 
	{
		return false;
	}
	
	/*
	 * When right-clicked...
	 *    (1) Decrement the stack size (if in Survival Mode)
	 *    (2) Perform any animation and play sound
	 *    (3) Create and Spawn the corresponding entity into the world
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		// Create an ItemStack from the item being held in the Main/Offhand of player (Main/Offhand determined by "handIn") 
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		// (1) Decrement the stack size if NOT in creative mode
		if (!playerIn.capabilities.isCreativeMode)
		{
			itemstack.shrink(1);
		}
		
		// (2) Play the sound
		worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ENDERPEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		// (3) Create and Spawn the shuriken entity into the world
		if (!worldIn.isRemote)
		{
			EntityShuriken shuriken = new EntityShuriken(worldIn, playerIn);
			//EntitySnowball snowball = new EntitySnowball(worldIn, playerIn);
			
			// Set the entity's direction, velocity, inaccuracy, rotation, etc.
			//snowball.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			shuriken.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			
			//worldIn.spawnEntity(snowball); // spawn the entity into the world
			worldIn.spawnEntity(shuriken); // spawn the entity into the world
		}
		
		playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult(EnumActionResult.SUCCESS, itemstack);
		//return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
