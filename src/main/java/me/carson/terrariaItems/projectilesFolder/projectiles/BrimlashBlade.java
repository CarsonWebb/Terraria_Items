package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class BrimlashBlade extends Projectile {

    public BrimlashBlade(Plugin plugin) {
        super(plugin, 0,  "brimlash_blade", "BrimlashBlade",99,0, DamageType.PLAYER_ATTACK,new Particle.DustOptions(Color.fromRGB(136, 30, 27), 1f),0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

    public void createBrimlashBladeProjectile(Player player,float speed,float weaponDamage,float duration){
        Location loc = player.getEyeLocation();
        loc.add(loc.getDirection().normalize().multiply(0.1));

        Vector dir = player.getEyeLocation().getDirection();

        dir.normalize().multiply(speed);

        loc.setDirection(dir);

        ItemDisplay proj = (ItemDisplay) player.getWorld().spawnEntity(loc, EntityType.ITEM_DISPLAY);

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
        moveBrimlashBladeProj(player,weaponDamage,duration,proj,dir);
    }

    private void moveBrimlashBladeProj(Player player,float weaponDamage,float duration,ItemDisplay proj, Vector dir){
        final int[] tick = {0};
        final Vector[] direction = {dir};
        ArrayList<Entity> hitEntities=new ArrayList<>();
        DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(player).build();

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                task.cancel();
                return;
            }

            tick[0]++;
            if (tick[0] >= duration) {
                proj.remove();
                createBrimlashExplosion(player,2,proj.getLocation(),direction[0],weaponDamage);
                task.cancel();
                return;
            }

            //block handling
            Location now = proj.getLocation();
            Location next = now.clone().add(direction[0]);
            float dist= (float) now.distance(next);

            direction[0] = direction[0].multiply(0.95f);

            RayTraceResult result= player.getWorld().rayTrace(now,direction[0],dist,FluidCollisionMode.NEVER,true,0.1, e -> (e.getType() != proj.getType())&&(e!=player)&&!(hitEntities.contains(e)));
            if(result!=null){
                if(result.getHitBlock()!=null){
                    if(!result.getHitBlock().isPassable() && result.getHitBlockFace()!=null){
                        hitBlockEffect(result.getHitBlock());
                        proj.remove();
                        createBrimlashExplosion(player,2,proj.getLocation(),direction[0],weaponDamage);
                        task.cancel();
                        return;
                    }
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        target.setMaximumNoDamageTicks(0);
                        target.damage((damage+weaponDamage),source);
                        hitEntityEffect(target,player);
                        target.setMaximumNoDamageTicks(20);
                        hitEntities.add(target);
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

    private void createBrimlashExplosion(Player player,float speed,Location loc,Vector dir,float weaponDamage){
        Vector normalDir = dir.clone().normalize();
        Vector perp = getPerpendicular(normalDir); // defines the plane with dir
        double[] radialAngles = {0, Math.toRadians(120), Math.toRadians(240)};

        for (double radial : radialAngles) {
            Vector spreadDir = rotateAround(normalDir.clone(), perp, radial).multiply(speed);

            Location loc2 = loc.clone();
            loc2.setDirection(spreadDir);

            ItemDisplay proj = (ItemDisplay) player.getWorld().spawnEntity(loc2, EntityType.ITEM_DISPLAY);

            ItemStack item = new ItemStack(Material.IRON_NUGGET);
            ItemMeta meta=item.getItemMeta();
            meta.setItemModel(new NamespacedKey("terraria", texture));
            item.setItemMeta(meta);

            proj.setItemStack(item);
            proj.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.HEAD);
            NamespacedKey key = new NamespacedKey(plugin, id);
            proj.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,1);
            proj.setInterpolationDuration(0);
            proj.setTeleportDuration(2);
            proj.setInterpolationDelay(-1);

            faceDirection(proj, spreadDir);
            moveHomingBrimlashBlade(player, 40, proj, spreadDir.normalize(),weaponDamage);
        }
    }

    private void moveHomingBrimlashBlade(Player player,float duration,ItemDisplay proj, Vector dir,float weaponDamage){
        final int[] tick = {0};
        final Vector[] direction = {dir};
        DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(player).build();

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

            if(tick[0]>=5){
                LivingEntity homing=getClosestEntity(proj,player,15);
                if(homing!=null){
                    direction[0]=vectorBetween(proj,homing).normalize().multiply(1);
                }
            }

            //block handling
            Location now = proj.getLocation();
            Location next = now.clone().add(direction[0]);
            float dist= (float) now.distance(next);

            RayTraceResult result= player.getWorld().rayTrace(now,direction[0],dist,FluidCollisionMode.NEVER,true,0.1, e -> (e.getType() != proj.getType())&&(e!=player)&&(e.getType()!=EntityType.ITEM_DISPLAY));
            if(result!=null){
                if(result.getHitBlock()!=null){
                    if(!result.getHitBlock().isPassable() && result.getHitBlockFace()!=null){
                        hitBlockEffect(result.getHitBlock());
                        proj.remove();
                        task.cancel();
                        return;
                    }
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        target.setMaximumNoDamageTicks(0);
                        target.damage(damage+weaponDamage,source);
                        hitEntityEffect(target,player);
                        target.setMaximumNoDamageTicks(20);
                        proj.remove();
                        task.cancel();
                        return;
                    }
                }
            }
            Vector norm = direction[0].clone().normalize();
            float yaw = (float) Math.toDegrees(Math.atan2(-norm.getX(), norm.getZ()));
            float pitch = (float) Math.toDegrees(Math.asin(-norm.getY()));
            next.setYaw(yaw);
            next.setPitch(pitch);
            proj.teleport(next);

        }, 1L, 1L);
    }

    private Vector getPerpendicular(Vector v) {
        Vector ref = Math.abs(v.getY()) < 0.9 ? new Vector(0, 1, 0) : new Vector(1, 0, 0);
        return v.clone().crossProduct(ref).normalize();
    }

    private Vector rotateAround(Vector v, Vector axis, double angleRad) {
        double cos = Math.cos(angleRad);
        double sin = Math.sin(angleRad);
        return v.clone().multiply(cos)
                .add(axis.clone().crossProduct(v).multiply(sin))
                .add(axis.clone().multiply(axis.dot(v) * (1 - cos)));
    }

}
