package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.WandOfSparkingProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class WandOfSparking extends Magic {

    public WandOfSparking(Plugin plugin) {
        super(plugin,"wand_of_sparking.name","#9696FF", Material.STICK,"wand_of_sparking","WandOfSparking",15,1,2,0,50,2,"wand_of_sparking.lore");
    }

    @Override
    public void rightActivate(Player player) {
        if(manaManagerInstance.useMana(player,cost)){
            new WandOfSparkingProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
            player.getWorld().playSound(player.getLocation(), "terraria:magic_use", 1.0F, 1.0F);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new WandOfSparking(plugin).createItem();
    }

}
