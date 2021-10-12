package gay.vaskel.pizza_mod.blockentities

import gay.vaskel.pizza_mod.utils.GenericRegistry
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.registry.Registry

object PizzaModBlockEntities: GenericRegistry<BlockEntityType<*>>(Registry.BLOCK_ENTITY_TYPE) {
    val MILK_CAULDRON_ENTITY = register("milk_cauldron_entity", BlockEntityType.Builder.create(
        {pos, state -> MilkCauldronBlockEntity(pos, state)}).build(null) as BlockEntityType<MilkCauldronBlockEntity>
    )

    val BUTCHER_BLOCK_ENTITY = register("butcher_block_entity", BlockEntityType.Builder.create(
        {pos, state -> ButcherBlockEntity(pos, state)}).build(null) as BlockEntityType<ButcherBlockEntity>
    )
}