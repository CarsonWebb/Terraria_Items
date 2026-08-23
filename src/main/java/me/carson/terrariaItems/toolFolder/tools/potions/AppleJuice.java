package me.carson.terrariaItems.toolFolder.tools.potions;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class AppleJuice extends Tool {

    public AppleJuice(Plugin plugin){
        super(plugin,"apple_juice.name","#9696FF", Material.BREWER_POTTERY_SHERD,"apple_juice","AppleJuice",20,"apple_juice.lore");
    }

    @Override
    public void rightActivate(Player player) {
        customPotionInstance.addCustomFoodEffect(player,12000,"Well_Fed");
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(), "terraria:food_eat", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new AppleJuice(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(64);
        item.setItemMeta(meta);
        return item;
    }

}
