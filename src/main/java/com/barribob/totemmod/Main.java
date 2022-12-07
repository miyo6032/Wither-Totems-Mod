package com.barribob.totemmod;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
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
					new TotemPedistal(Block.Properties.of(Material.STONE).strength(1.5f).explosionResistance(10f)).setRegistryName(TotemConstants.MOD_ID, "totem_base"),
					new TotemTop(Block.Properties.of(Material.STONE).strength(1.5f).explosionResistance(10f)).setRegistryName(TotemConstants.MOD_ID, "totem_top"));
		}
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	@ObjectHolder(TotemConstants.MOD_ID)
	public static class ModTileEntities {

		public static final BlockEntityType<TileEntityTotem> totem = null;

		@SubscribeEvent
		public static void onTileEntityRegistry(final RegistryEvent.Register<BlockEntityType<?>> evt) {
			evt.getRegistry().registerAll(BlockEntityType.Builder.of(TileEntityTotem::new, ModBlocks.totem_top).build(null).setRegistryName(TotemConstants.MOD_ID, "totem"));
		}
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	@ObjectHolder(TotemConstants.MOD_ID)
	public static class ModPotions {

		public static final MobEffect looting = null;

		@SubscribeEvent
		public static void onTileEntityRegistry(final RegistryEvent.Register<MobEffect> evt) {
			evt.getRegistry().registerAll(new LootEffect(MobEffectCategory.HARMFUL, 65506).setRegistryName(TotemConstants.MOD_ID, "looting"));
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
					new BlockItem(ModBlocks.totem_base, new Item.Properties().tab(CreativeModeTab.TAB_MISC)).setRegistryName(TotemConstants.MOD_ID, "totem_base"),
					new BlockItem(ModBlocks.totem_top, new Item.Properties().tab(CreativeModeTab.TAB_MISC)).setRegistryName(TotemConstants.MOD_ID, "totem_top"));
		}
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class clientStartup {
		@SubscribeEvent
		public static void onClientSetup(final FMLClientSetupEvent event) {
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.totem_top, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.totem_base, RenderType.cutout());
		}
	}
}
