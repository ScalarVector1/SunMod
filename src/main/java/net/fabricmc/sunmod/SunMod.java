package net.fabricmc.sunmod;

import net.fabricmc.Content.Blocks.Lens0;
import net.fabricmc.Content.Blocks.Lens0Entity;
import net.fabricmc.Content.Blocks.SunStone0;
import net.fabricmc.Content.Blocks.SunStone0Entity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.*;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SunMod implements ModInitializer 
{
	public static Block SunStone0;
	public static Block Lens0;
	public static BlockEntityType<SunStone0Entity> SunStone0EntityType;
	public static BlockEntityType<Lens0Entity> Lens0EntityType;

	@Override
	public void onInitialize() 
	{
		SunStone0 = new SunStone0(FabricBlockSettings.of(Material.STONE).hardness(1.0f));
		Registry.register(Registry.BLOCK, new Identifier("sunmod", "sunstone0"), SunStone0);
        Registry.register(Registry.ITEM, new Identifier("sunmod", "sunstone0"), new BlockItem(SunStone0, new Item.Settings().group(ItemGroup.MISC)));
		SunStone0EntityType = Registry.register(Registry.BLOCK_ENTITY_TYPE, "sunmod:sunstone0entity", FabricBlockEntityTypeBuilder.create(SunStone0Entity::new, SunStone0).build(null));

		Lens0 = new Lens0(FabricBlockSettings.of(Material.STONE).hardness(1.0f));
		Registry.register(Registry.BLOCK, new Identifier("sunmod", "lens0"), Lens0);
        Registry.register(Registry.ITEM, new Identifier("sunmod", "lens0"), new BlockItem(Lens0, new Item.Settings().group(ItemGroup.MISC)));
		Lens0EntityType = Registry.register(Registry.BLOCK_ENTITY_TYPE, "sunmod:lens0entity", FabricBlockEntityTypeBuilder.create(Lens0Entity::new, Lens0).build(null));
	}
}
