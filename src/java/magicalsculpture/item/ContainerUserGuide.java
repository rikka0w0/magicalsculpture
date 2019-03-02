package magicalsculpture.item;

import magicalsculpture.client.GuiUserGuide;
import net.minecraft.client.gui.GuiScreen;
import rikka.librikka.container.ContainerNoInventory;
import rikka.librikka.container.IContainerWithGui;

import javax.annotation.Nullable;

public class ContainerUserGuide extends ContainerNoInventory implements IContainerWithGui {
    public ContainerUserGuide(@Nullable Object host) {
        super(host);
    }

    @Override
    public GuiScreen createGui() {
        return new GuiUserGuide(this);
    }

    @Override
    public void detectAndSendChanges() {

    }
}
