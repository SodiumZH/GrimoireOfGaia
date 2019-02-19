package gaia.entity.monster;

import gaia.GaiaConfig;
import gaia.entity.EntityAttributes;
import gaia.entity.EntityMobHostileBase;
import gaia.entity.ai.EntityAIGaiaLeapAtTarget;
import gaia.init.GaiaEntities;
import gaia.init.GaiaItems;
import gaia.init.GaiaSounds;
import gaia.items.ItemShard;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityGaiaHarpy extends EntityMobHostileBase {
	private static final String MOB_TYPE_TAG = "MobType";
	private static final DataParameter<Integer> SKIN = EntityDataManager.createKey(EntityGaiaHarpy.class, DataSerializers.VARINT);

	private EntityAIGaiaLeapAtTarget aiGaiaLeapAtTarget = new EntityAIGaiaLeapAtTarget(this, 0.4F);
	private EntityAIAttackMelee aiMeleeAttack = new EntityGaiaHarpy.AILeapAttack(this);
	private EntityAIAvoidEntity<EntityPlayer> aiAvoid = new EntityAIAvoidEntity<>(this, EntityPlayer.class, 20.0F, EntityAttributes.ATTACK_SPEED_1, EntityAttributes.ATTACK_SPEED_3);

	private int switchHealth;
	private boolean isChild;

	public EntityGaiaHarpy(World worldIn) {
		super(GaiaEntities.HARPY, worldIn);

		experienceValue = EntityAttributes.EXPERIENCE_VALUE_1;
		stepHeight = 1.0F;

		switchHealth = 0;
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));

		tasks.addTask(3, new EntityAIWander(this, 1.0D));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(EntityAttributes.MAX_HEALTH_1);
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(EntityAttributes.FOLLOW_RANGE);
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityAttributes.MOVE_SPEED_1);
		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(EntityAttributes.ATTACK_DAMAGE_1);
		getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(EntityAttributes.RATE_ARMOR_1);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		return super.attackEntityFrom(source, Math.min(damage, EntityAttributes.BASE_DEFENSE_1));
	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
		super.knockBack(xRatio, zRatio, EntityAttributes.KNOCKBACK_1);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if (super.attackEntityAsMob(entityIn)) {
			if (entityIn instanceof EntityLivingBase) {
				byte byte0 = 0;

				if (world.getDifficulty() == EnumDifficulty.NORMAL) {
					byte0 = 5;
				} else if (world.getDifficulty() == EnumDifficulty.HARD) {
					byte0 = 10;
				}

				if (byte0 > 0) {
					((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, byte0 * 20, 0));
				}
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isAIDisabled() {
		return false;
	}

	@Override
	public void livingTick() {
		/* FLEE DATA */
		if ((getHealth() < EntityAttributes.MAX_HEALTH_1 * 0.25F) && (switchHealth == 0)) {
			switch (rand.nextInt(2)) {
			case 0:
				setAI((byte) 1);
				setEquipment((byte) 1);
				switchHealth = 1;
				break;
			case 1:
				switchHealth = 2;
				break;
			}
		}

		if ((getHealth() > EntityAttributes.MAX_HEALTH_1 * 0.25F) && (switchHealth == 1)) {
			setAI((byte) 0);
			setEquipment((byte) 0);

			switchHealth = 0;
		}
		/* FLEE DATA */

		if (!onGround && motionY < 0.0D) {
			motionY *= 0.8D;
		}

		super.livingTick();
	}

	private void setAI(byte id) {
		if (id == 0) {
			tasks.addTask(1, aiGaiaLeapAtTarget);
			tasks.addTask(2, aiMeleeAttack);
			tasks.removeTask(aiAvoid);
		}

		if (id == 1) {
			tasks.removeTask(aiGaiaLeapAtTarget);
			tasks.removeTask(aiMeleeAttack);
			tasks.addTask(2, aiAvoid);
		}
	}

	private void setEquipment(byte id) {
		if (id == 0) {
			setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.EGG));
		}

		if (id == 1) {
			setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.FEATHER));
		}

		if (id == 2) {
			setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.EGG));
		}
	}
	
	private void setBodyType(String id) {
		if (id == "none") {
			setItemStackToSlot(EntityEquipmentSlot.CHEST, ItemStack.EMPTY);
		}

		if (id == "baby") {
			setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.EGG));
		}
	}
	
	private void setCombatTask() {
		tasks.removeTask(aiMeleeAttack);
		tasks.removeTask(aiAvoid);
		
		setAI((byte) 0);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return GaiaSounds.HARPY_SAY;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return GaiaSounds.HARPY_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return GaiaSounds.HARPY_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, IBlockState blockIn) {
		playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		if (wasRecentlyHit) {
			if ((rand.nextInt(2) == 0 || rand.nextInt(1 + lootingModifier) > 0)) {
				entityDropItem(Items.FEATHER, 1);
			}

			// Nuggets/Fragments
			int dropNugget = rand.nextInt(3) + 1;

			for (int i = 0; i < dropNugget; ++i) {
				entityDropItem(Items.IRON_NUGGET, 1);
			}

			if (GaiaConfig.COMMON.additionalOre.get()) {
				int dropNuggetAlt = rand.nextInt(3) + 1;

				for (int i = 0; i < dropNuggetAlt; ++i) {
					ItemShard.dropNugget(this, 4);
				}
			}

			// Rare
			if ((rand.nextInt(EntityAttributes.RATE_RARE_DROP) == 0)) {
				entityDropItem(GaiaItems.BOX_IRON, 1);
			}
		}
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData entityLivingData, NBTTagCompound itemNbt) {
		IEntityLivingData ret = super.onInitialSpawn(difficulty, entityLivingData, itemNbt);

		if (!this.world.isRemote) {
            int i = MathHelper.floor(this.posX);
            int j = MathHelper.floor(this.posY);
            int k = MathHelper.floor(this.posZ);
            
            if (this.world.getBiome(new BlockPos(i, 0, k)).getTemperature(new BlockPos(i, j, k)) > 1.0F) {
    			setTextureType(2);
    		} else if (world.rand.nextInt(4) == 0) {
    			setTextureType(1);
    		}
		}

		// TEMP Method used instead of isChild
		setChild(true, 10);

		if (!isChild) {
			ItemStack weaponCustom = new ItemStack(GaiaItems.WEAPON_PROP_ENCHANTED, 1);
			weaponCustom.addEnchantment(Enchantments.KNOCKBACK, 2);
			setItemStackToSlot(EntityEquipmentSlot.MAINHAND, weaponCustom);
		}
		
		setCombatTask();

		return ret;
	}

	/* CHILD CODE */
	private void setChild(boolean isRandom, int chance) {
		if (isRandom) {
			if (world.rand.nextInt(chance) == 0) {
				setBodyType("baby");
			}
		} else {
			setBodyType("baby");
		}
	}

	@Override
	public float getEyeHeight() {
		float f;

		ItemStack itemstack = getItemStackFromSlot(EntityEquipmentSlot.CHEST);

		if (itemstack.isEmpty() || itemstack.getItem() != Items.EGG) {
			f = 1.74F;
		} else {
			f = 1.74F - 0.81F;
		}

		return f;
	}
	/* CHILD CODE */

	static class AILeapAttack extends EntityAIAttackMelee {

		AILeapAttack(EntityGaiaHarpy entity) {
			super(entity, EntityAttributes.ATTACK_SPEED_1, true);
		}

		@Override
		protected double getAttackReachSqr(EntityLivingBase attackTarget) {
			return 4.0D + attackTarget.width;
		}
	}

	/* ALTERNATE SKIN */
	@Override
	protected void registerData() {
		super.registerData();
		this.getDataManager().register(SKIN, 0);
	}

	public int getTextureType() {
		return dataManager.get(SKIN);
	}

	private void setTextureType(int par1) {
		dataManager.set(SKIN, par1);
	}

	@Override
	public void writeAdditional(NBTTagCompound compound) {
		super.writeAdditional(compound);
		compound.setByte(MOB_TYPE_TAG, (byte) getTextureType());
	}

	@Override
	public void readAdditional(NBTTagCompound compound) {
		super.readAdditional(compound);
		if (compound.hasKey(MOB_TYPE_TAG)) {
			byte b0 = compound.getByte(MOB_TYPE_TAG);
			setTextureType(b0);
		}
		
		setCombatTask();
	}
	/* ALTERNATE SKIN */

	/* IMMUNITIES */
	@Override
	public void fall(float distance, float damageMultiplier) {
	}
	/* IMMUNITIES */

	/* SPAWN CONDITIONS */
	@Override
	public int getMaxSpawnedInChunk() {
		return EntityAttributes.CHUNK_LIMIT_1;
	}

	@Override
	public boolean canSpawn(IWorld p_205020_1_, boolean p_205020_2_) {
		return posY > 60.0D && super.canSpawn(world, p_205020_2_);
	}
	/* SPAWN CONDITIONS */
}
