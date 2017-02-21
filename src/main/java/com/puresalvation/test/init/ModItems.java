package com.puresalvation.test.init;

import com.puresalvation.test.Reference;
import com.puresalvation.test.items.ItemMeatball;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item meatball;
	
	/*
	 * Initialize the items
	 */
	public static void init() 
	{
		meatball = new ItemMeatball();
	}
	
	/*
	 * register the items into Minecraft
	 */
	public static void register() 
	{
		GameRegistry.register(meatball);
	}

	/*
	 * register the item's render
	 */
	public static void registerRenders()
	{
		registerRender(meatball);
	}
	
	/*
	 * Helper method registering the item 
	 */
	private static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
