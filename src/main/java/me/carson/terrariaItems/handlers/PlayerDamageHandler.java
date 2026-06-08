package me.carson.terrariaItems.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;

import java.util.UUID;

public class PlayerDamageHandler implements Listener {

    private final Plugin plugin;
    private final PlayerDataHandler playerInstance=PlayerDataHandler.getInstance();

    public PlayerDamageHandler(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        double extraDamage=0;
        double originalDamage=event.getDamage();
        UUID id=player.getUniqueId();
        DamageType type=event.getDamageSource().getDamageType();
        extraDamage+=originalDamage *playerInstance.getBonusDamage(id);

        if(type==DamageType.PLAYER_ATTACK) {
            extraDamage+=originalDamage *playerInstance.getBonusMelee(id);
        }
        if(type==DamageType.LIGHTNING_BOLT){
            extraDamage+=originalDamage *playerInstance.getBonusMagic(id);
        }
        if(type==DamageType.TRIDENT){
            extraDamage+=originalDamage *playerInstance.getBonusRogue(id);
        }
        if(event.getDamager() instanceof Projectile projectile) {
            ProjectileSource source = projectile.getShooter();
            if (!(source ==player)) {return;}
            extraDamage+=originalDamage *playerInstance.getBonusRanged(id);
        }
        if(type==DamageType.ARROW){
            extraDamage+=originalDamage *playerInstance.getBonusRanged(id);
        }
        if(Math.random()<playerInstance.getCritChance(id)){
            event.setDamage((originalDamage+extraDamage)*2);
            if(event.getEntity() instanceof LivingEntity entity){
                spawnCritEffect(entity.getEyeLocation());
            }
            //player.sendMessage("Damage: "+((originalDamage+extraDamage)*2));
        }else{
            event.setDamage(originalDamage+extraDamage);
            //player.sendMessage("Damage: "+(originalDamage+extraDamage));
        }
    }

    public void spawnCritEffect(Location location){
        location.getWorld().spawnParticle(
                Particle.WAX_ON,   // particle type
                location,         // center location
                10,               // count
                0.3, 0.3, 0.3,   // offset X, Y, Z (spread radius)
                0.01              // extra (speed for most particles)
        );
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        event.setDamage(event.getDamage()-(event.getDamage()*playerInstance.getDamageReduction(player.getUniqueId())));
    }

}
