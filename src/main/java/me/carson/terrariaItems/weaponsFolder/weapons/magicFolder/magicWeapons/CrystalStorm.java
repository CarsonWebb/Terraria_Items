package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.CrystalStormProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class CrystalStorm extends Magic {

    public CrystalStorm(Plugin plugin) {
        super(plugin,"crystal_storm.name","#FF9696", Material.BOOK,"crystal_storm","CrystalStorm",0,1.5f,6,0.15f,50,5,"crystal_storm.lore");
    }

    @Override
    public void rightActivate(Player player) {
        if(manaManagerInstance.useMana(player,cost)){
            new CrystalStormProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
            player.getWorld().playSound(player.getLocation(), "terraria:falling_star", 0.75F, 1.0F);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new CrystalStorm(plugin).createItem();
    }
}
