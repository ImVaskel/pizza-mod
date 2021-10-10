package gay.vaskel.pizza_mod.blocks

import gay.vaskel.pizza_mod.PizzaModBlocks
import gay.vaskel.pizza_mod.PizzaModFluids
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant
import net.fabricmc.fabric.api.transfer.v1.storage.Storage
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.fluid.Fluid
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.BlockPos

class MilkCauldronBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(PizzaModBlocks.MILK_CAULDRON_ENTITY, pos, state), BlockEntityClientSerializable,
    Storage<FluidVariant>
{
    lateinit var milk: FluidVariant

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
    }

    override fun writeNbt(nbt: NbtCompound): NbtCompound {
        return super.writeNbt(nbt)
    }

    override fun fromClientTag(tag: NbtCompound) {
        readNbt(tag)
    }

    override fun toClientTag(tag: NbtCompound): NbtCompound {
        return writeNbt(tag)
    }

    override fun insert(resource: FluidVariant, maxAmount: Long, transaction: TransactionContext): Long {
        TODO("Not yet implemented")
    }

    override fun extract(resource: FluidVariant, maxAmount: Long, transaction: TransactionContext): Long {
        TODO("Not yet implemented")
    }

    override fun iterator(transaction: TransactionContext): MutableIterator<StorageView<FluidVariant>> {
        TODO("Not yet implemented")
    }
}