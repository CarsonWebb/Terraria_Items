package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class TestStealth extends Accessory  {

    public TestStealth(Plugin plugin){
        super(plugin,"test.name","#9696FF",Material.COPPER_INGOT,"aglet","TestStealth","test.lore");
    }

    @Override
    public void activateEffect(Player player){
        playerInstance.addMaxStealth(player.getUniqueId(),40);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractMaxStealth(player.getUniqueId(),40);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }


    public static ItemStack getItem(Plugin plugin) {
        return new TestStealth(plugin).createItem();
    }

}
