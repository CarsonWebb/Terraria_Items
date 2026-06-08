package me.carson.terrariaItems.armorFolder.armors.forbiddenArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ForbiddenElytra extends Armor {

    public ForbiddenElytra(Plugin plugin){
        super(plugin,"forbidden_elytra.name","#FF96FF", Material.ELYTRA,"forbidden_elytra_sprite","forbidden_elytra_armor", EquipmentSlot.CHEST,"ForbiddenElytra","forbidden_elytra.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new ForbiddenElytra(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"hallowed_leggings_armor"),4.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addExtraMana(player.getUniqueId(),20);
        playerInstance.addMaxStealth(player.getUniqueId(),20);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractExtraMana(player.getUniqueId(),20);
        playerInstance.subtractMaxStealth(player.getUniqueId(),20);
    }

}
