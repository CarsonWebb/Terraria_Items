package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class VampireKnife extends Projectile {

    public VampireKnife(Plugin plugin) {
        super(plugin, 0,  "vampire_knife", "VampireKnife",0,0, DamageType.PLAYER_ATTACK,null);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        player.setHealth(Math.min(player.getHealth()+0.5,player.getMaxHealth()));
    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
