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

public class Lens0Entity extends BlockEntity implements SolarDonor, SolarReciever
{
    public int intensity;
    public int radius;
    public List<BeamProperty> properties = new ArrayList<BeamProperty>();

    public Lens0Entity(BlockPos pos, BlockState state)
    {
        super(SunMod.Lens0EntityType, pos, state);
    }

    public int intensityIn() {return intensity;}
    public int radiusIn() {return radius;}
    public List<BeamProperty> propertiesIn() {return properties;}

    public static void tick(World world, BlockPos pos, BlockState state, BlockEntity be) 
    {
        ((Lens0Entity)be).castOut();
    }

    @Override 
    public void acceptBeam(SolarDonor donor)
    {
        intensity = donor.intensityOut();
        radius = donor.radiusOut();
        properties = donor.propertiesOut();
    }

    @Override
    public int intensityOut() 
    {
        if(intensity < 3)
            return intensity + 1;

        return intensity;
    }

    @Override
    public int radiusOut() 
    {
        if(intensity < 3)
            return radius - 1;

        return radius;
    }

    @Override
    public List<BeamProperty> propertiesOut() 
    {
        return properties;
    }

    @Override
    public SolarReciever castOut() 
    {
        int down = 1;

        while(true)
        {
            if(down >= 10)
            {
                reset();
                return null;
            }

            var scanpos = pos.down(down);
            var BE = getWorld().getBlockEntity(scanpos);

            down++;

            if(BE != null)
            {
                if(BE instanceof SolarReciever)
                {
                    var reciever = (SolarReciever)BE;
                    reciever.acceptBeam(this);
                    reset();
                    return reciever;
                }

                reset();
                return null;
            }
        }
    }

    public void reset()
    {
        intensity = 0;
        radius = 0;
        properties.clear();
    }
}