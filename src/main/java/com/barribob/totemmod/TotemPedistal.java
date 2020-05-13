package com.barribob.totemmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TotemPedistal extends Block {
	protected static VoxelShape BOTTOM_TOTEM_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	protected static VoxelShape TOP_TOTEM_SHAPE = Block.makeCuboidShape(4.0D, 2.0D, 4.0D, 12.0D, 16.0D, 12.0D);
	protected static VoxelShape TOTEM_SHAPE = VoxelShapes.or(BOTTOM_TOTEM_SHAPE, TOP_TOTEM_SHAPE);

	public TotemPedistal(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return TOTEM_SHAPE;
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		if (worldIn.getBlockState(pos.up()).getBlock().equals(Main.ModBlocks.totem_top)) {
			worldIn.destroyBlock(pos.up(), true);
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}
}
