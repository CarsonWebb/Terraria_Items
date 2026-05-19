package me.carson.terrariaItems.projectilesFolder.rougeProjectiles;

import me.carson.terrariaItems.projectilesFolder.RougeProjectiles;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ConsecratedFlameProjectile extends RougeProjectiles {

    public ConsecratedFlameProjectile(Plugin plugin) {
        super(plugin, 10,  "consecrated_flame", "ConsecratedFlameProjectile",0,0, DamageType.TRIDENT,null);
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

}
