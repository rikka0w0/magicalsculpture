package magicalsculpture;

import magicalsculpture.item.ItemAmplifier;
import magicalsculpture.item.ItemChisel;
import magicalsculpture.item.ItemRelic;
import magicalsculpture.item.ItemReverser;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemRegistry {
	public static ItemChisel itemChisel;
	public static ItemReverser itemReverser;
	public static ItemRelic itemRelic;
	public static ItemAmplifier itemAmplifier;
	
	public static void initItems() {
		itemChisel = new ItemChisel();
		itemReverser = new ItemReverser();
		itemRelic = new ItemRelic();
		itemAmplifier = new ItemAmplifier();
	}
	
	public static void registerItems(IForgeRegistry registry) {
		registry.registerAll(
				itemChisel,
				itemReverser,
                itemRelic,
				itemAmplifier
				);
	}
}
