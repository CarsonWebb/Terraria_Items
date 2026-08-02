package me.carson.terrariaItems.handlers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class CustomPotionHandler implements Listener {

    private final Plugin plugin;
    private static CustomPotionHandler instance;
    private final PlayerDataHandler playerDataHandler=PlayerDataHandler.getInstance();
    public record attributeID(Attribute attr, AttributeModifier mod) {}
    Multimap<Player, attributeID> playerPotions = ArrayListMultimap.create();
    public record attributeModTask(AttributeModifier mod,BukkitTask task) {}
    Multimap<UUID, attributeModTask> activeTasks = ArrayListMultimap.create();

    public CustomPotionHandler(Plugin plugin){
        this.plugin=plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void potionAddAttribute(Player player,Attribute attribute,double amount, int duration,String id){
        UUID uuid = player.getUniqueId();
        AttributeModifier modifier= new AttributeModifier(new NamespacedKey(plugin,id),amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY);

        if(hasAttributeModifier(player,attribute,modifier)){
            Collection<attributeModTask> tasks = activeTasks.get(uuid);
            tasks.removeIf(value -> {
                if(value.mod.equals(modifier)){
                    value.task.cancel();
                    return true;
                }
                return false;
            });
        }else{
            player.getAttribute(attribute).addModifier(modifier);
            playerPotions.put(player,new attributeID(attribute,modifier));
        }

        BukkitTask[] holder = new BukkitTask[1];
        holder[0] = Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.getAttribute(attribute).removeModifier(modifier);
            activeTasks.remove(uuid,new attributeModTask(modifier,holder[0]));
        }, duration);
        activeTasks.put(uuid,new attributeModTask(modifier,holder[0]));
    }

    public void removePotionAttributes(Player player){
        UUID uuid = player.getUniqueId();
        if(playerPotions.containsKey(player)){
            for(attributeID values : playerPotions.get(player)){
                AttributeInstance instance = player.getAttribute(values.attr());
                if(instance != null) instance.removeModifier(values.mod());
            }
            playerPotions.removeAll(player);
        }
        if(activeTasks.containsKey(uuid)){
            for(attributeModTask value : activeTasks.get(uuid)){
                value.task().cancel();
            }
            activeTasks.removeAll(uuid);
        }
    }


    public boolean hasAttributeModifier(Player player,Attribute attribute,AttributeModifier attributeModifier){
        return Objects.requireNonNull(player.getAttribute(attribute)).getModifiers().contains(attributeModifier);
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
