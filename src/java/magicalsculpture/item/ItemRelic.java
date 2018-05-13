package magicalsculpture.item;

import magicalsculpture.CreativeTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
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

    // Crafting
    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        if (!hasContainerItem(itemStack)) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(this, 1, 29);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return stack.getItemDamage() == 29;
    }

    // Effects
    public static final Object relicEffect[][] = {
            {MobEffects.REGENERATION, 0},       // 0
            {MobEffects.RESISTANCE, 0},
            {MobEffects.STRENGTH, 0},
            {MobEffects.SPEED, 0},
            {MobEffects.NIGHT_VISION, 0},
            {MobEffects.HEALTH_BOOST},          // 5
            {MobEffects.NAUSEA, 0},
            {MobEffects.JUMP_BOOST, 0},
            {MobEffects.MINING_FATIGUE, 0},
            {MobEffects.WEAKNESS, 0},
            {MobEffects.HASTE, 0},              // 10
            {MobEffects.HUNGER, 0},
            {MobEffects.INVISIBILITY, 0},
            {MobEffects.GLOWING, 0},
            {MobEffects.FIRE_RESISTANCE, 0},
            {MobEffects.WATER_BREATHING, 0},    // 15
            {MobEffects.FIRE_RESISTANCE, 0, MobEffects.WATER_BREATHING, 0},
            {MobEffects.REGENERATION, 1, MobEffects.HUNGER, 0},
            {MobEffects.RESISTANCE, 1, MobEffects.MINING_FATIGUE, 1},
            {MobEffects.STRENGTH, 2, MobEffects.MINING_FATIGUE, 4},
            {MobEffects.STRENGTH, 0, MobEffects.RESISTANCE, 0, MobEffects.NAUSEA, 0}, // 20
            {MobEffects.STRENGTH, 1, MobEffects.SLOWNESS, 0},
            {MobEffects.STRENGTH, 3, MobEffects.SLOWNESS, 1},
            {MobEffects.BLINDNESS, 0, MobEffects.NAUSEA, 0, MobEffects.REGENERATION, 0},
            {MobEffects.REGENERATION, 2, MobEffects.NAUSEA, 0, MobEffects.WEAKNESS, 0},
            {MobEffects.SATURATION, 0, MobEffects.POISON, 0},   // 25
            {MobEffects.HASTE, 1},
            {MobEffects.RESISTANCE, 4, MobEffects.WEAKNESS, 4},
            {MobEffects.RESISTANCE, 2},
            {MobEffects.STRENGTH, 0, MobEffects.WEAKNESS, 0, MobEffects.SPEED, 0, MobEffects.SLOWNESS, 0},
            {MobEffects.STRENGTH, 2, MobEffects.RESISTANCE, 2}, // 30
            {MobEffects.NAUSEA, 0, MobEffects.SPEED, 4, MobEffects.JUMP_BOOST, 0, MobEffects.WEAKNESS, 0, MobEffects.MINING_FATIGUE, 0},
            {MobEffects.SLOWNESS, 0, MobEffects.STRENGTH, 1},
            {MobEffects.WEAKNESS, 0, MobEffects.JUMP_BOOST, 1},
            {MobEffects.WEAKNESS, 0, MobEffects.RESISTANCE, 1},
            {MobEffects.JUMP_BOOST, 1, MobEffects.SPEED, 0},    // 35
            {MobEffects.STRENGTH, 0, MobEffects.HASTE, 0},
            {MobEffects.SATURATION, 0, MobEffects.HUNGER, 0},
            {MobEffects.INVISIBILITY, 0, MobEffects.SPEED, 2},
            {MobEffects.INVISIBILITY, 0, MobEffects.STRENGTH, 1, MobEffects.REGENERATION, 0},
            {MobEffects.SPEED, 2, MobEffects.JUMP_BOOST, 1},    // 40
            {MobEffects.GLOWING, 0, MobEffects.ABSORPTION, 1},
            {MobEffects.WEAKNESS, 0, MobEffects.SATURATION, 0},
            {MobEffects.HUNGER, 0, MobEffects.LUCK, 0},
            {MobEffects.SLOWNESS, 0, MobEffects.WATER_BREATHING, 0},
            {MobEffects.STRENGTH, 0},   // 45
            {MobEffects.RESISTANCE, 0},
            {MobEffects.SPEED, 0},
            {MobEffects.ABSORPTION, 0},
            {MobEffects.STRENGTH, 1, MobEffects.RESISTANCE, 1, MobEffects.SPEED, 1, MobEffects.ABSORPTION, 2}   // 49
    };
}
