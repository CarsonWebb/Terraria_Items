package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.MagicDaggerProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class MagicDagger extends Magic {

    public MagicDagger(Plugin plugin) {
        super(plugin,"magic_dagger.name","#FF9696", Material.BLAZE_ROD,"magic_dagger","MagicDagger",4,1.5f,9,0.005f,100,6,"magic_dagger.lore");
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if(manaManagerInstance.useMana(player,cost)){
            new MagicDaggerProjectile(plugin).createGravProjectile(player,speed,damage,spread,duration,20,0.1f);
            player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 0.75F, 1.0F);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MagicDagger(plugin).createItem();
    }

}
