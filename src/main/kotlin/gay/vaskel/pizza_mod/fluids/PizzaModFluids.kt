package gay.vaskel.pizza_mod.fluids

import gay.vaskel.pizza_mod.utils.GenericRegistry
import net.minecraft.fluid.Fluid
import net.minecraft.util.registry.Registry

object PizzaModFluids: GenericRegistry<Fluid>(Registry.FLUID) {
    val STILL_MILK = register("milk", MilkFluid.Still())
    val FLOWING_MILK = register("flowing_milk", MilkFluid.Flowing())

}