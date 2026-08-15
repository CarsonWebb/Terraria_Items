package me.carson.terrariaItems.accesoryFolder;

import me.carson.terrariaItems.TILangManager;
import me.carson.terrariaItems.handlers.ManaManager;
import me.carson.terrariaItems.handlers.PlayerDataHandler;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public abstract class Accessory {

    protected final Plugin plugin;
    protected final String name;
    protected final String rarity;
    protected final Material baseMaterial;
    protected final String texture;
    protected final String id;
    protected final String lore;
    private final NamespacedKey customItemKey;
    private final NamespacedKey unplaceableKey;
    public final PlayerDataHandler playerInstance=PlayerDataHandler.getInstance();
    public final ManaManager manaManagerInstance= ManaManager.getInstance();
    public final TILangManager lang =TILangManager.getInstance();

    public final Set<EntityDamageEvent.DamageCause> COUNTERSCARF_CAUSES = Set.of(
            EntityDamageEvent.DamageCause.ENTITY_ATTACK,
            EntityDamageEvent.DamageCause.PROJECTILE,
            EntityDamageEvent.DamageCause.ENTITY_EXPLOSION,
            EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK,
            EntityDamageEvent.DamageCause.LIGHTNING,
            EntityDamageEvent.DamageCause.MAGIC,
            EntityDamageEvent.DamageCause.SONIC_BOOM,
            EntityDamageEvent.DamageCause.DRAGON_BREATH
    );

    public final Set<EntityDamageEvent.DamageCause> OBSIDIAN_SKULL_DAMAGE = Set.of(
            EntityDamageEvent.DamageCause.FIRE,
            EntityDamageEvent.DamageCause.CAMPFIRE,
            EntityDamageEvent.DamageCause.HOT_FLOOR,
            EntityDamageEvent.DamageCause.FIRE_TICK
    );

    public final Set<PotionEffectType> ANKH_CHARM_EFFECTS= Set.of(
            PotionEffectType.POISON,
            PotionEffectType.SLOWNESS,
            PotionEffectType.BLINDNESS,
            PotionEffectType.DARKNESS,
            PotionEffectType.WEAKNESS
    );

    public Accessory(Plugin plugin, String name, String rarity, Material baseMaterial, String texture, String id, String lore){
        this.plugin = plugin;
        this.name = name;
        this.rarity = rarity;
        this.baseMaterial = baseMaterial;
        this.texture = texture;
        this.id = id;
        customItemKey=new NamespacedKey(plugin, "customItem");
        unplaceableKey=new NamespacedKey(plugin, "unplaceable");
        this.lore = lore;
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(baseMaterial);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(net.md_5.bungee.api.ChatColor.of(rarity)+lang.get("accessories",name));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ArrayList<String> newLore=new ArrayList<>(lang.getList("accessories",lore));
        newLore.add(lang.get("accessories","accessory_msg"));
        meta.setLore(newLore);
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.setItemModel(new NamespacedKey("terraria",texture));
        meta.getPersistentDataContainer().set(customItemKey, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(unplaceableKey, PersistentDataType.BYTE, (byte) 1);
        meta.setMaxStackSize(Integer.valueOf(1));
        item.setItemMeta(meta);
        return item;
    }

    public abstract void activateEffect(Player player);

    public abstract void deactivateEffect(Player player);

    public abstract void onPlayerHit(Player player, EntityDamageEvent event);

    public abstract void onPlayerEffect(Player player, EntityPotionEffectEvent event);
}
