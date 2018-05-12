package magicalsculpture.item;

import magicalsculpture.CreativeTab;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBase;

public class ItemRelic extends ItemBase implements ISimpleTexture{
    private static final String[] subNames = {"qwq", "qaq"};

    public ItemRelic() {
        super("relic", true);
        setCreativeTab(CreativeTab.instance);
    }

    @Override
    public String[] getSubItemUnlocalizedNames() {
        return ItemRelic.subNames;
    }

    @Override
    public String getIconName(int i) {
        return "relic_" + subNames[i];
    }
}
