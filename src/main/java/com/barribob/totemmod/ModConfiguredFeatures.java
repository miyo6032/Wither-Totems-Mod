package com.barribob.totemmod;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ModConfiguredFeatures {
    static Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> configuredFeature = FeatureUtils.register(new ResourceLocation(TotemConstants.MOD_ID, "configured_totem").toString(), ModFeatures.totem.get());
    static Holder<PlacedFeature> placedFeature = PlacementUtils.register(new ResourceLocation(TotemConstants.MOD_ID, "placed_totem").toString(), configuredFeature, RarityFilter.onAverageOnceEvery(100), InSquarePlacement.spread(), BiomeFilter.biome());

//    public static void modifyBiomes(final BiomeLoadingEvent event) {
//        if (event.getCategory() == Biome.BiomeCategory.NETHER) {
//            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES).add(placedFeature);
//        }
//    }
}
