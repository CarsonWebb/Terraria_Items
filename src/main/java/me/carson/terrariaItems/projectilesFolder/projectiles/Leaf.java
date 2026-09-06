package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Leaf extends Projectile {

    public Leaf(Plugin plugin) {
        super(plugin, 0,  "leaf_blade_projectile", "Leaf",0,0, DamageType.PLAYER_ATTACK,new Particle.DustOptions(Color.fromRGB(46, 84, 29), 0.5f),0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        if(Math.random()<0.25){
            entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON,140,2,false,true,true));
        }
    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
