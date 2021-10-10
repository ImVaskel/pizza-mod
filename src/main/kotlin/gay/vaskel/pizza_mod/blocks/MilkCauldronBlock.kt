package gay.vaskel.pizza_mod.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Items
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class MilkCauldronBlock: Block(FabricBlockSettings.of(Material.METAL).nonOpaque()), BlockEntityProvider {
    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return MilkCauldronBlockEntity(pos, state)
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        if (world.isClient) return ActionResult.PASS

        val entity = world.getBlockEntity(pos) as MilkCauldronBlockEntity
        val stack = player.getStackInHand(hand)

        if (stack.isOf(Items.MILK_BUCKET)) {
            // TODO:
        }
        else if (stack.isEmpty && player.isSneaking) {
            // TODO: Return the cauldron's current milk level.
        }

        return ActionResult.SUCCESS
    }

}