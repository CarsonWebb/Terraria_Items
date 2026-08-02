package me.carson.terrariaItems.bossFolder.bosses;

import me.carson.terrariaItems.bossFolder.Boss;
import me.carson.terrariaItems.enemyProjectilesFolder.bossProjectiles.WitherBomb;
import me.carson.terrariaItems.enemyProjectilesFolder.bossProjectiles.WitherLaser;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MechanicalWither extends Boss implements Listener {

    private final NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");

    public MechanicalWither(Plugin plugin){
        super(plugin,700, EntityType.WITHER,"Mechanical Wither",150);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void summonBoss(Player player) {
        World world=player.getWorld();
        Location spawnPoint=getSpawnPoint(player);
        Wither boss= (Wither) world.spawnEntity(spawnPoint, type);
        boss.getAttribute(Attribute.MAX_HEALTH).setBaseValue(health);
        boss.setHealth(health);
        boss.setCustomName(lang.get("enemies","mechanical_wither.name"));
        boss.setCustomNameVisible(true);
        boss.getPersistentDataContainer().set(key, PersistentDataType.STRING,"BossWither");
        startAttacks(boss);
    }

    private void startAttacks(Wither shooter) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (shooter.isDead()){return;}
            List<Player> playerList = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getWorld() != shooter.getWorld()) continue;
                if (p.getLocation().distanceSquared(shooter.getLocation()) <= range * range) {
                    playerList.add(p);
                }
            }
            if (!playerList.isEmpty()){
                Player target=getRandomPlayer(playerList);
                shooter.setTarget(target);
                fireAttack(shooter,target);
            }else {
                shooter.remove();
            }
        }, 40L, 40L);
    }

    private void fireAttack(Wither shooter, Player target){
        double distance = shooter.getLocation().distance(target.getLocation());
        if(shooter.getHealth()<(shooter.getAttribute(Attribute.MAX_HEALTH).getValue()/3)) {
            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    new WitherLaser(plugin).createBossProjectile(shooter,target,1.5f,0,0.02f,100);
                    shooter.getWorld().playSound(shooter.getLocation(), "terraria:laser", 3.0F, 1.0F);

                    count++;
                    if (count >= 2) {
                        new WitherBomb(plugin).createBossProjectile(shooter,target,0.2f,0,0,200);
                        shooter.getWorld().playSound(shooter.getLocation(), "terraria:impact_1", 4.0F, 1.0F);
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 0L, 10L);
        }else if(distance>10){
            new WitherLaser(plugin).createBossProjectile(shooter,target,1.5f,0,0.02f,100);
            shooter.getWorld().playSound(shooter.getLocation(), "terraria:laser", 3.0F, 1.0F);
        }else {
            new WitherBomb(plugin).createBossProjectile(shooter,target,0.2f,0,0,200);
            shooter.getWorld().playSound(shooter.getLocation(), "terraria:impact_1", 4.0F, 1.0F);
        }
    }

    private Location getSpawnPoint(Player player){
        Location center =player.getLocation();
        Vector dir = player.getEyeLocation().getDirection().normalize();
        dir.setY(0);
        Location result=center.add(dir.multiply(20));
        result.add(0,5,0);
        if(isValidSpawn(result)){
            return result;
        }
        return null;
    }

    private Boolean isValidSpawn(Location loc) {
        Block block = loc.getBlock();
        if (block.getType() != Material.AIR) {
            return false;
        }
        for (int x = -1; x <= 1; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    if (block.getRelative(x, y, z).getType() != Material.AIR) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @EventHandler
    public void onWitherShoot(ProjectileLaunchEvent e) {
        if (e.getEntity() instanceof WitherSkull skull) {
            LivingEntity shooter= (LivingEntity) skull.getShooter();
            if(shooter==null){return;}
            if(Objects.equals(shooter.getPersistentDataContainer().get(key, PersistentDataType.STRING), "BossWither")){
                e.setCancelled(true);
            }
        }
    }
}
