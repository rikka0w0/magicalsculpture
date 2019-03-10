package magicalsculpture;

import magicalsculpture.client.GuiUserGuide;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.AutoGuiHandler;

public class GuiHandler extends AutoGuiHandler{
	public enum GuiType {
		RelicGUI(7),
		AmplifierGUI(8),
		UserGuideGUI(9);

		private final int GuiID;
		GuiType(int GuiID){
			this.GuiID = GuiID;
		}

		public static GuiType parse(int i) {
			i -= 7;
			if (i>= GuiType.values().length)
				return null;
			return GuiType.values()[i];
		}
	}
	
	@Override
	protected Container getContainer(int ID, EntityPlayer player, World world, BlockPos pos) {
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected GuiScreen getGui(int ID, EntityPlayer player, World world, BlockPos pos) {
		return ID == GuiType.UserGuideGUI.GuiID ? new GuiUserGuide() : null;
	}

	public static void openGui(EntityPlayer player, World world, GuiType gui) {
		player.openGui(MagicalSculpture.instance, gui.GuiID, world, 0, 0, 0);
	}

    public static void openGui(EntityPlayer player, World world, BlockPos pos, GuiType gui) {
    	player.openGui(MagicalSculpture.instance, gui.GuiID, world, pos.getX(), pos.getY(), pos.getZ());
    }
	
    public static void openGui(EntityPlayer player, World world, BlockPos pos, EnumFacing facing) {
    	player.openGui(MagicalSculpture.instance, facing.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
    }
}
