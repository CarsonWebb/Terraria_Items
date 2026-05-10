package me.carson.terrariaItems.projectilesFolder;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public abstract class HookProjectile implements Listener {

    protected final Plugin plugin;
    protected final String texture;
    protected final String id;
    protected final float reach;
    protected final float speed;
    protected final Particle.DustOptions particle;
    private static final HashMap<UUID, ItemDisplay> hookMap = new HashMap<>();
    private static final HashMap<ItemDisplay, Boolean> hookMovingMap = new HashMap<>();

    public HookProjectile(Plugin plugin, String texture, String id, float reach, float speed, Particle.DustOptions particle) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        this.texture = texture;
        this.id = id;
        this.reach = reach;
        this.speed = speed;
        this.particle = particle;
    }

    public void activateHook(Player player){
        ItemDisplay proj=hookMap.get(player.getUniqueId());
        if(proj!=null){
            if(!hookMovingMap.get(proj)){
                removeHook(player,proj);
                createHook(player);
            }
        }else {
            createHook(player);
        }
    }

    public void createHook(Player player){

        Location loc = player.getEyeLocation();
        loc.add(loc.getDirection().normalize().multiply(0.1));

        Vector dir = player.getEyeLocation().getDirection();

        dir = dir.normalize().multiply(speed);

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
        hookMap.put(player.getUniqueId(),proj);
        hookMovingMap.put(proj,true);
        moveHook(player,proj,dir);
    }

    private void moveHook(Player player,ItemDisplay proj, Vector dir) {
        final int[] tick = {0};
        final Vector[][] direction = {{dir}};

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                removeHook(player, proj);
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
                        hookMovingMap.put(proj,false);
                        pullPlayerToHook(player,proj);
                        task.cancel();
                        return;
                    }
                }
            }
            drawChain(player,proj);

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
                removeHook(player, proj);
                task.cancel();
                return;
            }

            tick[0]++;
            if (tick[0] >= 100) {
                removeHook(player, proj);
                task.cancel();
                return;
            }

            Vector toPlayer = player.getEyeLocation().toVector().subtract(proj.getLocation().toVector());

            Vector direction = toPlayer.normalize().multiply(speed);

            Location now = proj.getLocation();
            Location next = now.clone().add(direction);

            RayTraceResult result = player.getWorld().rayTrace(now, direction, direction.length(), FluidCollisionMode.NEVER, true, 0.1, e -> e != proj);
            if (result != null && result.getHitEntity() == player) {
                removeHook(player, proj);
                task.cancel();
                return;
            }

            drawChain(player,proj);

            Vector norm = direction.clone().normalize().multiply(-1);
            float yaw = (float) Math.toDegrees(Math.atan2(-norm.getX(), norm.getZ()));
            float pitch = (float) Math.toDegrees(Math.asin(Math.max(-1.0, Math.min(1.0, -norm.getY()))));
            next.setYaw(yaw);
            next.setPitch(pitch);

            proj.teleport(next);
        }, 1L, 1L);
    }

    private void pullPlayerToHook(Player player, ItemDisplay proj) {
        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead() || !player.isOnline()) {
                if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE){
                    player.setAllowFlight(false);
                    player.setFlying(false);
                }
                task.cancel();
                return;
            }

            Vector toHook = proj.getLocation().toVector().subtract(player.getEyeLocation().add(0,-0.6,0).toVector());

            if (toHook.length() < 0.9) {
                player.setVelocity(new Vector(0, 0, 0));
                player.setAllowFlight(true);
                player.setFlying(true);
                return;
            }

            drawChain(player,proj);

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

    private void removeHook(Player player, ItemDisplay proj) {
        proj.remove();
        hookMap.remove(player.getUniqueId());
        hookMovingMap.remove(proj);
    }

    @EventHandler
    public void onPlayerJump(PlayerInputEvent event){
        Player player = event.getPlayer();
        if(!event.getInput().isJump()){return;}
        ItemDisplay proj=hookMap.get(player.getUniqueId());
        if(proj!=null){
            if(!hookMovingMap.get(proj)){
                if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE){
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.setVelocity(player.getVelocity().add(new Vector(0, 0.5, 0)));

                }
                removeHook(player,proj);
            }
        }
    }

    private void drawChain(Player player, ItemDisplay proj) {
        Location start = player.getEyeLocation();

        start.add(0, -0.6, 0);
        Vector right = start.getDirection().crossProduct(new Vector(0, 1, 0)).normalize();
        start.add(right.multiply(0.3));

        Location end = proj.getLocation();

        Vector between = end.toVector().subtract(start.toVector());
        double distance = between.length();
        Vector step = between.normalize().multiply(0.5); // 0.5 = gap between particles

        Location current = start.clone();
        for (double d = 0; d < distance; d += 0.5) {
            player.getWorld().spawnParticle(Particle.DUST, current, 1, 0, 0, 0, 0,particle);
            current.add(step);
        }
    }

}
