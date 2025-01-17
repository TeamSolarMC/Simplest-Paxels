package net.indevo.simplest_paxels.item;

import net.indevo.simplest_paxels.SimplestPaxels;
import net.indevo.simplest_paxels.item.custom.PaxelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(SimplestPaxels.MODID);

    public static final DeferredItem<Item> WOODEN_PAXEL = ITEMS.register("wooden_paxel",
            () -> new PaxelItem(Tiers.WOOD, 5.0F, -3.3F, new Item.Properties().durability(88)));
    public static final DeferredItem<Item> STONE_PAXEL = ITEMS.register("stone_paxel",
            () -> new PaxelItem(Tiers.STONE, 5.0F, -3.3F, new Item.Properties().durability(196)));
    public static final DeferredItem<Item> IRON_PAXEL = ITEMS.register("iron_paxel",
            () -> new PaxelItem(Tiers.IRON, 5.0F, -3.2F, new Item.Properties().durability(375)));
    public static final DeferredItem<Item> GOLDEN_PAXEL = ITEMS.register("golden_paxel",
            () -> new PaxelItem(Tiers.GOLD, 5.0F, -3.1F, new Item.Properties().durability(48)));
    public static final DeferredItem<Item> DIAMOND_PAXEL = ITEMS.register("diamond_paxel",
            () -> new PaxelItem(Tiers.DIAMOND, 5.0F, -3.1F, new Item.Properties().durability(2341)));
    public static final DeferredItem<Item> NETHERITE_PAXEL = ITEMS.register("netherite_paxel",
            () -> new PaxelItem(Tiers.NETHERITE, 5.0F, -3.1F, new Item.Properties().durability(3046).fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
