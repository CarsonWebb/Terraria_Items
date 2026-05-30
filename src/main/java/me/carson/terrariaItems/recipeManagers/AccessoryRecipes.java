package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.accesoryFolder.accessories.*;
import me.carson.terrariaItems.handlers.CustomRecipeManager;
import me.carson.terrariaItems.materialsFolder.materials.DemoniteBar;
import me.carson.terrariaItems.materialsFolder.materials.FallenStar;
import me.carson.terrariaItems.materialsFolder.materials.ForbiddenFragment;
import me.carson.terrariaItems.materialsFolder.materials.FrostCore;
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

public class AccessoryRecipes implements CustomRecipeManager.RecipeProvider {

    private final Plugin plugin;

    public AccessoryRecipes(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerRecipes(CustomRecipeManager manager) {
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
    }

    private void registerCoinOfDeceitRecipe(){
        ItemStack item=CoinOfDeceit.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CoinOfDeceit");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" C ","CDC"," C ");
        recipe.setIngredient('C', Material.COPPER_INGOT);
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(DemoniteBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBundleOfHorseshoeBalloonsRecipe(){
        ItemStack item= BundleOfHorseshoeBalloons.getItem(plugin);

        NamespacedKey key = new NamespacedKey(plugin, "BundleOfHorseshoeBalloons");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(BundleOfBalloons.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(LuckyHorseshoe.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);

        NamespacedKey key1 = new NamespacedKey(plugin, "BundleOfHorseshoeBalloons1");
        ShapelessRecipe recipe1 = new ShapelessRecipe(key1, item);
        recipe1.addIngredient(new RecipeChoice.ExactChoice(BlueHorseshoeBalloon.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(BlizzardInABalloon.getItem(plugin),WhiteHorseshoeBalloon.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(SandstormInABalloon.getItem(plugin),YellowHorseshoeBalloon.getItem(plugin)));
        recipe1.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe1);

        NamespacedKey key2 = new NamespacedKey(plugin, "BundleOfHorseshoeBalloons2");
        ShapelessRecipe recipe2 = new ShapelessRecipe(key2, item);
        recipe2.addIngredient(new RecipeChoice.ExactChoice(BlueHorseshoeBalloon.getItem(plugin),CloudInABalloon.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(WhiteHorseshoeBalloon.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(SandstormInABalloon.getItem(plugin),YellowHorseshoeBalloon.getItem(plugin)));
        recipe2.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe2);

        NamespacedKey key3 = new NamespacedKey(plugin, "BundleOfHorseshoeBalloons3");
        ShapelessRecipe recipe3 = new ShapelessRecipe(key3, item);
        recipe3.addIngredient(new RecipeChoice.ExactChoice(BlueHorseshoeBalloon.getItem(plugin),CloudInABalloon.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(BlizzardInABalloon.getItem(plugin),WhiteHorseshoeBalloon.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(YellowHorseshoeBalloon.getItem(plugin)));
        recipe3.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe3);
    }

    private void registerBundleOfBalloonsRecipe(){
        ItemStack item= BundleOfBalloons.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "BundleOfBalloons");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(SandstormInABalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(BlizzardInABalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(CloudInABalloon.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBlizzardInABottleRecipe(){
        ItemStack item= BlizzardInABottle.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "BlizzardInABottle");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(CloudInABottle.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(FrostCore.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerSandstormInABottleRecipe(){
        ItemStack item= SandstormInABottle.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "SandstormInABottle");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(CloudInABottle.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(ForbiddenFragment.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerYellowHorseshoeBalloonRecipe(){
        ItemStack item= YellowHorseshoeBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "YellowHorseshoeBalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(SandstormInABalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(LuckyHorseshoe.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerWhiteHorseshoeBalloonRecipe(){
        ItemStack item= WhiteHorseshoeBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "WhiteHorseshoeBalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(BlizzardInABalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(LuckyHorseshoe.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBlueHorseshoeBalloonRecipe(){
        ItemStack item= BlueHorseshoeBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "BlueHorseshoeBalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(CloudInABalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(LuckyHorseshoe.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerAmberHorseshoeBalloonRecipe(){
        ItemStack item= AmberHorseshoeBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "AmberHorseshoeBalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(HoneyBalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(LuckyHorseshoe.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerSandstormInABalloonRecipe(){
        ItemStack item= SandstormInABalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "SandstormInABalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(RedBalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(SandstormInABottle.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBlizzardInABalloonRecipe(){
        ItemStack item= BlizzardInABalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "BlizzardInABalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(RedBalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(BlizzardInABottle.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerCloudInABalloonRecipe(){
        ItemStack item= CloudInABalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CloudInABalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(RedBalloon.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(CloudInABottle.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerManaCloakRecipe(){
        ItemStack item= ManaCloak.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ManaCloak");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(ManaFlower.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(StarCloak.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerManaFlowerRecipe(){
        ItemStack item= ManaFlower.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ManaFlower");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(NaturesGift.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(ManaPotion.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerStingerNecklaceRecipe(){
        ItemStack item= StingerNecklace.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "StingerNecklace");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(SharkToothNecklace.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(HoneyComb.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerStarVeilRecipe(){
        ItemStack item= StarVeil.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "StarVeil");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(CrossNecklace.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(StarCloak.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBeeCloakRecipe(){
        ItemStack item= BeeCloak.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "BeeCloak");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(HoneyComb.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(StarCloak.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerMechanicalGloveRecipe(){
        ItemStack item=MechanicalGlove.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MechanicalGlove");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(PowerGlove.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(AvengerEmblem.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerObsidianHorseshoeRecipe(){
        ItemStack item=ObsidianHorseshoe.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ObsidianHorseshoe");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(LuckyHorseshoe.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(ObsidianSkull.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerAnkhShieldRecipe(){
        ItemStack item=AnkhShield.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "AnkhShield");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(AnkhCharm.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(ObsidianShield.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerPowerGloveRecipe(){
        ItemStack item=PowerGlove.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "PowerGlove");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(FeralClaws.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(TitanGlove.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerAnkhCharmRecipe(){
        ItemStack item=AnkhCharm.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "AnkhCharm");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(Bezoar.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(Vitamins.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(Blindfold.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(FastClock.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerObsidianShieldRecipe(){
        ItemStack item=ObsidianShield.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ObsidianShield");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(CobaltShield.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(ObsidianSkull.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerSweetheartNecklaceRecipe(){
        ItemStack item=SweetheartNecklace.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "SweetheartNecklace");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(HoneyComb.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(PanicNecklace.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerHoneyBalloonRecipe(){
        ItemStack item=HoneyBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HoneyBalloon");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(HoneyComb.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(RedBalloon.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerHoneyCombRecipe(){
        ItemStack item=HoneyComb.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HoneyComb");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" H ","HGH"," H ");
        recipe.setIngredient('H', Material.HONEYCOMB);
        recipe.setIngredient('G', Material.GHAST_TEAR);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerMagicCuffsRecipe(){
        ItemStack item=MagicCuffs.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MagicCuffs");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(ManaRegenerationBand.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(Shackle.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerManaRegenerationBandRecipe(){
        ItemStack item=ManaRegenerationBand.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ManaRegenerationBand");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(BandOfStarpower.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(BandOfRegeneration.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerPanicNecklaceRecipe(){
        ItemStack item=PanicNecklace.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "PanicNecklace");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("CCC","CSC"," R ");
        recipe.setIngredient('C', Material.CHAIN);
        recipe.setIngredient('S', Material.SUGAR);
        recipe.setIngredient('R', Material.REDSTONE_BLOCK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBandOfStarpowerRecipe(){
        ItemStack item=BandOfStarpower.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "BandOfStarpower");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("LFL","L L","LLL");
        recipe.setIngredient('L', Material.LAPIS_LAZULI);
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(FallenStar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerAgletRecipe(){
        ItemStack aglet=Aglet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "aglet");
        ShapedRecipe recipe = new ShapedRecipe(key, aglet);
        recipe.shape("CCC","C C","CCC");
        recipe.setIngredient('C', Material.COPPER_BLOCK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerObsidianSkullRecipe(){
        ItemStack skull=ObsidianSkull.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "skull");
        ShapedRecipe recipe = new ShapedRecipe(key, skull);
        recipe.shape("OOO","OOO"," O ");
        recipe.setIngredient('O', Material.OBSIDIAN);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerRedBalloonRecipe(){
        ItemStack balloon=RedBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "balloon");
        ShapedRecipe recipe = new ShapedRecipe(key, balloon);
        recipe.shape(" W "," S "," S ");
        recipe.setIngredient('W', Material.RED_WOOL);
        recipe.setIngredient('S', Material.STRING);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBandOfRegenerationRecipe(){
        ItemStack band=BandOfRegeneration.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "band");
        ShapedRecipe recipe = new ShapedRecipe(key, band);
        recipe.shape("RGR","R R","RRR");
        recipe.setIngredient('R', Material.REDSTONE_BLOCK);
        recipe.setIngredient('G', Material.GHAST_TEAR);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerHorseshoeRecipe(){
        ItemStack horseshoe=LuckyHorseshoe.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "LuckyHorseshoe");
        ShapedRecipe recipe = new ShapedRecipe(key, horseshoe);
        recipe.shape("G G","G G","GGG");
        recipe.setIngredient('G', Material.GOLD_BLOCK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerCobaltShieldRecipe(){
        ItemStack cobaltShield=CobaltShield.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CobaltShield");
        ShapedRecipe recipe = new ShapedRecipe(key, cobaltShield);
        recipe.shape("LSL","LNL"," L ");
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('S', Material.SHIELD);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }
    private void registerCounterScarfRecipe(){
        ItemStack counterScarf=CounterScarf.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CounterScarf");
        ShapedRecipe recipe = new ShapedRecipe(key, counterScarf);
        recipe.shape("ERE","RBR","ERE");
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('R', Material.RED_WOOL);
        recipe.setIngredient('B', Material.ENDER_EYE);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }
    private void registerNeptuneShellRecipe(){
        ItemStack item=NeptunesShell.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "NeptunesShell");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("STS","BHF","SUS");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice( SoulOfFright.getItem(plugin)));
        recipe.setIngredient('T', Material.TUBE_CORAL_BLOCK);
        recipe.setIngredient('B', Material.BRAIN_CORAL_BLOCK);
        recipe.setIngredient('H', Material.HORN_CORAL_BLOCK);
        recipe.setIngredient('F', Material.FIRE_CORAL_BLOCK);
        recipe.setIngredient('U', Material.BUBBLE_CORAL_BLOCK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }
    private void registerBezoarRecipe(){
        ItemStack bezoar=Bezoar.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Bezoar");
        ShapedRecipe recipe = new ShapedRecipe(key, bezoar);
        recipe.shape("LML","MSM","NMN");
        recipe.setIngredient('M', Material.MOSS_BLOCK);
        recipe.setIngredient('S', Material.SPIDER_EYE);
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(SoulOfNight.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }
    private void registerBlindfoldRecipe(){
        ItemStack blind=Blindfold.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Blindfold");
        ShapedRecipe recipe = new ShapedRecipe(key, blind);
        recipe.shape("LLL","WEW","NNN");
        recipe.setIngredient('W', Material.WHITE_WOOL);
        recipe.setIngredient('E', Material.ECHO_SHARD);
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(SoulOfNight.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerFastClockRecipe(){
        ItemStack clock=FastClock.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "FastClock");
        ShapedRecipe recipe = new ShapedRecipe(key, clock);
        recipe.shape("SSS","LCN","SSS");
        recipe.setIngredient('S', Material.SUGAR);
        recipe.setIngredient('C', Material.CLOCK);
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(SoulOfNight.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerVitaminsRecipe(){
        ItemStack vitamin=Vitamins.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Vitamins");
        ShapelessRecipe recipe = new ShapelessRecipe(key, vitamin);
        recipe.addIngredient(Material.POTION);
        recipe.addIngredient(new RecipeChoice.ExactChoice(SoulOfLight.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(SoulOfNight.getItem(plugin)));
        recipe.addIngredient(Material.SWEET_BERRIES);
        recipe.addIngredient(Material.POTATO);
        recipe.addIngredient(Material.CARROT);
        recipe.addIngredient(Material.BEETROOT);
        recipe.addIngredient(Material.MILK_BUCKET);
        recipe.addIngredient(Material.HONEY_BOTTLE);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerNightVisionHelmetRecipe(){
        ItemStack item=NightVisionHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "NightVisionHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("DDD","DCD","   ");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('C', Material.GOLDEN_CARROT);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerAvengerEmblemRecipe(){
        ItemStack item=AvengerEmblem.getItem(plugin);
        NamespacedKey key1 = new NamespacedKey(plugin, "AvengerEmblem1");
        ShapelessRecipe recipe1 = new ShapelessRecipe(key1, item);
        recipe1.addIngredient(new RecipeChoice.ExactChoice(WarriorEmblem.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(SoulOfMight.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(SoulOfFright.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(SoulOfSight.getItem(plugin)));
        recipe1.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe1);

        NamespacedKey key2 = new NamespacedKey(plugin, "AvengerEmblem2");
        ShapelessRecipe recipe2 = new ShapelessRecipe(key2, item);
        recipe2.addIngredient(new RecipeChoice.ExactChoice(RangerEmblem.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(SoulOfMight.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(SoulOfFright.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(SoulOfSight.getItem(plugin)));
        recipe2.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe2);

        NamespacedKey key3 = new NamespacedKey(plugin, "AvengerEmblem3");
        ShapelessRecipe recipe3 = new ShapelessRecipe(key3, item);
        recipe3.addIngredient(new RecipeChoice.ExactChoice(SorcererEmblem.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(SoulOfMight.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(SoulOfFright.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(SoulOfSight.getItem(plugin)));
        recipe3.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe3);
    }

}
