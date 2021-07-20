package net.fabricmc.Content.Blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.*;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import net.fabricmc.Content.Blocks.Lens0Entity;

public class Lens0 extends Block implements BlockEntityProvider
{
    public Lens0Entity entity;

    public Lens0(Settings settings)
    {
        super(settings.nonOpaque());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context)
    {
        return VoxelShapes.cuboid(1 / 16f, 7 / 16f, 1 / 16f, 15 / 16f, 9 / 16f, 15 / 16f);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) 
    {
        entity = new Lens0Entity(pos, state);
        return entity;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) 
    {
          return Lens0Entity::tick;
    }
}
