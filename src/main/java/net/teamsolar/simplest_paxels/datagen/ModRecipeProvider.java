package net.teamsolar.simplest_paxels.datagen;

import net.teamsolar.simplest_paxels.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> IRON_PAXEL = List.of(ModItems.IRON_PAXEL.get());
    private static final List<ItemLike> GOLDEN_PAXEL = List.of(ModItems.GOLDEN_PAXEL.get());


    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> p_251297_) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_PAXEL.get())
                .pattern("ABD")
                .pattern(" C ")
                .pattern(" C ")
                .define('A', Items.IRON_AXE)
                .define('B', Items.IRON_SHOVEL)
                .define('D', Items.IRON_PICKAXE)
                .define('C', Items.STICK)
                .unlockedBy("has_iron_axe", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_AXE).build()))
                .unlockedBy("has_iron_shovel", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_SHOVEL).build()))
                .unlockedBy("has_iron_pickaxe", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_PICKAXE).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_PAXEL.get())
                .pattern("ABD")
                .pattern(" C ")
                .pattern(" C ")
                .define('A', Items.WOODEN_AXE)
                .define('B', Items.WOODEN_SHOVEL)
                .define('D', Items.WOODEN_PICKAXE)
                .define('C', Items.STICK)
                .unlockedBy("has_wooden_axe", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.WOODEN_AXE).build()))
                .unlockedBy("has_wooden_shovel", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.WOODEN_SHOVEL).build()))
                .unlockedBy("has_wooden_pickaxe", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.WOODEN_PICKAXE).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_PAXEL.get())
                .pattern("ABD")
                .pattern(" C ")
                .pattern(" C ")
                .define('A', Items.STONE_AXE)
                .define('B', Items.STONE_SHOVEL)
                .define('D', Items.STONE_PICKAXE)
                .define('C', Items.STICK)
                .unlockedBy("has_stone_axe", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STONE_AXE).build()))
                .unlockedBy("has_stone_shovel", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STONE_SHOVEL).build()))
                .unlockedBy("has_stone_pickaxe", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STONE_PICKAXE).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLDEN_PAXEL.get())
                .pattern("ABD")
                .pattern(" C ")
                .pattern(" C ")
                .define('A', Items.GOLDEN_AXE)
                .define('B', Items.GOLDEN_SHOVEL)
                .define('D', Items.GOLDEN_PICKAXE)
                .define('C', Items.STICK)
                .unlockedBy("has_golden_axe", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.GOLDEN_AXE).build()))
                .unlockedBy("has_golden_shovel", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.GOLDEN_SHOVEL).build()))
                .unlockedBy("has_golden_pickaxe", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.GOLDEN_PICKAXE).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_PAXEL.get())
                .pattern("ABD")
                .pattern(" C ")
                .pattern(" C ")
                .define('A', Items.DIAMOND_AXE)
                .define('B', Items.DIAMOND_SHOVEL)
                .define('D', Items.DIAMOND_PICKAXE)
                .define('C', Items.STICK)
                .unlockedBy("has_diamond_axe", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.DIAMOND_AXE).build()))
                .unlockedBy("has_diamond_shovel", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.DIAMOND_SHOVEL).build()))
                .unlockedBy("has_diamond_pickaxe", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.DIAMOND_PICKAXE).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHERITE_PAXEL.get())
                .pattern("ABD")
                .pattern(" C ")
                .pattern(" C ")
                .define('A', Items.NETHERITE_AXE)
                .define('B', Items.NETHERITE_SHOVEL)
                .define('D', Items.NETHERITE_PICKAXE)
                .define('C', Items.STICK)
                .unlockedBy("has_netherite_axe", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.DIAMOND_AXE).build()))
                .unlockedBy("has_netherite_shovel", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.DIAMOND_SHOVEL).build()))
                .unlockedBy("has_netherite_pickaxe", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.DIAMOND_PICKAXE).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(p_251297_);




        oreSmelting(p_251297_, IRON_PAXEL, RecipeCategory.MISC, Items.IRON_NUGGET, 0.1f, 200, "iron");
        oreBlasting(p_251297_, IRON_PAXEL, RecipeCategory.MISC, Items.IRON_NUGGET, 0.1f, 100, "iron");
        oreSmelting(p_251297_, GOLDEN_PAXEL, RecipeCategory.MISC, Items.GOLD_NUGGET, 0.1f, 200, "gold");
        oreBlasting(p_251297_, GOLDEN_PAXEL, RecipeCategory.MISC, Items.GOLD_NUGGET, 0.1f, 100, "gold");
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> p_250654_, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> p_248775_, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(p_248775_, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> p_250791_, RecipeSerializer<? extends AbstractCookingRecipe> pSerializer,
                                     List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                     float pExperience, int pCookingTime, String pGroup, String pSuffix) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime,
                    pSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(p_250791_, getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }

    }
}
