package tschipp.creativePlus.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SuperBonemeal extends Item {

	int counter = 0;

	public SuperBonemeal(String name) {

		this.setUnlocalizedName(name);

		this.setMaxStackSize(64);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack stack = player.getHeldItem(hand);
		if(!worldIn.isRemote) {

			IBlockState iblockstate = worldIn.getBlockState(pos);

			int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, pos, iblockstate, stack);

			if (iblockstate.getBlock() instanceof IGrowable)
			{
				IGrowable igrowable = (IGrowable)iblockstate.getBlock();

				while(worldIn.getBlockState(pos).getBlock() instanceof IGrowable && worldIn.getBlockState(pos).getBlock() != Blocks.GRASS && worldIn.getBlockState(pos).getBlock() != Blocks.DOUBLE_PLANT) {

					IGrowable plant = (IGrowable)worldIn.getBlockState(pos).getBlock();

					if(plant.canGrow(worldIn, pos, worldIn.getBlockState(pos), worldIn.isRemote)) {


						plant.grow(worldIn, worldIn.rand, pos, worldIn.getBlockState(pos));



					} else {
						break;
					}

					counter++;

					if(counter >= 200) {
						counter = 0;
						if(!worldIn.isRemote) {
							player.sendMessage(new TextComponentString(TextFormatting.RED +"Tree can't grow here"));
						}
						break;
					}
				}

				stack.shrink(1);

				if(!worldIn.isRemote) {
					if(worldIn.getBlockState(pos).getBlock() == Blocks.GRASS) {

						player.sendMessage(new TextComponentString(TextFormatting.RED +"Use normal Bone Meal for Grass"));


					}
					if(worldIn.getBlockState(pos).getBlock() == Blocks.DOUBLE_PLANT) {
						player.sendMessage(new TextComponentString(TextFormatting.RED +"Use normal Bone Meal for Double Plants"));
					}

				}

				Block block = worldIn.getBlockState(pos).getBlock();

				double d0 = itemRand.nextGaussian() * 0.02D;
				double d1 = itemRand.nextGaussian() * 0.02D;
				double d2 = itemRand.nextGaussian() * 0.02D;
				worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, (double)((float)pos.getX() + itemRand.nextFloat()), (double)pos.getY() + (double)itemRand.nextFloat() * 2, (double)((float)pos.getZ() + itemRand.nextFloat()), d0, d1, d2, 200);

			}

		}

		return EnumActionResult.SUCCESS;

	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add("Instantly grows Trees, Crops, etc");

	}

}
