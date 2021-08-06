package codes.ultux.plugins.craftingapi.craftingapi.datamodels;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CraftingRecipe {
    private final ItemStack result;

    /**
     * Ingredients and their corresponding crafting slot.
     * Crafting slot is an integer in range 0-8:<br/>
     * 0 1 2 <br/>
     * 3 4 5 <br/>
     * 6 7 8 <br/>
     */
    private final HashMap<Integer, ItemStack> ingredients;

    /**
     * Create new crafting recipe.
     * @param result Resulting ItemStack.
     */
    public CraftingRecipe(ItemStack result) {
        this.result = result;
        ingredients = new HashMap<>();
        for (int i = 0; i < 9; i++){
            ingredients.put(i, null);
        }
    }

    /**
     * Add ingredient to this crafting recipe.
     * @param ingredient Ingredient to be added.
     * @param slot Slot for this ingredient.
     * @return Itself.
     */
    public CraftingRecipe addIngredient(ItemStack ingredient, int slot){
        ingredients.put(slot, ingredient);
        return this;
    }

    public ItemStack getResult() {
        return result;
    }

    public ItemStack getIngredient(int craftingSlot){
        return ingredients.get(craftingSlot);
    }



}
