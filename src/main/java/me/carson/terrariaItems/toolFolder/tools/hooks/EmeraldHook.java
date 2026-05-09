package me.carson.terrariaItems.toolFolder.tools.hooks;

import me.carson.terrariaItems.projectilesFolder.hookProjectiles.EmeraldHookProjectile;
import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class EmeraldHook extends Tool {

    public EmeraldHook(Plugin plugin) {
        super(plugin,"emerald_hook.name","#9696FF", Material.QUARTZ,"emerald_hook","EmeraldHook",0,"emerald_hook.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new EmeraldHook(plugin).createItem();
    }

    @Override
    public void rightActivate(Player player) {
        new EmeraldHookProjectile(plugin).activateHook(player);
    }

    @Override
    public void cooldownEffect(Player player) {

    }
}
