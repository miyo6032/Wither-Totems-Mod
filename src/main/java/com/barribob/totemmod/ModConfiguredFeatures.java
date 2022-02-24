package com.barribob.totemmod;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModConfiguredFeatures {
    public static ConfiguredFeature<?, ?> configuredTotem = ModFeatures.totem.get().configured(FeatureConfiguration.NONE);
    public static PlacedFeature placedTotem = configuredTotem.placed(RarityFilter.onAverageOnceEvery(100), InSquarePlacement.spread(), BiomeFilter.biome());

    public static void setupFeatures(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;
            Registry<PlacedFeature> registry2 = BuiltinRegistries.PLACED_FEATURE;
            Registry.register(registry, new ResourceLocation(TotemConstants.MOD_ID, "configured_totem"), configuredTotem);
            Registry.register(registry2, new ResourceLocation(TotemConstants.MOD_ID, "placed_totem"), placedTotem);
        });
    }

    public static void modifyBiomes(final BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.NETHER) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES).add(() -> placedTotem);
        }
    }
}
