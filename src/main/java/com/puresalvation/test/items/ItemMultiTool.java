package com.puresalvation.test.items;

import java.util.Set;

import com.puresalvation.test.Reference;
import com.puresalvation.test.TestMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemMultiTool extends ItemTool {

	public ItemMultiTool(ToolMaterial materialIn, Set<Block> effectiveBlocksIn) 
	{
		super(materialIn, effectiveBlocksIn);
		setUnlocalizedName(Reference.TestItems.MULTITOOL.getUnlocalizedName());
		setRegistryName(Reference.TestItems.MULTITOOL.getRegistryName());
		setCreativeTab(TestMod.modTab);
	}
	
	
	/*
	 * This tool can harvest it all! Doesn't mean it can harvest efficiently (see getStrVsBlock for harvesting speed)
	 */
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return true;
	}
	
	
	/*
	 * Strength of the tool material against the block
	 */
	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
        Material material = state.getMaterial();
        
        // Special case: make obsidian even EASIER to break (cuz the hardness is set so stinking high)
        if (state.getBlock() == Blocks.OBSIDIAN)
        {
        	return this.efficiencyOnProperMaterial * 8;
        }
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
	}

}
