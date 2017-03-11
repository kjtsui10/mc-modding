package com.puresalvation.test;

import java.util.Set;

import com.google.common.collect.Sets;
import com.puresalvation.test.init.ModCrafting;
import com.puresalvation.test.init.ModItems;
import com.puresalvation.test.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/*
 * Most of the initializations will happen here
 */
@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class TestMod {

	@Instance
	public static TestMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	// Creative Tab 
	public static CreativeTabs modTab = new ModTab("tab_mod"); // adds a creative tab for our mod
	
	// Tools related
	public static ToolMaterial SAVAGE_MATERIAL = EnumHelper.addToolMaterial("Savage", 3, 200, 64.0F, 10.0F, 15); // New Tool Material: Savage
	public static ToolMaterial BASIC_MULTITOOL_MATERIAL = EnumHelper.addToolMaterial("Basic Multi-Tool", 3, 2800, 10.0F, 6.0F, 10); // New Tool Material: Basic Multi-Tool Material
	public static ToolMaterial MULTITOOL_MATERIAL = EnumHelper.addToolMaterial("Multi-Tool", 3, 85000, 64.0F, 20.0F, 15); // New Tool Material: Multi-Tool
	public static final Set<Block> MY_EFFECTIVE_ON_1 = Sets.newHashSet(new Block[] {Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE});
	public static final Set<Block> MULTITOOL_EFFECTIVE_ON = Sets.newHashSet(new Block[] 
			{Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, 
			 Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, 
			 Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, 
			 Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, 
			 Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE,
			 Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, 
			 Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH,
			 Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, 
			 Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE, Blocks.LEAVES});

	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		System.out.println("Pre Init");
		
		ModItems.init();
		ModItems.register();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		System.out.println("Init");
		
		// Using Polymorphism, will appropriately call init for both client and server side
		proxy.init();
		
		// Register the crafting recipes
		ModCrafting.register();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		System.out.println("Post Init");
	}
}
