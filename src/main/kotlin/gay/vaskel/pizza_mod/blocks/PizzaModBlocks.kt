package gay.vaskel.pizza_mod.blocks

import gay.vaskel.pizza_mod.fluids.PizzaModFluids
import gay.vaskel.pizza_mod.utils.GenericRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.FluidBlock
import net.minecraft.fluid.FlowableFluid
import net.minecraft.util.registry.Registry


object PizzaModBlocks: GenericRegistry<Block>(Registry.BLOCK) {
    val BUTCHER_BLOCK = register("butcher_block", ButcherBlock())
    val MILK_CAULDRON_BLOCK = register("butcher_block", MilkCauldronBlock())

    val MILK_FLUID_BLOCK = register("milk", object: FluidBlock(PizzaModFluids.STILL_MILK as FlowableFluid, FabricBlockSettings.copy(Blocks.WATER)) {})
}