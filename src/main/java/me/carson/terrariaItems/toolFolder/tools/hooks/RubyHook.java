package me.carson.terrariaItems.toolFolder.tools.hooks;

import me.carson.terrariaItems.projectilesFolder.hookProjectiles.AmethystHookProjectile;
import me.carson.terrariaItems.projectilesFolder.hookProjectiles.RubyHookProjectile;
import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class RubyHook extends Tool {

    public RubyHook(Plugin plugin) {
        super(plugin,"ruby_hook.name","#9696FF", Material.QUARTZ,"ruby_hook","RubyHook",0,"ruby_hook.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new RubyHook(plugin).createItem();
    }

    @Override
    public void rightActivate(Player player) {
        new RubyHookProjectile(plugin).activateHook(player);
    }

    @Override
    public void cooldownEffect(Player player) {

    }
}
