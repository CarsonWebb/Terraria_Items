package me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.rouge;

import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.ExorcismProjectile;
import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.IronFranciscaProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.Rouge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Exorcism extends Rouge {

    public Exorcism(Plugin plugin) {
        super(plugin,"exorcism.name","#D2A0FF", Material.BLADE_POTTERY_SHERD,"exorcism","Exorcism",10,1f,0,0,50,"exorcism.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Exorcism(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 1.0F, 1.0F);

        new ExorcismProjectile(plugin).createExorcismProjectile(player,stealthManager.getStealth(player.getUniqueId()),stealthManager.isStealthStrike(player));

        stealthManager.reduceStealth(player);
        stealthManager.startStealthRegenDelay(player);
    }

    @Override
    public void rightActivate(Player player) {
        leftActivate(player);
    }

    @Override
    public void stealthAttack(Player player) {

    }
}
