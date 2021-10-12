package gay.vaskel.pizza_mod.items

import gay.vaskel.pizza_mod.blocks.PizzaModBlocks
import gay.vaskel.pizza_mod.fluids.PizzaModFluids
import gay.vaskel.pizza_mod.utils.GenericRegistry
import gay.vaskel.pizza_mod.utils.ModIdentifier
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Blocks
import net.minecraft.item.*
import net.minecraft.util.registry.Registry


@Suppress("MemberVisibilityCanBePrivate")
object PizzaModItems: GenericRegistry<Item>(Registry.ITEM) {
    val PIZZA_MOD_GROUP: ItemGroup = FabricItemGroupBuilder.build(ModIdentifier( "general")) {
        ItemStack(Blocks.COBBLESTONE)
    }

    val BUTCHER_BLOCK_ITEM = register("butcher_block", BlockItem(PizzaModBlocks.BUTCHER_BLOCK, FabricItemSettings().group(PIZZA_MOD_GROUP)))
    val MILK_CAULDRON_ITEM = register("milk_cauldron", BlockItem(PizzaModBlocks.MILK_CAULDRON_BLOCK, FabricItemSettings().group(PIZZA_MOD_GROUP)))

    lateinit var MILK_BUCKET: BucketItem
    val IRON_KNIFE_ITEM = register("iron_knife", IronKnifeItem())
    val PEPPERONI_ITEM = register("pepperoni", FoodItem(3, 0.2f))

    override fun onInitialize() {
        super.onInitialize()

        MILK_BUCKET = Registry.register(
            Registry.ITEM, ModIdentifier( "milk_bucket"),
            BucketItem(PizzaModFluids.STILL_MILK, Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1))
        )
    }
}