package codes.ultux.plugins.craftingapi.craftingapi.events;

import codes.ultux.plugins.craftingapi.craftingapi.datamodels.CraftingRecipe;
import codes.ultux.plugins.craftingapi.craftingapi.utils.CraftingUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class ItemPrepareCraftHandler implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        ItemStack[] craftingMatrix = event.getInventory().getMatrix();
        CraftingRecipe usedRecipe = CraftingUtils.getCraftingRecipe(craftingMatrix);
        if (usedRecipe != null) {
            event.getInventory().setResult(usedRecipe.getResult());
        }
    }

}
