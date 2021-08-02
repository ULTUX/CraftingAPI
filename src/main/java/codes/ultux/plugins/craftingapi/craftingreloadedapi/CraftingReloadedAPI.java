package codes.ultux.plugins.craftingapi.craftingreloadedapi;

import codes.ultux.plugins.craftingapi.craftingreloadedapi.datamodels.CraftingRecipe;
import codes.ultux.plugins.craftingapi.craftingreloadedapi.datamodels.CraftingRecipeStore;
import codes.ultux.plugins.craftingapi.craftingreloadedapi.events.ItemCraftHandler;
import codes.ultux.plugins.craftingapi.craftingreloadedapi.events.ItemPrepareCraftHandler;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class CraftingReloadedAPI extends JavaPlugin {
    public static CraftingReloadedAPI instance;
    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Enabling CraftingReloadedAPI...");
        CraftingRecipeStore store = CraftingRecipeStore.getInstance();

        getServer().getPluginManager().registerEvents(new ItemPrepareCraftHandler(), this);
        getServer().getPluginManager().registerEvents(new ItemCraftHandler(), this);

        CraftingRecipe recipe = new CraftingRecipe(new ItemStack(Material.DIAMOND));
        recipe.addIngredient(new ItemStack(Material.COBBLESTONE, 1), 0);

        store.addRecipe(recipe);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
