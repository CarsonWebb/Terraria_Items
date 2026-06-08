package me.carson.terrariaItems.armorFolder.armors.desertProwlerArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class DesertProwlerLeggings extends Armor {

    public DesertProwlerLeggings(Plugin plugin){
        super(plugin,"desert_prowler_leggings.name","#9696FF", Material.DIAMOND_LEGGINGS,"desert_prowler_leggings","desert_prowler_armor",EquipmentSlot.LEGS,"DesertProwlerLeggings","desert_prowler_leggings.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new DesertProwlerLeggings(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addMaxStealth(player.getUniqueId(),10);
        playerInstance.addBonusRogue(player.getUniqueId(),0.05);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractMaxStealth(player.getUniqueId(),10);
        playerInstance.subtractBonusRogue(player.getUniqueId(),0.05);
    }
}
