package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class StingerNecklace extends Accessory  {

    public StingerNecklace(Plugin plugin){
        super(plugin,"stinger_necklace.name","#FF96FF",Material.NETHER_BRICK,"stinger_necklace","StingerNecklace","stinger_necklace.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.ATTACK_DAMAGE).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"stinger_necklace_attack_damage"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_DAMAGE).addModifier(new AttributeModifier(new NamespacedKey(plugin,"stinger_necklace_attack_damage"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.ATTACK_DAMAGE).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"stinger_necklace_attack_damage"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,100,0,false,false,false));
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new StingerNecklace(plugin).createItem();
    }

}
