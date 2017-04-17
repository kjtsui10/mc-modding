package com.puresalvation.test.entity;

import java.util.Arrays;

import com.puresalvation.test.Reference;
import com.puresalvation.test.TestMod;
import com.puresalvation.test.init.ModItems;
import com.puresalvation.test.render.RenderShuriken;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityShuriken extends EntityThrowable {
	
	public static final float GRAVITY = 0.03F;
	public static final Block[] SHURIKEN_BREAKS_THROUGH = 
		{Blocks.TALLGRASS, Blocks.VINE, Blocks.RED_FLOWER, Blocks.YELLOW_FLOWER, Blocks.BROWN_MUSHROOM_BLOCK, Blocks.BROWN_MUSHROOM,
		 Blocks.RED_MUSHROOM_BLOCK, Blocks.RED_MUSHROOM, Blocks.REEDS, Blocks.DOUBLE_PLANT, Blocks.DEADBUSH, Blocks.WHEAT,
		 Blocks.WATERLILY, Blocks.CARROTS, Blocks.POTATOES, Blocks.SNOW_LAYER};
	
	
	
	// [CONSTRUCTORS]
	
	public EntityShuriken(World worldIn) 
	{
		super(worldIn);
	}

	public EntityShuriken(World worldIn, double x, double y, double z) 
	{
		super(worldIn, x, y, z);
	}

	public EntityShuriken(World worldIn, EntityLivingBase throwerIn) 
	{
		super(worldIn, throwerIn);
	}

	
	// [METHODS]
	
	/* 
	 * Register the entity
	 */
	public static void registerEntity()
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "textures/items/shuriken.png"), EntityShuriken.class, 
				Reference.TestItems.SHURIKEN.getUnlocalizedName(), 0, TestMod.instance, 64, 10, true);
	}
	
	
	/* Custom Helper Method
	 * 
	 * Inflict damage on the entity hit by the shuriken
	 */
	private void inflictDamage(RayTraceResult result)
	{
		// Get the entity that was hit by the shuriken and inflict damage
		result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 4);
	}
	
	/* Custom Helper Method
	 * 
	 * Destroy the shuriken entity once it hits another entity or a block. Also add a little animation too
	 */
	private void destroySelf()
	{
		// A little smoke animation
		this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
		
		// Destroy the shuriken entity and remove it from the world (inherited from the entity class)
		this.setDead();
	}
	
	
	/*
	 * When the shuriken entity hits something...
	 */
	@Override
	protected void onImpact(RayTraceResult result) 
	{
		if (result.typeOfHit == RayTraceResult.Type.BLOCK) // Shuriken hits a block
		{
			// Get the block that the shuriken hit
			Block block = this.world.getBlockState(result.getBlockPos()).getBlock();
			
			// If the shuriken collides with vegetation and other "quasi" blocks, destroy the thing and continue on its path
			if (Arrays.asList(SHURIKEN_BREAKS_THROUGH).contains(block))
			{
				// Get info relevant so the broken vegetation block is harvestable
				BlockPos blockpos = result.getBlockPos();
				IBlockState blockstate = this.world.getBlockState(blockpos);
				TileEntity te = this.world.getTileEntity(blockpos);
				
				if (this.getThrower() instanceof EntityPlayer) // if thrower is a player
				{
					// Destroy the block but make sure it's harvestable
					EntityPlayer player = (EntityPlayer)this.getThrower();
					this.world.destroyBlock(blockpos, false);
					block.harvestBlock(this.world, player, blockpos, blockstate, te, new ItemStack(ModItems.shuriken)); // TODO: Verify the last parameter is correct (making a guess on this one)
				}
			}
			// Otherwise it hit a block or entity and can be destroyed
			else
			{
				this.destroySelf();
			}
		}
		else
		{			
			// Ignore the player if the entity hits them and continue through
			if (result.entityHit != null && !(result.entityHit instanceof EntityPlayer))
			{
				this.inflictDamage(result);
				this.destroySelf();
			}
		}
	}
	
	/*
	 * Override impact of gravity to be minimal 
	 */
	@Override
	protected float getGravityVelocity() 
	{
		return this.GRAVITY;
	}

}
