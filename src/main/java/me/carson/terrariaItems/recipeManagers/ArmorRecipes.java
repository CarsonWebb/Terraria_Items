package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.armorFolder.armors.forbiddenArmor.*;
import me.carson.terrariaItems.armorFolder.armors.frostArmor.*;
import me.carson.terrariaItems.armorFolder.armors.jungleArmor.JungleHat;
import me.carson.terrariaItems.armorFolder.armors.jungleArmor.JungleLeggings;
import me.carson.terrariaItems.armorFolder.armors.jungleArmor.JunglePants;
import me.carson.terrariaItems.armorFolder.armors.jungleArmor.JungleShirt;
import me.carson.terrariaItems.armorFolder.armors.necroArmor.NecroBreastplate;
import me.carson.terrariaItems.armorFolder.armors.necroArmor.NecroGreaves;
import me.carson.terrariaItems.armorFolder.armors.necroArmor.NecroHelmet;
import me.carson.terrariaItems.armorFolder.armors.necroArmor.NecroLeggings;
import me.carson.terrariaItems.handlers.CustomRecipeManager;
import me.carson.terrariaItems.materialsFolder.materials.*;
import me.carson.terrariaItems.miscFolder.hats.GoldenCrown;
import me.carson.terrariaItems.armorFolder.armors.cactusArmor.CactusBoots;
import me.carson.terrariaItems.armorFolder.armors.cactusArmor.CactusChestplate;
import me.carson.terrariaItems.armorFolder.armors.cactusArmor.CactusHelmet;
import me.carson.terrariaItems.armorFolder.armors.cactusArmor.CactusLeggings;
import me.carson.terrariaItems.armorFolder.armors.hallowedArmor.*;
import me.carson.terrariaItems.armorFolder.armors.moltenArmor.*;
import me.carson.terrariaItems.armorFolder.armors.shadowArmor.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.Plugin;

public class ArmorRecipes implements CustomRecipeManager.RecipeProvider {

    private final Plugin plugin;

    public ArmorRecipes(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerRecipes(CustomRecipeManager manager) {
        registerShadowHelmetRecipe();
        registerShadowScalemailRecipe();
        registerShadowLeggingsRecipe();
        registerShadowGreavesRecipe();
        //registerShadowElytraRecipe();
        registerMoltenHelmRecipe();
        registerMoltenChestplateRecipe();
        registerMoltenLeggingsRecipe();
        registerMoltenBootsRecipe();
        //registerMoltenElytraRecipe();
        registerHallowedMaskRecipe();
        registerHallowedHelmetRecipe();
        registerHallowedHeadgearRecipe();
        registerHallowedChestplateRecipe();
        registerHallowedLeggingsRecipe();
        registerHallowedBootsRecipe();
        registerHallowedElytraRecipe();
        registerCactusHelmetRecipe();
        registerCactusChestplateRecipe();
        registerCactusLeggingsRecipe();
        registerCactusBootsRecipe();
        registerGoldenCrownRecipe();
        registerJungleHatRecipe();
        registerJungleShirtRecipe();
        registerJungleLeggingsRecipe();
        registerJunglePantsRecipe();
        registerNecroHelmetRecipe();
        registerNecroBreastplateRecipe();
        registerNecroLeggingsRecipe();
        registerNecroGreavesRecipe();
        registerForbiddenMaskRecipe();
        registerForbiddenRobesRecipe();
        registerForbiddenLeggingsRecipe();
        registerForbiddenTreadsRecipe();
        registerForbiddenElytraRecipe();
        registerFrostHelmetRecipe();
        registerFrostBreastplateRecipe();
        registerFrostLeggingsRecipe();
        registerFrostBootsRecipe();
        registerFrostElytraRecipe();
    }

    private void registerShadowHelmetRecipe(){
        ItemStack helmet=ShadowHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ShadowHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, helmet);
        recipe.shape("DDD","D D","   ");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(DemoniteBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowScalemailRecipe(){
        ItemStack scalemail=ShadowScalemail.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ShadowScalemail");
        ShapedRecipe recipe = new ShapedRecipe(key, scalemail);
        recipe.shape("D D","DDD","DDD");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(DemoniteBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowLeggingsRecipe(){
        ItemStack leggings=ShadowLeggings.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ShadowLeggings");
        ShapedRecipe recipe = new ShapedRecipe(key, leggings);
        recipe.shape("DDD","D D","D D");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(DemoniteBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowGreavesRecipe(){
        ItemStack greaves=ShadowGreaves.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ShadowGreaves");
        ShapedRecipe recipe = new ShapedRecipe(key, greaves);
        recipe.shape("   ","D D","D D");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(DemoniteBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowElytraRecipe(){
        ItemStack elytra=ShadowElytra.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ShadowElytra");
        ShapedRecipe recipe = new ShapedRecipe(key, elytra);
        recipe.shape(" D ","DED"," D ");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(DemoniteBar.getItem(plugin)));
        recipe.setIngredient('E',new RecipeChoice.ExactChoice(new ItemStack(Material.ELYTRA)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenHelmRecipe(){
        ItemStack helm= MoltenHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenHelmet");
        ShapelessRecipe recipe = new ShapelessRecipe(key,helm);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND_HELMET)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(HellstoneBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenChestplateRecipe(){
        ItemStack chestplate= MoltenChestplate.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenChestplate");
        ShapelessRecipe recipe = new ShapelessRecipe(key,chestplate);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND_CHESTPLATE)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(HellstoneBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenLeggingsRecipe(){
        ItemStack leggings= MoltenLeggings.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenLeggings");
        ShapelessRecipe recipe = new ShapelessRecipe(key,leggings);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND_LEGGINGS)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(HellstoneBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenBootsRecipe(){
        ItemStack boots= MoltenBoots.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenBoots");
        ShapelessRecipe recipe = new ShapelessRecipe(key,boots);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND_BOOTS)));
        recipe.addIngredient(new RecipeChoice.ExactChoice( HellstoneBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenElytraRecipe(){
        ItemStack elytra= MoltenElytra.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenElytra");
        ShapelessRecipe recipe = new ShapelessRecipe(key,elytra);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.ELYTRA)));
        recipe.addIngredient(new RecipeChoice.ExactChoice( HellstoneBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerHallowedMaskRecipe(){
        ItemStack item= HallowedMask.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedMask");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("HHH","HBH","   ");
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BLAZE_POWDER)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerHallowedHelmetRecipe(){
        ItemStack item= HallowedHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("HHH","HAH","   ");
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new ItemStack(Material.ARROW)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerHallowedHeadgearRecipe(){
        ItemStack item= HallowedHeadgear.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedHeadgear");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("HHH","HEH","   ");
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setIngredient('E', new RecipeChoice.ExactChoice(new ItemStack(Material.ENDER_PEARL)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerHallowedChestplateRecipe(){
        ItemStack item= HallowedChestplate.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedChestplate");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("H H","HHH","HHH");
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerHallowedLeggingsRecipe(){
        ItemStack item= HallowedLeggings.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedLeggings");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("HHH","H H","H H");
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerHallowedBootsRecipe(){
        ItemStack item= HallowedBoots.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedBoots");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ","H H","H H");
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerHallowedElytraRecipe(){
        ItemStack item= HallowedElytra.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedElytra");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" H ","HEH"," H ");
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        recipe.setIngredient('E', new RecipeChoice.ExactChoice(new ItemStack(Material.ELYTRA)));
        Bukkit.addRecipe(recipe);
        
    }

    private void registerCactusHelmetRecipe(){
        ItemStack item= CactusHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CactusHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("CCC","C C","   ");
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CACTUS)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerCactusChestplateRecipe(){
        ItemStack item= CactusChestplate.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CactusChestplate");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("C C","CCC","CCC");
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CACTUS)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerCactusLeggingsRecipe(){
        ItemStack item= CactusLeggings.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CactusLeggings");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("CCC","C C","C C");
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CACTUS)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerCactusBootsRecipe(){
        ItemStack item= CactusBoots.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CactusBoots");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ","C C","C C");
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.CACTUS)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerGoldenCrownRecipe(){
        ItemStack item= GoldenCrown.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "GoldenCrown");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("GGG","GRG","   ");
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(Ruby.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerJungleHatRecipe(){
        ItemStack item= JungleHat.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "JungleHat");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("BVB","I I","   ");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BAMBOO)));
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)));
        recipe.setIngredient('V', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerJungleShirtRecipe(){
        ItemStack item= JungleShirt.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "JungleShirt");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("V V","BIB","BCB");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BAMBOO)));
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)));
        recipe.setIngredient('V', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.COCOA_BEANS)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerJungleLeggingsRecipe(){
        ItemStack item= JungleLeggings.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "JungleLeggings");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("VIV","B B","B B");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BAMBOO)));
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)));
        recipe.setIngredient('V', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerJunglePantsRecipe(){
        ItemStack item= JunglePants.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "JunglePants");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ","V V","B B");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BAMBOO)));
        recipe.setIngredient('V', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerNecroHelmetRecipe(){
        ItemStack item= NecroHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "NecroHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("CSC","B B","   ");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BONE)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.COBWEB)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.SOUL_SAND)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerNecroBreastplateRecipe(){
        ItemStack item= NecroBreastplate.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "NecroBreastplate");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("B B","CSC","CSC");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BONE)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.COBWEB)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.SOUL_SAND)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerNecroLeggingsRecipe(){
        ItemStack item= NecroLeggings.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "NecroLeggings");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("SBS","C C","C C");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BONE)));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new ItemStack(Material.COBWEB)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.SOUL_SAND)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerNecroGreavesRecipe(){
        ItemStack item= NecroGreaves.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "NecroGreaves");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ","S S","B B");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BONE)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.SOUL_SAND)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerForbiddenMaskRecipe(){
        ItemStack item= ForbiddenMask.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ForbiddenMask");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("GFG","B B","   ");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BLACK_WOOL)));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(ForbiddenFragment.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerForbiddenRobesRecipe(){
        ItemStack item= ForbiddenRobes.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ForbiddenRobes");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("G G","GFG","BBB");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BLACK_WOOL)));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(ForbiddenFragment.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerForbiddenLeggingsRecipe(){
        ItemStack item= ForbiddenLeggings.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ForbiddenLeggings");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("GFG","B B","B B");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BLACK_WOOL)));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(ForbiddenFragment.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }
    private void registerForbiddenTreadsRecipe(){
        ItemStack item= ForbiddenTreads.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ForbiddenTreads");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" F ","G G","B B");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BLACK_WOOL)));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(ForbiddenFragment.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerForbiddenElytraRecipe(){
        ItemStack item= ForbiddenElytra.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ForbiddenElytra");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.ELYTRA)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(ForbiddenFragment.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerFrostHelmetRecipe(){
        ItemStack item= FrostHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "FrostHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("DFD","I I","   ");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND)));
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.ICE)));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(FrostCore.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerFrostBreastplateRecipe(){
        ItemStack item= FrostBreastplate.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "FrostBreastplate");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("D D","DFD","III");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND)));
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.ICE)));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(FrostCore.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerFrostLeggingsRecipe(){
        ItemStack item= FrostLeggings.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "FrostLeggings");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("DFD","I I","I I");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND)));
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.ICE)));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(FrostCore.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerFrostBootsRecipe(){
        ItemStack item= FrostBoots.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "FrostBoots");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" F ","D D","I I");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND)));
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.ICE)));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(FrostCore.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

    private void registerFrostElytraRecipe(){
        ItemStack item= FrostElytra.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "FrostElytra");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.ELYTRA)));
        recipe.addIngredient(new RecipeChoice.ExactChoice(FrostCore.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
        
    }

}
