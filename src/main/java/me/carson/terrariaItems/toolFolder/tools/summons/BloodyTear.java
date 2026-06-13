package me.carson.terrariaItems.toolFolder.tools.summons;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class BloodyTear extends Tool {

    public BloodyTear(Plugin plugin){
        super(plugin,"bloody_tear.name","#96FF96", Material.NAUTILUS_SHELL,"bloody_tear","BloodyTear",40,"bloody_tear.lore");
    }

    @Override
    public void rightActivate(Player player) {
        if(player.getWorld().getEnvironment() == World.Environment.NORMAL&&isNight(player.getWorld())&& !worldInstance.getBloodMoon()){
            if(worldInstance.getBloodMoonEnabled()){
                bloodMoonManagerInstance.startBloodMoon(player.getWorld());
                player.getInventory().removeItem(BloodyTear.getItem(plugin));
            }else{
                player.sendMessage(ChatColor.RED+lang.get("commands","blood_moon_msg.disabled"));
            }
        }
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new BloodyTear(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(64);
        item.setItemMeta(meta);
        return item;
    }

}
