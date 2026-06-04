package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.AmethystBolt;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.List;

public class LifeDrain extends Magic {

    List<Entity> enemiesList;
    DamageSource source;
    Particle.DustOptions particle=new Particle.DustOptions(Color.fromRGB(136, 30, 27), 0.5f);

    public LifeDrain(Plugin plugin) {
        super(plugin,"life_drain.name","#FF96FF", Material.AMETHYST_SHARD,"life_drain","LifeDrain",5,0,4,0,0,10,"life_drain.lore");
    }

    @Override
    public void rightActivate(Player player) {
        source=DamageSource.builder(DamageType.LIGHTNING_BOLT).withCausingEntity(player).withDirectEntity(player).build();
        if(manaManagerInstance.useMana(player,cost)){
            enemiesList=raycastCone(player,15,15,20);
            if(!enemiesList.isEmpty()){
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,40,1,false,false,false));
            }
            for(Entity entity:enemiesList){
                if(entity instanceof LivingEntity livingEntity){
                    livingEntity.damage(damage,source);
                    drawChain(player,livingEntity);
                }
            }
        }
    }

    private void drawChain(Player player, LivingEntity target) {
        Location start = player.getEyeLocation();

        start.add(0, -0.6, 0);
        Vector right = start.getDirection().crossProduct(new Vector(0, 1, 0)).normalize();
        start.add(right.multiply(0.3));

        Location end = target.getLocation().add(target.getEyeLocation()).multiply(0.5);

        Vector between = end.toVector().subtract(start.toVector());
        double distance = between.length();
        Vector step = between.normalize().multiply(0.5); // 0.5 = gap between particles

        Location current = start.clone();
        for (double d = 0; d < distance; d += 0.5) {
            player.getWorld().spawnParticle(Particle.DUST, current, 1, 0, 0, 0, 0,particle);
            current.add(step);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new LifeDrain(plugin).createItem();
    }

}
