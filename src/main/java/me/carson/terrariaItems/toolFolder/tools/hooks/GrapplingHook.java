package me.carson.terrariaItems.toolFolder.tools.hooks;

import me.carson.terrariaItems.projectilesFolder.hookProjectiles.GrapplingHookProjectile;
import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class GrapplingHook extends Tool {

    public GrapplingHook(Plugin plugin) {
        super(plugin,"grappling_hook.name","#9696FF", Material.QUARTZ,"grappling_hook","GrapplingHook",40,"grappling_hook.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new GrapplingHook(plugin).createItem();
    }

    @Override
    public void rightActivate(Player player) {
        new GrapplingHookProjectile(plugin).createHook(player);
    }

    @Override
    public void cooldownEffect(Player player) {

    }
}
