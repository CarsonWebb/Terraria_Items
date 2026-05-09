package me.carson.terrariaItems.projectilesFolder.hookProjectiles;

import me.carson.terrariaItems.projectilesFolder.HookProjectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;

public class RubyHookProjectile extends HookProjectile {

    public RubyHookProjectile(Plugin plugin) {
        super(plugin,  "ruby_hook_projectile", "RubyHookProjectile",20,1f,new Particle.DustOptions(Color.fromRGB(155, 21, 18), 0.5f));
    }

}
