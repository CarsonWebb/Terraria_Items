package me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.rouge;

import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.GlaiveProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.Rouge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Glaive extends Rouge {

    public Glaive(Plugin plugin) {
        super(plugin,"glaive.name","#FFC896", Material.SCRAPE_POTTERY_SHERD,"glaive","Glaive",15,0.75f,3,0,150,"glaive.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Glaive(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 1.0F, 1.0F);
        if(stealthManager.isStealthStrike(player)){
            stealthAttack(player);
        }else{
            new GlaiveProjectile(plugin).createGlaiveProjectile(player,speed,damage,spread,duration,0,0.04f,15,stealthManager.getStealth(player.getUniqueId()));
        }
        stealthManager.reduceStealth(player);
        stealthManager.startStealthRegenDelay(player);
    }

    @Override
    public void rightActivate(Player player) {
        leftActivate(player);
    }

    @Override
    public void stealthAttack(Player player) {
        new GlaiveProjectile(plugin).onStealthThrow(player,speed,damage,spread,duration,0,0.04f,15,stealthManager.getStealth(player.getUniqueId()));
    }
}
