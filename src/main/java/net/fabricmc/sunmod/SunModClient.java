package net.fabricmc.sunmod;

import net.fabricmc.Content.Blocks.SunStone0;
import net.fabricmc.Content.Blocks.SunStone0Entity;
import net.fabricmc.Content.Blocks.SunStone0Renderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.*;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.client.render.*;

public class SunModClient implements ClientModInitializer 
{
    @Override
    public void onInitializeClient() 
    {
        BlockEntityRendererRegistry.INSTANCE.register(SunMod.SunStone0EntityType, SunStone0Renderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(SunMod.Lens0, RenderLayer.getTranslucent());
    }
}