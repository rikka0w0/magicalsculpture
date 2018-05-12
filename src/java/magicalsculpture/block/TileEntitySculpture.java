package magicalsculpture.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.Utils;
import rikka.librikka.container.StandardInventory;
import rikka.librikka.multiblock.IMultiBlockTile;
import rikka.librikka.multiblock.MultiBlockTileInfo;
import rikka.librikka.tileentity.IGuiProviderTile;
import rikka.librikka.tileentity.TileEntityBase;

import java.util.List;

public abstract class TileEntitySculpture extends TileEntityBase implements IMultiBlockTile, IGuiProviderTile {
    public static class PlaceHolderStone extends TileEntitySculpture{
        @Override
        public Container getContainer(EntityPlayer entityPlayer, EnumFacing enumFacing) {
            BlockPos hostPos = getRenderPos();
            TileEntity host = this.world.getTileEntity(hostPos);
            if (host instanceof Render)
                return new ContainerRelic(entityPlayer.inventory, ((Render)host).inventoryRelic);
            else
                return null;
        }
    }

    public static class PlaceHolderBase extends TileEntitySculpture{
        @Override
        public Container getContainer(EntityPlayer entityPlayer, EnumFacing enumFacing) {
            BlockPos hostPos = getRenderPos();
            TileEntity host = this.world.getTileEntity(hostPos);
            if (host instanceof Render)
                return new ContainerAmplifier(entityPlayer.inventory, ((Render)host).inventoryAmplifier);
            else
                return null;
        }
    }

    public static class Render extends TileEntitySculpture implements ITickable{
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
        public final StandardInventory inventoryAmplifier = new StandardInventory(this, 2, "container.sculpture.amplifier.name");
        public final StandardInventory inventoryRelic = new StandardInventory(this, 2, "container.sculpture.relic.name");

        @Override
        public void readFromNBT(NBTTagCompound nbt) {
            super.readFromNBT(nbt);
            this.facing = Utils.facingFromNbt(nbt, "facing");

            NBTTagCompound amplifier = nbt.getCompoundTag("amplifier");
            if (amplifier != null)
                inventoryAmplifier.readFromNBT(amplifier);
            NBTTagCompound relic = nbt.getCompoundTag("relic");
            if (relic != null)
                inventoryRelic.readFromNBT(relic);
        }

        @Override
        public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
            Utils.saveToNbt(nbt, "facing", this.facing);

            NBTTagCompound amplifier = new NBTTagCompound();
            inventoryAmplifier.writeToNBT(amplifier);
            nbt.setTag("amplifier", amplifier);
            NBTTagCompound relic = new NBTTagCompound();
            inventoryRelic.writeToNBT(relic);
            nbt.setTag("relic", relic);

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

        @Override
        public Container getContainer(EntityPlayer player, EnumFacing side) {
            return new ContainerAmplifier(player.inventory, inventoryAmplifier);
        }

        @Override
        public void onStructureRemoved() {
            for (int i=0; i<inventoryAmplifier.getSizeInventory(); i++) {
                ItemStack itemToDrop = inventoryAmplifier.getStackInSlot(i);
                if (itemToDrop != null)
                    Utils.dropItemIntoWorld(world, pos, itemToDrop);
            }
            for (int i=0; i<inventoryRelic.getSizeInventory(); i++) {
                ItemStack itemToDrop = inventoryRelic.getStackInSlot(i);
                if (itemToDrop != null)
                    Utils.dropItemIntoWorld(world, pos, itemToDrop);
            }
        }

        ///////////////////////////////////
        /// ITickable
        ///////////////////////////////////
        @Override
        public void update() {
            if (world.isRemote) {
                //Update client rendering
                return;
            }

            //Scan for players
            List<EntityPlayer> playerList = world.getEntitiesWithinAABB(EntityPlayer.class, (new AxisAlignedBB(-4, 0 , -4 , 4, 10, 4)).offset(pos).offset(1, 0, 1));
            if (playerList == null)
                return;

            for (EntityPlayer player: playerList) {
                player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 100, 0));
            }
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

    public BlockPos getRenderPos() {return this.mbInfo==null? new BlockPos(0,-1,0) : this.mbInfo.getPartPos(new Vec3i(0,0,0));}
}
