package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PulseBolt extends Projectile {

    public PulseBolt(Plugin plugin) {
        super(plugin, 0,  "pulse_bolt", "PulseBolt",5,5, DamageType.ARROW,new Particle.DustOptions(Color.fromRGB(0, 255, 255), 1f));
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
    }

    @Override
    public void hitBlockEffect(Block block) {
    }

}
