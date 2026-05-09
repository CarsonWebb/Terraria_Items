package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.handlers.CustomRecipeManager;
import me.carson.terrariaItems.materialsFolder.materials.FallenStar;
import me.carson.terrariaItems.materialsFolder.materials.Ruby;
import me.carson.terrariaItems.materialsFolder.materials.souls.*;
import me.carson.terrariaItems.toolFolder.tools.*;
import me.carson.terrariaItems.toolFolder.tools.hooks.*;
import me.carson.terrariaItems.toolFolder.tools.potions.GreaterManaPotion;
import me.carson.terrariaItems.toolFolder.tools.potions.LesserManaPotion;
import me.carson.terrariaItems.toolFolder.tools.potions.ManaPotion;
import me.carson.terrariaItems.toolFolder.tools.potions.SuperManaPotion;
import me.carson.terrariaItems.toolFolder.tools.summons.BloodyTear;
import me.carson.terrariaItems.toolFolder.tools.summons.MechanicalEgg;
import me.carson.terrariaItems.toolFolder.tools.summons.MechanicalShrieker;
import me.carson.terrariaItems.toolFolder.tools.summons.MechanicalSkull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.Plugin;

public class ToolRecipes implements CustomRecipeManager.RecipeProvider {

    private final Plugin plugin;


    public ToolRecipes(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerRecipes(CustomRecipeManager manager) {
        registerMirrorRecipe();
        registerCosmolightRecipe();
        //registerCapacitorRecipe();
        //registerRodRecipe();
        registerLifeCrystalRecipe();
        registerManaCrystalRecipe();
        registerTorrentialTearRecipe();
        registerMechanicalShriekerRecipe();
        registerMechanicalEggRecipe();
        registerMechanicalSkullRecipe();
        registerManaPotionRecipe();
        registerSuperManaPotionRecipe();
        registerBloodyTearRecipe();
        registerGrapplingHookRecipe();
        registerAmethystHookRecipe();
        registerEmeraldHookRecipe();
        registerDiamondHookRecipe();
        registerRubyHookRecipe();
    }

    private void registerRubyHookRecipe(){
        ItemStack item= RubyHook.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "RubyHook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" RH","CCR","CC ");
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(Ruby.getItem(plugin)));
        recipe.setIngredient('H', Material.TRIPWIRE_HOOK);
        recipe.setIngredient('C', Material.CHAIN);
        recipe.setCategory(CraftingBookCategory.MISC);Bukkit.addRecipe(recipe);
    }

    private void registerDiamondHookRecipe(){
        ItemStack item= DiamondHook.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "DiamondHook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" DH","CCD","CC ");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('H', Material.TRIPWIRE_HOOK);
        recipe.setIngredient('C', Material.CHAIN);
        recipe.setCategory(CraftingBookCategory.MISC);Bukkit.addRecipe(recipe);
    }

    private void registerEmeraldHookRecipe(){
        ItemStack item= EmeraldHook.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "EmeraldHook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" EH","CCE","CC ");
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('H', Material.TRIPWIRE_HOOK);
        recipe.setIngredient('C', Material.CHAIN);
        recipe.setCategory(CraftingBookCategory.MISC);Bukkit.addRecipe(recipe);
    }

    private void registerAmethystHookRecipe(){
        ItemStack item= AmethystHook.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "AmethystHook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" AH","CCA","CC ");
        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        recipe.setIngredient('H', Material.TRIPWIRE_HOOK);
        recipe.setIngredient('C', Material.CHAIN);
        recipe.setCategory(CraftingBookCategory.MISC);Bukkit.addRecipe(recipe);
    }

    private void registerGrapplingHookRecipe(){
        ItemStack item= GrapplingHook.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "GrapplingHook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" IH","CCI","CC ");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('H', Material.TRIPWIRE_HOOK);
        recipe.setIngredient('C', Material.CHAIN);
        recipe.setCategory(CraftingBookCategory.MISC);Bukkit.addRecipe(recipe);
    }

    private void registerBloodyTearRecipe(){
        ItemStack item= BloodyTear.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "BloodyTear");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("RSR","RGR","BBB");
        recipe.setIngredient('B', Material.BONE);
        recipe.setIngredient('G', Material.GHAST_TEAR);
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('S', Material.BEEF);
        recipe.setCategory(CraftingBookCategory.MISC);Bukkit.addRecipe(recipe);
    }

    private void registerMirrorRecipe(){
        ItemStack mirror=MagicMirror.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MagicMirror");
        ShapedRecipe recipe = new ShapedRecipe(key, mirror);
        recipe.shape("IDI","IGI","IDI");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('G', Material.GLASS);
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setCategory(CraftingBookCategory.MISC);Bukkit.addRecipe(recipe);
    }
    private void registerCosmolightRecipe(){
        ItemStack cosmolight=Cosmolight.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Cosmolight");
        ShapedRecipe recipe = new ShapedRecipe(key, cosmolight);
        recipe.shape("LSL","SCS","NSN");
        recipe.setIngredient('C', Material.CLOCK);
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(SoulOfNight.getItem(plugin)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(FallenStar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }
    private void registerCapacitorRecipe(){
        ItemStack capacitor=MomentumCapacitor.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MomentumCapacitor");
        ShapedRecipe recipe = new ShapedRecipe(key, capacitor);
        recipe.shape("WWW","WNW","WWW");
        recipe.setIngredient('W', Material.WIND_CHARGE);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }
    private void registerRodRecipe(){
        ItemStack rod=RodOfDiscord.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "RodOfDiscord");
        ShapedRecipe recipe = new ShapedRecipe(key, rod);
        recipe.shape("ECE","ENE","ENE");
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('C', Material.CHORUS_FRUIT);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }

    private void registerLifeCrystalRecipe(){
        ItemStack lifeCrystal=LifeCrystal.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "LifeCrystal");
        ShapedRecipe recipe = new ShapedRecipe(key, lifeCrystal);
        recipe.shape("DCD","DDD"," D ");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice( Ruby.getItem(plugin)));
        recipe.setIngredient('C', Material.COBBLESTONE);
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }

    private void registerManaCrystalRecipe(){
        ItemStack crystal= ManaCrystal.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ManaCrystal");
        ShapedRecipe recipe = new ShapedRecipe(key, crystal);
        recipe.shape(" S ","SSS"," S ");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice( FallenStar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }

    private void registerTorrentialTearRecipe(){
        ItemStack item= TorrentialTear.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "TorrentialTear");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" D ","WCW"," D ");
        recipe.setIngredient('C', Material.CLOCK);
        recipe.setIngredient('D', Material.SPONGE);
        recipe.setIngredient('W', Material.WET_SPONGE);
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }
    private void registerMechanicalShriekerRecipe(){
        ItemStack item= MechanicalShrieker.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MechanicalShrieker");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" L ","NSN"," L ");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice( SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice( SoulOfNight.getItem(plugin)));
        recipe.setIngredient('S', Material.SCULK_SHRIEKER);
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }

    private void registerMechanicalEggRecipe(){
        ItemStack item= MechanicalEgg.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MechanicalEgg");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" L ","NCN"," L ");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice( SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice( SoulOfNight.getItem(plugin)));
        recipe.setIngredient('C', Material.CHORUS_FRUIT);
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }

    private void registerMechanicalSkullRecipe(){
        ItemStack item= MechanicalSkull.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MechanicalSkull");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" L ","NSN"," L ");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice( SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice( SoulOfNight.getItem(plugin)));
        recipe.setIngredient('S', Material.WITHER_SKELETON_SKULL);
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }

    private void registerManaPotionRecipe(){
        ItemStack item= ManaPotion.getItem(plugin);

        NamespacedKey key1 = new NamespacedKey(plugin, "ManaPotion1");
        ShapelessRecipe recipe1 = new ShapelessRecipe(key1, item);
        recipe1.addIngredient(new RecipeChoice.ExactChoice(LesserManaPotion.getItem(plugin)));
        recipe1.addIngredient(Material.WARPED_FUNGUS);
        recipe1.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe1);

        NamespacedKey key2 = new NamespacedKey(plugin, "ManaPotion2");
        ShapelessRecipe recipe2 = new ShapelessRecipe(key2, item);
        recipe2.addIngredient(new RecipeChoice.ExactChoice(LesserManaPotion.getItem(plugin)));
        recipe2.addIngredient(Material.CRIMSON_FUNGUS);
        recipe2.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe2);

    }

    private void registerSuperManaPotionRecipe(){
        ItemStack item = SuperManaPotion.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "SuperManaPotion");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(GreaterManaPotion.getItem(plugin)));
        recipe.addIngredient(Material.AMETHYST_SHARD);
        recipe.addIngredient(new RecipeChoice.ExactChoice(FallenStar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }
}
