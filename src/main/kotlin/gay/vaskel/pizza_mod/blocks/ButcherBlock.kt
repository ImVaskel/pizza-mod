package gay.vaskel.pizza_mod.blocks

import gay.vaskel.pizza_mod.PizzaModItems
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.text.TranslatableText
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.ItemScatterer
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


class ButcherBlock: Block(FabricBlockSettings.of(Material.WOOD).strength(2.0f).nonOpaque()), BlockEntityProvider {
    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return ButcherBlockEntity(pos, state)
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        if (world.isClient) {
            return ActionResult.PASS
        }

        val blockEntity = world.getBlockEntity(pos) as Inventory

        val stack = player.getStackInHand(hand)

        if (stack.isOf(PizzaModItems.IRON_KNIFE_ITEM)) {
            val entity = blockEntity as ButcherBlockEntity
            val item = entity.getStack(0)

            if (!item.isEmpty) {
                // This shouldn't be possible, but as a safeguard, check.
                if (!item.isOf(Items.BEEF) && !item.isOf(Items.PORKCHOP)) return ActionResult.PASS

                val progress = (25..30).random()

                if (entity.progress + progress >= 100) {
                    entity.progress = 0
                    item.count -= 1

                    entity.sync()

                    stack.damage(1, player) { e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND) }

                    ItemScatterer.spawn(world, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(),
                        ItemStack(PizzaModItems.PEPPERONI_ITEM, (1..3).random())
                    )

                    player.sendMessage(TranslatableText("block.pizza_mod.butcher_block.progress.complete"), true)
                }
                else {
                    entity.progress += progress
                    player.sendMessage(
                        TranslatableText("block.pizza_mod.butcher_block.progress", entity.progress), true
                    )
                }
            }

        } else if (stack.isOf(Items.BEEF) || stack.isOf(Items.PORKCHOP) && blockEntity.getStack(0).isEmpty) {
            blockEntity.setStack(0, stack.copy())
            stack.count = 0
            (blockEntity as ButcherBlockEntity).sync()
        }
        else if (stack.isEmpty && player.isInSneakingPose) {
            if (blockEntity.getStack(0).isEmpty){
                player.sendMessage(TranslatableText("block.pizza_mod.butcher_block.nothing"), true)
            }
            else {
                val item = blockEntity.getStack(0)
                player.sendMessage(TranslatableText("block.pizza_mod.butcher_block.something", item.count, item.name), true)
            }
        }

        return ActionResult.SUCCESS
    }

    override fun onStateReplaced(
        state: BlockState,
        world: World,
        pos: BlockPos,
        newState: BlockState,
        moved: Boolean
    ) {
        if (state.block != newState.block) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is ButcherBlockEntity) {
                ItemScatterer.spawn(world, pos, blockEntity as ButcherBlockEntity)
                world.updateComparators(pos, this)
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved)
    }
}
