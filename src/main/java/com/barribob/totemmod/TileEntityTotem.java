package com.barribob.totemmod;

import java.util.List;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
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
		List<MobEntity> mobs = this.world.getEntitiesWithinAABB(MobEntity.class, box);
		for (MobEntity mob : mobs) {
			if (mob instanceof WitherSkeletonEntity) {
				mob.addPotionEffect(new EffectInstance(Main.ModPotions.loot, 40, 1));
				mob.addPotionEffect(new EffectInstance(Effects.STRENGTH, 40, 1));
			}
		}
	}

}
