package me.carson.terrariaItems.projectilesFolder.projectiles.bullets;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BubonicRoundProjectile extends Projectile {

    public BubonicRoundProjectile(Plugin plugin) {
        super(plugin, 5, "bubonic_round_projectile", "BubonicRound",0,0, DamageType.ARROW,null,0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,60,2,false,true,true));
    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().playSound(block.getLocation(), "terraria:impact_1", 0.5F, 1.0F);
    }


}
