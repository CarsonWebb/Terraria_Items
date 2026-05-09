package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.handlers.CustomRecipeManager;
import me.carson.terrariaItems.materialsFolder.materials.*;
import me.carson.terrariaItems.materialsFolder.materials.bullets.EmptyBullet;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfFright;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfLight;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfMight;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfNight;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows.*;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns.*;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons.*;
import me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.melee.*;
import me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.throwablesFolder.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class WeaponRecipes implements CustomRecipeManager.RecipeProvider {

    private final Plugin plugin;

    public WeaponRecipes(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerRecipes(CustomRecipeManager manager) {
        registerLightsBaneRecipe();
        registerDaedalusStormbowRecipe();
        registerVolcanoRecipe();
        registerMoltenFury();
        registerExcaliburRecipe();
        registerHallowedRepeaterRecipe();
        registerSnowballCannonRecipe();
        registerBladeOfGrassRecipe();
        registerIceBladeRecipe();
        registerBlowpipeRecipe();
        //registerMinisharkRecipe();
        registerSniperRifleRecipe();
        registerHandgunRecipe();
        registerMegasharkRecipe();
        registerNeedlerRecipe();
        registerPhoenixBlasterRecipe();
        //registerShotgunRecipe();
        registerChristmasTreeSwordRecipe();
        registerWaterBoltRecipe();
        registerBubbleGunRecipe();
        registerMeteorStaffRecipe();
        registerAmethystStaffRecipe();
        registerRubyStaffRecipe();
        registerIcicleStaffRecipe();
        registerStarCannonRecipe();
        registerSuperStarShooterRecipe();
        registerMagicalHarpRecipe();
        registerCrystalStormRecipe();
        registerOnyxBlasterRecipe();
        registerHoarfrostBowRecipe();
        registerSandGunRecipe();
        registerVampireKnivesRecipe();
        registerTaintedBladeRecipe();
        registerCausticEdgeRecipe();
        registerThrowingKnifeRecipe();
        registerBoneThrowingKnifeRecipe();
        registerPoisonedKnifeRecipe();
        registerShurikenRecipe();
        //registerGrenadeRecipe();
        registerStickyGrenadeRecipe();
        registerBouncyGrenadeRecipe();
        registerBouncyBombRecipe();
        registerStickyBombRecipe();
        registerBouncyDynamiteRecipe();
        registerStickyDynamiteRecipe();
        //registerDynamiteRecipe();
        //registerTNTRecipe();
        registerSpikyBallRecipe();
        registerWandOfSparkingRecipe();
    }

    private void registerWandOfSparkingRecipe(){
        ItemStack item= WandOfSparking.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "WandOfSparking");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ","  T"," S ");
        recipe.setIngredient('S',Material.STICK);
        recipe.setIngredient('T',Material.TORCH);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerSpikyBallRecipe(){
        ItemStack item= SpikyBall.getItem(plugin);
        item.setAmount(11);
        NamespacedKey key = new NamespacedKey(plugin, "SpikyBall");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" N ","NIN"," N ");
        recipe.setIngredient('N',Material.IRON_NUGGET);
        recipe.setIngredient('I',Material.IRON_INGOT);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerTNTRecipe(){
        ItemStack item= new ItemStack(Material.TNT);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "customItem"), PersistentDataType.BYTE, (byte) 1);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(plugin, "TNTBlock");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("DD ","   ","   ");
        recipe.setIngredient('D',new RecipeChoice.ExactChoice(Dynamite.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerDynamiteRecipe(){
        ItemStack item= Dynamite.getItem(plugin);
        item.setAmount(2);
        NamespacedKey key = new NamespacedKey(plugin, "Dynamite");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(Material.TNT);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerStickyDynamiteRecipe(){
        ItemStack item= StickyDynamite.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "StickyDynamite");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(Material.HONEY_BOTTLE);
        recipe.addIngredient(new RecipeChoice.ExactChoice(Dynamite.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBouncyDynamiteRecipe(){
        ItemStack item= BouncyDynamite.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "BouncyDynamite");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(Material.SLIME_BALL);
        recipe.addIngredient(new RecipeChoice.ExactChoice(Dynamite.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBouncyBombRecipe(){
        ItemStack item= BouncyBomb.getItem(plugin);
        item.setAmount(2);
        NamespacedKey key = new NamespacedKey(plugin, "BouncyBomb");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ","BSB","   ");
        recipe.setIngredient('S',Material.SLIME_BALL);
        recipe.setIngredient('B',new RecipeChoice.ExactChoice(Bomb.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerStickyBombRecipe(){
        ItemStack item= StickyBomb.getItem(plugin);
        item.setAmount(2);
        NamespacedKey key = new NamespacedKey(plugin, "StickyBomb");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ","BHB","   ");
        recipe.setIngredient('H',Material.HONEY_BOTTLE);
        recipe.setIngredient('B',new RecipeChoice.ExactChoice(Bomb.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBouncyGrenadeRecipe(){
        ItemStack item= BouncyGrenade.getItem(plugin);
        item.setAmount(4);
        NamespacedKey key = new NamespacedKey(plugin, "BouncyGrenade");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" G ","GSG"," G ");
        recipe.setIngredient('S',Material.SLIME_BALL);
        recipe.setIngredient('G',new RecipeChoice.ExactChoice(Grenade.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerStickyGrenadeRecipe(){
        ItemStack item= StickyGrenade.getItem(plugin);
        item.setAmount(4);
        NamespacedKey key = new NamespacedKey(plugin, "StickyGrenade");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" G ","GHG"," G ");
        recipe.setIngredient('H',Material.HONEY_BOTTLE);
        recipe.setIngredient('G',new RecipeChoice.ExactChoice(Grenade.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerGrenadeRecipe(){
        ItemStack item= Grenade.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Grenade");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" I ","IGI"," I ");
        recipe.setIngredient('I',Material.IRON_INGOT);
        recipe.setIngredient('G',Material.GUNPOWDER);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerShurikenRecipe(){
        ItemStack item= Shuriken.getItem(plugin);
        item.setAmount(49);
        NamespacedKey key = new NamespacedKey(plugin, "Shuriken");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" C ","C C"," C ");
        recipe.setIngredient('C',Material.COBBLESTONE);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBoneThrowingKnifeRecipe(){
        ItemStack item= BoneThrowingKnife.getItem(plugin);
        item.setAmount(49);
        NamespacedKey key = new NamespacedKey(plugin, "BoneThrowingKnife");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   "," B "," G ");
        recipe.setIngredient('B',Material.BONE);
        recipe.setIngredient('G',Material.GOLD_INGOT);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerPoisonedKnifeRecipe(){
        ItemStack item= PoisonedKnife.getItem(plugin);
        item.setAmount(49);
        NamespacedKey key = new NamespacedKey(plugin, "PoisonedKnife");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" E "," C "," S ");
        recipe.setIngredient('C',Material.COBBLESTONE);
        recipe.setIngredient('S',Material.STICK);
        recipe.setIngredient('E',Material.SPIDER_EYE);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerThrowingKnifeRecipe(){
        ItemStack item= ThrowingKnife.getItem(plugin);
        item.setAmount(49);
        NamespacedKey key = new NamespacedKey(plugin, "ThrowingKnife");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   "," C "," S ");
        recipe.setIngredient('C',Material.COBBLESTONE);
        recipe.setIngredient('S',Material.STICK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerLightsBaneRecipe(){
        ItemStack bane= LightsBane.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "LightsBane");
        ShapedRecipe recipe = new ShapedRecipe(key, bane);
        recipe.shape(" D "," D "," S ");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice( DemoniteBar.getItem(plugin)));
        recipe.setIngredient('S',Material.STICK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerDaedalusStormbowRecipe(){
        ItemStack item= DaedalusStormbow.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "DaedalusStormbow");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("FFF","LBL","FFF");
        recipe.setIngredient('B',Material.BOW);
        recipe.setIngredient('F',Material.FEATHER);
        recipe.setIngredient('L', new RecipeChoice.ExactChoice( SoulOfLight.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }


    private void registerVolcanoRecipe(){
        ItemStack volcano = Volcano.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Volcano");
        ShapelessRecipe recipe = new ShapelessRecipe(key, volcano);
        recipe.addIngredient(Material.DIAMOND_SWORD);
        recipe.addIngredient(new RecipeChoice.ExactChoice( HellstoneBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenFury(){
        ItemStack fury= MoltenFury.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenFury");
        ShapedRecipe recipe =new ShapedRecipe(key,fury);
        recipe.shape(" WS","WNS"," WS");
        recipe.setIngredient('W',Material.STICK);
        recipe.setIngredient('N', new RecipeChoice.ExactChoice( HellstoneBar.getItem(plugin)));
        recipe.setIngredient('S',Material.STRING);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerExcaliburRecipe(){
        ItemStack sword= Excalibur.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Excalibur");
        ShapedRecipe recipe =new ShapedRecipe(key,sword);
        recipe.shape(" H "," H "," S ");
        recipe.setIngredient('S',Material.STICK);
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }
    private void registerHallowedRepeaterRecipe(){
        ItemStack repeater= HallowedRepeater.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedRepeater");
        ShapedRecipe recipe =new ShapedRecipe(key,repeater);
        recipe.shape("SHS","TKT"," S ");
        recipe.setIngredient('S',Material.STICK);
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setIngredient('T', Material.STRING);
        recipe.setIngredient('K', Material.TRIPWIRE_HOOK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerSnowballCannonRecipe(){
        ItemStack snowball= SnowballCannon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "SnowballCannon");
        ShapedRecipe recipe =new ShapedRecipe(key,snowball);
        recipe.shape(" IB","SCI","SS ");
        recipe.setIngredient('S',Material.SNOW_BLOCK);
        recipe.setIngredient('C', Material.CROSSBOW);
        recipe.setIngredient('B', Material.BLUE_ICE);
        recipe.setIngredient('I', Material.ICE);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBladeOfGrassRecipe(){
        ItemStack item= BladeOfGrass.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "BladeOfGrass");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("BSB","BIB","BSB");
        recipe.setIngredient('B',Material.BAMBOO);
        recipe.setIngredient('S',Material.SPIDER_EYE);
        recipe.setIngredient('I',Material.IRON_SWORD);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerIceBladeRecipe(){
        ItemStack item= IceBlade.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "IceBlade");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("CCC","CIC","CCC");
        recipe.setIngredient('C',Material.SNOW_BLOCK);
        recipe.setIngredient('I',Material.IRON_SWORD);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBlowpipeRecipe(){
        ItemStack item= Blowpipe.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Blowpipe");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("S S","S S","S S");
        recipe.setIngredient('S',Material.STICK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerMinisharkRecipe(){
        ItemStack item= Minishark.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Minishark");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("   ","FBI","   ");
        recipe.setIngredient('F',Material.COD);
        recipe.setIngredient('B',Material.IRON_BLOCK);
        recipe.setIngredient('I',Material.IRON_INGOT);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerHandgunRecipe(){
        ItemStack item= Handgun.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Handgun");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("A A","BII","L  ");
        recipe.setIngredient('L',Material.LEATHER);
        recipe.setIngredient('B',Material.IRON_BLOCK);
        recipe.setIngredient('I',Material.IRON_INGOT);
        recipe.setIngredient('A',Material.IRON_BARS);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerMegasharkRecipe(){
        ItemStack item= Megashark.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Megashark");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape(" S ","SMS","GS ");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(SoulOfMight.getItem(plugin)));
        recipe.setIngredient('M', new RecipeChoice.ExactChoice(Minishark.getItem(plugin)));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(IllegalGunParts.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerNeedlerRecipe(){
        ItemStack item= Needler.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Needler");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("L  ","BBE","T  ");
        recipe.setIngredient('T',Material.TWISTING_VINES);
        recipe.setIngredient('B',Material.BAMBOO_PLANKS);
        recipe.setIngredient('E',Material.SPIDER_EYE);
        recipe.setIngredient('L',Material.JUNGLE_LEAVES);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerPhoenixBlasterRecipe(){
        ItemStack item= PhoenixBlaster.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "PhoenixBlaster");
        ShapelessRecipe recipe =new ShapelessRecipe(key,item);
        recipe.addIngredient(new RecipeChoice.ExactChoice( HellstoneBar.getItem(plugin)));
        recipe.addIngredient(new RecipeChoice.ExactChoice( Handgun.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerShotgunRecipe(){
        ItemStack item= Shotgun.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Shotgun");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("IIN","I W","   ");
        recipe.setIngredient('I',Material.IRON_INGOT);
        recipe.setIngredient('N',Material.NETHER_BRICK);
        recipe.setIngredient('W',Material.OAK_LOG);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerSniperRifleRecipe(){
        ItemStack item= SniperRifle.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "SniperRifle");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape(" S ","HHH","G  ");
        recipe.setIngredient('S',Material.SPYGLASS);
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setIngredient('G',Material.GREEN_DYE);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerChristmasTreeSwordRecipe(){
        ItemStack item= ChristmasTreeSword.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ChristmasTreeSword");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape(" H ","RLG","BSY");
        recipe.setIngredient('S',Material.SPRUCE_LOG);
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setIngredient('G',Material.GREEN_STAINED_GLASS);
        recipe.setIngredient('Y',Material.BLUE_STAINED_GLASS);
        recipe.setIngredient('B',Material.YELLOW_STAINED_GLASS);
        recipe.setIngredient('R',Material.RED_STAINED_GLASS);
        recipe.setIngredient('L',Material.SPRUCE_LEAVES);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerAmethystStaffRecipe(){
        ItemStack item= AmethystStaff.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "AmethystStaff");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("  A"," C ","C  ");
        recipe.setIngredient('A',Material.AMETHYST_SHARD);
        recipe.setIngredient('C',Material.COPPER_INGOT);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }
    private void registerRubyStaffRecipe(){
        ItemStack item= RubyStaff.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "RubyStaff");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("  R"," G ","G  ");
        recipe.setIngredient('R', new RecipeChoice.ExactChoice( Ruby.getItem(plugin)));
        recipe.setIngredient('G',Material.GOLD_INGOT);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerIcicleStaffRecipe(){
        ItemStack item= IcicleStaff.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "IcicleStaff");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("  I"," S ","S  ");
        recipe.setIngredient('I', Material.ICE);
        recipe.setIngredient('S',Material.SNOW_BLOCK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerMeteorStaffRecipe(){
        ItemStack item= MeteorStaff.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MeteorStaff");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("SSM","SNS","NSS");
        recipe.setIngredient('M', Material.MAGMA_BLOCK);
        recipe.setIngredient('N',Material.NETHERITE_SCRAP);
        recipe.setIngredient('S', new RecipeChoice.ExactChoice( SoulOfLight.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerBubbleGunRecipe(){
        ItemStack item= BubbleGun.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "BubbleGun");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("WWS","HHH","WWM");
        recipe.setIngredient('M', Material.MAGMA_BLOCK);
        recipe.setIngredient('W',Material.WATER_BUCKET);
        recipe.setIngredient('S',Material.SOUL_SAND);
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerWaterBoltRecipe(){
        ItemStack item= WaterBolt.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "WaterBolt");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("WDW","WBW","WWW");
        recipe.setIngredient('W',Material.WATER_BUCKET);
        recipe.setIngredient('D',Material.DIAMOND);
        recipe.setIngredient('B',Material.BOOK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerStarCannonRecipe(){
        ItemStack item= StarCannon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "StarCannon");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("SSS","CMC","SSS");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice( FallenStar.getItem(plugin)));
        recipe.setIngredient('C',Material.MAGMA_CREAM);
        recipe.setIngredient('M', new RecipeChoice.ExactChoice( Minishark.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerSuperStarShooterRecipe(){
        ItemStack item= SuperStarShooter.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "SuperStarShooter");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape(" H ","HSH"," H ");
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice( StarCannon.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerMagicalHarpRecipe(){
        ItemStack item= MagicalHarp.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MagicalHarp");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("GGG","GNG","SGG");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice( SoulOfNight.getItem(plugin)));
        recipe.setIngredient('G',Material.GOLD_INGOT);
        recipe.setIngredient('N',Material.NOTE_BLOCK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerCrystalStormRecipe(){
        ItemStack item= CrystalStorm.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CrystalStorm");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("SAS","ABA","SAS");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice( SoulOfLight.getItem(plugin)));
        recipe.setIngredient('A',Material.AMETHYST_SHARD);
        recipe.setIngredient('B',Material.BOOK);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerOnyxBlasterRecipe(){
        ItemStack item= OnyxBlaster.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "OnyxBlaster");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape(" N ","NSN"," N ");
        recipe.setIngredient('N', new RecipeChoice.ExactChoice( SoulOfNight.getItem(plugin)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice( Shotgun.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerHoarfrostBowRecipe(){
        ItemStack item= HoarfrostBow.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HoarfrostBow");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape(" IS","ILS"," IS");
        recipe.setIngredient('S',Material.STRING);
        recipe.setIngredient('I',Material.ICE);
        recipe.setIngredient('L',Material.SOUL_LANTERN);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerSandGunRecipe(){
        ItemStack item= SandGun.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "SandGun");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("  S","GGG","I S");
        recipe.setIngredient('S',Material.SANDSTONE);
        recipe.setIngredient('I',new RecipeChoice.ExactChoice(IllegalGunParts.getItem(plugin)));
        recipe.setIngredient('G',Material.GOLD_INGOT);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerVampireKnivesRecipe(){
        ItemStack item= VampireKnives.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "VampireKnives");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("ISM","RIS","NRI");
        recipe.setIngredient('S',new RecipeChoice.ExactChoice(SoulOfFright.getItem(plugin)));
        recipe.setIngredient('I',Material.IRON_SWORD);
        recipe.setIngredient('M',Material.GLISTERING_MELON_SLICE);
        recipe.setIngredient('R',Material.REDSTONE);
        recipe.setIngredient('N',Material.NETHERITE_SCRAP);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerTaintedBladeRecipe(){
        ItemStack item= TaintedBlade.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "TaintedBlade");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape(" FC","PWF","NP ");
        recipe.setIngredient('F',Material.CRIMSON_FUNGUS);
        recipe.setIngredient('P',Material.WARPED_FUNGUS);
        recipe.setIngredient('W',Material.WARPED_PLANKS);
        recipe.setIngredient('C',Material.CRIMSON_PLANKS);
        recipe.setIngredient('N',Material.NETHERITE_SCRAP);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }

    private void registerCausticEdgeRecipe(){
        ItemStack item= CausticEdge.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CausticEdge");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("ELE","NTN","ELE");
        recipe.setIngredient('T',new RecipeChoice.ExactChoice(TaintedBlade.getItem(plugin)));
        recipe.setIngredient('N',new RecipeChoice.ExactChoice(SoulOfNight.getItem(plugin)));
        recipe.setIngredient('L',new RecipeChoice.ExactChoice(SoulOfLight.getItem(plugin)));
        recipe.setIngredient('E',Material.SPIDER_EYE);
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }
}
