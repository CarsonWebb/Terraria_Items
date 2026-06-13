package me.carson.terrariaItems.bossFolder.bosses;

import me.carson.terrariaItems.bossFolder.Boss;
import me.carson.terrariaItems.enemyProjectilesFolder.bossProjectiles.*;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class MechanicalDragon extends Boss {

    private final NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");

    public MechanicalDragon(Plugin plugin){
        super(plugin,500, EntityType.ENDER_DRAGON,"Mechanical Dragon",500);
    }

    @Override
    public void summonBoss(Player player) {
        World world=player.getWorld();
        Location spawnPoint=getSpawnPoint(player);
        EnderDragon boss= (EnderDragon) world.spawnEntity(spawnPoint, type);
        boss.getAttribute(Attribute.MAX_HEALTH).setBaseValue(health);
        boss.setHealth(health);
        boss.setCustomName(lang.get("enemies","mechanical_dragon.name"));
        boss.setCustomNameVisible(true);
        boss.getPersistentDataContainer().set(key, PersistentDataType.STRING,"BossDragon");
        BossBar bar = Bukkit.createBossBar(name, BarColor.PURPLE, BarStyle.SOLID);
        attachBossBar(boss,bar);
        Bukkit.getScheduler().runTask(plugin, () -> {
            boss.setAI(true);
            boss.setPhase(EnderDragon.Phase.CIRCLING);
        });
        startAttacks(boss);
    }

    private void startAttacks(LivingEntity shooter) {
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
                fireAttack(shooter,target);
            }else {
                shooter.remove();
            }
        }, 40L, 40L);
    }

    private void fireAttack(LivingEntity shooter, Player target){
        if(shooter.getHealth()<(shooter.getAttribute(Attribute.MAX_HEALTH).getValue()/3)) {
            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    new DragonLaser(plugin).createBossProjectile(shooter,target,2.5f,5,0.1f,100);
                    shooter.getWorld().playSound(shooter.getLocation(), "terraria:laser", 4.0F, 1.0F);

                    count++;
                    if (count >= 4) {
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 0L, 5L);
        }
        else {
            new DragonLaser(plugin).createBossProjectile(shooter,target,3,5,0.01f,100);
            shooter.getWorld().playSound(shooter.getLocation(), "terraria:laser", 4.0F, 1.0F);
        }
    }

    private Location getSpawnPoint(Player player){
        World world = player.getWorld();
        return new Location(world,0,75,0);
    }

}
