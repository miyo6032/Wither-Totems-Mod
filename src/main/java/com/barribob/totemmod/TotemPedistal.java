package com.barribob.totemmod;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TotemPedistal extends Block {
	protected static VoxelShape BOTTOM_TOTEM_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	protected static VoxelShape TOP_TOTEM_SHAPE = Block.makeCuboidShape(5.0D, 2.0D, 5.0D, 11.0D, 16.0D, 11.0D);
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

	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("block.totemmod.tooltip_1").applyTextStyle(TextFormatting.GRAY));
		tooltip.add(new TranslationTextComponent("block.totemmod.tooltip_2").applyTextStyle(TextFormatting.GRAY));
		tooltip.add(new TranslationTextComponent("block.totemmod.tooltip_3").applyTextStyle(TextFormatting.GRAY));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
