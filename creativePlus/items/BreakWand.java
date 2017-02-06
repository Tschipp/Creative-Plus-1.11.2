package tschipp.creativePlus.items;

import java.util.ArrayList;
import java.util.List;

import tschipp.creativePlus.CreativePlus;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BreakWand extends Item {

	public ArrayList<BlockPos> positions = new ArrayList<BlockPos>();
	public ArrayList<BlockPos> tempPositions = new ArrayList<BlockPos>();
	public IBlockState mat;
	public EnumFacing side = null;


	public BreakWand() {
		this.setMaxStackSize(1);

	}


	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems)
	{

		NBTTagCompound subTag = new NBTTagCompound();
		subTag.setInteger("radius", 3);

		ItemStack stack = new ItemStack(item, 1, 0);
		stack.setTagCompound(subTag);

		subItems.add(stack);
	}



	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player)
	{
		World world = player.world;

		if(world.isRemote) {
			this.side = player.rayTrace(10, 1.0F).sideHit;;
			
		}

		if(!world.isRemote) {

			positions.add(pos);
			for(int i = 0; i < stack.getTagCompound().getInteger("radius"); i++) {

				for(int j = 0; j < positions.size(); j++) {

					if(side == EnumFacing.NORTH || side == EnumFacing.SOUTH) {
						addPositionsNorth(j,world);
					}
					if(side == EnumFacing.EAST || side == EnumFacing.WEST) {
						addPositionsEast(j,world);
					}	
					if(side == EnumFacing.DOWN || side == EnumFacing.UP) {
						addPositionsDown(j,world);
					}

				}

				positions.addAll(tempPositions);

				tempPositions.clear();
			} 




			for(int k = 0; k < positions.size(); k++) {

				if(CreativePlus.enableBreakWandParticles)
				{
					world.destroyBlock(positions.get(k), false);
				}
				else
				{
					world.setBlockToAir(positions.get(k));
				}
			}


			positions.clear();
			tempPositions.clear();


		}



		return false;
	}


	public void addPositionsDown(int i, World world) {

		if(!positions.contains(positions.get(i).north()) && !tempPositions.contains(positions.get(i).north())) {
			tempPositions.add(positions.get(i).north());

		}

		if(!positions.contains(positions.get(i).east()) && !tempPositions.contains(positions.get(i).east())) {
			tempPositions.add(positions.get(i).east());


		}

		if(!positions.contains(positions.get(i).south()) && !tempPositions.contains(positions.get(i).south())) {
			tempPositions.add(positions.get(i).south());
		}



		if(!positions.contains(positions.get(i).west()) && !tempPositions.contains(positions.get(i).west())) {
			tempPositions.add(positions.get(i).west());
		}


	}

	public void addPositionsNorth(int i, World world) {

		if(!positions.contains(positions.get(i).up()) && !tempPositions.contains(positions.get(i).north())) {
			tempPositions.add(positions.get(i).up());
		}


		if(!positions.contains(positions.get(i).east()) && !tempPositions.contains(positions.get(i).east())) {
			tempPositions.add(positions.get(i).east());
		}



		if(!positions.contains(positions.get(i).down()) && !tempPositions.contains(positions.get(i).south())) {
			tempPositions.add(positions.get(i).down());
		}



		if(!positions.contains(positions.get(i).west()) && !tempPositions.contains(positions.get(i).west())) {
			tempPositions.add(positions.get(i).west());
		}


	}

	public void addPositionsEast(int i, World world) {

		if(!positions.contains(positions.get(i).north()) && !tempPositions.contains(positions.get(i).north())) {
			tempPositions.add(positions.get(i).north());
		}


		if(!positions.contains(positions.get(i).up()) && !tempPositions.contains(positions.get(i).east())) {
			tempPositions.add(positions.get(i).up());

		}

		if(!positions.contains(positions.get(i).south()) && !tempPositions.contains(positions.get(i).south())) {
			tempPositions.add(positions.get(i).south());


		}

		if(!positions.contains(positions.get(i).down()) && !tempPositions.contains(positions.get(i).west())) {
			tempPositions.add(positions.get(i).down());
		}


	}


	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{

		tooltip.add("Destroys every block in the specified radius. Side Specific.");
		tooltip.add("Radius: " + stack.getTagCompound().getInteger("radius"));


	}


	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state)
	{
		return 1000.0F;
	}


}
