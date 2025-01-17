package net.indevo.simplest_paxels.datagen;

import net.indevo.simplest_paxels.SimplestPaxels;
import net.indevo.simplest_paxels.item.ModItems;
import net.indevo.simplest_paxels.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                               CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, SimplestPaxels.MODID, existingFileHelper);
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
