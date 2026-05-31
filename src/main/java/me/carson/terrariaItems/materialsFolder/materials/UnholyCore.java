package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class UnholyCore extends Material {

    public UnholyCore(Plugin plugin) {
        super(plugin,"unholy_core.name","#FF96FF", org.bukkit.Material.POPPED_CHORUS_FRUIT,"unholy_core","UnholyCore","unholy_core.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new UnholyCore(plugin).createItem();
    }
}
