package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class IceBolt extends Projectile {

    public IceBolt(Plugin plugin) {
        super(plugin, 0,  "ice_bolt", "IceBolt",0, 0,DamageType.PLAYER_ATTACK,new Particle.DustOptions(Color.fromRGB(17, 143, 180), 0.75f),0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        entity.getWorld().playSound(entity.getLocation(), "terraria:frost_bolt_impact", 1.0F, 1.0F);
    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
