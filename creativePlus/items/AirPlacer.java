package tschipp.creativePlus.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AirPlacer extends Item{
	
	public AirPlacer() {
		this.setMaxStackSize(1);
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
	{
		if(!world.isRemote) {
		world.setBlockState(new BlockPos(player.posX, player.posY-1, player.posZ), Blocks.STONE.getDefaultState(), 2);
		}
		
		
		return new ActionResult(EnumActionResult.PASS, player.getHeldItem(hand));
		}	
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add("Places a block of stone under the Player");
	}


}
