package me.carson.terrariaItems.armorFolder.armors.desertProwlerArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.plugin.Plugin;

public class DesertProwlerHat extends Armor {

    public DesertProwlerHat(Plugin plugin){
        super(plugin,"desert_prowler_hat.name","#9696FF", Material.DIAMOND_HELMET,"desert_prowler_hat","desert_prowler_armor",EquipmentSlot.HEAD,"DesertProwlerHat","desert_prowler_hat.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item= new DesertProwlerHat(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setModel(null);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
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
