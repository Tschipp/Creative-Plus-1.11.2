package tschipp.creativePlus.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.Item;

public class FakeSlab extends Item{

	public final Block block;
	public final int meta;
	
	public FakeSlab(Block block, int meta) {
		
		this.block = block;
		this.meta = meta;
		
		
		
	}
	
	
	public FakeSlab setUnlocalizedName(String unlocalizedName)
    {
        super.setUnlocalizedName(unlocalizedName);
        return this;
    }

}
