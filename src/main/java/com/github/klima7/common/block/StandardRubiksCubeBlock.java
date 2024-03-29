package com.github.klima7.common.block;

import com.github.klima7.common.entity.StandardRubiksCubeBlockEntity;
import com.github.klima7.core.init.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StandardRubiksCubeBlock extends BaseRubiksCubeBlock {

    private Direction facing;   // Auxiliary variable to pass value between callback methods

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return BlockEntityRegistry.STANDARD_RUBIKS_CUBE.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        this.facing = context.getHorizontalDirection().getOpposite();
        return super.getStateForPlacement(context);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity,
                            ItemStack itemStack) {
        StandardRubiksCubeBlockEntity entity = (StandardRubiksCubeBlockEntity) level.getBlockEntity(blockPos);
        entity.initializeFromItem(itemStack);
        if(this.facing != null) {
            entity.setFacing(facing);
        }
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        if(player.isCreative()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            dropResources(blockState, level, blockPos, blockEntity);
        }
        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootContext.Builder lootContextBuilder) {
        StandardRubiksCubeBlockEntity entity = (StandardRubiksCubeBlockEntity) lootContextBuilder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        ItemStack item = entity.asItem();
        return List.of(item);
    }

}
