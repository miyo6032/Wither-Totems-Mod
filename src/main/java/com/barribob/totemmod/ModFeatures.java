package com.barribob.totemmod;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TotemConstants.MOD_ID);
    public static final RegistryObject<Feature<NoFeatureConfig>> totem = FEATURES.register("totem", () -> new TotemFeature(NoFeatureConfig.field_236558_a_));
}
