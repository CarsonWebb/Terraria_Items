package me.carson.terrariaItems.handlers;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class StealthManager implements Listener {

    private static StealthManager instance;
    private final PlayerDataHandler playerDataHandler=PlayerDataHandler.getInstance();
    private final File file;
    private final YamlConfiguration config;

    private final Map<UUID, Double> currentStealth = new HashMap<>();
    private final Map<UUID, Double> stealthDelay = new HashMap<>();
    private final Map<UUID, Location> lastLocation = new HashMap<>();
    private final Set<UUID> movingPlayers = new HashSet<>();

    public StealthManager(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        file = new File(plugin.getDataFolder(), "playerData.yml");
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }

        config = YamlConfiguration.loadConfiguration(file);

        for (String key : config.getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            double current = config.getDouble(key + ".current_stealth", getStealth(uuid));
            currentStealth.put(uuid, current);
        }
    }

    public Double getStealth(UUID uuid) {
        return currentStealth.getOrDefault(uuid, 0.0);
    }

    public void setStealth(UUID uuid, double amount) {
        double max = playerDataHandler.getMaxStealth(uuid);
        currentStealth.put(uuid, Math.min(amount, max));
    }

    public void addStealth(UUID uuid, double amount) {
        setStealth(uuid, getStealth(uuid) + amount);
    }

    public void removeStealth(Player player) {
        setStealth(player.getUniqueId(), 0);
    }

    public void updateStealthBar(Player player) {
        UUID id = player.getUniqueId();
        double stealth=instance.getStealth(id);
        String formattedValue = String.format("%.1f", stealth);
        String bar = ChatColor.DARK_PURPLE + "Stealth: " + formattedValue + ChatColor.GRAY + " / " + playerDataHandler.getMaxStealth(id);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(bar));
    }

    public void startStealthRegen(Plugin plugin){
        StealthManager instance = StealthManager.getInstance();
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                UUID id = player.getUniqueId();
                double max =playerDataHandler.getMaxStealth(id);
                if(max>0){
                    if(instance.getStealthDelay(id)<=0&&(playerDataHandler.getMaxStealth(id)>instance.getStealth(id))){
                        instance.addStealth(id,instance.getStealthRegen(player));
                        instance.updateStealthBar(player);
                        if(isMaxStealth(player)){
                            player.getWorld().playSound(player.getLocation(), "terraria:stealth_full_charge", 1.0F, 1.0F);
                        }
                    }else if(instance.getStealthDelay(id)>-1){
                        instance.reduceStealthDelay(player,1.0);
                    }
                }
            }
        }, 0L, 1L);
    }

    public boolean isMaxStealth(Player player){
        double max =playerDataHandler.getMaxStealth(player.getUniqueId());
        return instance.getStealth(player.getUniqueId())==max&&max>0;
    }

    public double getStealthRegen(Player player){
        if(isMoving(player)){
            return playerDataHandler.getMaxStealth(player.getUniqueId())/80;
        }
        return playerDataHandler.getMaxStealth(player.getUniqueId())/40;
    }

    public void startStealthRegenDelay(Player player){
        stealthDelay.put(player.getUniqueId(),1.0);
    }

    public Double getStealthDelay(UUID id){
        return stealthDelay.getOrDefault(id, 0.0);
    }

    public void reduceStealthDelay(Player player,Double amount){
        UUID id = player.getUniqueId();
        stealthDelay.put(id,getStealthDelay(id)-amount);
    }

    public boolean isMoving(Player player) {
        return movingPlayers.contains(player.getUniqueId());
    }

    public void startMovementChecks(Plugin plugin){
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                UUID id = player.getUniqueId();
                Location current = player.getLocation();
                Location last = lastLocation.get(id);

                if (last != null) {
                    boolean moving = current.getX() != last.getX() ||
                            current.getY() != last.getY() ||
                            current.getZ() != last.getZ();
                    if (moving) {
                        movingPlayers.add(id);
                    } else {
                        movingPlayers.remove(id);
                    }
                }
                lastLocation.put(id, current.clone());
            }
        }, 0L, 2L);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID id = event.getPlayer().getUniqueId();
        movingPlayers.remove(id);
        lastLocation.remove(id);
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new StealthManager(plugin);
    }

    public static StealthManager getInstance() {
        return instance;
    }

}
