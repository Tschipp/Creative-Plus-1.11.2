package tschipp.creativePlus.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.creativePlus.CreativePlus;

public class FakeItemBlock extends ItemBlock
{
	public final Block block;


	public FakeItemBlock(Block block)
	{
		super(block);
		this.block = block;
		this.hasSubtypes = true;
	}

	/**
	 * Sets the unlocalized name of this item to the string passed as the parameter, prefixed by "item."
	 */


	/**
	 * Called when a Block is right-clicked with this Item
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = playerIn.getHeldItem(hand);
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if (!block.isReplaceable(worldIn, pos))
		{
			pos = pos.offset(side);
		}

		if (stack.getCount() == 0)
		{
			return EnumActionResult.FAIL;
		}
		else if (!playerIn.canPlayerEdit(pos, side, stack))
		{
			return EnumActionResult.FAIL;
		}
		else if (worldIn.mayPlace(this.block, pos, false, side, (Entity)null))
		{


			int j = stack.getItemDamage();
			//  IBlockState iblockstate1 = this.block.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, i, playerIn);

			if (placeBlockAt(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ, this.block.getStateFromMeta(j)))
			{
				SoundType soundtype = this.block.getSoundType();
				worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
				stack.shrink(1);
			} 




			return EnumActionResult.SUCCESS;
		}
		else
		{
			return EnumActionResult.FAIL;
		}
	}

	public static boolean setTileEntityNBT(World worldIn, @Nullable EntityPlayer player, BlockPos pos, ItemStack stackIn)
	{
		MinecraftServer minecraftserver = worldIn.getMinecraftServer();

		if (minecraftserver == null)
		{
			return false;
		}
		else
		{
			if (stackIn.hasTagCompound() && stackIn.getTagCompound().hasKey("BlockEntityTag", 10))
			{
				TileEntity tileentity = worldIn.getTileEntity(pos);

				if (tileentity != null)
				{
					if (!worldIn.isRemote && tileentity.onlyOpsCanSetNbt() && (player == null || !minecraftserver.getPlayerList().canSendCommands(player.getGameProfile())))
					{
						return false;
					}


					NBTTagCompound nbttagcompound = tileentity.writeToNBT(new NBTTagCompound());
					NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttagcompound.copy();
					NBTTagCompound nbttagcompound2 = (NBTTagCompound)stackIn.getTagCompound().getTag("BlockEntityTag");
					nbttagcompound.merge(nbttagcompound2);
					nbttagcompound.setInteger("x", pos.getX());
					nbttagcompound.setInteger("y", pos.getY());
					nbttagcompound.setInteger("z", pos.getZ());

					if (!nbttagcompound.equals(nbttagcompound1))
					{
						tileentity.readFromNBT(nbttagcompound);
						tileentity.markDirty();
						return true;
					}
				}
			}

			return false;
		}
	}


	@SideOnly(Side.CLIENT)
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack)
	{
		Block block = worldIn.getBlockState(pos).getBlock();

		if (block == Blocks.SNOW_LAYER && block.isReplaceable(worldIn, pos))
		{
			side = EnumFacing.UP;
		}
		else if (!block.isReplaceable(worldIn, pos))
		{
			pos = pos.offset(side);
		}

		return worldIn.mayPlace(this.block, pos, false, side, (Entity)null);
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
	 * different names based on their damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return this.block.getUnlocalizedName();
	}

	/**
	 * Returns the unlocalized name of this item.
	 */
	@Override
	public String getUnlocalizedName()
	{
		return this.block.getUnlocalizedName();
	}

	/**
	 * gets the CreativeTab this item is displayed on
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public CreativeTabs getCreativeTab()
	{
		return CreativePlus.missingBlocks;
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems)
	{
		

		if(this.block instanceof BlockStoneSlab) {

			for(int i = 0; i < 10; i++)
				subItems.add(new ItemStack(itemIn, 1, i));
		} else if(this.block instanceof BlockOldLog) {

			for(int i = 12; i < 16; i++) {
				subItems.add(new ItemStack(itemIn, 1, i));
			}
		}
		else if(this.block instanceof BlockNewLog) {

			for(int i = 12; i < 14; i++) {
				subItems.add(new ItemStack(itemIn, 1, i));
			}
		} else if(this.block instanceof BlockFarmland) {
			subItems.add(new ItemStack(itemIn, 1, 0));
			subItems.add(new ItemStack(itemIn, 1, 7));

		}
		else {

			this.block.getSubBlocks(itemIn, tab, subItems);

		}

		if(this.block instanceof BlockStoneSlabNew) {

			subItems.add(new ItemStack(itemIn, 1, 0));
			subItems.add(new ItemStack(itemIn, 1, 8));


		}
		if(this.block instanceof BlockWoodSlab) {

			for(int i = 0; i < 6; i++) {
				subItems.add(new ItemStack(itemIn, 1, i));
			}

		}



	}

	public Block getBlock()
	{
		return this.block;
	}

	/**
	 * Called to actually place the block, after the location is determined
	 * and all permission checks have been made.
	 *
	 * @param stack The item stack that was used to place the block. This can be changed inside the method.
	 * @param player The player who is placing the block. Can be null if the block is not being placed by a player.
	 * @param side The side the player (or machine) right-clicked on.
	 */
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
	{
		if (!world.setBlockState(pos, newState, 3)) return false;

		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == this.block)
		{
			// setTileEntityNBT(world, player, pos, stack);
			this.block.onBlockPlacedBy(world, pos, state, player, stack);
		}

		return true;
	}
}