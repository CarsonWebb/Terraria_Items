package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class WandOfSparkingProjectile extends Projectile {

    public WandOfSparkingProjectile(Plugin plugin) {
        super(plugin, 0,  "wand_of_sparking_projectile", "WandOfSparkingProjectile",1,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(232, 100, 0), 0.5f));
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        if(Math.random()<0.5){
            entity.setFireTicks(50);
        }
    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
