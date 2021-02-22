package xyz.vaskel.pizza_mod;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.vaskel.pizza_mod.blocks.PizzaOvenBlock;
import xyz.vaskel.pizza_mod.blocks.PizzaOvenEntity;
import xyz.vaskel.pizza_mod.items.*;

import java.util.function.Supplier;

public class PizzaModItems {

    public static String modid = "pizza_mod";

    public static final PepperoniItem PEPPERONI_ITEM;
    public static final PizzaItem PIZZA_ITEM;
    public static final KnifeItem IRON_KNIFE;
    public static final Item PIZZA_DOUGH_ITEM;
    public static final Item CHEESE_ITEM;
    public static final ReusableItem MORTAR_PESTLE_ITEM;
    public static final ItemGroup PIZZA_ITEM_GROUP;
    public static final Item MILK_BOWL_ITEM;
    public static final Item WHEAT_FLOUR_ITEM;
    public static final PineapplePizzaItem PINEAPPLE_PIZZA_ITEM;
    public static final SpoonItem SPOON_ITEM;


    static {
        PIZZA_ITEM_GROUP = FabricItemGroupBuilder.build(
                new Identifier(modid, "pizza_group"),
                displayGroupIcon());

        PIZZA_ITEM = new PizzaItem(new FabricItemSettings().group(PIZZA_ITEM_GROUP)
                .food(new FoodComponent.Builder().hunger(10).saturationModifier(0.7f).build()));

        PEPPERONI_ITEM = new PepperoniItem(new FabricItemSettings().group(PIZZA_ITEM_GROUP)
                .food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 20 * 3), 0.15f)
                        .hunger(3).saturationModifier(0.2F).snack().meat().build()));

        IRON_KNIFE = new KnifeItem(ToolMaterials.IRON, 1, -1.75f,
                new Item.Settings().group(PIZZA_ITEM_GROUP));

        PIZZA_DOUGH_ITEM = new Item(new FabricItemSettings().group(PIZZA_ITEM_GROUP)
                .food(new FoodComponent.Builder().hunger(4).saturationModifier(0.4f).build()));

        CHEESE_ITEM = new Item(new FabricItemSettings().group(PIZZA_ITEM_GROUP)
                .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.15f).snack().build()));

        MORTAR_PESTLE_ITEM = new ReusableItem(new FabricItemSettings().group(PIZZA_ITEM_GROUP));

        MILK_BOWL_ITEM = new Item(new FabricItemSettings().group(PIZZA_ITEM_GROUP));

        WHEAT_FLOUR_ITEM = new Item(new FabricItemSettings().group(PIZZA_ITEM_GROUP));

        PINEAPPLE_PIZZA_ITEM = new PineapplePizzaItem(new FabricItemSettings().group(PIZZA_ITEM_GROUP).food(
                new FoodComponent.Builder().hunger(9).saturationModifier(0.65F).statusEffect
                        (new StatusEffectInstance(StatusEffects.POISON, 20*3), 0.50f).build()
        ));

        SPOON_ITEM = new SpoonItem();
        }

    public static void initialize(){
        Registry.register(Registry.ITEM, new Identifier(modid, "pizza"), PIZZA_ITEM);
        Registry.register(Registry.ITEM, new Identifier(modid, "pepperoni"), PEPPERONI_ITEM);
        Registry.register(Registry.ITEM, new Identifier(modid, "iron_knife"), IRON_KNIFE);
        Registry.register(Registry.ITEM, new Identifier(modid, "pizza_dough"), PIZZA_DOUGH_ITEM);
        Registry.register(Registry.ITEM, new Identifier(modid, "cheese"), CHEESE_ITEM);
        Registry.register(Registry.ITEM, new Identifier(modid, "mortar_and_pestle"), MORTAR_PESTLE_ITEM);
        Registry.register(Registry.ITEM, new Identifier(modid, "milk_bowl"), MILK_BOWL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(modid, "wheat_flour"), WHEAT_FLOUR_ITEM);
        Registry.register(Registry.ITEM, new Identifier(modid, "pineapple_pizza"), PINEAPPLE_PIZZA_ITEM);

        Registry.register(Registry.ITEM, new Identifier(modid, "pizza_oven"), new BlockItem(PizzaModBlocks.PIZZA_OVEN_BLOCK,
                new FabricItemSettings().group(PIZZA_ITEM_GROUP)));

        Registry.register(Registry.ITEM, new Identifier(modid, "cauldron"), new BlockItem(PizzaModBlocks.MILK_CAULDRON_BLOCK,
                new FabricItemSettings().group(PIZZA_ITEM_GROUP)));

        Registry.register(Registry.ITEM, new Identifier(modid, "spoon"), SPOON_ITEM);

    }

    public static Supplier<ItemStack> displayGroupIcon(){
        return () -> new ItemStack(PIZZA_ITEM);
    }
}
