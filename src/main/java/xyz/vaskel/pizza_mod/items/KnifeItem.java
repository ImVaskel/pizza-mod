package xyz.vaskel.pizza_mod.items;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class KnifeItem extends SwordItem {

    public KnifeItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
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
