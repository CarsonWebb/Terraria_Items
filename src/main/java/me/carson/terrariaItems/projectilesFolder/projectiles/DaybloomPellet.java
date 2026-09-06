package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DaybloomPellet extends Projectile {

    public DaybloomPellet(Plugin plugin) {
        super(plugin, 0,  "daybloom_pellet", "DaybloomPellet",0,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(236, 197, 18), 1f),0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

    public void shootPellets(Player player,float  speed,float damage,float spread,float duration){
        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                count++;
                createProjectile(player, speed, damage, spread, duration);
                if (count >= 4) {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 1L, 2L);
    }

}
