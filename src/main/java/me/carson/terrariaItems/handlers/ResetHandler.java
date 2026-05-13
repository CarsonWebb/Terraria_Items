package me.carson.terrariaItems.handlers;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import me.carson.terrariaItems.accesoryFolder.AccessoryManager;
import me.carson.terrariaItems.armorFolder.ArmorManager;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.UUID;

public class ResetHandler implements Listener {

    private final Plugin plugin;
    private static ResetHandler instance;
    private final PlayerDataHandler playerDataHandler=PlayerDataHandler.getInstance();
    private final AccessoryManager accessoryManager=AccessoryManager.getInstance();
    private final ArmorManager armorInstance=ArmorManager.getInstance();

    public ResetHandler(Plugin plugin){
        this.plugin=plugin;
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }

    public void resetBonuses(Player player){
        UUID id =player.getUniqueId();
        playerDataHandler.setBonusMelee(id,0.0);
        playerDataHandler.setBonusRanged(id,0.0);
        playerDataHandler.setBonusMagic(id,0.0);
        playerDataHandler.setBonusDamage(id,0.0);
        playerDataHandler.setDamageReduction(id,0.0);
        playerDataHandler.setExtraMana(id,0);
        playerDataHandler.setCritChance(id,0);
        playerDataHandler.setManaReduction(id,0);
        playerDataHandler.setMaxStealth(id,0);
        player.setWalkSpeed(0.2f);
        Objects.requireNonNull(player.getAttribute(Attribute.ATTACK_SPEED)).setBaseValue(4);

        if(playerDataHandler.getInventory(player.getUniqueId())!=null){
            for(ItemStack item:playerDataHandler.getInventory(player.getUniqueId())){
                Accessory accessory=accessoryManager.getAccessory(item);
                if(accessory!=null){
                    accessory.activateEffect(player);
                }
            }
        }
        for (ItemStack armor : player.getInventory().getArmorContents()) {
            if(armorInstance.getArmorPiece(armor)!=null){armorInstance.getArmorPiece(armor).activateArmorEffect(player);}
        }
        playerDataHandler.save();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            playerDataHandler.save();
            resetBonuses(event.getPlayer());
        }, 5L);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            resetBonuses(event.getPlayer());
        }, 5L);

    }

    public static void initialize(JavaPlugin plugin) {instance = new ResetHandler(plugin);}

    public static ResetHandler getInstance() {return instance;}

}
