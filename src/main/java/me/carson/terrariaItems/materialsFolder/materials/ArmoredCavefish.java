package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.bukkit.inventory.meta.components.consumable.ConsumableComponent;
import org.bukkit.plugin.Plugin;

public class ArmoredCavefish extends Material {

    public ArmoredCavefish(Plugin plugin) {
        super(plugin,"armored_cavefish.name","#9696FF", org.bukkit.Material.COOKED_COD,"armored_cavefish","ArmoredCavefish","armored_cavefish.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack itemstack=new ArmoredCavefish(plugin).createItem();
        ItemMeta itemMeta = itemstack.getItemMeta();

        FoodComponent foodComponent= itemMeta.getFood();
        foodComponent.setCanAlwaysEat(false);
        foodComponent.setNutrition(5);
        foodComponent.setSaturation(8.5f);
        itemMeta.setFood(foodComponent);

        itemstack.setItemMeta(itemMeta);
        return itemstack;
    }
}
