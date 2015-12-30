package kovu.coloredlights.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import kovu.coloredlights.ColoredLights;
import kovu.coloredlights.utils.BufferTools;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

public class CLEventHandler 
{
	// For ambient, diffuse, specular (that I know), the last float value is the inverse strength, that is 1 is (none / low)? and 0 is all
	private float[] lightPosition;
	private float[] light_ambient = {0.0f, 1.0f, 0.0f, 0.0f}; // .4 .4 .4 .0 loos like MC normally
	private float[] light_diffuse = {0.0f, 0.0f, 0.0f, 0.0f};
	private float[] light_specular = {0.0f, 0.0f, 0.0f, 0.0f};
	private float[] light_spot_direction = {0.0f, 1.0f, 0.0f, 0.0f};
		
	@SubscribeEvent()
	public void onWorldRender(RenderWorldEvent.Pre event)
	{
		render();
	}
	
	private void render() 
	{
		glPushMatrix();
		
		glDisable(GL_TEXTURE_2D);
	   	glShadeModel(GL_SMOOTH);
    	glEnable(GL_DEPTH_TEST);
    	
    	glEnable(GL_LIGHTING);
    	glEnable(GL_LIGHT0);
    	glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);
    	glEnable(GL_COLOR_MATERIAL);

    	glEnable(GL_CULL_FACE);
    	glColorMaterial(GL_FRONT, GL_DIFFUSE);
				
		lightPosition = new float[]{(float)Minecraft.getMinecraft().thePlayer.getPlayerCoordinates().posX, (float)Minecraft.getMinecraft().thePlayer.getPlayerCoordinates().posY, (float)Minecraft.getMinecraft().thePlayer.getPlayerCoordinates().posZ, 1.0f};
		
		glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(lightPosition));
		//glLight(GL_LIGHT0, GL_SPOT_DIRECTION, BufferTools.asFlippedFloatBuffer(light_spot_direction));
		glLight(GL_LIGHT0, GL_AMBIENT, BufferTools.asFlippedFloatBuffer(light_ambient));
		glLight(GL_LIGHT0, GL_DIFFUSE, BufferTools.asFlippedFloatBuffer(light_diffuse));
		glLight(GL_LIGHT0, GL_SPECULAR, BufferTools.asFlippedFloatBuffer(light_specular));
		glEnable(GL_TEXTURE_2D);
		
		glPopMatrix();
	}
}