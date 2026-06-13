package me.carson.terrariaItems.bossFolder.bosses;

import me.carson.terrariaItems.bossFolder.Boss;
import me.carson.terrariaItems.enemyProjectilesFolder.bossProjectiles.WardenFlame;
import me.carson.terrariaItems.enemyProjectilesFolder.bossProjectiles.WardenLaser;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class MechanicalWarden extends Boss {

    private final NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");

    public MechanicalWarden(Plugin plugin){
        super(plugin,1000, EntityType.WARDEN,"Mechanical Warden",150);
    }

    @Override
    public void summonBoss(Player player) {
        World world=player.getWorld();
        Location spawnPoint=getSpawnPoint(player);
        if(spawnPoint==null){
            player.sendMessage(ChatColor.RED + "Invalid spawn, doesn't have adequate room");
            return;
        }
        Warden boss= (Warden) world.spawnEntity(spawnPoint, type);
        boss.getAttribute(Attribute.MAX_HEALTH).setBaseValue(health);
        boss.setHealth(health);
        boss.setCustomName(lang.get("enemies","mechanical_warden.name"));
        boss.setCustomNameVisible(true);
        boss.setAnger(player,150);
        boss.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, -1, 0, false, false,false));
        boss.getPersistentDataContainer().set(key, PersistentDataType.STRING,"BossWarden");
        BossBar bar = Bukkit.createBossBar(name, BarColor.BLUE, BarStyle.SOLID);
        attachBossBar(boss,bar);
        startAttacks(boss);
    }

    private void startAttacks(Warden shooter) {
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
                shooter.setAnger(target,150);
            }else {
                shooter.remove();
            }
        }, 40L, 100L);
    }

    private void fireAttack(LivingEntity shooter, Player target){
        double distance = shooter.getLocation().distance(target.getLocation());
        if(shooter.getHealth()<(shooter.getAttribute(Attribute.MAX_HEALTH).getValue()/3)) {
            shooter.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,100,3,true));
        }
        if(distance>10){
            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    new WardenLaser(plugin).createBossProjectile(shooter,target,2,15,0.02f,100);
                    shooter.getWorld().playSound(shooter.getLocation(), "terraria:laser", 3.0F, 1.0F);

                    count++;
                    if (count >= 3) {
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 0L, 10L);
        }else {
            new WardenFlame(plugin).createBossProjectile(shooter,target,0.5f,5,0,100);
            shooter.getWorld().playSound(shooter.getLocation(), "terraria:flame", 2F, 1.0F);
        }
    }

    private Location getSpawnPoint(Player player){
        Location center =player.getLocation();
        Vector dir = player.getEyeLocation().getDirection().normalize();
        dir.setY(0);
        Location result=center.add(dir.multiply(20));
        if(isValidSpawn(result)){
            return result;
        }
        return null;
    }

    private Boolean isValidSpawn(Location loc){
        Block block = loc.getBlock();
        if(block.getType()!= Material.AIR){return false;}
        for (int x = -1; x <= 1; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    if(block.getRelative(x, y, z).getType()!= Material.AIR){return false;}
                }
            }
        }
        return  true;
    }

}
