package magicalsculpture;

import magicalsculpture.block.TileEntitySculpture;
import magicalsculpture.client.CustomStateMapper;

import magicalsculpture.client.FastTESRSculpture;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import rikka.librikka.model.loader.AdvancedModelLoader;

@Mod.EventBusSubscriber(modid = MagicalSculpture.MODID, value = Side.CLIENT)
public class ClientRegistrationHandler {
	@SubscribeEvent
	public static void registerModel(ModelRegistryEvent event) {
		AdvancedModelLoader loader = new AdvancedModelLoader(MagicalSculpture.MODID);
		
		//Blocks
		CustomStateMapper customStateMapper = new CustomStateMapper(MagicalSculpture.MODID);
		loader.registerModelLoader(customStateMapper);
//		loader.registerInventoryIcon(BlockRegistry.blockSculpture);
		customStateMapper.register(BlockRegistry.blockSculpture);

		//Items
		loader.registerInventoryIcon(ItemRegistry.itemChisel);
		loader.registerInventoryIcon(ItemRegistry.itemReverser);
		loader.registerInventoryIcon(ItemRegistry.itemRelic);
		loader.registerInventoryIcon(ItemRegistry.itemAmplifier);
		loader.registerInventoryIcon(ItemRegistry.itemUserGuide);
	}
	
	public static void registerTileEntityRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySculpture.Render.class, FastTESRSculpture.instance);
	}
}
