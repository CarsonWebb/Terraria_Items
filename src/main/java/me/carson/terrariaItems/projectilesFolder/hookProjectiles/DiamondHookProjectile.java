package me.carson.terrariaItems.projectilesFolder.hookProjectiles;

import me.carson.terrariaItems.projectilesFolder.HookProjectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;

public class DiamondHookProjectile extends HookProjectile {

    public DiamondHookProjectile(Plugin plugin) {
        super(plugin,  "diamond_hook_projectile", "DiamondHookProjectile",20,1f,new Particle.DustOptions(Color.fromRGB(155, 200, 202), 0.5f));
    }

}
