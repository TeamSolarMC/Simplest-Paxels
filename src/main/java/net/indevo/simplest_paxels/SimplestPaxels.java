package net.indevo.simplest_paxels;

import com.mojang.logging.LogUtils;
import net.indevo.simplest_paxels.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(SimplestPaxels.MODID)
public class SimplestPaxels
{
    public static final String MODID = "simplest_paxels";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SimplestPaxels(IEventBus modEventBus, ModContainer modContainer) {

        ModItems.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.WOODEN_PAXEL);
            event.accept(ModItems.STONE_PAXEL);
            event.accept(ModItems.IRON_PAXEL);
            event.accept(ModItems.GOLDEN_PAXEL);
            event.accept(ModItems.DIAMOND_PAXEL);
            event.accept(ModItems.NETHERITE_PAXEL);
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.WOODEN_PAXEL);
            event.accept(ModItems.STONE_PAXEL);
            event.accept(ModItems.IRON_PAXEL);
            event.accept(ModItems.GOLDEN_PAXEL);
            event.accept(ModItems.DIAMOND_PAXEL);
            event.accept(ModItems.NETHERITE_PAXEL);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
