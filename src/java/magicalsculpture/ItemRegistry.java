package magicalsculpture;

import magicalsculpture.item.*;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemRegistry {
	public static ItemChisel itemChisel;
	public static ItemReverser itemReverser;
	public static ItemRelic itemRelic;
	public static ItemAmplifier itemAmplifier;
	public static ItemUserGuide itemUserGuide;
	
	public static void initItems() {
		itemChisel = new ItemChisel();
		itemReverser = new ItemReverser();
		itemRelic = new ItemRelic();
		itemAmplifier = new ItemAmplifier();
		itemUserGuide = new ItemUserGuide();
	}
	
	public static void registerItems(IForgeRegistry registry) {
		registry.registerAll(
				itemChisel,
				itemReverser,
                itemRelic,
				itemAmplifier,
				itemUserGuide
				);
	}
}
