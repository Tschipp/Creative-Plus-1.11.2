package tschipp.creativePlus.Proxies;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tschipp.creativePlus.items.CustomItems;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		CustomItems.createItems();
	}
	
	public void init(FMLInitializationEvent event) {
		
	}

	public void postInit(FMLPostInitializationEvent event) {
	
	}

}
