package gay.vaskel.pizza_mod.blocks

import gay.vaskel.pizza_mod.PizzaModBlocks
import gay.vaskel.pizza_mod.utils.ImplementedInventory
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtList
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos


class ButcherBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(PizzaModBlocks.BUTCHER_BLOCK_ENTITY, pos, state), ImplementedInventory,
        BlockEntityClientSerializable
{
    override var items: DefaultedList<ItemStack> = DefaultedList.ofSize(1, ItemStack.EMPTY)
    var progress: Int = 0

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        Inventories.readNbt(nbt, items)
        progress = nbt.getInt("progress")
    }

    override fun writeNbt(nbt: NbtCompound): NbtCompound {
        Inventories.writeNbt(nbt, items)
        nbt.putInt("progress", progress)
        return super.writeNbt(nbt)
    }

    override fun fromClientTag(tag: NbtCompound) {
        items.clear()
        readNbt(tag)
    }

    override fun toClientTag(tag: NbtCompound): NbtCompound {
        return writeNbt(tag)
    }

    override fun markDirty() {

    }
}