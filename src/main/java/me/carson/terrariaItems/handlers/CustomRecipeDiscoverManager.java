package me.carson.terrariaItems.handlers;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class CustomRecipeDiscoverManager implements Listener {

    public interface RecipeProvider {
        void registerRecipes(CustomRecipeDiscoverManager manager);
    }

    private record ExactEntry(ItemStack stack, NamespacedKey key) {}
    private static CustomRecipeDiscoverManager instance;
    private final JavaPlugin plugin;
    private final List<NamespacedKey> allKeys = new ArrayList<>();
    private final Map<Material, List<NamespacedKey>> materialIndex = new HashMap<>();
    private final Map<Material, List<ExactEntry>> exactIndex = new HashMap<>();


    public CustomRecipeDiscoverManager(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public NamespacedKey key(String id) {
        return new NamespacedKey(plugin, id);
    }

    public void freeze() {
        allKeys.clear();
        materialIndex.clear();
        exactIndex.clear();

        String ns = plugin.getName().toLowerCase();
        Iterator<Recipe> it = Bukkit.recipeIterator();

        while (it.hasNext()) {
            Recipe recipe = it.next();
            if (!(recipe instanceof Keyed keyed)) continue;
            if (!keyed.getKey().getNamespace().equals(ns)) continue;

            allKeys.add(keyed.getKey());

            if (recipe instanceof ShapedRecipe sr) {
                for (RecipeChoice choice : sr.getChoiceMap().values()) {
                    if (choice != null) indexChoice(choice, keyed.getKey());
                }
            } else if (recipe instanceof ShapelessRecipe slr) {
                for (RecipeChoice choice : slr.getChoiceList()) {
                    indexChoice(choice, keyed.getKey());
                }
            }
        }
    }

    private void indexChoice(RecipeChoice choice, NamespacedKey recipeKey) {
        if (choice instanceof RecipeChoice.MaterialChoice mc) {
            for (Material mat : mc.getChoices()) {
                materialIndex
                        .computeIfAbsent(mat, k -> new ArrayList<>())
                        .add(recipeKey);
            }
        } else if (choice instanceof RecipeChoice.ExactChoice ec) {
            for (ItemStack is : ec.getChoices()) {
                exactIndex
                        .computeIfAbsent(is.getType(), k -> new ArrayList<>())
                        .add(new ExactEntry(is.clone(), recipeKey));
            }
        }
    }

    private void discoverByItem(Player player, ItemStack item) {
        Material type = item.getType();

        if (!materialIndex.containsKey(type) && !exactIndex.containsKey(type)) return;

        Set<NamespacedKey> toDiscover = new HashSet<>();

        List<NamespacedKey> byMaterial = materialIndex.getOrDefault(type, Collections.emptyList());
        toDiscover.addAll(byMaterial);

        List<ExactEntry> exactCandidates = exactIndex.getOrDefault(type, Collections.emptyList());
        for (ExactEntry entry : exactCandidates) {
            if (entry.stack().isSimilar(item)) {
                toDiscover.add(entry.key());
            }
        }

        for (NamespacedKey key : toDiscover) {
            if (!player.hasDiscoveredRecipe(key)) {
                player.discoverRecipe(key);
            }
        }
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;
        discoverByItem(player, e.getItem().getItemStack());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) return;
        if (e.getClickedInventory() == null) return;

        ItemStack item = null;

        switch (e.getAction()) {
            // Shift clicking from another inventory into player inventory
            case MOVE_TO_OTHER_INVENTORY -> {
                if (e.getClickedInventory().getType() != InventoryType.PLAYER) {
                    item = e.getCurrentItem();
                }
            }
            // Placing an item into a player inventory slot directly
            case PLACE_ALL, PLACE_ONE, PLACE_SOME -> {
                if (e.getClickedInventory().getType() == InventoryType.PLAYER) {
                    item = e.getCursor();
                }
            }
        }
        if (item != null && item.getType() != Material.AIR) {
            discoverByItem(player, item);
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) return;
        if (!(e.getRecipe() instanceof Keyed keyed)) return;
        if (!keyed.getKey().getNamespace().equals(plugin.getName().toLowerCase())) return;
        player.discoverRecipe(keyed.getKey());
    }

    public void discoverAll(Player player) {
        player.discoverRecipes(allKeys);
    }

    public void undiscoverAll(Player player) {
        player.undiscoverRecipes(allKeys);
    }

    public List<NamespacedKey> getAllKeys() {
        return Collections.unmodifiableList(allKeys);
    }

    public void unregisterAll() {
        for (NamespacedKey k : allKeys) {
            Bukkit.removeRecipe(k);
        }
        allKeys.clear();
        materialIndex.clear();
        exactIndex.clear();
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new CustomRecipeDiscoverManager(plugin);
    }

    public static CustomRecipeDiscoverManager getInstance() {
        return instance;
    }

}