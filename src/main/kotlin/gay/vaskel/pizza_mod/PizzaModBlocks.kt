package gay.vaskel.pizza_mod

import gay.vaskel.pizza_mod.blocks.ButcherBlock
import gay.vaskel.pizza_mod.blocks.ButcherBlockEntity
import gay.vaskel.pizza_mod.blocks.MilkCauldronBlock
import gay.vaskel.pizza_mod.blocks.MilkCauldronBlockEntity
import gay.vaskel.pizza_mod.utils.ModIdentifier
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.impl.networking.NetworkingImpl.MOD_ID
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.FluidBlock
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.registry.Registry


object PizzaModBlocks {
    val BUTCHER_BLOCK = ButcherBlock()
    val MILK_CAULDRON_BLOCK = MilkCauldronBlock()


    lateinit var MILK_FLUID_BLOCK: Block

    lateinit var BUTCHER_BLOCK_ENTITY: BlockEntityType<ButcherBlockEntity>
    lateinit var MILK_CAULDRON_ENTITY: BlockEntityType<MilkCauldronBlockEntity>

    fun onInitialize() {
        Registry.register(Registry.BLOCK, ModIdentifier("butcher_block"), BUTCHER_BLOCK)
        Registry.register(Registry.BLOCK, ModIdentifier("milk_cauldron"), MILK_CAULDRON_BLOCK)
        MILK_FLUID_BLOCK =
            Registry.register(
                Registry.BLOCK,
                ModIdentifier("milk"),
                object : FluidBlock(PizzaModFluids.STILL_MILK, FabricBlockSettings.copy(Blocks.WATER)) {})
        BUTCHER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ModIdentifier("butcher_block_entity"),
            BlockEntityType.Builder.create( {pos, state -> ButcherBlockEntity(pos, state)}, BUTCHER_BLOCK).build(null) as BlockEntityType<ButcherBlockEntity>)
        MILK_CAULDRON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ModIdentifier("milk_cauldron_entity"),
            BlockEntityType.Builder.create({pos, state -> MilkCauldronBlockEntity(pos, state)}, MILK_CAULDRON_BLOCK).build(null) as BlockEntityType<MilkCauldronBlockEntity>)
    }
}