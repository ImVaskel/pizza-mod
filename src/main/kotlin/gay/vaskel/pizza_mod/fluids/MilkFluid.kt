package gay.vaskel.pizza_mod.fluids

import gay.vaskel.pizza_mod.PizzaModBlocks
import gay.vaskel.pizza_mod.PizzaModFluids
import gay.vaskel.pizza_mod.PizzaModItems
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.fluid.FlowableFluid
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.item.Item
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

abstract class MilkFluid: FlowableFluid() {
    class Flowing: MilkFluid() {
        override fun appendProperties(builder: StateManager.Builder<Fluid, FluidState>) {
            super.appendProperties(builder)
            builder.add(LEVEL)
        }

        override fun getLevel(state: FluidState): Int {
            return state.get(LEVEL)
        }

        override fun isStill(state: FluidState): Boolean {
            return false
        }
    }

    class Still: MilkFluid() {
        override fun getLevel(state: FluidState): Int {
            return 8;
        }

        override fun isStill(state: FluidState): Boolean {
            return true
        }
    }

    override fun isInfinite(): Boolean {
        return false
    }

    override fun getBucketItem(): Item {
        return PizzaModItems.MILK_BUCKET
    }

    override fun canBeReplacedWith(
        state: FluidState,
        world: BlockView,
        pos: BlockPos,
        fluid: Fluid,
        direction: Direction
    ): Boolean {
        return false
    }

    override fun matchesType(fluid: Fluid): Boolean {
        return fluid == still || fluid == flowing
    }

    override fun beforeBreakingBlock(world: WorldAccess, pos: BlockPos, state: BlockState) {
        val blockEntity: BlockEntity? = if (state.hasBlockEntity()) world.getBlockEntity(pos) else null
        Block.dropStacks(state, world, pos, blockEntity)
    }

    override fun getFlowSpeed(world: WorldView): Int {
        return 4
    }

    override fun getLevelDecreasePerBlock(world: WorldView): Int {
        return 1
    }

    override fun getTickRate(world: WorldView): Int {
        return 5
    }

    override fun getBlastResistance(): Float {
        return 100f
    }

    override fun getStill(): Fluid {
        return PizzaModFluids.STILL_MILK
    }

    override fun getFlowing(): Fluid {
        return PizzaModFluids.FLOWING_MILK
    }

    override fun toBlockState(state: FluidState?): BlockState {
        return PizzaModBlocks.MILK_FLUID_BLOCK.defaultState.with(Properties.LEVEL_15, getBlockStateLevel(state))
    }


}