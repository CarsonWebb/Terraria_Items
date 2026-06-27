package me.carson.terrariaItems.projectilesFolder.rougeProjectiles;

import me.carson.terrariaItems.projectilesFolder.RougeProjectiles;
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
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ExorcismProjectile extends RougeProjectiles {

    public ExorcismProjectile(Plugin plugin) {
        super(plugin, 20,  "exorcism", "ExorcismProjectile",0,0, DamageType.TRIDENT,null);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

    @Override
    public void onStealthThrow(Player player,float speed, float damage, float spread, float duration,float gravDuration, float gravStrength,float spinSpeed,double currentStealth) {

    }

    public void createExorcismProjectile(Player player,double currentStealth, boolean isStealthStrike){
        Location loc = player.getEyeLocation();
        Vector right = loc.getDirection().crossProduct(new Vector(0, -1, 0)).normalize();
        loc.add(right.multiply(0.5));

        loc.add(loc.getDirection().normalize().multiply(0.1));

        Vector dir = player.getEyeLocation().getDirection();

        loc.add(dir.multiply(0.5));
        loc.setDirection(dir);
        loc.setPitch(-90);

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

        moveExorcismStage1(player,proj,currentStealth,isStealthStrike);
    }

    private void moveExorcismStage1(Player player, ItemDisplay proj, double currentStealth, boolean isStealthStrike) {
        final double[] angle = {0};

        Transformation t = proj.getTransformation();
        proj.setTransformation(new Transformation(
                t.getTranslation(),
                new Quaternionf().rotateX((float) Math.toRadians(90)),
                t.getScale(),
                new Quaternionf()
        ));

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                proj.remove();
                task.cancel();
                return;
            }

            if (angle[0] >= Math.PI) {
                task.cancel();
                moveExorcismStage2(player,proj,currentStealth,isStealthStrike);
                return;
            }

            angle[0] += 0.25;

            Location eye = player.getEyeLocation();
            float yaw = (float) Math.toRadians(-eye.getYaw());
            Quaternionf rotation = new Quaternionf().rotateY(yaw);

            Vector3f localOffset = new Vector3f(
                    -0.5f,
                    (float) (Math.sin(angle[0])),
                    (float) (Math.cos(angle[0]))
            );

            Vector3f worldOffset = rotation.transform(localOffset);

            Location next = eye.clone().add(worldOffset.x, worldOffset.y, worldOffset.z);
            next.setPitch(0);
            proj.teleport(next);
        }, 1L, 1L);
    }

    private void moveExorcismStage2(Player player, ItemDisplay proj, double currentStealth, boolean isStealthStrike){
        final int[] tick = {0};

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                proj.remove();
                task.cancel();
                return;
            }

            tick[0]++;
            if (tick[0] >= 15) {
                moveExorcismStage3(player,proj,currentStealth,isStealthStrike);
                task.cancel();
                return;
            }

            Location next=proj.getLocation().add(0,2.5,0);
            proj.teleport(next);
        }, 1L, 1L);
    }

    private void moveExorcismStage3(Player player, ItemDisplay proj, double currentStealth, boolean isStealthStrike){
        final int[] tick = {0};
        final float[] speed = {2f};
        final double[] last={0};
        final Vector[] direction = {vectorBetween(proj,player).normalize().multiply(speed[0])};

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
            RayTraceResult lookingResult = player.getWorld().rayTrace(player.getEyeLocation(),player.getEyeLocation().getDirection(),200, FluidCollisionMode.NEVER,true,0.1, entity -> (entity != player&&entity!=proj));
            if(lookingResult!=null){
                Vector going=lookingResult.getHitPosition().subtract(proj.getLocation().toVector());
                last[0]=lookingResult.getHitPosition().getY();
                going.setY(-speed[0]);
                direction[0]=going.normalize().multiply(speed[0]);
            }
            Location now = proj.getLocation();
            Location next = now.clone().add(direction[0]);
            float dist= (float) now.distance(next);

            RayTraceResult result= player.getWorld().rayTrace(now,direction[0],dist,FluidCollisionMode.NEVER,true,0.1, e -> (e.getType() != proj.getType())&&(e!=player));
            if(result!=null){
                if(result.getHitBlock()!=null){
                    if(!result.getHitBlock().isPassable() && result.getHitBlockFace()!=null){
                        if((proj.getLocation().getY()-last[0])<2){
                            Location ground=proj.getLocation();
                            ground.setY(result.getHitBlock().getY()+1.5);
                            proj.teleport(ground);
                            moveExorcismStage4(player,proj,currentStealth,isStealthStrike);
                            task.cancel();
                            return;
                        }
                    }
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(player).build();
                        target.damage((damage+getStealthDamage(damage,currentStealth)),source);
                    }
                }
            }
            Vector norm = direction[0].clone().normalize();
            float yaw = (float) Math.toDegrees(Math.atan2(-norm.getX(), norm.getZ()));
            float pitch = (float) Math.toDegrees(Math.asin(-norm.getY()));
            next.setYaw(yaw);
            next.setPitch(pitch-90);
            proj.teleport(next);
        }, 1L, 1L);
    }

    private void moveExorcismStage4(Player player, ItemDisplay proj, double currentStealth, boolean isStealthStrike){
        final int[] tick = {0};

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                proj.remove();
                task.cancel();
                return;
            }
            tick[0]++;
            if (tick[0] >= 40) {
                if(isStealthStrike){
                    proj.getWorld().createExplosion(proj.getLocation(), 5.0F, false, false);
                }
                proj.remove();
                task.cancel();
                return;
            }

            for (Entity e : proj.getNearbyEntities(0.2, 0.4, 0.2)) {
                if (e instanceof LivingEntity target && e!=player) {
                    DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(player).build();
                    target.damage((damage+getStealthDamage(damage,currentStealth)),source);
                }
            }
        }, 1L, 1L);
    }

}
