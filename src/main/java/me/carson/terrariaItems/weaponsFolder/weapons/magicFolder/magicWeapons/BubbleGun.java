package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.AmethystBolt;
import me.carson.terrariaItems.projectilesFolder.projectiles.Bubble;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class BubbleGun extends Magic {

    public BubbleGun(Plugin plugin) {
        super(plugin,"bubble_gun.name","#FFFF0A", Material.PRISMARINE_CRYSTALS,"bubble_gun","BubbleGun",0,0.75f,10,0.25f,20,5,"bubble_gun.lore");
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if(manaManagerInstance.useMana(player,cost)){
            new Bubble(plugin).createProjectile(player,speed,damage,spread,duration);
            new Bubble(plugin).createProjectile(player,speed,damage,spread,duration);
            new Bubble(plugin).createProjectile(player,speed,damage,spread,duration);
            player.getWorld().playSound(player.getLocation(), "terraria:bubble_gun_use", 1.0F, 1.0F);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new BubbleGun(plugin).createItem();
    }


}
