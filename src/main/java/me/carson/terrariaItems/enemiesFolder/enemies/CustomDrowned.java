package me.carson.terrariaItems.enemiesFolder.enemies;

import me.carson.terrariaItems.enemiesFolder.CustomEnemy;
import me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles.MermanBolt;
import me.carson.terrariaItems.miscFolder.hats.IcyMermanHat;
import me.carson.terrariaItems.miscFolder.hats.ZombieMermanHat;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class CustomDrowned extends CustomEnemy implements Listener {

    private static final Set<Biome> icyBiomes = Set.of(Biome.COLD_OCEAN,Biome.DEEP_COLD_OCEAN,Biome.FROZEN_OCEAN,Biome.DEEP_FROZEN_OCEAN);

    public CustomDrowned(Plugin plugin){
        super(plugin);
    }

    @EventHandler
    public void onDrownedSpawn(CreatureSpawnEvent event){
        if (event.getEntityType() != EntityType.DROWNED) return;
        Drowned drowned = (Drowned) event.getEntity();
        Location loc=drowned.getLocation();

        if(instance.getHardmode()&&instance.getHardmodeEnabled()){
            if(icyBiomes.contains(loc.getBlock().getBiome())){
                spawnIcyMerman(drowned);
                return;
            }
            if(instance.getBloodMoon()){
                spawnZombieMerman(drowned);
                return;
            }
        }else if(instance.getPreHardmodeEnabled()){
            if(instance.getBloodMoon()){
                spawnZombieMerman(drowned);
                return;
            }
        }

    }

    public void spawnIcyMerman(Drowned drowned){
        drowned.setCustomName(lang.get("enemies","icy_merman.name"));
        drowned.setCustomNameVisible(false);
        drowned.getAttribute(Attribute.MAX_HEALTH).setBaseValue(50);
        drowned.setHealth(50);
        drowned.setInvisible(true);
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        drowned.getPersistentDataContainer().set(key, PersistentDataType.STRING,"IcyMerman");
        drowned.setCanPickupItems(false);
        EntityEquipment equipment=drowned.getEquipment();
        equipment.setHelmet(IcyMermanHat.getItem(plugin));
        equipment.setChestplate(null);
        equipment.setLeggings(null);
        equipment.setBoots(null);
        equipment.setItemInMainHand(null);
        equipment.setHelmetDropChance(0f);
        startAttacks(drowned,new MermanBolt(plugin),"terraria:frost_bolt");
    }

    public void spawnZombieMerman(Drowned drowned){
        drowned.setCustomName(lang.get("enemies","zombie_merman.name"));
        drowned.setCustomNameVisible(false);
        drowned.getAttribute(Attribute.MAX_HEALTH).setBaseValue(30);
        drowned.setHealth(30);
        drowned.setInvisible(true);
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        drowned.getPersistentDataContainer().set(key, PersistentDataType.STRING,"ZombieMerman");
        drowned.setCanPickupItems(false);
        EntityEquipment equipment=drowned.getEquipment();
        equipment.setHelmet(ZombieMermanHat.getItem(plugin));
        equipment.setChestplate(null);
        equipment.setLeggings(null);
        equipment.setBoots(null);
        equipment.setItemInMainHand(null);
        equipment.setHelmetDropChance(0f);
    }
}
