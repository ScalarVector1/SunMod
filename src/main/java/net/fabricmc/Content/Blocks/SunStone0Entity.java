package net.fabricmc.Content.Blocks;

import net.fabricmc.sunmod.SunMod;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.Core.SolarCore.BeamProperty;
import net.fabricmc.Core.SolarCore.SolarDonor;
import net.fabricmc.Core.SolarCore.SolarReciever;
import net.fabricmc.fabric.api.block.entity.*;
import net.minecraft.nbt.*;

public class SunStone0Entity extends BlockEntity implements Inventory, SolarReciever
{
    public DefaultedList<ItemStack> items = DefaultedList.ofSize(3, ItemStack.EMPTY);
    public int nextFreeStack = 0;

    public int intensity;
    public int radius;
    public List<BeamProperty> properties = new ArrayList<BeamProperty>();

    public SunStone0Entity(BlockPos pos, BlockState state)
    {
        super(SunMod.SunStone0EntityType, pos, state);
    }

    public int intensityIn() {return intensity;}
    public int radiusIn() {return radius;}
    public List<BeamProperty> propertiesIn() {return properties;}

    @Override 
    public void acceptBeam(SolarDonor donor)
    {
        reset();

        intensity = donor.intensityOut();
        radius = donor.radiusOut();
        properties = donor.propertiesOut();
    }

    public void reset()
    {
        intensity = 0;
        radius = 0;
        properties.clear();
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag)
    {
        super.writeNbt(tag);
        Inventories.writeNbt(tag, items);

        return tag;
    }

    @Override
    public void readNbt(NbtCompound tag)
    {
        super.readNbt(tag);
        Inventories.readNbt(tag, items);
    }

    //Inventory interface garbage boilerplate below. TODO: Fix this later

    @Override
    public void clear()
    {
        items.clear();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) 
    {
        return !player.getMainHandStack().isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) 
    {
        return items.get(slot);
    }

    @Override
    public boolean isEmpty()
    {
        for(int k = 0; k < items.size(); k++)
            if(items.get(k) != ItemStack.EMPTY)
                return false;

        return true;
    }

    @Override
    public ItemStack removeStack(int slot) 
    {
        return Inventories.removeStack(items, slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) 
    {
        return Inventories.splitStack(items, slot, amount);
    }

    @Override
    public void setStack(int slot, ItemStack stack)
    {
        items.set(slot, stack);

        if(stack.getCount() > getMaxCountPerStack())
            stack.setCount(getMaxCountPerStack());
    }

    @Override
    public int size() {
        return 3;
    }


}