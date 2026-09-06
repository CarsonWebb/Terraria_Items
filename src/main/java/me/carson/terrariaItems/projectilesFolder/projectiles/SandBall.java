package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SandBall extends Projectile {

    public SandBall(Plugin plugin) {
        super(plugin, 0,  "sand_ball", "SandBall",0,0, DamageType.ARROW,null,0);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {
        Block above = block.getRelative(BlockFace.UP);
        if(above.getType()== Material.AIR){
            above.setType(Material.SAND);
        }
    }

}
