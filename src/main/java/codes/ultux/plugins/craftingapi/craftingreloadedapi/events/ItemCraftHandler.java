package codes.ultux.plugins.craftingapi.craftingreloadedapi.events;

import codes.ultux.plugins.craftingapi.craftingreloadedapi.CraftingReloadedAPI;
import codes.ultux.plugins.craftingapi.craftingreloadedapi.datamodels.CraftingRecipe;
import codes.ultux.plugins.craftingapi.craftingreloadedapi.utils.CraftingUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class ItemCraftHandler implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
       if (event.getInventory() instanceof CraftingInventory){
           CraftingInventory inventory = (CraftingInventory) event.getInventory();
           ItemStack[] craftingMatrix = inventory.getMatrix();
           CraftingRecipe usedRecipe = CraftingUtils.getCraftingRecipe(craftingMatrix);
           if (usedRecipe != null) {
               if (inventory.getResult().equals(usedRecipe.getResult())) {
                   if (event.getCurrentItem() != null && event.getCurrentItem().equals(usedRecipe.getResult())) {
                       ItemStack[] items = new ItemStack[9];
                       for (int i = 0; i < 9; i++) {
                           items[i] = new ItemStack(Material.AIR);
                       }
                       Bukkit.getScheduler().scheduleSyncDelayedTask(CraftingReloadedAPI.instance, new Runnable() {
                           @Override
                           public void run() {
                               inventory.setMatrix(items);
                           }
                       }, 1);
                   }
               }
               else {
                   event.setCancelled(true);
                   event.getWhoClicked().sendMessage(ChatColor.RED+"It looks like you are trying to glitch crafting system, please stop.");
               }
           }
       }
    }
}
