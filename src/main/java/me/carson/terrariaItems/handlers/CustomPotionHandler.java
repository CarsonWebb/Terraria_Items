package me.carson.terrariaItems.handlers;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomPotionHandler {

    private final Plugin plugin;
    private static CustomPotionHandler instance;

    public CustomPotionHandler(Plugin plugin){
        this.plugin=plugin;
    }

    public void potionAddArmor(Player player,int amount, int duration,String id){
        player.getAttribute(Attribute.ARMOR).addModifier(new AttributeModifier(new NamespacedKey(plugin,id),amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.getAttribute(Attribute.ARMOR).removeModifier(new AttributeModifier(new NamespacedKey(plugin,id),amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        }, duration);
    }

    public void potionAddBuildRange(Player player,int amount, int duration,String id){
        player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE).addModifier(new AttributeModifier(new NamespacedKey(plugin,id),amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE).removeModifier(new AttributeModifier(new NamespacedKey(plugin,id),amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        }, duration);
    }


    public static void initialize(JavaPlugin plugin) {
        instance = new CustomPotionHandler(plugin);
    }

    public static CustomPotionHandler getInstance() {
        return instance;
    }

}
