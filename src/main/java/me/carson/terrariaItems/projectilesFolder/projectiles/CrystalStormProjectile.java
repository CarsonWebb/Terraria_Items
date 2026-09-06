package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CrystalStormProjectile extends Projectile {

    public CrystalStormProjectile(Plugin plugin) {
        super(plugin, 0,  "crystal_storm_projectile", "CrystalStormProjectile",0,99, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(124, 89, 159), 0.5f),0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
