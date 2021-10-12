package gay.vaskel.pizza_mod.items

import gay.vaskel.pizza_mod.items.PizzaModItems
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterials

// TODO: Figure out a way to have the remainder be damaged.
class IronKnifeItem : SwordItem(
    ToolMaterials.IRON,
    3,
    -3.0f,
    FabricItemSettings().group(PizzaModItems.PIZZA_MOD_GROUP)
) {
    // returns itself for a recipe remainder instead of disappearing.
    override fun getRecipeRemainder(): Item {
        return this
    }

    override fun hasRecipeRemainder(): Boolean {
        return true
    }


}