package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.blocksFolder.blocks.Hellforge;
import me.carson.terrariaItems.handlers.CustomRecipeDiscoverManager;
import me.carson.terrariaItems.handlers.CustomRecipeManager;
import me.carson.terrariaItems.materialsFolder.materials.Hellstone;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.Plugin;

public class BlocksRecipes {

    private final Plugin plugin;
    private final CustomRecipeManager recipeManager;

    public BlocksRecipes(Plugin plugin, CustomRecipeManager recipeManager) {
        this.plugin = plugin;
        this.recipeManager = recipeManager;
    }


    public void registerRecipes() {
        registerHellforgeRecipe();
    }

    private void registerHellforgeRecipe(){
        ItemStack forge=Hellforge.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_Hellforge");
        ShapedRecipe recipe = new ShapedRecipe(key, forge);
        recipe.shape(" M ","MFM"," M ");
        recipe.setIngredient('M', new RecipeChoice.ExactChoice( Hellstone.getItem(plugin)));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(new ItemStack(Material.FURNACE)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }
}
