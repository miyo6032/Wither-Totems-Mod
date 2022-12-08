package com.barribob.totemmod;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class TotemFeature extends Feature<NoneFeatureConfiguration> {

	public TotemFeature(Codec<NoneFeatureConfiguration> p_65786_) {
		super(p_65786_);
	}

	private boolean isValid(WorldGenLevel reader, BlockPos pos) {
		boolean isSolidBlock = reader.getBlockState(pos.below()).isSolidRender(reader, pos.below());
		return isSolidBlock && reader.isEmptyBlock(pos) && reader.isEmptyBlock(pos.above());
	}

	public boolean generate(WorldGenLevel reader, RandomSource rand, BlockPos pos) {
		pos = new BlockPos(pos.getX(), 120, pos.getZ());

		// Keep moving down until we find a valid position to place the totem
		while (!isValid(reader, pos)) {
			pos = pos.below();
			if (pos.getY() < 30) {
				return true;
			}
		}

		// Gets a random direction to face in
		Direction dir = Direction.NORTH;
		int rotations = rand.nextInt(4);
		for (int i = 0; i < rotations; i++) {
			dir = dir.getClockWise();
		}

		reader.setBlock(pos.above(), Main.ModBlocks.TOTEM_TOP.get().defaultBlockState().setValue(TotemTop.FACING, dir).setValue(TotemTop.TRIGGERED, true), 0);
		reader.setBlock(pos, Main.ModBlocks.TOTEM_BASE.get().defaultBlockState(), 0);
		return true;
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		return this.generate(context.level(), context.random(), context.origin());
	}
}
