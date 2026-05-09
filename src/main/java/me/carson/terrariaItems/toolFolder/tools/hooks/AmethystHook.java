package me.carson.terrariaItems.toolFolder.tools.hooks;

import me.carson.terrariaItems.projectilesFolder.hookProjectiles.AmethystHookProjectile;
import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class AmethystHook extends Tool {

    public AmethystHook(Plugin plugin) {
        super(plugin,"amethyst_hook.name","#9696FF", Material.QUARTZ,"amethyst_hook","AmethystHook",0,"amethyst_hook.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new AmethystHook(plugin).createItem();
    }

    @Override
    public void rightActivate(Player player) {
        new AmethystHookProjectile(plugin).activateHook(player);
    }

    @Override
    public void cooldownEffect(Player player) {

    }
}
