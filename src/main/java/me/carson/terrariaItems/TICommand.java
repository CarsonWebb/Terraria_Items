package me.carson.terrariaItems;

import me.carson.terrariaItems.accesoryFolder.AccessoryManager;
import me.carson.terrariaItems.accesoryFolder.accessories.*;
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
import me.carson.terrariaItems.armorFolder.armors.timArmor.WizardHat;
import me.carson.terrariaItems.handlers.CustomRecipeManager;
import me.carson.terrariaItems.handlers.ResetHandler;
import me.carson.terrariaItems.materialsFolder.materials.ForbiddenFragment;
import me.carson.terrariaItems.materialsFolder.materials.FrostCore;
import me.carson.terrariaItems.miscFolder.BasicItems.BonePickaxe;
import me.carson.terrariaItems.miscFolder.fishingRods.*;
import me.carson.terrariaItems.miscFolder.hats.GoldenCrown;
import me.carson.terrariaItems.armorFolder.armors.cactusArmor.CactusBoots;
import me.carson.terrariaItems.armorFolder.armors.cactusArmor.CactusChestplate;
import me.carson.terrariaItems.armorFolder.armors.cactusArmor.CactusHelmet;
import me.carson.terrariaItems.armorFolder.armors.cactusArmor.CactusLeggings;
import me.carson.terrariaItems.armorFolder.armors.hallowedArmor.*;
import me.carson.terrariaItems.armorFolder.armors.moltenArmor.*;
import me.carson.terrariaItems.armorFolder.armors.shadowArmor.*;
import me.carson.terrariaItems.blocksFolder.blocks.Hellforge;
import me.carson.terrariaItems.handlers.PlayerDataHandler;
import me.carson.terrariaItems.handlers.VanityManager;
import me.carson.terrariaItems.materialsFolder.materials.DemoniteBar;
import me.carson.terrariaItems.materialsFolder.materials.FallenStar;
import me.carson.terrariaItems.materialsFolder.materials.souls.*;
import me.carson.terrariaItems.miscFolder.BasicItems.PickaxeAxe;
import me.carson.terrariaItems.toolFolder.tools.*;
import me.carson.terrariaItems.toolFolder.tools.crates.*;
import me.carson.terrariaItems.toolFolder.tools.potions.GreaterManaPotion;
import me.carson.terrariaItems.toolFolder.tools.potions.LesserManaPotion;
import me.carson.terrariaItems.toolFolder.tools.potions.ManaPotion;
import me.carson.terrariaItems.toolFolder.tools.potions.SuperManaPotion;
import me.carson.terrariaItems.toolFolder.tools.summons.BloodyTear;
import me.carson.terrariaItems.toolFolder.tools.summons.MechanicalEgg;
import me.carson.terrariaItems.toolFolder.tools.summons.MechanicalShrieker;
import me.carson.terrariaItems.toolFolder.tools.summons.MechanicalSkull;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows.*;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns.*;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons.*;
import me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.melee.*;
import me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.throwablesFolder.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TICommand implements CommandExecutor, TabCompleter {

    private final TerrariaItems plugin;
    private final AccessoryManager accessoryManagerInstance=AccessoryManager.getInstance();
    private final PlayerDataHandler playerInstance= PlayerDataHandler.getInstance();
    private final ResetHandler resetInstance=ResetHandler.getInstance();
    public final TILangManager lang =TILangManager.getInstance();

    public TICommand(TerrariaItems plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§eUsage: /ti <subcommand>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "give" -> {
                if (args.length < 2) {
                    player.sendMessage("§cUsage: /ti give <item>");
                    return true;
                }
                if (!player.hasPermission("terrariaitems.sub.give")) {
                    player.sendMessage(ChatColor.RED+"You do not have permission to use /ti give.");
                    return true;
                }

                String itemName = args[1].toLowerCase();
                switch (itemName) {
                    case "cosmolight"-> {
                        player.getInventory().addItem(Cosmolight.getItem(plugin));
                    }
                    case "rod_of_discord"-> {
                        player.getInventory().addItem(RodOfDiscord.getItem(plugin));
                    }
                    case "momentum_capacitor"-> {
                        player.getInventory().addItem(MomentumCapacitor.getItem(plugin));
                    }
                    case "stormbow"-> {
                        player.getInventory().addItem(DaedalusStormbow.getItem(plugin));
                    }
                    case "cloud_bottle"-> {
                        player.getInventory().addItem(CloudInABottle.getItem(plugin));
                    }
                    case "aglet"-> {
                        player.getInventory().addItem(Aglet.getItem(plugin));
                    }
                    case "obsidian_skull"-> {
                        player.getInventory().addItem(ObsidianSkull.getItem(plugin));
                    }
                    case "band_of_regeneration"-> {
                        player.getInventory().addItem(BandOfRegeneration.getItem(plugin));
                    }
                    case "red_balloon"-> {
                        player.getInventory().addItem(RedBalloon.getItem(plugin));
                    }
                    case "lucky_horseshoe"-> {
                        player.getInventory().addItem(LuckyHorseshoe.getItem(plugin));
                    }
                    case "magic_mirror"-> {
                        player.getInventory().addItem(MagicMirror.getItem(plugin));
                    }
                    case "cobalt_shield"-> {
                        player.getInventory().addItem(CobaltShield.getItem(plugin));
                    }
                    case "golden_crown"-> {
                        player.getInventory().addItem(GoldenCrown.getItem(plugin));
                    }
                    case "demonite_bar"-> {
                        player.getInventory().addItem(DemoniteBar.getItem(plugin));
                    }
                    case "lights_bane"-> {
                        player.getInventory().addItem(LightsBane.getItem(plugin));
                    }
                    case "shadow_armour"-> {
                        player.getInventory().addItem(ShadowHelmet.getItem(plugin));
                        player.getInventory().addItem(ShadowScalemail.getItem(plugin));
                        player.getInventory().addItem(ShadowLeggings.getItem(plugin));
                        player.getInventory().addItem(ShadowGreaves.getItem(plugin));
                    }
                    case "molten_armour"-> {
                        player.getInventory().addItem(MoltenHelmet.getItem(plugin));
                        player.getInventory().addItem(MoltenChestplate.getItem(plugin));
                        player.getInventory().addItem(MoltenLeggings.getItem(plugin));
                        player.getInventory().addItem(MoltenBoots.getItem(plugin));
                    }
                    case "counter_scarf"-> {
                        player.getInventory().addItem(CounterScarf.getItem(plugin));
                    }
                    case "molten_fury"-> {
                        player.getInventory().addItem(MoltenFury.getItem(plugin));
                    }
                    case "volcano"-> {
                        player.getInventory().addItem(Volcano.getItem(plugin));
                    }
                    case "hellforge"-> {
                        player.getInventory().addItem(Hellforge.getItem(plugin));
                    }
                    case "bezoar"-> {
                        player.getInventory().addItem(Bezoar.getItem(plugin));
                    }
                    case "blindfold"-> {
                        player.getInventory().addItem(Blindfold.getItem(plugin));
                    }
                    case "fast_clock"-> {
                        player.getInventory().addItem(FastClock.getItem(plugin));
                    }
                    case "vitamins"-> {
                        player.getInventory().addItem(Vitamins.getItem(plugin));
                    }
                    case "molten_elytra"-> {
                        player.getInventory().addItem(MoltenElytra.getItem(plugin));
                    }
                    case "shadow_elytra"-> {
                        player.getInventory().addItem(ShadowElytra.getItem(plugin));
                    }
                    case "ranger_emblem"-> {
                        player.getInventory().addItem(RangerEmblem.getItem(plugin));
                    }
                    case "warrior_emblem"-> {
                        player.getInventory().addItem(WarriorEmblem.getItem(plugin));
                    }
                    case "shackle"-> {
                        player.getInventory().addItem(Shackle.getItem(plugin));
                    }
                    case "might"-> {
                        player.getInventory().addItem(SoulOfMight.getItem(plugin));
                    }
                    case "excalibur"-> {
                        player.getInventory().addItem(Excalibur.getItem(plugin));
                    }
                    case "snowball_cannon"-> {
                        player.getInventory().addItem(SnowballCannon.getItem(plugin));
                    }
                    case "hallowed_repeater"-> {
                        player.getInventory().addItem(HallowedRepeater.getItem(plugin));
                    }
                    case "pickaxe_axe"-> {
                        player.getInventory().addItem(PickaxeAxe.getItem(plugin));
                    }
                    case "hallowed_armour"-> {
                        player.getInventory().addItem(HallowedMask.getItem(plugin));
                        player.getInventory().addItem(HallowedHelmet.getItem(plugin));
                        player.getInventory().addItem(HallowedHeadgear.getItem(plugin));
                        player.getInventory().addItem(HallowedChestplate.getItem(plugin));
                        player.getInventory().addItem(HallowedLeggings.getItem(plugin));
                        player.getInventory().addItem(HallowedBoots.getItem(plugin));
                    }
                    case "hallowed_elytra"-> {
                        player.getInventory().addItem(HallowedElytra.getItem(plugin));
                    }
                    case "avenger_emblem"-> {
                        player.getInventory().addItem(AvengerEmblem.getItem(plugin));
                    }
                    case "blade_of_grass"-> {
                        player.getInventory().addItem(BladeOfGrass.getItem(plugin));
                    }
                    case "ice_blade"-> {
                        player.getInventory().addItem(IceBlade.getItem(plugin));
                    }
                    case "blowpipe"-> {
                        player.getInventory().addItem(Blowpipe.getItem(plugin));
                    }
                    case "minishark"-> {
                        player.getInventory().addItem(Minishark.getItem(plugin));
                    }
                    case "handgun"-> {
                        player.getInventory().addItem(Handgun.getItem(plugin));
                    }
                    case "shotgun"-> {
                        player.getInventory().addItem(Shotgun.getItem(plugin));
                    }
                    case "needler"-> {
                        player.getInventory().addItem(Needler.getItem(plugin));
                    }
                    case "christmastreesword"-> {
                        player.getInventory().addItem(ChristmasTreeSword.getItem(plugin));
                    }
                    case "mega_shark"-> {
                        player.getInventory().addItem(Megashark.getItem(plugin));
                    }
                    case "sniper_rifle"-> {
                        player.getInventory().addItem(SniperRifle.getItem(plugin));
                    }
                    case "phoenix_blaster"-> {
                        player.getInventory().addItem(PhoenixBlaster.getItem(plugin));
                    }
                    case "torrential_tear"-> {
                        player.getInventory().addItem(TorrentialTear.getItem(plugin));
                    }
                    case "amethyst_staff"-> {
                        player.getInventory().addItem(AmethystStaff.getItem(plugin));
                    }
                    case "ruby_staff"-> {
                        player.getInventory().addItem(RubyStaff.getItem(plugin));
                    }
                    case "mana_crystal"-> {
                        player.getInventory().addItem(ManaCrystal.getItem(plugin));
                    }
                    case "meteor_staff"-> {
                        player.getInventory().addItem(MeteorStaff.getItem(plugin));
                    }
                    case "bubble_gun"-> {
                        player.getInventory().addItem(BubbleGun.getItem(plugin));
                    }
                    case "water_bolt"-> {
                        player.getInventory().addItem(WaterBolt.getItem(plugin));
                    }
                    case "icicle_staff"-> {
                        player.getInventory().addItem(IcicleStaff.getItem(plugin));
                    }
                    case "neptunes_shell"-> {
                        player.getInventory().addItem(NeptunesShell.getItem(plugin));
                    }
                    case "ancient_fossil"-> {
                        player.getInventory().addItem(AncientFossil.getItem(plugin));
                    }
                    case "cactus_armor"-> {
                        player.getInventory().addItem(CactusHelmet.getItem(plugin));
                        player.getInventory().addItem(CactusChestplate.getItem(plugin));
                        player.getInventory().addItem(CactusLeggings.getItem(plugin));
                        player.getInventory().addItem(CactusBoots.getItem(plugin));
                    }
                    case "terra_blade"-> {
                        player.getInventory().addItem(TerraBlade.getItem(plugin));
                    }
                    case "star_cannon"-> {
                        player.getInventory().addItem(StarCannon.getItem(plugin));
                    }
                    case "fallen_star"-> {
                        ItemStack itemStack=FallenStar.getItem(plugin);
                        itemStack.setAmount(20);
                        player.getInventory().addItem(itemStack);
                    }
                    case "super_star_shooter"-> {
                        player.getInventory().addItem(SuperStarShooter.getItem(plugin));
                    }
                    case "sorcerer_emblem"-> {
                        player.getInventory().addItem(SorcererEmblem.getItem(plugin));
                    }
                    case "wooden_crate"-> {
                        player.getInventory().addItem(WoodenCrate.getItem(plugin));
                    }
                    case "iron_crate"-> {
                        player.getInventory().addItem(IronCrate.getItem(plugin));
                    }
                    case "golden_crate"-> {
                        player.getInventory().addItem(GoldenCrate.getItem(plugin));
                    }
                    case "frozen_crate"-> {
                        player.getInventory().addItem(FrozenCrate.getItem(plugin));
                    }
                    case "sky_crate"-> {
                        player.getInventory().addItem(SkyCrate.getItem(plugin));
                    }
                    case "jungle_crate"-> {
                        player.getInventory().addItem(JungleCrate.getItem(plugin));
                    }
                    case "oasis_crate"-> {
                        player.getInventory().addItem(OasisCrate.getItem(plugin));
                    }
                    case "ocean_crate"-> {
                        player.getInventory().addItem(OceanCrate.getItem(plugin));
                    }
                    case "falcon_blade"-> {
                        player.getInventory().addItem(FalconBlade.getItem(plugin));
                    }
                    case "enchanted_sword"-> {
                        player.getInventory().addItem(EnchantedSword.getItem(plugin));
                    }
                    case "tsunami_in_a_bottle"-> {
                        player.getInventory().addItem(TsunamiInABottle.getItem(plugin));
                    }
                    case "anklet_of_the_wind"-> {
                        player.getInventory().addItem(AnkletOfTheWind.getItem(plugin));
                    }
                    case "blizzard_in_a_bottle"-> {
                        player.getInventory().addItem(BlizzardInABottle.getItem(plugin));
                    }
                    case "sandstorm_in_a_bottle"-> {
                        player.getInventory().addItem(SandstormInABottle.getItem(plugin));
                    }
                    case "thunder_zapper"-> {
                        player.getInventory().addItem(ThunderZapper.getItem(plugin));
                    }
                    case "magical_harp"-> {
                        player.getInventory().addItem(MagicalHarp.getItem(plugin));
                    }
                    case "crystal_storm"-> {
                        player.getInventory().addItem(CrystalStorm.getItem(plugin));
                    }
                    case "onyx_blaster"-> {
                        player.getInventory().addItem(OnyxBlaster.getItem(plugin));
                    }
                    case "hoarfrost_bow"-> {
                        player.getInventory().addItem(HoarfrostBow.getItem(plugin));
                    }
                    case "mechanical_shrieker"-> {
                        player.getInventory().addItem(MechanicalShrieker.getItem(plugin));
                    }
                    case "mechanical_egg"-> {
                        player.getInventory().addItem(MechanicalEgg.getItem(plugin));
                    }
                    case "mechanical_skull"-> {
                        player.getInventory().addItem(MechanicalSkull.getItem(plugin));
                    }
                    case "pulse_bow"-> {
                        player.getInventory().addItem(PulseBow.getItem(plugin));
                    }
                    case "bone_pickaxe"-> {
                        player.getInventory().addItem(BonePickaxe.getItem(plugin));
                    }
                    case "night_vision_helmet"-> {
                        player.getInventory().addItem(NightVisionHelmet.getItem(plugin));
                    }
                    case "sand_gun"-> {
                        player.getInventory().addItem(SandGun.getItem(plugin));
                    }
                    case "souls"-> {
                        ItemStack light= SoulOfLight.getItem(plugin);
                        light.setAmount(4);
                        player.getInventory().addItem(light);
                        ItemStack night= SoulOfNight.getItem(plugin);
                        night.setAmount(4);
                        player.getInventory().addItem(night);
                        ItemStack might= SoulOfMight.getItem(plugin);
                        might.setAmount(4);
                        player.getInventory().addItem(might);
                        ItemStack fright= SoulOfFright.getItem(plugin);
                        fright.setAmount(4);
                        player.getInventory().addItem(fright);
                        ItemStack sight= SoulOfSight.getItem(plugin);
                        sight.setAmount(4);
                        player.getInventory().addItem(sight);
                    }
                    case "jungle_armor"-> {
                        player.getInventory().addItem(JungleHat.getItem(plugin));
                        player.getInventory().addItem(JungleShirt.getItem(plugin));
                        player.getInventory().addItem(JungleLeggings.getItem(plugin));
                        player.getInventory().addItem(JunglePants.getItem(plugin));
                    }
                    case "necro_armor"-> {
                        player.getInventory().addItem(NecroHelmet.getItem(plugin));
                        player.getInventory().addItem(NecroBreastplate.getItem(plugin));
                        player.getInventory().addItem(NecroLeggings.getItem(plugin));
                        player.getInventory().addItem(NecroGreaves.getItem(plugin));
                    }
                    case "forbidden_armor"-> {
                        player.getInventory().addItem(ForbiddenMask.getItem(plugin));
                        player.getInventory().addItem(ForbiddenRobes.getItem(plugin));
                        player.getInventory().addItem(ForbiddenLeggings.getItem(plugin));
                        player.getInventory().addItem(ForbiddenTreads.getItem(plugin));
                    }
                    case "forbidden_fragment"-> {
                        player.getInventory().addItem(ForbiddenFragment.getItem(plugin));
                    }
                    case "frost_core"-> {
                        player.getInventory().addItem(FrostCore.getItem(plugin));
                    }
                    case "frost_armor"-> {
                        player.getInventory().addItem(FrostHelmet.getItem(plugin));
                        player.getInventory().addItem(FrostBreastplate.getItem(plugin));
                        player.getInventory().addItem(FrostLeggings.getItem(plugin));
                        player.getInventory().addItem(FrostBoots.getItem(plugin));
                    }
                    case "magic_quiver"-> {
                        player.getInventory().addItem(MagicQuiver.getItem(plugin));
                    }
                    case "wizard_hat"-> {
                        player.getInventory().addItem(WizardHat.getItem(plugin));
                    }
                    case "vampire_knives"-> {
                        player.getInventory().addItem(VampireKnives.getItem(plugin));
                    }
                    case "forbidden_elytra"-> {
                        player.getInventory().addItem(ForbiddenElytra.getItem(plugin));
                    }
                    case "frost_elytra"-> {
                        player.getInventory().addItem(FrostElytra.getItem(plugin));
                    }
                    case "tainted_blade"-> {
                        player.getInventory().addItem(TaintedBlade.getItem(plugin));
                    }
                    case "caustic_edge"-> {
                        player.getInventory().addItem(CausticEdge.getItem(plugin));
                    }
                    case "ice_sickle"-> {
                        player.getInventory().addItem(IceSickle.getItem(plugin));
                    }
                    case "breaker_blade"-> {
                        player.getInventory().addItem(BreakerBlade.getItem(plugin));
                    }
                    case "slap_hand"-> {
                        player.getInventory().addItem(SlapHand.getItem(plugin));
                    }
                    case "laser_rifle"-> {
                        player.getInventory().addItem(LaserRifle.getItem(plugin));
                    }
                    case "clockwork_assault_rifle"-> {
                        player.getInventory().addItem(ClockworkAssaultRifle.getItem(plugin));
                    }
                    case "lesser_mana_potion"-> {
                        player.getInventory().addItem(LesserManaPotion.getItem(plugin));
                    }
                    case "mana_potion"-> {
                        player.getInventory().addItem(ManaPotion.getItem(plugin));
                    }
                    case "greater_mana_potion"-> {
                        player.getInventory().addItem(GreaterManaPotion.getItem(plugin));
                    }
                    case "super_mana_potion"-> {
                        player.getInventory().addItem(SuperManaPotion.getItem(plugin));
                    }
                    case "feral_claws"-> {
                        player.getInventory().addItem(FeralClaws.getItem(plugin));
                    }
                    case "panic_necklace"-> {
                        player.getInventory().addItem(PanicNecklace.getItem(plugin));
                    }
                    case "band_of_starpower"-> {
                        player.getInventory().addItem(BandOfStarpower.getItem(plugin));
                    }
                    case "magic_cuffs"-> {
                        player.getInventory().addItem(MagicCuffs.getItem(plugin));
                    }
                    case "honey_comb"-> {
                        player.getInventory().addItem(HoneyComb.getItem(plugin));
                    }
                    case "honey_balloon"-> {
                        player.getInventory().addItem(HoneyBalloon.getItem(plugin));
                    }
                    case "sweetheart_necklace"-> {
                        player.getInventory().addItem(SweetheartNecklace.getItem(plugin));
                    }
                    case "obsidian_shield"-> {
                        player.getInventory().addItem(ObsidianShield.getItem(plugin));
                    }
                    case "ankh_charm"-> {
                        player.getInventory().addItem(AnkhCharm.getItem(plugin));
                    }
                    case "ankh_shield"-> {
                        player.getInventory().addItem(AnkhShield.getItem(plugin));
                    }
                    case "titan_glove"-> {
                        player.getInventory().addItem(TitanGlove.getItem(plugin));
                    }
                    case "power_glove"-> {
                        player.getInventory().addItem(PowerGlove.getItem(plugin));
                    }
                    case "mechanical_glove"-> {
                        player.getInventory().addItem(MechanicalGlove.getItem(plugin));
                    }
                    case "cross_necklace"-> {
                        player.getInventory().addItem(CrossNecklace.getItem(plugin));
                    }
                    case "magic_dagger"-> {
                        player.getInventory().addItem(MagicDagger.getItem(plugin));
                    }
                    case "star_cloak"-> {
                        player.getInventory().addItem(StarCloak.getItem(plugin));
                    }
                    case "star_veil"-> {
                        player.getInventory().addItem(StarVeil.getItem(plugin));
                    }
                    case "life_crystal"-> {
                        player.getInventory().addItem(LifeCrystal.getItem(plugin));
                    }
                    case "blood_rain_bow"-> {
                        player.getInventory().addItem(BloodRainBow.getItem(plugin));
                    }
                    case "bloody_tear"-> {
                        player.getInventory().addItem(BloodyTear.getItem(plugin));
                    }
                    case "shark_tooth_necklace"-> {
                        player.getInventory().addItem(SharkToothNecklace.getItem(plugin));
                    }
                    case "stinger_necklace"-> {
                        player.getInventory().addItem(StingerNecklace.getItem(plugin));
                    }
                    case "fisher_of_souls"-> {
                        player.getInventory().addItem(FisherOfSouls.getItem(plugin));
                    }
                    case "golden_fishing_rod"-> {
                        player.getInventory().addItem(GoldenFishingRod.getItem(plugin));
                    }
                    case "mechanics_rod"-> {
                        player.getInventory().addItem(MechanicsRod.getItem(plugin));
                    }
                    case "natures_gift"-> {
                        player.getInventory().addItem(NaturesGift.getItem(plugin));
                    }
                    case "mana_flower"-> {
                        player.getInventory().addItem(ManaFlower.getItem(plugin));
                    }
                    case "mana_cloak"-> {
                        player.getInventory().addItem(ManaCloak.getItem(plugin));
                    }
                    case "chum_caster"-> {
                        player.getInventory().addItem(ChumCaster.getItem(plugin));
                    }
                    case "scarab_fishing_rod"-> {
                        player.getInventory().addItem(ScarabFishingRod.getItem(plugin));
                    }
                    case "fiberglass_fishing_pole"-> {
                        player.getInventory().addItem(FiberglassFishingPole.getItem(plugin));
                    }
                    case "sitting_ducks_fishing_pole"-> {
                        player.getInventory().addItem(SittingDucksFishingPole.getItem(plugin));
                    }
                    case "grenade"-> {
                        player.getInventory().addItem(Grenade.getItem(plugin));
                    }
                    case "sticky_grenade"-> {
                        player.getInventory().addItem(StickyGrenade.getItem(plugin));
                    }
                    case "bouncy_grenade"-> {
                        player.getInventory().addItem(BouncyGrenade.getItem(plugin));
                    }
                    case "bomb"-> {
                        player.getInventory().addItem(Bomb.getItem(plugin));
                    }
                    case "sticky_bomb"-> {
                        player.getInventory().addItem(StickyBomb.getItem(plugin));
                    }
                    case "bouncy_bomb"-> {
                        player.getInventory().addItem(BouncyBomb.getItem(plugin));
                    }
                    case "dynamite"-> {
                        player.getInventory().addItem(Dynamite.getItem(plugin));
                    }
                    case "bouncy_dynamite"-> {
                        player.getInventory().addItem(BouncyDynamite.getItem(plugin));
                    }
                    case "sticky_dynamite"-> {
                        player.getInventory().addItem(StickyDynamite.getItem(plugin));
                    }
                    case "spiky_ball"-> {
                        ItemStack item= SpikyBall.getItem(plugin);
                        item.setAmount(11);
                        player.getInventory().addItem(item);
                    }
                    case "cloud_in_a_balloon"-> {
                        player.getInventory().addItem(CloudInABalloon.getItem(plugin));
                    }
                    case "sandstorm_in_a_balloon"-> {
                        player.getInventory().addItem(SandstormInABalloon.getItem(plugin));
                    }
                    case "blizzard_in_a_balloon"-> {
                        player.getInventory().addItem(BlizzardInABalloon.getItem(plugin));
                    }
                    case "amber_horseshoe_balloon"-> {
                        player.getInventory().addItem(AmberHorseshoeBalloon.getItem(plugin));
                    }
                    case "blue_horseshoe_balloon"-> {
                        player.getInventory().addItem(BlueHorseshoeBalloon.getItem(plugin));
                    }
                    case "white_horseshoe_balloon"-> {
                        player.getInventory().addItem(WhiteHorseshoeBalloon.getItem(plugin));
                    }
                    case "yellow_horseshoe_balloon"-> {
                        player.getInventory().addItem(YellowHorseshoeBalloon.getItem(plugin));
                    }
                    case "bundle_of_balloons"-> {
                        player.getInventory().addItem(BundleOfBalloons.getItem(plugin));
                    }
                    case "bundle_of_horseshoe_balloons"-> {
                        player.getInventory().addItem(BundleOfHorseshoeBalloons.getItem(plugin));
                    }
                    default -> player.sendMessage("§cUnknown item: " + itemName);
                }
            }
            case "toggle_message"->{
                playerInstance.toggleMsg(player.getUniqueId());
                playerInstance.save();
            }
            case "accessory"->{
                accessoryManagerInstance.openMenu(player);
            }
            case "vanity"->{
                VanityManager vanityManager=new VanityManager(plugin);
                vanityManager.openVanity(player);
            }
            case "reset_bonuses"->{
                resetInstance.resetBonuses(player);
            }
            case "undiscover" ->{
                CustomRecipeManager.getInstance().undiscoverAll(player);
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            // First argument: subcommands
            List<String> subCommands = Arrays.asList("give","toggle_message","accessory","vanity","reset_bonuses","undiscover");
            StringUtil.copyPartialMatches(args[0], subCommands, completions);
        } else if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            // Second argument: item names
            List<String> items = Arrays.asList("cosmolight","warrior_emblem","bundle_of_horseshoe_balloons","bundle_of_balloons","yellow_horseshoe_balloon","bouncy_grenade","white_horseshoe_balloon","amber_horseshoe_balloon","blue_horseshoe_balloon","sandstorm_in_a_balloon","blizzard_in_a_balloon","cloud_in_a_balloon","spiky_ball","sticky_dynamite","bouncy_dynamite","dynamite","bomb","sticky_bomb","bouncy_bomb","grenade","sticky_grenade","sitting_ducks_fishing_pole","fiberglass_fishing_pole","mana_flower","scarab_fishing_rod","chum_caster","mana_cloak","mechanics_rod","natures_gift","fisher_of_souls","golden_fishing_rod","stinger_necklace","ice_sickle","shark_tooth_necklace","blood_rain_bow","bloody_tear","star_veil","life_crystal","star_cloak","magic_dagger","cross_necklace","titan_glove","mechanical_glove","obsidian_shield","power_glove","ankh_shield","ankh_charm","honey_comb","sweetheart_necklace","honey_balloon","magic_cuffs","band_of_starpower","panic_necklace","mana_potion","feral_claws","greater_mana_potion","super_mana_potion","laser_rifle","lesser_mana_potion","clockwork_assault_rifle","slap_hand","wizard_hat","breaker_blade","frost_elytra","caustic_edge","tainted_blade","forbidden_elytra","vampire_knives","magic_quiver","mechanical_skull","frost_armor","frost_core","forbidden_fragment","forbidden_armor","necro_armor","jungle_armor","sand_gun","night_vision_helmet","mechanical_egg","bone_pickaxe","pulse_bow","golden_crown","mechanical_shrieker","souls","hoarfrost_bow","onyx_blaster","enchanted_sword","crystal_storm","magical_harp","sandstorm_in_a_bottle","thunder_zapper","blizzard_in_a_bottle","anklet_of_the_wind","tsunami_in_a_bottle","wooden_crate","falcon_blade","iron_crate","golden_crate","oasis_crate","sky_crate","ocean_crate","jungle_crate","frozen_crate","sorcerer_emblem","super_star_shooter","star_cannon","fallen_star","cactus_armor","terra_blade","icicle_staff","bubble_gun","ancient_fossil","neptunes_shell","water_bolt","mana_crystal","meteor_staff","christmastreesword","ruby_staff","amethyst_staff","torrential_tear","phoenix_blaster","sniper_rifle","mega_shark","needler","minishark","shotgun","handgun","ice_blade","blowpipe","blade_of_grass","avenger_emblem","hallowed_elytra","pickaxe_axe","hallowed_armour","hallowed_repeater","excalibur","snowball_cannon","might","shackle","molten_elytra","ranger_emblem","shadow_elytra","blindfold","vitamins","fast_clock","Rod_of_Discord","bezoar","hellforge","molten_fury","volcano","counter_scarf","molten_armour","lights_bane","shadow_armour","momentum_Capacitor","stormbow","demonite_bar","cloud_bottle","aglet","obsidian_Skull","red_balloon","band_of_regeneration","lucky_horseshoe","magic_mirror","cobalt_shield");
            StringUtil.copyPartialMatches(args[1], items, completions);
        }

        return completions;
    }

}