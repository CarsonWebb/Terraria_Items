package me.carson.terrariaItems.armorFolder.armors.desertProwlerArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class DesertProwlerPants extends Armor {

    public DesertProwlerPants(Plugin plugin){
        super(plugin,"desert_prowler_pants.name","#9696FF", Material.DIAMOND_BOOTS,"desert_prowler_pants","desert_prowler_armor",EquipmentSlot.FEET,"DesertProwlerPants","desert_prowler_pants.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new DesertProwlerPants(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addMaxStealth(player.getUniqueId(),10);
        playerInstance.addCritChance(player.getUniqueId(),0.04);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractMaxStealth(player.getUniqueId(),10);
        playerInstance.subtractCritChance(player.getUniqueId(),0.04);
    }
}
