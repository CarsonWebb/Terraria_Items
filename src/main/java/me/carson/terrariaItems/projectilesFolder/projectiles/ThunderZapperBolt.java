package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ThunderZapperBolt extends Projectile {

    public ThunderZapperBolt(Plugin plugin) {
        super(plugin, 0,  "thunder_zapper_bolt", "ThunderZapperBolt",0,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(75, 166, 211), 1f),0);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
