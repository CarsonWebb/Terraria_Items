package me.carson.terrariaItems.toolFolder.tools.summons;

import me.carson.terrariaItems.bossFolder.bosses.MechanicalWarden;
import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class MechanicalShrieker extends Tool {

    private final NamespacedKey key = new NamespacedKey(plugin, "BossWarden");

    public MechanicalShrieker(Plugin plugin){
        super(plugin,"mechanical_shrieker.name","#FFC896", Material.LIGHT_GRAY_DYE,"mechanical_shrieker","MechanicalShrieker",10,"mechanical_shrieker.lore");
    }

    @Override
    public void rightActivate(Player player) {
        World world= player.getWorld();
        if(world.getEnvironment() != World.Environment.NORMAL){
            player.sendMessage(ChatColor.RED + "This Boss must be summoned in the Overworld");
            return;
        }
        for (Entity entity : world.getEntities()) {
            if(entity.getPersistentDataContainer().has(key)){
                player.sendMessage(ChatColor.RED + "There is already one of these bosses active");
                return;
            }
        }
        new MechanicalWarden(plugin).summonBoss(player);
        player.getInventory().removeItem(MechanicalShrieker.getItem(plugin));
        player.getWorld().playSound(player.getLocation(), "terraria:boss_spawn", 1.0F, 1.0F);
    }

    @Override
    public void cooldownEffect(Player player) {
        //N/A
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MechanicalShrieker(plugin).createItem();
    }

}
