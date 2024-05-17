package net.indevo.simplest_paxels.item;

import net.indevo.simplest_paxels.SimplestPaxels;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimplestPaxels.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SIMPLEST_PAXELS_TAB = CREATIVE_MODE_TABS.register("simplest_paxel_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.NETHERITE_PAXEL.get()))
                    .title(Component.translatable("creativetab.simplest_paxel_tab"))
                    .displayItems(((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.WOODEN_PAXEL.get());
                        pOutput.accept(ModItems.STONE_PAXEL.get());
                        pOutput.accept(ModItems.IRON_PAXEL.get());
                        pOutput.accept(ModItems.GOLDEN_PAXEL.get());
                        pOutput.accept(ModItems.DIAMOND_PAXEL.get());
                        pOutput.accept(ModItems.NETHERITE_PAXEL.get());
                    }))
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
