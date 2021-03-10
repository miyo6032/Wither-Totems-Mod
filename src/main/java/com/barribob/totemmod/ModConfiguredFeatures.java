package com.barribob.totemmod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModConfiguredFeatures {
    public static ConfiguredFeature<?, ?> configuredTotem = ModFeatures.totem.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).chance(10);

    public static void setupFeatures(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;
            Registry.register(registry, new ResourceLocation(TotemConstants.MOD_ID, "configured_totem"), configuredTotem);
        });
    }

    public static void modifyBiomes(final BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.Category.NETHER) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES).add(() -> configuredTotem);
        }
    }
}
