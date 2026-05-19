package me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.rouge;

import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.IronFranciscaProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.Rouge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class IronFrancisca extends Rouge {

    public IronFrancisca(Plugin plugin) {
        super(plugin,"iron_francisca.name","#9696FF", Material.SCRAPE_POTTERY_SHERD,"iron_francisca","IronFrancisca",10,1.2f,4,0,50,"iron_francisca.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new IronFrancisca(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 1.0F, 1.0F);
        if(stealthManager.isStealthStrike(player)){
            stealthAttack(player);
        }else{
            new IronFranciscaProjectile(plugin).createProjectile(player,speed,damage,spread,duration,0,0.03f,15,stealthManager.getStealth(player.getUniqueId()));
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
        new IronFranciscaProjectile(plugin).onStealthThrow(player,speed,damage,spread,duration,0,0.03f,15,stealthManager.getStealth(player.getUniqueId()));
    }
}
