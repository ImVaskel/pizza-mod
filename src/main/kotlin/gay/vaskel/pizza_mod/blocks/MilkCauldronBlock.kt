package gay.vaskel.pizza_mod.blocks

import gay.vaskel.pizza_mod.fluids.MilkFluid
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Items
import net.minecraft.text.TranslatableText
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
        val handIo = ContainerItemContext.ofPlayerHand(player, Hand.MAIN_HAND).find(FluidStorage.ITEM)

        if (handIo != null) {
            if (StorageUtil.move(handIo, entity, { f -> f.fluid is MilkFluid }, Integer.MAX_VALUE.toLong(), null) > 0)
                return ActionResult.SUCCESS
            if (StorageUtil.move(entity, handIo, { f -> f.fluid is MilkFluid }, Integer.MAX_VALUE.toLong(), null) > 0)
                return ActionResult.SUCCESS

            return ActionResult.PASS
        }
        else if (player.getStackInHand(hand).isEmpty && player.isSneaking) {
            if (entity.amt > 0) {
                player.sendMessage(TranslatableText("block.pizza_mod.milk_cauldron.display", "${entity.amt} droplets of milk"),
                    true)
            }
            else {
                player.sendMessage(TranslatableText("block.pizza_mod.milk_cauldron.display", "no milk"), true)
            }
        }


        return ActionResult.PASS
    }

}