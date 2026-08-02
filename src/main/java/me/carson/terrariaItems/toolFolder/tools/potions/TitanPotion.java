package me.carson.terrariaItems.toolFolder.tools.potions;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class TitanPotion extends Tool {

    public TitanPotion(Plugin plugin){
        super(plugin,"titan_potion.name","#9696FF", Material.BREWER_POTTERY_SHERD,"titan_potion","TitanPotion",20,"titan_potion.lore");
    }

    @Override
    public void rightActivate(Player player) {
        customPotionInstance.potionAddAttribute(player, Attribute.ATTACK_KNOCKBACK,1.5,9600,"titan");
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(), "terraria:potion_drink", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new TitanPotion(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
