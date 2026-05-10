package me.carson.terrariaItems.toolFolder.tools;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;


public class StepStool extends Tool{

    public StepStool(Plugin plugin){
        super(plugin,"step_stool.name","#9696FF",Material.ARCHER_POTTERY_SHERD,"step_stool","StepStool",5,"step_stool.lore");
    }

    @Override
    public void rightActivate(Player player){

    }

    @Override
    public void cooldownEffect(Player player) {
        //N/A
    }

    public static ItemStack getItem(Plugin plugin) {
        return new StepStool(plugin).createItem();
    }
}
