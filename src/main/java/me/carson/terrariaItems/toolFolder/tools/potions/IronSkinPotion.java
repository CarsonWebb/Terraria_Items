package me.carson.terrariaItems.toolFolder.tools.potions;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class IronSkinPotion extends Tool {

    public IronSkinPotion(Plugin plugin){
        super(plugin,"ironskin_potion.name","#9696FF", Material.BREWER_POTTERY_SHERD,"ironskin_potion","IronSkinPotion",20,"ironskin_potion.lore");
    }

    @Override
    public void rightActivate(Player player) {
        customPotionInstance.potionAddArmor(player,4,800,"ironskin");
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(), "terraria:potion_drink", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new IronSkinPotion(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
