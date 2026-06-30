package net.teamsolar.simple_paxels.datagen;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.teamsolar.simple_paxels.SimplestPaxels;
import net.teamsolar.simple_paxels.item.ModItems;
import net.teamsolar.simple_paxels.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, SimplestPaxels.MODID);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void addTags(HolderLookup.Provider pProvider) {
        TagAppender<Item> builder = this.tag(ModTags.Items.PAXELS);

        builder.addAll(
                List.of(
                        ModItems.WOODEN_PAXEL.getKey(),
                        ModItems.STONE_PAXEL.getKey(),
                        ModItems.COPPER_PAXEL.getKey(),
                        ModItems.GOLDEN_PAXEL.getKey(),
                        ModItems.IRON_PAXEL.getKey(),
                        ModItems.DIAMOND_PAXEL.getKey(),
                        ModItems.NETHERITE_PAXEL.getKey()
                )
        );
        this.tag(ItemTags.DURABILITY_ENCHANTABLE).addTag(ModTags.Items.PAXELS);
        this.tag(ItemTags.MINING_ENCHANTABLE).addTag(ModTags.Items.PAXELS);
        this.tag(ItemTags.MINING_LOOT_ENCHANTABLE).addTag(ModTags.Items.PAXELS);
    }

    @Override
    public @NotNull String getName() {
        return "Item Tags";
    }
}
