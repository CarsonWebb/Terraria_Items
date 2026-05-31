package me.carson.terrariaItems.armorFolder;

import me.carson.terrariaItems.armorFolder.armors.cactusArmor.CactusBoots;
import me.carson.terrariaItems.armorFolder.armors.cactusArmor.CactusChestplate;
import me.carson.terrariaItems.armorFolder.armors.cactusArmor.CactusHelmet;
import me.carson.terrariaItems.armorFolder.armors.cactusArmor.CactusLeggings;
import me.carson.terrariaItems.armorFolder.armors.desertProwlerArmor.DesertProwlerHat;
import me.carson.terrariaItems.armorFolder.armors.forbiddenArmor.ForbiddenLeggings;
import me.carson.terrariaItems.armorFolder.armors.forbiddenArmor.ForbiddenMask;
import me.carson.terrariaItems.armorFolder.armors.forbiddenArmor.ForbiddenRobes;
import me.carson.terrariaItems.armorFolder.armors.forbiddenArmor.ForbiddenTreads;
import me.carson.terrariaItems.armorFolder.armors.frostArmor.FrostBoots;
import me.carson.terrariaItems.armorFolder.armors.frostArmor.FrostBreastplate;
import me.carson.terrariaItems.armorFolder.armors.frostArmor.FrostHelmet;
import me.carson.terrariaItems.armorFolder.armors.frostArmor.FrostLeggings;
import me.carson.terrariaItems.armorFolder.armors.hallowedArmor.*;
import me.carson.terrariaItems.armorFolder.armors.jungleArmor.JungleHat;
import me.carson.terrariaItems.armorFolder.armors.jungleArmor.JungleLeggings;
import me.carson.terrariaItems.armorFolder.armors.jungleArmor.JunglePants;
import me.carson.terrariaItems.armorFolder.armors.jungleArmor.JungleShirt;
import me.carson.terrariaItems.armorFolder.armors.moltenArmor.*;
import me.carson.terrariaItems.armorFolder.armors.necroArmor.NecroBreastplate;
import me.carson.terrariaItems.armorFolder.armors.necroArmor.NecroGreaves;
import me.carson.terrariaItems.armorFolder.armors.necroArmor.NecroHelmet;
import me.carson.terrariaItems.armorFolder.armors.necroArmor.NecroLeggings;
import me.carson.terrariaItems.armorFolder.armors.shadowArmor.*;
import me.carson.terrariaItems.armorFolder.armors.timArmor.WizardHat;
import me.carson.terrariaItems.listeners.ArmorChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ArmorManager implements Listener {

    private final HashMap<String, Armor> armorList = new HashMap<>();
    private final NamespacedKey armorKey;
    protected final Plugin plugin;
    private static ArmorManager instance;


    public ArmorManager(Plugin plugin) {
        armorKey = new NamespacedKey(plugin, "custom_item_id");
        this.plugin=plugin;

        armorList.put("ShadowHelmet",new ShadowHelmet(plugin));
        armorList.put("ShadowScalemail",new ShadowScalemail(plugin));
        armorList.put("ShadowLeggings",new ShadowLeggings(plugin));
        armorList.put("ShadowGreaves",new ShadowGreaves(plugin));
        armorList.put("ShadowElytra",new ShadowElytra(plugin));
        armorList.put("MoltenHelmet",new MoltenHelmet(plugin));
        armorList.put("MoltenChestplate",new MoltenChestplate(plugin));;
        armorList.put("MoltenLeggings",new MoltenLeggings(plugin));
        armorList.put("MoltenBoots",new MoltenBoots(plugin));
        armorList.put("MoltenElytra",new MoltenElytra(plugin));
        armorList.put("HallowedHelmet",new HallowedHelmet(plugin));
        armorList.put("HallowedMask",new HallowedMask(plugin));
        armorList.put("HallowedHeadgear",new HallowedHeadgear(plugin));
        armorList.put("HallowedChestplate",new HallowedChestplate(plugin));
        armorList.put("HallowedLeggings",new HallowedLeggings(plugin));
        armorList.put("HallowedBoots",new HallowedBoots(plugin));
        armorList.put("HallowedElytra",new HallowedElytra(plugin));
        armorList.put("CactusHelmet",new CactusHelmet(plugin));
        armorList.put("CactusChestplate",new CactusChestplate(plugin));
        armorList.put("CactusLeggings",new CactusLeggings(plugin));
        armorList.put("CactusBoots",new CactusBoots(plugin));
        armorList.put("JungleHat",new JungleHat(plugin));
        armorList.put("JungleShirt",new JungleShirt(plugin));
        armorList.put("JungleLeggings",new JungleLeggings(plugin));
        armorList.put("JunglePants",new JunglePants(plugin));
        armorList.put("NecroHelmet",new NecroHelmet(plugin));
        armorList.put("NecroBreastplate",new NecroBreastplate(plugin));
        armorList.put("NecroLeggings",new NecroLeggings(plugin));
        armorList.put("NecroGreaves",new NecroGreaves(plugin));
        armorList.put("ForbiddenMask",new ForbiddenMask(plugin));
        armorList.put("ForbiddenRobes",new ForbiddenRobes(plugin));
        armorList.put("ForbiddenLeggings",new ForbiddenLeggings(plugin));
        armorList.put("ForbiddenTreads",new ForbiddenTreads(plugin));
        armorList.put("FrostHelmet",new FrostHelmet(plugin));
        armorList.put("FrostBreastplate",new FrostBreastplate(plugin));
        armorList.put("FrostLeggings",new FrostLeggings(plugin));
        armorList.put("FrostBoots",new FrostBoots(plugin));
        armorList.put("WizardHat",new WizardHat(plugin));
        armorList.put("DesertProwlerHat",new DesertProwlerHat(plugin));

        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getPluginManager().registerEvents(new MoltenHelmet(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new HallowedChestplate(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new CactusHelmet(plugin), plugin);
    }

    public Armor getArmorPiece(ItemStack item){
        if(item==null|| !item.hasItemMeta()){return null;}
        String armorId= item.getItemMeta().getPersistentDataContainer().get(armorKey, PersistentDataType.STRING);
        return armorList.get(armorId);
    }

    @EventHandler
    public void onArmorChange(ArmorChangeEvent event){
        Player player = event.getPlayer();
        ItemStack old = event.getOldItem();
        ItemStack current = event.getNewItem();
        if(getArmorPiece(old)!=null){getArmorPiece(old).deactivateArmorEffect(player);}
        if(getArmorPiece(current)!=null){getArmorPiece(current).activateArmorEffect(player);}
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if(player.getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)){return;}
        for (ItemStack armor : player.getInventory().getArmorContents()) {
            if(getArmorPiece(armor)!=null){
                getArmorPiece(armor).deactivateArmorEffect(player);}
        }
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new ArmorManager(plugin);
    }

    public static ArmorManager getInstance() {
        return instance;
    }
}
