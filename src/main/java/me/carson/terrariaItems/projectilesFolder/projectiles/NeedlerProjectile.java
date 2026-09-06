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

public class NeedlerProjectile extends Projectile {

    public NeedlerProjectile(Plugin plugin) {
        super(plugin, 0, "needler_projectile", "NeedlerProjectile", 0,0,DamageType.ARROW,null,0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON,30,2,false,true,true));
    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
