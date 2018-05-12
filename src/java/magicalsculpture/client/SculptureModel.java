package magicalsculpture.client;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.MultiModelState;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;
import javax.vecmath.Matrix4f;

import java.util.*;

public class SculptureModel implements IModel, IBakedModel {
    public static final int[] rotationAngle = {90, 270, 180, 0};    //NSWE {4, 0, 6, 2}
    private static final ModelRotation[] rotationMatrix = {
            ModelRotation.X0_Y270,
            ModelRotation.X0_Y90,
            ModelRotation.X0_Y180,
            ModelRotation.X0_Y0
    };
    private final List<ResourceLocation> dependencies = new ArrayList<ResourceLocation>();
    private final Set<ResourceLocation> textures = Sets.newHashSet();
    private final IModel model;
    private final IModelState defaultState;
    private final ResourceLocation particleTexture;

    public SculptureModel() throws Exception {
        String modelName = "magicalsculpture:sculpture.obj";    //Sketch Up --*.dae--> Blender --> *.obj & *.mtl
        ImmutableList.Builder<Pair<IModel, IModelState>> builder = ImmutableList.builder();
        List<Variant> variants = new LinkedList<Variant>();
        boolean uvLock = false;
        // IModel model = OBJLoader.INSTANCE.loadModel(new ResourceLocation("magicalsculpture:models/sculpture.obj"));
        Variant variant = new Variant(new ResourceLocation(modelName), ModelRotation.X0_Y0, uvLock, 1);

        ResourceLocation loc = variant.getModelLocation();
        if (!dependencies.contains(loc))
            dependencies.add(loc);

        IModel preModel = ModelLoaderRegistry.getModel(loc);
        IModel model = variant.process(preModel);

        for (ResourceLocation location : model.getDependencies()) {
            ModelLoaderRegistry.getModelOrMissing(location);
        }

        textures.addAll(model.getTextures()); // Kick this, just in case.
        this.model = model;
        builder.add(Pair.of(model, variant.getState()));

        defaultState = new MultiModelState(builder.build());

        particleTexture = new ResourceLocation("magicalsculpture:blocks/stone");
        textures.add(particleTexture);
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return ImmutableList.copyOf(this.dependencies);
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return ImmutableSet.copyOf(this.textures);
    }

    @Override
    public IModelState getDefaultState() {
        return this.defaultState;
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format,
                            Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        for (int facing=0; facing<4; facing++) {
            ModelRotation rotationState = rotationMatrix[facing];

            Matrix4f offsetMatrix1 = new Matrix4f();
            offsetMatrix1.setIdentity();
            offsetMatrix1.m03 = -0.5F;  // 1 -> 0.5
            offsetMatrix1.m23 = -0.5F;  // 1 -> 0.5
            Matrix4f ret = new Matrix4f();
            ret.mul(rotationState.getMatrix(), offsetMatrix1);

            IModelState transformation = new TRSRTransformation(ret);
            //Bake the Obj Model
            IBakedModel bakedModel = this.model.bake(transformation, format, bakedTextureGetter);

            List<BakedQuad> quads = new ArrayList();
            quads.addAll(bakedModel.getQuads(null, null, 0));

            int rotation = rotationAngle[facing];

            FastTESRSculpture.bakedModelUnmirrored[facing] = quads;
        }

        this.particle = bakedTextureGetter.apply(particleTexture);
        return this;
    }

    /////////////////
    /// IBakedModel
    /////////////////
    private TextureAtlasSprite particle;

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        return ImmutableList.of();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.NONE;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return particle;
    }
}
