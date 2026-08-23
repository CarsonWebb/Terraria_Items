package me.carson.terrariaItems.toolFolder.tools.potions;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class GoldenDelight extends Tool {

    public GoldenDelight(Plugin plugin){
        super(plugin,"golden_delight.name","#D2A0FF", Material.BREWER_POTTERY_SHERD,"golden_delight","GoldenDelight",20,"golden_delight.lore");
    }

    @Override
    public void rightActivate(Player player) {
        customPotionInstance.addCustomFoodEffect(player,36000,"Exquisitely_Stuffed");
        player.getInventory().removeItem(getItem(plugin));
        player.setFoodLevel(player.getFoodLevel()+15);
        player.getWorld().playSound(player.getLocation(), "terraria:food_eat", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new GoldenDelight(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(64);
        item.setItemMeta(meta);
        return item;
    }

}
