package me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.melee;

import me.carson.terrariaItems.projectilesFolder.projectiles.BrimlashBlade;
import me.carson.terrariaItems.projectilesFolder.projectiles.Leaf;
import me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.Sword;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class Brimlash extends Sword {

    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();

    public Brimlash(Plugin plugin) {
        super(plugin,"brimlash.name","#FFC896",Material.NETHERITE_SWORD,"brimlash","Brimlash",0,1.5f,10,0,60,"brimlash.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new Brimlash(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),9, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void leftActivate(Player player) {
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 1.0F, 1.0F);

        long currentTime = System.currentTimeMillis();
        long lastTime = lastClickTime.getOrDefault(player.getUniqueId(), 0L);

        if (currentTime - lastTime < 750) {
            return;
        }
        lastClickTime.put(player.getUniqueId(), currentTime);

        new BrimlashBlade(plugin).createBrimlashBladeProjectile(player,speed,damage,duration);
    }

    @Override
    public void rightActivate(Player player) {

    }
}
