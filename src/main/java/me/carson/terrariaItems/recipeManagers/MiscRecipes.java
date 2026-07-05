package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.handlers.CustomRecipeDiscoverManager;
import me.carson.terrariaItems.handlers.CustomRecipeManager;
import me.carson.terrariaItems.materialsFolder.materials.DemoniteBar;
import me.carson.terrariaItems.materialsFolder.materials.HallowedBar;
import me.carson.terrariaItems.miscFolder.BasicItems.*;
import me.carson.terrariaItems.miscFolder.fishingRods.FisherOfSouls;
import me.carson.terrariaItems.miscFolder.fishingRods.GoldenFishingRod;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.Plugin;

public class MiscRecipes {

    private final Plugin plugin;
    private final CustomRecipeManager recipeManager;

    public MiscRecipes(Plugin plugin, CustomRecipeManager recipeManager) {
        this.plugin = plugin;
        this.recipeManager = recipeManager;
    }

    public void registerRecipes() {
        registerPickaxeAxeRecipe();
        registerFisherOfSoulsRecipe();
        registerGoldenFishingRodRecipe();
    }

    private void registerGoldenFishingRodRecipe(){
        ItemStack item= GoldenFishingRod.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_GoldenFishingRod");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("  H"," HS","H S");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.STRING)));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(HallowedBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerFisherOfSoulsRecipe(){
        ItemStack item= FisherOfSouls.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_FisherOfSouls");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("  D"," DS","D S");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.STRING)));
        recipe.setIngredient('D', new RecipeChoice.ExactChoice( DemoniteBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerPickaxeAxeRecipe(){
        ItemStack axe= PickaxeAxe.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_PickaxeAxe");
        ShapedRecipe recipe = new ShapedRecipe(key, axe);
        recipe.shape("HHH","HS "," S ");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.STICK)));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }
}
