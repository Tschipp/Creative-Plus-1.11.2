package tschipp.creativePlus;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import tschipp.creativePlus.Proxies.CommonProxy;
import tschipp.creativePlus.events.MouseScroll;
import tschipp.creativePlus.events.MouseScroll2;
import tschipp.creativePlus.items.CustomItems;
import tschipp.creativePlus.items.creativeTabs.Tabs;
import tschipp.creativePlus.network.NoisePacket;
import tschipp.creativePlus.network.NoisePacketHandler;
import tschipp.creativePlus.network.RadiusPacket;
import tschipp.creativePlus.network.RadiusPacketHandler;

@Mod(modid = "creativeplus", name = "Creative +", version = "1.0.1")


public class CreativePlus {

	@Instance(value = "creativeplus")
	public static CreativePlus instance;

	public static final String CLIENT_PROXY = "tschipp.creativePlus.Proxies.ClientProxy";
	public static final String COMMON_PROXY = "tschipp.creativePlus.Proxies.CommonProxy";


	@SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
	public static CommonProxy proxy;

	public static SimpleNetworkWrapper network;

	public static boolean enableGodSword;
	public static boolean enableGodRing;
	public static boolean enableFlyingRing;
	public static boolean enableSuperBonemeal;
	public static boolean enableAirPlacer;
	public static boolean enableFlyingSpeedAjuster;
	public static boolean enableWalkingSpeedAjuster;
	public static boolean enablePointWand;
	public static boolean enableCircleWand;
	public static boolean enableSpawnerMinecart;
	public static boolean enableFillWand;
	public static boolean enableNoiseWand;
	public static boolean enableInstaPick;
	public static boolean enableBreakWand;
	public static boolean enableBreakWandParticles;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		config.load();
		enableInstaPick = config.getBoolean("enableInstaPick", Configuration.CATEGORY_GENERAL, true, "Enable/Disable God Pickaxe");	
		enableGodSword = config.getBoolean("enableGodSword", Configuration.CATEGORY_GENERAL, true, "Enable/Disable God Sword");
		enableGodRing = config.getBoolean("enableGodRing", Configuration.CATEGORY_GENERAL, true, "Enable/Disable God Ring");
		enableFlyingRing = config.getBoolean("enableFlyingRing", Configuration.CATEGORY_GENERAL, true, "Enable/Disable Flying Ring");
		enableSuperBonemeal = config.getBoolean("enableSuperBonemeal", Configuration.CATEGORY_GENERAL, true, "Enable/Disable Super Bonemeal");
		enableAirPlacer = config.getBoolean("enableAirPlacer", Configuration.CATEGORY_GENERAL, true, "Enable/Disable Air Placer");
		enableFlyingSpeedAjuster = config.getBoolean("enableFlyingSpeedAjuster", Configuration.CATEGORY_GENERAL, true, "Enable/Disable Flying Speed Ajuster");
		enableWalkingSpeedAjuster = config.getBoolean("enableWalkingSpeedAjuster", Configuration.CATEGORY_GENERAL, true, "Enable/Disable Walking Speed Ajuster");
		enablePointWand = config.getBoolean("enablePointWand", Configuration.CATEGORY_GENERAL, true, "Enable/Disable Line Wand");	
		enableCircleWand = config.getBoolean("enableCircleWand", Configuration.CATEGORY_GENERAL, true, "Enable/Disable Circle Wand");	
		enableFillWand = config.getBoolean("enableFillWand", Configuration.CATEGORY_GENERAL, true, "Enable/Disable Fill Wand");	
		enableNoiseWand = config.getBoolean("enableNoiseWand", Configuration.CATEGORY_GENERAL, true, "Enable/Disable Noise Wand");	
		enableBreakWand = config.getBoolean("enableBreakWand", Configuration.CATEGORY_GENERAL, true, "Enable/Disable God Breaker");	
		enableBreakWandParticles = config.getBoolean("enableBreakWandParticles", Configuration.CATEGORY_GENERAL, true, "Enable/Disable Particles from God Breaker");	
		enableSpawnerMinecart = config.getBoolean("enableSpawnerMinecart", Configuration.CATEGORY_GENERAL, true, "Enable/Disable Spawner Minecart");	
		

		config.save();


		this.proxy.preInit(event);

		network = NetworkRegistry.INSTANCE.newSimpleChannel("CreativePlusChannel");

		network.registerMessage(NoisePacketHandler.class, NoisePacket.class, 0, Side.SERVER);
		network.registerMessage(RadiusPacketHandler.class, RadiusPacket.class, 1, Side.SERVER);


		Tabs.addToTabs();





	}


	@EventHandler
	public void init(FMLInitializationEvent event) {
		//Proxy, TileEntity, entity, GUI and Packet Registering	
		//All the Crafting Recipes
		this.proxy.init(event);

		MinecraftForge.EVENT_BUS.register(new MouseScroll());
		MinecraftForge.EVENT_BUS.register(new MouseScroll2());
		MinecraftForge.EVENT_BUS.register(this);


	}



	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		this.proxy.postInit(event);



	}


	public static CreativeTabs missingBlocks = new CreativeTabs("missingBlocks"){
		@Override
		public ItemStack getTabIconItem(){
			return new ItemStack(Blocks.COMMAND_BLOCK);
		}
	};
	
	
	 
	
	public static CreativeTabs creativePlus = new CreativeTabs("creativePlus"){
		@Override
		public ItemStack getTabIconItem(){
			if(!enableSuperBonemeal) {
				return new ItemStack(Blocks.BARRIER);
			} else {
				return new ItemStack(CustomItems.superBonemeal);
			}
		}
	};
	
	


	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent.LeftClickBlock event) {


		EntityPlayer player = event.getEntityPlayer();
		Item item = (player.getHeldItem(EnumHand.MAIN_HAND) == null) ? null : player.getHeldItem(EnumHand.MAIN_HAND).getItem();
		World world = player.world;
		BlockPos pos = event.getPos();
		ItemStack itemstack = player.getHeldItem(EnumHand.MAIN_HAND);
		Vec3d pos1 = null;
		boolean isCircleWand = item == CustomItems.circleWand;
		boolean isFillWand = item == CustomItems.floodFill;
		boolean isNoiseWand = item == CustomItems.noiseFill;
		boolean isLineWand = item == CustomItems.wand;


		if (event instanceof PlayerInteractEvent.LeftClickBlock &&( isCircleWand || isFillWand || isNoiseWand || isLineWand) && player.isSneaking()) 
		{

			IBlockState mat = world.getBlockState(pos);
			NBTTagCompound tag = itemstack.getTagCompound();
			tag.setString("material", mat.getBlock().getRegistryName().getResourceDomain() + ":" + mat.getBlock().getRegistryName().getResourcePath());
			tag.setInteger("damage", mat.getBlock().getMetaFromState(mat));
			itemstack.setTagCompound(tag);
			Block block = Block.getBlockFromName(itemstack.getTagCompound().getString("material"));
			IBlockState state = block.getStateFromMeta(itemstack.getTagCompound().getInteger("damage"));
			ItemStack blockStack = new ItemStack(Item.getItemFromBlock(block), 1, block.getMetaFromState(state));
			if(!world.isRemote) {

				player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE +"Material set to: " + blockStack.getDisplayName()));

			}

			event.setCanceled(true);

		}

		if (event instanceof PlayerInteractEvent.LeftClickBlock &&( isCircleWand || isLineWand) && !player.isSneaking()) 
		{


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

			if(isCircleWand){

				if(!world.isRemote) {
					player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE +"Set Center at X: " + (int)pos1.xCoord + ", Y: " + (int)pos1.yCoord + ", Z: " + (int)pos1.zCoord));
				}

			} else {

				if(!world.isRemote) {
					player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE +"Set Pos1 at X: " + (int)pos1.xCoord + ", Y: " + (int)pos1.yCoord + ", Z: " + (int)pos1.zCoord));
				}

			}

			event.setCanceled(true);
		}
	}







}
