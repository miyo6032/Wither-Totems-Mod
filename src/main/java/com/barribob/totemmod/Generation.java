package com.barribob.totemmod;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class Generation {
	public static void setupOreGen() {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			if (!(biome.getCategory() == Biome.Category.NETHER)) { // Only generate in nether biomes
				continue;
			}

			biome.addFeature(
					GenerationStage.Decoration.VEGETAL_DECORATION,
					Main.ModFeatures.totem.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(100))));
		}
	}
}
