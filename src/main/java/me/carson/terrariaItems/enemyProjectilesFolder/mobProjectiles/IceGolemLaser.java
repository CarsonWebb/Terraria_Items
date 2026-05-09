package me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles;

import me.carson.terrariaItems.enemyProjectilesFolder.EnemyProjectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class IceGolemLaser extends EnemyProjectile {

    public IceGolemLaser(Plugin plugin) {
        super(plugin, 15,1f, "ice_golem_laser", "IceGolemLaser",0,0, DamageType.ARROW,null);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.setFreezeTicks(200);
    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
