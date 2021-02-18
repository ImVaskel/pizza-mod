package xyz.vaskel.pizza_mod.items;

import net.minecraft.item.Item;

// Item that can be reused in crafting recipes
public class ReusableItem extends Item {

    public ReusableItem(Settings settings) {
        super(settings);
    }

    @Override
    public Item getRecipeRemainder(){
        return this;
    }

    @Override
    public boolean hasRecipeRemainder(){
        return true;
    }

}
