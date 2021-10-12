package gay.vaskel.pizza_mod

import gay.vaskel.pizza_mod.blockentities.PizzaModBlockEntities
import gay.vaskel.pizza_mod.blocks.PizzaModBlocks
import gay.vaskel.pizza_mod.fluids.PizzaModFluids
import gay.vaskel.pizza_mod.items.PizzaModItems
import net.fabricmc.api.ModInitializer

@Suppress("unused")
class PizzaMod : ModInitializer {

    companion object {
        const val MOD_ID = "pizza_mod"
    }

    override fun onInitialize() {
        PizzaModFluids.onInitialize()
        PizzaModItems.onInitialize()
        PizzaModBlocks.onInitialize()
        PizzaModBlockEntities.onInitialize()
    }
}

