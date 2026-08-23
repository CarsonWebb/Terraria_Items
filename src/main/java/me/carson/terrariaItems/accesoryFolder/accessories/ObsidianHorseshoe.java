package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ObsidianHorseshoe extends Accessory{

    public ObsidianHorseshoe(Plugin plugin){
        super(plugin,"obsidian_horseshoe.name","#FF9696",Material.NETHER_BRICK,"obsidian_horseshoe","ObsidianHorseshoe","obsidian_horseshoe.lore");
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {
        if ((OBSIDIAN_SKULL_DAMAGE.contains(event.getCause()))||(event.getCause() == EntityDamageEvent.DamageCause.FALL)||(event.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL)){
            event.setCancelled(true);
        }
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new ObsidianHorseshoe(plugin).createItem();
    }

}
