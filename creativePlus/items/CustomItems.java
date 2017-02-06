package tschipp.creativePlus.items;

import net.minecraft.block.BlockDoubleStoneSlabNew;
import net.minecraft.block.BlockDoubleWoodSlab;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tschipp.creativePlus.CreativePlus;

public class CustomItems {


	public static Item flyingSpeed;
	public static Item walkSpeed;
	public static Item superBonemeal;
	public static Item wand;
	public static Item circleWand;
	public static Item floodFill;
	public static Item noiseFill;
	public static Item breaker;
	public static Item airPlacer;
	public static ItemMinecart spawnerMinecart;
	public static Item instaPick;
	public static ItemSword godSword;
	public static Item godRing;
	public static Item flyingRing;

	public static FakeItemBlock lit_furnace;
	public static FakeItemBlock fire;
	public static FakeItemBlock endPortal;
	public static FakeItemBlock portal;
	public static FakeItemBlock endGateway;
	public static FakeItemBlock doubleStoneSlab;
	public static FakeItemBlock doubleStoneSlab2;
	public static FakeItemBlock doublePurpurSlab;
	public static FakeItemBlock doubleWoodenSlab;
	public static FakeItemBlock water;
	public static FakeItemBlock lava;
	public static FakeItemNotBlock logs;
	public static FakeItemNotBlock logs2;
	public static FakeItemBlock farmland;
	public static FakeItemNotBlock tnt;






	public static ToolMaterial godMaterial = EnumHelper.addToolMaterial("godMaterial", 100, 1000000000, 25000.0F, 1000000000000000000000.0F, 0);


	public static void createItems() {

		if(CreativePlus.enableFlyingSpeedAjuster) {
			GameRegistry.register(flyingSpeed = new FlyingSpeed("flyingSpeed"), new ResourceLocation("creativeplus:" + "flyingSpeed"));
		}

		if(CreativePlus.enableWalkingSpeedAjuster) {
			GameRegistry.register(walkSpeed = new WalkSpeed("walkSpeed"), new ResourceLocation("creativeplus:" + "walkSpeed"));
		}

		if(CreativePlus.enableSuperBonemeal) {
			GameRegistry.register(superBonemeal = new SuperBonemeal("superBonemeal"), new ResourceLocation("creativeplus:" + "superBonemeal"));
		}

		if(CreativePlus.enableSpawnerMinecart) {
			GameRegistry.register(spawnerMinecart = (ItemMinecart) new ItemMinecart(EntityMinecart.Type.SPAWNER).setUnlocalizedName("spawnerMinecart"), new ResourceLocation("creativeplus:" + "spawnerMinecart"));
		}

		if(CreativePlus.enablePointWand) {
			GameRegistry.register(wand = new Wand().setUnlocalizedName("pointWand"),new ResourceLocation("creativeplus:" +  "pointWand"));
		}

		if(CreativePlus.enableCircleWand) {
			GameRegistry.register(circleWand = new CircleWand().setUnlocalizedName("circleWand"), new ResourceLocation("creativeplus:" + "circleWand"));
		}

		if(CreativePlus.enableFillWand) {
			GameRegistry.register(floodFill = new FloodFill().setUnlocalizedName("floodFill"), new ResourceLocation("creativeplus:" + "floodFill"));
		}

		if(CreativePlus.enableNoiseWand) {
			GameRegistry.register(noiseFill = new NoiseFill().setUnlocalizedName("noiseFill"), new ResourceLocation("creativeplus:" + "noiseFill"));
		}

		if(CreativePlus.enableAirPlacer) {
			GameRegistry.register(airPlacer = new AirPlacer().setUnlocalizedName("airPlacer"),new ResourceLocation("creativeplus:" +  "airPlacer"));
		}

		if(CreativePlus.enableInstaPick) {
			GameRegistry.register(instaPick = new InstaPick().setUnlocalizedName("instaPick"), new ResourceLocation("creativeplus:" + "instaPick"));
		}

		if(CreativePlus.enableBreakWand) {
			GameRegistry.register(breaker = new BreakWand().setUnlocalizedName("breaker"), new ResourceLocation("creativeplus:" + "breaker"));
		}

		if(CreativePlus.enableGodSword) {
			GameRegistry.register(godSword = (ItemSword) new GodSword(godMaterial).setUnlocalizedName("godSword"),new ResourceLocation("creativeplus:" +  "godSword"));
		}

		if(CreativePlus.enableGodRing) {
			GameRegistry.register(godRing = new GodRing().setUnlocalizedName("godRing"),new ResourceLocation("creativeplus:" +  "godRing"));
		}

		if(CreativePlus.enableFlyingRing) {
			GameRegistry.register(flyingRing = new FlyingRing().setUnlocalizedName("flyingRing"),new ResourceLocation("creativeplus:" +  "flyingRing"));
		}


		GameRegistry.register(lit_furnace = new FakeItemBlock(Blocks.LIT_FURNACE),  new ResourceLocation("creativeplus:" + "lit_furnace"));
		GameRegistry.register(tnt = new FakeItemNotBlock(Blocks.TNT),new ResourceLocation("creativeplus:" +  "tnt"));
		GameRegistry.register(fire = new FakeItemBlock(Blocks.FIRE), new ResourceLocation("creativeplus:" + "fire"));
		GameRegistry.register(farmland = new FakeItemBlock(Blocks.FARMLAND),new ResourceLocation("creativeplus:" +  "farmland"));
		GameRegistry.register(endPortal = new FakeItemBlock(Blocks.END_PORTAL.setUnlocalizedName("end_portal")),new ResourceLocation("creativeplus:" +  "end_portal"));
		GameRegistry.register(portal = new FakeItemBlock(Blocks.PORTAL),new ResourceLocation("creativeplus:" +  "portal"));
		GameRegistry.register(endGateway = new FakeItemBlock(Blocks.END_GATEWAY.setUnlocalizedName("end_gateway")),new ResourceLocation("creativeplus:" +  "end_gateway"));
		GameRegistry.register(doubleStoneSlab = new FakeItemBlock(Blocks.DOUBLE_STONE_SLAB),new ResourceLocation("creativeplus:" +  "double_stone_slab"));
		GameRegistry.register(doubleStoneSlab2 = new FakeItemBlock((BlockDoubleStoneSlabNew)Blocks.DOUBLE_STONE_SLAB2), new ResourceLocation("creativeplus:" + "double_stone_slab2"));
		GameRegistry.register(doublePurpurSlab = new FakeItemBlock(Blocks.PURPUR_DOUBLE_SLAB),new ResourceLocation("creativeplus:" +  "double_purpur_slab"));
		GameRegistry.register(doubleWoodenSlab = new FakeItemBlock((BlockDoubleWoodSlab)Blocks.DOUBLE_WOODEN_SLAB),new ResourceLocation("creativeplus:" +  "double_wooden_slab"));
		GameRegistry.register(logs = new FakeItemNotBlock(Blocks.LOG),new ResourceLocation("creativeplus:" +  "log"));
		GameRegistry.register(logs2 = new FakeItemNotBlock(Blocks.LOG2), new ResourceLocation("creativeplus:" + "log2"));
		GameRegistry.register(water = new FakeItemBlock(Blocks.WATER), new ResourceLocation("creativeplus:" + "water"));
		GameRegistry.register(lava = new FakeItemBlock(Blocks.LAVA), new ResourceLocation("creativeplus:" + "lava"));









	}

}
