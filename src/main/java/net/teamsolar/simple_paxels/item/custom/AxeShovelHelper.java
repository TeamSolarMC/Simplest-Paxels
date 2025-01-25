package net.teamsolar.simple_paxels.item.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShovelItem;

import java.util.Map;

public class AxeShovelHelper {
    public static final Map<Block, Block> STRIPPABLES = AxeItem.STRIPPABLES;
    public static final Map<Block, BlockState> FLATTENABLES = ShovelItem.FLATTENABLES;
}
