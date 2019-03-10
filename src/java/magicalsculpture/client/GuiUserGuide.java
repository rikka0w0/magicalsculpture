package magicalsculpture.client;

import magicalsculpture.ItemRegistry;
import magicalsculpture.MagicalSculpture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiUserGuide extends GuiScreen {
    public final static ResourceLocation backGroundRes = new ResourceLocation("minecraft:textures/gui/book.png");
    private NextPageButton buttonNextPage;
    private NextPageButton buttonPreviousPage;
    private ItemStackButton buttonIndexPage;
    private Page[] pages;
    private int page;

    public GuiUserGuide() {
        super();

        page = 0;
        pages = new Page[] {
            new Page(this, I18n.format("item.magicalsculpture:userguide.name")) {
                @Override
                public void initGui() {

                }

                @Override
                public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//                    parent.mc.renderEngine.bindTexture(new ResourceLocation(MagicalSculpture.MODID, "textures/gui/cover.png"));
//                    int i = (parent.width - 192) / 2;
//                    parent.drawTexturedModalRect(i, 32, 0, 0, 192, 192);
                    drawCenterString("23333", 36, 80, 0);
                }
            },
                new Page(this, "Content") {
                    @Override
                    public void initGui() {
                        int i = (parent.width - 192) / 2;
                        parent.addButton(new ItemStackButton(3, i + 38, 100, new ItemStack(ItemRegistry.itemChisel), ""));
                    }

                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        drawCenterString("Cyka Blayt", 36, 80, 0);
                    }
                }
        };
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(backGroundRes);
        int i = (this.width - 192) / 2;
        this.drawTexturedModalRect(i, 32, 0, 0, 192, 192);

        drawCenterString(this.pages[this.page].title, 36, 44, 0);
        this.pages[this.page].drawScreen(mouseX, mouseY, partialTicks);
        drawCenterString((this.page+1) + "/" + this.pages.length, 46, 188, 0);

        // drawItemStack(new ItemStack(ItemRegistry.itemUserGuide), (this.width - 192) / 2 + 38, 44, "");

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        this.buttonList.clear();

        int i = (this.width - 192) / 2;
        this.buttonIndexPage = this.addButton(new ItemStackButton(0, i + 70, 185, new ItemStack(ItemRegistry.itemUserGuide), ""));
        this.buttonNextPage = this.addButton(new NextPageButton(1, i + 120, 186, true));
        this.buttonPreviousPage = this.addButton(new NextPageButton(2, i + 38, 186,false));
        gotoPage(0);
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button == this.buttonPreviousPage)
            gotoPage(this.page - 1);
        else if (button == this.buttonNextPage)
            gotoPage(this.page + 1);
        else if (button == this.buttonIndexPage)
            gotoPage(1);
    }

    @Override
    public void mouseClicked(int x, int y, int button) throws IOException {
        super.mouseClicked(x, y, button);

    }

    public void drawCenterString(String s1, int x, int y, int color) {
        int i = (this.width - 192) / 2;
        int k = this.fontRenderer.getStringWidth(s1);
        this.fontRenderer.drawString(s1, i + x + (116 - k) / 2, y, 0);
    }

    /**
     * Draws an ItemStack.
     *
     * The z index is increased by 32 (and not decreased afterwards), and the item is then rendered at z=200.
     */
    protected int drawItemStack(ItemStack stack, int x, int y, String altText) {
        RenderHelper.enableGUIStandardItemLighting();

        GlStateManager.translate(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        this.itemRender.zLevel = 200.0F;
        net.minecraft.client.gui.FontRenderer font = stack.getItem().getFontRenderer(stack);
        if (font == null) font = fontRenderer;
        this.itemRender.renderItemAndEffectIntoGUI(stack, x, y);
        this.itemRender.renderItemOverlayIntoGUI(font, stack, x, y, altText);
        this.zLevel = 0.0F;
        this.itemRender.zLevel = 0.0F;

        RenderHelper.disableStandardItemLighting();

        return font.getStringWidth(altText);
    }

    public void gotoPage(int page) {
        this.buttonPreviousPage.enabled = page > 0;
        this.buttonNextPage.enabled = page < pages.length-1;

        this.buttonList.clear();
        this.buttonList.add(this.buttonIndexPage);
        this.buttonList.add(this.buttonPreviousPage);
        this.buttonList.add(this.buttonNextPage);

        this.page = page;
        this.pages[page].initGui();
    }

    @SideOnly(Side.CLIENT)
    static abstract class Page {
        public String title;
        protected final GuiUserGuide parent;
        public Page(GuiUserGuide parent, String title) {
            this.parent = parent;
            this.title = title;
        }

        public abstract void initGui();

        public abstract void drawScreen(int mouseX, int mouseY, float partialTicks);
    }

    @SideOnly(Side.CLIENT)
    static class ItemStackButton extends GuiButton {
        public ItemStack itemStack;

        public ItemStackButton(int buttonId, int x, int y, ItemStack stack, String text) {
            super(buttonId, x, y, 18 + Minecraft.getMinecraft().fontRenderer.getStringWidth(text), 16, text);
            this.itemStack = stack;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (!this.visible)
                return;

            FontRenderer font = Minecraft.getMinecraft().fontRenderer;
            RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();
            boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int x = this.x;
            int y = flag ? this.y-1 : this.y;

            if (Mouse.getEventButtonState() && flag)
                y += 2;

            RenderHelper.enableGUIStandardItemLighting();

            GlStateManager.translate(0.0F, 0.0F, 32.0F);
            this.zLevel = 200.0F;
            itemRender.zLevel = 200.0F;
            itemRender.renderItemAndEffectIntoGUI(itemStack, x, y);
            this.zLevel = 0.0F;
            itemRender.zLevel = 0.0F;

            RenderHelper.disableStandardItemLighting();

            font.drawString(displayString, this.x + 18, this.y + 3, 0);
        }
    }

    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton {
        private final boolean isForward;

        public NextPageButton(int buttonId, int x, int y, boolean isForwardIn) {
            super(buttonId, x, y, 23, 13, "");
            this.isForward = isForwardIn;
        }

        /**
         * Draws this button to the screen.
         */
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (this.visible) {
                boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(backGroundRes);
                int i = 0;
                int j = 192;

                if (flag)
                {
                    i += 23;
                }

                if (!this.isForward)
                {
                    j += 13;
                }

                this.drawTexturedModalRect(this.x, this.y, i, j, 23, 13);
            }
        }
    }
}
