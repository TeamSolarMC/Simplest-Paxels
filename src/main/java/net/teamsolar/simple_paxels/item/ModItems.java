package net.teamsolar.simple_paxels.item;

import net.minecraft.world.item.ToolMaterial;
import net.teamsolar.simple_paxels.SimplestPaxels;
import net.teamsolar.simple_paxels.item.custom.PaxelItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(SimplestPaxels.MODID);

    public static final DeferredItem<Item> WOODEN_PAXEL = ITEMS.registerItem(
            "wooden_paxel",
            PaxelItem::new,
            () -> PaxelItem.paxelProperties(
                    ToolMaterial.WOOD,
                    new Item.Properties(),
                    6.0F,
                    -3.3F,
                    88

            )
    );
    public static final DeferredItem<Item> STONE_PAXEL = ITEMS.registerItem(
            "stone_paxel",
            PaxelItem::new,
            () -> PaxelItem.paxelProperties(
                    ToolMaterial.STONE,
                    new Item.Properties(),
                    7.0F,
                    -3.3F,
                    196

            )
    );
    public static final DeferredItem<Item> IRON_PAXEL = ITEMS.registerItem(
            "iron_paxel",
            PaxelItem::new,
            () -> PaxelItem.paxelProperties(
                    ToolMaterial.IRON,
                    new Item.Properties(),
                    6.0F,
                    -3.2F,
                    375

            )
    );
    public static final DeferredItem<Item> GOLDEN_PAXEL = ITEMS.registerItem(
            "golden_paxel",
            PaxelItem::new,
            () -> PaxelItem.paxelProperties(
                    ToolMaterial.GOLD,
                    new Item.Properties(),
                    6.0F,
                    -3.1F,
                    48

            )
    );
    public static final DeferredItem<Item> DIAMOND_PAXEL = ITEMS.registerItem(
            "diamond_paxel",
            PaxelItem::new,
            () -> PaxelItem.paxelProperties(
                    ToolMaterial.DIAMOND,
                    new Item.Properties(),
                    5.0F,
                    -3.1F,
                    2341

            )
    );
    public static final DeferredItem<Item> NETHERITE_PAXEL = ITEMS.registerItem(
            "netherite_paxel",
            PaxelItem::new,
            () -> PaxelItem.paxelProperties(
                    ToolMaterial.NETHERITE,
                    new Item.Properties().fireResistant(),
                    5.0F,
                    -3.1F,
                    3046

            )
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
