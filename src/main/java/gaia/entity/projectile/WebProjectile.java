package gaia.entity.projectile;

import gaia.registry.GaiaRegistry;
import gaia.util.SharedEntityData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class WebProjectile extends SmallFireball {
	public WebProjectile(EntityType<? extends SmallFireball> entityType, Level level) {
		super(entityType, level);
	}

	public WebProjectile(Level level, LivingEntity livingEntity, double accelX, double accelY, double accelZ) {
		super(level, livingEntity, accelX, accelY, accelZ);
	}

	@Override
	public ItemStack getItem() {
		ItemStack itemstack = this.getItemRaw();
		return itemstack.isEmpty() ? new ItemStack(GaiaRegistry.PROJECTILE_WEB.get()) : itemstack;
	}

	public WebProjectile(SpawnEntity spawnEntity, Level level) {
		this(GaiaRegistry.MAGIC.get(), level);
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public EntityType<?> getType() {
		return GaiaRegistry.WEB.get();
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		return ParticleTypes.EXPLOSION_EMITTER;
	}

	@Override
	protected float getInertia() {
		return isInvulnerable() ? 0.73F : super.getInertia();
	}

	@Override
	public boolean isOnFire() {
		return false;
	}

	@Override
	protected void onHit(HitResult result) {
		if (!this.level.isClientSide && result instanceof EntityHitResult entityResult) {
			Entity entity = entityResult.getEntity();
			entity.hurt(DamageSource.indirectMagic(this, entity), SharedEntityData.getAttackDamage2() / 2.0F);
		}
		discard();
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		BlockState blockstate = this.level.getBlockState(result.getBlockPos());
		blockstate.onProjectileHit(this.level, blockstate, result, this);
		if (!this.level.isClientSide) {
			Entity entity = this.getOwner();
			if (!(entity instanceof Mob) || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
				BlockPos blockpos = result.getBlockPos().relative(result.getDirection());
				if (this.level.isEmptyBlock(blockpos)) {
					this.level.setBlockAndUpdate(blockpos, Blocks.COBWEB.defaultBlockState()); //TODO: WEB BLOCK
				}
			}

		}
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		return false;
	}
}