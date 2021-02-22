package xyz.vaskel.pizza_mod.blocks;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.math.BlockPos;
import xyz.vaskel.pizza_mod.PizzaModBlocks;

public class MilkCauldronEntity extends BlockEntity implements BlockEntityClientSerializable {

    public int milk = 0;

    public MilkCauldronEntity(BlockPos pos, BlockState state) {
        super(PizzaModBlocks.MILK_CAULDRON_ENTITY, pos, state);
    }

    @Override
    public void readNbt(CompoundTag tag) {
        super.readNbt(tag);
        milk = tag.getInt("milk_level");
    }

    @Override
    public CompoundTag writeNbt(CompoundTag tag) {
        super.writeNbt(tag);
        tag.putInt("milk_level", milk);
        return tag;
    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        readNbt(compoundTag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        return writeNbt(compoundTag);
    }

    public void addMilk(int amount){
        milk = milk + amount;

        if (milk > 81000){
            milk = 81000;
        }
    }
}
