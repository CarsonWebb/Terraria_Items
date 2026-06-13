package me.carson.terrariaItems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArmorChangeDetector implements Listener {

    // Armor slot indices in the player's inventory
    //private static final int HELMET_SLOT     = 39;
    //private static final int CHESTPLATE_SLOT = 38;
    //private static final int LEGGINGS_SLOT   = 37;
    //private static final int BOOTS_SLOT      = 36;
    private final Plugin plugin;

    private final Set<EquipmentSlot> armorSlots = new HashSet<>(Arrays.asList(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET));

    public ArmorChangeDetector(Plugin plugin){
        this.plugin=plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        if (event.getSlotType() == InventoryType.SlotType.ARMOR) {

            EquipmentSlot equipSlot = rawSlotToArmorSlot(event.getSlot());
            ItemStack newItem=event.getCursor();
            ItemStack oldItem=event.getCurrentItem();

            if(!armorSlots.contains(equipSlot)){return;}

            if(newItem.getType()==Material.AIR||oldItem.getType()==Material.AIR){
                if (newItem.getType().getEquipmentSlot()==equipSlot||oldItem.getType().getEquipmentSlot()==equipSlot){
                    fireAndCancel(event, player, equipSlot, oldItem, newItem);
                    return;
                }
            }else{
                if (newItem.getType().getEquipmentSlot()==equipSlot&&oldItem.getType().getEquipmentSlot()==equipSlot){
                    fireAndCancel(event, player, equipSlot, oldItem, newItem);
                    return;
                }
            }
        }

        if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
            ItemStack item = event.getCurrentItem();
            if (item == null) return;
            if (event.getClickedInventory() == null) return;
            if (event.getView().getTopInventory().getType() != InventoryType.CRAFTING) return;

            EquipmentSlot targetSlot = getArmorSlotForItem(item);
            if (targetSlot == null) return;

            ItemStack currentlyWearing = getArmorInSlot(player.getInventory(), targetSlot);
            ItemStack oldItem = nullToAir(currentlyWearing);
            if(oldItem.getType()!=Material.AIR){return;}
            ItemStack newItem = item.clone();

            fireAndCancel(event, player, targetSlot, oldItem, newItem);
            return;
        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        EquipmentSlot slot=event.getItemDrop().getItemStack().getType().getEquipmentSlot();
        if(!armorSlots.contains(slot)){return;}
        fireAndCancel(event, event.getPlayer(), slot, event.getItemDrop().getItemStack(), null);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL || event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) return;
        if (!event.hasItem()) return;

        Player player = event.getPlayer();
        final ItemStack newItem = event.getHand() == EquipmentSlot.HAND ? player.getEquipment().getItemInMainHand() : player.getEquipment().getItemInOffHand();
        if (!isNotNullOrAir(newItem) || newItem.getType() == Material.CARVED_PUMPKIN)
            return;
        EquipmentSlot targetSlot = newItem.getType().getEquipmentSlot();
        if (!armorSlots.contains(targetSlot))
            return;
        ItemStack oldItem = player.getEquipment().getItem(targetSlot);
        fireAndCancel(event, player, targetSlot, oldItem, newItem);
    }

    @EventHandler
    private void onBlockDispenseArmor(BlockDispenseArmorEvent event) {
        if (event.getTargetEntity() instanceof Player player) {
            fireAndCancel(event, player, event.getItem().getType().getEquipmentSlot(), null, event.getItem());
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        for (int slot : event.getRawSlots()) {
            if (isArmorSlot(slot)) {
                ItemStack oldItem = event.getOldCursor();
                ItemStack newItem = event.getNewItems().get(slot);
                fireAndCancel(event, player, rawSlotToArmorSlot(slot), oldItem, newItem);
            }
        }
    }

    private void fireAndCancel(Cancellable trigger, Player player, EquipmentSlot slot, ItemStack oldItem, ItemStack newItem) {
        ArmorChangeEvent armorEvent = new ArmorChangeEvent(player, slot, oldItem, newItem);
        player.getServer().getPluginManager().callEvent(armorEvent);

        if (armorEvent.isCancelled()) {
            trigger.setCancelled(true);
        }
    }

    private boolean isArmorSlot(int rawSlot) {
        return rawSlot >= 36 && rawSlot <= 39;
    }

    private ItemStack nullToAir(ItemStack item) {
        return (item == null) ? new ItemStack(Material.AIR) : item;
    }

    private EquipmentSlot rawSlotToArmorSlot(int rawSlot) {
        return switch (rawSlot) {
            case 39 -> EquipmentSlot.HEAD;
            case 38 -> EquipmentSlot.CHEST;
            case 37 -> EquipmentSlot.LEGS;
            case 36 -> EquipmentSlot.FEET;
            default -> null;
        };
    }

    private EquipmentSlot getArmorSlotForItem(ItemStack item) {
        if (item == null||!item.getItemMeta().hasEquippable()) return null;
        if (item.getItemMeta().getEquippable().getSlot()== EquipmentSlot.HEAD)
            return EquipmentSlot.HEAD;
        if (item.getItemMeta().getEquippable().getSlot()== EquipmentSlot.CHEST)
            return EquipmentSlot.CHEST;
        if (item.getItemMeta().getEquippable().getSlot()== EquipmentSlot.LEGS)
            return EquipmentSlot.LEGS;
        if (item.getItemMeta().getEquippable().getSlot()== EquipmentSlot.FEET)
            return EquipmentSlot.FEET;
        return null;
    }

    private ItemStack getArmorInSlot(PlayerInventory inv, EquipmentSlot slot) {
        return switch (slot) {
            case HEAD     -> inv.getHelmet();
            case CHEST -> inv.getChestplate();
            case LEGS   -> inv.getLeggings();
            case FEET      -> inv.getBoots();
            case HAND, SADDLE, BODY, OFF_HAND -> null;
        };
    }

    private boolean isNotNullOrAir(ItemStack item) {
        return item == null ? false : item.getType() != Material.AIR;
    }
}