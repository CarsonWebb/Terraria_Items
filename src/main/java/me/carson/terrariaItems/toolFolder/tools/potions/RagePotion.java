package me.carson.terrariaItems.toolFolder.tools.potions;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class RagePotion extends Tool {

    public RagePotion(Plugin plugin){
        super(plugin,"rage_potion.name","#9696FF", Material.BREWER_POTTERY_SHERD,"rage_potion","RagePotion",20,"rage_potion.lore");
    }

    @Override
    public void rightActivate(Player player) {
        customPotionInstance.addCustomPotionEffect(player, "crit",0.1,4800,"Rage");
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(), "terraria:potion_drink", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new RagePotion(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
