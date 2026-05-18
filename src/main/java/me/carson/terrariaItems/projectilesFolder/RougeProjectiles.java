package me.carson.terrariaItems.projectilesFolder;

import me.carson.terrariaItems.handlers.PlayerDataHandler;
import me.carson.terrariaItems.handlers.StealthManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class RougeProjectiles {

    protected final Plugin plugin;
    protected final int damage;
    protected final String texture;
    protected final String id;
    protected int peirce;
    protected final int bounces;
    protected final DamageType damageType;
    protected final Particle.DustOptions particle;
    private final StealthManager stealthManager=StealthManager.getInstance();
    public record Result(ItemDisplay proj, Vector dir) {}

    public RougeProjectiles(Plugin plugin, int damage, String texture, String id, int peirce, int bounces, DamageType damageType, Particle.DustOptions particle) {
        this.plugin = plugin;
        this.texture = texture;
        this.id = id;
        this.damage=damage;
        this.peirce = peirce;
        this.bounces = bounces;
        this.damageType = damageType;
        this.particle = particle;
    }

    private Result createDefaultProjectile(Player player,float speed, float spread){
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

        ItemDisplay proj = (ItemDisplay) player.getWorld().spawnEntity(loc, EntityType.ITEM_DISPLAY);

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
        faceDirection(proj, dir);
        return new Result(proj,dir);
    }

    public void createProjectile(Player player,float speed,float weaponDamage, float spread,float duration,float gravDuration,float gravStrength,float spinSpeed){
        Result r = createDefaultProjectile(player,speed,spread);
        moveProj(player,weaponDamage,duration,r.proj,r.dir,gravDuration,gravStrength,spinSpeed);
    }

    private void moveProj(Player player,float weaponDamage,float duration,ItemDisplay proj, Vector dir, float gravDuration,float gravStrength,float spinSpeed){
        final int[] tick = {0};
        final int[] enemiesHit = {0};
        final int[] blocksBounced = {0};
        final Vector[] direction = {dir};
        final float[] spinAngle = {0f};
        ArrayList<Entity> hitEntities=new ArrayList<>();

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                task.cancel();
                hitEntities.clear();
                return;
            }

            tick[0]++;
            if (tick[0] >= duration) {
                proj.remove();
                task.cancel();
                hitEntities.clear();
                return;
            }

            if (tick[0] >= gravDuration) {
                direction[0] = new Vector(direction[0].getX(), direction[0].getY() - gravStrength, direction[0].getZ());
            }

            //block handling
            Location now = proj.getLocation();
            Location next = now.clone().add(direction[0]);
            float dist= (float) now.distance(next);

            RayTraceResult result= player.getWorld().rayTrace(now,direction[0],dist,FluidCollisionMode.NEVER,true,0.1, e -> (e.getType() != proj.getType())&&(e!=player)&&!(hitEntities.contains(e)));
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
                        target.damage((damage+weaponDamage+getStealthDamage(weaponDamage,player)),source);
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
            //proj.setInterpolationDelay(0);
            spinProjectile(proj, spinAngle, spinSpeed);

            if (particle != null&&tick[0]>2) {
                proj.getWorld().spawnParticle(Particle.DUST, now, 1, 0, 0, 0, 0,particle);
            }
        }, 1L, 1L);
    }

    public void createGlaiveProjectile(Player player,float speed,float weaponDamage, float spread,float duration,float gravDuration,float gravStrength,float spinSpeed){
        Result r = createDefaultProjectile(player,speed,spread);
        moveGlaiveProj(player,weaponDamage,duration,r.proj,r.dir,gravDuration,gravStrength,spinSpeed);
    }

    private void moveGlaiveProj(Player player,float weaponDamage,float duration,ItemDisplay proj, Vector dir, float gravDuration,float gravStrength,float spinSpeed){
        final int[] tick = {0};
        final int[] enemiesHit = {0};
        final int[] blocksBounced = {0};
        final Vector[] direction = {dir};
        final float[] spinAngle = {0f};
        ArrayList<Entity> hitEntities=new ArrayList<>();

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                task.cancel();
                hitEntities.clear();
                return;
            }

            tick[0]++;
            if (tick[0] >= duration) {
                proj.remove();
                task.cancel();
                hitEntities.clear();
                return;
            }

            if (tick[0] >= gravDuration) {
                direction[0] = new Vector(direction[0].getX(), direction[0].getY() - gravStrength, direction[0].getZ());
            }

            //block handling
            Location now = proj.getLocation();
            Location next = now.clone().add(direction[0]);
            float dist= (float) now.distance(next);

            RayTraceResult result= player.getWorld().rayTrace(now,direction[0],dist,FluidCollisionMode.NEVER,true,0.1, e -> (e.getType() != proj.getType())&&(e!=player)&&!(hitEntities.contains(e)));
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
                            LivingEntity target=getClosestEntity(proj,player, hitEntities,10);
                            if (target == null) {
                                direction[0] =bounce(direction[0],result.getHitBlockFace());
                            }else{
                                direction[0]=target.getEyeLocation().toVector().subtract(proj.getLocation().toVector()).normalize();
                            }
                            next = now.clone().add(direction[0]);
                        }
                    }
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        target.setMaximumNoDamageTicks(0);
                        DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(player).build();
                        target.damage((damage+weaponDamage+getStealthDamage(weaponDamage+damage,player)),source);
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
            proj.setInterpolationDelay(0);
            spinProjectile(proj, spinAngle, spinSpeed);

            if (particle != null&&tick[0]>2) {
                proj.getWorld().spawnParticle(Particle.DUST, now, 1, 0, 0, 0, 0,particle);
            }
        }, 1L, 1L);
    }

    private double getStealthDamage(double base,Player player){
        return base*(stealthManager.getStealth(player.getUniqueId())*0.01);
    }

    private void spinProjectile(ItemDisplay proj, float[] spinAngle, float spinSpeed) {
        spinAngle[0] = (spinAngle[0] + spinSpeed) % 360f;
        double rad = Math.toRadians(spinAngle[0]);
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);

        org.joml.Matrix4f spinMatrix = new org.joml.Matrix4f(
                1,    0,   0, 0,
                0,  cos, sin, 0,
                0, -sin, cos, 0,
                0,    0,   0, 1
        );

        proj.setTransformationMatrix(spinMatrix);
    }

    public LivingEntity getClosestEntity(ItemDisplay proj, Player player, ArrayList<Entity> hitEntities, double radius) {
        Location loc = proj.getLocation();

        return proj.getWorld().getNearbyEntities(loc, radius, radius, radius).stream()
                .filter(e -> e instanceof LivingEntity)
                .filter(e -> e != player)
                .filter(e -> e.getType() != proj.getType())
                .filter(e -> !hitEntities.contains(e))
                .map(e -> (LivingEntity) e)
                .min(Comparator.comparingDouble(e -> e.getLocation().distanceSquared(loc)))
                .orElse(null);
    }

    private Vector bounce(Vector currentDir, BlockFace face) {
        Vector v = currentDir.clone();
        switch (face) {
            case EAST, WEST   -> v.setX(-v.getX());
            case UP, DOWN     -> v.setY(-v.getY());
            case NORTH, SOUTH -> v.setZ(-v.getZ());
        }
        return v;
    }

    private void faceDirection(ItemDisplay proj, Vector dir) {
        Vector norm = dir.clone().normalize();

        float yaw = (float) Math.toDegrees(Math.atan2(-norm.getX(), norm.getZ()));
        float pitch = (float) Math.toDegrees(Math.asin(-norm.getY()));

        Location loc = proj.getLocation();
        loc.setYaw(yaw);
        loc.setPitch(pitch);
        proj.teleport(loc);
    }

    public abstract void hitEntityEffect(LivingEntity entity, Player player);

    public abstract void hitBlockEffect(Block block);

    public abstract void onStealthThrow(Player player,float speed, float damage, float spread, float duration,float gravDuration, float gravStrength, float spinSpeed);

}
