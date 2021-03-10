package com.barribob.totemmod;

import com.mojang.serialization.Codec;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class TotemFeature extends Feature<NoFeatureConfig> {

	public TotemFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	private boolean isValid(ISeedReader reader, BlockPos pos) {
		boolean isSolidBlock = reader.getBlockState(pos.down()).isSolid();
		return isSolidBlock && reader.isAirBlock(pos) && reader.isAirBlock(pos.up());
	}

	@Override
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		pos = new BlockPos(pos.getX(), 120, pos.getZ());

		// Keep moving down until we find a valid position to place the totem
		while (!isValid(reader, pos)) {
			pos = pos.down();
			if (pos.getY() < 30) {
				return true;
			}
		}

		// Gets a random direction to face in
		Direction dir = Direction.NORTH;
		int rotations = rand.nextInt(4);
		for (int i = 0; i < rotations; i++) {
			dir = dir.rotateY();
		}

		reader.setBlockState(pos.up(), Main.ModBlocks.totem_top.getDefaultState().with(TotemTop.FACING, dir).with(TotemTop.TRIGGERED, true), 0);
		reader.setBlockState(pos, Main.ModBlocks.totem_base.getDefaultState(), 0);
		return true;
	}
}
