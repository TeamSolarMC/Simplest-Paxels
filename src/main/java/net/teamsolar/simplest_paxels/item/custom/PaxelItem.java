package net.teamsolar.simplest_paxels.item.custom;

import net.teamsolar.simplest_paxels.util.ModTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.state.properties.Property;



import java.util.Optional;

public class PaxelItem extends DiggerItem {

    public PaxelItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pAttackDamageModifier, pAttackSpeedModifier, pTier, ModTags.Blocks.PAXEL_MINEABLE, pProperties);
    }
    
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockState = level.getBlockState(blockPos);
        Block block = blockState.getBlock();
        ItemStack itemStack = context.getItemInHand();
        
        if (player == null) {
            return InteractionResult.PASS;
        }
        
        // Try log stripping
        Optional<Block> strippedBlock = getStrippedBlock(block);
        if (strippedBlock.isPresent()) {
            return performLogStripping(level, blockPos, player, itemStack, strippedBlock.get(), blockState);
        }
        
        // Try wax removal
        Optional<Block> unwaxedBlock = getUnwaxedBlock(block);
        if (unwaxedBlock.isPresent()) {
            return performWaxRemoval(level, blockPos, player, itemStack, unwaxedBlock.get(), blockState);
        }
        
        // Try rust removal (copper oxidation removal)
        Optional<Block> deoxidizedBlock = getDeoxidizedBlock(block);
        if (deoxidizedBlock.isPresent()) {
            return performRustRemoval(level, blockPos, player, itemStack, deoxidizedBlock.get(), blockState);
        }
        
        // Try path creation
        if (canCreatePath(blockState, level, blockPos)) {
            return performPathCreation(level, blockPos, player, itemStack, Blocks.DIRT_PATH, blockState);
        }
        
        return InteractionResult.PASS;
    }
    
    /**
     * Finds the stripped variant of a log or wood block using tags and registry name patterns.
     * Works with both vanilla and modded blocks.
     */
    private Optional<Block> getStrippedBlock(Block block) {
        // Check if block is in logs or logs_that_burn tag
        if (!block.defaultBlockState().is(BlockTags.LOGS) && !block.defaultBlockState().is(BlockTags.LOGS_THAT_BURN)) {
            return Optional.empty();
        }
        
        ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(block);
        if (blockId == null) return Optional.empty();
        
        // Try to find stripped variant by adding "stripped_" prefix
        String path = blockId.getPath();
        String strippedPath = "stripped_" + path;
        ResourceLocation strippedId = new ResourceLocation(blockId.getNamespace(), strippedPath);
        
        Block strippedBlock = ForgeRegistries.BLOCKS.getValue(strippedId);
        return strippedBlock != null && strippedBlock != Blocks.AIR ? Optional.of(strippedBlock) : Optional.empty();
    }
    
    /**
     * Finds the unwaxed variant of a waxed copper block using registry name patterns.
     * Works with both vanilla and modded waxed copper blocks.
     */
    private Optional<Block> getUnwaxedBlock(Block block) {
        ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(block);
        if (blockId == null) return Optional.empty();
        
        String path = blockId.getPath();
        
        // Check if block name starts with "waxed_"
        if (!path.startsWith("waxed_")) {
            return Optional.empty();
        }
        
        // Remove "waxed_" prefix to get unwaxed variant
        String unwaxedPath = path.substring(6); // Remove "waxed_"
        ResourceLocation unwaxedId = new ResourceLocation(blockId.getNamespace(), unwaxedPath);
        
        Block unwaxedBlock = ForgeRegistries.BLOCKS.getValue(unwaxedId);
        return unwaxedBlock != null && unwaxedBlock != Blocks.AIR ? Optional.of(unwaxedBlock) : Optional.empty();
    }
    
    /**
     * Finds the less oxidized variant of a copper block (rust removal).
     * Works with both vanilla and modded copper blocks.
     */
    private Optional<Block> getDeoxidizedBlock(Block block) {
        ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(block);
        if (blockId == null) return Optional.empty();
        
        String path = blockId.getPath();
        String namespace = blockId.getNamespace();
        
        // Handle oxidized -> weathered
        if (path.contains("oxidized_")) {
            String weatheredPath = path.replace("oxidized_", "weathered_");
            ResourceLocation weatheredId = new ResourceLocation(namespace, weatheredPath);
            Block weatheredBlock = ForgeRegistries.BLOCKS.getValue(weatheredId);
            if (weatheredBlock != null && weatheredBlock != Blocks.AIR) {
                return Optional.of(weatheredBlock);
            }
        }
        
        // Handle weathered -> exposed
        if (path.contains("weathered_")) {
            String exposedPath = path.replace("weathered_", "exposed_");
            ResourceLocation exposedId = new ResourceLocation(namespace, exposedPath);
            Block exposedBlock = ForgeRegistries.BLOCKS.getValue(exposedId);
            if (exposedBlock != null && exposedBlock != Blocks.AIR) {
                return Optional.of(exposedBlock);
            }
        }
        
        // Handle exposed -> clean copper
        if (path.contains("exposed_")) {
            String cleanPath = path.replace("exposed_", "");
            ResourceLocation cleanId = new ResourceLocation(namespace, cleanPath);
            Block cleanBlock = ForgeRegistries.BLOCKS.getValue(cleanId);
            if (cleanBlock != null && cleanBlock != Blocks.AIR) {
                return Optional.of(cleanBlock);
            }
        }
        
        return Optional.empty();
    }
    
    /**
     * Checks if a block can be converted to a dirt path.
     * Uses block tags and specific block checks to work with modded blocks.
     */
    private boolean canCreatePath(BlockState blockState, Level level, BlockPos pos) {
        Block block = blockState.getBlock();
        
        // Check if the block above is air or replaceable
        BlockState aboveState = level.getBlockState(pos.above());
        if (!aboveState.isAir() && !aboveState.canBeReplaced()) {
            return false;
        }
        
        // Check for vanilla blocks that can become paths
        if (block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || 
            block == Blocks.ROOTED_DIRT || block == Blocks.MYCELIUM || block == Blocks.PODZOL) {
            return true;
        }
        
        // Check if block is in dirt tag (for modded compatibility)
        if (blockState.is(BlockTags.DIRT)) {
            return true;
        }
        
        // Additional check for blocks that might be grass-like or dirt-like based on name
        ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(block);
        if (blockId != null) {
            String path = blockId.getPath().toLowerCase();
            return path.contains("grass") || path.contains("dirt") || path.contains("soil") || path.contains("earth");
        }
        
        return false;
    }
    
    /**
     * Safely copies compatible properties from source block state to target block state.
     */
    private BlockState copyBlockStateProperties(BlockState source, BlockState target) {
        for (Property<?> property : source.getProperties()) {
            if (target.hasProperty(property)) {
                target = copyPropertySafely(source, target, property);
            }
        }
        return target;
    }
    
    /**
     * Helper method to safely copy a single property with proper generic handling.
     */
    private <T extends Comparable<T>> BlockState copyPropertySafely(BlockState source, BlockState target, Property<T> property) {
        try {
            T value = source.getValue(property);
            return target.setValue(property, value);
        } catch (Exception e) {
            // If property transfer fails, return target unchanged
            return target;
        }
    }
    
    private InteractionResult performLogStripping(Level level, BlockPos pos, Player player, ItemStack itemStack, Block strippedBlock, BlockState originalState) {
        if (!level.isClientSide) {
            // Play sound
            level.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            
            // Set the stripped block (preserve properties like axis)
            BlockState newState = strippedBlock.defaultBlockState();
            // Try to preserve properties like axis, waterlogged, etc.
            newState = copyBlockStateProperties(originalState, newState);
            level.setBlock(pos, newState, 11);
            
            // Damage the tool
            if (!player.getAbilities().instabuild) {
                itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
            }
            
            // Add particles on server side
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.ITEM_SNOWBALL, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, 0.3, 0.3, 0.3, 0.1);
            }
        }
        
        return InteractionResult.SUCCESS;
    }
    
    private InteractionResult performWaxRemoval(Level level, BlockPos pos, Player player, ItemStack itemStack, Block unwaxedBlock, BlockState originalState) {
        if (!level.isClientSide) {
            // Play sound
            level.playSound(null, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
            
            // Set the unwaxed block (preserve block state properties)
            BlockState newState = unwaxedBlock.defaultBlockState();
            // Try to preserve properties like facing, waterlogged, etc.
            newState = copyBlockStateProperties(originalState, newState);
            level.setBlock(pos, newState, 11);
            
            // Drop honeycomb
            if (!player.getAbilities().instabuild) {
                ItemEntity honeycombEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.HONEYCOMB));
                level.addFreshEntity(honeycombEntity);
                
                // Damage the tool
                itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
            }
            
            // Add particles
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.WAX_OFF, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 8, 0.3, 0.3, 0.3, 0.1);
            }
        }
        
        return InteractionResult.SUCCESS;
    }
    
    private InteractionResult performPathCreation(Level level, BlockPos pos, Player player, ItemStack itemStack, Block pathBlock, BlockState originalState) {
        if (!level.isClientSide) {
            // Play sound
            level.playSound(null, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            
            // Set the path block
            level.setBlock(pos, pathBlock.defaultBlockState(), 11);
            
            // Damage the tool
            if (!player.getAbilities().instabuild) {
                itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
            }
            
            // Add particles
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.ITEM_SNOWBALL, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, 0.3, 0.3, 0.3, 0.1);
            }
        }
        
        return InteractionResult.SUCCESS;
    }
    
    private InteractionResult performRustRemoval(Level level, BlockPos pos, Player player, ItemStack itemStack, Block cleanerBlock, BlockState originalState) {
        if (!level.isClientSide) {
            // Play sound (similar to axe scraping)
            level.playSound(null, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
            
            // Set the cleaner block (preserve block state properties)
            BlockState newState = cleanerBlock.defaultBlockState();
            // Try to preserve properties like facing, waterlogged, etc.
            newState = copyBlockStateProperties(originalState, newState);
            level.setBlock(pos, newState, 11);
            
            // Damage the tool
            if (!player.getAbilities().instabuild) {
                itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
            }
            
            // Add particles
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.SCRAPE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 8, 0.3, 0.3, 0.3, 0.1);
            }
        }
        
        return InteractionResult.SUCCESS;
    }
}
