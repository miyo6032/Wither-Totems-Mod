package com.barribob.totemmod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class TileEntityTotem extends BlockEntity {

	int tickCounter = 0;

	public TileEntityTotem(BlockPos p_155229_, BlockState p_155230_) {
		super(Main.ModTileEntities.totem, p_155229_, p_155230_);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, TileEntityTotem blockEntity) {
		if (!state.getValue(TotemTop.TRIGGERED)) {
			return;
		}

		if (blockEntity.tickCounter % 20 != 0) {
			blockEntity.tickCounter--;
			return;
		}

		blockEntity.tickCounter = 19;

		AABB box = new AABB(pos).inflate(15);
		List<Entity> mobs = level.getEntities(null, box);
		for (Entity mob : mobs) {
			if (mob instanceof Enemy && mob instanceof LivingEntity) {
				((LivingEntity) mob).addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 1));
				((LivingEntity) mob).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 0));
			}
			else if (mob instanceof Player) {
				((Player) mob).addEffect(new MobEffectInstance(Main.ModPotions.looting, 100, 1));
			}
		}
	}
}
