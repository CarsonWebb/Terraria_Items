package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RubyBolt extends Projectile {

    public RubyBolt(Plugin plugin) {
        super(plugin, 0,  "ruby_bolt", "RubyBolt",0,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(136, 30, 27), 1f),0);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

    public void shootRubyBolts(Player player,float  speed,float damage,float spread,float duration){
        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                count++;
                createProjectile(player, speed, damage, spread, duration);
                if (count >= 2) {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 1L, 6L);
    }
}
