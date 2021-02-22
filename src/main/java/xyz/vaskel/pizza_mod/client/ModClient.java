package xyz.vaskel.pizza_mod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;

import static xyz.vaskel.pizza_mod.PizzaModBlocks.MILK_CAULDRON_BLOCK;
import static xyz.vaskel.pizza_mod.PizzaModBlocks.MILK_CAULDRON_ENTITY;

@Environment(EnvType.CLIENT)
public class ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register(MILK_CAULDRON_ENTITY, MilkCauldronRenderer::new);
    }
}
