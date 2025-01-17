package net.teamsolar.simple_paxels.datagen;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.teamsolar.simple_paxels.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;


public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        PaxelRecipeHelper(Items.WOODEN_AXE, Items.WOODEN_SHOVEL, Items.WOODEN_PICKAXE, ModItems.WOODEN_PAXEL.get())
                .save(output);
        PaxelRecipeHelper(Items.STONE_AXE, Items.STONE_SHOVEL, Items.STONE_PICKAXE, ModItems.STONE_PAXEL.get())
                .save(output);
        PaxelRecipeHelper(Items.IRON_AXE, Items.IRON_SHOVEL, Items.IRON_PICKAXE, ModItems.IRON_PAXEL.get())
                .save(output);
        PaxelRecipeHelper(Items.GOLDEN_AXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_PICKAXE, ModItems.GOLDEN_PAXEL.get())
                .save(output);
        PaxelRecipeHelper(Items.DIAMOND_AXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_PICKAXE, ModItems.DIAMOND_PAXEL.get())
                .save(output);
        PaxelRecipeHelper(Items.NETHERITE_AXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_PICKAXE, ModItems.NETHERITE_PAXEL.get())
                .save(output);

    }

    private String stripNamespace(String itemString) {
        Pattern pattern = Pattern.compile("(.+):(.+)");
        var matches = pattern.matcher(itemString);
        if(matches.find()) {
            return matches.group(2);
        }
        return "";
    }
    private Criterion<InventoryChangeTrigger.TriggerInstance> hasInInventory(ItemLike item) {
        return inventoryTrigger(ItemPredicate.Builder.item()
                .of(item).build());
    }
    private String hasInInventoryCriterionName(ItemLike item) {
        return "has_".concat(item.asItem().toString());
    }

    private ShapedRecipeBuilder PaxelRecipeHelper(Item axe, Item shovel, Item pickaxe, Item output) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output)
                .pattern("ABD")
                .pattern(" C ")
                .pattern(" C ")
                .define('A', axe)
                .define('B', shovel)
                .define('D', pickaxe)
                .define('C', Items.STICK)
                .unlockedBy(hasInInventoryCriterionName(axe), hasInInventory(axe))
                .unlockedBy(hasInInventoryCriterionName(shovel), hasInInventory(shovel))
                .unlockedBy(hasInInventoryCriterionName(pickaxe), hasInInventory(pickaxe))
                .unlockedBy(hasInInventoryCriterionName(Items.STICK), hasInInventory(Items.STICK));
    }
}