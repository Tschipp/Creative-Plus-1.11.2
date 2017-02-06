package tschipp.creativePlus.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tschipp.creativePlus.items.CustomItems;

public class RadiusPacketHandler implements IMessageHandler<RadiusPacket, IMessage> {


	@Override
	public IMessage onMessage(final RadiusPacket message, final MessageContext ctx) {
		IThreadListener mainThread = (WorldServer)ctx.getServerHandler().playerEntity.world;

		mainThread.addScheduledTask(new Runnable(){
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;


			@Override
			public void run() {



				if(player.getHeldItem(EnumHand.MAIN_HAND) != null) {

					if(player.getHeldItem(EnumHand.MAIN_HAND).getItem() == CustomItems.breaker && player.isSneaking()) {

						if(message.upOrDown == 1) {

							ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND); 
							NBTTagCompound subTag = stack.getTagCompound();
							if(subTag.getInteger("radius") >= 50) {
								return;
							} else {
								subTag.setInteger("radius", subTag.getInteger("radius") + 1);
								stack.setTagCompound(subTag);

								if(!player.world.isRemote) {
									player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE +"Radius changed to: " + (stack.getTagCompound().getInteger("radius"))));
								}

							}

						}else
							if(message.upOrDown == 2) {



								ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND); 
								NBTTagCompound subTag = stack.getTagCompound();
								if(subTag.getInteger("radius") < 1) {
									return;
								} else {
									subTag.setInteger("radius", subTag.getInteger("radius") - 1);
									stack.setTagCompound(subTag);

									if(!player.world.isRemote) {
										player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE +"Radius changed to: " + (stack.getTagCompound().getInteger("radius"))));
									}

								}

							}
					} 
				}

			}

		});

		return null;
	}



}
