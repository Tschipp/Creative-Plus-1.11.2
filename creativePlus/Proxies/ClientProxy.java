package tschipp.creativePlus.Proxies;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tschipp.creativePlus.CreativePlus;
import tschipp.creativePlus.items.CircleWand;
import tschipp.creativePlus.items.CustomItems;
import tschipp.creativePlus.items.FloodFill;
import tschipp.creativePlus.items.ItemRenderRegister;
import tschipp.creativePlus.items.NoiseFill;
import tschipp.creativePlus.items.Wand;

public class ClientProxy extends CommonProxy{
	
public void preInit(FMLPreInitializationEvent event) {
	
	super.preInit(event);

	ItemRenderRegister.registerItems();

		
	}
	
	public void init(FMLInitializationEvent event) {
		
		super.init(event);
		
		if(CreativePlus.enableNoiseWand) {
		FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new NoiseFill.Color(), CustomItems.noiseFill);
		}
		
		if(CreativePlus.enableFillWand) {
		FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new FloodFill.Color(), CustomItems.floodFill);
		}
		
		if(CreativePlus.enablePointWand) {
		FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new Wand.Color(), CustomItems.wand);
		}
		
		if(CreativePlus.enableCircleWand) {
		FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new CircleWand.Color(), CustomItems.circleWand);
		}

	}

	public void postInit(FMLPostInitializationEvent event) {
		
		super.postInit(event);
	
	}
	
	

}
