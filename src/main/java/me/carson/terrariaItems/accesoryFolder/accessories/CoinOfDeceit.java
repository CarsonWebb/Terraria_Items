package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class CoinOfDeceit extends Accessory  {

    public CoinOfDeceit(Plugin plugin){
        super(plugin,"coin_of_deceit.name","#9696FF",Material.NETHER_BRICK,"coin_of_deceit","CoinOfDeceit","coin_of_deceit.lore");
    }

    @Override
    public void activateEffect(Player player){
        playerInstance.setStealthThreshold(player.getUniqueId(),0.9);
        playerInstance.addCritChance(player.getUniqueId(),0.06);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.setStealthThreshold(player.getUniqueId(),1);
        playerInstance.subtractCritChance(player.getUniqueId(),0.06);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }


    public static ItemStack getItem(Plugin plugin) {
        return new CoinOfDeceit(plugin).createItem();
    }

}
