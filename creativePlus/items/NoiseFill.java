package tschipp.creativePlus.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IItemColor;
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
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NoiseFill extends Item {
	
	@SideOnly(Side.CLIENT)
	public static class Color implements IItemColor 
	{
		@Override
		@SideOnly(Side.CLIENT)
		public int getColorFromItemstack(ItemStack stack, int tintIndex) {
			{
				Block block = Block.getBlockFromName(stack.getTagCompound().getString("material"));
				IBlockState state = block.getStateFromMeta(stack.getTagCompound().getInteger("damage"));

				if(tintIndex < 1) {
					if(block.getMapColor(state).colorValue == 000) {
						return 201196;
					} else {

						return block.getMapColor(state).colorValue;
					}
				} else {
					return 16777215;


				}

			}

		}
	}

	public ArrayList<BlockPos> positions = new ArrayList<BlockPos>();
	public ArrayList<BlockPos> tempPositions = new ArrayList<BlockPos>();
	public IBlockState mat;

	public IBlockState matToReplace;
	Random rand = new Random();


	public NoiseFill() {
		this.setMaxStackSize(1);
	}




	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems)
	{


		NBTTagCompound subTag = new NBTTagCompound();
		subTag.setString("material", "minecraft:stone");
		subTag.setInteger("damage", 0);
		subTag.setInteger("amount", 5);
		ItemStack stack = new ItemStack(item, 1, 0);
		stack.setTagCompound(subTag);
		
		
		Block block = Block.getBlockFromName(stack.getTagCompound().getString("material"));
		IBlockState state = block.getStateFromMeta(stack.getTagCompound().getInteger("damage"));

		subItems.add(stack);
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
	{
		
		
		if(stack.getTagCompound() != null) {
			Block block = Block.getBlockFromName(stack.getTagCompound().getString("material"));
			IBlockState state = block.getStateFromMeta(stack.getTagCompound().getInteger("damage"));
		
			ItemStack blockStack = new ItemStack(block, 1, block.getMetaFromState(state));
			
			if(Item.getItemFromBlock(block) != null)
			{
				return "Noise Wand (" + blockStack.getDisplayName() +")";
			}
			else
			{
				return "Noise Wand (" +  I18n.translateToLocal(block.getUnlocalizedName() + ".name") + ")";
			}			} else {
			return "" + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name");
		}
		
	} 


	/*	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{

		return "Noise Wand ("+ net.minecraft.util.text.translation.I18n.translateToLocal(Block.getBlockFromName(stack.getTagCompound().getString("material")).getLocalizedName()) + ")";

	}  */

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = player.getHeldItem(hand);

		if(!world.isRemote) {


			matToReplace = world.getBlockState(pos);
			positions.add(pos);
			for(int i = 0; i < 4; i++) {

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


			//for(int k = 0; k < (int)((rand.nextInt((int)(positions.size() - (positions.size()*0.3)))+positions.size()*0.7) * ((double)(stack.getTagCompound().getInteger("amount"))/10)); k++) {


			for(int k = 0; k < (int)((rand.nextInt((int)(positions.size() - (positions.size()*0.3)))+positions.size()*0.7) * ((double)(stack.getTagCompound().getInteger("amount"))/20)); k++) {


				world.setBlockState(positions.get(rand.nextInt(positions.size())), Block.getBlockFromName(stack.getTagCompound().getString("material")).getStateFromMeta(stack.getTagCompound().getInteger("damage")), 2);

			}


			positions.clear();
			tempPositions.clear();

		}


		return EnumActionResult.SUCCESS;
	}




	public void addPositionsDown(int i, World world) {
		if(world.getBlockState(positions.get(i).north()) == matToReplace) {
			if(!positions.contains(positions.get(i).north()) && !tempPositions.contains(positions.get(i).north())) {
				tempPositions.add(positions.get(i).north());
			}
		}
		if(world.getBlockState(positions.get(i).east()) == matToReplace) {
			if(!positions.contains(positions.get(i).east()) && !tempPositions.contains(positions.get(i).east())) {
				tempPositions.add(positions.get(i).east());
			}

		}
		if(world.getBlockState(positions.get(i).south()) == matToReplace) {
			if(!positions.contains(positions.get(i).south()) && !tempPositions.contains(positions.get(i).south())) {
				tempPositions.add(positions.get(i).south());
			}

		}
		if(world.getBlockState(positions.get(i).west()) == matToReplace) {
			if(!positions.contains(positions.get(i).west()) && !tempPositions.contains(positions.get(i).west())) {
				tempPositions.add(positions.get(i).west());
			}

		}
	}

	public void addPositionsNorth(int i, World world) {
		if(world.getBlockState(positions.get(i).up()) == matToReplace) {
			if(!positions.contains(positions.get(i).up()) && !tempPositions.contains(positions.get(i).north())) {
				tempPositions.add(positions.get(i).up());
			}
		}
		if(world.getBlockState(positions.get(i).east()) == matToReplace) {
			if(!positions.contains(positions.get(i).east()) && !tempPositions.contains(positions.get(i).east())) {
				tempPositions.add(positions.get(i).east());
			}

		}
		if(world.getBlockState(positions.get(i).down()) == matToReplace) {
			if(!positions.contains(positions.get(i).down()) && !tempPositions.contains(positions.get(i).south())) {
				tempPositions.add(positions.get(i).down());
			}

		}
		if(world.getBlockState(positions.get(i).west()) == matToReplace) {
			if(!positions.contains(positions.get(i).west()) && !tempPositions.contains(positions.get(i).west())) {
				tempPositions.add(positions.get(i).west());
			}

		}
	}

	public void addPositionsEast(int i, World world) {
		if(world.getBlockState(positions.get(i).north()) == matToReplace) {
			if(!positions.contains(positions.get(i).north()) && !tempPositions.contains(positions.get(i).north())) {
				tempPositions.add(positions.get(i).north());
			}
		}
		if(world.getBlockState(positions.get(i).up()) == matToReplace) {
			if(!positions.contains(positions.get(i).up()) && !tempPositions.contains(positions.get(i).east())) {
				tempPositions.add(positions.get(i).up());
			}

		}
		if(world.getBlockState(positions.get(i).south()) == matToReplace) {
			if(!positions.contains(positions.get(i).south()) && !tempPositions.contains(positions.get(i).south())) {
				tempPositions.add(positions.get(i).south());
			}

		}
		if(world.getBlockState(positions.get(i).down()) == matToReplace) {
			if(!positions.contains(positions.get(i).down()) && !tempPositions.contains(positions.get(i).west())) {
				tempPositions.add(positions.get(i).down());
			}

		}
	}

	/*	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
	{

		World world = player.worldObj;

		if(player.isSneaking()) {

			mat = world.getBlockState(pos);
			NBTTagCompound subTag = itemstack.getTagCompound();

			subTag.setString("material", mat.getBlock().getRegistryName().getResourceDomain() + ":" + mat.getBlock().getRegistryName().getResourcePath());
			subTag.setInteger("damage", mat.getBlock().getMetaFromState(mat));

			Block block = (Block)Block.REGISTRY.getObject(new ResourceLocation(itemstack.getTagCompound().getString("material")));

			itemstack.setTagCompound(subTag);
			if(!world.isRemote) {
				player.addChatComponentMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE +"Material set to: " + net.minecraft.util.text.translation.I18n.translateToLocal(net.minecraft.util.text.translation.I18n.translateToLocal(block.getLocalizedName()))));
			}
			world.scheduleBlockUpdate(pos, world.getBlockState(pos).getBlock(), 0, 1);
		}
		else {
			world.scheduleBlockUpdate(pos, world.getBlockState(pos).getBlock(), 0, 1);

		}
		return true;


	} */


	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		
		Block block = Block.getBlockFromName(stack.getTagCompound().getString("material"));
		IBlockState state = block.getStateFromMeta(stack.getTagCompound().getInteger("damage"));
		ItemStack blockStack = new ItemStack(block, 1, block.getMetaFromState(state));
		
		tooltip.add("Places material randomly around the clicked point. Side Specific.");
		if(Item.getItemFromBlock(block) != null)
		{
			tooltip.add("Material: " + blockStack.getDisplayName());
		}
		else
		{
			tooltip.add("Material: " + I18n.translateToLocal(block.getUnlocalizedName() + ".name"));
		}
		tooltip.add("Amount Value: "+ (stack.getTagCompound().getInteger("amount")));

	}





}
