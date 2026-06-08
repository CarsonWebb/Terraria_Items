package me.carson.terrariaItems.armorFolder.armors.forbiddenArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ForbiddenLeggings extends Armor {

    public ForbiddenLeggings(Plugin plugin){
        super(plugin,"forbidden_leggings.name","#FF96FF", Material.NETHERITE_LEGGINGS,"forbidden_leggings","forbidden_armor",EquipmentSlot.LEGS,"ForbiddenLeggings","forbidden_leggings.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new ForbiddenLeggings(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,2,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusMagic(player.getUniqueId(),0.1);
        playerInstance.addBonusRogue(player.getUniqueId(),0.1);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusMagic(player.getUniqueId(),0.1);
        playerInstance.subtractBonusRogue(player.getUniqueId(),0.1);
    }
}
