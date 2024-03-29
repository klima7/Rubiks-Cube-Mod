package com.github.klima7.client.renderer.blockentity;

import com.github.klima7.common.entity.BaseRubiksCubeBlockEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public abstract class BaseRubiksCubeRenderer<T extends BaseRubiksCubeBlockEntity> extends GeoBlockRenderer<T> {

    public BaseRubiksCubeRenderer(BlockEntityRendererProvider.Context context, AnimatedGeoModel<T> model) {
        super(context, model);
    }

    @Override
    public void render(T tile, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
        AnimatedGeoModel<T> modelProvider = getGeoModelProvider();
        GeoModel model = modelProvider.getModel(modelProvider.getModelResource(tile));
        modelProvider.setLivingAnimations(tile, this.getUniqueID(tile));
        stack.pushPose();
        stack.translate(0, 0, 0);
        stack.translate(0.5, 0, 0.5);

        rotateBlock(getFacing(tile), stack);

        RenderSystem.setShaderTexture(0, getTextureLocation(tile));
        Color renderColor = getRenderColor(tile, partialTicks, stack, bufferIn, null, packedLightIn);
        RenderType renderType = getRenderType(tile, partialTicks, stack, bufferIn, null, packedLightIn,
                getTextureLocation(tile));
        render(model, tile, partialTicks, renderType, stack, bufferIn, null, packedLightIn, OverlayTexture.NO_OVERLAY,
                (float) renderColor.getRed() / 255f, (float) renderColor.getGreen() / 255f,
                (float) renderColor.getBlue() / 255f, (float) renderColor.getAlpha() / 255);
        stack.popPose();
    }

    @Override
    protected void rotateBlock(Direction facing, PoseStack stack) {
        switch (facing) {
            case SOUTH -> stack.mulPose(Vector3f.YP.rotationDegrees(180));
            case WEST -> stack.mulPose(Vector3f.YP.rotationDegrees(90));
            case NORTH -> stack.mulPose(Vector3f.YP.rotationDegrees(0));
            case EAST -> stack.mulPose(Vector3f.YP.rotationDegrees(270));
            case UP -> {
                stack.mulPose(Vector3f.XP.rotationDegrees(90));
                stack.translate(0, -0.5, -0.5);
            }
            case DOWN -> {
                stack.mulPose(Vector3f.XN.rotationDegrees(90));
                stack.translate(0, -0.5, 0.5);
            }
        }
    }

    private Direction getFacing(BaseRubiksCubeBlockEntity entity) {
        return entity.getFacing();
    }

}
