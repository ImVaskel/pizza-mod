package gay.vaskel.pizza_mod.blockentities

import gay.vaskel.pizza_mod.fluids.MilkFluid
import gay.vaskel.pizza_mod.fluids.PizzaModFluids
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant
import net.fabricmc.fabric.api.transfer.v1.storage.Storage
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleViewIterator
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.BlockPos


@Suppress("DEPRECATION")
class MilkCauldronBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(PizzaModBlockEntities.MILK_CAULDRON_ENTITY, pos, state), BlockEntityClientSerializable,
    StorageView<FluidVariant>, Storage<FluidVariant> {

    companion object {
        const val CAPACITY = FluidConstants.BLOCK * 4
    }

    var fluid: FluidVariant = FluidVariant.of(PizzaModFluids.STILL_MILK)
    var amt: Long = 0

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        this.amt = nbt.getLong("amt")
    }

    override fun writeNbt(nbt: NbtCompound): NbtCompound {
        nbt.putLong("amt", amt)
        return super.writeNbt(nbt)
    }

    override fun fromClientTag(tag: NbtCompound) {
        this.amt = 0
        readNbt(tag)
    }

    override fun toClientTag(tag: NbtCompound): NbtCompound {
        return writeNbt(tag)
    }

    override fun isResourceBlank(): Boolean {
        return fluid.isBlank
    }

    override fun getResource(): FluidVariant {
        return fluid
    }

    override fun getAmount(): Long {
        return this.amt
    }

    override fun getCapacity(): Long {
        return CAPACITY
    }

    override fun insert(resource: FluidVariant, maxAmount: Long, transaction: TransactionContext): Long {
        StoragePreconditions.notBlankNotNegative(resource, CAPACITY)

        if (resource.fluid is MilkFluid) {
            val inserted: Long = maxAmount.coerceAtMost(CAPACITY - amount)
            if (inserted > 0) {
                amt += inserted
            }
            return inserted
        }
        return 0
    }

    override fun extract(resource: FluidVariant, maxAmount: Long, transaction: TransactionContext): Long {
        StoragePreconditions.notBlankNotNegative(resource, maxAmount)
        if(resource.fluid is MilkFluid) {
            val extracted: Long = maxAmount.coerceAtMost(amount)
            if (extracted > 0) {
                amt -= extracted
            }
            return extracted
        }
        return 0
    }

    override fun iterator(transaction: TransactionContext): MutableIterator<StorageView<FluidVariant>> {
        return SingleViewIterator.create(this, transaction);
    }
}