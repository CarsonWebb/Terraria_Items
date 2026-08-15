package me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows;

import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.Bow;
import org.bukkit.*;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class BrimstoneFury extends Bow {

    private final Particle.DustOptions particle=new Particle.DustOptions(Color.fromRGB(101, 31, 51), 0.5f);

    public BrimstoneFury(Plugin plugin) {
        super(plugin,"brimstone_fury.name","#FF96FF", Material.ARCHER_POTTERY_SHERD,"brimstone_fury","BrimstoneFury",10,0,8,0,"brimstone_fury.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new BrimstoneFury(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player){
        if (!player.getInventory().contains(Material.ARROW)) {return;}
        fireBrimstoneFury(player);
        player.getWorld().playSound(player.getLocation(), "terraria:repeater_use", 0.75F, 1.0F);
        player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));

    }

    private void fireBrimstoneFury(Player player){
        World world = player.getWorld();
        Vector dir = player.getEyeLocation().getDirection();
        Location start = player.getEyeLocation().add(dir.normalize().multiply(1));
        double distance = 50.0;

        Location loc = new Location(world, 0, 0, 0, 0f, 0f);
        loc.setDirection(dir);
        loc.setYaw(loc.getYaw() + 0);
        loc.setPitch(loc.getPitch() - 2);
        Vector angledDir = loc.getDirection();
        sendRaytrace(player,world,start,angledDir,distance);

        Location loc2 = new Location(world, 0, 0, 0, 0f, 0f);
        loc2.setDirection(dir);
        loc2.setYaw(loc2.getYaw() + 2f);
        loc2.setPitch(loc2.getPitch() + 2f);
        Vector angledDir2 = loc2.getDirection();
        sendRaytrace(player,world,start,angledDir2,distance);

        Location loc3 = new Location(world, 0, 0, 0, 0f, 0f);
        loc3.setDirection(dir);
        loc3.setYaw(loc3.getYaw() - 2f);
        loc3.setPitch(loc3.getPitch() +2f);
        Vector angledDir3 = loc3.getDirection();
        sendRaytrace(player,world,start,angledDir3,distance);
    }

    private void sendRaytrace(Player player,World world, Location start, Vector dir, double dist){
        Location endpoint=null;
        RayTraceResult result = world.rayTrace(start, dir, dist, FluidCollisionMode.NEVER, true, 0.1f, e ->e!=player);
        if(result!=null) {
            if (result.getHitEntity() != null) {
                if (result.getHitEntity() instanceof LivingEntity target) {
                    target.setMaximumNoDamageTicks(0);
                    DamageSource source = DamageSource.builder(DamageType.ARROW).withCausingEntity(player).withDirectEntity(player).build();
                    target.damage(damage, source);
                    target.setMaximumNoDamageTicks(20);
                }
                endpoint = result.getHitPosition().toLocation(world);
            }
            if(result.getHitBlock() != null) {
                endpoint = result.getHitPosition().toLocation(world);
            }
        }
        else {
            endpoint = start.clone().add(dir.multiply(dist));
        }
        drawArrow(start,endpoint,world);
    }

    private void drawArrow(Location start, Location end,World world) {;
        Vector between = end.toVector().subtract(start.toVector());
        double distance = between.length();
        Vector step = between.normalize().multiply(0.5); // 0.5 = gap between particles

        Location current = start.clone();
        for (double d = 0; d < distance; d += 0.5) {
            world.spawnParticle(Particle.DUST, current, 1, 0, 0, 0, 0,particle);
            current.add(step);
        }
    }
}
