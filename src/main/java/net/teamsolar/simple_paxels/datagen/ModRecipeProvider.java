package net.teamsolar.simple_paxels.datagen;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.teamsolar.simple_paxels.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }


    @Override
    protected void buildRecipes() {
        paxelRecipe(Items.WOODEN_AXE, Items.WOODEN_SHOVEL, Items.WOODEN_PICKAXE, ModItems.WOODEN_PAXEL.get());
        paxelRecipe(Items.STONE_AXE, Items.STONE_SHOVEL, Items.STONE_PICKAXE, ModItems.STONE_PAXEL.get());
        paxelRecipe(Items.COPPER_AXE, Items.COPPER_SHOVEL, Items.COPPER_PICKAXE, ModItems.COPPER_PAXEL.get());
        paxelRecipe(Items.IRON_AXE, Items.IRON_SHOVEL, Items.IRON_PICKAXE, ModItems.IRON_PAXEL.get());
        paxelRecipe(Items.GOLDEN_AXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_PICKAXE, ModItems.GOLDEN_PAXEL.get());
        paxelRecipe(Items.DIAMOND_AXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_PICKAXE, ModItems.DIAMOND_PAXEL.get());
        paxelRecipe(Items.NETHERITE_AXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_PICKAXE, ModItems.NETHERITE_PAXEL.get());

        basicBlastingAndSmeltingRecipe(ModItems.IRON_PAXEL.get(), Items.IRON_NUGGET);
        basicBlastingAndSmeltingRecipe(ModItems.COPPER_PAXEL.get(), Items.COPPER_NUGGET);
        basicBlastingAndSmeltingRecipe(ModItems.GOLDEN_PAXEL.get(), Items.GOLD_NUGGET);
    }

    private String itemNameWithoutNamespace(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

    private String hasInInventoryCriterionName(ItemLike item) {
        return "has_".concat(itemNameWithoutNamespace(item.asItem()));
    }

    private void paxelRecipe(Item axe, Item shovel, Item pickaxe, Item outputItem) {
        ShapedRecipeBuilder.shaped(
            this.registries.lookupOrThrow(Registries.ITEM),
            RecipeCategory.TOOLS,
            outputItem
        ).pattern("ABD")
            .pattern(" C ")
            .pattern(" C ")
            .define('A', axe)
            .define('B', shovel)
            .define('D', pickaxe)
            .define('C', Items.STICK)
            .unlockedBy(hasInInventoryCriterionName(axe), has(axe))
            .unlockedBy(hasInInventoryCriterionName(shovel), has(shovel))
            .unlockedBy(hasInInventoryCriterionName(pickaxe), has(pickaxe))
            .save(output);
    }
    private void basicBlastingAndSmeltingRecipe(Item input, Item outputItem) {
        String unqualifiedItemName = itemNameWithoutNamespace(input);
        SimpleCookingRecipeBuilder.blasting(
                        Ingredient.of(input),
                        RecipeCategory.MISC,
                        outputItem,
                        0.1F,
                        100
                )
                .unlockedBy("has_".concat(unqualifiedItemName), has(input))
                .save(output, unqualifiedItemName.concat("_blasting"));
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(input),
                        RecipeCategory.MISC,
                        outputItem,
                        0.1F,
                        200
                )
                .unlockedBy("has_".concat(unqualifiedItemName), has(input))
                .save(output, unqualifiedItemName.concat("_smelting"));
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider, @NotNull RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public @NotNull String getName() {
            return "My Recipes";
        }
    }
}