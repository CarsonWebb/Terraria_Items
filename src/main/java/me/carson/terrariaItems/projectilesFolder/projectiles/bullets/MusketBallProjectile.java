package me.carson.terrariaItems.projectilesFolder.projectiles.bullets;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MusketBallProjectile extends Projectile {

    public MusketBallProjectile(Plugin plugin) {
        super(plugin, 2, "bullet_projectile", "MusketBall",0,0, DamageType.ARROW,null,0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().playSound(block.getLocation(), "terraria:impact_1", 0.5F, 1.0F);
    }
}
