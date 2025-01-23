package net.teamsolar.simplest_paxels.datagen;

import net.teamsolar.simplest_paxels.SimplestPaxels;
import net.teamsolar.simplest_paxels.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SimplestPaxels.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(ModItems.WOODEN_PAXEL);
        handheldItem(ModItems.STONE_PAXEL);
        handheldItem(ModItems.IRON_PAXEL);
        handheldItem(ModItems.GOLDEN_PAXEL);
        handheldItem(ModItems.DIAMOND_PAXEL);
        handheldItem(ModItems.NETHERITE_PAXEL);
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(SimplestPaxels.MOD_ID, "item/" + item.getId().getPath()));
    }
}
