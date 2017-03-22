package com.puresalvation.test.proxy;

import com.puresalvation.test.entity.EntityShuriken;
import com.puresalvation.test.init.ModItems;
import com.puresalvation.test.render.RenderShuriken;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements CommonProxy {

	@Override
	public void preInit() 
	{
		// Called on Client Side
		RenderShuriken.registerRender();
	}

	@Override
	public void init() 
	{	
		// Called on the client side
		ModItems.registerRenders();
	}
}
