package com.barribob.totemmod;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class TotemPedistal extends Block {
	protected static VoxelShape BOTTOM_TOTEM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	protected static VoxelShape TOP_TOTEM_SHAPE = Block.box(5.0D, 2.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	protected static VoxelShape TOTEM_SHAPE = Shapes.or(BOTTOM_TOTEM_SHAPE, TOP_TOTEM_SHAPE);

	public TotemPedistal(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
		return TOTEM_SHAPE;
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level world, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
		if (world.getBlockState(pos.above()).getBlock().equals(Main.ModBlocks.totem_top)) {
			world.destroyBlock(pos.above(), true);
		}
		return super.onDestroyedByPlayer(state, world, pos, player, willHarvest, fluid);
	}

	@Override
	public void appendHoverText(ItemStack stack, BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(new TranslatableComponent("block.totemmod.tooltip_1").withStyle(ChatFormatting.GRAY));
		tooltip.add(new TranslatableComponent("block.totemmod.tooltip_2").withStyle(ChatFormatting.GRAY));
		tooltip.add(new TranslatableComponent("block.totemmod.tooltip_3").withStyle(ChatFormatting.GRAY));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
