package com.puresalvation.test.proxy;

import com.puresalvation.test.entity.EntityShuriken;
import com.puresalvation.test.init.ModItems;
import com.puresalvation.test.render.RenderShuriken;

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
