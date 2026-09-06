package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BloodRainArrow extends Projectile {

    public BloodRainArrow(Plugin plugin) {
        super(plugin, 0,  "blood_rain_arrow", "BloodRainArrow",0,0, DamageType.ARROW,new Particle.DustOptions(Color.fromRGB(101, 29, 29), 0.75f),0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
