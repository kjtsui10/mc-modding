package com.puresalvation.test.init;

import com.puresalvation.test.Reference;
import com.puresalvation.test.TestMod;
import com.puresalvation.test.items.ItemBasicMultiTool;
import com.puresalvation.test.items.ItemFinder;
import com.puresalvation.test.items.ItemMeatball;
import com.puresalvation.test.items.ItemMultiTool;
import com.puresalvation.test.items.ItemShuriken;
import com.puresalvation.test.items.ItemSuperpick;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item meatball;
	public static Item superpick;
	public static Item multitool;
	public static Item basicmultitool;
	public static Item shuriken;
	public static Item finder;
	
	/*
	 * Initialize the items
	 */
	public static void init() 
	{
		meatball = new ItemMeatball();
		superpick = new ItemSuperpick(TestMod.SAVAGE_MATERIAL, TestMod.MY_EFFECTIVE_ON_1);
		multitool = new ItemMultiTool(TestMod.MULTITOOL_MATERIAL, TestMod.MULTITOOL_EFFECTIVE_ON);
		basicmultitool = new ItemBasicMultiTool(TestMod.BASIC_MULTITOOL_MATERIAL, TestMod.MULTITOOL_EFFECTIVE_ON);
		shuriken = new ItemShuriken();
		//superpick = new ItemSuperpick(EnumHelper.addToolMaterial("SAVAGE", 3, 300, 12.0F, 10.0F, 15), TestMod.MY_EFFECTIVE_ON_1);
		finder = new ItemFinder();
	}
	
	/*
	 * register the items into Minecraft
	 */
	public static void register() 
	{
		GameRegistry.register(meatball);
		GameRegistry.register(superpick);
		GameRegistry.register(multitool);
		GameRegistry.register(basicmultitool);
		GameRegistry.register(shuriken);
		GameRegistry.register(finder);
	}

	/*
	 * register the item's render
	 */
	public static void registerRenders()
	{
		registerRender(meatball);
		registerRender(superpick);
		registerRender(multitool);
		registerRender(basicmultitool);
		registerRender(shuriken);
		registerRender(finder);
	}
	
	/*
	 * Helper method registering the item 
	 */
	private static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
