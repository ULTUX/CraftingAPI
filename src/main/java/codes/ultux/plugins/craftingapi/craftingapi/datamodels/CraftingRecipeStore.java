package codes.ultux.plugins.craftingapi.craftingapi.datamodels;

import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CraftingRecipeStore implements Iterable<CraftingRecipe> {
    private final ArrayList<CraftingRecipe> craftingRecipeStore = new ArrayList<>();
    private final HashMap<CraftingRecipe, CraftingInventory> mappedInventoriesToRecipes = new HashMap<>();
    private static CraftingRecipeStore instance;


    public CraftingRecipe findRecipe(ItemStack result) {
        for (CraftingRecipe recipe: craftingRecipeStore.toArray(new CraftingRecipe[0])){
            if (recipe.getResult().equals(result)) {
                return recipe;
            }
        }
        return null;
    }

    public static CraftingRecipeStore getInstance() {
        if (instance == null){
            instance = new CraftingRecipeStore();
        }
        return instance;
    }

    public void addRecipe(CraftingRecipe recipe) {
        craftingRecipeStore.add(recipe);
    }

    public void removeRecipe(CraftingRecipe recipe){
        craftingRecipeStore.remove(recipe);
    }

    public void removeRecipe(ItemStack result) {
        CraftingRecipe recipe = findRecipe(result);
        if (recipe != null) craftingRecipeStore.remove(recipe);
    }


    @Override
    public Iterator<CraftingRecipe> iterator() {
        return craftingRecipeStore.iterator();
    }

    @Override
    public Spliterator<CraftingRecipe> spliterator() {
        return craftingRecipeStore.spliterator();
    }

    public Stream<CraftingRecipe> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
