package kovu.coloredlights.events;

import net.minecraftforge.client.event.RenderWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CLEventHandler 
{
	@SubscribeEvent()
	public void onWorldRender(RenderWorldEvent.Pre event)
	{
		System.out.println("[KCL] WORLD RENDERED");
	}
}