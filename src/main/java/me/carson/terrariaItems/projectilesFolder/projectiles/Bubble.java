package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Bubble extends Projectile {

    public Bubble(Plugin plugin) {
        super(plugin, 0,  "bubble", "Bubble",0,0, DamageType.LIGHTNING_BOLT,null);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        entity.getWorld().playSound(entity.getLocation(), "terraria:bubble_pop", 2.0F, 1.0F);
    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().playSound(block.getLocation(), "terraria:bubble_pop", 2.0F, 1.0F);
    }

}
