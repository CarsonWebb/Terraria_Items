package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.handlers.CustomRecipeDiscoverManager;
import me.carson.terrariaItems.handlers.CustomRecipeManager;
import me.carson.terrariaItems.materialsFolder.materials.*;
import me.carson.terrariaItems.materialsFolder.materials.bullets.BubonicRound;
import me.carson.terrariaItems.materialsFolder.materials.bullets.EmptyBullet;
import me.carson.terrariaItems.materialsFolder.materials.bullets.ExplodingBullet;
import me.carson.terrariaItems.materialsFolder.materials.bullets.MusketBall;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfFright;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfMight;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfSight;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.Plugin;

public class MaterialRecipes {

    private final Plugin plugin;
    private final CustomRecipeManager recipeManager;

    public MaterialRecipes(Plugin plugin, CustomRecipeManager recipeManager) {
        this.plugin = plugin;
        this.recipeManager = recipeManager;
    }

    public void registerRecipes() {
        registerDemoniteBarRecipe();
        registerHellstoneRecipe();
        registerRubyRecipe();
        registerHellstoneBarRecipe();
        registerHallowedBarRecipe();
        registerMusketBallRecipe();
        registerEmptyBulletRecipe();
        registerExplodingBulletRecipe();
        registerBubonicRoundRecipe();
        registerUnholyCoreRecipe();
    }

    private void registerUnholyCoreRecipe(){
        ItemStack item=UnholyCore.getItem(plugin);
        item.setAmount(2);
        NamespacedKey key = new NamespacedKey(plugin, "hm_UnholyCore");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(Hellstone.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.POPPED_CHORUS_FRUIT)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerDemoniteBarRecipe(){
        ItemStack demoniteBar=DemoniteBar.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_DemoniteBar");
        ShapedRecipe recipe = new ShapedRecipe(key, demoniteBar);
        recipe.shape(" S ","SIS"," S ");
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.SOUL_SAND)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerHellstoneRecipe(){
        ItemStack hellstone=Hellstone.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_Hellstone");
        ShapelessRecipe recipe = new ShapelessRecipe(key, hellstone);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.NETHERITE_SCRAP)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.LAVA_BUCKET)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.OBSIDIAN)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerRubyRecipe(){
        ItemStack ruby=Ruby.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_Ruby");
        ShapedRecipe recipe = new ShapedRecipe(key,ruby);
        recipe.shape("RRR","RDR","RRR");
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(new ItemStack(Material.REDSTONE)));
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerHellstoneBarRecipe(){
        ItemStack bar=HellstoneBar.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HellstoneBar");
        FurnaceRecipe recipe = new FurnaceRecipe(
                key,
                bar, // Result
                Material.MAGMA_BLOCK,                   // Ingredient (simple)
                0.35f,                               // XP
                50                                  // Cook time (10s)
        );
        recipeManager.register(recipe);
    }

    private void registerHallowedBarRecipe(){
        ItemStack hallow=HallowedBar.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_HallowedBar");
        ShapelessRecipe recipe = new ShapelessRecipe(key, hallow);
        recipe.addIngredient(new RecipeChoice.ExactChoice( SoulOfFright.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice( SoulOfSight.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice( SoulOfMight.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerMusketBallRecipe(){
        ItemStack bullet= MusketBall.getItem(plugin);
        bullet.setAmount(9);
        NamespacedKey key = new NamespacedKey(plugin, "pre_MusketBall");
        ShapelessRecipe recipe = new ShapelessRecipe(key, bullet);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_NUGGET)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerEmptyBulletRecipe(){
        ItemStack item= EmptyBullet.getItem(plugin);
        item.setAmount(8);
        NamespacedKey key = new NamespacedKey(plugin, "pre_EmptyBullet");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ","I I"," I ");
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_NUGGET)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerExplodingBulletRecipe(){
        ItemStack item= ExplodingBullet.getItem(plugin);
        item.setAmount(50);
        RecipeChoice choice = new RecipeChoice.ExactChoice(EmptyBullet.getItem(plugin));
        NamespacedKey key = new NamespacedKey(plugin, "pre_ExplodingBullet");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        for(int i=0;i<8;i++){
            recipe.addIngredient(choice);
        }
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.GUNPOWDER)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerBubonicRoundRecipe(){
        ItemStack item= BubonicRound.getItem(plugin);
        item.setAmount(50);
        RecipeChoice choice = new RecipeChoice.ExactChoice(EmptyBullet.getItem(plugin));
        NamespacedKey key = new NamespacedKey(plugin, "pre_BubonicRound");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        for(int i=0;i<8;i++){
            recipe.addIngredient(choice);
        }
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.WITHER_ROSE)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }


}
