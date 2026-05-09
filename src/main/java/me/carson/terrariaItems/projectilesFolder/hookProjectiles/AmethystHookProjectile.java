package me.carson.terrariaItems.projectilesFolder.hookProjectiles;

import me.carson.terrariaItems.projectilesFolder.HookProjectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;

public class AmethystHookProjectile extends HookProjectile {

    public AmethystHookProjectile(Plugin plugin) {
        super(plugin,  "amethyst_hook_projectile", "AmethystHookProjectile",14,0.8f,new Particle.DustOptions(Color.fromRGB(165, 0, 236), 0.5f));
    }

}
