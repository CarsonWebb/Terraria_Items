package me.carson.terrariaItems.accesoryFolder;

import me.carson.terrariaItems.accesoryFolder.accessories.*;
import me.carson.terrariaItems.handlers.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class AccessoryManager implements Listener {
    private final HashMap<String, Accessory> accessoryList = new HashMap<>();
    private final NamespacedKey accessoryKey;
    private final NamespacedKey unmovableKey;
    private final PlayerDataHandler playerDataInstance = PlayerDataHandler.getInstance();
    private static AccessoryManager instance;
    protected final Plugin plugin;

    public AccessoryManager(Plugin plugin) {
        accessoryKey = new NamespacedKey(plugin, "custom_item_id");
        unmovableKey=new NamespacedKey(plugin, "unmovable");
        this.plugin=plugin;

        accessoryList.put("Aglet",new Aglet(plugin));
        accessoryList.put("ObsidianSkull",new ObsidianSkull(plugin));
        accessoryList.put("RedBalloon",new RedBalloon(plugin));
        accessoryList.put("BandOfRegeneration",new BandOfRegeneration(plugin));
        accessoryList.put("CloudInABottle",new CloudInABottle(plugin));
        accessoryList.put("LuckyHorseshoe",new LuckyHorseshoe(plugin));
        accessoryList.put("CobaltShield",new CobaltShield(plugin));
        accessoryList.put("CounterScarf",new CounterScarf(plugin));
        accessoryList.put("AncientFossil",new AncientFossil(plugin));
        accessoryList.put("NeptunesShell",new NeptunesShell(plugin));
        accessoryList.put("Bezoar",new Bezoar(plugin));
        accessoryList.put("Blindfold",new Blindfold(plugin));
        accessoryList.put("FastClock",new FastClock(plugin));
        accessoryList.put("Vitamins",new Vitamins(plugin));
        accessoryList.put("WarriorEmblem",new WarriorEmblem(plugin));
        accessoryList.put("RangerEmblem",new RangerEmblem(plugin));
        accessoryList.put("SorcererEmblem",new SorcererEmblem(plugin));
        accessoryList.put("Shackle",new Shackle(plugin));
        accessoryList.put("AvengerEmblem",new AvengerEmblem(plugin));
        accessoryList.put("NightVisionHelmet",new NightVisionHelmet(plugin));
        accessoryList.put("TsunamiInABottle",new TsunamiInABottle(plugin));
        accessoryList.put("AnkletOfTheWind",new AnkletOfTheWind(plugin));
        accessoryList.put("BlizzardInABottle",new BlizzardInABottle(plugin));
        accessoryList.put("SandstormInABottle",new SandstormInABottle(plugin));
        accessoryList.put("AncientChisel",new AncientChisel(plugin));
        accessoryList.put("Flipper",new Flipper(plugin));
        accessoryList.put("MagicQuiver",new MagicQuiver(plugin));
        accessoryList.put("FeralClaws",new FeralClaws(plugin));
        accessoryList.put("PanicNecklace",new PanicNecklace(plugin));
        accessoryList.put("BandOfStarpower",new BandOfStarpower(plugin));
        accessoryList.put("ManaRegenerationBand",new ManaRegenerationBand(plugin));
        accessoryList.put("MagicCuffs",new MagicCuffs(plugin));
        accessoryList.put("HoneyComb",new HoneyComb(plugin));
        accessoryList.put("HoneyBalloon",new HoneyBalloon(plugin));
        accessoryList.put("SweetheartNecklace",new SweetheartNecklace(plugin));
        accessoryList.put("ObsidianShield",new ObsidianShield(plugin));
        accessoryList.put("AnkhCharm",new AnkhCharm(plugin));
        accessoryList.put("AnkhShield",new AnkhShield(plugin));
        accessoryList.put("ObsidianHorseshoe",new ObsidianHorseshoe(plugin));
        accessoryList.put("TitanGlove",new TitanGlove(plugin));
        accessoryList.put("PowerGlove",new PowerGlove(plugin));
        accessoryList.put("MechanicalGlove",new MechanicalGlove(plugin));
        accessoryList.put("CrossNecklace",new CrossNecklace(plugin));
        accessoryList.put("StarCloak",new StarCloak(plugin));
        accessoryList.put("BeeCloak",new BeeCloak(plugin));
        accessoryList.put("StarVeil",new StarVeil(plugin));
        accessoryList.put("SharkToothNecklace",new SharkToothNecklace(plugin));
        accessoryList.put("StingerNecklace",new StingerNecklace(plugin));
        accessoryList.put("NaturesGift",new NaturesGift(plugin));
        accessoryList.put("ManaFlower",new ManaFlower(plugin));
        accessoryList.put("ManaCloak",new ManaCloak(plugin));
        accessoryList.put("CloudInABalloon",new CloudInABalloon(plugin));
        accessoryList.put("SandstormInABalloon",new SandstormInABalloon(plugin));
        accessoryList.put("BlizzardInABalloon",new BlizzardInABalloon(plugin));
        accessoryList.put("AmberHorseshoeBalloon",new AmberHorseshoeBalloon(plugin));
        accessoryList.put("BlueHorseshoeBalloon",new BlueHorseshoeBalloon(plugin));
        accessoryList.put("WhiteHorseshoeBalloon",new WhiteHorseshoeBalloon(plugin));
        accessoryList.put("YellowHorseshoeBalloon",new YellowHorseshoeBalloon(plugin));
        accessoryList.put("BundleOfBalloons",new BundleOfBalloons(plugin));
        accessoryList.put("BundleOfHorseshoeBalloons",new BundleOfHorseshoeBalloons(plugin));
        accessoryList.put("CoinOfDeceit",new CoinOfDeceit(plugin));
        accessoryList.put("RuinMedallion",new RuinMedallion(plugin));
        accessoryList.put("RougeEmblem",new RougeEmblem(plugin));
        accessoryList.put("SilencingSheath",new SilencingSheath(plugin));

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public Accessory getAccessory(ItemStack item){
        if(item==null|| !item.hasItemMeta()){return null;}
        String accessoryId= item.getItemMeta().getPersistentDataContainer().get(accessoryKey, PersistentDataType.STRING);
        return accessoryList.get(accessoryId);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!event.getView().getTitle().equals("Accessories Inventory")) {return;}
        Player player = (Player) event.getPlayer();
        Inventory inv= event.getInventory();
        List<ItemStack> invList = new ArrayList<>();
        for(int i =0;i<5;i++){
            invList.add(i,inv.getItem(i+2));
        }
        playerDataInstance.setInventory(player.getUniqueId(), invList);
        playerDataInstance.save();
        for(ItemStack item:invList){
            Accessory accessory=getAccessory(item);
            if(accessory!=null){
                accessory.activateEffect(player);
            }
        }
    }

    public void openMenu(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "Accessories Inventory");

        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§r");
        meta.getPersistentDataContainer().set(unmovableKey, PersistentDataType.BYTE, (byte) 1);
        item.setItemMeta(meta);

        gui.setItem(0, item);
        gui.setItem(1, item);
        gui.setItem(7, item);
        gui.setItem(8, item);

        List<ItemStack> invList= playerDataInstance.getInventory(player.getUniqueId());
        if(invList!=null) {
            for (int i = 0; i < 5; i++) {
                gui.setItem(i + 2, invList.get(i));
            }
        }
        if(invList!=null){
            for(ItemStack invItem:invList){
                Accessory accessory=getAccessory(invItem);
                if(accessory!=null){
                    accessory.deactivateEffect(player);
                }
            }
        }

        player.openInventory(gui);
    }

    //Prevents players from removing the gray stained-glass
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Accessories Inventory")) return;

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null) return;
        ItemMeta clickedMeta=clicked.getItemMeta();

        if (clickedMeta.getPersistentDataContainer().has(unmovableKey)) {
            event.setCancelled(true);
        }
    }


    public static void initialize(JavaPlugin plugin) {
        instance = new AccessoryManager(plugin);
    }

    public static AccessoryManager getInstance() {
        return instance;
    }

}