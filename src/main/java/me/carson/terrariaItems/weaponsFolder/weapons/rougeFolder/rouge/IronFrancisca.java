package me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.rouge;

import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.IronFranciscaProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.Rouge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class IronFrancisca extends Rouge {

    public IronFrancisca(Plugin plugin) {
        super(plugin,"iron_francisca.name","#9696FF", Material.IRON_SWORD,"iron_francisca","IronFrancisca",10,1.5f,3,0,50,"iron_francisca.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new IronFrancisca(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {
        if(stealthManager.isMaxStealth(player)){
            stealthAttack(player);
        }else{
            new IronFranciscaProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
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
        new IronFranciscaProjectile(plugin).onStealthThrow(player);
    }
}
