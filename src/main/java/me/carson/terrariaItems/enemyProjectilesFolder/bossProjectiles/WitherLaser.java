package me.carson.terrariaItems.enemyProjectilesFolder.bossProjectiles;

import me.carson.terrariaItems.enemyProjectilesFolder.EnemyProjectile;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WitherLaser extends EnemyProjectile {

    public WitherLaser(Plugin plugin) {
        super(plugin, 20,0,  "wither_laser", "WitherLaser",0,0, DamageType.GENERIC,null);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 1), true);
    }

    @Override
    public void hitBlockEffect(Block block) {
        Block above = block.getRelative(BlockFace.UP);
        if(above.getType()== Material.AIR){
            above.setType(Material.SOUL_FIRE);
        }
    }

}
