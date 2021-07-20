package net.fabricmc.Content.Blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
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

public class SunStone0 extends Block implements BlockEntityProvider
{
    public SunStone0Entity entity;

    public SunStone0(Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) 
    {
        if (!world.isClient)
        {
            player.sendMessage(new LiteralText("Intensity recieved: " + entity.intensity), false);

            var handItem = player.getStackInHand(hand);

            if(!handItem.isEmpty() && entity.nextFreeStack < entity.size())
            {
                entity.setStack(entity.nextFreeStack, handItem.copy());
                handItem.setCount(0);
                entity.nextFreeStack++;

                entity.markDirty();
            }
            else if (entity.nextFreeStack > 0)
            {
                player.giveItemStack(entity.items.get(entity.nextFreeStack - 1).copy());
                entity.items.get(entity.nextFreeStack - 1).setCount(0);
                entity.nextFreeStack--;

                entity.markDirty();
            }
        }
 
        return ActionResult.SUCCESS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context)
    {
        return VoxelShapes.cuboid(0, 0, 0, 1, 2 / 16f, 1);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) 
    {
        entity = new SunStone0Entity(pos, state);
        return entity;
    }
}
