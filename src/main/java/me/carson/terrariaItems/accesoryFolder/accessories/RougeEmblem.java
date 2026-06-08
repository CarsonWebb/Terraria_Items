package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class RougeEmblem extends Accessory {

    public RougeEmblem(Plugin plugin){
        super(plugin,"rogue_emblem.name","#FF9696", Material.NETHER_BRICK,"rogue_emblem","RougeEmblem","rogue_emblem.lore");
    }

    @Override
    public void activateEffect(Player player) {
        playerInstance.addBonusRogue(player.getUniqueId(),0.2);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractBonusRogue(player.getUniqueId(),0.2);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new RougeEmblem(plugin).createItem();
    }

}
