package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder;

import me.carson.terrariaItems.handlers.ManaManager;
import me.carson.terrariaItems.weaponsFolder.Weapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class Magic extends Weapon {

    protected final float speed;
    protected final float damage;
    protected final float spread;
    protected final float duration;
    protected final float cost;
    public ManaManager manaManagerInstance=ManaManager.getInstance();

    public Magic(Plugin plugin, String name, String rarity, Material baseMaterial, String texture, String id, int cooldown, float speed, float damage, float spread, float duration, float cost, String lore) {
        super(plugin, name, rarity, baseMaterial, texture, id, cooldown, lore);
        this.speed = speed;
        this.damage = damage;
        this.spread = spread;
        this.duration = duration;
        this.cost = cost;
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {

    }


}
