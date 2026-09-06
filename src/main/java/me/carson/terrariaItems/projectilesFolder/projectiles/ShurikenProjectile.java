package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ShurikenProjectile extends Projectile {

    public ShurikenProjectile(Plugin plugin) {
        super(plugin, 0,  "shuriken", "ShurikenProjectile",3,0, DamageType.ARROW,null,0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
