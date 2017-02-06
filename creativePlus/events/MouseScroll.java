package tschipp.creativePlus.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.creativePlus.CreativePlus;
import tschipp.creativePlus.items.CustomItems;
import tschipp.creativePlus.network.NoisePacket;

public class MouseScroll {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void scroll(MouseEvent event) 
	{
		

		EntityPlayerSP player = Minecraft.getMinecraft().player;

		if(player.getHeldItem(EnumHand.MAIN_HAND)!= null) {
			if(player.getHeldItem(EnumHand.MAIN_HAND).getItem() == CustomItems.noiseFill && player.isSneaking()) {
				if(event.getDwheel() > 0) 
				{
					CreativePlus.network.sendToServer(new NoisePacket(1));
			
					event.setCanceled(true);
				} 
				else if(event.getDwheel() < 0) 
				{

					CreativePlus.network.sendToServer(new NoisePacket(2));
		
					event.setCanceled(true);

				}

			}

		}


	}

}
