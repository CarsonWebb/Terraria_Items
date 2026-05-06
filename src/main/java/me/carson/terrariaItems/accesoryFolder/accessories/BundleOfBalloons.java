package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class BundleOfBalloons extends Accessory implements Listener {

    public BundleOfBalloons(Plugin plugin){
        super(plugin,"bundle_of_balloons.name","#FFFF0A", Material.NETHER_BRICK,"bundle_of_balloons","BundleOfBalloons","bundle_of_balloons.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.GRAVITY).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"balloon_gravity"),-0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.GRAVITY).addModifier(new AttributeModifier(new NamespacedKey(plugin,"balloon_gravity"),-0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.SAFE_FALL_DISTANCE).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"balloon_fall_distance"),2.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.SAFE_FALL_DISTANCE).addModifier(new AttributeModifier(new NamespacedKey(plugin,"balloon_fall_distance"),2.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.GRAVITY).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"balloon_gravity"),-0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.SAFE_FALL_DISTANCE).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"balloon_fall_distance"),2.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new BundleOfBalloons(plugin).createItem();
    }

}