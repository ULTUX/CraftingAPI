package codes.ultux.plugins.craftingapi.craftingreloadedapi.utils;

import codes.ultux.plugins.craftingapi.craftingreloadedapi.datamodels.CraftingRecipe;
import codes.ultux.plugins.craftingapi.craftingreloadedapi.datamodels.CraftingRecipeStore;
import org.bukkit.inventory.ItemStack;

public class CraftingUtils {
    public static CraftingRecipe getCraftingRecipe(ItemStack[] craftingMatrix) {
        CraftingRecipe appliedRecipe = null;
        for (CraftingRecipe recipe: CraftingRecipeStore.getInstance().stream().toArray(CraftingRecipe[]::new)){
            boolean isOk = true;
            for (int i = 0; i < 9; i++){
                if (recipe.getIngredient(i) == null && craftingMatrix[i] == null) continue;
                if (recipe.getIngredient(i) == null || craftingMatrix[i] == null) {
                    isOk = false;
                    break;
                }
                if (!recipe.getIngredient(i).equals(craftingMatrix[i])) {
                    isOk = false;
                    break;
                }
            }
            if (isOk) {
                appliedRecipe = recipe;
                break;
            }
        }
        return appliedRecipe;

    }
}
