package magicalsculpture;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;

public class ClientProxy extends CommonProxy{
    @Override
    public IThreadListener getClientThread() {
        return Minecraft.getMinecraft();
    }
	
	@Override
	public void preInit() {

	}
	
	@Override
	public void init() {
		ClientRegistrationHandler.registerTileEntityRenders();
	}
	
	@Override
	public void postInit() {
		
	}
}
