package magicalsculpture.block;

import magicalsculpture.ItemRegistry;
import magicalsculpture.client.GuiRelic;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.container.ContainerInventory;
import rikka.librikka.container.IContainerWithGui;

public class ContainerRelic extends ContainerInventory implements IContainerWithGui {
    protected ContainerRelic(InventoryPlayer invPlayer, IInventory inventoryTile) {
        super(invPlayer, inventoryTile);

        addSlotToContainer(new Relic(inventoryTile, 0, 80, 35));
    }


    public static class Relic extends Slot {
        public Relic(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack itemStack) {
            return itemStack.getItem() == ItemRegistry.itemRelic;
        }

        @Override
        public int getSlotStackLimit() {
            return 1;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen createGui() {
        return new GuiRelic(this);
    }
}
