package com.barribob.totemmod;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityTotem extends TileEntity implements ITickableTileEntity {

	int tickCounter = 0;

	public TileEntityTotem() {
		super(Main.ModTileEntities.totem);
	}

	@Override
	public void tick() {
		if (!this.getBlockState().get(TotemTop.TRIGGERED)) {
			return;
		}

		if (tickCounter % 20 != 0) {
			tickCounter--;
			return;
		}

		tickCounter = 19;

		AxisAlignedBB box = new AxisAlignedBB(pos).grow(15);
		List<Entity> mobs = this.world.getEntitiesWithinAABB(Entity.class, box);
		for (Entity mob : mobs) {
			if (mob instanceof IMob && mob instanceof LivingEntity) {
				((LivingEntity) mob).addPotionEffect(new EffectInstance(Effects.STRENGTH, 40, 1));
				((LivingEntity) mob).addPotionEffect(new EffectInstance(Effects.SPEED, 40, 0));
			}
			else if (mob instanceof PlayerEntity) {
				((PlayerEntity) mob).addPotionEffect(new EffectInstance(Main.ModPotions.looting, 100, 1));
			}
		}
	}
}
