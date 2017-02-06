package tschipp.creativePlus.items.creativeTabs;

import tschipp.creativePlus.CreativePlus;
import tschipp.creativePlus.items.CustomItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Tabs {

	static CreativeTabs missingBlocks = CreativePlus.missingBlocks;
	static CreativeTabs creativePlus = CreativePlus.creativePlus;


	public static void addToTabs() {

		Blocks.COMMAND_BLOCK.setCreativeTab(missingBlocks);
		Blocks.REPEATING_COMMAND_BLOCK.setCreativeTab(missingBlocks);
		Blocks.CHAIN_COMMAND_BLOCK.setCreativeTab(missingBlocks);
		Blocks.STRUCTURE_BLOCK.setCreativeTab(missingBlocks);
		Blocks.STRUCTURE_VOID.setCreativeTab(missingBlocks);
		Blocks.MOB_SPAWNER.setCreativeTab(missingBlocks);
		Blocks.BROWN_MUSHROOM_BLOCK.setCreativeTab(missingBlocks);
		Blocks.RED_MUSHROOM_BLOCK.setCreativeTab(missingBlocks);
		Blocks.PORTAL.setCreativeTab(missingBlocks);
		Blocks.END_PORTAL.setCreativeTab(missingBlocks);
		Blocks.DRAGON_EGG.setCreativeTab(missingBlocks);
		Blocks.BARRIER.setCreativeTab(missingBlocks);
		Blocks.FIRE.setCreativeTab(missingBlocks);
		Blocks.DOUBLE_STONE_SLAB.setCreativeTab(missingBlocks);
		Blocks.DOUBLE_STONE_SLAB2.setCreativeTab(missingBlocks);
		Blocks.DOUBLE_WOODEN_SLAB.setCreativeTab(missingBlocks);
		Blocks.LAVA.setCreativeTab(missingBlocks);
		Blocks.WATER.setCreativeTab(missingBlocks);
		Blocks.LIT_FURNACE.setCreativeTab(missingBlocks);
		Items.COMMAND_BLOCK_MINECART.setCreativeTab(missingBlocks);
		Blocks.PURPUR_DOUBLE_SLAB.setCreativeTab(missingBlocks);
		Blocks.END_GATEWAY.setCreativeTab(missingBlocks);


		if(CreativePlus.enableFlyingRing) {
			CustomItems.flyingSpeed.setCreativeTab(creativePlus);
		}
		if(CreativePlus.enableFillWand) {
			CustomItems.floodFill.setCreativeTab(creativePlus);
		}

		if(CreativePlus.enableNoiseWand) {
			CustomItems.noiseFill.setCreativeTab(creativePlus);
		}

		if(CreativePlus.enableBreakWand) {
			CustomItems.breaker.setCreativeTab(creativePlus);
		}

		if(CreativePlus.enableWalkingSpeedAjuster) {
			CustomItems.walkSpeed.setCreativeTab(creativePlus);
		}

		if(CreativePlus.enableSuperBonemeal) {
			CustomItems.superBonemeal.setCreativeTab(creativePlus);
		}

		if(CreativePlus.enableInstaPick) {
			CustomItems.instaPick.setCreativeTab(creativePlus);
		}

		if(CreativePlus.enableGodSword) {
			CustomItems.godSword.setCreativeTab(creativePlus);
		}

		if(CreativePlus.enablePointWand) {
			CustomItems.wand.setCreativeTab(creativePlus);
		}

		if(CreativePlus.enableGodRing) {
			CustomItems.godRing.setCreativeTab(creativePlus);
		}

		if(CreativePlus.enableCircleWand) {
			CustomItems.circleWand.setCreativeTab(creativePlus);
		}

		if(CreativePlus.enableFlyingRing) {
			CustomItems.flyingRing.setCreativeTab(creativePlus);
		}

		if(CreativePlus.enableSpawnerMinecart) {
			CustomItems.spawnerMinecart.setCreativeTab(creativePlus);
		}

		if(CreativePlus.enableAirPlacer) {
			CustomItems.airPlacer.setCreativeTab(creativePlus);
		}

		CustomItems.lit_furnace.setCreativeTab(missingBlocks);
		CustomItems.fire.setCreativeTab(missingBlocks);
		CustomItems.tnt.setCreativeTab(missingBlocks);
		CustomItems.endPortal.setCreativeTab(missingBlocks);
		CustomItems.portal.setCreativeTab(missingBlocks);
		CustomItems.endGateway.setCreativeTab(missingBlocks);
		CustomItems.doubleStoneSlab.setCreativeTab(missingBlocks);
		CustomItems.doubleStoneSlab2.setCreativeTab(missingBlocks);
		CustomItems.doubleWoodenSlab.setCreativeTab(missingBlocks);
		CustomItems.doublePurpurSlab.setCreativeTab(missingBlocks);
		CustomItems.water.setCreativeTab(missingBlocks);
		CustomItems.lava.setCreativeTab(missingBlocks);
		CustomItems.logs.setCreativeTab(missingBlocks);
		CustomItems.logs2.setCreativeTab(missingBlocks);
		CustomItems.farmland.setCreativeTab(missingBlocks);



	}

}
