package kovu.coloredlights;

import kovu.coloredlights.block.BlueBlock;
import kovu.coloredlights.events.CLEventHandler;
import kovu.coloredlights.proxy.ClientProxy;
import kovu.coloredlights.proxy.CommonProxy;
import kovu.coloredlights.utils.BufferTools;
import kovu.coloredlights.utils.Constants;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

import static org.lwjgl.opengl.GL11.*;

@Mod(modid = Constants.MODID, version = Constants.VERSION)
public class ColoredLights
{		
	public static LightEngine lightengine;
	public static BlueBlock blueBlock = new BlueBlock(Material.anvil);
	
	@SidedProxy(clientSide = Constants.PROXY_LOCATION + ".ClientProxy", serverSide = Constants.PROXY_LOCATION + ".CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		
	}
	
    @EventHandler
    public static void init(FMLInitializationEvent event)
    {
		setUpLighting();

    	MinecraftForge.EVENT_BUS.register(new CLEventHandler());
    	ClientProxy.initRenderers();
    	
    	GameRegistry.registerBlock(blueBlock, "blueBlock");
    }
    
    @EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
    	
    }
    
    public static void setUpLighting()
    {
    	lightengine = new LightEngine();
    	lightengine.initialize();   	
    	
    	System.out.println("[KCL]: Lighting has been set up.");
    }
}