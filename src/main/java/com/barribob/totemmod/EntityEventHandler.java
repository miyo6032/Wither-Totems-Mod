package com.barribob.totemmod;

import com.barribob.totemmod.Main.ModPotions;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class EntityEventHandler {

	// Apply the looting potion effect
	@SubscribeEvent
	public static void dropItems(LootingLevelEvent event) {
		if (event.getEntityLiving() != null) {
			LivingEntity entity = event.getEntityLiving();
			if (entity.isPotionActive(ModPotions.loot)) {
				event.setLootingLevel(
						event.getLootingLevel() + entity.getActivePotionEffect(ModPotions.loot).getAmplifier() + 1);
			}
		}
	}
}
