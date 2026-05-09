package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class IceSickleProjectile extends Projectile {

    public IceSickleProjectile(Plugin plugin) {
        super(plugin, 0,  "ice_sickle_projectile", "IceSickleProjectile",3,0, DamageType.PLAYER_ATTACK,null);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
