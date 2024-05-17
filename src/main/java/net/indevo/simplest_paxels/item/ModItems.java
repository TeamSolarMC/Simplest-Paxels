package net.indevo.simplest_paxels.item;

import net.indevo.simplest_paxels.SimplestPaxels;
import net.indevo.simplest_paxels.item.custom.PaxelItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SimplestPaxels.MOD_ID);

    public static final RegistryObject<Item> WOODEN_PAXEL = ITEMS.register("wooden_paxel",
            () -> new PaxelItem(ModToolTiers.WOOD, 2.5F, -3.4F, new Item.Properties().durability(88)));
    public static final RegistryObject<Item> STONE_PAXEL = ITEMS.register("stone_paxel",
            () -> new PaxelItem(ModToolTiers.STONE, 2.5F, -3.4F, new Item.Properties().durability(196)));
    public static final RegistryObject<Item> IRON_PAXEL = ITEMS.register("iron_paxel",
            () -> new PaxelItem(ModToolTiers.IRON, 2.5F, -3.4F, new Item.Properties().durability(375)));
    public static final RegistryObject<Item> GOLDEN_PAXEL = ITEMS.register("golden_paxel",
            () -> new PaxelItem(ModToolTiers.GOLD, 2.5F, -3.4F, new Item.Properties().durability(48)));
    public static final RegistryObject<Item> DIAMOND_PAXEL = ITEMS.register("diamond_paxel",
            () -> new PaxelItem(ModToolTiers.DIAMOND, 2.5F, -3.4F, new Item.Properties().durability(2341)));
    public static final RegistryObject<Item> NETHERITE_PAXEL = ITEMS.register("netherite_paxel",
            () -> new PaxelItem(ModToolTiers.NETHERITE, 2.5F, -3.4F, new Item.Properties().durability(3046)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
