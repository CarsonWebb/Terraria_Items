package me.carson.terrariaItems.toolFolder.tools.potions;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class SeafoodDinner extends Tool {

    public SeafoodDinner(Plugin plugin){
        super(plugin,"seafood_dinner.name","#96FF96", Material.BREWER_POTTERY_SHERD,"seafood_dinner","SeafoodDinner",20,"seafood_dinner.lore");
    }

    @Override
    public void rightActivate(Player player) {
        customPotionInstance.addCustomPotionEffect(player, "damage",0.075,16800,"Plenty_Satisfied");
        customPotionInstance.addCustomPotionEffect(player, "crit",0.03,16800,"Plenty_Satisfied");
        customPotionInstance.potionAddAttribute(player, Attribute.ARMOR,2,16800,"Plenty_Satisfied");
        customPotionInstance.potionAddAttribute(player, Attribute.BLOCK_BREAK_SPEED,0.5,16800,"Plenty_Satisfied");
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(), "terraria:food_eat", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new SeafoodDinner(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
