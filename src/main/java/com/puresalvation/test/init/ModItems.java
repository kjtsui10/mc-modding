package com.puresalvation.test.init;

import com.puresalvation.test.Reference;
import com.puresalvation.test.TestMod;
import com.puresalvation.test.items.ItemMeatball;
import com.puresalvation.test.items.ItemSuperpick;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item meatball;
	public static Item superpick;
	
	/*
	 * Initialize the items
	 */
	public static void init() 
	{
		meatball = new ItemMeatball();
		superpick = new ItemSuperpick(TestMod.SAVAGE_MATERIAL, TestMod.MY_EFFECTIVE_ON_1);
		//superpick = new ItemSuperpick(EnumHelper.addToolMaterial("SAVAGE", 3, 300, 12.0F, 10.0F, 15), TestMod.MY_EFFECTIVE_ON_1);
	}
	
	/*
	 * register the items into Minecraft
	 */
	public static void register() 
	{
		GameRegistry.register(meatball);
		GameRegistry.register(superpick);
	}

	/*
	 * register the item's render
	 */
	public static void registerRenders()
	{
		registerRender(meatball);
		registerRender(superpick);
	}
	
	/*
	 * Helper method registering the item 
	 */
	private static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
