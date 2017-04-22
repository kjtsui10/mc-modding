package com.puresalvation.test.items;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.puresalvation.test.Reference;
import com.puresalvation.test.TestMod;
import com.puresalvation.test.init.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemIronPouch extends Item {

	// [VARIABLES]
	private final int RANGE_VERY_UNLUCKY = 5;
	private final int RANGE_UNLUCKY = 20;
	private final int RANGE_AVERAGE = 80;
	private final int RANGE_LUCKY = 95;
	private final int RANGE_VERY_LUCKY = 100;
	private final int RANGE_MAX = 100;
	
	// Items.BUCKET, Items.BONE
	public static final List<Item> ITEM_POOL_1 = Arrays.asList(ModItems.exp_elixir_empty, ModItems.meatball);
	public static final List<PoolItem> ITEM_POOL_3 = Arrays.asList(
			new PoolItem(Items.DIAMOND, 10),
			new PoolItem(ModItems.exp_elixir_full, 5));
	
	private Random rand;
	
	
	// [NESTED CLASS]
	
	// Simulate a "tuple" in java so I can store the item and amount as a reward in an item pool (e.g. a reward of 8 diamonds) 
	private static class PoolItem {
		public Item item;
		public int amt;
		
		public PoolItem(Item item, int amount)
		{
			this.item = item;
			this.amt = amount;
		}
	}
	
	
	// [CONSTRUCTOR]
	public ItemIronPouch() 
	{
		setUnlocalizedName(Reference.TestItems.IRONPOUCH.getUnlocalizedName());
		setRegistryName(Reference.TestItems.IRONPOUCH.getRegistryName());
		setCreativeTab(TestMod.modTab);
		
		rand = new Random();
	}
	
	// [METHODS]
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		ItemStack pouches = playerIn.getHeldItem(handIn);
		
		worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_CLOTH_BREAK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
		
		if (!worldIn.isRemote) // On the Server
		{
			return new ActionResult(EnumActionResult.SUCCESS, this.turnPouchIntoItem(pouches, playerIn));
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	/* Custom Helper Method
	 * 
	 * When the player opens the pouch, get a random item
	 */
	protected ItemStack turnPouchIntoItem(ItemStack pouches, EntityPlayer player)
	{
		ItemStack reward = getRandomItemFromPool();
		
		pouches.shrink(1);
		
		if (pouches.isEmpty())
		{
			return reward;
		}
		else
		{
			if (!player.inventory.addItemStackToInventory(reward))
			{
				player.dropItem(reward, false);
			}
			return pouches;
		}
	}
	
	/* Custom Helper Method
	 * 
	 * Get a random item from the item pools for this pouch
	 */
	protected ItemStack getRandomItemFromPool()
	{
		int randNdx, num = rand.nextInt(RANGE_MAX);
		PoolItem pItem;
		ItemStack reward = new ItemStack(Items.AIR);
		
		if (num < RANGE_VERY_UNLUCKY) // Very Unlucky item pool
		{
			randNdx = rand.nextInt(ITEM_POOL_1.size());
			reward = new ItemStack(ITEM_POOL_1.get(randNdx), 1);
		}
		else if (RANGE_VERY_UNLUCKY <= num && num < RANGE_UNLUCKY) // Unlucky item pool
		{
			randNdx = rand.nextInt(ITEM_POOL_1.size());
			reward = new ItemStack(ITEM_POOL_1.get(randNdx), 1);
		}
		else if (RANGE_UNLUCKY <= num && num < RANGE_AVERAGE) // Average item pool
		{
			randNdx = rand.nextInt(ITEM_POOL_3.size()); 	// random index in the item pool
			pItem = ITEM_POOL_3.get(randNdx); 				// get the pool item tuple
			reward = new ItemStack(pItem.item, pItem.amt); 	// create an itemstack based on the tuple's values
		}
		else if (RANGE_AVERAGE <= num && num < RANGE_LUCKY) // Lucky item pool
		{
			randNdx = rand.nextInt(ITEM_POOL_1.size());
			reward = new ItemStack(ITEM_POOL_1.get(randNdx), 1);
		}
		else if (RANGE_LUCKY <= num && num < RANGE_VERY_LUCKY) // Very Lucky item pool
		{
			randNdx = rand.nextInt(ITEM_POOL_1.size());
			reward = new ItemStack(ITEM_POOL_1.get(randNdx), 1);
		}
		
		return reward;
	}
}
