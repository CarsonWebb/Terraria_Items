package me.carson.terrariaItems.toolFolder.tools.potions;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class WrathPotion extends Tool {

    public WrathPotion(Plugin plugin){
        super(plugin,"wrath_potion.name","#9696FF", Material.BREWER_POTTERY_SHERD,"wrath_potion","WrathPotion",20,"wrath_potion.lore");
    }

    @Override
    public void rightActivate(Player player) {
        customPotionInstance.addCustomPotionEffect(player, "damage",0.1,4800,"Wrath");
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(), "terraria:potion_drink", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new WrathPotion(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
