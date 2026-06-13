package me.carson.terrariaItems.handlers;

import me.carson.terrariaItems.TILangManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class BloodMoonManager implements Listener{

    private final Plugin plugin;
    private boolean checked=false;
    private final WorldDataHandler instance=WorldDataHandler.getInstance();
    private final double chance=0.1111;
    private static BloodMoonManager bloodMoonInstance;
    public final TILangManager lang =TILangManager.getInstance();
    private final NamespacedKey key;

    public BloodMoonManager(Plugin plugin){
        this.plugin=plugin;
        key = new NamespacedKey(plugin, "custom_enemy");
        Bukkit.getPluginManager().registerEvents(this, plugin);
        countTime();
    }

    public int getMoonPhase(World world) {
        long days = world.getFullTime() / 24000;
        return (int) (days % 8);
    }

    public void countTime(){
        checked=false;
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (World world : Bukkit.getWorlds()) {
                if (world.getEnvironment() != World.Environment.NORMAL) continue;

                long time = world.getTime();

                // Just turned night (13000 ticks) — roll for blood moon
                if ((time >= 13000 && time<18000 )&& !instance.getBloodMoon() && !checked &&getMoonPhase(world)!=4) {
                    checked=true;
                    if (Math.random() < chance&&instance.getBloodMoonEnabled()) {
                        startBloodMoon(world);
                    }
                }

                // Approaching dawn (23000 ticks) — end blood moon
                if ((time >= 23000 || time<13000) && instance.getBloodMoon()) {
                    endBloodMoon(world);
                }
            }
        }, 0L, 40L);
    }

    public void startBloodMoon(World world){
        for(Player player:world.getPlayers()){
            player.sendMessage(ChatColor.DARK_RED+lang.get("commands","blood_moon_msg.rising"));
        }
        instance.setBloodMoon(true);
        instance.save();
    }

    public void endBloodMoon(World world){
        checked=false;
        for(Player player:world.getPlayers()){
            player.sendMessage(ChatColor.DARK_RED+lang.get("commands","blood_moon_msg.falling"));
        }
        killBloodZombies(world);
        instance.setBloodMoon(false);
        instance.save();
    }

    @EventHandler
    public void onMonsterSpawn(CreatureSpawnEvent event){
        if(!instance.getBloodMoon()){return;}
        if (event.getEntity().getWorld().getEnvironment() != World.Environment.NORMAL){return;}
        EntityType type=event.getEntity().getType();
        if(type==EntityType.ZOMBIE){
            if(Math.random()<0.7){
                event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), type);
            }
            event.getEntity().getAttribute(Attribute.FOLLOW_RANGE).setBaseValue(96);
        }else if(type==EntityType.SKELETON||type==EntityType.DROWNED||type==EntityType.SPIDER){
            if(Math.random()<0.33){
                event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), type);
            }
            event.getEntity().getAttribute(Attribute.FOLLOW_RANGE).setBaseValue(96);
        }

    }

    public void killBloodZombies(World world){
        for(Zombie zombie: world.getEntitiesByClass(Zombie.class)){
            if (Objects.equals(zombie.getPersistentDataContainer().get(key, PersistentDataType.STRING), "BloodZombie")) {
                zombie.remove();
            }
        }
    }

    public static void initialize(JavaPlugin plugin) {
        bloodMoonInstance = new BloodMoonManager(plugin);
    }

    public static BloodMoonManager getInstance() {
        return bloodMoonInstance;
    }
}
