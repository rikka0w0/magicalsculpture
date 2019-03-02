package magicalsculpture.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiUserGuide extends GuiContainer {
    public GuiUserGuide(Container container) {
        super(container);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        //draw text and stuff here
        //the parameters for drawString are: string, x, y, color

        String s = I18n.translateToLocal("gui.magicalsculpture:amplifier");
        this.fontRenderer.drawString(s
                , this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);

        this.fontRenderer.drawString("Cyka Blayt!", this.xSize - 38, this.ySize - 96, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int par2, int par3) {
        //draw your Gui here, only thing you need to change is the path
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(new ResourceLocation("minecraft:textures/gui/book.png"));
        int i = (this.width - 192) / 2;
        this.drawTexturedModalRect(i, 2, 0, 0, 192, 192);
    }

    @Override
    public void actionPerformed(GuiButton button) {

    }

    @Override
    public void mouseClicked(int x, int y, int button) throws IOException {
        super.mouseClicked(x, y, button);

    }
}
