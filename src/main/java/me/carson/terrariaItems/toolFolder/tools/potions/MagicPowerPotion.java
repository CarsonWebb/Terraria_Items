package me.carson.terrariaItems.toolFolder.tools.potions;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class MagicPowerPotion extends Tool {

    public MagicPowerPotion(Plugin plugin){
        super(plugin,"magic_power_potion.name","#9696FF", Material.BREWER_POTTERY_SHERD,"magic_power_potion","MagicPowerPotion",20,"magic_power_potion.lore");
    }

    @Override
    public void rightActivate(Player player) {
        customPotionInstance.addCustomPotionEffect(player, "magic",0.2,4800,"Magic_Power");
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(), "terraria:potion_drink", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new MagicPowerPotion(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
