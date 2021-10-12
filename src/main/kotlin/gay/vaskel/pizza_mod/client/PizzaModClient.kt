package gay.vaskel.pizza_mod.client

import gay.vaskel.pizza_mod.blockentities.ButcherBlockEntity
import gay.vaskel.pizza_mod.blockentities.PizzaModBlockEntities
import gay.vaskel.pizza_mod.blocks.PizzaModBlocks
import gay.vaskel.pizza_mod.client.renderers.ButcherBlockRenderer
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.client.render.RenderLayer

@Suppress("unused")
class PizzaModClient: ClientModInitializer {

    override fun onInitializeClient() {
        BlockEntityRendererRegistry.register(PizzaModBlockEntities.BUTCHER_BLOCK_ENTITY as BlockEntityType<ButcherBlockEntity>) { factory ->
            ButcherBlockRenderer(factory)
        }
        BlockRenderLayerMap.INSTANCE.putBlock(PizzaModBlocks.BUTCHER_BLOCK, RenderLayer.getCutout())
    }
}