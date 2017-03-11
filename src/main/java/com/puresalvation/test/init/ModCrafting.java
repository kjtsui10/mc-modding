package com.puresalvation.test.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {

	public static void register()
	{
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.multitool), "DGD", "OIO", "OIO", 'D', Blocks.DIAMOND_BLOCK, 'G', Blocks.GOLD_BLOCK, 'O', Blocks.OBSIDIAN, 'I', Items.IRON_INGOT);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.basicmultitool), "IDI", " O ", " O ", 'D', Items.DIAMOND, 'O', Blocks.OBSIDIAN, 'I', Items.IRON_INGOT);
	}
}
