package me.carson.terrariaItems.handlers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;


public class CustomPotionHandler implements Listener {

    private final Plugin plugin;
    private static CustomPotionHandler instance;
    private final PlayerDataHandler playerDataHandler=PlayerDataHandler.getInstance();
    public record attributeID(Attribute attr, AttributeModifier mod) {};
    Multimap<Player, attributeID> playerPotions = ArrayListMultimap.create();

    public CustomPotionHandler(Plugin plugin){
        this.plugin=plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void potionAddAttribute(Player player,Attribute attribute,double amount, int duration,String id){
        AttributeModifier modifier= new AttributeModifier(new NamespacedKey(plugin,id),amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY);
        player.getAttribute(attribute).addModifier(modifier);
        playerPotions.put(player,new attributeID(attribute,modifier));
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.getAttribute(attribute).removeModifier(modifier);
        }, duration);
    }

    public void removePotionAttributes(Player player){
        if(playerPotions.containsKey(player)){
            Collection<attributeID> potionEffects = playerPotions.get(player);
            for(attributeID values:potionEffects){
                player.getAttribute(values.attr).removeModifier(values.mod);
            }
        }
    }

    public void addPotionEffect(Player player,double amount,int duration){
        playerDataHandler.addBonusDamage(player.getUniqueId(),amount);
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            playerDataHandler.subtractBonusDamage(player.getUniqueId(),amount);
        }, duration);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        removePotionAttributes(event.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        removePotionAttributes(event.getEntity().getPlayer());
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new CustomPotionHandler(plugin);
    }

    public static CustomPotionHandler getInstance() {
        return instance;
    }

}
