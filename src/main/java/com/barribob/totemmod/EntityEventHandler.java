package com.barribob.totemmod;

import com.barribob.totemmod.Main.ModPotions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class EntityEventHandler {

	/**
	 * Whenever an entity dies, see if the killer has the looting potion effect, and if so, apply looting bonuses to the drops
	 */
	@SubscribeEvent
	public static void dropItems(LootingLevelEvent event) {
		if (event.getDamageSource() != null && event.getDamageSource().getEntity() != null) {
			Entity killer = event.getDamageSource().getEntity();
			if (killer instanceof LivingEntity && ((LivingEntity) killer).hasEffect(ModPotions.LOOTING.get())) {
				event.setLootingLevel(event.getLootingLevel() + ((LivingEntity) killer).getEffect(ModPotions.LOOTING.get()).getAmplifier() + 1);
			}
		}
	}
}
