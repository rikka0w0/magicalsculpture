package magicalsculpture.item;

import magicalsculpture.CreativeTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBase;

import java.util.List;

public class ItemReverser extends ItemBase implements ISimpleTexture {
    public ItemReverser() {
        super("reverser", false);
        setCreativeTab(CreativeTab.instance);
    }
    @Override
    public String getIconName(int i) {
        return "reverser";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.translateToLocal("item.magicalsculpture:reverser.comment"));
    }
}
