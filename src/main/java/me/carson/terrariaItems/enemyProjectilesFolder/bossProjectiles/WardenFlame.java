package me.carson.terrariaItems.enemyProjectilesFolder.bossProjectiles;

import me.carson.terrariaItems.enemyProjectilesFolder.EnemyProjectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class WardenFlame extends EnemyProjectile {

    public WardenFlame(Plugin plugin) {
        super(plugin, 15,0,  "warden_flame", "WardenFlame",0,99, DamageType.GENERIC,new Particle.DustOptions(Color.fromRGB(0, 146, 149), 1.5f));
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.setFreezeTicks(400);
    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
