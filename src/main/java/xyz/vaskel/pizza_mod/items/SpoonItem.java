package xyz.vaskel.pizza_mod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import static xyz.vaskel.pizza_mod.PizzaModBlocks.MILK_CAULDRON_BLOCK;
import static xyz.vaskel.pizza_mod.PizzaModItems.PIZZA_ITEM_GROUP;

public class SpoonItem extends Item {
    public SpoonItem() {
        super(new FabricItemSettings().group(PIZZA_ITEM_GROUP));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient){ return ActionResult.SUCCESS;}

        BlockState blockState = context.getWorld().getBlockState(context.getBlockPos());

        if (blockState.getBlock() == Blocks.CAULDRON){
            boolean successful = context.getWorld().setBlockState(context.getBlockPos(), MILK_CAULDRON_BLOCK.getDefaultState());
        }
        return ActionResult.SUCCESS;
    }
}
