package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class RuinMedallion extends Accessory  {

    public RuinMedallion(Plugin plugin){
        super(plugin,"ruin_medallion.name","#FF96FF",Material.NETHER_BRICK,"ruin_medallion","RuinMedallion","ruin_medallion.lore");
    }

    @Override
    public void activateEffect(Player player){
        playerInstance.setStealthThreshold(player.getUniqueId(),0.75);
        playerInstance.addBonusRouge(player.getUniqueId(),0.06);
        playerInstance.addCritChance(player.getUniqueId(),0.06);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.setStealthThreshold(player.getUniqueId(),1);
        playerInstance.subtractCritChance(player.getUniqueId(),0.06);
        playerInstance.subtractBonusRouge(player.getUniqueId(),0.06);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }


    public static ItemStack getItem(Plugin plugin) {
        return new RuinMedallion(plugin).createItem();
    }

}
