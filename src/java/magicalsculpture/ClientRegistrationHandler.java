package magicalsculpture;

import magicalsculpture.block.TileEntitySculpture;
import magicalsculpture.client.CustomStateMapper;

import magicalsculpture.client.FastTESRSculpture;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import rikka.librikka.model.loader.AdvancedModelLoader;
import rikka.librikka.properties.Properties;

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
		loader.registerInventoryIcon(ItemRegistry.itemMisc);
	}
	
	public static void registerTileEntityRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySculpture.Render.class, FastTESRSculpture.instance);
	}
}
