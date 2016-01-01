package kovu.coloredlights.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import kovu.coloredlights.renderers.BlueBlockRenderer;

public class ClientProxy extends CommonProxy
{
	public static int renderPass;
	public static int blueBlockRenderType;
	
	public static void initRenderers()
	{
		blueBlockRenderType = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new BlueBlockRenderer());
	}
	
	@Override
	public void initSounds()
	{
		
	}
}