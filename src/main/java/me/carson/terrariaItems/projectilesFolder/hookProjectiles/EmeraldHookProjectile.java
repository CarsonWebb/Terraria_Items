package me.carson.terrariaItems.projectilesFolder.hookProjectiles;

import me.carson.terrariaItems.projectilesFolder.HookProjectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;

public class EmeraldHookProjectile extends HookProjectile {

    public EmeraldHookProjectile(Plugin plugin) {
        super(plugin,  "emerald_hook_projectile", "EmeraldHookProjectile",18,0.9f,new Particle.DustOptions(Color.fromRGB(10, 143, 93), 0.5f));
    }

}
