package me.carson.terrariaItems.listeners;

import me.carson.terrariaItems.handlers.CustomRecipeManager;
import me.carson.terrariaItems.handlers.WorldDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.*;

public class RecipeValidationListener implements Listener {

    private final CustomRecipeManager recipeManager;
    private final WorldDataHandler worldDataHandler=WorldDataHandler.getInstance();

    public RecipeValidationListener(CustomRecipeManager recipeManager) {
        this.recipeManager = recipeManager;
    }

    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        CraftingInventory inv = event.getInventory();
        ItemStack[] matrix = inv.getMatrix();

        Recipe eventRecipe = event.getRecipe();
        boolean isOurRecipe = eventRecipe instanceof Keyed keyed && recipeManager.isOurRecipe(keyed.getKey());

        ItemStack resolved = null;
        NamespacedKey key = null;

        List<String> shapelessIds = new ArrayList<>();
        for (ItemStack item : matrix) {
            if (item == null || item.getType().isAir()) continue;
            String id = item.getItemMeta() != null
                    ? item.getItemMeta().getPersistentDataContainer().get(recipeManager.getIdKey(),
                    org.bukkit.persistence.PersistentDataType.STRING)
                    : null;
            shapelessIds.add(id != null ? "custom:" + id : "material:" + item.getType());
        }
        Collections.sort(shapelessIds);
        resolved = recipeManager.resolveShapeless(shapelessIds);
        key = recipeManager.getResolvedShapelessKey(shapelessIds);

        if (resolved == null && recipeManager.hasAnyShapedSignatures()) {
            int size = matrix.length;
            int cols = (size == 4) ? 2 : 3;
            int rows = size / cols;
            List<String> grid = recipeManager.normalizedGridFromMatrix(matrix, rows, cols);
            resolved = recipeManager.resolveShaped(grid);
            key = recipeManager.getResolvedShapedKey(grid);
        }
        if (resolved == null || key == null) {
            boolean hasCustomItem = shapelessIds.stream().anyMatch(s -> s.startsWith("custom:"));
            if (hasCustomItem || isOurRecipe) {
                event.getInventory().setResult(null);
            }
            return;
        }

        if (!isAllowed(getPrefix(key.getKey()))) {
            event.getInventory().setResult(null);
        } else {
            event.getInventory().setResult(resolved);
        }
    }

    public Boolean isAllowed(String prefix){
        if(!worldDataHandler.getPreHardmodeEnabled()){
            if(Objects.equals(prefix, "pre")){
                return false;
            }
        }
        if(!worldDataHandler.getHardmodeEnabled()){
            if(Objects.equals(prefix, "hm")){
                return false;
            }
        }
        return true;
    }

    public String getPrefix(String key){
        if(key==null){return "";}
        int idx = key.indexOf('_');
        return idx != -1 ? key.substring(0, idx) : "";
    }
}