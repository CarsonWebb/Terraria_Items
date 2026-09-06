package me.carson.terrariaItems.projectilesFolder.projectiles.notes;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Note2 extends Projectile {

    public Note2(Plugin plugin) {
        super(plugin, 0,  "note_2", "Note2",99,99, DamageType.LIGHTNING_BOLT,null,0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
