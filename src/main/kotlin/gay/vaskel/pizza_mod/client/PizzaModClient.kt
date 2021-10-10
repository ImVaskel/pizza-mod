package gay.vaskel.pizza_mod.client

import gay.vaskel.pizza_mod.PizzaModBlocks
import gay.vaskel.pizza_mod.client.renderers.ButcherBlockRenderer
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry
import net.minecraft.client.render.RenderLayer

@Suppress("unused")
class PizzaModClient: ClientModInitializer {

    override fun onInitializeClient() {
        BlockEntityRendererRegistry.register(PizzaModBlocks.BUTCHER_BLOCK_ENTITY) { factory ->
            ButcherBlockRenderer(factory)
        }
        BlockRenderLayerMap.INSTANCE.putBlock(PizzaModBlocks.BUTCHER_BLOCK, RenderLayer.getCutout())
    }
}