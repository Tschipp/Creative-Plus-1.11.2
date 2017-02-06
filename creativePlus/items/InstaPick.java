package tschipp.creativePlus.items;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InstaPick extends Item{

	public InstaPick() {
		this.setMaxStackSize(1);

	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state)
	{
		return Float.MAX_VALUE;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
	{
		World world = player.world;


		if(!player.isCreative()) {

			world.destroyBlock(pos, false);

		}


		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add("Destroys every (breakable) block instantly");
		tooltip.add("Most effective in survival mode");

	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}


}
