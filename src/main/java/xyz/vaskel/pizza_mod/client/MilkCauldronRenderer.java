package xyz.vaskel.pizza_mod.client;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import xyz.vaskel.pizza_mod.blocks.MilkCauldronEntity;

import java.util.Collections;

public class MilkCauldronRenderer implements BlockEntityRenderer<MilkCauldronEntity> {

    static ModelPart.Cuboid CUBOID = new ModelPart.Cuboid(
            0, 0, 2, 4, 2, 12, 8, 12, 0, 0, 0, true, 16, 16
            );

    static final ModelPart MODEL = new ModelPart(ImmutableList.of(CUBOID), Collections.emptyMap());

    RenderLayer RENDERLAYER = RenderLayer.getEntityTranslucentCull(new Identifier("pizza_mod", "textures/block/milk_fluid.png"));

    public MilkCauldronRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(MilkCauldronEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier("minecraft", "textures/block/water_still.png"));

        if (entity.milk > 0) {
            MODEL.render(matrices, vertexConsumers.getBuffer(RENDERLAYER), light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
        }
        matrices.pop();
    }
}

