package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CausticBeam extends Projectile {

    public CausticBeam(Plugin plugin) {
        super(plugin, 0,  "caustic_beam", "CausticBeam",0,5, DamageType.PLAYER_ATTACK,null);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON,50,1,false,true,true));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,30,0,false,true,true));
    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
