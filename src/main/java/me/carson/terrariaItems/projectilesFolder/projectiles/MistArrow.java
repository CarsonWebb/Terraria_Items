package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Snow;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MistArrow extends Projectile {

    public MistArrow(Plugin plugin) {
        super(plugin, 0,  "mist_arrow", "MistArrow",0,0, DamageType.ARROW,new Particle.DustOptions(Color.fromRGB(214, 249, 251), 1f));
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        entity.setFreezeTicks(400);
    }

    @Override
    public void hitBlockEffect(Block block) {
        Block above = block.getRelative(BlockFace.UP);
        if(above.getType()== Material.AIR){
            Snow snow = (Snow) Bukkit.createBlockData(Material.SNOW);
            snow.setLayers(1);
            above.setBlockData(snow);
        }
    }

}
