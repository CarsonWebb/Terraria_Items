package me.carson.terrariaItems.handlers;

import me.carson.terrariaItems.materialsFolder.materials.ArmoredCavefish;
import me.carson.terrariaItems.materialsFolder.materials.Ebonkoi;
import me.carson.terrariaItems.materialsFolder.materials.Hemopiranha;
import me.carson.terrariaItems.toolFolder.tools.crates.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Biome;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class FishingManager implements Listener {

    private final Plugin plugin;
    private final NamespacedKey customItemId;

    private static final Set<Biome> jungleBiomes = Set.of(Biome.JUNGLE,Biome.BAMBOO_JUNGLE,Biome.SPARSE_JUNGLE);
    private static final Set<Biome> desertBiomes = Set.of(Biome.DESERT,Biome.BADLANDS,Biome.WOODED_BADLANDS,Biome.ERODED_BADLANDS);
    private static final Set<Biome> frozenBiomes = Set.of(Biome.SNOWY_TAIGA,Biome.JAGGED_PEAKS,Biome.FROZEN_PEAKS,Biome.GROVE,Biome.SNOWY_SLOPES,Biome.FROZEN_RIVER,Biome.SNOWY_PLAINS,Biome.ICE_SPIKES);
    private static final Set<Biome> oceanBiomes = Set.of(Biome.BEACH,Biome.OCEAN,Biome.DEEP_OCEAN,Biome.WARM_OCEAN,Biome.LUKEWARM_OCEAN,Biome.DEEP_LUKEWARM_OCEAN,Biome.COLD_OCEAN,Biome.DEEP_COLD_OCEAN,Biome.FROZEN_OCEAN,Biome.DEEP_FROZEN_OCEAN,Biome.MUSHROOM_FIELDS,Biome.SNOWY_BEACH,Biome.STONY_SHORE);
    private static final List<Material> FISHING_TREASURE = List.of(Material.BOW, Material.ENCHANTED_BOOK, Material.FISHING_ROD, Material.NAME_TAG, Material.NAUTILUS_SHELL,Material.SADDLE);
    private static final List<Material> FISHING_JUNK = List.of(Material.LILY_PAD, Material.BOWL, Material.LEATHER, Material.ROTTEN_FLESH, Material.STICK, Material.STRING, Material.WATER_BUCKET, Material.BONE);
    private static final List<Material> TREASURE = List.of(Material.NAME_TAG, Material.NAUTILUS_SHELL,Material.SADDLE);

    public FishingManager(Plugin plugin) {
        this.plugin = plugin;
        customItemId=new NamespacedKey(plugin, "custom_item_id");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    public String getCustomId(ItemStack item){
        if(item==null|| !item.hasItemMeta()){return null;}
        return item.getItemMeta().getPersistentDataContainer().get(customItemId, PersistentDataType.STRING);
    }

    @EventHandler
    public void onFish(PlayerFishEvent event){
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;
        if (!(event.getCaught() instanceof Item caughtItem)) return;
        Player player = event.getPlayer();

        if(!FISHING_TREASURE.contains(caughtItem.getItemStack().getType())){
            double chance= Math.random();
            if(chance<=0.1){
                caughtItem.setItemStack(selectCrate(player));
            } else if (chance<=0.2) {
                ItemStack customFish=selectCustomFish(player);
                if(customFish!=null){
                    caughtItem.setItemStack(customFish);
                }
            }
        }

        String rodId=getCustomId(player.getInventory().getItemInMainHand());
        if(rodId==null){return;}
        switch (rodId){
            case "GoldenFishingRod" ->{
                if(Math.random()<.50){
                    player.getWorld().dropItemNaturally(player.getLocation(), rollBonusCatch(player));
                }else{
                    player.getWorld().dropItemNaturally(player.getLocation(), rollBonusCatch(player));
                    player.getWorld().dropItemNaturally(player.getLocation(), rollBonusCatch(player));
                }

            }
            case "MechanicsRod","SittingDucksFishingPole" ->{
                if(Math.random()<.33){
                    player.getWorld().dropItemNaturally(player.getLocation(), rollBonusCatch(player));
                }
            }

            default -> {
                return;
            }
        }

    }

    private ItemStack rollBonusCatch(Player player) {
        double roll = Math.random();
        if (roll <= 0.08) {//Treasure at 8%
            return new ItemStack(FISHING_TREASURE.get((int) (Math.random() * FISHING_TREASURE.size())));
        }
        else if (roll <= 0.18) { //Crate at 10%
            return selectCrate(player);
        }
        else if (roll <= 0.23) { //junk at 5%
            return new ItemStack(FISHING_JUNK.get((int) (Math.random() * FISHING_JUNK.size())));
        }
        else if (roll <= 0.33) {  //Custom fish at 10%
            ItemStack customFish = selectCustomFish(player);
            if (customFish != null) {
                return customFish;
            }
        }else {
            double fishChance = Math.random();
            if (fishChance < 0.02) {
                return new ItemStack(Material.TROPICAL_FISH, 1);
            } else if (fishChance < 0.15) {
                return new ItemStack(Material.PUFFERFISH, 1);
            } else if (fishChance < 0.40) {
                return new ItemStack(Material.SALMON, 1);
            } else {
                return new ItemStack(Material.COD, 1);
            }
        }
        return new ItemStack(Material.COD, 1);
    }

    public ItemStack selectCustomFish(Player player){
        Location location=player.getLocation();
        if(location.getY()<=45){
            return ArmoredCavefish.getItem(plugin);
        } else if (location.getBlock().getBiome()==Biome.MUSHROOM_FIELDS) {
            return Hemopiranha.getItem(plugin);
        }else if (location.getBlock().getBiome()==Biome.MANGROVE_SWAMP) {
            return Ebonkoi.getItem(plugin);
        }
        return null;
    }

    public ItemStack selectCrate(Player player){
        double crateChance =Math.random();
        if(selectBiomeCrate(player)!=null){//Biome Crate Loot table
            if(crateChance<0.1){ //10% Gold
                return GoldenCrate.getItem(plugin);
            } else if (crateChance<0.3) { //20% Biome
                return selectBiomeCrate(player);
            }else if(crateChance<0.5){ //20% Iron
                return IronCrate.getItem(plugin);
            }else { //50% Wood
                return WoodenCrate.getItem(plugin);
            }
        }else{
            if(crateChance<0.1){ //10% Gold
                return GoldenCrate.getItem(plugin);
            } else if (crateChance<0.4) { //30% Iron
                return IronCrate.getItem(plugin);
            }else{ //60% Wood
                return WoodenCrate.getItem(plugin);
            }
        }
    }

    public ItemStack selectBiomeCrate(Player player){
        Biome biome= player.getLocation().getBlock().getBiome();
        double height=player.getLocation().getY();

        if(height>=175){
            return SkyCrate.getItem(plugin);
        }else if(jungleBiomes.contains(biome)){
            return JungleCrate.getItem(plugin);
        } else if (desertBiomes.contains(biome)) {
            return OasisCrate.getItem(plugin);
        } else if (frozenBiomes.contains(biome)) {
            return FrozenCrate.getItem(plugin);
        } else if (oceanBiomes.contains(biome)) {
            return OceanCrate.getItem(plugin);
        }else{
            return null;
        }
    }
}
