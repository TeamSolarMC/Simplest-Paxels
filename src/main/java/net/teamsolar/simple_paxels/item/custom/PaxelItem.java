package net.teamsolar.simple_paxels.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.teamsolar.simple_paxels.util.ModTags;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;

public class PaxelItem extends DiggerItemWithoutDurability {
    public PaxelItem(Tier tier, float attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(
                tier,
                ModTags.Blocks.PAXEL_MINEABLE,
                properties.attributes(
                        PaxelItem.createAttributes(tier, attackDamageModifier, attackSpeedModifier)
                )
        );
    }
    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        Player player = context.getPlayer();
        // First attempt axe actions
        Optional<BlockState> optional = this.evaluateNewBlockStateAsAxe(level, blockpos, player, level.getBlockState(blockpos), context);
        if (optional.isPresent()) {
            ItemStack itemstack = context.getItemInHand();
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
            }

            level.setBlock(blockpos, optional.get(), 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, optional.get()));
            if (player != null) {
                itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        // Then attempt shovel actions
        if (context.getClickedFace() == Direction.DOWN) {
            return InteractionResult.PASS;
        } else {
            BlockState intermediateBlockState = blockstate.getToolModifiedState(context, net.neoforged.neoforge.common.ItemAbilities.SHOVEL_FLATTEN, false);
            BlockState finalBlockState = null;
            if (intermediateBlockState != null && level.getBlockState(blockpos.above()).isAir()) {
                level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                finalBlockState = intermediateBlockState;
            } else {
                intermediateBlockState = blockstate.getToolModifiedState(context, net.neoforged.neoforge.common.ItemAbilities.SHOVEL_DOUSE, false);
                if (intermediateBlockState != null) {
                    if (!level.isClientSide()) {
                        level.levelEvent(null, 1009, blockpos, 0);
                    }
                    finalBlockState = intermediateBlockState;
                }
            }
            if (finalBlockState != null) {
                if (!level.isClientSide) {
                    level.setBlock(blockpos, finalBlockState, 11);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, finalBlockState));
                    if (player != null) {
                        context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                    }
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    private Optional<BlockState> evaluateNewBlockStateAsAxe(Level level, BlockPos pos, @Nullable Player player, BlockState state, UseOnContext context) {
        Optional<BlockState> optional = Optional.ofNullable(state.getToolModifiedState(context, net.neoforged.neoforge.common.ItemAbilities.AXE_STRIP, false));
        if (optional.isPresent()) {
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            return optional;
        } else {
            Optional<BlockState> optional1 = Optional.ofNullable(state.getToolModifiedState(context, net.neoforged.neoforge.common.ItemAbilities.AXE_SCRAPE, false));
            if (optional1.isPresent()) {
                level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3005, pos, 0);
                return optional1;
            } else {
                Optional<BlockState> optional2 = Optional.ofNullable(state.getToolModifiedState(context, net.neoforged.neoforge.common.ItemAbilities.AXE_WAX_OFF, false));
                if (optional2.isPresent()) {
                    level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.levelEvent(player, 3004, pos, 0);
                    return optional2;
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility) || ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);
    }
}
