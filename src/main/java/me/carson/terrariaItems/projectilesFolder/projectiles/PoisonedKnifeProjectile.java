package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonedKnifeProjectile extends Projectile {

    public PoisonedKnifeProjectile(Plugin plugin) {
        super(plugin, 0,  "poisoned_knife_projectile", "PoisonedKnifeProjectile",1,0, DamageType.ARROW,null);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        if(Math.random()<0.5){
            entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON,200,0,true,true,true));
        }
    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
