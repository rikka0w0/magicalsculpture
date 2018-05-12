package magicalsculpture;

import magicalsculpture.item.ItemAmplifier;
import magicalsculpture.item.ItemChisel;
import magicalsculpture.item.ItemRelic;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

public class ItemRegistry {
	public static ItemChisel itemChisel;
	public static ItemRelic itemRelic;
	public static ItemAmplifier itemAmplifier;
	
	public static void initItems() {
		itemChisel = new ItemChisel();
		itemRelic = new ItemRelic();
		itemAmplifier = new ItemAmplifier();
	}
	
	public static void registerItems(IForgeRegistry registry) {
		registry.registerAll(
				itemChisel,
                itemRelic,
				itemAmplifier
				);
	}
}
