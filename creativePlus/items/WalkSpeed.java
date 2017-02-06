package tschipp.creativePlus.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WalkSpeed extends Item {
	
		
	
		public WalkSpeed(String name) {
		this.setUnlocalizedName(name);
		this.setMaxStackSize(1);
		
		
		
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		
		ItemStack itemStackIn = player.getHeldItem(hand);
		
		if(player.isSneaking()) {
			
			player.capabilities.setPlayerWalkSpeed(player.capabilities.getWalkSpeed() / 2);
			
		} else {
			
		player.capabilities.setPlayerWalkSpeed(player.capabilities.getWalkSpeed() * 2);
		
		
		} 
		
		if(!world.isRemote) {
			
			if((int)Math.floor(player.capabilities.getWalkSpeed()/0.1) > 1) {
				player.sendMessage(new TextComponentString("Your Walk Speed is now "  + TextFormatting.GOLD + (int)Math.floor(player.capabilities.getWalkSpeed()/0.1) + TextFormatting.RESET +" times " + TextFormatting.DARK_GREEN +"faster" + TextFormatting.RESET +" than normal"));
			} else if((int)Math.floor(player.capabilities.getWalkSpeed()/0.1) == 1) {
				
				player.sendMessage(new TextComponentString("Your Walk Speed is now normal"));
				
			} else {
				player.sendMessage(new TextComponentString("Your Walk Speed is now "  + TextFormatting.GOLD + (int)(1/(Math.floor(player.capabilities.getWalkSpeed()*1000000000/0.1)/1000000000)) + TextFormatting.RESET +" times " + TextFormatting.DARK_RED +"slower" + TextFormatting.RESET +" than normal"));

			}
			
			
			
		

			
		}
	  return new ActionResult(EnumActionResult.PASS, itemStackIn);
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add("Changes the players walking speed");
	}

}
