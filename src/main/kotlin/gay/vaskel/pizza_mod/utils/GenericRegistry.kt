package gay.vaskel.pizza_mod.utils

import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

// Roughly based on https://github.com/lucaargolo/lifts/blob/1.17/src/main/kotlin/io/github/lucaargolo/lifts/utils/GenericCompendium.kt
// not sure if credit is needed, but to avoid any licensing problems it is given.
open class GenericRegistry<T: Any>(private val type: Registry<T>) {
    protected val map = mutableMapOf<Identifier, T>()

    protected fun register(identifier: String, entry: T): T {
        return register(ModIdentifier(identifier), entry)
    }

    protected fun register(identifier: Identifier, entry: T): T{
        map[identifier] = entry
        return entry
    }

    fun get(identifier: Identifier): T? {
        return type.get(identifier)
    }

    fun getId(entry: T): Identifier? {
        return type.getId(entry)
    }

    open fun onInitialize() {
        map.forEach() {
            (id, entry) -> Registry.register(type, id, entry)
        }
    }
}