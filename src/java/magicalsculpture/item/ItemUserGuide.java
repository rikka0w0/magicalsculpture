package magicalsculpture.item;

import magicalsculpture.CreativeTab;
import magicalsculpture.client.GuiUserGuide;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBase;

public class ItemUserGuide extends ItemBase implements ISimpleTexture {
    public ItemUserGuide() {
        super("userguide", false);
        setCreativeTab(CreativeTab.instance);
        setMaxStackSize(1);
        setMaxDamage(0);
    }

    @Override
    public String getIconName(int i) {
        return "userguide";
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiUserGuide());
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
