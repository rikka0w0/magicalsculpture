package magicalsculpture.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.Utils;
import rikka.librikka.multiblock.IMultiBlockTile;
import rikka.librikka.multiblock.MultiBlockTileInfo;
import rikka.librikka.tileentity.TileEntityBase;

public abstract class TileEntitySculpture extends TileEntityBase implements IMultiBlockTile {
    public static class PlaceHolderBase extends TileEntitySculpture{
        @Override
        protected void onStructureCreating() {

        }

        @Override
        public void onStructureCreated() {

        }

        @Override
        public void onStructureRemoved() {

        }
    }

    public static class PlaceHolderStone extends TileEntitySculpture{
        @Override
        protected void onStructureCreating() {

        }

        @Override
        public void onStructureCreated() {

        }

        @Override
        public void onStructureRemoved() {

        }
    }

    public static class Render extends TileEntitySculpture {
        private EnumFacing facing;

        public void setRenderFacing(EnumFacing facing) {
            this.facing = facing;
            markForRenderUpdate();
        }

        public EnumFacing getRenderFacing() {
            return facing;
        }

        /////////////////////////////////////////////////////////
        /////Sync
        /////////////////////////////////////////////////////////
        @Override
        public void prepareS2CPacketData(NBTTagCompound nbt) {
            super.prepareS2CPacketData(nbt);
            Utils.saveToNbt(nbt, "facing", this.facing);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void onSyncDataFromServerArrived(NBTTagCompound nbt) {
            super.onSyncDataFromServerArrived(nbt);
            this.facing = Utils.facingFromNbt(nbt, "facing");
            markForRenderUpdate();
        }

        //////////////////////////////
        /////TileEntity
        //////////////////////////////
        @Override
        public void readFromNBT(NBTTagCompound nbt) {
            super.readFromNBT(nbt);
            this.facing = Utils.facingFromNbt(nbt, "facing");
        }

        @Override
        public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
            Utils.saveToNbt(nbt, "facing", this.facing);
            return super.writeToNBT(nbt);
        }

        @SideOnly(Side.CLIENT)
        @Override
        public double getMaxRenderDistanceSquared() {
            return 100000;
        }

        @SideOnly(Side.CLIENT)
        @Override
        public AxisAlignedBB getRenderBoundingBox() {
            return TileEntity.INFINITE_EXTENT_AABB;
        }

        public boolean hasFastRenderer() {
            return true;
        }
    }

    //To minimize network usage, mbInfo will not be send to blocks other than the Render block
    protected MultiBlockTileInfo mbInfo;

    //////////////////////////////
    /////TileEntity
    //////////////////////////////
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.mbInfo = new MultiBlockTileInfo(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        this.mbInfo.saveToNBT(nbt);
        return super.writeToNBT(nbt);
    }

    @Override
    public final void onStructureCreating(MultiBlockTileInfo mbInfo) {
        this.mbInfo = mbInfo;
        markDirty();

        onStructureCreating();
    }

    @Override
    public MultiBlockTileInfo getMultiBlockTileInfo() {
        return mbInfo;
    }

    protected void onStructureCreating() {}

    @Override
    public void onStructureCreated() {}

    @Override
    public void onStructureRemoved() {}

    /////////////////////////////////////////////////////////
    /////Sync
    /////////////////////////////////////////////////////////
    @Override
    public void prepareS2CPacketData(NBTTagCompound nbt) {
        super.prepareS2CPacketData(nbt);
        mbInfo.saveToNBT(nbt);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onSyncDataFromServerArrived(NBTTagCompound nbt) {
        mbInfo = new MultiBlockTileInfo(nbt);

        super.onSyncDataFromServerArrived(nbt);
    }

    /////////////////////////////////////////////////////////
    /////Utils
    /////////////////////////////////////////////////////////
    public boolean isMirrored() {
        return false;
    }

    public EnumFacing getFacing() {
        return this.mbInfo==null? null : this.mbInfo.facing;
    }

    public int getFacingInt() {
        return this.mbInfo==null? 0 : this.mbInfo.facing.ordinal()-2;
    }

    public BlockPos getRenderPos() {return this.mbInfo.getPartPos(new Vec3i(0,0,0));}
}
