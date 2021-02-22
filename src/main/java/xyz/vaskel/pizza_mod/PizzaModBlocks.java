package xyz.vaskel.pizza_mod;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.vaskel.pizza_mod.blocks.CauldronBlock;
import xyz.vaskel.pizza_mod.blocks.MilkCauldronEntity;
import xyz.vaskel.pizza_mod.blocks.PizzaOvenBlock;
import xyz.vaskel.pizza_mod.blocks.PizzaOvenEntity;

import static xyz.vaskel.pizza_mod.PizzaModItems.modid;

public class PizzaModBlocks {
    public static BlockEntityType<PizzaOvenEntity> PIZZA_OVEN_BLOCK_ENTITY;
    public static final PizzaOvenBlock PIZZA_OVEN_BLOCK;
    public static BlockEntityType<MilkCauldronEntity> MILK_CAULDRON_ENTITY;
    public static CauldronBlock MILK_CAULDRON_BLOCK;

    static {
        PIZZA_OVEN_BLOCK = new PizzaOvenBlock();

        MILK_CAULDRON_BLOCK = new CauldronBlock();
    }

    public static void initialize(){
        PIZZA_OVEN_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "pizza_mod:pizza_oven_entity",
                FabricBlockEntityTypeBuilder.create(PizzaOvenEntity::new, PIZZA_OVEN_BLOCK).build(null));

        MILK_CAULDRON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "pizza_mod:milk_cauldron_entity",
                FabricBlockEntityTypeBuilder.create(MilkCauldronEntity::new, MILK_CAULDRON_BLOCK).build(null));

        Registry.register(Registry.BLOCK, new Identifier(modid, "pizza_oven"), PIZZA_OVEN_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(modid, "cauldron"), MILK_CAULDRON_BLOCK);
    }
}
