package xyz.vaskel.pizza_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.vaskel.pizza_mod.items.KnifeItem;
import xyz.vaskel.pizza_mod.items.PepperoniItem;
import xyz.vaskel.pizza_mod.items.PizzaItem;

public class Mod implements ModInitializer {

    public static String modid = "pizza-mod";

    public static final PizzaItem PIZZA_ITEM = new PizzaItem(new FabricItemSettings().group(ItemGroup.FOOD)
            .food(new FoodComponent.Builder().hunger(10).saturationModifier(0.7f).build()));

    public static final PepperoniItem PEPPERONI_ITEM = new PepperoniItem(new FabricItemSettings().group(ItemGroup.FOOD)
            .food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 20*3), 0.15f)
                    .hunger(3).saturationModifier(0.2F).snack().meat().build()));

   public static final KnifeItem IRON_KNIFE = new KnifeItem(ToolMaterials.IRON, 1, -1.75f,
           new Item.Settings().group(ItemGroup.COMBAT));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(modid, "pizza"), PIZZA_ITEM);
        Registry.register(Registry.ITEM, new Identifier(modid, "pepperoni"), PEPPERONI_ITEM);
        Registry.register(Registry.ITEM, new Identifier(modid, "iron_knife"), IRON_KNIFE);

    }
}
