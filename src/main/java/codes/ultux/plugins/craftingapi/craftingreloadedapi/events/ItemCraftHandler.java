package codes.ultux.plugins.craftingapi.craftingreloadedapi.events;

import codes.ultux.plugins.craftingapi.craftingreloadedapi.datamodels.CraftingRecipe;
import codes.ultux.plugins.craftingapi.craftingreloadedapi.utils.CraftingUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;


public class ItemCraftHandler implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onCraftItem(CraftItemEvent event) {
        System.out.println("Crafting...");
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
       if (event.getInventory() instanceof CraftingInventory){
           CraftingInventory inventory = (CraftingInventory) event.getInventory();
           ItemStack[] craftingMatrix = inventory.getMatrix();
           CraftingRecipe usedRecipe = CraftingUtils.getCraftingRecipe(craftingMatrix);
           if (usedRecipe != null) {
               if (inventory.getResult().equals(usedRecipe.getResult())) {
                   if (event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)){
                       event.setCancelled(true);
                       craftAllItems(inventory, usedRecipe, (Player) event.getWhoClicked());

                   }
                   else if (event.getCurrentItem() != null && event.getCurrentItem().equals(usedRecipe.getResult())) {
                       CraftingUtils.craftItem(usedRecipe, craftingMatrix, inventory, true);
                   }
               }
               else {
                   event.setCancelled(true);
                   event.getWhoClicked().sendMessage(ChatColor.RED+"It looks like you are trying to glitch crafting system, please stop.");
               }
           }
       }
    }

    private void craftAllItems(CraftingInventory inventory, CraftingRecipe usedRecipe, Player player) {
        if (CraftingUtils.giveItems(usedRecipe.getResult(), player)) {
            CraftingUtils.craftItem(usedRecipe, inventory.getMatrix(), inventory, false);
        }
        while (CraftingUtils.verifyRecipe(usedRecipe, inventory.getMatrix())){
            if (CraftingUtils.giveItems(usedRecipe.getResult(), player)) {
                CraftingUtils.craftItem(usedRecipe, inventory.getMatrix(), inventory, false);
            }
        }

    }
}
