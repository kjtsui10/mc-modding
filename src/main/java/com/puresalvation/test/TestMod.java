package com.puresalvation.test;

import com.puresalvation.test.init.ModItems;
import com.puresalvation.test.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
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
	
	public static CreativeTabs modTab = new ModTab("tab_mod");
	
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
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		System.out.println("Post Init");
	}
}
