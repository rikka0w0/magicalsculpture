package magicalsculpture.item;

import magicalsculpture.CreativeTab;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBase;

public class ItemReverser extends ItemBase implements ISimpleTexture {
    public ItemReverser() {
        super("reverser", false);
        setCreativeTab(CreativeTab.instance);
    }
    @Override
    public String getIconName(int i) {
        return "reverser";
    }
}
