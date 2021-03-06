package com.puresalvation.test;


/*
 * Where you can reference whatever data you need from wherever (like a global class if you will)
 */
public class Reference {
	
	// NOTE: Changes made here also need to be made in resources/mcmod.info
	public static final String MOD_ID = "pstest";
	public static final String NAME = "Test Mod";
	public static final String VERSION = "1.0";
	public static final String ACCEPTED_VERSIONS = "[1.11.2]";
	
	public static final String CLIENT_PROXY_CLASS = "com.puresalvation.test.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.puresalvation.test.proxy.ServerProxy";
	
	public static enum TestItems 
	{
		/* Naming Convention
		 * 
		 * unlocalizedName = name of the ModItems variable (e.g. meatball)
		 * registryName = name of the item's class (e.g. ItemMeatball)
		 */

		BASICMULTITOOL("basicmultitool", "ItemBasicMultiTool"),
		EXP_ELIXIR_EMPTY("exp_elixir_empty", "ItemExpElixirEmpty"),
		EXP_ELIXIR_FULL("exp_elixir_full", "ItemExpElixirFull"),
		FINDER("finder", "ItemFinder"),
		MEATBALL("meatball", "ItemMeatball"),
		MULTITOOL("multitool", "ItemMultiTool"),
		SHURIKEN("shuriken", "ItemShuriken"),
		SUPERPICK("superpick", "ItemSuperpick"),
		
		IRONPOUCH("ironpouch", "ItemIronPouch");
		
		
		private String unlocalizedName;
		private String registryName;
		
		TestItems(String unlocalizedName, String registryName)
		{
			this.unlocalizedName = unlocalizedName;
			this.registryName = registryName;
		}
		
		public String getRegistryName() 
		{
			return this.registryName;
		}
		
		public String getUnlocalizedName() 
		{
			return this.unlocalizedName;
		}
	}
}
