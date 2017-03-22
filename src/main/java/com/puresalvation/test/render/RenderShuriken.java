package com.puresalvation.test.render;

import org.lwjgl.opengl.GL11;

import com.puresalvation.test.Reference;
import com.puresalvation.test.entity.EntityShuriken;
import com.puresalvation.test.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderShuriken extends Render<EntityShuriken> {

	// Location of the texture
	private static final ResourceLocation shurikenTexture = new ResourceLocation(Reference.MOD_ID, "textures/items/shuriken.png"); 

	protected final Item item;
    private final RenderItem itemRenderer;
	
	// [CONSTRUCTORS]
	
	public RenderShuriken(RenderManager renderManager) 
	{
		super(renderManager);
		this.item = ModItems.shuriken;
		this.itemRenderer = Minecraft.getMinecraft().getRenderItem();
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
	@Override
	protected ResourceLocation getEntityTexture(EntityShuriken shuriken) 
	{
		return this.shurikenTexture;
	}
	
	public ItemStack getStackToRender(EntityShuriken entityIn)
    {
        return new ItemStack(this.item);
    }
	
	@Override
	public void doRender(EntityShuriken shuriken, double x, double y, double z, float entityYaw, float partialTicks) 
	{
		//EntityShuriken shuriken = (EntityShuriken)entity;
//		this.bindEntityTexture(shuriken);
//		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F); // set texture color to white
		
		GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.bindEntityTexture(shuriken);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(shuriken));
        }
        
        this.itemRenderer.renderItem(this.getStackToRender(shuriken), ItemCameraTransforms.TransformType.GROUND);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
		
//		// Everything between pushMatrix and popMatrix will affect the render
//		GlStateManager.pushMatrix(); // push Matrix and start operations
//		
//		GlStateManager.translate((float)x, (float)y, (float)z); // Move matrix to the world position of the shuriken
//		//GlStateManager.rotate(shuriken.prevRotationYaw + (shuriken.rotationYaw - shuriken.prevRotationYaw) * entityYaw - 90.0F, 0.0F, 1.0F, 0.0F); // rotate on y-axis to face the direction the player is facing
//		
//		Tessellator tessellator = Tessellator.getInstance(); // Tessellator generates quad faces from a list of vertices
//		VertexBuffer vertexbuffer = tessellator.getBuffer(); // Draws the quad faces
//		
//		GlStateManager.enableRescaleNormal(); // Performs OpenGL operation which sets a flag to enable uniform normal rescaling
//		
//		// Set scale variable to use as a global scaling value for the matrix and normals. Then scale the matrix accordingly
//		float scale = 0.05F;
//		GlStateManager.scale(scale, scale, scale);
//		
//		GL11.glNormal3f(0.0F, 0.0F, scale); // set facing direction of normal. Basically set direction of individual vertices or a quad
//		
//		// ====================================================================
//		// Draw quads for the shuriken model (will be manually creating quads)
//		// ____________________________________________________________________
//		
//		// Draw front-facing quad
//		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX); // start of quad drawing
//		vertexbuffer.pos(-2.0D, -2.0D, 0.0D).tex(0, 0).endVertex();
//		vertexbuffer.pos(2.0D, -2.0D, 0.0D).tex(1, 0).endVertex();
//		vertexbuffer.pos(2.0D, 2.0D, 0.0D).tex(1, 1).endVertex();
//		vertexbuffer.pos(-2.0D, 2.0D, 0.0D).tex(0, 1).endVertex();
//		tessellator.draw(); // end of quad drawing
//		
//		// Draw back-facing quad (by rotating it 180 degrees and redrawing the same quad)
//		GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F); // rotate 180 degrees
//		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX); 
//		vertexbuffer.pos(-2.0D, -2.0D, 0.0D).tex(0, 0).endVertex();
//		vertexbuffer.pos(2.0D, -2.0D, 0.0D).tex(1, 0).endVertex();
//		vertexbuffer.pos(2.0D, 2.0D, 0.0D).tex(1, 1).endVertex();
//		vertexbuffer.pos(-2.0D, 2.0D, 0.0D).tex(0, 1).endVertex();
//		tessellator.draw();
//		
//		// ====================================================================
//		
//		GlStateManager.disableRescaleNormal();
//		GlStateManager.popMatrix(); // return matrix to default translation
		
		super.doRender(shuriken, x, y, z, entityYaw, partialTicks);
	}
}
