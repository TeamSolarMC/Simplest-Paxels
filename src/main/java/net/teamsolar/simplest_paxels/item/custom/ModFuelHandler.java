package net.teamsolar.simplest_paxels.item.custom;

import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamsolar.simplest_paxels.SimplestPaxels;
import net.teamsolar.simplest_paxels.item.ModItems;

@Mod.EventBusSubscriber(modid = SimplestPaxels.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModFuelHandler {

    @SubscribeEvent
    public static void onFurnaceFuel(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().is(ModItems.WOODEN_PAXEL.get())) {
            event.setBurnTime(200);
        }
    }
}
