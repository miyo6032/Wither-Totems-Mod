package com.barribob.totemmod;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TotemConstants.MOD_ID);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> totem = FEATURES.register("totem", () -> new TotemFeature(NoneFeatureConfiguration.CODEC));
}
