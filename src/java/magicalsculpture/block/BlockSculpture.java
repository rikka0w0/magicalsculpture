package magicalsculpture.block;

import magicalsculpture.CreativeTab;
import magicalsculpture.GuiHandler;
import magicalsculpture.ItemRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.Utils;
import rikka.librikka.block.BlockBase;
import rikka.librikka.block.ISubBlock;
import rikka.librikka.item.ItemBlockBase;
import rikka.librikka.multiblock.BlockMapping;
import rikka.librikka.multiblock.MultiBlockStructure;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockSculpture extends BlockBase implements ISubBlock {
    private static final String[] subNames = EnumSculptureBlockType.getRawStructureNames();
    public final MultiBlockStructure structureTemplate;

    public BlockSculpture()  {
        super("magicalsculpture", Material.ROCK, ItemBlockBase.class);
        this.structureTemplate = this.createStructureTemplate();
        setCreativeTab(CreativeTab.instance);
        setHardness(3.0F);
        setResistance(5.0F);
    }

    @Override
    public String[] getSubBlockUnlocalizedNames() {
        return this.subNames;
    }

    private MultiBlockStructure createStructureTemplate() {
        //y,z,x facing NORTH(Z-), do not change
        BlockMapping[][][] configuration = new BlockMapping[5][][];

        BlockMapping base2base = new BlockMapping(stateFromType(EnumSculptureBlockType.Base), stateFromType(EnumSculptureBlockType.FormedBase));
        BlockMapping base2render = new BlockMapping(stateFromType(EnumSculptureBlockType.Base), stateFromType(EnumSculptureBlockType.Render));
        BlockMapping stone2stone = new BlockMapping(stateFromType(EnumSculptureBlockType.Stone), stateFromType(EnumSculptureBlockType.FormedStone));


        //  .-->x+ (East)
        //  |                           Facing/Looking at North(x-)
        // \|/
        //  z+ (South)
        configuration[0] = new BlockMapping[][]{
                {base2render, base2base},
                {base2base  , base2base}
        };

        configuration[1] = new BlockMapping[][]{
                {stone2stone, stone2stone},
                {stone2stone, stone2stone}
        };

        configuration[2] = new BlockMapping[][]{
                {stone2stone, stone2stone},
                {stone2stone, stone2stone}
        };

        configuration[3] = new BlockMapping[][]{
                {stone2stone, stone2stone},
                {stone2stone, stone2stone}
        };

        configuration[4] = new BlockMapping[][]{
                {stone2stone, stone2stone},
                {stone2stone, stone2stone}
        };


        return new MultiBlockStructure(configuration);
    }

    ///////////////////////////////
    /// TileEntity
    ///////////////////////////////
    @Override
    public boolean hasTileEntity(IBlockState state) {return true;}

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        EnumSculptureBlockType blockType = state.getValue(EnumSculptureBlockType.property);

        if (!blockType.formed)
            return null;

        switch (blockType) {
            case FormedBase:
                return new TileEntitySculpture.PlaceHolderBase();
            case FormedStone:
                return new TileEntitySculpture.PlaceHolderStone();
            case Render:
                return new TileEntitySculpture.Render();
            default:
                return null;
        }
    }

    ///////////////////////////////
    ///BlockStates
    ///////////////////////////////
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, EnumSculptureBlockType.property);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.stateFromType(EnumSculptureBlockType.fromInt(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(EnumSculptureBlockType.property).index;
    }

    public IBlockState stateFromType(EnumSculptureBlockType blockType) {
        return getDefaultState().withProperty(EnumSculptureBlockType.property, blockType);
    }

    private ItemStack getItemToDrop(IBlockState state) {
        EnumSculptureBlockType blockType = state.getValue(EnumSculptureBlockType.property);

        if (blockType.formed)
            return ItemStack.EMPTY;

        return new ItemStack(this.itemBlock, 1, this.getMetaFromState(state));
    }

    @Override
    public IBlockState getActualState(@Nonnull IBlockState state, IBlockAccess world, BlockPos pos) {
//        TileEntity te = world.getTileEntity(pos);
//        if (te instanceof TileEntitySculpture) {
//            TileEntitySculpture render = (TileEntitySculpture) te;
//            EnumFacing facing = render.getFacing();
//            if (facing == null) {
//                return state; //Prevent crashing!
//            }
//
//            state = state.withProperty(BlockHorizontal.FACING, facing);
//        }
        return state;
    }

    ///////////////////////////////
    /// Block activities
    ///////////////////////////////
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (player.isSneaking())
            return false;

        EnumSculptureBlockType blockType = state.getValue(EnumSculptureBlockType.property);
        if (!blockType.formed)
            return false;


        //When openGui() is call on the server side, Forge seems automatically send a packet to client side
        //in order to notify the client to set up the container and show the Gui.
        if (!world.isRemote) {
            GuiHandler.openGui(player, world, pos, facing);
        }

        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity te = world.getTileEntity(pos);
        if (te != null) {
            this.structureTemplate.restoreStructure(te, state, true);
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getItemToDrop(state).getItemDamage();
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();

        ItemStack itemStack = getItemToDrop(state);
        if (itemStack.getItem() != Items.AIR)
            ret.add(itemStack);

        return ret;
    }

    /**
     * Creative-mode middle mouse button clicks
     */
    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return getItemToDrop(state);
    }

    ////////////////////////////////////
    /// Rendering
    ////////////////////////////////////
    //This will tell minecraft not to render any side of our cube.
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        EnumSculptureBlockType blockType = blockState.getValue(EnumSculptureBlockType.property);
        return !blockType.formed;
    }

    //And this tell it that you can see through this block, and neighbor blocks should be rendered.
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}
