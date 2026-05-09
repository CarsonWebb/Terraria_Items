package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.RubyBolt;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class RubyStaff extends Magic {

    public RubyStaff(Plugin plugin) {
        super(plugin,"ruby_staff.name","#9696FF", Material.DIAMOND,"ruby_staff","RubyStaff",12,1.5f,6,0,50,7,"ruby_staff.lore");
    }

    @Override
    public void rightActivate(Player player) {
        if(manaManagerInstance.useMana(player,cost)){
            new RubyBolt(plugin).createProjectile(player,speed,damage,spread,duration);
            player.getWorld().playSound(player.getLocation(), "terraria:magic_use", 1.0F, 1.0F);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new RubyStaff(plugin).createItem();
    }

}
