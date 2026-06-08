package me.carson.terrariaItems.armorFolder.armors.desertProwlerArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class DesertProwlerShirt extends Armor {

    public DesertProwlerShirt(Plugin plugin){
        super(plugin,"desert_prowler_shirt.name","#9696FF", Material.DIAMOND_CHESTPLATE,"desert_prowler_shirt","desert_prowler_armor",EquipmentSlot.CHEST,"DesertProwlerShirt","desert_prowler_shirt.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new DesertProwlerShirt(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addMaxStealth(player.getUniqueId(),20);
        playerInstance.addBonusRogue(player.getUniqueId(),0.05);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractMaxStealth(player.getUniqueId(),20);
        playerInstance.subtractBonusRogue(player.getUniqueId(),0.05);
    }
}
