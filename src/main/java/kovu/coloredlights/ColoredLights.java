package kovu.coloredlights;

import kovu.coloredlights.events.CLEventHandler;
import kovu.coloredlights.proxy.CommonProxy;
import kovu.coloredlights.utils.Constants;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MODID, version = Constants.VERSION)
public class ColoredLights
{    
	@SidedProxy(clientSide = Constants.PROXY_LOCATION + ".ClientProxy", serverSide = Constants.PROXY_LOCATION + ".CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		
	}
	
    @EventHandler
    public static void init(FMLInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new CLEventHandler());
    }
    
    @EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
    	
    }
}