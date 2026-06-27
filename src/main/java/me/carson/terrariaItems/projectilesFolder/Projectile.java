package me.carson.terrariaItems.projectilesFolder;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Projectile implements Listener {

    protected final Plugin plugin;
    protected final int damage;
    protected final String texture;
    protected final String id;
    protected final int peirce;
    protected final int bounces;
    protected final DamageType damageType;
    protected final Particle.DustOptions particle;

    public Projectile(Plugin plugin, int damage, String texture, String id, int peirce, int bounces, DamageType damageType, Particle.DustOptions particle) {
        this.plugin = plugin;
        this.texture = texture;
        this.id = id;
        this.damage=damage;
        this.peirce = peirce;
        this.bounces = bounces;
        this.damageType = damageType;
        this.particle = particle;
    }

    public void createProjectile(Player player,float speed,float weaponDamage, float spread,float duration){
        Location loc = player.getEyeLocation();
        loc.add(loc.getDirection().normalize().multiply(0.1));

        Vector dir = player.getEyeLocation().getDirection();
        dir.add(new Vector(
                (Math.random() - 0.5) * spread,
                (Math.random() - 0.5) * spread,
                (Math.random() - 0.5) * spread
        ));
        dir.normalize().multiply(speed);

        loc.setDirection(dir);

        ItemDisplay proj = (ItemDisplay) player.getWorld().spawnEntity(loc,EntityType.ITEM_DISPLAY);

        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta=item.getItemMeta();
        meta.setItemModel(new NamespacedKey("terraria", texture));
        item.setItemMeta(meta);

        proj.setItemStack(item);
        NamespacedKey key = new NamespacedKey(plugin, id);
        proj.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,1);
        proj.setInterpolationDuration(0);
        proj.setTeleportDuration(2);
        proj.setInterpolationDelay(-1);
        faceDirection(proj, dir);
        moveProj(player,weaponDamage,duration,proj,dir);
    }

    public void createFallingProjectile(Player player,float speed,float weaponDamage, float spread,float duration,float height,Location location){
        if(spread==0){return;}
        float valueX = ThreadLocalRandom.current().nextFloat(-spread, spread);
        float valueZ = ThreadLocalRandom.current().nextFloat(-spread, spread);

        Location loc = location.clone().add(valueX,height,valueZ);

        Vector dir = location.toVector().subtract(loc.toVector());

        dir.normalize().multiply(speed);

        loc.setDirection(dir);

        ItemDisplay proj = (ItemDisplay) player.getWorld().spawnEntity(loc,EntityType.ITEM_DISPLAY);

        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta=item.getItemMeta();
        meta.setItemModel(new NamespacedKey("terraria", texture));
        item.setItemMeta(meta);

        proj.setItemStack(item);
        NamespacedKey key = new NamespacedKey(plugin, id);
        proj.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,1);
        proj.setInterpolationDuration(0);
        proj.setTeleportDuration(2);
        proj.setInterpolationDelay(-1);
        faceDirection(proj, dir);
        moveProj(player,weaponDamage,duration,proj,dir);

    }

    public void moveProj(Player player,float weaponDamage,float duration,ItemDisplay proj, Vector dir){
        final int[] tick = {0};
        final int[] enemiesHit = {0};
        final int[] blocksBounced = {0};
        final Vector[] direction = {dir};
        ArrayList<Entity> hitEntities=new ArrayList<>();

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                task.cancel();
                return;
            }

            tick[0]++;
            if (tick[0] >= duration) {
                proj.remove();
                task.cancel();
                return;
            }

            //block handling
            Location now = proj.getLocation();
            Location next = now.clone().add(direction[0]);
            float dist= (float) now.distance(next);

            RayTraceResult result= player.getWorld().rayTrace(now,direction[0],dist,FluidCollisionMode.NEVER,true,0.1,e -> (e.getType() != proj.getType())&&(e!=player)&&!(hitEntities.contains(e)));
            if(result!=null){
                if(result.getHitBlock()!=null){
                    if(!result.getHitBlock().isPassable() && result.getHitBlockFace()!=null){
                        hitBlockEffect(result.getHitBlock());
                        if(blocksBounced[0]>=bounces){
                            proj.remove();
                            task.cancel();
                            return;
                        }else{
                            blocksBounced[0]++;
                            direction[0] =bounce(direction[0],result.getHitBlockFace());
                            next = now.clone().add(direction[0]);
                            hitEntities.clear();
                        }
                    }
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        target.setMaximumNoDamageTicks(0);
                        DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(player).build();
                        target.damage((damage+weaponDamage),source);
                        hitEntityEffect(target,player);
                        target.setMaximumNoDamageTicks(20);
                        hitEntities.add(target);
                    }
                    if(enemiesHit[0] >=peirce) {
                        proj.remove();
                        task.cancel();
                        return;
                    }else {
                        enemiesHit[0]++;
                    }
                }
            }
            Vector norm = direction[0].clone().normalize();
            float yaw = (float) Math.toDegrees(Math.atan2(-norm.getX(), norm.getZ()));
            float pitch = (float) Math.toDegrees(Math.asin(-norm.getY()));
            next.setYaw(yaw);
            next.setPitch(pitch);
            proj.teleport(next);

            if (particle != null&&tick[0]>2) {
                proj.getWorld().spawnParticle(Particle.DUST, now, 1, 0, 0, 0, 0,particle);
            }
        }, 1L, 1L);
    }

    public void createGravProjectile(Player player,float speed,float weaponDamage, float spread,float duration,float gravDuration,float gravStrength){
        Location loc = player.getEyeLocation();
        loc.add(loc.getDirection().normalize().multiply(0.1));

        Vector dir = player.getEyeLocation().getDirection();
        dir.add(new Vector(
                (Math.random() - 0.5) * spread,
                (Math.random() - 0.5) * spread,
                (Math.random() - 0.5) * spread
        ));
        dir.normalize().multiply(speed);

        loc.setDirection(dir);

        ItemDisplay proj = (ItemDisplay) player.getWorld().spawnEntity(loc,EntityType.ITEM_DISPLAY);

        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta=item.getItemMeta();
        meta.setItemModel(new NamespacedKey("terraria", texture));
        item.setItemMeta(meta);

        proj.setItemStack(item);
        NamespacedKey key = new NamespacedKey(plugin, id);
        proj.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,1);
        proj.setInterpolationDuration(0);
        proj.setTeleportDuration(2);
        proj.setInterpolationDelay(-1);
        faceDirection(proj, dir);
        moveGravProj(player,weaponDamage,duration,proj,dir,gravDuration,gravStrength);
    }

    private void moveGravProj(Player player,float weaponDamage,float duration,ItemDisplay proj, Vector dir, float gravDuration,float gravStrength){
        final int[] tick = {0};
        final int[] enemiesHit = {0};
        final int[] blocksBounced = {0};
        final Vector[][] direction = {{dir}};
        ArrayList<Entity> hitEntities=new ArrayList<>();

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                task.cancel();
                return;
            }

            tick[0]++;
            if (tick[0] >= duration) {
                proj.remove();
                task.cancel();
                return;
            }

            if (tick[0] >= gravDuration) {
                direction[0][0] = new Vector(direction[0][0].getX()*0.9, direction[0][0].getY() - gravStrength, direction[0][0].getZ()*0.9);
            }

            //block handling
            Location now = proj.getLocation();
            Location next = now.clone().add(direction[0][0]);
            float dist= (float) now.distance(next);

            RayTraceResult result= player.getWorld().rayTrace(now,direction[0][0],dist,FluidCollisionMode.NEVER,true,0.1,e -> (e.getType() != proj.getType())&&(e!=player)&&!(hitEntities.contains(e)));
            if(result!=null){
                if(result.getHitBlock()!=null){
                    if(!result.getHitBlock().isPassable() && result.getHitBlockFace()!=null){
                        hitBlockEffect(result.getHitBlock());
                        if(blocksBounced[0]>=bounces){
                            proj.remove();
                            task.cancel();
                            return;
                        }else{
                            blocksBounced[0]++;
                            direction[0][0] =bounce(direction[0][0],result.getHitBlockFace());
                            next = now.clone().add(direction[0][0]);
                            hitEntities.clear();
                        }
                    }
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        target.setMaximumNoDamageTicks(0);
                        DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(player).build();
                        target.damage((damage+weaponDamage),source);
                        hitEntityEffect(target,player);
                        target.setMaximumNoDamageTicks(20);
                        hitEntities.add(target);
                    }
                    if(enemiesHit[0] >=peirce) {
                        proj.remove();
                        task.cancel();
                        return;
                    }else {
                        enemiesHit[0]++;
                    }
                }
            }
            Vector norm = direction[0][0].clone().normalize();
            float yaw = (float) Math.toDegrees(Math.atan2(-norm.getX(), norm.getZ()));
            float pitch = (float) Math.toDegrees(Math.asin(-norm.getY()));
            next.setYaw(yaw);
            next.setPitch(pitch);
            proj.teleport(next);

            if (particle != null&&tick[0]>2) {
                proj.getWorld().spawnParticle(Particle.DUST, now, 1, 0, 0, 0, 0,particle);
            }
        }, 1L, 1L);
    }

    public Vector bounce(Vector currentDir, BlockFace face) {
        Vector v = currentDir.clone();
        switch (face) {
            case EAST, WEST   -> v.setX(-v.getX());
            case UP, DOWN     -> v.setY(-v.getY());
            case NORTH, SOUTH -> v.setZ(-v.getZ());
        }
        return v;
    }

    public void faceDirection(ItemDisplay proj, Vector dir) {
        Vector norm = dir.clone().normalize();

        float yaw = (float) Math.toDegrees(Math.atan2(-norm.getX(), norm.getZ()));
        float pitch = (float) Math.toDegrees(Math.asin(-norm.getY()));

        Location loc = proj.getLocation();
        loc.setYaw(yaw);
        loc.setPitch(pitch);
        proj.teleport(loc);
    }

    public abstract void hitEntityEffect(LivingEntity entity,Player player);

    public abstract void hitBlockEffect(Block block);

}
