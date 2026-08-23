package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.handlers.CustomRecipeDiscoverManager;
import me.carson.terrariaItems.handlers.CustomRecipeManager;
import me.carson.terrariaItems.materialsFolder.materials.*;
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
        registerTitanPotionRecipe();
        registerMiningPotionRecipe();
        registerEndurancePotionRecipe();
        registerWrathPotionRecipe();
        registerRagePotionRecipe();
        registerMagicPowerPotionRecipe();
        registerGoldenDelightRecipe();
        registerSeafoodDinnerRecipe();
        registerAppleJuiceRecipe();
    }

    private void registerAppleJuiceRecipe() {
        ItemStack item = AppleJuice.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_AppleJuice");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.GLASS_BOTTLE)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.APPLE)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerSeafoodDinnerRecipe() {
        ItemStack item = SeafoodDinner.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_SeafoodDinner");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.BOWL)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.COD)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.SALMON)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.TROPICAL_FISH)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.COD)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.SALMON)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.TROPICAL_FISH)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerGoldenDelightRecipe(){
        ItemStack item= GoldenDelight.getItem(plugin);
        NamespacedKey key1 = new NamespacedKey(plugin, "pre_GoldenDelight1");
        ShapedRecipe recipe1 = new ShapedRecipe(key1, item);
        recipe1.shape("GGG","BFB","GGG");
        recipe1.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
        recipe1.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_BLOCK)));
        recipe1.setIngredient('F', new RecipeChoice.ExactChoice(new ItemStack(Material.COOKED_BEEF)));
        recipe1.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe1);

        NamespacedKey key2 = new NamespacedKey(plugin, "pre_GoldenDelight2");
        ShapedRecipe recipe2 = new ShapedRecipe(key2, item);
        recipe2.shape("GGG","BFB","GGG");
        recipe2.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
        recipe2.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_BLOCK)));
        recipe2.setIngredient('F', new RecipeChoice.ExactChoice(new ItemStack(Material.COOKED_PORKCHOP)));
        recipe2.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe2);

        NamespacedKey key3 = new NamespacedKey(plugin, "pre_GoldenDelight3");
        ShapedRecipe recipe3 = new ShapedRecipe(key3, item);
        recipe3.shape("GGG","BFB","GGG");
        recipe3.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
        recipe3.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_BLOCK)));
        recipe3.setIngredient('F', new RecipeChoice.ExactChoice(new ItemStack(Material.COOKED_CHICKEN)));
        recipe3.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe3);

        NamespacedKey key4 = new NamespacedKey(plugin, "pre_GoldenDelight4");
        ShapedRecipe recipe4 = new ShapedRecipe(key4, item);
        recipe4.shape("GGG","BFB","GGG");
        recipe4.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
        recipe4.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_BLOCK)));
        recipe4.setIngredient('F', new RecipeChoice.ExactChoice(new ItemStack(Material.COOKED_MUTTON)));
        recipe4.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe4);

        NamespacedKey key5 = new NamespacedKey(plugin, "pre_GoldenDelight5");
        ShapedRecipe recipe5 = new ShapedRecipe(key5, item);
        recipe5.shape("GGG","BFB","GGG");
        recipe5.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
        recipe5.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_BLOCK)));
        recipe5.setIngredient('F', new RecipeChoice.ExactChoice(new ItemStack(Material.COOKED_RABBIT)));
        recipe5.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe5);

        NamespacedKey key6 = new NamespacedKey(plugin, "pre_GoldenDelight6");
        ShapedRecipe recipe6 = new ShapedRecipe(key6, item);
        recipe6.shape("GGG","BFB","GGG");
        recipe6.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
        recipe6.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_BLOCK)));
        recipe6.setIngredient('F', new RecipeChoice.ExactChoice(new ItemStack(Material.COOKED_COD)));
        recipe6.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe6);

        NamespacedKey key7 = new NamespacedKey(plugin, "pre_GoldenDelight7");
        ShapedRecipe recipe7 = new ShapedRecipe(key7, item);
        recipe7.shape("GGG","BFB","GGG");
        recipe7.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
        recipe7.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_BLOCK)));
        recipe7.setIngredient('F', new RecipeChoice.ExactChoice(new ItemStack(Material.COOKED_SALMON)));
        recipe7.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe7);
    }

    private void registerMagicPowerPotionRecipe() {
        ItemStack item = MagicPowerPotion.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_MagicPowerPotion");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.POTION)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(FallenStar.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.ALLIUM)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.BLUE_ORCHID)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerRagePotionRecipe() {
        ItemStack item = RagePotion.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_RagePotion");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.POTION)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(Hemopiranha.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.ALLIUM)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerWrathPotionRecipe() {
        ItemStack item = WrathPotion.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_WrathPotion");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.POTION)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(Ebonkoi.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.ALLIUM)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerEndurancePotionRecipe() {
        ItemStack item = EndurancePotion.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_EndurancePotion");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.POTION)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(ArmoredCavefish.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.DANDELION)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerMiningPotionRecipe() {
        ItemStack item = MiningPotion.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_MiningPotion");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.POTION)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.RAW_GOLD)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.SUNFLOWER)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
    }

    private void registerTitanPotionRecipe() {
        ItemStack item = TitanPotion.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_TitanPotion");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.POTION)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.BONE)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.CLOSED_EYEBLOSSOM)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.BLUE_ORCHID)));
        recipe.setCategory(CraftingBookCategory.MISC);
        recipeManager.register(recipe);
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
