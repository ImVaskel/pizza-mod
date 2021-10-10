package gay.vaskel.pizza_mod.utils

import gay.vaskel.pizza_mod.PizzaMod
import net.minecraft.util.Identifier

/*
A helper class to create an identifier from the path
 */
class ModIdentifier(path: String): Identifier(PizzaMod.MOD_ID, path) {
}