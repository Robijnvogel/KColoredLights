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
	private float[] lightPosition0;
	public  float[] light_ambient0 = {0.4f, 0.4f, 0.4f, 0.0f}; // .4 .4 .4 .0 loos like MC normally, RGB
	private float[] light_diffuse0 = {0.0f, 0.0f, 0.0f, 0.0f};
	private float[] light_specular0 = {0.0f, 0.0f, 0.0f, 0.0f};
	
	private float[] light_ambient1 = {0.0f, 1.0f, 0.0f, 0.0f};
	private float[] light_specular1 = {1.0f, 1.0f, 1.0f, 1.0f};
	private float[] light_specref1 = {1.0f, 1.0f, 1.0f, 1.0f};
	private float[] light_spot_direction = {0.0f, 0.0f, -1.0f, 0.0f};
		
	@SubscribeEvent()
	public void onWorldRender(RenderWorldEvent.Pre event)
	{
		render();
	}
	
	private void render() 
	{
		//glPushMatrix();
		
    	glEnable(GL_LIGHTING);
	   	glShadeModel(GL_SMOOTH);
    	glEnable(GL_DEPTH_TEST);
    	glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);
    	
    	glEnable(GL_CULL_FACE);
				
		lightPosition0 = new float[]{(float)Minecraft.getMinecraft().thePlayer.getPlayerCoordinates().posX + 2, (float)Minecraft.getMinecraft().thePlayer.getPlayerCoordinates().posY + 2, (float)Minecraft.getMinecraft().thePlayer.getPlayerCoordinates().posZ + 2, 1.0f};		
		
		// LIGHT 0
		glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(lightPosition0));
		glLight(GL_LIGHT0, GL_AMBIENT, BufferTools.asFlippedFloatBuffer(light_ambient0));
		glLight(GL_LIGHT0, GL_DIFFUSE, BufferTools.asFlippedFloatBuffer(light_diffuse0));
		glLight(GL_LIGHT0, GL_SPECULAR, BufferTools.asFlippedFloatBuffer(light_specular0));
				
		// Light 1
		glLight(GL_LIGHT1, GL_DIFFUSE, BufferTools.asFlippedFloatBuffer(light_ambient1));
		glLight(GL_LIGHT1, GL_SPECULAR, BufferTools.asFlippedFloatBuffer(light_specular1));
		glLight(GL_LIGHT1, GL_POSITION, BufferTools.asFlippedFloatBuffer(lightPosition0));
		glLightf(GL_LIGHT1, GL_SPOT_CUTOFF, 60.0f);
		glLightf(GL_LIGHT1, GL_SPOT_EXPONENT, 100.0f);

		glEnable(GL_LIGHT0);
    	glEnable(GL_LIGHT1);

		//glPopMatrix();
	}
}