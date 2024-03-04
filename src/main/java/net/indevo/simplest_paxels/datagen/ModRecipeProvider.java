package net.indevo.simplest_paxels.datagen;

import net.indevo.simplest_paxels.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOOD_PAXEL.get())
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_PAXEL.get())
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

        netheriteSmithing(p_251297_, ModItems.DIAMOND_PAXEL.get(), RecipeCategory.MISC, ModItems.NETHERITE_PAXEL.get());
    }
}
