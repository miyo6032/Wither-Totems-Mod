package com.barribob.totemmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;

@Mod(value = TotemConstants.MOD_ID)
public class Main {

	public Main() {
		IEventBus eventBus = MinecraftForge.EVENT_BUS;
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(ModConfiguredFeatures::setupFeatures);
		eventBus.register(this);
		eventBus.addListener(ModConfiguredFeatures::modifyBiomes);
		ModFeatures.FEATURES.register(modEventBus);
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	@ObjectHolder(TotemConstants.MOD_ID)
	public static class ModBlocks {

		public static final Block totem_base = null;
		public static final Block totem_top = null;

		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			blockRegistryEvent.getRegistry().registerAll(
					new TotemPedistal(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 10f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(TotemConstants.MOD_ID, "totem_base"),
					new TotemTop(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 10f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(TotemConstants.MOD_ID, "totem_top"));
		}
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	@ObjectHolder(TotemConstants.MOD_ID)
	public static class ModTileEntities {

		public static final TileEntityType<TileEntityTotem> totem = null;

		@SubscribeEvent
		public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> evt) {
			evt.getRegistry().registerAll(TileEntityType.Builder.create(TileEntityTotem::new, ModBlocks.totem_top).build(null).setRegistryName(TotemConstants.MOD_ID, "totem"));
		}
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	@ObjectHolder(TotemConstants.MOD_ID)
	public static class ModPotions {

		public static final Effect looting = null;

		@SubscribeEvent
		public static void onTileEntityRegistry(final RegistryEvent.Register<Effect> evt) {
			evt.getRegistry().registerAll(
					new LootEffect(EffectType.HARMFUL, 65506).setRegistryName(TotemConstants.MOD_ID, "looting"));
		}
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	@ObjectHolder(TotemConstants.MOD_ID)
	public static class ModItems {

		public static final Item totem_base = null;
		public static final Item totem_top = null;

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			event.getRegistry().registerAll(
					new BlockItem(ModBlocks.totem_base, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(TotemConstants.MOD_ID, "totem_base"),
					new BlockItem(ModBlocks.totem_top, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(TotemConstants.MOD_ID, "totem_top"));
		}
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class clientStartup {
		@SubscribeEvent
		public static void onClientSetup(final FMLClientSetupEvent event) {
			RenderTypeLookup.setRenderLayer(ModBlocks.totem_top, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(ModBlocks.totem_base, RenderType.getCutout());
		}
	}
}
