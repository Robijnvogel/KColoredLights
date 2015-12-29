package kovu.coloredlights;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LightEngine
{
	private int fragmentShader;
	private int shaderProgram;
	

	public LightEngine()
	{
		
	}
	
	public void initialize()
	{
		shaderProgram = glCreateProgram();
		fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		StringBuilder fragmentShaderSource = new StringBuilder();
		
		try
		{
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("assets/KColoredLights/shader.frag"), "UTF-8"));
			while((line = reader.readLine()) != null)
			{
				fragmentShaderSource.append(line).append("\n");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);
		if(glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE)
		{
			System.err.println("Fragment shader not compiled!");
		}
		
		glAttachShader(shaderProgram, fragmentShader);
		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_STENCIL_TEST);
		glClearColor(0, 0, 0, 0);
	}
}