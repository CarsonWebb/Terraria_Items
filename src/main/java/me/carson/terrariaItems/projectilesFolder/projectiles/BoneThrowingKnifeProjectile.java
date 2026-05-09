package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BoneThrowingKnifeProjectile extends Projectile {

    public BoneThrowingKnifeProjectile(Plugin plugin) {
        super(plugin, 0,  "bone_throwing_knife_projectile", "BoneThrowingKnifeProjectile",6,0, DamageType.ARROW,null);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
