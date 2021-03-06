package magicalsculpture.block;

import magicalsculpture.ItemRegistry;
import magicalsculpture.client.GuiAmplifier;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.container.ContainerInventory;
import rikka.librikka.container.IContainerWithGui;

public class ContainerAmplifier extends ContainerInventory implements IContainerWithGui {
    protected ContainerAmplifier(InventoryPlayer invPlayer, IInventory inventoryTile) {
        super(invPlayer, inventoryTile);

        addSlotToContainer(new Amplifier(inventoryTile, 0, 70, 32));
        addSlotToContainer(new Amplifier(inventoryTile, 1, 89, 32));
        addSlotToContainer(new Amplifier(inventoryTile, 2, 70, 51));
        addSlotToContainer(new Amplifier(inventoryTile, 3, 89, 51));
    }

    public static class Amplifier extends Slot {
        public Amplifier(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack itemStack) {
            return itemStack.getItem() == ItemRegistry.itemAmplifier;
        }

        @Override
        public int getSlotStackLimit() {
            return 1;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen createGui() {
        return new GuiAmplifier(this);
    }
}
