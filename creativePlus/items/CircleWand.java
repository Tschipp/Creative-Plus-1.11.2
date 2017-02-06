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

public class CircleWand extends Item {
	
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
					System.out.println(block.getMapColor(state));
					return 201196;
				} else {

					return block.getMapColor(state).colorValue;
				}
			} 

		}
		
	}


	public Vec3d pos1;
	public Vec3d pos2;
	public Vec3d radius;
	public Vec3d posToPlace;

	public IBlockState mat;

	public CircleWand() {
		this.setMaxStackSize(1);

	}




	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("material", "minecraft:stone");
		tag.setInteger("damage", 0);
		tag.setDouble("pos1x", 0);
		tag.setDouble("pos1y", 0);
		tag.setDouble("pos1z", 0);
		tag.setDouble("pos2x", 0);
		tag.setDouble("pos2y", 0);
		tag.setDouble("pos2z", 0);
		tag.setBoolean("hasSetPos1", false);
		tag.setBoolean("hasSetPos2", false);
		ItemStack stack = new ItemStack(item, 1, 0);
		stack.setTagCompound(tag);

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
				return "Circle Wand (" + blockStack.getDisplayName() +")";
			}
			else
			{
				return "Circle Wand (" +  I18n.translateToLocal(block.getUnlocalizedName() + ".name") + ")";
			}
			} else {
			return "" + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name");
		}
		
	} 


	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{	
		ItemStack stack = player.getHeldItem(hand);
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
			player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE +"Set Rim Point at X: " + (int)pos2.xCoord + ", Y: " + (int)pos2.yCoord + ", Z: " + (int)pos2.zCoord));
		}

		return EnumActionResult.FAIL;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);

		if(stack.getTagCompound().getBoolean("hasSetPos1") && stack.getTagCompound().getBoolean("hasSetPos2") && player.isSneaking()) {


			NBTTagCompound nbt = stack.getTagCompound();

			Vec3d pos1 = new Vec3d(nbt.getDouble("pos1x"), nbt.getDouble("pos1y"), nbt.getDouble("pos1z"));
			Vec3d pos2 = new Vec3d(nbt.getDouble("pos2x"), nbt.getDouble("pos2y"), nbt.getDouble("pos2z"));

			radius = pos1.subtractReverse(pos2);

			if(pos1.xCoord == pos2.xCoord && pos1.zCoord == pos2.zCoord) {

				for(int i = 0; i < 23040; i++) {
					radius = radius.rotatePitch(0.015625F);
					posToPlace = pos1.add(radius);
					if(!world.isRemote) {
						world.setBlockState(new BlockPos((int)posToPlace.xCoord, (int)posToPlace.yCoord, (int)posToPlace.zCoord), Block.getBlockFromName(stack.getTagCompound().getString("material")).getStateFromMeta(stack.getTagCompound().getInteger("damage")), 2);
						BlockPos pos_ = new BlockPos((int)posToPlace.xCoord, (int)posToPlace.yCoord, (int)posToPlace.zCoord);
						world.scheduleBlockUpdate(pos_, world.getBlockState(pos_).getBlock(), 0, 1);
					}


				}


			} else {
				for(int i = 0; i < 23040; i++) {
					radius = radius.rotateYaw(0.015625F);
					posToPlace = pos1.add(radius);
					if(!world.isRemote) {
						world.setBlockState(new BlockPos((int)posToPlace.xCoord, (int)posToPlace.yCoord, (int)posToPlace.zCoord),  Block.getBlockFromName(stack.getTagCompound().getString("material")).getStateFromMeta(stack.getTagCompound().getInteger("damage")), 2);
						BlockPos pos__ = new BlockPos((int)posToPlace.xCoord, (int)posToPlace.yCoord, (int)posToPlace.zCoord);
						world.scheduleBlockUpdate(pos__, world.getBlockState(pos__).getBlock(), 0, 1);
					}

				}

			}

		}

		return new ActionResult(EnumActionResult.PASS, stack);

	}




	/*	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
	{

		World world = player.worldObj;

		if(player.isSneaking()) {


			mat = world.getBlockState(pos);
			NBTTagCompound tag = itemstack.getTagCompound();
			tag.setString("material", mat.getBlock().getRegistryName().getResourceDomain() + ":" + mat.getBlock().getRegistryName().getResourcePath());
			tag.setInteger("damage", mat.getBlock().getMetaFromState(mat));
			itemstack.setTagCompound(tag);
			Block block = (Block)Block.REGISTRY.getObject(new ResourceLocation(itemstack.getTagCompound().getString("material")));
			if(!world.isRemote) {

				player.addChatComponentMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE +"Material set to: " + net.minecraft.util.text.translation.I18n.translateToLocal(net.minecraft.util.text.translation.I18n.translateToLocal(block.getLocalizedName()))));

			}


		world.scheduleBlockUpdate(pos, world.getBlockState(pos).getBlock(), 1, 10000);


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
			player.addChatComponentMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE +"Set Center at X: " + (int)pos1.xCoord + ", Y: " + (int)pos1.yCoord + ", Z: " + (int)pos1.zCoord));
		}
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
		
		tooltip.add("Draws a circle");
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


