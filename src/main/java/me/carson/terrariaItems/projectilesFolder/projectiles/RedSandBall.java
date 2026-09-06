package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class RedSandBall extends Projectile {

    public RedSandBall(Plugin plugin) {
        super(plugin, 1,  "red_sand_ball", "RedSandBall",5,0, DamageType.ARROW,null,0);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {
        Block above = block.getRelative(BlockFace.UP);
        if(above.getType()== Material.AIR){
            above.setType(Material.RED_SAND);
        }
    }

}
