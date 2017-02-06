package tschipp.creativePlus.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FlyingRing extends Item{
	
	public FlyingRing() {
		this.setMaxStackSize(1);
	}
	
	@Override
	 public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected)
    {
		if(entity instanceof EntityPlayer) {
			
			((EntityPlayer)entity).capabilities.allowFlying = true;
		}
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add("Grants the ability to fly");
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
		player.capabilities.allowFlying = false;
		player.capabilities.isFlying = false;
		player.fallDistance = 0.0F;
		GameType mode = ((EntityPlayerMP) player).interactionManager.getGameType();	
		if(!player.capabilities.isCreativeMode) {
		player.setGameType(GameType.SURVIVAL);
		player.setGameType(mode);
		}

        return true;
    }

}
