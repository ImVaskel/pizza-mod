package xyz.vaskel.pizza_mod.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.List;

public class PineapplePizzaItem extends Item {

    public PineapplePizzaItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("item.pizza_mod.pineapple_pizza.tooltip"));
    }
}
