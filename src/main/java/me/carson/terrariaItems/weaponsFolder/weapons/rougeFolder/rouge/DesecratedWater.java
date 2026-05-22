package me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.rouge;

import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.ConsecratedWaterProjectile;
import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.DesecratedWaterProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.Rouge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class DesecratedWater extends Rouge {

    public DesecratedWater(Plugin plugin) {
        super(plugin,"desecrated_water.name","#FF96FF", Material.BURN_POTTERY_SHERD,"desecrated_water","DesecratedWater",20,1f,0,0,50,"desecrated_water.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new DesecratedWater(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {
        player.getWorld().playSound(player.getLocation(), "terraria:bottle_throw", 1.0F, 1.0F);
        new DesecratedWaterProjectile(plugin).createDesecratedWaterProjectile(player,speed,damage,spread,duration,0,0.05f,0,stealthManager.getStealth(player.getUniqueId()),stealthManager.isStealthStrike(player));
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
