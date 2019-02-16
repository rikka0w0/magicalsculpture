package magicalsculpture;

import magicalsculpture.block.BlockSculpture;
import magicalsculpture.block.TileEntitySculpture;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import rikka.librikka.block.BlockBase;

public class BlockRegistry {
	public static BlockSculpture blockSculpture;

	
	public static void initBlocks() {
		blockSculpture = new BlockSculpture();
	}
	
	public static void registerBlocks(IForgeRegistry registry, boolean isItemBlock) {
		registerBlocks(registry, isItemBlock,
				blockSculpture
				);
	}
	
	public static void registerTileEntities() {
		registerTile(TileEntitySculpture.PlaceHolderBase.class);
		registerTile(TileEntitySculpture.PlaceHolderStone.class);
		registerTile(TileEntitySculpture.Render.class);
	}
	
    
    private static void registerBlocks(IForgeRegistry registry, boolean isItemBlock, BlockBase... blocks) {
    	if (isItemBlock) {
        	for (BlockBase block: blocks)
        		registry.register(block.itemBlock);
    	} else {
    		registry.registerAll(blocks);
    	}
    }
	
    private static void registerTile(Class<? extends TileEntity> teClass) {
        String registryName = teClass.getName();
        registryName = registryName.substring(registryName.lastIndexOf(".") + 1);
        registryName = MagicalSculpture.MODID + ":" + registryName;
        GameRegistry.registerTileEntity(teClass, registryName);
    }
}
