package tschipp.creativePlus.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Wand extends Item {
	
	
	@SideOnly(Side.CLIENT)
	public static class Color implements IItemColor 
	{
		@Override
		@SideOnly(Side.CLIENT)
		public int getColorFromItemstack(ItemStack stack, int tintIndex) {
			{
				Block block = Block.getBlockFromName(stack.getTagCompound().getString("material"));
				IBlockState state = block.getStateFromMeta(stack.getTagCompound().getInteger("damage"));


				if(block.getMapColor(state).colorValue == 000) {
					return 201196;
				} else {

					return block.getMapColor(state).colorValue;
				}
			} 

		}		
	}


	public Vec3d pos1;
	public Vec3d pos2;
	public Vec3d difference;
	public Vec3d posToPlaceBlock;



	public IBlockState mat;

	public Wand() {
		this.setMaxStackSize(1);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems)
	{


		NBTTagCompound subTag = new NBTTagCompound();
		subTag.setString("material", "minecraft:stone");
		subTag.setInteger("damage", 0);
		subTag.setDouble("pos1x", 0);
		subTag.setDouble("pos1y", 0);
		subTag.setDouble("pos1z", 0);
		subTag.setDouble("pos2x", 0);
		subTag.setDouble("pos2y", 0);
		subTag.setDouble("pos2z", 0);
		subTag.setBoolean("hasSetPos1", false);
		subTag.setBoolean("hasSetPos2", false);
		ItemStack stack = new ItemStack(item, 1, 0);
		stack.setTagCompound(subTag);


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
				return "Line Wand (" + blockStack.getDisplayName() +")";
			}
			else
			{
				return "Line Wand (" +  I18n.translateToLocal(block.getUnlocalizedName() + ".name") + ")";
			}			} else {
			return "" + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name");
		}
		
	} 


	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

		ItemStack stack = player.getHeldItem(hand);

		World world = player.world;
		if(pos.getX() < 0) {
			pos2 = new Vec3d(pos.getX()-0.5, pos.getY(), pos.getZ());
		}
		if(pos.getX() > 0) {
			pos2 = new Vec3d(pos.getX()+0.5, pos.getY(), pos.getZ());
		}
		if(pos.getZ() < 0) {
			pos2 = new Vec3d(pos.getX(), pos.getY(), pos.getZ()-0.5);
		}
		if(pos.getX() > 0) {
			pos2 = new Vec3d(pos.getX()-0.5, pos.getY(), pos.getZ()+0.5);
		}
		if(pos.getX() < 0 && pos.getZ() < 0) {
			pos2 = new Vec3d(pos.getX()-0.5, pos.getY(), pos.getZ()-0.5);
		}
		if(pos.getX() > 0 && pos.getZ() > 0) {
			pos2 = new Vec3d(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
		}
		if(pos.getX() < 0 && pos.getZ() > 0) {
			pos2 = new Vec3d(pos.getX()-0.5, pos.getY(), pos.getZ()+0.5);
		}
		if(pos.getX() > 0 && pos.getZ() < 0) {
			pos2 = new Vec3d(pos.getX()+0.5, pos.getY(), pos.getZ()-0.5);
		}

		NBTTagCompound subTag = stack.getTagCompound();
		subTag.setDouble("pos2x", pos2.xCoord);
		subTag.setDouble("pos2y", pos2.yCoord);
		subTag.setDouble("pos2z", pos2.zCoord);
		subTag.setBoolean("hasSetPos2", true);
		stack.setTagCompound(subTag);

		if(!world.isRemote) {
			player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "Set Pos2 at X: " + (int)pos2.xCoord + ", Y: " + (int)pos2.yCoord + ", Z: " + (int)pos2.zCoord));
		}
		world.scheduleBlockUpdate(pos, world.getBlockState(pos).getBlock(), 0, 1);
		return EnumActionResult.PASS;
	}


	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerIn, EnumHand hand)
	{

		ItemStack stack = playerIn.getHeldItem(hand);

		if(stack.getTagCompound().getBoolean("hasSetPos1") && stack.getTagCompound().getBoolean("hasSetPos2") && playerIn.isSneaking()) {

			NBTTagCompound nbt = stack.getTagCompound();

			Vec3d setPos1 = new Vec3d(nbt.getDouble("pos1x"), nbt.getDouble("pos1y"), nbt.getDouble("pos1z"));
			Vec3d setPos2 = new Vec3d(nbt.getDouble("pos2x"), nbt.getDouble("pos2y"), nbt.getDouble("pos2z"));
			difference = setPos1.subtractReverse(setPos2).normalize();
			posToPlaceBlock = setPos1;

			if(setPos1.xCoord > 0 && setPos2.xCoord < 0 || setPos1.xCoord < 0 && setPos2.xCoord > 0 || setPos1.zCoord > 0 && setPos2.zCoord < 0 || setPos1.zCoord < 0 && setPos2.zCoord > 0 || setPos1.yCoord > 256 || setPos2.yCoord > 256 || setPos1.yCoord < 0 || setPos2.yCoord < 0) {
				if(!world.isRemote) {
					playerIn.sendMessage(new TextComponentString(TextFormatting.RED +"Don't try to create lines from positive X to negative X, Z, or Y"));
				}
			} else {

				if(compare(setPos1, setPos2)) {
					pos1 = null;
					if(!world.isRemote) {
						world.setBlockState(new BlockPos((int)setPos1.xCoord, (int)setPos1.yCoord, (int)setPos1.zCoord), Block.getBlockFromName(stack.getTagCompound().getString("material")).getStateFromMeta(stack.getTagCompound().getInteger("damage")), 2);
						BlockPos pos2 = new BlockPos((int)setPos1.xCoord, (int)setPos1.yCoord, (int)setPos1.zCoord);
						world.scheduleBlockUpdate(pos2, world.getBlockState(pos2).getBlock(), 0, 1);
					}
					pos2 = null;
				} else {
					while (!compare(setPos1, setPos2))
					{

						posToPlaceBlock = posToPlaceBlock.add(difference);
						if(!world.isRemote) {
							world.setBlockState(new BlockPos((int)posToPlaceBlock.xCoord, (int)posToPlaceBlock.yCoord, (int)posToPlaceBlock.zCoord),  Block.getBlockFromName(stack.getTagCompound().getString("material")).getStateFromMeta(stack.getTagCompound().getInteger("damage")), 2);
						}

					} 
				}

			}


		}
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}

	public boolean compare(Vec3d setPos1, Vec3d setPos2) {

		if(Math.abs(setPos1.xCoord) <= Math.abs(setPos2.xCoord) && Math.abs(setPos1.zCoord) <= Math.abs(setPos2.zCoord)) {
			return Math.abs(posToPlaceBlock.xCoord) >= Math.abs(setPos2.xCoord) && Math.abs(posToPlaceBlock.zCoord) >= Math.abs(setPos2.zCoord);
		} 
		else if(Math.abs(setPos1.xCoord) >= Math.abs(setPos2.xCoord) && Math.abs(setPos1.zCoord) >= Math.abs(setPos2.zCoord)) {
			return Math.abs(posToPlaceBlock.xCoord) <= Math.abs(setPos2.xCoord) && Math.abs(posToPlaceBlock.zCoord) <= Math.abs(setPos2.zCoord);
		} 
		else if(Math.abs(setPos1.xCoord) <= Math.abs(setPos2.xCoord) && Math.abs(setPos1.zCoord) >= Math.abs(setPos2.zCoord)) {
			return Math.abs(posToPlaceBlock.xCoord) >= Math.abs(setPos2.xCoord) && Math.abs(posToPlaceBlock.zCoord) <= Math.abs(setPos2.zCoord);
		} 
		else if(Math.abs(setPos1.xCoord) >= Math.abs(setPos2.xCoord) && Math.abs(setPos1.zCoord) <= Math.abs(setPos2.zCoord)) {
			return Math.abs(posToPlaceBlock.xCoord) <= Math.abs(setPos2.xCoord) && Math.abs(posToPlaceBlock.zCoord) >= Math.abs(setPos2.zCoord);
		}
		else {
			return true;
		}
	}



	/*	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
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
			if(pos.getX() < 0) {
				pos1 = new Vec3d(pos.getX()-0.5, pos.getY(), pos.getZ());
			}
			if(pos.getX() > 0) {
				pos1 = new Vec3d(pos.getX()+0.5, pos.getY(), pos.getZ());
			}
			if(pos.getZ() < 0) {
				pos1 = new Vec3d(pos.getX(), pos.getY(), pos.getZ()-0.5);
			}
			if(pos.getX() > 0) {
				pos1 = new Vec3d(pos.getX()-0.5, pos.getY(), pos.getZ()+0.5);
			}
			if(pos.getX() < 0 && pos.getZ() < 0) {
				pos1 = new Vec3d(pos.getX()-0.5, pos.getY(), pos.getZ()-0.5);
			}
			if(pos.getX() > 0 && pos.getZ() > 0) {
				pos1 = new Vec3d(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
			}
			if(pos.getX() < 0 && pos.getZ() > 0) {
				pos1 = new Vec3d(pos.getX()-0.5, pos.getY(), pos.getZ()+0.5);
			}
			if(pos.getX() > 0 && pos.getZ() < 0) {
				pos1 = new Vec3d(pos.getX()+0.5, pos.getY(), pos.getZ()-0.5);
			}

			NBTTagCompound subTag = itemstack.getTagCompound();
			subTag.setDouble("pos1x", pos1.xCoord);
			subTag.setDouble("pos1y", pos1.yCoord);
			subTag.setDouble("pos1z", pos1.zCoord);
			subTag.setBoolean("hasSetPos1", true);
			itemstack.setTagCompound(subTag);

			if(!world.isRemote) {
				player.addChatComponentMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE +"Set Pos1 at X: " + (int)pos1.xCoord + ", Y: " + (int)pos1.yCoord + ", Z: " + (int)pos1.zCoord));
			}
			world.scheduleBlockUpdate(pos, world.getBlockState(pos).getBlock(), 0, 1);

		}

		return true;
	}

	 */

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		Block block = Block.getBlockFromName(stack.getTagCompound().getString("material"));
		IBlockState state = block.getStateFromMeta(stack.getTagCompound().getInteger("damage"));
		ItemStack blockStack = new ItemStack(block, 1, block.getMetaFromState(state));
		
		
		tooltip.add("Draws a straight line from two points");
		if(Item.getItemFromBlock(block) != null)
		{
			tooltip.add("Material: " + blockStack.getDisplayName());
		}
		else
		{
			tooltip.add("Material: " + I18n.translateToLocal(block.getUnlocalizedName() + ".name"));
		}
		tooltip.add("Pos1 = X: " + stack.getTagCompound().getDouble("pos1x") + ", Y: " + stack.getTagCompound().getDouble("pos1y") + ", Z: " + stack.getTagCompound().getDouble("pos1z"));
		tooltip.add("Pos2 = X: " + stack.getTagCompound().getDouble("pos2x") + ", Y: " + stack.getTagCompound().getDouble("pos2y") + ", Z: " + stack.getTagCompound().getDouble("pos2z"));

	}


}
