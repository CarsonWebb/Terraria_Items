package me.carson.terrariaItems.projectilesFolder.hookProjectiles;

import me.carson.terrariaItems.projectilesFolder.HookProjectile;
import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GrapplingHookProjectile extends HookProjectile {

    public GrapplingHookProjectile(Plugin plugin) {
        super(plugin,  "grappling_hook_projectile", "GrapplingHookProjectile",12,0.75f,new Particle.DustOptions(Color.fromRGB(50, 50, 50), 0.5f));
    }

}
