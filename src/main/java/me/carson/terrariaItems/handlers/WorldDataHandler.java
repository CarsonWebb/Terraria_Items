package me.carson.terrariaItems.handlers;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class WorldDataHandler implements Listener {

    private static WorldDataHandler instance;
    private final File file;
    private final YamlConfiguration config;

    public WorldDataHandler(Plugin plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
        file = new File(plugin.getDataFolder(), "worldData.yml");
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public Boolean getHardmode(){return config.getBoolean(".hardmode",false);}
    public void setHardmode(Boolean set){
        config.set(".hardmode",set);
    }

    public Boolean getMechDragon(){return config.getBoolean(".mechDragon",false);}
    public void setMechDragon(Boolean set){
        config.set(".mechDragon",set);
    }

    public Boolean getMechWither(){return config.getBoolean(".mechWither",false);}
    public void setMechWither(Boolean set){
        config.set(".mechWither",set);
    }

    public Boolean getMechWarden(){return config.getBoolean(".mechWarden",false);}
    public void setMechWarden(Boolean set){
        config.set(".mechWarden",set);
    }

    public Boolean getBloodMoon(){return config.getBoolean(".bloodMoon",false);}
    public void setBloodMoon(Boolean set){
        config.set(".bloodMoon",set);
    }

    public Boolean getBloodMoonEnabled(){return config.getBoolean(".bloodMoonEnabled",true);}
    public void setBloodMoonEnabled(Boolean set){
        config.set(".bloodMoonEnabled",set);
    }

    public Boolean getPreHardmodeEnabled(){return config.getBoolean(".preHardmodeEnabled",true);}
    public void setPreHardmodeEnabled(Boolean set){
        config.set(".preHardmodeEnabled",set);
    }

    public Boolean getHardmodeEnabled(){return config.getBoolean(".hardmodeEnabled",true);}
    public void setHardmodeEnabled(Boolean set){
        config.set(".hardmodeEnabled",set);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new WorldDataHandler(plugin);
    }

    public static WorldDataHandler getInstance() {
        return instance;
    }

    @EventHandler
    public void onDragonKill(EntityDeathEvent event){
        if(!(event.getEntity() instanceof EnderDragon)){return;}
        if(getHardmode()){return;}
        setHardmode(true);
        String light=(net.md_5.bungee.api.ChatColor.of("#FF96FF")+"light"+ChatColor.WHITE);
        String dark=(net.md_5.bungee.api.ChatColor.of("#B428FF")+"dark"+ChatColor.WHITE);
        String bar="The ancient spirits of "+light+" and "+dark+" have been released";
        for(Player player: Bukkit.getOnlinePlayers()){
            player.spigot().sendMessage(TextComponent.fromLegacy(bar));
        }
        save();
    }
}
