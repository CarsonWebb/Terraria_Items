package me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles;

import me.carson.terrariaItems.enemyProjectilesFolder.EnemyProjectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DustDevil extends EnemyProjectile {

    public DustDevil(Plugin plugin) {
        super(plugin, 15,1f, "dust_devil", "DustDevil",0,0, DamageType.ARROW,new Particle.DustOptions(Color.fromRGB(210, 148, 87), 1f));
    }


    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.setVelocity(entity.getVelocity().setY(1));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,100,1,false,true,true));
    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
