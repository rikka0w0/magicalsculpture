package magicalsculpture.item;

import magicalsculpture.CreativeTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBase;

import javax.annotation.Nullable;
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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.translateToLocal("item.magicalsculpture:reverser.comment"));
    }
}
