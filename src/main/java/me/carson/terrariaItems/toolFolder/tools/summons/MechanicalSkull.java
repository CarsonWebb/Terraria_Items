package me.carson.terrariaItems.toolFolder.tools.summons;

import me.carson.terrariaItems.bossFolder.bosses.MechanicalWither;
import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MechanicalSkull extends Tool {

    private final NamespacedKey key = new NamespacedKey(plugin, "BossWither");

    public MechanicalSkull(Plugin plugin){
        super(plugin,"mechanical_skull.name","#FFC896", Material.LIGHT_GRAY_DYE,"mechanical_skull","MechanicalSkull",10,"mechanical_skull.lore");
    }

    @Override
    public void rightActivate(Player player) {
        World world= player.getWorld();
        if(world.getEnvironment() != World.Environment.NETHER){
            player.sendMessage(ChatColor.RED + "This Boss must be summoned in the Nether");
            return;
        }
        for (Entity entity : world.getEntities()) {
            if(entity.getPersistentDataContainer().has(key)){
                player.sendMessage(ChatColor.RED + "There is already one of these bosses active");
                return;
            }
        }
        new MechanicalWither(plugin).summonBoss(player);
        player.getInventory().removeItem(MechanicalSkull.getItem(plugin));
        player.getWorld().playSound(player.getLocation(), "terraria:boss_spawn", 1.0F, 1.0F);
    }

    @Override
    public void cooldownEffect(Player player) {
        //N/A
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MechanicalSkull(plugin).createItem();
    }

}
