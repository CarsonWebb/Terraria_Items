package me.carson.terrariaItems.throwablesFolder;

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

import java.util.concurrent.atomic.AtomicReference;

public abstract class Throwable implements Listener {

    protected final Plugin plugin;
    protected final int damage;
    protected final String texture;
    protected final String id;
    protected final int peirce;
    protected final int bounces;
    protected final float bounciness;
    protected final float drag;
    protected final DamageType damageType;

    public Throwable(Plugin plugin, int damage, String texture, String id, int peirce, int bounces,float bounciness,float drag, DamageType damageType) {
        this.plugin = plugin;
        this.texture = texture;
        this.id = id;
        this.damage=damage;
        this.peirce = peirce;
        this.bounces = bounces;
        this.bounciness=bounciness;
        this.drag=drag;
        this.damageType = damageType;
    }

    public void createThrowableObj(Player player, float speed, float weaponDamage, float spread, float duration, float gravDuration, float gravStrength){
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
        NamespacedKey key = new NamespacedKey(plugin, id);
        proj.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,1);
        proj.setInterpolationDuration(0);
        proj.setTeleportDuration(2);
        proj.setInterpolationDelay(-1);
        faceDirection(proj, dir);
        moveThrowableObj(player,weaponDamage,duration,proj,dir,gravDuration,gravStrength);
    }

    private void moveThrowableObj(Player player, float weaponDamage, float duration, ItemDisplay proj, Vector dir, float gravDuration, float gravStrength) {
        final int[] tick = {0};
        final int[] enemiesHit = {0};
        final Vector[][] direction = {{dir}};
        final boolean[] stuck = {false}; // add this

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                proj.remove();
                task.cancel();
                return;
            }

            tick[0]++;
            if (tick[0] >= duration) {
                timerEndEffect(proj, player);
                proj.remove();
                task.cancel();
                return;
            }

            if (stuck[0]) return; // skip everything if stuck

            if (tick[0] >= gravDuration) {
                direction[0][0] = new Vector(direction[0][0].getX() * drag, direction[0][0].getY() - gravStrength, direction[0][0].getZ() * drag);
            }

            Location now = proj.getLocation();
            Location next = now.clone().add(direction[0][0]);
            float dist = (float) now.distance(next);

            RayTraceResult result = player.getWorld().rayTrace(now, direction[0][0], dist, FluidCollisionMode.NEVER, true, 0.1, e -> (e.getType() != proj.getType()) && (e != player));
            if (result != null) {
                if (result.getHitBlock() != null) {
                    if (!result.getHitBlock().isPassable() && result.getHitBlockFace() != null) {
                        if (bounciness == 0) {
                            stuck[0] = true; // stop here, don't teleport
                            return;
                        }
                        hitBlockEffect(result.getHitBlock());
                        if(direction[0][0].length()<=0.12&&result.getHitBlockFace()!=BlockFace.DOWN){
                            stuck[0]=true;
                            startCollisionChecks(proj,player,enemiesHit[0],duration,weaponDamage);
                        }

                        direction[0][0] = bounce(direction[0][0], result.getHitBlockFace());
                        next = now.clone().add(direction[0][0]);
                    }
                }
                if (result.getHitEntity() != null) {
                    if (result.getHitEntity() instanceof LivingEntity target) {
                        int temp= target.getMaximumNoDamageTicks();
                        target.setMaximumNoDamageTicks(0);
                        DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(player).build();
                        target.damage((damage + weaponDamage), source);
                        hitEntityEffect(proj, player);
                        target.setMaximumNoDamageTicks(temp);
                    }
                    if (enemiesHit[0] >= peirce) {
                        proj.remove();
                        task.cancel();
                        return;
                    } else {
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
        }, 1L, 1L);
    }

    private Vector bounce(Vector currentDir, BlockFace face) {
        Vector v = currentDir.clone();
        switch (face) {
            case EAST, WEST   -> v.setX(-v.getX());
            case UP, DOWN     -> v.setY(-v.getY());
            case NORTH, SOUTH -> v.setZ(-v.getZ());
        }
        return v.multiply(bounciness);
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

    private void startCollisionChecks(ItemDisplay proj,Player player,int hit,float duration,float weaponDamage){
        final int[] enemiesHit = {hit};
        final int[] enemiesHitAgain = {0};
        final int[] tick = {0};

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                proj.remove();
                task.cancel();
                return;
            }
            tick[0]++;
            if (tick[0] >= duration) {
                timerEndEffect(proj, player);
                proj.remove();
                task.cancel();
                return;
            }

            for (Entity e : proj.getNearbyEntities(0.2, 0.2, 0.2)) {
                if (e instanceof LivingEntity target && e!=player) {
                    int temp= target.getMaximumNoDamageTicks();
                    target.setMaximumNoDamageTicks(0);
                    target.damage(damage+weaponDamage);
                    target.setMaximumNoDamageTicks(temp);
                    if (enemiesHit[0]+enemiesHitAgain[0] >= peirce) {
                        proj.remove();
                        task.cancel();
                        return;
                    } else {
                        enemiesHitAgain[0]++;
                    }

                }
            }
        }, 1L, 1L);
    }

    public abstract void hitEntityEffect(ItemDisplay proj,Player player);

    public abstract void hitBlockEffect(Block block);

    public abstract void timerEndEffect(ItemDisplay proj,Player player);
}
