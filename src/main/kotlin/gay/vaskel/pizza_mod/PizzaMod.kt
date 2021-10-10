package gay.vaskel.pizza_mod

import net.fabricmc.api.ModInitializer

@Suppress("unused")
class PizzaMod : ModInitializer {

    companion object {
        const val MOD_ID = "pizza_mod";
    }

    override fun onInitialize() {
        PizzaModFluids.onInitialize()
        PizzaModItems.onInitialize()
        PizzaModBlocks.onInitialize()
    }
}

