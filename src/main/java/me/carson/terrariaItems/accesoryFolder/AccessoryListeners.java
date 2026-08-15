package me.carson.terrariaItems.accesoryFolder;

import me.carson.terrariaItems.accesoryFolder.accessories.*;
import me.carson.terrariaItems.handlers.PlayerDataHandler;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.*;


public class AccessoryListeners implements Listener {

    private final Plugin plugin;
    private NamespacedKey customItemKey;
    private final PlayerDataHandler playerDataInstance=PlayerDataHandler.getInstance();
    private final AccessoryManager accessoryManagerInstance= AccessoryManager.getInstance();
    private static final Set<Biome> jungleBiomes = Set.of(Biome.JUNGLE,Biome.BAMBOO_JUNGLE,Biome.SPARSE_JUNGLE);
    private final HashMap<UUID,Integer> usedJumps = new HashMap<UUID, Integer>();
    public final Set<String> DOUBLE_JUMPS = Set.of("BundleOfBalloons","BundleOfHorseshoeBalloons","CloudInABottle","TsunamiInABottle","BlizzardInABottle","SandstormInABottle","CloudInABalloon","BlizzardInABalloon","SandstormInABalloon","BlueHorseshoeBalloon","WhiteHorseshoeBalloon","YellowHorseshoeBalloon");
    public final Set<String> SHIELDS = Set.of("CobaltShield","ObsidianShield","AnkhShield");

    public AccessoryListeners(Plugin plugin){
        this.plugin=plugin;
        customItemKey=new NamespacedKey(plugin, "custom_item_id");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event){
        if (!(event.getEntity() instanceof Player player)) return;
        List<ItemStack> playerAccessories=playerDataInstance.getInventory(player.getUniqueId());
        if(playerAccessories==null){return;}
        for(ItemStack item:playerAccessories){
            Accessory accessory=accessoryManagerInstance.getAccessory(item);
            if(accessory!=null){
                accessory.onPlayerHit(player,event);
            }
        }
    }

    @EventHandler
    public void onPotionEffect(EntityPotionEffectEvent event){
        if (!(event.getEntity() instanceof Player player)) return;
        List<ItemStack> playerAccessories=playerDataInstance.getInventory(player.getUniqueId());
        if(playerAccessories==null){return;}
        for(ItemStack item:playerAccessories){
            Accessory accessory=accessoryManagerInstance.getAccessory(item);
            if(accessory!=null){
                accessory.onPlayerEffect(player,event);
            }
        }
    }

    @EventHandler
    public void onKnockback(EntityKnockbackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        List<ItemStack> playerAccessories=playerDataInstance.getInventory(player.getUniqueId());
        if(playerAccessories==null){return;}
        for(ItemStack item:playerAccessories){
            if(item!=null && item.hasItemMeta()){
                String accessoryId= item.getItemMeta().getPersistentDataContainer().get(customItemKey, PersistentDataType.STRING);
                if(SHIELDS.contains(accessoryId)){
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR)) return;
        Player player = event.getPlayer();
        ItemStack glass = player.getInventory().getItemInMainHand();
        double height=player.getLocation().getY();
        if(glass.getType() == Material.GLASS_BOTTLE&&((height>=180)&&(height<=200))){
            glass.setAmount(glass.getAmount() - 1);
            player.getInventory().addItem(CloudInABottle.getItem(plugin));
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(((Entity)player).isOnGround()){
            usedJumps.put(player.getUniqueId(),0);
        }
    }

    @EventHandler
    public void onDoubleJump(PlayerInputEvent event){
        if(!event.getInput().isJump()){return;}
        Player player = event.getPlayer();
        if(((Entity)player).isOnGround()){return;}
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR){return;}

        String itemId=null;
        List<ItemStack> playerAccessories=playerDataInstance.getInventory(player.getUniqueId());
        if(playerAccessories==null){return;}
        for(ItemStack item:playerAccessories){
            if(item!=null && item.hasItemMeta()){
                String accessoryId= item.getItemMeta().getPersistentDataContainer().get(customItemKey, PersistentDataType.STRING);
                if(DOUBLE_JUMPS.contains(accessoryId)){
                    itemId=accessoryId;
                    break;
                }
            }
        }
        if(itemId==null){return;}
        UUID id = player.getUniqueId();
        switch (itemId){
            case "CloudInABottle","CloudInABalloon","BlueHorseshoeBalloon" ->{
                if(usedJumps.get(id)>0){return;}
                player.setVelocity(player.getVelocity().setY(0.5));
                player.getWorld().playSound(player.getLocation(), "terraria:double_jump", 1.0F, 1.0F);
                player.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
                usedJumps.merge(id,1,Integer::sum);
            }
            case "TsunamiInABottle"->{
                if(usedJumps.get(id)>0){return;}
                player.setVelocity(player.getVelocity().setY(0.6));
                player.getWorld().playSound(player.getLocation(), "terraria:double_jump", 1.0F, 1.0F);
                player.getWorld().spawnParticle(Particle.BUBBLE, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
                usedJumps.merge(id,1,Integer::sum);
            }
            case "BlizzardInABottle","BlizzardInABalloon","WhiteHorseshoeBalloon"->{
                if(usedJumps.get(id)>0){return;}
                player.getWorld().playSound(player.getLocation(), "terraria:double_jump", 1.0F, 1.0F);
                final int[] timeLeft = {10};
                Bukkit.getScheduler().runTaskTimer(plugin, task -> {
                    player.setVelocity(player.getVelocity().setY(0.4));
                    player.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
                    timeLeft[0]--;
                    if (timeLeft[0] <= 0) {
                        task.cancel();
                    }
                }, 0L, 1L);
                usedJumps.merge(id,1,Integer::sum);
            }
            case "SandstormInABottle","SandstormInABalloon","YellowHorseshoeBalloon"->{
                if(usedJumps.get(id)>0){return;}
                player.getWorld().playSound(player.getLocation(), "terraria:double_jump", 0.5F, 1.0F);
                final int[] timeLeft = {15};
                Bukkit.getScheduler().runTaskTimer(plugin, task -> {
                    player.setVelocity(player.getVelocity().setY(0.4));
                    player.getWorld().spawnParticle(Particle.DUST_PLUME, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
                    timeLeft[0]--;
                    if (timeLeft[0] <= 0) {
                        task.cancel();
                    }
                }, 0L, 1L);
                usedJumps.merge(id,1,Integer::sum);
            }
            case "BundleOfBalloons","BundleOfHorseshoeBalloons" ->{
                switch (usedJumps.get(id)){
                    case 0 ->{
                        player.getWorld().playSound(player.getLocation(), "terraria:double_jump", 0.5F, 1.0F);
                        final int[] timeLeft = {15};
                        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
                            player.setVelocity(player.getVelocity().setY(0.4));
                            player.getWorld().spawnParticle(Particle.DUST_PLUME, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
                            timeLeft[0]--;
                            if (timeLeft[0] <= 0) {
                                task.cancel();
                            }
                        }, 0L, 1L);
                        usedJumps.merge(id,1,Integer::sum);
                    }
                    case 1 -> {
                        player.getWorld().playSound(player.getLocation(), "terraria:double_jump", 1.0F, 1.0F);
                        final int[] timeLeft = {10};
                        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
                            player.setVelocity(player.getVelocity().setY(0.4));
                            player.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
                            timeLeft[0]--;
                            if (timeLeft[0] <= 0) {
                                task.cancel();
                            }
                        }, 0L, 1L);
                        usedJumps.merge(id,1,Integer::sum);
                    }
                    case 2 -> {
                        player.setVelocity(player.getVelocity().setY(0.5));
                        player.getWorld().playSound(player.getLocation(), "terraria:double_jump", 1.0F, 1.0F);
                        player.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
                        usedJumps.merge(id,1,Integer::sum);
                    }
                    default -> {
                        return;
                    }
                }
            }
            default -> {
                return;
            }
        }
    }

    @EventHandler
    public void onBrush(BlockDropItemEvent event){
        Block block = event.getBlock();
        if(!(block.getType()==Material.SUSPICIOUS_SAND||block.getType()==Material.SUSPICIOUS_GRAVEL)){return;}
        if(Math.random()<0.25){
            ItemStack item = AncientFossil.getItem(plugin);
            block.getWorld().dropItem(block.getLocation().add(0,1,0), item);
        }
    }

    @EventHandler
    public void onGrassBreak(BlockBreakEvent event){
        if(!(event.getBlock().getType()==Material.SHORT_GRASS||event.getBlock().getType()==Material.TALL_GRASS||event.getBlock().getType()==Material.FERN)){return;}
        if(!jungleBiomes.contains(event.getBlock().getBiome())){return;}
        if(Math.random()<0.01){
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation().add(0.5,0.5,0.5), NaturesGift.getItem(plugin));
        }
    }
}
