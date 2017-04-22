package com.puresalvation.test.init;

import com.puresalvation.test.Reference;
import com.puresalvation.test.TestMod;
import com.puresalvation.test.items.ItemBasicMultiTool;
import com.puresalvation.test.items.ItemExpElixirEmpty;
import com.puresalvation.test.items.ItemExpElixirFull;
import com.puresalvation.test.items.ItemFinder;
import com.puresalvation.test.items.ItemIronPouch;
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
	
	public static Item basicmultitool;
	public static Item exp_elixir_empty;
	public static Item exp_elixir_full;
	public static Item finder;
	public static Item meatball;
	public static Item multitool;
	public static Item shuriken;
	public static Item superpick;

	public static Item ironpouch;

	
	/*
	 * Initialize the items
	 */
	public static void init() 
	{
		basicmultitool = new ItemBasicMultiTool(TestMod.BASIC_MULTITOOL_MATERIAL, TestMod.MULTITOOL_EFFECTIVE_ON);
		exp_elixir_empty = new ItemExpElixirEmpty();
		exp_elixir_full = new ItemExpElixirFull();
		finder = new ItemFinder();
		meatball = new ItemMeatball();
		multitool = new ItemMultiTool(TestMod.MULTITOOL_MATERIAL, TestMod.MULTITOOL_EFFECTIVE_ON);
		shuriken = new ItemShuriken();
		superpick = new ItemSuperpick(TestMod.SAVAGE_MATERIAL, TestMod.MY_EFFECTIVE_ON_1);
		//superpick = new ItemSuperpick(EnumHelper.addToolMaterial("SAVAGE", 3, 300, 12.0F, 10.0F, 15), TestMod.MY_EFFECTIVE_ON_1);
		
		ironpouch = new ItemIronPouch();
	}
	
	/*
	 * register the items into Minecraft
	 */
	public static void register() 
	{
		GameRegistry.register(basicmultitool);
		GameRegistry.register(exp_elixir_empty);
		GameRegistry.register(exp_elixir_full);
		GameRegistry.register(finder);
		GameRegistry.register(meatball);
		GameRegistry.register(multitool);
		GameRegistry.register(shuriken);
		GameRegistry.register(superpick);
		
		GameRegistry.register(ironpouch);
	}

	/*
	 * register the item's render
	 */
	public static void registerRenders()
	{
		registerRender(basicmultitool);
		registerRender(exp_elixir_empty);
		registerRender(exp_elixir_full);
		registerRender(finder);
		registerRender(meatball);
		registerRender(multitool);
		registerRender(shuriken);
		registerRender(superpick);
		
		registerRender(ironpouch);
	}
	
	/*
	 * Helper method registering the item 
	 */
	private static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
