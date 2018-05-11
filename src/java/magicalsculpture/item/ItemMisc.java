package magicalsculpture.item;

import magicalsculpture.CreativeTab;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBase;

public class ItemMisc extends ItemBase implements ISimpleTexture{
    private static final String[] subNames = {"chisel", "qwq"};

    public ItemMisc() {
        super("magicalsculptureitems", true);
        setCreativeTab(CreativeTab.instance);
    }

    @Override
    public String[] getSubItemUnlocalizedNames() {
        return ItemMisc.subNames;
    }

    @Override
    public String getIconName(int i) {
        return subNames[i];
    }
}
