package me.carson.terrariaItems.enemyProjectilesFolder.bossProjectiles;

import me.carson.terrariaItems.enemyProjectilesFolder.EnemyProjectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class WardenLaser extends EnemyProjectile {

    public WardenLaser(Plugin plugin) {
        super(plugin, 0,0,  "warden_laser", "WardenLaser",0,0, DamageType.GENERIC,null);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.setFreezeTicks(entity.getFreezeTicks()+100);
    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
