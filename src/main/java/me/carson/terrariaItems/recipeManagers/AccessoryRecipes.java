package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.accesoryFolder.accessories.*;
import me.carson.terrariaItems.handlers.CustomRecipeDiscoverManager;
import me.carson.terrariaItems.handlers.CustomRecipeManager;
import me.carson.terrariaItems.materialsFolder.materials.*;
import me.carson.terrariaItems.materialsFolder.materials.souls.*;
import me.carson.terrariaItems.toolFolder.tools.potions.ManaPotion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.Plugin;

public class AccessoryRecipes {

    private final Plugin plugin;
    private final CustomRecipeManager recipeManager;

    public AccessoryRecipes(Plugin plugin, CustomRecipeManager recipeManager) {
        this.plugin = plugin;
        this.recipeManager = recipeManager;
    }

    public void registerRecipes() {
        registerAgletRecipe();
        registerObsidianSkullRecipe();
        registerBandOfRegenerationRecipe();
        //registerRedBalloonRecipe();
        //registerHorseshoeRecipe();
        registerCobaltShieldRecipe();
        registerCounterScarfRecipe();
        registerNeptuneShellRecipe();
        //registerBezoarRecipe();
        registerBlindfoldRecipe();
        //registerFastClockRecipe();
        registerVitaminsRecipe();
        registerAvengerEmblemRecipe();
        registerNightVisionHelmetRecipe();
        registerPanicNecklaceRecipe();
        registerBandOfStarpowerRecipe();
        registerManaRegenerationBandRecipe();
        registerMagicCuffsRecipe();
        registerHoneyCombRecipe();
        registerHoneyBalloonRecipe();
        registerSweetheartNecklaceRecipe();
        registerObsidianShieldRecipe();
        registerAnkhCharmRecipe();
        registerPowerGloveRecipe();
        registerAnkhShieldRecipe();
        registerObsidianHorseshoeRecipe();
        registerMechanicalGloveRecipe();
        registerBeeCloakRecipe();
        registerStarVeilRecipe();
        registerStingerNecklaceRecipe();
        registerManaFlowerRecipe();
        registerManaCloakRecipe();
        registerCloudInABalloonRecipe();
        registerBlizzardInABalloonRecipe();
        registerSandstormInABalloonRecipe();
        registerAmberHorseshoeBalloonRecipe();
        registerBlueHorseshoeBalloonRecipe();
        registerWhiteHorseshoeBalloonRecipe();
        registerYellowHorseshoeBalloonRecipe();
        registerSandstormInABottleRecipe();
        registerBlizzardInABottleRecipe();
        registerBundleOfBalloonsRecipe();
        registerBundleOfHorseshoeBalloonsRecipe();
        registerCoinOfDeceitRecipe();
        registerRuinMedallionRecipe();
        registerSilencingSheathRecipe();
        registerCrossedHeartNecklaceRecipe();
    }

    private void registerCrossedHeartNecklaceRecipe(){
        ItemStack item= CrossedHeartNecklace.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_CrossedHeartNecklace");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(CrossNecklace.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(PanicNecklace.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }


    private void registerSilencingSheathRecipe(){
        ItemStack item=SilencingSheath.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_SilencingSheath");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("  W"," W ","N  ");
        recipe.setIngredient('W', new RecipeChoice.ExactChoice(new ItemStack(Material.WHITE_WOOL)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(new ItemStack(Material.NETHER_BRICK)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerRuinMedallionRecipe(){
        ItemStack item=RuinMedallion.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_RuinMedallion");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" U ","UCU"," U ");
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(CoinOfDeceit.getItem(plugin)));
        recipe.setIngredient('U', new RecipeChoice.ExactChoice(UnholyCore.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerCoinOfDeceitRecipe(){
        ItemStack item=CoinOfDeceit.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_CoinOfDeceit");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" C ","CDC"," C ");
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.COPPER_INGOT)));
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(DemoniteBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerBundleOfHorseshoeBalloonsRecipe(){
        ItemStack item= BundleOfHorseshoeBalloons.getItem(plugin);

        NamespacedKey key = new NamespacedKey(plugin, "pre_BundleOfHorseshoeBalloons");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(BundleOfBalloons.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(LuckyHorseshoe.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);

        NamespacedKey key1 = new NamespacedKey(plugin, "pre_BundleOfHorseshoeBalloons1");
        ShapelessRecipe recipe1 = new ShapelessRecipe(key1, item);
        recipe1.addIngredient(new RecipeChoice.ExactChoice(BlueHorseshoeBalloon.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(BlizzardInABalloon.getItem(plugin),WhiteHorseshoeBalloon.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(SandstormInABalloon.getItem(plugin),YellowHorseshoeBalloon.getItem(plugin)));
        recipe1.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe1);

        NamespacedKey key2 = new NamespacedKey(plugin, "pre_BundleOfHorseshoeBalloons2");
        ShapelessRecipe recipe2 = new ShapelessRecipe(key2, item);
        recipe2.addIngredient(new RecipeChoice.ExactChoice(BlueHorseshoeBalloon.getItem(plugin),CloudInABalloon.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(WhiteHorseshoeBalloon.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(SandstormInABalloon.getItem(plugin),YellowHorseshoeBalloon.getItem(plugin)));
        recipe2.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe2);

        NamespacedKey key3 = new NamespacedKey(plugin, "pre_BundleOfHorseshoeBalloons3");
        ShapelessRecipe recipe3 = new ShapelessRecipe(key3, item);
        recipe3.addIngredient(new RecipeChoice.ExactChoice(BlueHorseshoeBalloon.getItem(plugin),CloudInABalloon.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(BlizzardInABalloon.getItem(plugin),WhiteHorseshoeBalloon.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(YellowHorseshoeBalloon.getItem(plugin)));
        recipe3.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe3);
    }

    private void registerBundleOfBalloonsRecipe(){
        ItemStack item= BundleOfBalloons.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_BundleOfBalloons");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(SandstormInABalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(BlizzardInABalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(CloudInABalloon.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerBlizzardInABottleRecipe(){
        ItemStack item= BlizzardInABottle.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_BlizzardInABottle");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(CloudInABottle.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(FrostCore.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerSandstormInABottleRecipe(){
        ItemStack item= SandstormInABottle.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_SandstormInABottle");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(CloudInABottle.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(ForbiddenFragment.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerYellowHorseshoeBalloonRecipe(){
        ItemStack item= YellowHorseshoeBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_YellowHorseshoeBalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(SandstormInABalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(LuckyHorseshoe.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerWhiteHorseshoeBalloonRecipe(){
        ItemStack item= WhiteHorseshoeBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_WhiteHorseshoeBalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(BlizzardInABalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(LuckyHorseshoe.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerBlueHorseshoeBalloonRecipe(){
        ItemStack item= BlueHorseshoeBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_BlueHorseshoeBalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(CloudInABalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(LuckyHorseshoe.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerAmberHorseshoeBalloonRecipe(){
        ItemStack item= AmberHorseshoeBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_AmberHorseshoeBalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(HoneyBalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(LuckyHorseshoe.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerSandstormInABalloonRecipe(){
        ItemStack item= SandstormInABalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_SandstormInABalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(RedBalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(SandstormInABottle.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerBlizzardInABalloonRecipe(){
        ItemStack item= BlizzardInABalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_BlizzardInABalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(RedBalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(BlizzardInABottle.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerCloudInABalloonRecipe(){
        ItemStack item= CloudInABalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_CloudInABalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(RedBalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(CloudInABottle.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerManaCloakRecipe(){
        ItemStack item= ManaCloak.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_ManaCloak");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(ManaFlower.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(StarCloak.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerManaFlowerRecipe(){
        ItemStack item= ManaFlower.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_ManaFlower");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(NaturesGift.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(ManaPotion.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerStingerNecklaceRecipe(){
        ItemStack item= StingerNecklace.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_StingerNecklace");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(SharkToothNecklace.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(HoneyComb.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerStarVeilRecipe(){
        ItemStack item= StarVeil.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_StarVeil");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(CrossNecklace.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(StarCloak.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerBeeCloakRecipe(){
        ItemStack item= BeeCloak.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_BeeCloak");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(HoneyComb.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(StarCloak.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerMechanicalGloveRecipe(){
        ItemStack item=MechanicalGlove.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_MechanicalGlove");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(PowerGlove.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(AvengerEmblem.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerObsidianHorseshoeRecipe(){
        ItemStack item=ObsidianHorseshoe.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_ObsidianHorseshoe");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(LuckyHorseshoe.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(ObsidianSkull.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerAnkhShieldRecipe(){
        ItemStack item=AnkhShield.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_AnkhShield");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(AnkhCharm.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(ObsidianShield.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerPowerGloveRecipe(){
        ItemStack item=PowerGlove.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_PowerGlove");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(FeralClaws.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(TitanGlove.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerAnkhCharmRecipe(){
        ItemStack item=AnkhCharm.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_AnkhCharm");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(Bezoar.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(Vitamins.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(Blindfold.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(FastClock.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerObsidianShieldRecipe(){
        ItemStack item=ObsidianShield.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_ObsidianShield");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(CobaltShield.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(ObsidianSkull.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerSweetheartNecklaceRecipe(){
        ItemStack item=SweetheartNecklace.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_SweetheartNecklace");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(HoneyComb.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(PanicNecklace.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerHoneyBalloonRecipe(){
        ItemStack item=HoneyBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_HoneyBalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(HoneyComb.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(RedBalloon.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerHoneyCombRecipe(){
        ItemStack item=HoneyComb.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_HoneyComb");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" H ","HGH"," H ");
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(new ItemStack(Material.HONEYCOMB)));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GHAST_TEAR)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerMagicCuffsRecipe(){
        ItemStack item=MagicCuffs.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_MagicCuffs");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(ManaRegenerationBand.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(Shackle.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerManaRegenerationBandRecipe(){
        ItemStack item=ManaRegenerationBand.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_ManaRegenerationBand");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(BandOfStarpower.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(BandOfRegeneration.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerPanicNecklaceRecipe(){
        ItemStack item=PanicNecklace.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_PanicNecklace");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("CCC","CSC"," R ");
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CHAIN)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.SUGAR)));
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(new ItemStack(Material.REDSTONE_BLOCK)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerBandOfStarpowerRecipe(){
        ItemStack item=BandOfStarpower.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_BandOfStarpower");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("LFL","L L","LLL");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(new ItemStack(Material.LAPIS_LAZULI)));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(FallenStar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerAgletRecipe(){
        ItemStack aglet=Aglet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_aglet");
        ShapedRecipe recipe = new ShapedRecipe(key, aglet);
        recipe.shape("CCC","C C","CCC");
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.COPPER_BLOCK)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerObsidianSkullRecipe(){
        ItemStack skull=ObsidianSkull.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_skull");
        ShapedRecipe recipe = new ShapedRecipe(key, skull);
        recipe.shape("OOO","OOO"," O ");
        recipe.setIngredient('O', new RecipeChoice.ExactChoice(new ItemStack(Material.OBSIDIAN)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerRedBalloonRecipe(){
        ItemStack balloon=RedBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_balloon");
        ShapedRecipe recipe = new ShapedRecipe(key, balloon);
        recipe.shape(" W "," S "," S ");
        recipe.setIngredient('W', new RecipeChoice.ExactChoice(new ItemStack(Material.RED_WOOL)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.STRING)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerBandOfRegenerationRecipe(){
        ItemStack band=BandOfRegeneration.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_band");
        ShapedRecipe recipe = new ShapedRecipe(key, band);
        recipe.shape("RGR","R R","RRR");
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(new ItemStack(Material.REDSTONE_BLOCK)));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GHAST_TEAR)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerHorseshoeRecipe(){
        ItemStack horseshoe=LuckyHorseshoe.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_LuckyHorseshoe");
        ShapedRecipe recipe = new ShapedRecipe(key, horseshoe);
        recipe.shape("G G","G G","GGG");
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_BLOCK)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerCobaltShieldRecipe(){
        ItemStack cobaltShield=CobaltShield.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_CobaltShield");
        ShapedRecipe recipe = new ShapedRecipe(key, cobaltShield);
        recipe.shape("LSL","LNL"," L ");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(new ItemStack(Material.LAPIS_BLOCK)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.SHIELD)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(new ItemStack(Material.NETHERITE_INGOT)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }
    private void registerCounterScarfRecipe(){
        ItemStack counterScarf=CounterScarf.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_CounterScarf");
        ShapedRecipe recipe = new ShapedRecipe(key, counterScarf);
        recipe.shape("ERE","RBR","ERE");
        recipe.setIngredient('E', new RecipeChoice.ExactChoice(new ItemStack(Material.ENDER_PEARL)));
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(new ItemStack(Material.RED_WOOL)));
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.ENDER_EYE)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }
    private void registerNeptuneShellRecipe(){
        ItemStack item=NeptunesShell.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_NeptunesShell");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("STS","BHF","SUS");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice( SoulOfFright.getItem(plugin)));
        recipe.setIngredient('T', new RecipeChoice.ExactChoice(new ItemStack(Material.TUBE_CORAL_BLOCK)));
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BRAIN_CORAL_BLOCK)));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(new ItemStack(Material.HORN_CORAL_BLOCK)));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(new ItemStack(Material.FIRE_CORAL_BLOCK)));
        recipe.setIngredient('U', new RecipeChoice.ExactChoice(new ItemStack(Material.BUBBLE_CORAL_BLOCK)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }
    private void registerBezoarRecipe(){
        ItemStack bezoar=Bezoar.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_Bezoar");
        ShapedRecipe recipe = new ShapedRecipe(key, bezoar);
        recipe.shape("LML","MSM","NMN");
        recipe.setIngredient('M', new RecipeChoice.ExactChoice(new ItemStack(Material.MOSS_BLOCK)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.SPIDER_EYE)));
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(SoulOfNight.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }
    private void registerBlindfoldRecipe(){
        ItemStack blind=Blindfold.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_Blindfold");
        ShapedRecipe recipe = new ShapedRecipe(key, blind);
        recipe.shape("LLL","WEW","NNN");
        recipe.setIngredient('W', new RecipeChoice.ExactChoice(new ItemStack(Material.WHITE_WOOL)));
        recipe.setIngredient('E', new RecipeChoice.ExactChoice(new ItemStack(Material.ECHO_SHARD)));
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(SoulOfNight.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerFastClockRecipe(){
        ItemStack clock=FastClock.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_FastClock");
        ShapedRecipe recipe = new ShapedRecipe(key, clock);
        recipe.shape("SSS","LCN","SSS");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.SUGAR)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CLOCK)));
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(SoulOfNight.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerVitaminsRecipe(){
        ItemStack vitamin=Vitamins.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "hm_Vitamins");
        ShapelessRecipe recipe = new ShapelessRecipe(key, vitamin);
        recipe.addIngredient(Material.POTION);
        recipe.addIngredient(new RecipeChoice.ExactChoice(SoulOfLight.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(SoulOfNight.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.SWEET_BERRIES)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.POTATO)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.CARROT)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.BEETROOT)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.MILK_BUCKET)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.HONEY_BOTTLE)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerNightVisionHelmetRecipe(){
        ItemStack item=NightVisionHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "pre_NightVisionHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("DDD","DCD","   ");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLDEN_CARROT)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe);
    }

    private void registerAvengerEmblemRecipe(){
        ItemStack item=AvengerEmblem.getItem(plugin);
        NamespacedKey key1 = new NamespacedKey(plugin, "hm_AvengerEmblem1");
        ShapelessRecipe recipe1 = new ShapelessRecipe(key1, item);
        recipe1.addIngredient(new RecipeChoice.ExactChoice(WarriorEmblem.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(SoulOfMight.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(SoulOfFright.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(SoulOfSight.getItem(plugin)));
        recipe1.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe1);

        NamespacedKey key2 = new NamespacedKey(plugin, "hm_AvengerEmblem2");
        ShapelessRecipe recipe2 = new ShapelessRecipe(key2, item);
        recipe2.addIngredient(new RecipeChoice.ExactChoice(RangerEmblem.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(SoulOfMight.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(SoulOfFright.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(SoulOfSight.getItem(plugin)));
        recipe2.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe2);

        NamespacedKey key3 = new NamespacedKey(plugin, "hm_AvengerEmblem3");
        ShapelessRecipe recipe3 = new ShapelessRecipe(key3, item);
        recipe3.addIngredient(new RecipeChoice.ExactChoice(SorcererEmblem.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(SoulOfMight.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(SoulOfFright.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(SoulOfSight.getItem(plugin)));
        recipe3.setCategory(CraftingBookCategory.EQUIPMENT);
        recipeManager.register(recipe3);
    }

}
