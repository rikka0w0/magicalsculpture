package magicalsculpture.item;

import magicalsculpture.BlockRegistry;
import magicalsculpture.CreativeTab;
import magicalsculpture.block.BlockSculpture;
import magicalsculpture.block.EnumSculptureBlockType;
import magicalsculpture.block.TileEntitySculpture;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rikka.librikka.Utils;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBase;
import rikka.librikka.multiblock.MultiBlockStructure;

public class ItemChisel extends ItemBase implements ISimpleTexture {
    public ItemChisel() {
        super("chisel", false);
        setCreativeTab(CreativeTab.instance);
        setMaxStackSize(1);
        setMaxDamage(5);
    }

    @Override
    public String getIconName(int i) {
        return "chisel";
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        if(stack == null)
            return EnumActionResult.FAIL;

        if (stack.isEmpty())
            return EnumActionResult.FAIL;


        if (!world.isRemote) {
            IBlockState blockState = world.getBlockState(pos);
            if (blockState.getBlock() == BlockRegistry.blockSculpture) {
                EnumSculptureBlockType blockType = blockState.getValue(EnumSculptureBlockType.property);

                if (!blockType.formed) {
                    MultiBlockStructure.Result ret = BlockRegistry.blockSculpture.structureTemplate.attempToBuild(world, pos);
                    if (ret != null) {
                        EnumFacing playerSight = Utils.getPlayerSightHorizontal(player);
                        ret.createStructure();
                        stack.damageItem(1, player);

                        TileEntity te = world.getTileEntity(pos);
                        if (te instanceof TileEntitySculpture) {
                            BlockPos renderPos = ((TileEntitySculpture)te).getRenderPos();
                            te =  world.getTileEntity(renderPos);
                            if (te instanceof TileEntitySculpture.Render) {
//                                EnumFacing orientation = ((TileEntitySculpture.Render)te).getFacing();
//                                if (orientation == playerSight | orientation == playerSight.getOpposite())
                                    ((TileEntitySculpture.Render)te).setRenderFacing(playerSight);
                            }
                        }
                    }
                }
            }
        }

        return EnumActionResult.SUCCESS;
    }
}
