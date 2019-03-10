package magicalsculpture.client;

import magicalsculpture.BlockRegistry;
import magicalsculpture.ItemRegistry;
import magicalsculpture.MagicalSculpture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
    private int nextPage = -1;
    private Page[] pages;
    private int page = 0;

    public void drawPicture(int x, int y, int width, int height, double scale, String texture) {
        width = (int) (width*scale);
        height= (int) (height*scale);
        x= x + (this.width - width) / 2;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(new ResourceLocation(MagicalSculpture.MODID, "textures/gui/"+texture+".png"));
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex(0, 1).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex(1, 1).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex(1, 0).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex(0, 0).endVertex();
        tessellator.draw();
    }

    public GuiUserGuide() {
        super();

        pages = new Page[] {
                new Page(this, I18n.format("item.magicalsculpture:userguide.name")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        parent.drawPicture(-2, 64, 864, 720, 0.125, "cover");
                    }
                },

                new Page(this, l("content.title")) {
                    ItemStackButton[] buttons = new ItemStackButton[5];
                    int[] pageToJump = new int[] {2,3,9,13,16};
                    @Override
                    public void initGui() {
                        int i = (parent.width - 192) / 2;

                        buttons[0] = new ItemStackButton(3, i + 38, 70, new ItemStack(ItemRegistry.itemChisel), l("content.intro"));
                        buttons[1] = new ItemStackButton(4, i + 38, 90, new ItemStack(BlockRegistry.blockSculpture), l("content.structure"));
                        buttons[2] = new ItemStackButton(5, i + 38, 110, new ItemStack(ItemRegistry.itemRelic), l("content.relics"));
                        buttons[3] = new ItemStackButton(6, i + 38, 130, new ItemStack(ItemRegistry.itemAmplifier), l("content.amplifier"));
                        buttons[4] = new ItemStackButton(7, i + 38, 150, new ItemStack(ItemRegistry.itemRelic,1,6), l("content.notes"));

                        for (int j=0; j<buttons.length; j++)
                            parent.addButton(buttons[j]);
                    }

                    @Override
                    public void actionPerformed(GuiButton button) {
                        for (int j=0; j<buttons.length; j++)
                            if (button == buttons[j]) {
                                parent.gotoPage(pageToJump[j]);
                                return;
                            }
                    }
                },

                new Page(this, l("content.intro")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 100) / 2;
                        parent.fontRenderer.drawSplitString(l("intro.text"), i,80,100, 0);

                        i = (parent.width - 16) / 2;
                        parent.drawItemStack(new ItemStack(ItemRegistry.itemChisel), i, 60, "");
                    }
                },

                new Page(this, l("content.structure")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 116) / 2 - 3;
                        parent.mc.renderEngine.bindTexture(new ResourceLocation("minecraft:textures/gui/container/crafting_table.png"));
                        parent.drawTexturedModalRect(i, 64, 29, 16, 116, 54);

                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1, 64+1, "");
                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1+18, 64+1, "");
                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1+36, 64+1, "");

                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1, 64+1+18, "");
                        parent.drawItemStack(new ItemStack(Items.DIAMOND), i+1+18, 64+1+18, "");
                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1+36, 64+1+18, "");

                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1, 64+1+36, "");
                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1+18, 64+1+36, "");
                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1+36, 64+1+36, "");

                        parent.drawItemStack(new ItemStack(BlockRegistry.blockSculpture, 1, 1), i+1+95, 64+1+18, "");

                        i = (parent.width - 100) / 2;
                        parent.fontRenderer.drawSplitString(l("structure.text1"), i,128,100, 0);
                    }
                },

                new Page(this, l("content.structure")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 116) / 2 - 3;
                        parent.mc.renderEngine.bindTexture(new ResourceLocation("minecraft:textures/gui/container/crafting_table.png"));
                        parent.drawTexturedModalRect(i, 64, 29, 16, 116, 54);

                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1, 64+1, "");
                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1+18, 64+1, "");
                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1+36, 64+1, "");

                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1, 64+1+18, "");
                        parent.drawItemStack(new ItemStack(Blocks.CHEST), i+1+18, 64+1+18, "");
                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1+36, 64+1+18, "");

                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1, 64+1+36, "");
                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1+18, 64+1+36, "");
                        parent.drawItemStack(new ItemStack(Blocks.STONE), i+1+36, 64+1+36, "");

                        parent.drawItemStack(new ItemStack(BlockRegistry.blockSculpture), i+1+95, 64+1+18, "");

                        i = (parent.width - 100) / 2;
                        parent.fontRenderer.drawSplitString(l("structure.text2"), i,128,100, 0);
                    }
                },

                new Page(this, l("content.structure")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        parent.drawPicture(-35, 72, 338, 703, 0.125, "structure");

                        int i = (parent.width - 60) / 2;
                        parent.fontRenderer.drawSplitString(l("structure.text3"), i+26,80,60, 0);
                    }
                },

                new Page(this, l("content.structure")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        parent.drawPicture(-35, 72, 338, 703, 0.125, "sculpture");

                        int i = (parent.width - 60) / 2;
                        parent.fontRenderer.drawSplitString(l("structure.text4"), i+26,80,60, 0);
                    }
                },

                new Page(this, l("content.structure")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 60) / 2 - 4;
                        parent.mc.renderEngine.bindTexture(new ResourceLocation(MagicalSculpture.MODID,"textures/gui/relic.png"));
                        parent.drawTexturedModalRect(i, 64, 58, 28, 60, 50);


                        i = (parent.width - 100) / 2;
                        parent.fontRenderer.drawSplitString(l("structure.text5"), i,128,100, 0);
                    }
                },

                new Page(this, l("content.structure")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 85) / 2 - 4;
                        parent.mc.renderEngine.bindTexture(new ResourceLocation(MagicalSculpture.MODID,"textures/gui/amplifier.png"));
                        parent.drawTexturedModalRect(i, 64, 45, 24, 85, 50);


                        i = (parent.width - 100) / 2;
                        parent.fontRenderer.drawSplitString(l("structure.text6"), i,128,100, 0);
                    }
                },

                new Page(this, l("content.relics")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 100) / 2;
                        parent.fontRenderer.drawSplitString(l("relics.text1"), i,80,100, 0);

                        parent.fontRenderer.drawSplitString(l("relics.text2"), i,140,100, 0);
                    }
                },

                new Page(this, l("content.relics")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 100) / 2 - 4;

                        ItemStack stack = new ItemStack(ItemRegistry.itemRelic,1,14);
                        parent.drawItemStack(stack, i+1, 74+1, "");
                        parent.fontRenderer.drawSplitString(stack.getDisplayName(), i+20,78,80, 0);
                        parent.fontRenderer.drawSplitString(l("relics.text3") + "\n" + l("relics.text4"), i+20,87,80, 0);
                        parent.fontRenderer.drawSplitString("", i+20,96,80, 0);

                        stack = new ItemStack(ItemRegistry.itemRelic,1,4);
                        parent.drawItemStack(stack, i+1, 128+1, "");
                        parent.fontRenderer.drawSplitString(stack.getDisplayName(), i+20,132,80, 0);
                        parent.fontRenderer.drawSplitString(l("relics.text5") + "\n" + l("relics.text6"), i+20,141,80, 0);
                        parent.fontRenderer.drawSplitString("", i+20,150,80, 0);
                    }
                },

                new Page(this, l("content.relics")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 100) / 2 - 4;

                        ItemStack stack = new ItemStack(ItemRegistry.itemRelic,1,48);
                        parent.drawItemStack(stack, i+1, 74+1, "");
                        parent.fontRenderer.drawSplitString(stack.getDisplayName(), i+20,78,80, 0);
                        parent.fontRenderer.drawSplitString(l("relics.text7") + "\n" + l("relics.text8"), i+20,87,80, 0);

                        stack = new ItemStack(ItemRegistry.itemRelic,1,18);
                        parent.drawItemStack(stack, i+1, 128+1, "");
                        parent.fontRenderer.drawSplitString(stack.getDisplayName(), i+20,132,80, 0);
                        parent.fontRenderer.drawSplitString(l("relics.text9") + "\n" + l("relics.text10"), i+20,141,80, 0);
                    }
                },

                new Page(this, l("content.relics")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        parent.drawPicture(0, 135, 528,186, 0.2,"relics");

                        int i = (parent.width - 100) / 2;
                        parent.fontRenderer.drawSplitString(l("relics.text11"), i,80,100, 0);
                    }
                },

                new Page(this, l("content.amplifier")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 100) / 2;
                        parent.fontRenderer.drawSplitString(l("amplifier.text1"), i,80,100, 0);
                    }
                },

                new Page(this, l("content.amplifier")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 100) / 2 - 4;

                        ItemStack stack = new ItemStack(ItemRegistry.itemAmplifier,1,0);
                        parent.drawItemStack(stack, i+1, 74+1, "");
                        parent.fontRenderer.drawSplitString(stack.getDisplayName(), i+20,78,80, 0);
                        parent.fontRenderer.drawSplitString(l("amplifier.text2"), i+20,87,80, 0);

                        stack = new ItemStack(ItemRegistry.itemAmplifier,1,1);
                        parent.drawItemStack(stack, i+1, 128+1, "");
                        parent.fontRenderer.drawSplitString(stack.getDisplayName(), i+20,132,80, 0);
                        parent.fontRenderer.drawSplitString(l("amplifier.text3"), i+20,141,80, 0);
                    }
                },

                new Page(this, l("content.amplifier")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 100) / 2 - 4;

                        ItemStack stack = new ItemStack(ItemRegistry.itemAmplifier,1,2);
                        parent.drawItemStack(stack, i+1, 74+1, "");
                        parent.fontRenderer.drawSplitString(stack.getDisplayName(), i+20,78,80, 0);
                        parent.fontRenderer.drawSplitString(l("amplifier.text4"), i+20,87,80, 0);

                        stack = new ItemStack(ItemRegistry.itemAmplifier,1,3);
                        parent.drawItemStack(stack, i+1, 128+1, "");
                        parent.fontRenderer.drawSplitString(stack.getDisplayName(), i+20,132,80, 0);
                        parent.fontRenderer.drawSplitString(l("amplifier.text5"), i+20,141,80, 0);
                    }
                },

                new Page(this, l("content.notes")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 100) / 2 - 4;
                        parent.fontRenderer.drawSplitString(l("notes.text1")+"\n\n"+l("notes.text2"), i,58,100, 0);
                    }
                },

                new Page(this, l("content.notes")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 100) / 2 - 4;
                        parent.fontRenderer.drawSplitString(l("notes.text2")+"\n\n"+l("notes.text3"), i,58,100, 0);
                    }
                },

                new Page(this, l("content.notes")) {
                    @Override
                    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
                        int i = (parent.width - 100) / 2 - 4;
                        parent.fontRenderer.drawSplitString(l("notes.text5")+"\n\n\n"+l("notes.text6"), i,72,100, 0);

                        i = (parent.width - 16) / 2;
                        parent.drawItemStack(new ItemStack(ItemRegistry.itemRelic,1,5), i, 140, "");
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

        this.pages[this.page].drawScreen(mouseX, mouseY, partialTicks);
        drawCenterString(this.pages[this.page].title, 36, 44, 0);
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

        this.pages[this.page].initGui();

        if (this.page == 0)
            this.buttonPreviousPage.enabled = false;
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button == this.buttonPreviousPage)
            gotoPage(this.page - 1);
        else if (button == this.buttonNextPage)
            gotoPage(this.page + 1);
        else if (button == this.buttonIndexPage)
            gotoPage(1);
        else
            this.pages[this.page].actionPerformed(button);
    }

    @Override
    public void updateScreen() {
        if (nextPage > -1 && nextPage < this.pages.length) {
            this.buttonPreviousPage.enabled = nextPage > 0;
            this.buttonNextPage.enabled = nextPage < pages.length-1;

            this.buttonList.clear();
            this.buttonList.add(this.buttonIndexPage);
            this.buttonList.add(this.buttonPreviousPage);
            this.buttonList.add(this.buttonNextPage);

            this.page = nextPage;
            this.pages[this.page].initGui();

            nextPage = -1;
        }
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
        this.nextPage = page;
    }

    @SideOnly(Side.CLIENT)
    public static abstract class Page {
        public String title;
        protected final GuiUserGuide parent;
        public Page(GuiUserGuide parent, String title) {
            this.parent = parent;
            this.title = title;
        }

        public void initGui() {}

        public void actionPerformed(GuiButton button) {}

        public void drawScreen(int mouseX, int mouseY, float partialTicks) {}
    }

    @SideOnly(Side.CLIENT)
    public static class ItemStackButton extends GuiButton {
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

    public static String l(String key) {
        return I18n.format("guide.magicalsculpture:"+key);
    }
}
