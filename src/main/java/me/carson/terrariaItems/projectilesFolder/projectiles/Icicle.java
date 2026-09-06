package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Icicle extends Projectile {

    public Icicle(Plugin plugin) {
        super(plugin, 0,  "icicle", "Icicle",0,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(84, 186, 255), 1.5f),0);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        entity.getWorld().playSound(entity.getLocation(), "terraria:frost_bolt_impact", 1.5F, 1.0F);
    }

    @Override
    public void hitBlockEffect(Block block) {
       block.getWorld().playSound(block.getLocation(), "terraria:frost_bolt_impact", 1.5F, 1.0F);
    }

}
