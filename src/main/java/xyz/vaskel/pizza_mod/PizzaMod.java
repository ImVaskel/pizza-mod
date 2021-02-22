package xyz.vaskel.pizza_mod;

import net.fabricmc.api.ModInitializer;

public class PizzaMod implements ModInitializer {

    @Override
    public void onInitialize() {
        PizzaModItems.initialize();
        PizzaModBlocks.initialize();
    }
}
