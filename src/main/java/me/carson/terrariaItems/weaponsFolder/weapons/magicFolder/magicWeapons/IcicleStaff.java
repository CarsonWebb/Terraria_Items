package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.Icicle;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;

import java.util.UUID;

public class IcicleStaff extends Magic {

    public IcicleStaff(Plugin plugin) {
        super(plugin,"icicle_staff.name","#9696FF", Material.LIGHT_BLUE_DYE,"icicle_staff","IcicleStaff",0,1.25f,8,4,50,6,"icicle_staff.lore");
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        RayTraceResult result= player.getWorld().rayTrace(player.getEyeLocation(),player.getEyeLocation().getDirection(),150, FluidCollisionMode.NEVER,true,0.1, e -> (e!=player));
        if (result == null) {return;}
        Location hit=result.getHitPosition().toLocation(player.getWorld());

        if(manaManagerInstance.useMana(player,cost)){
            new Icicle(plugin).createFallingProjectile(player,speed,damage,spread,duration,25,hit);
            player.getWorld().playSound(player.getLocation(), "terraria:rod_of_discord_use", 1.0F, 1.0F);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new IcicleStaff(plugin).createItem();
    }

}
