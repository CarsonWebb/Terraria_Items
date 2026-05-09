package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.notes.*;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class MagicalHarp extends Magic {

    public MagicalHarp(Plugin plugin) {
        super(plugin,"magical_harp.name","#FF96FF", Material.AMETHYST_SHARD,"magical_harp","MagicalHarp",3,0.4f,7,0.05f,100,5,"magical_harp.lore");
    }

    @Override
    public void rightActivate(Player player) {
        if(manaManagerInstance.useMana(player,cost)){
            int note = ThreadLocalRandom.current().nextInt(1, 4);
            switch (note){
                case 1 -> new Note1(plugin).createProjectile(player,speed,damage,spread,duration);
                case 2 -> new Note2(plugin).createProjectile(player,speed,damage,spread,duration);
                case 3 -> new Note3(plugin).createProjectile(player,speed,damage,spread,duration);
                default -> {
                    return;
                }
            }
            player.getWorld().playSound(player.getLocation(), "terraria:harp", 0.75F, 1.0F);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MagicalHarp(plugin).createItem();
    }

}
