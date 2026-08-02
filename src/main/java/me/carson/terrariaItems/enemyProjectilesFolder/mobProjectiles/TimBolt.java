package me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles;

import me.carson.terrariaItems.enemyProjectilesFolder.EnemyProjectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class TimBolt extends EnemyProjectile {

    public TimBolt(Plugin plugin) {
        super(plugin, 8,1f, "water_bolt_projectile", "WaterBoltProjectile",0,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(25, 33, 183), 1f));
    }


    @Override
    public void hitEntityEffect(LivingEntity entity) {

    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().playSound(block.getLocation(), "terraria:impact_1", 2.0F, 1.0F);
    }

}
