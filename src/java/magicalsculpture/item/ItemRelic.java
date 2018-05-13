package magicalsculpture.item;

import magicalsculpture.CreativeTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBase;

import java.util.List;

public class ItemRelic extends ItemBase implements ISimpleTexture{
    public static final int numOfRelic = 50;
    private static final String[] subNames;
    static {
        subNames = new String[numOfRelic];
        for (int i=0; i<numOfRelic; i++) {
            subNames[i] = String.valueOf(i);
        }
    }
    public static final PotionEffect relicEffect[][] = {

    };

    public ItemRelic() {
        super("relic", true);
        setCreativeTab(CreativeTab.instance);
    }

    @Override
    public String[] getSubItemUnlocalizedNames() {
        return ItemRelic.subNames;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getIconName(int i) {
        return "relic_" + subNames[i];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.translateToLocal("item.magicalsculpture:relic." + stack.getItemDamage() + ".comment"));
    }
}
