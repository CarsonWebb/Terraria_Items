package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class WaterBoltProjectile extends Projectile {

    public WaterBoltProjectile(Plugin plugin) {
        super(plugin, 0, "water_bolt_projectile", "WaterBoltProjectile",9,5, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(25, 33, 183), 1f));
    }


    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().playSound(block.getLocation(), "terraria:impact_1", 2.0F, 1.0F);
    }

}
