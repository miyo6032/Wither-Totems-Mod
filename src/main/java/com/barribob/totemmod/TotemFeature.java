package com.barribob.totemmod;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class TotemFeature extends Feature<NoFeatureConfig> {

	public TotemFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn, false);
	}

	private boolean isValid(IWorld worldIn, BlockPos pos) {
		boolean correctBlock = worldIn.getBlockState(pos.down()).getBlock().equals(Blocks.NETHERRACK)
				|| worldIn.getBlockState(pos.down()).getBlock().equals(Blocks.NETHER_BRICKS)
				|| worldIn.getBlockState(pos.down()).getBlock().equals(Blocks.SOUL_SAND);
		return correctBlock && worldIn.isAirBlock(pos) && worldIn.isAirBlock(pos.up());
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, NoFeatureConfig config) {

		pos = new BlockPos(pos.getX(), 255, pos.getZ());

		// Keep moving down until we find a valid position to place the totem
		while (!isValid(worldIn, pos)) {
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

		worldIn.setBlockState(pos.up(), Main.ModBlocks.totem_top.getDefaultState().with(TotemTop.FACING, dir)
				.with(TotemTop.TRIGGERED, Boolean.valueOf(true)), 0);
		worldIn.setBlockState(pos, Main.ModBlocks.totem_base.getDefaultState(), 0);
		return true;
	}
}
