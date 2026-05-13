package me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder;

import me.carson.terrariaItems.handlers.StealthManager;
import me.carson.terrariaItems.weaponsFolder.Weapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class Rouge extends Weapon {

    protected final float speed;
    protected final float damage;
    protected final float spread;
    protected final float duration;
    public StealthManager stealthManager=StealthManager.getInstance();

    public Rouge(Plugin plugin, String name, String rarity, Material baseMaterial, String texture, String id, int cooldown, float speed, float damage, float spread, float duration, String lore) {
        super(plugin, name, rarity, baseMaterial, texture, id, cooldown, lore);
        this.speed = speed;
        this.damage = damage;
        this.spread = spread;
        this.duration = duration;
    }

    public abstract void stealthAttack(Player player);

}
