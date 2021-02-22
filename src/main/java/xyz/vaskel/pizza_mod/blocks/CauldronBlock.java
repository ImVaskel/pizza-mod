package xyz.vaskel.pizza_mod.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xyz.vaskel.pizza_mod.PizzaModItems;

public class CauldronBlock extends Block implements BlockEntityProvider {

    public CauldronBlock() {
        super(FabricBlockSettings.of(Material.METAL).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).
                hardness(6.0f).requiresTool().nonOpaque());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) { return ActionResult.SUCCESS;}

        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof MilkCauldronEntity) {

            MilkCauldronEntity entity = (MilkCauldronEntity) blockEntity;

            if (entity.milk == 81000) {
                return ActionResult.SUCCESS;
            }

            if (player.getStackInHand(hand).getItem() == Items.MILK_BUCKET) {


                entity.addMilk(27000); // TODO: IMPLEMENT DIFFERENT STORAGE

                player.setStackInHand(hand, new ItemStack(Items.BUCKET));

                entity.sync();

            } else if (player.getStackInHand(hand).getItem() == PizzaModItems.MILK_BOWL_ITEM) {

                entity.addMilk(9000);

                player.getStackInHand(hand).setCount(player.getStackInHand(hand).getCount() - 1);

                entity.sync();

            }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MilkCauldronEntity(pos, state);
    }
}
