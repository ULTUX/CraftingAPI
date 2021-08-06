package codes.ultux.plugins.craftingapi.craftingapi;

import codes.ultux.plugins.craftingapi.craftingapi.datamodels.CraftingRecipe;
import codes.ultux.plugins.craftingapi.craftingapi.datamodels.CraftingRecipeStore;
import codes.ultux.plugins.craftingapi.craftingapi.events.ItemCraftHandler;
import codes.ultux.plugins.craftingapi.craftingapi.events.ItemPrepareCraftHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class CraftingReloadedAPI extends JavaPlugin {
    public static CraftingReloadedAPI instance;
    private static CraftingRecipeStore craftingStore;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Enabling CraftingReloadedAPI...");
        craftingStore = CraftingRecipeStore.getInstance();

        getServer().getPluginManager().registerEvents(new ItemPrepareCraftHandler(), this);
        getServer().getPluginManager().registerEvents(new ItemCraftHandler(), this);


    }

    @Override
    public void onDisable() {
        getLogger().info("Unregistering events...");
        HandlerList.unregisterAll(this);
    }

    /**
     * Register new recipe using CraftingRecipe object.
     * @param recipe CraftingRecipe object that represents recipe to be added.
     */
    public void addRecipe(CraftingRecipe recipe){
        craftingStore.addRecipe(recipe);
    }
}
