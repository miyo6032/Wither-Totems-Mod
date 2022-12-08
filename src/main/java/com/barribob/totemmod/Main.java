package com.barribob.totemmod;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;

@Mod(value = TotemConstants.MOD_ID)
public class Main {

	public Main() {
		IEventBus eventBus = MinecraftForge.EVENT_BUS;
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		eventBus.register(this);
		ModBlocks.init();
		ModPotions.init();
		ModTileEntities.init();
		ModItems.init();
//		eventBus.addListener(ModConfiguredFeatures::modifyBiomes);
		ModFeatures.FEATURES.register(modEventBus);
	}

	public static class ModBlocks {
		private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TotemConstants.MOD_ID);
		public static final RegistryObject<Block> TOTEM_BASE = BLOCKS.register("totem_base", () -> new TotemPedistal(Block.Properties.of(Material.STONE).strength(1.5f).explosionResistance(10f)));
		public static final RegistryObject<Block> TOTEM_TOP = BLOCKS.register("totem_top", () -> new TotemTop(Block.Properties.of(Material.STONE).strength(1.5f).explosionResistance(10f)));
		
		public static void init() {
			BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		}
	}

	public static class ModTileEntities {
		private static final DeferredRegister<BlockEntityType<?>> BlockEntities = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TotemConstants.MOD_ID);
		public static final RegistryObject<BlockEntityType<TileEntityTotem>> TOTEM = BlockEntities.register("totem", () -> BlockEntityType.Builder.of(TileEntityTotem::new, ModBlocks.TOTEM_TOP.get()).build(null));

		public static void init() {
			BlockEntities.register(FMLJavaModLoadingContext.get().getModEventBus());
		}
	}

	public static class ModPotions {
		private static final DeferredRegister<MobEffect> MobEffects = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TotemConstants.MOD_ID);
		public static final RegistryObject<MobEffect> LOOTING = MobEffects.register("looting", () -> new LootEffect(MobEffectCategory.HARMFUL, 65506));

		public static void init() {
			MobEffects.register(FMLJavaModLoadingContext.get().getModEventBus());
		}
	}

	public static class ModItems {
		private static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, TotemConstants.MOD_ID);
		public static final RegistryObject<Item> TOTEM_BASE = Items.register("totem_base", () -> new BlockItem(ModBlocks.TOTEM_BASE.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
		public static final RegistryObject<Item> TOTEM_TOP = Items.register("totem_top", () -> new BlockItem(ModBlocks.TOTEM_TOP.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

		public static void init() {
			Items.register(FMLJavaModLoadingContext.get().getModEventBus());
		}
	}
}
