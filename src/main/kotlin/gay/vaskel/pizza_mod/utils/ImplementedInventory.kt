package gay.vaskel.pizza_mod.utils

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.util.collection.DefaultedList


interface ImplementedInventory: Inventory {
    var items: DefaultedList<ItemStack>

    companion object {

        fun of(items: DefaultedList<ItemStack>): ImplementedInventory {
            return object : ImplementedInventory {
                override var items: DefaultedList<ItemStack>
                    get() = items
                    set(value) { }
            }
        }

        fun ofSize(size: Int): ImplementedInventory {
            return of(DefaultedList.ofSize(size, ItemStack.EMPTY))
        }
    }

    override fun size(): Int {
        return items.size
    }

    override fun isEmpty(): Boolean {
        for (i in 0..this.size()) {
            val stack: ItemStack = getStack(i)
            if (!stack.isEmpty) {
                return false
            }
        }
        return true
    }

    override fun getStack(slot: Int): ItemStack {
        return items[slot]
    }

    override fun removeStack(slot: Int): ItemStack {
        return Inventories.removeStack(items, slot)
    }

    override fun removeStack(slot: Int, amount: Int): ItemStack {
        val res = Inventories.splitStack(items, slot, amount)
        if (!res.isEmpty) {
            markDirty()
        }
        return res
    }

    override fun setStack(slot: Int, stack: ItemStack) {
        items[slot] = stack
        if (stack.count > maxCountPerStack) {
            stack.count = maxCountPerStack;
        }
    }

    override fun clear() {
        items.clear()
    }

    override fun markDirty() {

    }

    override fun canPlayerUse(player: PlayerEntity): Boolean {
        return true
    }
}