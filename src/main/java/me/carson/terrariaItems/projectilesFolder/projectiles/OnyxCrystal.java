package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class OnyxCrystal extends Projectile {

    public OnyxCrystal(Plugin plugin) {
        super(plugin, 0, "onyx_crystal", "OnyxCrystal",0,0, DamageType.ARROW,null,0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        entity.getWorld().createExplosion(entity.getLocation(), 1.0F, false, false);
    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().createExplosion(block.getLocation().add(0.5,1.1f,0.5), 1.0F, false, false);
    }
}
