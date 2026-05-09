package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class RubyBolt extends Projectile {

    public RubyBolt(Plugin plugin) {
        super(plugin, 0,  "ruby_bolt", "RubyBolt",1,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(136, 30, 27), 1f));
    }


    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
