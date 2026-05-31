package me.carson.terrariaItems.listeners;

import me.carson.terrariaItems.handlers.WorldDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public class CustomCraftingListener implements Listener {

    private final NamespacedKey customItemKey;
    private final List<NamespacedKey> preHardmodeRecipes;
    private final List<NamespacedKey> hardmodeRecipes;
    private final WorldDataHandler worldDataHandler=WorldDataHandler.getInstance();

    public CustomCraftingListener(JavaPlugin plugin) {
        this.customItemKey = new NamespacedKey(plugin, "custom_item_id");
        Bukkit.getPluginManager().registerEvents(this, plugin);

        preHardmodeRecipes = List.of(
                new NamespacedKey(plugin, "aglet"),
                new NamespacedKey(plugin, "skull"),
                new NamespacedKey(plugin, "band"),
                new NamespacedKey(plugin, "CobaltShield"),
                new NamespacedKey(plugin, "CounterScarf"),
                //new NamespacedKey(plugin, "Bezoar"),
                //new NamespacedKey(plugin, "FastClock"),
                new NamespacedKey(plugin, "NightVisionHelmet"),
                new NamespacedKey(plugin, "PanicNecklace"),
                new NamespacedKey(plugin, "BandOfStarpower"),
                new NamespacedKey(plugin, "ManaRegenerationBand"),
                new NamespacedKey(plugin, "MagicCuffs"),
                new NamespacedKey(plugin, "HoneyComb"),
                new NamespacedKey(plugin, "HoneyBalloon"),
                new NamespacedKey(plugin, "SweetheartNecklace"),
                new NamespacedKey(plugin, "ObsidianHorseshoe"),
                new NamespacedKey(plugin, "StingerNecklace"),
                new NamespacedKey(plugin, "ManaFlower"),
                new NamespacedKey(plugin, "ObsidianShield")
        );

        hardmodeRecipes = List.of(
                new NamespacedKey(plugin, "NeptuneShell"),
                new NamespacedKey(plugin, "AnkhCharm"),
                new NamespacedKey(plugin, "Vitamins"),
                new NamespacedKey(plugin, "Blindfold"),
                new NamespacedKey(plugin, "AnkhCharm"),
                new NamespacedKey(plugin, "AnkhShield"),
                new NamespacedKey(plugin, "PowerGlove"),
                new NamespacedKey(plugin, "BeeCloak"),
                new NamespacedKey(plugin, "StarVeil"),
                new NamespacedKey(plugin, "MechanicalGlove"),
                new NamespacedKey(plugin, "ManaCloak"),
                new NamespacedKey(plugin, "AvengerEmblem1"),
                new NamespacedKey(plugin, "AvengerEmblem2"),
                new NamespacedKey(plugin, "AvengerEmblem3")
        );
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        CraftingInventory inv = event.getInventory();
        ItemStack[] matrix = inv.getMatrix();
        ItemStack result=inv.getResult();
        if(result!=null){
            if(hasCustom(matrix)&&getCustomKey(result)==null){
                inv.setResult(null);
            } else if (event.getRecipe() instanceof Keyed keyed) {
                String key = keyed.getKey().getKey();
                if(!isAllowed(getPrefix(key))){
                    inv.setResult(null);
                }
            }
        }
    }

    public Boolean isAllowed(String prefix){
        if(!worldDataHandler.getPreHardmodeRecipes()){
            if(Objects.equals(prefix, "pre")){
                return false;
            }
        }
        if(!worldDataHandler.getHardmodeRecipes()){
            if(Objects.equals(prefix, "hm")){
                return false;
            }
        }
        return true;
    }

    public String getPrefix(String key){
        if(key==null){return "";}
        int idx = key.indexOf('_');
        return idx != -1 ? key.substring(0, idx) : "";
    }

    public Boolean hasCustom(ItemStack[] matrix){
        for (ItemStack item : matrix) {
            if(item!=null){
                if(getCustomKey(item)!=null){
                    return true;
                }
            }
        }
        return false;
    }

    public String getCustomKey(ItemStack item){
        if(item==null|| !item.hasItemMeta()){return null;}
        return item.getItemMeta().getPersistentDataContainer().get(customItemKey, PersistentDataType.STRING);
    }

    @EventHandler
    public void onAnvil(PrepareAnvilEvent event) {
        ItemStack item = event.getResult();
        if (getCustomKey(item)!=null){
            event.setResult(null);
        }
    }

    @EventHandler
    public void onSmithing(PrepareSmithingEvent event) {
        ItemStack item = event.getInventory().getItem(1);
        if(getCustomKey(item)!=null){
            event.setResult(null);
        }
    }

    @EventHandler
    public void onEnchant(PrepareItemEnchantEvent event){
        ItemStack itemStack = event.getItem();
        if(getCustomKey(itemStack)!=null){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPotion(BrewEvent event)   {
        ItemStack[] contents=event.getContents().getContents();
        for(ItemStack item:contents){
            if(getCustomKey(item)!=null){
                event.setCancelled(true);
            }
        }
    }


}
