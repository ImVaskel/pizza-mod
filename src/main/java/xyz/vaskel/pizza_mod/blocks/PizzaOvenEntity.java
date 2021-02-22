package xyz.vaskel.pizza_mod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import xyz.vaskel.pizza_mod.ImplementedInventory;
import xyz.vaskel.pizza_mod.PizzaMod;
import xyz.vaskel.pizza_mod.PizzaModBlocks;
import xyz.vaskel.pizza_mod.PizzaModItems;

public class PizzaOvenEntity extends BlockEntity implements ImplementedInventory {

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public PizzaOvenEntity(BlockPos pos, BlockState state) {
        super(PizzaModBlocks.PIZZA_OVEN_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void readNbt(CompoundTag tag) {
        super.readNbt(tag);
        Inventories.readNbt(tag, items);
    }

    @Override
    public CompoundTag writeNbt(CompoundTag tag) {
        super.writeNbt(tag);
        return Inventories.writeNbt(tag, items);
    }
}
