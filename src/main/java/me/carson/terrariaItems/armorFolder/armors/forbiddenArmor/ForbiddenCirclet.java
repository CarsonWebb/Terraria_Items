package me.carson.terrariaItems.armorFolder.armors.forbiddenArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.plugin.Plugin;

public class ForbiddenCirclet extends Armor {

    public ForbiddenCirclet(Plugin plugin){
        super(plugin,"forbidden_circlet.name","#FF96FF", Material.NETHERITE_HELMET,"forbidden_circlet","forbidden_armor", EquipmentSlot.HEAD,"ForbiddenCirclet","forbidden_circlet.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item= new ForbiddenCirclet(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,5,true);
        meta.setEnchantmentGlintOverride(false);
        EquippableComponent equip= meta.getEquippable();
        equip.setModel(null);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusRogue(player.getUniqueId(),0.15);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.addBonusRogue(player.getUniqueId(),0.15);
    }

}
