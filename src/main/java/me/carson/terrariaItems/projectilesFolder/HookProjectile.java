package me.carson.terrariaItems.projectilesFolder;


import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public abstract class HookProjectile implements Listener {

    protected final Plugin plugin;
    protected final String texture;
    protected final String id;
    protected final float reach;
    protected final float speed;

    public HookProjectile(Plugin plugin, String texture, String id, float reach, float speed) {
        this.plugin = plugin;
        this.texture = texture;
        this.id = id;
        this.reach = reach;
        this.speed = speed;
    }

    public void createHook(Player player){
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
        moveHook(player,proj,dir);
    }

    private void moveHook(Player player,ItemDisplay proj, Vector dir) {
        final int[] tick = {0};
        final Vector[][] direction = {{dir}};

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                proj.remove();
                task.cancel();
                return;
            }

            tick[0]++;
            if (tick[0] >= reach) {
                moveHookBack(player,proj);
                task.cancel();
                return;
            }

            Location now = proj.getLocation();
            Location next = now.clone().add(direction[0][0]);
            float dist = (float) now.distance(next);

            RayTraceResult result = player.getWorld().rayTrace(now, direction[0][0], dist, FluidCollisionMode.NEVER, true, 0.1, e -> (e.getType() != proj.getType()) && (e != player));
            if (result != null) {
                if (result.getHitBlock() != null) {
                    if (!result.getHitBlock().isPassable() && result.getHitBlockFace() != null) {
                        pullPlayerToHook(player,proj);
                        task.cancel();
                        return;
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

    private void moveHookBack(Player player, ItemDisplay proj) {
        final int[] tick = {0};

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                proj.remove();
                task.cancel();
                return;
            }

            tick[0]++;
            if (tick[0] >= 100) {
                proj.remove();
                task.cancel();
                return;
            }

            Vector toPlayer = player.getEyeLocation().toVector().subtract(proj.getLocation().toVector());

            Vector direction = toPlayer.normalize().multiply(speed);

            Location now = proj.getLocation();
            Location next = now.clone().add(direction);

            RayTraceResult result = player.getWorld().rayTrace(now, direction, direction.length(), FluidCollisionMode.NEVER, true, 0.1, e -> e != proj);
            if (result != null && result.getHitEntity() == player) {
                proj.remove();
                task.cancel();
                return;
            }

            Vector norm = direction.clone().normalize().multiply(-1);
            float yaw = (float) Math.toDegrees(Math.atan2(-norm.getX(), norm.getZ()));
            float pitch = (float) Math.toDegrees(Math.asin(Math.max(-1.0, Math.min(1.0, -norm.getY()))));
            next.setYaw(yaw);
            next.setPitch(pitch);

            proj.teleport(next);
        }, 1L, 1L);
    }

    private void pullPlayerToHook(Player player, ItemDisplay proj) {
        final int[] tick = {0};

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead() || !player.isOnline()) {
                proj.remove();
                player.setAllowFlight(false);
                player.setFlying(false);
                task.cancel();
                return;
            }

            tick[0]++;
            if (tick[0] >= 100) {
                proj.remove();
                player.setAllowFlight(false);
                player.setFlying(false);
                task.cancel();
                return;
            }

            Vector toHook = proj.getLocation().toVector().subtract(player.getEyeLocation().toVector());

            if (toHook.length() < 1) {
                player.setVelocity(new Vector(0, 0, 0));
                player.setAllowFlight(true);
                player.setFlying(true);
                return;
            }

            player.setVelocity(toHook.normalize().multiply(speed)); // 1.5 = pull speed
        }, 1L, 1L);
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

}
