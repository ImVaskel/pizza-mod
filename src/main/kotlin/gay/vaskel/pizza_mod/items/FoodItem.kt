package gay.vaskel.pizza_mod.items

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item

class FoodItem(hunger: Int, saturation: Float): Item(
    FabricItemSettings()
            .group(PizzaModItems.PIZZA_MOD_GROUP)
            .food(FoodComponent.Builder().hunger(hunger).saturationModifier(saturation).build())
    ) {
}