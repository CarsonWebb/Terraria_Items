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

public class ForbiddenTreads extends Armor {

    public ForbiddenTreads(Plugin plugin){
        super(plugin,"forbidden_treads.name","#FF96FF", Material.NETHERITE_BOOTS,"forbidden_treads","forbidden_armor",EquipmentSlot.FEET,"ForbiddenTreads","forbidden_treads.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new ForbiddenTreads(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,2,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addExtraMana(player.getUniqueId(),40);
        playerInstance.addBonusMagic(player.getUniqueId(),0.1);
        playerInstance.addMaxStealth(player.getUniqueId(),40);
        playerInstance.addBonusRogue(player.getUniqueId(),0.1);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractExtraMana(player.getUniqueId(),40);
        playerInstance.subtractBonusMagic(player.getUniqueId(),0.1);
        playerInstance.subtractMaxStealth(player.getUniqueId(),40);
        playerInstance.subtractBonusRogue(player.getUniqueId(),0.1);
    }
}
