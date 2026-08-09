package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.bukkit.inventory.meta.components.consumable.ConsumableComponent;
import org.bukkit.plugin.Plugin;

public class Hemopiranha extends Material {

    public Hemopiranha(Plugin plugin) {
        super(plugin,"hemopiranha.name","#9696FF", org.bukkit.Material.COOKED_COD,"hemopiranha","Hemopiranha","hemopiranha.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack itemstack=new Hemopiranha(plugin).createItem();
        ItemMeta itemMeta = itemstack.getItemMeta();

        FoodComponent foodComponent= itemMeta.getFood();
        foodComponent.setCanAlwaysEat(false);
        foodComponent.setNutrition(6);
        foodComponent.setSaturation(10);
        itemMeta.setFood(foodComponent);

        itemstack.setItemMeta(itemMeta);
        return itemstack;
    }
}
