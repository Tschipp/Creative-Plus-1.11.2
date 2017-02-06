package tschipp.creativePlus.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemRenderRegister {
	
	
	
	public static void registerItems() {
		
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

		ModelBakery.registerItemVariants(CustomItems.doubleStoneSlab, new ModelResourceLocation("creativeplus:double_stone_slab" , "inventory"),new ModelResourceLocation("creativeplus:double_stone_slab8", "inventory"), new ModelResourceLocation("creativeplus:double_stone_slab9", "inventory"));
		ModelBakery.registerItemVariants(CustomItems.lit_furnace, new ModelResourceLocation("creativeplus:lit_furnace", "inventory"));
		ModelBakery.registerItemVariants(CustomItems.doubleStoneSlab2, new ModelResourceLocation("creativeplus:double_stone_slab2" , "inventory"));
		ModelBakery.registerItemVariants(CustomItems.logs, new ModelResourceLocation("creativeplus:logs1" , "inventory"), new ModelResourceLocation("creativeplus:logs2" , "inventory"), new ModelResourceLocation("creativeplus:logs3" , "inventory"), new ModelResourceLocation("creativeplus:logs4" , "inventory"));
		ModelBakery.registerItemVariants(CustomItems.logs2, new ModelResourceLocation("creativeplus:logs2_1" , "inventory"), new ModelResourceLocation("creativeplus:logs2_2" , "inventory"));
		ModelBakery.registerItemVariants(CustomItems.farmland, new ModelResourceLocation("creativeplus:farmland_wet" , "inventory"), new ModelResourceLocation("creativeplus:farmland" , "inventory"));
		
	
		ModelLoader.setCustomModelResourceLocation(CustomItems.flyingSpeed, 0, new ModelResourceLocation("creativeplus:flyingspeed", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.walkSpeed, 0, new ModelResourceLocation("creativeplus:walkspeed", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.superBonemeal, 0, new ModelResourceLocation("creativeplus:superbonemeal", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.godSword, 0, new ModelResourceLocation("creativeplus:godsword", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.wand, 0, new ModelResourceLocation("creativeplus:pointwand", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.godRing, 0, new ModelResourceLocation("creativeplus:godring", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.circleWand, 0, new ModelResourceLocation("creativeplus:circlewand", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.flyingRing, 0, new ModelResourceLocation("creativeplus:flyingring", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.airPlacer, 0, new ModelResourceLocation("creativeplus:airplacer", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.floodFill, 0, new ModelResourceLocation("creativeplus:floodfill", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.noiseFill, 0, new ModelResourceLocation("creativeplus:noisefill", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.instaPick, 0, new ModelResourceLocation("creativeplus:instapick", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.breaker, 0, new ModelResourceLocation("creativeplus:breaker", "inventory"));

	
		ModelLoader.setCustomModelResourceLocation(CustomItems.portal, 0, new ModelResourceLocation("creativeplus:portal", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.endPortal, 0, new ModelResourceLocation("creativeplus:end_portal", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.endGateway, 0, new ModelResourceLocation("creativeplus:portal", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.water, 0, new ModelResourceLocation("creativeplus:water", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.lava, 0, new ModelResourceLocation("creativeplus:lava", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.fire, 0, new ModelResourceLocation("creativeplus:fire", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.spawnerMinecart,0, new ModelResourceLocation("creativeplus:spawnerMinecart", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleStoneSlab,0, new ModelResourceLocation("creativeplus:double_stone_slab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleStoneSlab,1, new ModelResourceLocation("minecraft:sandstone", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleStoneSlab,2, new ModelResourceLocation("minecraft:oak_planks", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleStoneSlab,3, new ModelResourceLocation("minecraft:cobblestone", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleStoneSlab,4, new ModelResourceLocation("minecraft:brick_block", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleStoneSlab,5, new ModelResourceLocation("minecraft:stonebrick", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleStoneSlab,6, new ModelResourceLocation("minecraft:nether_brick", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleStoneSlab,7, new ModelResourceLocation("minecraft:quartz_block", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleStoneSlab,8, new ModelResourceLocation("creativeplus:double_stone_slab8", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleStoneSlab,9, new ModelResourceLocation("creativeplus:double_stone_slab9", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.lit_furnace,0, new ModelResourceLocation("creativeplus:lit_furnace", "inventory"));
		//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Blocks.STONE_SLAB),2, new ModelResourceLocation("minecraft:oak_slab", "inventory"));	
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleWoodenSlab,0, new ModelResourceLocation("minecraft:oak_planks", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleWoodenSlab,1, new ModelResourceLocation("minecraft:spruce_planks", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleWoodenSlab,2, new ModelResourceLocation("minecraft:birch_planks", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleWoodenSlab,3, new ModelResourceLocation("minecraft:jungle_planks", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleWoodenSlab,4, new ModelResourceLocation("minecraft:acacia_planks", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleWoodenSlab,5, new ModelResourceLocation("minecraft:dark_oak_planks", "inventory"));		
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleStoneSlab2,0, new ModelResourceLocation("minecraft:red_sandstone", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doubleStoneSlab2,8, new ModelResourceLocation("creativeplus:double_stone_slab2", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.doublePurpurSlab,0, new ModelResourceLocation("purpur_block", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.logs,12, new ModelResourceLocation("creativeplus:logs1", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.logs,13, new ModelResourceLocation("creativeplus:logs2", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.logs,14, new ModelResourceLocation("creativeplus:logs3", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.logs,15, new ModelResourceLocation("creativeplus:logs4", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.logs2,12, new ModelResourceLocation("creativeplus:logs2_1", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.logs2,13, new ModelResourceLocation("creativeplus:logs2_2", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.farmland,0, new ModelResourceLocation("creativeplus:farmland", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.farmland,7, new ModelResourceLocation("creativeplus:farmland_wet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(CustomItems.tnt,1, new ModelResourceLocation("minecraft:tnt", "inventory"));

		

	}
	

}
