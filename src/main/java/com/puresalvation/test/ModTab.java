package com.puresalvation.test;

import com.puresalvation.test.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModTab extends CreativeTabs {

	public ModTab(String label) 
	{
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		// Set the icon picture in the creative tab
		return new ItemStack(ModItems.meatball);
	}

}
