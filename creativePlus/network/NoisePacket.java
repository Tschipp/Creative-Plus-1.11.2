package tschipp.creativePlus.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class NoisePacket implements IMessage{
	
	public int upOrDown;
	
	public NoisePacket() {
		
	}
	
	public NoisePacket(int i) {
		this.upOrDown = i;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		upOrDown = ByteBufUtils.readVarInt(buf, 4);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, upOrDown, 4);
	}

}
