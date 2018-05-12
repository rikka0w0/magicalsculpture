package magicalsculpture.item;

import magicalsculpture.CreativeTab;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBase;

public class ItemAmplifier extends ItemBase implements ISimpleTexture {
    private static final String[] subNames = {"qwq", "qaq"};

    public ItemAmplifier() {
        super("amplifier", true);
        setCreativeTab(CreativeTab.instance);
    }

    @Override
    public String[] getSubItemUnlocalizedNames() {
        return ItemAmplifier.subNames;
    }

    @Override
    public String getIconName(int i) {
        return "amplifier_" + subNames[i];
    }
}
