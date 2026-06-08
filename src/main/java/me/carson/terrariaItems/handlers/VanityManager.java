package me.carson.terrariaItems.handlers;

import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityEquipment;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.Equipment;
import com.github.retrooper.packetevents.protocol.player.EquipmentSlot;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import me.carson.terrariaItems.listeners.ArmorChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class VanityManager implements Listener {

    private final NamespacedKey unmovableKey;
    private final PlayerDataHandler playerDataInstance = PlayerDataHandler.getInstance();
    private final Plugin plugin;
    private static VanityManager instance;

    public VanityManager(Plugin plugin) {
        this.plugin=plugin;
        unmovableKey = new NamespacedKey(plugin, "unmovable");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!event.getView().getTitle().equals("Vanity Inventory")) return;
        Player player = (Player) event.getPlayer();
        Inventory inv = event.getInventory();
        List<ItemStack> invList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            invList.add(i, inv.getItem(i + 2));
        }

        playerDataInstance.setVanity(player.getUniqueId(), invList);
        playerDataInstance.save();
        applyVanity(invList, player);
    }

    public void applyVanity(List<ItemStack> invList, Player target) {
        if (invList == null) return;

        List<Equipment> equipmentList = new ArrayList<>();

        if (invList.get(0) != null) {
            equipmentList.add(new Equipment(EquipmentSlot.HELMET,
                    SpigotConversionUtil.fromBukkitItemStack(invList.get(0))));
        }
        if (invList.get(1) != null) {
            equipmentList.add(new Equipment(EquipmentSlot.CHEST_PLATE,
                    SpigotConversionUtil.fromBukkitItemStack(invList.get(1))));
        }
        if (invList.get(2) != null) {
            equipmentList.add(new Equipment(EquipmentSlot.LEGGINGS,
                    SpigotConversionUtil.fromBukkitItemStack(invList.get(2))));
        }
        if (invList.get(3) != null) {
            equipmentList.add(new Equipment(EquipmentSlot.BOOTS,
                    SpigotConversionUtil.fromBukkitItemStack(invList.get(3))));
        }

        if (equipmentList.isEmpty()) return;

        WrapperPlayServerEntityEquipment packet =
                new WrapperPlayServerEntityEquipment(target.getEntityId(), equipmentList);

        for (Player viewer : Bukkit.getOnlinePlayers()) {
            PacketEvents.getAPI().getPlayerManager().sendPacket(viewer, packet);
        }
    }

    public void openVanity(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "Vanity Inventory");

        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§r");
        meta.getPersistentDataContainer().set(unmovableKey, PersistentDataType.BYTE, (byte) 1);
        item.setItemMeta(meta);

        gui.setItem(0, item);
        gui.setItem(1, item);
        gui.setItem(6, item);
        gui.setItem(7, item);
        gui.setItem(8, item);

        List<ItemStack> invList = playerDataInstance.getVanity(player.getUniqueId());
        if (invList != null) {
            for (int i = 0; i < 4; i++) {
                gui.setItem(i + 2, invList.get(i));
            }
        }
        player.openInventory(gui);
    }

    @EventHandler
    public void onVanityClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Vanity Inventory")) return;
        ItemStack clicked = event.getCurrentItem();
        if (clicked == null) return;
        ItemMeta clickedMeta = clicked.getItemMeta();

        if (clickedMeta.getPersistentDataContainer().has(unmovableKey)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onArmorEquip(ArmorChangeEvent event) {
        Player player = event.getPlayer();
        List<ItemStack> invList = playerDataInstance.getVanity(player.getUniqueId());
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            applyVanity(invList, player);
        }, 1);
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new VanityManager(plugin);
    }

    public static VanityManager getInstance() {
        return instance;
    }
}
