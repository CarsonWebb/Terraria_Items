package me.carson.terrariaItems.listeners;

import me.carson.terrariaItems.accesoryFolder.accessories.*;
import me.carson.terrariaItems.handlers.WorldDataHandler;
import me.carson.terrariaItems.materialsFolder.materials.ForbiddenFragment;
import me.carson.terrariaItems.materialsFolder.materials.FrostCore;
import me.carson.terrariaItems.miscFolder.fishingRods.ChumCaster;
import me.carson.terrariaItems.toolFolder.tools.summons.BloodyTear;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows.BloodRainBow;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons.MagicDagger;
import me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.throwablesFolder.Bomb;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.ThreadLocalRandom;

public class MobDeathListeners implements Listener {

    private final Plugin plugin;
    private final WorldDataHandler worldDataInstance=WorldDataHandler.getInstance();
    private final NamespacedKey key;

    public MobDeathListeners(Plugin plugin){
        this.plugin=plugin;
        key = new NamespacedKey(plugin, "custom_enemy");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public ItemStack getMimicLoot() {
        int x = ThreadLocalRandom.current().nextInt(1, 5);
        switch (x) {
            case 1 -> {
                return TitanGlove.getItem(plugin);
            }
            case 2 -> {
                return CrossNecklace.getItem(plugin);
            }
            case 3 -> {
                return MagicDagger.getItem(plugin);
            }
            case 4 -> {
                return StarCloak.getItem(plugin);
            }
            default -> {
            }
        }
        return null;
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        EntityType entityType = e.getEntity().getType();
        double rand=Math.random();
        if (e.getEntity().getType() == EntityType.ZOMBIE){
            if(rand<0.02){
                e.getDrops().add(Shackle.getItem(plugin));
                return;
            }
        }
        if(worldDataInstance.getHardmode()){    //HARDMODE DROPS
            switch (entityType){
                case BOGGED ->{
                    if(rand<0.1){
                        e.getDrops().add(Bezoar.getItem(plugin));
                    }
                }
                case EVOKER->{
                    if(rand<0.15){
                        ItemStack custom = SorcererEmblem.getItem(plugin);
                        e.getDrops().add(custom);
                    }
                }
                case BLAZE -> {
                    if(rand<0.05){
                        ItemStack custom = RangerEmblem.getItem(plugin);
                        e.getDrops().add(custom);
                    }
                }
                case WITHER_SKELETON -> {
                    if(rand<0.05){
                        ItemStack custom = WarriorEmblem.getItem(plugin);
                        e.getDrops().add(custom);
                    }
                }
                case STRAY -> {
                    if(rand<0.1){
                        ItemStack custom = FastClock.getItem(plugin);
                        e.getDrops().add(custom);
                    }
                }
                case SHULKER -> {
                    if(rand<0.2){
                        e.getDrops().add(getMimicLoot());
                    }
                }
                case BREEZE -> {
                    if(rand<0.05){
                        ItemStack custom = RougeEmblem.getItem(plugin);
                        e.getDrops().add(custom);
                    }
                }
                case ENDER_DRAGON -> {
                    worldDataInstance.setHardmode(true);
                }
                default -> {
                }
            }

        }

    }

    @EventHandler
    public void onCustomDeath(EntityDeathEvent event) {
        String id = event.getEntity().getPersistentDataContainer().get(key, PersistentDataType.STRING);
        if (id == null) {
            return;
        }
        switch (id) {
            case "UndeadMiner" -> {
                if (Math.random() < 0.72) {
                    int tnt = ThreadLocalRandom.current().nextInt(1, 4);
                    ItemStack item = new ItemStack(Bomb.getItem(plugin));
                    item.setAmount(tnt);
                    event.getDrops().add(item);
                }
                if (Math.random() < 0.1) {
                    ItemStack itemStack = new ItemStack(Material.COOKED_BEEF);
                    event.getDrops().add(itemStack);
                }
            }
            case "SkeletonArcher" -> {
                if (Math.random() < 0.025) {
                    event.getDrops().add(MagicQuiver.getItem(plugin));
                }
            }
            case "SandElemental" -> {
                event.getDrops().clear();
                event.getDrops().add(ForbiddenFragment.getItem(plugin));
            }
            case "IceGolem" -> {
                event.getDrops().clear();
                event.getDrops().add(FrostCore.getItem(plugin));
            }
            case "BloodZombie" ->{
                if(Math.random()<0.01){
                    event.getDrops().add(BloodyTear.getItem(plugin));
                }
                if(Math.random()<0.013){
                    event.getDrops().add(SharkToothNecklace.getItem(plugin));
                }
            }
            case "TheGroom", "TheBride" ->{
                if(Math.random()<0.2){
                    event.getDrops().add(BloodyTear.getItem(plugin));
                }
            }
            case "ZombieMerman" ->{
                if(Math.random()<0.04){
                    event.getDrops().add(BloodyTear.getItem(plugin));
                }
                if(Math.random()<0.125){
                    event.getDrops().add(BloodRainBow.getItem(plugin));
                }
                if(Math.random()<0.125){
                    event.getDrops().add(ChumCaster.getItem(plugin));
                }
            }
            default -> {

            }
        }
    }

}
