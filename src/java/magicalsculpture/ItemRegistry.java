package magicalsculpture;

import magicalsculpture.item.ItemMisc;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

public class ItemRegistry {
	public static ItemMisc itemMisc;
	
	public static void initItems() {
		itemMisc = new ItemMisc();
	}
	
	public static void registerItems(IForgeRegistry registry) {
		registry.registerAll(
				itemMisc
				);
	}
}
