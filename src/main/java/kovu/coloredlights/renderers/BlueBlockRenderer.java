package kovu.coloredlights.renderers;

import static org.lwjgl.opengl.GL11.GL_AMBIENT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LIGHT_MODEL_LOCAL_VIEWER;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SPECULAR;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLight;
import static org.lwjgl.opengl.GL11.glLightModeli;
import static org.lwjgl.opengl.GL11.glShadeModel;

import java.util.Random;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import kovu.coloredlights.proxy.ClientProxy;
import kovu.coloredlights.utils.BufferTools;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class BlueBlockRenderer implements ISimpleBlockRenderingHandler
{
	private Random r = new  Random();
	
	public float[] light_ambient0 = {r.nextFloat(), r.nextFloat(), r.nextFloat(), r.nextFloat()}; // .4 .4 .4 .0 loos like MC normally, RGB
	private float[] light_diffuse0 = {0.0f, 0.0f, 0.0f, 0.0f};
	private float[] light_specular0 = {0.0f, 0.0f, 0.0f, 0.0f};

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) 
	{

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if(ClientProxy.renderPass == 0) // 0 is solid block, else it is alpha render pass
		{
			
			light_ambient0 = new float[] {r.nextFloat(), r.nextFloat(), r.nextFloat(), r.nextFloat()};
			renderer.renderStandardBlock(Block.getBlockById(5), x, y, z);
		
	    	glEnable(GL_LIGHTING);
		   	glShadeModel(GL_SMOOTH);
	    	glEnable(GL_DEPTH_TEST);
	    	glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);
	    	
	    	glEnable(GL_CULL_FACE);
							
			// LIGHT 0
			glLight(GL_LIGHT0, GL_AMBIENT, BufferTools.asFlippedFloatBuffer(light_ambient0));
			glLight(GL_LIGHT0, GL_DIFFUSE, BufferTools.asFlippedFloatBuffer(light_diffuse0));
			glLight(GL_LIGHT0, GL_SPECULAR, BufferTools.asFlippedFloatBuffer(light_specular0));
					
			glEnable(GL_LIGHT0);
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) 
	{
		return true;
	}

	@Override
	public int getRenderId() 
	{
		return ClientProxy.blueBlockRenderType;
	}
}