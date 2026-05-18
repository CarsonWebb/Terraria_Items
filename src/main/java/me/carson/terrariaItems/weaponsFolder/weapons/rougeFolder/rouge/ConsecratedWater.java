package me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.rouge;

import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.ConsecratedWaterProjectile;
import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.GlaiveProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.Rouge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ConsecratedWater extends Rouge {

    public ConsecratedWater(Plugin plugin) {
        super(plugin,"consecrated_water.name","#FFC896", Material.SCRAPE_POTTERY_SHERD,"consecrated_water","ConsecratedWater",20,1f,0,0,50,"consecrated_water.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ConsecratedWater(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {
        player.getWorld().playSound(player.getLocation(), "terraria:bottle_throw", 1.0F, 1.0F);
        if(stealthManager.isMaxStealth(player)){
            stealthAttack(player);
        }else{
            new ConsecratedWaterProjectile(plugin).createProjectile(player,speed,damage,spread,duration,0,0.05f,0);
        }
        stealthManager.removeStealth(player);
        stealthManager.startStealthRegenDelay(player);
    }

    @Override
    public void rightActivate(Player player) {
        leftActivate(player);
    }

    @Override
    public void stealthAttack(Player player) {
        new ConsecratedWaterProjectile(plugin).onStealthThrow(player,speed,damage,spread,duration,0,0.05f,0);
    }
}
