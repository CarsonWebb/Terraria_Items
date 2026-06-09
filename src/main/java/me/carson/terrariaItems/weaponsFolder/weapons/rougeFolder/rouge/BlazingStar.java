package me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.rouge;

import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.BlazingStarProjectile;
import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.GlaiveProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.Rouge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class BlazingStar extends Rouge {

    public BlazingStar(Plugin plugin) {
        super(plugin,"blazing_star.name","#FF9696", Material.SCRAPE_POTTERY_SHERD,"blazing_star","BlazingStarProjectile",15,0.9f,6,0,200,"blazing_star.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new BlazingStar(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 1.0F, 1.0F);
        new BlazingStarProjectile(plugin).createBlazingStarProjectile(player,speed,damage,spread,duration,0,0.04f,15,stealthManager.getStealth(player.getUniqueId()),stealthManager.isStealthStrike(player));
        stealthManager.reduceStealth(player);
        stealthManager.startStealthRegenDelay(player);
    }

    @Override
    public void rightActivate(Player player) {
        leftActivate(player);
    }

    @Override
    public void stealthAttack(Player player) {

    }
}
