package me.carson.terrariaItems.handlers;

import me.carson.terrariaItems.recipeManagers.*;
import me.carson.terrariaItems.weaponsFolder.WeaponManager;
import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.stream.Collectors;

public class CustomRecipeManager {

    private final Plugin plugin;
    private final NamespacedKey idKey;

    record RecipeEntry(ItemStack itemStack,NamespacedKey key){}
    private final Map<List<String>, RecipeEntry> shapelessResults = new HashMap<>();
    private final Map<List<String>, RecipeEntry> shapedResults = new HashMap<>();
    private final Set<NamespacedKey> registeredKeys = new HashSet<>();

    public CustomRecipeManager(Plugin plugin) {
        this.plugin = plugin;
        this.idKey = new NamespacedKey(plugin, "custom_item_id");
    }

    public NamespacedKey getIdKey() {
        return idKey;
    }

    public void register(Recipe recipe) {
        if (!(recipe instanceof Keyed keyed)) {
            Bukkit.addRecipe(recipe);
            return;
        }
        NamespacedKey key = keyed.getKey();

        if (recipe instanceof ShapelessRecipe sr) {
            List<String> identities = sr.getChoiceList().stream()
                    .map(this::identityOf)
                    .filter(Objects::nonNull)
                    .sorted()
                    .collect(Collectors.toList());
            shapelessResults.put(identities, new RecipeEntry(sr.getResult(), key));
            registeredKeys.add(key);
        } else if (recipe instanceof ShapedRecipe sr) {
            List<String> grid = normalizedGrid(sr);
            shapedResults.put(grid, new RecipeEntry(sr.getResult(), key));
            shapedResults.put(mirror(grid), new RecipeEntry(sr.getResult(), key));
            registeredKeys.add(key);
        }

        Bukkit.addRecipe(recipe);
    }

    public boolean isOurRecipe(NamespacedKey key) {
        return registeredKeys.contains(key);
    }

    public ItemStack resolveShapeless(List<String> actualIdentitiesSorted) {
        RecipeEntry entry = shapelessResults.get(actualIdentitiesSorted);
        return entry != null ? entry.itemStack() : null;
    }

    public ItemStack resolveShaped(List<String> normalizedActualGrid) {
        RecipeEntry entry = shapedResults.get(normalizedActualGrid);
        return entry != null ? entry.itemStack() : null;
    }

    public NamespacedKey getResolvedShapelessKey(List<String> actualIdentitiesSorted) {
        RecipeEntry entry = shapelessResults.get(actualIdentitiesSorted);
        return entry != null ? entry.key() : null;
    }

    public NamespacedKey getResolvedShapedKey(List<String> actualIdentitiesSorted) {
        RecipeEntry entry = shapedResults.get(actualIdentitiesSorted);
        return entry != null ? entry.key() : null;
    }

    public boolean hasAnyShapedSignatures() {
        return !shapedResults.isEmpty();
    }


    private boolean isCustomItemChoice(RecipeChoice choice) {
        if (!(choice instanceof RecipeChoice.ExactChoice exact)) return false;
        for (ItemStack option : exact.getChoices()) {
            if (getCustomId(option) != null) return true;
        }
        return false;
    }

    private String identityOf(RecipeChoice choice) {
        if (choice instanceof RecipeChoice.ExactChoice exact) {
            for (ItemStack option : exact.getChoices()) {
                String id = getCustomId(option);
                if (id != null) return "custom:" + id;
            }
            // ExactChoice with no custom id found - fall back to material of first option
            if (!exact.getChoices().isEmpty()) {
                return "material:" + exact.getChoices().get(0).getType();
            }
            return null;
        } else if (choice instanceof RecipeChoice.MaterialChoice mat) {
            List<Material> mats = new ArrayList<>(mat.getChoices());
            Collections.sort(mats);
            return "material:" + mats.stream().map(Enum::name).collect(Collectors.joining(","));
        }
        return null;
    }

    private String getCustomId(ItemStack item) {
        if (item == null || item.getType().isAir()) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;
        return meta.getPersistentDataContainer().get(idKey, PersistentDataType.STRING);
    }

    private List<String> normalizedGrid(ShapedRecipe recipe) {
        String[] shape = recipe.getShape();
        Map<Character, RecipeChoice> choiceMap = recipe.getChoiceMap();
        int rows = shape.length;
        int cols = shape[0].length();

        String[][] grid = new String[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = shape[r].charAt(c);
                if (ch == ' ') {
                    grid[r][c] = null;
                } else {
                    RecipeChoice choice = choiceMap.get(ch);
                    grid[r][c] = choice != null ? identityOf(choice) : null;
                }
            }
        }
        return trim(grid);
    }

    public List<String> normalizedGridFromMatrix(ItemStack[] matrix, int rows, int cols) {
        String[][] grid = new String[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                ItemStack item = matrix[r * cols + c];
                if (item == null || item.getType().isAir()) {
                    grid[r][c] = null;
                    continue;
                }
                String id = getCustomId(item);
                grid[r][c] = id != null ? "custom:" + id : "material:" + item.getType();
            }
        }
        return trim(grid);
    }

    private List<String> trim(String[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int minR = rows, maxR = -1, minC = cols, maxC = -1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] != null) {
                    minR = Math.min(minR, r);
                    maxR = Math.max(maxR, r);
                    minC = Math.min(minC, c);
                    maxC = Math.max(maxC, c);
                }
            }
        }
        if (maxR == -1) return List.of("0x0"); // completely empty grid

        int trimmedRows = maxR - minR + 1;
        int trimmedCols = maxC - minC + 1;
        List<String> flat = new ArrayList<>();
        flat.add(trimmedRows + "x" + trimmedCols); // dimensions header, so shape size is part of signature
        for (int r = minR; r <= maxR; r++) {
            for (int c = minC; c <= maxC; c++) {
                String cell = grid[r][c];
                flat.add(cell == null ? "empty" : cell);
            }
        }
        return flat;
    }

    private List<String> mirror(List<String> normalized) {
        String header = normalized.get(0);
        String[] dims = header.split("x");
        int rows = Integer.parseInt(dims[0]);
        int cols = Integer.parseInt(dims[1]);

        List<String> cells = normalized.subList(1, normalized.size());
        List<String> mirrored = new ArrayList<>();
        mirrored.add(header);
        for (int r = 0; r < rows; r++) {
            for (int c = cols - 1; c >= 0; c--) {
                mirrored.add(cells.get(r * cols + c));
            }
        }
        return mirrored;
    }

    public void registerAll() {
        new AccessoryRecipes(plugin, this).registerRecipes();
        new ArmorRecipes(plugin, this).registerRecipes();
        new BlocksRecipes(plugin, this).registerRecipes();
        new MaterialRecipes(plugin, this).registerRecipes();
        new MiscRecipes(plugin, this).registerRecipes();
        new ToolRecipes(plugin, this).registerRecipes();
        new WeaponRecipes(plugin, this).registerRecipes();
    }
}