package me.carson.terrariaItems.toolFolder.tools.hooks;

import me.carson.terrariaItems.projectilesFolder.hookProjectiles.DiamondHookProjectile;
import me.carson.terrariaItems.projectilesFolder.hookProjectiles.RubyHookProjectile;
import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class DiamondHook extends Tool {

    public DiamondHook(Plugin plugin) {
        super(plugin,"diamond_hook.name","#9696FF", Material.QUARTZ,"diamond_hook","DiamondHook",0,"diamond_hook.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new DiamondHook(plugin).createItem();
    }

    @Override
    public void rightActivate(Player player) {
        new DiamondHookProjectile(plugin).activateHook(player);
    }

    @Override
    public void cooldownEffect(Player player) {

    }
}
