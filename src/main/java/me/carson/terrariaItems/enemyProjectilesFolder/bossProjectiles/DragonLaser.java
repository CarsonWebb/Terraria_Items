package me.carson.terrariaItems.enemyProjectilesFolder.bossProjectiles;

import me.carson.terrariaItems.enemyProjectilesFolder.EnemyProjectile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DragonLaser extends EnemyProjectile {

    public DragonLaser(Plugin plugin) {
        super(plugin, 0, 0, "dragon_laser", "DragonLaser",99,0, DamageType.GENERIC,null);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {

    }

    @Override
    public void hitBlockEffect(Block block) {
        spawnDragonFireEffect(block.getLocation().add(0.5,1f,0.5));
    }

    public void spawnDragonFireEffect(Location loc) {

        AreaEffectCloud cloud = (AreaEffectCloud) loc.getWorld().spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);

        Bukkit.getScheduler().runTask(plugin, () -> {
            cloud.setRadius(2.0f);
            cloud.setDuration(100);
            cloud.setWaitTime(0);
            cloud.setRadiusPerTick(0.01f);
            cloud.setParticle(Particle.WITCH);
            cloud.setReapplicationDelay(5);

            cloud.addCustomEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 1, 1), true);
            cloud.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 2), true);
            cloud.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1), true);
        });
    }

}
