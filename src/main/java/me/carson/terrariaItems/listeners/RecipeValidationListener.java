package me.carson.terrariaItems.listeners;

import me.carson.terrariaItems.handlers.CustomRecipeManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeValidationListener implements Listener {

    private final CustomRecipeManager recipeManager;

    public RecipeValidationListener(CustomRecipeManager recipeManager) {
        this.recipeManager = recipeManager;
    }

    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        CraftingInventory inv = event.getInventory();
        ItemStack[] matrix = inv.getMatrix();

        boolean hasCustomItem = false;
        for (ItemStack item : matrix) {
            if (item == null || item.getType().isAir()) continue;
            if (item.getItemMeta() != null
                    && item.getItemMeta().getPersistentDataContainer().has(recipeManager.getIdKey())) {
                hasCustomItem = true;
                break;
            }
        }
        if (!hasCustomItem) return;

        Recipe recipe = event.getRecipe();
        boolean looksShaped = recipe instanceof ShapedRecipe;
        boolean looksShapeless = recipe instanceof ShapelessRecipe;

        ItemStack resolved = null;

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

        if (resolved == null && recipeManager.hasAnyShapedSignatures()) {
            int rows = 3, cols = 3;
            List<String> grid = recipeManager.normalizedGridFromMatrix(matrix, rows, cols);
            resolved = recipeManager.resolveShaped(grid);
        }

        event.getInventory().setResult(resolved);
    }
}