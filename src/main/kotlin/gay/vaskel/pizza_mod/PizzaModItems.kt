package gay.vaskel.pizza_mod

import gay.vaskel.pizza_mod.items.FoodItem
import gay.vaskel.pizza_mod.items.IronKnifeItem
import gay.vaskel.pizza_mod.utils.ModIdentifier
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.impl.networking.NetworkingImpl.MOD_ID
import net.minecraft.block.Blocks
import net.minecraft.item.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


@Suppress("MemberVisibilityCanBePrivate")
object PizzaModItems {
    val PIZZA_MOD_GROUP: ItemGroup = FabricItemGroupBuilder.build(ModIdentifier( "general")) {
        ItemStack(Blocks.COBBLESTONE)
    }

    val BUTCHER_BLOCK_ITEM = BlockItem(PizzaModBlocks.BUTCHER_BLOCK, FabricItemSettings().group(PIZZA_MOD_GROUP))
    val MILK_CAULDRON_ITEM = BlockItem(PizzaModBlocks.MILK_CAULDRON_BLOCK, FabricItemSettings().group(PIZZA_MOD_GROUP))

    lateinit var MILK_BUCKET: BucketItem;
    val IRON_KNIFE_ITEM = IronKnifeItem()
    val PEPPERONI_ITEM = FoodItem(3, 0.2f)

    fun onInitialize() {
        Registry.register(Registry.ITEM, ModIdentifier( "iron_knife"), IRON_KNIFE_ITEM)
        Registry.register(Registry.ITEM, ModIdentifier( "pepperoni"), PEPPERONI_ITEM)
        Registry.register(Registry.ITEM, ModIdentifier( "butcher_block"), BUTCHER_BLOCK_ITEM)
        Registry.register(Registry.ITEM, ModIdentifier("milk_cauldron"), MILK_CAULDRON_ITEM)
        MILK_BUCKET = Registry.register(
            Registry.ITEM, ModIdentifier( "milk_bucket"),
            BucketItem(PizzaModFluids.STILL_MILK, Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1))
        )
    }
}