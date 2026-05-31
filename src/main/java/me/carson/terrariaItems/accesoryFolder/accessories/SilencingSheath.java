package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class SilencingSheath extends Accessory  {

    public SilencingSheath(Plugin plugin){
        super(plugin,"silencing_sheath.name","#96FF96",Material.NETHER_BRICK,"silencing_sheath","SilencingSheath","silencing_sheath.lore");
    }

    @Override
    public void activateEffect(Player player){
        playerInstance.addMaxStealth(player.getUniqueId(),10);
        playerInstance.setStealthGeneration(player.getUniqueId(),0.96);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractMaxStealth(player.getUniqueId(),10);
        playerInstance.setStealthGeneration(player.getUniqueId(),1);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }


    public static ItemStack getItem(Plugin plugin) {
        return new SilencingSheath(plugin).createItem();
    }

}
