package magicalsculpture.client;

import magicalsculpture.block.TileEntitySculpture;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.client.model.pipeline.LightUtil;

import java.util.List;

public class FastTESRSculpture extends FastTESR<TileEntitySculpture.Render> {
    public final static FastTESRSculpture instance = new FastTESRSculpture();
    private FastTESRSculpture() {}

    public final static List<BakedQuad>[] bakedModelUnmirrored = new List[4];

    @Override
    public boolean isGlobalRenderer(TileEntitySculpture.Render te) {
        return true;
    }

    @Override
    public void renderTileEntityFast(TileEntitySculpture.Render te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        BlockPos pos = te.getPos();
        double xoff = x-pos.getX() + 0.5F;
        double yoff = y-pos.getY() - 0.01F;
        double zoof = z-pos.getZ() + 0.5F;
        buffer.setTranslation(xoff, yoff, zoof);

        int facing = te.getRenderFacing().ordinal() - 2;
        boolean mirrored = te.isMirrored();
        List<BakedQuad> quads = bakedModelUnmirrored[facing];

        int i = 15728640;	//TODO: Fix light calculation
        for (BakedQuad quad: quads) {

            buffer.addVertexData(quad.getVertexData());
            buffer.putBrightness4(i, i, i, i);

            float diffuse = LightUtil.diffuseLight(mirrored ? quad.getFace() : quad.getFace().getOpposite());

            buffer.putColorMultiplier(diffuse, diffuse, diffuse, 4);
            buffer.putColorMultiplier(diffuse, diffuse, diffuse, 3);
            buffer.putColorMultiplier(diffuse, diffuse, diffuse, 2);
            buffer.putColorMultiplier(diffuse, diffuse, diffuse, 1);
            buffer.putPosition(pos.getX(), pos.getY(), pos.getZ());
        }
    }
}
