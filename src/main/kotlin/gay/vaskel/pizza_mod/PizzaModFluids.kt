package gay.vaskel.pizza_mod

import gay.vaskel.pizza_mod.fluids.MilkFluid
import gay.vaskel.pizza_mod.utils.ModIdentifier
import net.minecraft.fluid.FlowableFluid
import net.minecraft.util.registry.Registry

object PizzaModFluids {
    lateinit var STILL_MILK: FlowableFluid;
    lateinit var FLOWING_MILK: FlowableFluid;

    fun onInitialize() {
        STILL_MILK = Registry.register(Registry.FLUID, ModIdentifier("milk"), MilkFluid.Still())
        FLOWING_MILK = Registry.register(Registry.FLUID, ModIdentifier("flowing_milk"), MilkFluid.Flowing())
    }
}