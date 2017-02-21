package com.puresalvation.test.items;

import com.puresalvation.test.Reference;
import com.puresalvation.test.TestMod;

import net.minecraft.item.Item;

public class ItemMeatball extends Item {

	public ItemMeatball() 
	{
		setUnlocalizedName(Reference.TestItems.MEATBALL.getUnlocalizedName());
		setRegistryName(Reference.TestItems.MEATBALL.getRegistryName());
		setCreativeTab(TestMod.modTab);
	}
}
