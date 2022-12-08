package com.barribob.totemmod;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class TotemTop extends BaseEntityBlock {

	public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;
	public static DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	protected static VoxelShape TOTEM_HEAD = Block.box(4.0D, 8.0D, 4.0D, 12.0D, 16.0D, 12.0D);
	protected static VoxelShape TOTEM_NECK = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 8.0D, 11.0D);
	protected static VoxelShape TOTEM_SHAPE = Shapes.or(TOTEM_HEAD, TOTEM_NECK);

	public TotemTop(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(TRIGGERED, false));
	}

	@Override
	public RenderShape getRenderShape(BlockState p_49232_) {
		return RenderShape.MODEL;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return TOTEM_SHAPE;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(TRIGGERED, false);
	}

	@Deprecated
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Deprecated
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
		p_49915_.add(FACING, TRIGGERED);
	}

	@Override
	public void animateTick(BlockState p_220827_, Level worldIn, BlockPos pos, RandomSource rand) {
		if (worldIn.getBlockState(pos).getValue(TRIGGERED)) {
			worldIn.addParticle(DustParticleOptions.REDSTONE,
					pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D,
					0.0D, 0.0D);
		}
	}

	@Override
	public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (worldIn.getBlockState(pos.below()).getBlock().equals(Main.ModBlocks.TOTEM_BASE.get())) {
			worldIn.setBlock(pos, state.setValue(TRIGGERED, true), 2);
		}
		else {
			worldIn.setBlock(pos, state.setValue(TRIGGERED, false), 2);
		}
	}

	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState state2, boolean flag) {
		neighborChanged(state, worldIn, pos, null, null, false);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new TileEntityTotem(p_153215_, p_153216_);
	}

	@Override
	public void appendHoverText(ItemStack stack, BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(Component.translatable("block.totemmod.tooltip_1").withStyle(ChatFormatting.GRAY));
		tooltip.add(Component.translatable("block.totemmod.tooltip_2").withStyle(ChatFormatting.GRAY));
		tooltip.add(Component.translatable("block.totemmod.tooltip_3").withStyle(ChatFormatting.GRAY));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
		return createTickerHelper(p_153214_, Main.ModTileEntities.TOTEM.get(), TileEntityTotem::tick);
	}
}
