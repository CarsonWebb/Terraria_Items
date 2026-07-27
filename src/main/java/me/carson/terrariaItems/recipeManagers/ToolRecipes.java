package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.handlers.CustomRecipeDiscoverManager;
import me.carson.terrariaItems.handlers.CustomRecipeManager;
import me.carson.terrariaItems.materialsFolder.materials.FallenStar;
import me.carson.terrariaItems.materialsFolder.materials.Ruby;
import me.carson.terrariaItems.materialsFolder.materials.souls.*;
import me.carson.terrariaItems.toolFolder.tools.*;
import me.carson.terrariaItems.toolFolder.tools.hooks.*;
import me.carson.terrariaItems.toolFolder.tools.potions.*;
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

public class ToolRecipes {

    private final Plugin plugin;
    private final CustomRecipeManager recipeManager;

    public ToolRecipes(Plugin plugin, CustomRecipeManager recipeManager) {
        this.plugin = plugin;
        this.recipeManager = recipeManager;
    }

    public void registerRecipes() {
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
        //registerStepStoolRecipe();
        registerIronskinPotionRecipe();
        registerBuilderPotionRecipe();
    }

    private void registerBuilderPotionRecipe() {
        ItemStack item = BuilderPotion.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_BuilderPotion");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.POTION)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.BRICK)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.CORNFLOWER)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.AZURE_BLUET)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerIronskinPotionRecipe() {
        ItemStack item = IronSkinPotion.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_IronskinPotion");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.POTION)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.RAW_IRON)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.DANDELION)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerStepStoolRecipe(){
        ItemStack item=StepStool.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "StepStool");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("PPP","S S","S S");
        recipe.setIngredient('P', new RecipeChoice.MaterialChoice(
                Material.OAK_PLANKS,
                Material.SPRUCE_PLANKS,
                Material.BIRCH_PLANKS,
                Material.JUNGLE_PLANKS,
                Material.ACACIA_PLANKS,
                Material.DARK_OAK_PLANKS,
                Material.MANGROVE_PLANKS,
                Material.CHERRY_PLANKS,
                Material.BAMBOO_PLANKS,
                Material.CRIMSON_PLANKS,
                Material.WARPED_PLANKS
        ));
        recipe.setIngredient('S', Material.STICK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerRubyHookRecipe(){
        ItemStack item= RubyHook.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_RubyHook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" RH","CCR","CC ");
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(Ruby.getItem(plugin)));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(new ItemStack(Material.TRIPWIRE_HOOK)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CHAIN)));
        recipe.setCategory(CraftingBookCategory.MISC);recipeManager.register(recipe);
    }

    private void registerDiamondHookRecipe(){
        ItemStack item= DiamondHook.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_DiamondHook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" DH","CCD","CC ");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND)));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(new ItemStack(Material.TRIPWIRE_HOOK)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CHAIN)));
        recipe.setCategory(CraftingBookCategory.MISC);recipeManager.register(recipe);
    }

    private void registerEmeraldHookRecipe(){
        ItemStack item= EmeraldHook.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_EmeraldHook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" EH","CCE","CC ");
        recipe.setIngredient('E', new RecipeChoice.ExactChoice(new ItemStack(Material.EMERALD)));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(new ItemStack(Material.TRIPWIRE_HOOK)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CHAIN)));
        recipe.setCategory(CraftingBookCategory.MISC);recipeManager.register(recipe);
    }

    private void registerAmethystHookRecipe(){
        ItemStack item= AmethystHook.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_AmethystHook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" AH","CCA","CC ");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new ItemStack(Material.AMETHYST_SHARD)));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(new ItemStack(Material.TRIPWIRE_HOOK)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CHAIN)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerGrapplingHookRecipe(){
        ItemStack item= GrapplingHook.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_GrapplingHook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" IH","CCI","CC ");
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(new ItemStack(Material.TRIPWIRE_HOOK)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CHAIN)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerBloodyTearRecipe(){
        ItemStack item= BloodyTear.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_BloodyTear");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("RSR","RGR","BBB");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BONE)));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GHAST_TEAR)));
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(new ItemStack(Material.REDSTONE)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.BEEF)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerMirrorRecipe(){
        ItemStack mirror=MagicMirror.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_MagicMirror");
        ShapedRecipe recipe = new ShapedRecipe(key, mirror);
        recipe.shape("IDI","IGI","IDI");
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GLASS)));
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND)));
        recipe.setCategory(CraftingBookCategory.MISC);recipeManager.register(recipe);
    }
    private void registerCosmolightRecipe(){
        ItemStack cosmolight=Cosmolight.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_Cosmolight");
        ShapedRecipe recipe = new ShapedRecipe(key, cosmolight);
        recipe.shape("LSL","SCS","NSN");
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CLOCK)));
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(SoulOfNight.getItem(plugin)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(FallenStar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }
    private void registerCapacitorRecipe(){
        ItemStack capacitor=MomentumCapacitor.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_MomentumCapacitor");
        ShapedRecipe recipe = new ShapedRecipe(key, capacitor);
        recipe.shape("WWW","WNW","WWW");
        recipe.setIngredient('W', new RecipeChoice.ExactChoice(new ItemStack(Material.WIND_CHARGE)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(new ItemStack(Material.NETHERITE_INGOT)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }
    private void registerRodRecipe(){
        ItemStack rod=RodOfDiscord.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_RodOfDiscord");
        ShapedRecipe recipe = new ShapedRecipe(key, rod);
        recipe.shape("ECE","ENE","ENE");
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CHORUS_FRUIT)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(new ItemStack(Material.NETHERITE_INGOT)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerLifeCrystalRecipe(){
        ItemStack lifeCrystal=LifeCrystal.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_LifeCrystal");
        ShapedRecipe recipe = new ShapedRecipe(key, lifeCrystal);
        recipe.shape("DCD","DDD"," D ");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice( Ruby.getItem(plugin)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.COBBLESTONE)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerManaCrystalRecipe(){
        ItemStack crystal= ManaCrystal.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_ManaCrystal");
        ShapedRecipe recipe = new ShapedRecipe(key, crystal);
        recipe.shape(" S ","SSS"," S ");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice( FallenStar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerTorrentialTearRecipe(){
        ItemStack item= TorrentialTear.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_TorrentialTear");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" D ","WCW"," D ");
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CLOCK)));
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(new ItemStack(Material.SPONGE)));
        recipe.setIngredient('W', new RecipeChoice.ExactChoice(new ItemStack(Material.WET_SPONGE)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }
    private void registerMechanicalShriekerRecipe(){
        ItemStack item= MechanicalShrieker.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_MechanicalShrieker");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" L ","NSN"," L ");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice( SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice( SoulOfNight.getItem(plugin)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.SCULK_SHRIEKER)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerMechanicalEggRecipe(){
        ItemStack item= MechanicalEgg.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_MechanicalEgg");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" L ","NCN"," L ");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice( SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice( SoulOfNight.getItem(plugin)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CHORUS_FRUIT)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerMechanicalSkullRecipe(){
        ItemStack item= MechanicalSkull.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_MechanicalSkull");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" L ","NSN"," L ");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice( SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice( SoulOfNight.getItem(plugin)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.WITHER_SKELETON_SKULL)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerManaPotionRecipe(){
        ItemStack item= ManaPotion.getItem(plugin);

        NamespacedKey key1 = new NamespacedKey(plugin, "pre_ManaPotion1");
        ShapelessRecipe recipe1 = new ShapelessRecipe(key1, item);
        recipe1.addIngredient(new RecipeChoice.ExactChoice(LesserManaPotion.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.WARPED_FUNGUS)));
        recipe1.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe1);

        NamespacedKey key2 = new NamespacedKey(plugin, "pre_ManaPotion2");
        ShapelessRecipe recipe2 = new ShapelessRecipe(key2, item);
        recipe2.addIngredient(new RecipeChoice.ExactChoice(LesserManaPotion.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.CRIMSON_FUNGUS)));
        recipe2.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe2);

    }

    private void registerSuperManaPotionRecipe(){
        ItemStack item = SuperManaPotion.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_SuperManaPotion");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(GreaterManaPotion.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.AMETHYST_SHARD)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(FallenStar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }
}
