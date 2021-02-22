package xyz.vaskel.pizza_mod.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xyz.vaskel.pizza_mod.ImplementedInventory;

public class PizzaOvenBlock extends Block implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = Properties.LIT;

    public PizzaOvenBlock() {
        super(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).
                hardness(4.0f).requiresTool());
        setDefaultState(getStateManager().getDefaultState().with(LIT, false).with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(LIT);
        stateManager.add(Properties.HORIZONTAL_FACING);
    }
    
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PizzaOvenEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (world.isClient) return ActionResult.SUCCESS;
        Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);


        if (!player.getStackInHand(hand).isEmpty()) {
            // Check what is the first open slot and put an item from the player's hand there
            assert blockEntity != null;
            if (blockEntity.getStack(0).isEmpty()) {
                // Put the stack the player is holding into the inventory
                blockEntity.setStack(0, player.getStackInHand(hand).copy());
                // Remove the stack from the player's hand
                player.getStackInHand(hand).setCount(0);
            } else if (blockEntity.getStack(1).isEmpty()) {
                blockEntity.setStack(1, player.getStackInHand(hand).copy());
                player.getStackInHand(hand).setCount(0);
            }
            else {
                // If the inventory is full we'll print it's contents
                player.sendMessage(new TranslatableText("block.pizza_mod.pizza_oven.items",
                        blockEntity.getStack(0).getName(), blockEntity.getStack(1).getName()),
                        false);
            }
        }
        else if (player.getStackInHand(hand).isEmpty() && player.isSneaking()){
            player.sendMessage(new TranslatableText("block.pizza_mod.pizza_oven.items",
                            blockEntity.getStack(0).getName(), blockEntity.getStack(1).getName()),
                    false);
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (world.isClient){return;}

        Inventory blockEntity = (Inventory) world.getBlockEntity(pos);

        if (player.getStackInHand(Hand.MAIN_HAND).isEmpty() && player.isSneaking()){
            for (int i = 0; i < blockEntity.size(); i++) {
                if (blockEntity.getStack(i) != ItemStack.EMPTY){
                    boolean succesful = player.getInventory().insertStack(blockEntity.getStack(i));
                    if (succesful) {
                        blockEntity.setStack(i, ItemStack.EMPTY);
                    }
                    return;
                }
            }
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (world.isClient){return;}

        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof ImplementedInventory){
            ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
        }

        super.onBreak(world, pos, state, player);
    }
}
