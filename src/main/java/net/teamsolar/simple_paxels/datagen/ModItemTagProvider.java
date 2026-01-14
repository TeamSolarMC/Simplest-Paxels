package net.teamsolar.simple_paxels.datagen;

import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.teamsolar.simple_paxels.SimplestPaxels;
import net.teamsolar.simple_paxels.item.ModItems;
import net.teamsolar.simple_paxels.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, SimplestPaxels.MODID);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Items.PAXELS)
                .add(
                        ModItems.WOODEN_PAXEL.get(),
                        ModItems.STONE_PAXEL.get(),
                        ModItems.GOLDEN_PAXEL.get(),
                        ModItems.IRON_PAXEL.get(),
                        ModItems.DIAMOND_PAXEL.get(),
                        ModItems.NETHERITE_PAXEL.get()
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
