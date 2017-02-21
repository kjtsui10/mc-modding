package com.puresalvation.test.proxy;

import com.puresalvation.test.init.ModItems;

public class ClientProxy implements CommonProxy {

	@Override
	public void init() 
	{	
		// Called on the client side
		ModItems.registerRenders();
	}
}
