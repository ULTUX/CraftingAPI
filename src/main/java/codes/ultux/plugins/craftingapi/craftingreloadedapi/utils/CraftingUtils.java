package codes.ultux.plugins.craftingapi.craftingreloadedapi.utils;

import codes.ultux.plugins.craftingapi.craftingreloadedapi.CraftingReloadedAPI;
import codes.ultux.plugins.craftingapi.craftingreloadedapi.datamodels.CraftingRecipe;
import codes.ultux.plugins.craftingapi.craftingreloadedapi.datamodels.CraftingRecipeStore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;

public class CraftingUtils {
    public static CraftingRecipe getCraftingRecipe(ItemStack[] craftingMatrix) {
        for (CraftingRecipe recipe : CraftingRecipeStore.getInstance().stream().toArray(CraftingRecipe[]::new)) {
            if (verifyRecipe(recipe, craftingMatrix)) return recipe;
        }
        return null;
    }

    public static boolean verifyRecipe(CraftingRecipe recipe, ItemStack[] craftingMatrix) {
        boolean isOk = true;
        for (int i = 0; i < 9; i++){
            if (recipe.getIngredient(i) == null && craftingMatrix[i] == null) continue;
            if (recipe.getIngredient(i) == null || craftingMatrix[i] == null) {
                isOk = false;
                break;
            }

            ItemStack comparedItemStack = craftingMatrix[i].clone();
            if (comparedItemStack.getAmount() > recipe.getIngredient(i).getAmount())
                comparedItemStack.setAmount(recipe.getIngredient(i).getAmount());

            if (!recipe.getIngredient(i).equals(comparedItemStack)) {
                isOk = false;
                break;
            }
        }
        if (isOk) {
            return true;
        }
        return false;
    }

    public static void craftItem(CraftingRecipe usedRecipe, ItemStack[] craftingMatrix, CraftingInventory craftingInventory, boolean shouldDelay){
        ItemStack[] items = new ItemStack[9];
        for (int i = 0; i < 9; i++) {
            if (craftingMatrix[i] != null) {
                int slotAmount = craftingMatrix[i].getAmount();
                int recipeAmount = usedRecipe.getIngredient(i).getAmount();
                if (slotAmount == recipeAmount) items[i] = new ItemStack(Material.AIR);
                else {
                    items[i] = craftingMatrix[i].clone();
                    items[i].setAmount(slotAmount - recipeAmount);
                }
            }
        }
        if (shouldDelay) Bukkit.getScheduler().scheduleSyncDelayedTask(CraftingReloadedAPI.instance, () -> craftingInventory.setMatrix(items), 1);
        else {
            craftingInventory.setMatrix(items);
        }
    }

    public static boolean giveItems(ItemStack item, Player player){
        HashMap<Integer, ItemStack> didntFit =  player.getInventory().addItem(item);
        return didntFit.isEmpty();
    }
}
