package gay.vaskel.pizza_mod.client.renderers

import gay.vaskel.pizza_mod.blockentities.ButcherBlockEntity
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Vec3f


class ButcherBlockRenderer(private val dispatcher: BlockEntityRendererFactory.Context) : BlockEntityRenderer<ButcherBlockEntity> {

    private val itemRenderer: ItemRenderer = MinecraftClient.getInstance().itemRenderer

    override fun render(
        entity: ButcherBlockEntity,
        tickDelta: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        val stack: ItemStack = entity.getStack(0)

        if (stack.isEmpty) {
            return
        }

        matrices.push()
        matrices.translate(0.5, 1.0, 0.6)
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-90F))
        itemRenderer.renderItem(
            stack,
            ModelTransformation.Mode.GROUND,
            light,
            overlay,
            matrices,
            vertexConsumers,
            5
        )
        matrices.pop()
    }
}