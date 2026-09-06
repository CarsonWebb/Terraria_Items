package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class AmethystBolt extends Projectile {

    public AmethystBolt(Plugin plugin) {
        super(plugin, 0,  "amethyst_bolt", "AmethystBolt",0,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(125, 27, 116), 1f),5);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
