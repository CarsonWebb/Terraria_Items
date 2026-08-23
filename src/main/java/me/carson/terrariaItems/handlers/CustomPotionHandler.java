package me.carson.terrariaItems.handlers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import io.netty.channel.epoll.Epoll;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.*;

import java.util.*;


public class CustomPotionHandler implements Listener {

    private final Plugin plugin;
    private static CustomPotionHandler instance;
    private final PlayerDataHandler playerDataHandler=PlayerDataHandler.getInstance();

    public record attributeID(Attribute attr, AttributeModifier mod,String id) {}
    Multimap<Player, attributeID> playerPotions = ArrayListMultimap.create();
    public record attributeModTask(AttributeModifier mod,BukkitTask task) {}
    Multimap<UUID, attributeModTask> activeTasks = ArrayListMultimap.create();

    public record customPotionInfo(String effect, double amount,String id,BukkitTask task) {}
    Multimap<UUID, customPotionInfo> activeCustomPotions= ArrayListMultimap.create();

    public record customFoodInfo(String id,BukkitTask task){}
    HashMap<UUID, customFoodInfo> customFoodList = new HashMap<>();

    ScoreboardManager manager = Bukkit.getScoreboardManager();
    private final Map<UUID, Scoreboard> boards = new HashMap<>();

    public CustomPotionHandler(Plugin plugin){
        this.plugin=plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void potionAddAttribute(Player player,Attribute attribute,double amount, int duration,String id){
        UUID uuid = player.getUniqueId();
        AttributeModifier modifier= new AttributeModifier(new NamespacedKey(plugin,id),amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY);

        if(hasAttributeModifier(player,attribute,modifier)){
            Collection<attributeModTask> tasks = activeTasks.get(uuid);
            tasks.removeIf(value -> {
                if(value.mod.equals(modifier)){
                    value.task.cancel();
                    return true;
                }
                return false;
            });
        }else{
            player.getAttribute(attribute).addModifier(modifier);
            playerPotions.put(player,new attributeID(attribute,modifier,id));
        }

        BukkitTask[] holder = new BukkitTask[1];
        holder[0] = Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.getAttribute(attribute).removeModifier(modifier);
            activeTasks.remove(uuid,new attributeModTask(modifier,holder[0]));
            updateSidebar(player);
        }, duration);
        activeTasks.put(uuid,new attributeModTask(modifier,holder[0]));
        updateSidebar(player);
    }

    public void removePotionAttributes(Player player){
        UUID uuid = player.getUniqueId();
        if(playerPotions.containsKey(player)){
            for(attributeID values : playerPotions.get(player)){
                AttributeInstance instance = player.getAttribute(values.attr());
                if(instance != null) instance.removeModifier(values.mod());
            }
            playerPotions.removeAll(player);
        }
        if(activeTasks.containsKey(uuid)){
            for(attributeModTask value : activeTasks.get(uuid)){
                value.task().cancel();
            }
            activeTasks.removeAll(uuid);
        }
        updateSidebar(player);
    }

    public boolean hasAttributeModifier(Player player,Attribute attribute,AttributeModifier attributeModifier){
        return Objects.requireNonNull(player.getAttribute(attribute)).getModifiers().contains(attributeModifier);
    }

    public void addCustomPotionEffect(Player player,String effect, double amount,int duration,String id){
        UUID uuid= player.getUniqueId();

        for(customPotionInfo values : new ArrayList<>(activeCustomPotions.get(uuid))){
            if(Objects.equals(values.id, id)){
                values.task.cancel();
                removeBonus(effect, amount, uuid);
                activeCustomPotions.remove(uuid, values);
            }
        }

        addBonus(effect,amount,uuid);
        BukkitTask[] holder = new BukkitTask[1];
        holder[0] = Bukkit.getScheduler().runTaskLater(plugin, () -> {
            removeBonus(effect,amount,uuid);
            activeCustomPotions.remove(uuid,new customPotionInfo(effect,amount,id,holder[0]));
            updateSidebar(player);
        }, duration);
        activeCustomPotions.put(uuid,new customPotionInfo(effect,amount,id,holder[0]));
        updateSidebar(player);
    }

    public void removeCustomPotionEffects(Player player){
        UUID uuid = player.getUniqueId();
        Collection<customPotionInfo> potions = activeCustomPotions.removeAll(uuid);
        if(potions.isEmpty()){return;}
        for(customPotionInfo values : potions){
            values.task.cancel();
            removeBonus(values.effect, values.amount, uuid);
        }
        updateSidebar(player);
    }

    private void addBonus(String effect,double amount,UUID uuid){
        switch (effect){
            case "melee" -> {
                playerDataHandler.addBonusMelee(uuid,amount);
            }
            case "ranged" -> {
                playerDataHandler.addBonusRanged(uuid,amount);
            }
            case "magic" -> {
                playerDataHandler.addBonusMagic(uuid,amount);
            }
            case "rogue" -> {
                playerDataHandler.addBonusRogue(uuid,amount);
            }
            case "damage" -> {
                playerDataHandler.addBonusDamage(uuid,amount);
            }
            case "crit" -> {
                playerDataHandler.addCritChance(uuid,amount);
            }
            case "reduction" -> {
                playerDataHandler.addDamageReduction(uuid,amount);
            }
            default -> {
                return;
            }
        }
    }

    private void removeBonus(String effect,double amount,UUID uuid){
        switch (effect){
            case "melee" -> {
                playerDataHandler.subtractBonusMelee(uuid,amount);
            }
            case "ranged" -> {
                playerDataHandler.subtractBonusRanged(uuid,amount);
            }
            case "magic" -> {
                playerDataHandler.subtractBonusMagic(uuid,amount);
            }
            case "rogue" -> {
                playerDataHandler.subtractBonusRogue(uuid,amount);
            }
            case "damage" -> {
                playerDataHandler.subtractBonusDamage(uuid,amount);
            }
            case "crit" -> {
                playerDataHandler.subtractCritChance(uuid,amount);
            }
            case "reduction" -> {
                playerDataHandler.subtractDamageReduction(uuid,amount);
            }
            default -> {
                return;
            }
        }
    }

    public void addCustomFoodEffect(Player player, int duration, String id){
        UUID uuid= player.getUniqueId();

        if(customFoodList.get(uuid)!=null){
            customFoodList.get(uuid).task.cancel();
            removeFoodEffect(player,customFoodList.get(uuid).id);
            customFoodList.remove(uuid);
        }

        addFoodEffect(player,id);
        BukkitTask[] holder = new BukkitTask[1];
        holder[0] = Bukkit.getScheduler().runTaskLater(plugin, () -> {
            removeFoodEffect(player,id);
            customFoodList.remove(uuid);
            updateSidebar(player);
        }, duration);
        customFoodList.put(uuid,new customFoodInfo(id,holder[0]));
        updateSidebar(player);
    }

    public void removeCustomFoodEffect(Player player){
        UUID uuid = player.getUniqueId();
        if(customFoodList.get(uuid)!=null){
            customFoodList.get(uuid).task.cancel();
            removeFoodEffect(player,customFoodList.get(uuid).id);
            customFoodList.remove(uuid);
        }
        updateSidebar(player);
    }

    private void addFoodEffect(Player player,String id){
        UUID uuid=player.getUniqueId();
        switch (id){
            case "Well_Fed" ->{
                player.getAttribute(Attribute.ARMOR).addModifier(new AttributeModifier(new NamespacedKey(plugin,"Well_Fed_ARMOR"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.ATTACK_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"Well_Fed_ATTACK_SPEED"),0.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.MOVEMENT_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"Well_Fed_MOVEMENT_SPEED"),0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.BLOCK_BREAK_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"Well_Fed_BLOCK_BREAK_SPEED"),0.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                addBonus("damage",0.05,uuid);
                addBonus("crit",0.02,uuid);
            }
            case "Plenty_Satisfied" ->{
                player.getAttribute(Attribute.ARMOR).addModifier(new AttributeModifier(new NamespacedKey(plugin,"Plenty_Satisfied_ARMOR"),2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.ATTACK_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"Plenty_Satisfied_ATTACK_SPEED"),0.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.MOVEMENT_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"Plenty_Satisfied_MOVEMENT_SPEED"),0.03, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.BLOCK_BREAK_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"Plenty_Satisfied_BLOCK_BREAK_SPEED"),0.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                addBonus("damage",0.075,uuid);
                addBonus("crit",0.03,uuid);
            }
            case "Exquisitely_Stuffed" ->{
                player.getAttribute(Attribute.ARMOR).addModifier(new AttributeModifier(new NamespacedKey(plugin,"Exquisitely_Stuffed_ARMOR"),4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.ATTACK_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"Exquisitely_Stuffed_ATTACK_SPEED"),0.4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.MOVEMENT_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"Exquisitely_Stuffed_MOVEMENT_SPEED"),0.04, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.BLOCK_BREAK_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"Exquisitely_Stuffed_BLOCK_BREAK_SPEED"),0.4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                addBonus("damage",0.10,uuid);
                addBonus("crit",0.04,uuid);
            }
            default -> {
                return;
            }
        }
    }

    private void removeFoodEffect(Player player,String id){
        UUID uuid=player.getUniqueId();
        switch (id){
            case "Well_Fed" ->{
                player.getAttribute(Attribute.ARMOR).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"Well_Fed_ARMOR"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"Well_Fed_ATTACK_SPEED"),0.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.MOVEMENT_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"Well_Fed_MOVEMENT_SPEED"),0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.BLOCK_BREAK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"Well_Fed_BLOCK_BREAK_SPEED"),0.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                removeBonus("damage",0.05,uuid);
                removeBonus("crit",0.02,uuid);
            }
            case "Plenty_Satisfied" ->{
                player.getAttribute(Attribute.ARMOR).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"Plenty_Satisfied_ARMOR"),2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"Plenty_Satisfied_ATTACK_SPEED"),0.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.MOVEMENT_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"Plenty_Satisfied_MOVEMENT_SPEED"),0.03, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.BLOCK_BREAK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"Plenty_Satisfied_BLOCK_BREAK_SPEED"),0.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                removeBonus("damage",0.075,uuid);
                removeBonus("crit",0.03,uuid);
            }
            case "Exquisitely_Stuffed" ->{
                player.getAttribute(Attribute.ARMOR).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"Exquisitely_Stuffed_ARMOR"),4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"Exquisitely_Stuffed_ATTACK_SPEED"),0.4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.MOVEMENT_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"Exquisitely_Stuffed_MOVEMENT_SPEED"),0.04, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                player.getAttribute(Attribute.BLOCK_BREAK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"Exquisitely_Stuffed_BLOCK_BREAK_SPEED"),0.4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                removeBonus("damage",0.10,uuid);
                removeBonus("crit",0.04,uuid);
            }
            default -> {
                return;
            }
        }
    }

    public void updateSidebar(Player player){
        UUID uuid = player.getUniqueId();
        if(!playerDataHandler.getShowSidebar(uuid)){return;}
        Collection<customPotionInfo> customPotions = activeCustomPotions.get(uuid);
        Collection<attributeID> attributePotions = playerPotions.get(player);

        boolean noCustom = customPotions == null || customPotions.isEmpty();
        boolean noAttribute = attributePotions == null || attributePotions.isEmpty();
        boolean noFood=customFoodList.get(uuid)==null;

        if (noCustom && noAttribute && noFood) {
            removeSidebar(player);
            return;
        }

        Scoreboard board = boards.computeIfAbsent(uuid, k -> {
            Scoreboard b = manager.getNewScoreboard();
            player.setScoreboard(b);
            return b;
        });

        ArrayList<String> potionList = new ArrayList<>();

        for (customPotionInfo value : customPotions) potionList.add(value.id);
        for (attributeID value : attributePotions) potionList.add(value.id);
        if(customFoodList.get(uuid)!=null){potionList.add(customFoodList.get(uuid).id);}

        Objective objective = board.getObjective("potions");
        if (objective == null) {
            objective = board.registerNewObjective("potions", "dummy", ChatColor.DARK_PURPLE + "Active Potions");
        }
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        for (String entry : board.getEntries()) {
            board.resetScores(entry);
        }

        int score = potionList.size();
        for (String line : potionList) {
            objective.getScore(line).setScore(score--);
        }
    }

    public void removeSidebar(Player player){
        UUID uuid = player.getUniqueId();
        Scoreboard board = boards.get(uuid);
        if (board == null) {
            return;
        }
        board.clearSlot(DisplaySlot.SIDEBAR);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        removePotionAttributes(event.getPlayer());
        removeCustomPotionEffects(event.getPlayer());
        removeCustomFoodEffect(event.getPlayer());
        boards.remove(event.getPlayer().getUniqueId());
        removeSidebar(event.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        removePotionAttributes(event.getEntity().getPlayer());
        removeCustomPotionEffects(event.getEntity().getPlayer());
        removeCustomFoodEffect(event.getEntity().getPlayer());
        boards.remove(event.getEntity().getPlayer().getUniqueId());
        removeSidebar(event.getEntity().getPlayer());
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new CustomPotionHandler(plugin);
    }

    public static CustomPotionHandler getInstance() {
        return instance;
    }

}
