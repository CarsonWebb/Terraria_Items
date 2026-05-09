package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.AmethystBolt;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class AmethystStaff extends Magic {

    public AmethystStaff(Plugin plugin) {
        super(plugin,"amethyst_staff.name","#FFFFFF", Material.AMETHYST_SHARD,"amethyst_staff","AmethystStaff",15,1,4,0,50,5,"amethyst_staff.lore");
    }

    @Override
    public void rightActivate(Player player) {
        if(manaManagerInstance.useMana(player,cost)){
            new AmethystBolt(plugin).createProjectile(player,speed,damage,spread,duration);
            player.getWorld().playSound(player.getLocation(), "terraria:magic_use", 1.0F, 1.0F);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new AmethystStaff(plugin).createItem();
    }

}
