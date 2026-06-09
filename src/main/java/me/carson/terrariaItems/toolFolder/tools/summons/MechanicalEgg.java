package me.carson.terrariaItems.toolFolder.tools.summons;

import me.carson.terrariaItems.bossFolder.bosses.MechanicalDragon;
import me.carson.terrariaItems.bossFolder.bosses.MechanicalWarden;
import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MechanicalEgg extends Tool {

    private final NamespacedKey key = new NamespacedKey(plugin, "BossDragon");

    public MechanicalEgg(Plugin plugin){
        super(plugin,"mechanical_egg.name","#FFC896", Material.LIGHT_GRAY_DYE,"mechanical_egg","MechanicalEgg",10,"mechanical_egg.lore");
    }

    @Override
    public void rightActivate(Player player) {
        World world= player.getWorld();
        if(player.getLocation().getBlock().getBiome()!= Biome.THE_END){
            player.sendMessage(ChatColor.RED + "This Boss must be summoned in the Central End Island");
            return;
        }
        for (Entity entity : world.getEntities()) {
            if(entity.getPersistentDataContainer().has(key)){
                player.sendMessage(ChatColor.RED + "There is already one of these bosses active");
                return;
            }
        }
        new MechanicalDragon(plugin).summonBoss(player);
        player.getInventory().removeItem(MechanicalEgg.getItem(plugin));
        player.getWorld().playSound(player.getLocation(), "terraria:boss_spawn", 1.0F, 1.0F);
    }

    @Override
    public void cooldownEffect(Player player) {
        //N/A
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MechanicalEgg(plugin).createItem();
    }

}
