package me.carson.terrariaItems.handlers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class PlayerDataHandler implements Listener {

    private static PlayerDataHandler instance;
    private final File file;
    private final YamlConfiguration config;
    private final Plugin plugin;

    public PlayerDataHandler(Plugin plugin){
        this.plugin=plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        file = new File(plugin.getDataFolder(), "playerData.yml");
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public Boolean getShowMsg(UUID id){
        return config.getBoolean(id +".show_message",true);
    }
    public void setShowMsg(UUID id,Boolean set){
        config.set(id +".show_message",set);
    }
    public void toggleMsg(UUID id){
        setShowMsg(id,!getShowMsg(id));
    }

    public double getMaxMana(UUID id){
        return config.getDouble(id +".max_mana",20);
    }
    public void setMaxMana(UUID id, double x){
        config.set(id +".max_mana",Math.max(x,20));
    }

    public double getMaxStealth(UUID id){
        return config.getDouble(id +".max_stealth",0);
    }
    public void setMaxStealth(UUID id, double x){
        config.set(id +".max_stealth",Math.max(x,0));
    }
    public void addMaxStealth(UUID id, double add){setMaxStealth(id,getMaxStealth(id)+add);}
    public void subtractMaxStealth(UUID id, double minus){setMaxStealth(id,Math.max((getMaxStealth(id)-minus),0));}

    public double getExtraMana(UUID id){
        return config.getDouble(id +".extra_mana",0);
    }
    public void setExtraMana(UUID id, double x){config.set(id +".extra_mana",Math.max(x,0));}
    public void addExtraMana(UUID id, double add){setExtraMana(id,getExtraMana(id)+add);}
    public void subtractExtraMana(UUID id, double minus){setExtraMana(id,Math.max((getExtraMana(id)-minus),0));}

    public double getManaReduction(UUID id){
        return config.getDouble(id +".mana_reduction",0);
    }
    public void setManaReduction(UUID id, double x){config.set(id +".mana_reduction",Math.max(x,0));}
    public void addManaReduction(UUID id, double add){setManaReduction(id,getManaReduction(id)+add);}
    public void subtractManaReduction(UUID id, double minus){setManaReduction(id,Math.max((getManaReduction(id)-minus),0));}
    
    public List<ItemStack> getInventory(UUID id){
        return (List<ItemStack>) config.getList(id+".accessory_inv");
    }
    public void setInventory(UUID id,List<ItemStack> list){
        config.set(id+".accessory_inv",list);
    }

    public List<ItemStack> getVanity(UUID id){
        return (List<ItemStack>) config.getList(id+".vanity_inv");
    }
    public void setVanity(UUID id,List<ItemStack> list){
        config.set(id+".vanity_inv",list);
    }

    public double getBonusDamage(UUID id){return config.getDouble(id +".bonus_damage",0);}
    public void setBonusDamage(UUID id, double bonus){config.set(id +".bonus_damage",bonus);}
    public void addBonusDamage(UUID id,double add){setBonusDamage(id,getBonusDamage(id)+add);}
    public void subtractBonusDamage(UUID id,double minus){setBonusDamage(id,Math.max(getBonusDamage(id)-minus,0));}

    public double getBonusMelee(UUID id){return config.getDouble(id +".bonus_melee",0);}
    public void setBonusMelee(UUID id, double bonus){config.set(id +".bonus_melee",bonus);}
    public void addBonusMelee(UUID id,double add){setBonusMelee(id,getBonusMelee(id)+add);}
    public void subtractBonusMelee(UUID id,double minus){setBonusMelee(id,Math.max(getBonusMelee(id)-minus,0));}

    public double getBonusRanged(UUID id){return config.getDouble(id +".bonus_ranged",0);}
    public void setBonusRanged(UUID id, double bonus){config.set(id +".bonus_ranged",bonus);}
    public void addBonusRanged(UUID id,double add){setBonusRanged(id,getBonusRanged(id)+add);}
    public void subtractBonusRanged(UUID id,double minus){setBonusRanged(id,Math.max(getBonusRanged(id)-minus,0));}

    public double getBonusMagic(UUID id){return config.getDouble(id +".bonus_magic",0);}
    public void setBonusMagic(UUID id, double bonus){config.set(id +".bonus_magic",bonus);}
    public void addBonusMagic(UUID id,double add){setBonusMagic(id,getBonusMagic(id)+add);}
    public void subtractBonusMagic(UUID id,double minus){setBonusMagic(id,Math.max(getBonusMagic(id)-minus,0));}

    public double getDamageReduction(UUID id){return config.getDouble(id +".damage_reduction",0);}
    public void setDamageReduction(UUID id, double bonus){config.set(id +".damage_reduction",bonus);}
    public void addDamageReduction(UUID id,double add){setDamageReduction(id,getDamageReduction(id)+add);}
    public void subtractDamageReduction(UUID id,double minus){setDamageReduction(id,Math.max(getDamageReduction(id)-minus,0));}

    public double getCritChance(UUID id){return config.getDouble(id +".crit_chance",0);}
    public void setCritChance(UUID id, double bonus){config.set(id +".crit_chance",bonus);}
    public void addCritChance(UUID id,double add){setCritChance(id,getCritChance(id)+add);}
    public void subtractCritChance(UUID id,double minus){setCritChance(id,Math.max(getCritChance(id)-minus,0));}

    public double getStealthThreshold(UUID id){
        return config.getDouble(id +".stealth_threshold",1);
    }
    public void setStealthThreshold(UUID id, double x){
        config.set(id +".stealth_threshold",Math.max(x,0));
    }
    public void addStealthThreshold(UUID id, double add){setStealthThreshold(id,getStealthThreshold(id)+add);}
    public void subtractStealthThreshold(UUID id, double minus){setStealthThreshold(id,Math.max((getStealthThreshold(id)-minus),0));}

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new PlayerDataHandler(plugin);
    }

    public static PlayerDataHandler getInstance() {
        return instance;
    }

}
