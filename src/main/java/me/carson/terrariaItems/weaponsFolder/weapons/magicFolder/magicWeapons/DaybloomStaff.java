package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.DaybloomPellet;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class DaybloomStaff extends Magic {

    public DaybloomStaff(Plugin plugin) {
        super(plugin,"daybloom_staff.name","#9696FF", Material.AMETHYST_SHARD,"daybloom_staff","DaybloomStaff",30,0.5f,1.5f,0.2f,40,5,"daybloom_staff.lore");
    }

    @Override
    public void rightActivate(Player player) {
        if(manaManagerInstance.useMana(player,cost)){
            new DaybloomPellet(plugin).shootPellets(player, speed, damage, spread, duration);
            player.getWorld().playSound(player.getLocation(), "terraria:magic_use_2", 1.0F, 1.0F);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new DaybloomStaff(plugin).createItem();
    }

}
