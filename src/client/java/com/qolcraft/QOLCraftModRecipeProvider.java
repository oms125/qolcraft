package com.qolcraft;

import java.util.List;import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class QOLCraftModRecipeProvider extends FabricRecipeProvider {
    public QOLCraftModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                // Smelting Rotten Flesh to Leather
                oreSmelting(
                        List.of(Items.ROTTEN_FLESH),
                        RecipeCategory.MISC,
                        Items.LEATHER,
                        0.1f,
                        200,
                        "rotten_flesh_to_leather"
                );
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "QOLCraftModRecipeProvider";
    }
}