package me.carson.terrariaItems.recipeManagers;

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

public class MiscRecipes implements CustomRecipeManager.RecipeProvider {

    private final Plugin plugin;

    public MiscRecipes(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerRecipes(CustomRecipeManager manager) {
        registerPickaxeAxeRecipe();
        registerFisherOfSoulsRecipe();
        registerGoldenFishingRodRecipe();
    }

    private void registerGoldenFishingRodRecipe(){
        ItemStack item= GoldenFishingRod.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "GoldenFishingRod");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("  H"," HS","H S");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.STRING)));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(HallowedBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerFisherOfSoulsRecipe(){
        ItemStack item= FisherOfSouls.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "FisherOfSouls");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("  D"," DS","D S");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.STRING)));
        recipe.setIngredient('D', new RecipeChoice.ExactChoice( DemoniteBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerPickaxeAxeRecipe(){
        ItemStack axe= PickaxeAxe.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "PickaxeAxe");
        ShapedRecipe recipe = new ShapedRecipe(key, axe);
        recipe.shape("HHH","HS "," S ");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.STICK)));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }
}
