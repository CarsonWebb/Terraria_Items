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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AmberHorseshoeBalloon extends Accessory implements Listener {

    public AmberHorseshoeBalloon(Plugin plugin){
        super(plugin,"amber_horseshoe_balloon.name","#FF9696", Material.NETHER_BRICK,"amber_horseshoe_balloon","AmberHorseshoeBalloon","amber_horseshoe_balloon.lore");
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
        if(((event.getCause() == EntityDamageEvent.DamageCause.FALL)||(event.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL))){
            event.setCancelled(true);
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,100,1,false,false,false));
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new AmberHorseshoeBalloon(plugin).createItem();
    }

}