package com.puresalvation.test.render;

import org.lwjgl.opengl.GL11;

import com.puresalvation.test.Reference;
import com.puresalvation.test.entity.EntityShuriken;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderShuriken extends Render {

	// Location of the texture
	private static final ResourceLocation shurikenTexture = new ResourceLocation(Reference.MOD_ID, "textures/items/shuriken.png"); 

	
	// [CONSTRUCTORS]
	
	public RenderShuriken(RenderManager renderManager) 
	{
		super(renderManager);
	}

	
	// [METHODS]
	
	public static void registerRender() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityShuriken.class, new IRenderFactory() {
			@Override
			public Render createRenderFor(RenderManager manager) 
			{
				return new RenderShuriken(manager);
			}
		});
	}
	
	
	/* Custom Helper Method
	 * 
	 * Return the texture of the shuriken
	 */
	protected ResourceLocation getEntityTexture(EntityShuriken shuriken) 
	{
		return this.shurikenTexture;
	}
	
	/*
	 * Cast the entity to a shuriken and call the helper method to get the texture
	 */
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return this.getEntityTexture((EntityShuriken)entity);
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) 
	{
		doRender((EntityShuriken)entity, x, y, z, entityYaw, partialTicks);
	}
	
	public void doRender(EntityShuriken shuriken, double x, double y, double z, float entityYaw, float partialTicks) 
	{
		//EntityShuriken shuriken = (EntityShuriken)entity;
		this.bindEntityTexture(shuriken);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F); // set texture color to white
		
		// Everything between pushMatrix and popMatrix will affect the render
		GlStateManager.pushMatrix(); // push Matrix and start operations
		
		GlStateManager.translate((float)x, (float)y, (float)z); // Move matrix to the world position of the shuriken
		//GlStateManager.rotate(shuriken.prevRotationYaw + (shuriken.rotationYaw - shuriken.prevRotationYaw) * entityYaw - 90.0F, 0.0F, 1.0F, 0.0F); // rotate on y-axis to face the direction the player is facing
		
		Tessellator tessellator = Tessellator.getInstance(); // Tessellator generates quad faces from a list of vertices
		VertexBuffer vertexbuffer = tessellator.getBuffer(); // Draws the quad faces
		
		GlStateManager.enableRescaleNormal(); // Performs OpenGL operation which sets a flag to enable uniform normal rescaling
		
		// Set scale variable to use as a global scaling value for the matrix and normals. Then scale the matrix accordingly
		float scale = 0.05F;
		GlStateManager.scale(scale, scale, scale);
		
		GL11.glNormal3f(0.0F, 0.0F, scale); // set facing direction of normal. Basically set direction of individual vertices or a quad
		
		// ====================================================================
		// Draw quads for the shuriken model (will be manually creating quads)
		// ____________________________________________________________________
		
		// Draw front-facing quad
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX); // start of quad drawing
		vertexbuffer.pos(-2.0D, -2.0D, 0.0D).tex(0, 0).endVertex();
		vertexbuffer.pos(2.0D, -2.0D, 0.0D).tex(1, 0).endVertex();
		vertexbuffer.pos(2.0D, 2.0D, 0.0D).tex(1, 1).endVertex();
		vertexbuffer.pos(-2.0D, 2.0D, 0.0D).tex(0, 1).endVertex();
		tessellator.draw(); // end of quad drawing
		
		// Draw back-facing quad (by rotating it 180 degrees and redrawing the same quad)
		GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F); // rotate 180 degrees
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX); 
		vertexbuffer.pos(-2.0D, -2.0D, 0.0D).tex(0, 0).endVertex();
		vertexbuffer.pos(2.0D, -2.0D, 0.0D).tex(1, 0).endVertex();
		vertexbuffer.pos(2.0D, 2.0D, 0.0D).tex(1, 1).endVertex();
		vertexbuffer.pos(-2.0D, 2.0D, 0.0D).tex(0, 1).endVertex();
		tessellator.draw();
		
		// ====================================================================
		
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix(); // return matrix to default translation
		
		super.doRender(shuriken, x, y, z, entityYaw, partialTicks);
	}
}
