package gaia.entity.prop;

import gaia.entity.AbstractMobPropEntity;
import gaia.init.GaiaBlocks;
import gaia.init.GaiaEntities;
import gaia.init.GaiaLootTables;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class GaiaPropCampfireEntity extends AbstractMobPropEntity {
	private static final String ROTATION_TAG = "Rotation";

	private static final DataParameter<Integer> ROTATION = EntityDataManager.createKey(GaiaPropCampfireEntity.class, DataSerializers.VARINT);

	public GaiaPropCampfireEntity(EntityType<? extends GaiaPropCampfireEntity> type, World worldIn) {
		super(type, worldIn);
		experienceValue = 0;
		prevRenderYawOffset = 180.0F;
		renderYawOffset = 180.0F;
	}

	public GaiaPropCampfireEntity(World worldIn) {
		this(GaiaEntities.CAMPFIRE, worldIn);
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData livingdata, @Nullable CompoundNBT itemNbt) {
		renderYawOffset = 180.0F;
		prevRenderYawOffset = 180.0F;
		rotationYaw = 180.0F;
		prevRotationYaw = 180.0F;
		rotationYawHead = 180.0F;
		prevRotationYawHead = 180.0F;

		if (canSpawn()) {
			for (int i = 0; i < 2; i++) {
//				EntityGaiaDwarf dwarf; TODO: Reimplement EntityGaiaDwarf spawning
//
//				if (i == 0) {
//					dwarf = new EntityGaiaDwarf(world);
//					dwarf.classID = 2;
//					dwarf.randomClass = false;
//					dwarf.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
//					dwarf.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(dwarf)), null, null);
//					world.spawnEntity(dwarf);
//				} else {
//					dwarf = new EntityGaiaDwarf(world);
//					dwarf.classID = rand.nextInt(2);
//					dwarf.randomClass = false;
//					dwarf.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
//					dwarf.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(dwarf)), null, null);
//					world.spawnEntity(dwarf);
//				}
			}
		}

		switch (rand.nextInt(2)) {
			case 0:
				setRotation(0);
				break;
			case 1:
				setRotation(1);
				break;
		}

		if (!world.isRemote) {
			int i_block = MathHelper.floor(posX);
			int j_block = MathHelper.floor(posY);
			int k_block = MathHelper.floor(posZ);

			for (int l = 0; l < 4; ++l) {
				i_block = MathHelper.floor(posX + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
				j_block = MathHelper.floor(posY);
				k_block = MathHelper.floor(posZ + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
				BlockPos blockpos = new BlockPos(i_block, j_block, k_block);

				if (world.getBlockState(blockpos).getMaterial() == Material.AIR) {
					world.setBlockState(blockpos, GaiaBlocks.FIRE_CAMP.getDefaultState());
				}
			}
		}

		return super.onInitialSpawn(worldIn, difficulty, reason, livingdata, itemNbt);
	}

	public boolean canSpawn() {
		int i = MathHelper.floor(posX);
		int j = MathHelper.floor(posY + 2.0D);
		int k = MathHelper.floor(posZ);
		BlockState iblockstate = world.getBlockState(new BlockPos(i, j, k));

		int i1 = MathHelper.floor(posX);
		int j1 = MathHelper.floor(posY + 3.0D);
		int k1 = MathHelper.floor(posZ);
		BlockState iblockstate_j = world.getBlockState(new BlockPos(i1, j1, k1));

		if (iblockstate.getMaterial() == Material.AIR && iblockstate_j.getMaterial() == Material.AIR) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0F);
	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
	}

	@Override
	public boolean isAIDisabled() {
		return false;
	}

	@Override
	public void livingTick() {
		if (getHealth() <= 0.0F) {
			if (!world.isRemote) {
				int i = MathHelper.floor(posX);
				int j = MathHelper.floor(posY);
				int k = MathHelper.floor(posZ);

				for (int l = 0; l < 4; ++l) {
					i = MathHelper.floor(posX + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
					j = MathHelper.floor(posY);
					k = MathHelper.floor(posZ + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
					BlockPos blockpos = new BlockPos(i, j, k);

					world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
				}
			}
		} else {
			super.livingTick();
		}
	}

	@Override
	protected void registerData() {
		super.registerData();
		getDataManager().register(ROTATION, 0);
	}

	public int getRotation() {
		return dataManager.get(ROTATION);
	}

	private void setRotation(int par1) {
		dataManager.set(ROTATION, par1);
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putByte(ROTATION_TAG, (byte) getRotation());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains(ROTATION_TAG)) {
			byte b0 = compound.getByte(ROTATION_TAG);
			setRotation(b0);
		}
	}

	protected void playParticleEffect() {
		IParticleData enumparticletypes = ParticleTypes.LARGE_SMOKE;

		for (int i = 0; i < 7; ++i) {
			double d0 = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			world.addParticle(enumparticletypes, posX + (double) (rand.nextFloat() * getWidth() * 2.0F) - (double) getWidth(), posY + 0.5D + (double) (rand.nextFloat() * getHeight()), posZ + (double) (rand.nextFloat() * getWidth() * 2.0F) - (double) getWidth(), d0, d1, d2);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 6) {
			playParticleEffect();
		} else {
			super.handleStatusUpdate(id);
		}
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_WOOD_BREAK;
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		return GaiaLootTables.ENTITIES_GAIA_CAMPFIRE;
	}

	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		if (recentlyHitIn) {
			int drop = rand.nextInt(3 + looting);

			switch (rand.nextInt(2)) {
				case 0:
					world.setEntityState(this, (byte) 6);
					for (int i = 0; i < drop; ++i) {
						entityDropItem(Items.COOKED_PORKCHOP, 1);
					}
					break;
				case 1:
					world.setEntityState(this, (byte) 6);
					for (int i = 0; i < drop; ++i) {
						entityDropItem(new ItemStack(Items.CHARCOAL, 1), 0.0F);
					}
					break;
			}
		}
	}

	@Override
	protected void onDeathUpdate() {
		remove();
	}

	/* IMMUNITIES */
	@Override
	public boolean isPotionApplicable(EffectInstance potioneffectIn) {
		return false;
	}
	/* IMMUNITIES */

	@Override
	protected void collideWithEntity(Entity entityIn) {
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	private boolean isValidLightLevel() {
		BlockPos blockpos = new BlockPos(this.posX, this.getBoundingBox().minY, this.posZ);
		if (this.getBrightness() > 0.5F && this.world.canBlockSeeSky(blockpos)) {
			return true;
		} else {
			return false;
		}
	}

	/* SPAWN CONDITIONS */
	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	public boolean canSpawn(IWorld worldIn, SpawnReason value) {
		return posY < 60.0D && posY > 32.0D && world.getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel() && super.canSpawn(worldIn, value);
	}
	/* SPAWN CONDITIONS */

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox() {
		return isAlive() ? getBoundingBox() : null;
	}

	@Override
	public int getVerticalFaceSpeed() {
		return 180;
	}

	@Override
	public int getHorizontalFaceSpeed() {
		return 180;
	}

	@Override
	public void applyEntityCollision(Entity entityIn) {
	}

	@Override
	public float getCollisionBorderSize() {
		return 0.0F;
	}
}