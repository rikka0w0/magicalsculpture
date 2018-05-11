package magicalsculpture.client;

import magicalsculpture.block.BlockSculpture;
import magicalsculpture.block.EnumSculptureBlockType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.block.MetaBlock;
import rikka.librikka.model.GhostModel;
import rikka.librikka.model.SingleTextureModel;
import rikka.librikka.model.loader.IModelLoader;

@SideOnly(Side.CLIENT)
public class CustomStateMapper extends StateMapperBase implements IModelLoader {
    public static final String VPATH = "virtual/blockstates";
    public final String domain;

    public CustomStateMapper(String domain) {
        this.domain = domain;
        OBJLoader.INSTANCE.addDomain(domain);
    }

    @Override
    public boolean accepts(String resPath) {
        return resPath.startsWith(VPATH);
    }
    
    @Override
    public ModelResourceLocation getModelResourceLocation(IBlockState state) {
        Block block = state.getBlock();
        String blockDomain = block.getRegistryName().getResourceDomain();
        String blockName = block.getRegistryName().getResourcePath();

        String varStr = "";
        
        if (block instanceof BlockSculpture) {
        	EnumFacing rotation = state.getValue(BlockHorizontal.FACING);
            EnumSculptureBlockType blockType = state.getValue(EnumSculptureBlockType.property);

        	varStr = rotation.name() + "," + String.valueOf(blockType.index);
        }
        
        ModelResourceLocation res = new ModelResourceLocation(domain + ":" + VPATH,
                blockDomain + "," + blockName + "," + varStr);
        return res;
    }
    
    @Override
    public IModel loadModel(String domain, String resPath, String variantStr) throws Exception {
        String[] splited = variantStr.split(",");
        String blockDomain = splited[0];
        String blockName = splited[1];
        Block block = Block.getBlockFromName(blockDomain + ":" + blockName);

        if (block instanceof BlockSculpture) {
            EnumFacing rotation = EnumFacing.byName(splited[2]);
            EnumSculptureBlockType blockType = EnumSculptureBlockType.fromInt(Integer.parseInt(splited[3]));

            switch (blockType) {
                case Base:
                    return new SingleTextureModel(blockDomain, "base", true);
                case Stone:
                    return new SingleTextureModel(blockDomain, "stone", true);
                case Render:
                    return new SculptureModel(rotation.ordinal()-2);
                default:
                    return new GhostModel("blocks/stone");
            }
        }
        
        return null;
    }
    
    public void register(Block block) {
        ModelLoader.setCustomStateMapper(block, this);
    }
    
    public void register3D(MetaBlock block) {
    	ModelLoader.setCustomStateMapper(block, this);
    	
    	for (int i: block.propertyMeta.getAllowedValues()) {
    		ModelLoader.setCustomModelResourceLocation(block.itemBlock, i, 
    				this.getModelResourceLocation(block.getStateFromMeta(i)));
    	}
    }

    public void register(BlockSculpture block) {
        ModelLoader.setCustomStateMapper(block, this);

        ItemBlock itemBlock = block.itemBlock;
        for (EnumSculptureBlockType blockType : EnumSculptureBlockType.values) {
            IBlockState blockState = block.stateFromType(blockType);
            int meta = block.getMetaFromState(blockState);
            ModelResourceLocation res = getModelResourceLocation(blockState);
            //Also register inventory variants here
            ModelLoader.setCustomModelResourceLocation(itemBlock, meta, res);
        }
    }
}
