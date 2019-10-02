package gaia.client.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelGaiaSelkie<T extends MobEntity> extends ModelGaia<T> {
	private RendererModel head;
	private RendererModel headeyes;
	private RendererModel headaccessory;
	private RendererModel neck;
	private RendererModel bodytop;
	private RendererModel bodymiddle;
	private RendererModel bodymiddlebutton;
	private RendererModel bodybottom;
	private RendererModel rightchest;
	private RendererModel leftchest;
	private RendererModel rightarm;
	private RendererModel leftarm;
	private RendererModel hair1;
	private RendererModel hair2;
	private RendererModel righthatear;
	private RendererModel lefthatear;
	private RendererModel chestpiece;
	private RendererModel waist;
	private RendererModel fin1;
	private RendererModel fin2;
	private RendererModel fin3;
	private RendererModel fin4;
	private RendererModel fintail;

	private static final double CYCLES_PER_BLOCK = 1.0D;
	private float[][] undulationCycle = new float[][]
			{
					{-5F, -10F, -15F, -20F, -25F, -30F},
					{-5F, -7F, -9F, -11F, -13F, -15F},
					{0F, 0F, 0F, 0F, 0F, 0F},
					{5F, 10F, 15F, 20F, 25F, 30F},
					{5F, 7F, 9F, 11F, 13F, 15F},
					{0F, 0F, 0F, 0F, 0F, 0F},
			};

	public ModelGaiaSelkie() {
		textureWidth = 128;
		textureHeight = 64;

		head = new RendererModel(this, 0, 0);
		head.addBox(-3F, -6F, -3F, 6, 6, 6);
		head.setRotationPoint(0F, 1F, 0F);
		head.setTextureSize(64, 32);
		setRotation(head, 0F, 0F, 0F);
		headeyes = new RendererModel(this, 24, 0);
		headeyes.addBox(-3F, -6F, -3.1F, 6, 6, 0);
		headeyes.setRotationPoint(0F, 1F, 0F);
		headeyes.setTextureSize(64, 32);
		setRotation(headeyes, 0F, 0F, 0F);
		headaccessory = new RendererModel(this, 36, 0);
		headaccessory.addBox(-3.5F, -6.5F, -3.5F, 7, 7, 7);
		headaccessory.setRotationPoint(0F, 1F, 0F);
		headaccessory.setTextureSize(64, 32);
		setRotation(headaccessory, 0F, 0F, 0F);
		neck = new RendererModel(this, 0, 12);
		neck.addBox(-1F, -1F, -1F, 2, 2, 2);
		neck.setRotationPoint(0F, 1F, 0F);
		neck.setTextureSize(64, 32);
		setRotation(neck, 0F, 0F, 0F);
		bodytop = new RendererModel(this, 0, 16);
		bodytop.addBox(-2.5F, 0F, -1.5F, 5, 6, 3);
		bodytop.setRotationPoint(0F, 1F, 0F);
		bodytop.setTextureSize(64, 32);
		setRotation(bodytop, -0.0872665F, 0F, 0F);
		bodymiddle = new RendererModel(this, 0, 25);
		bodymiddle.addBox(-2F, 5.5F, -1.5F, 4, 3, 2);
		bodymiddle.setRotationPoint(0F, 1F, 0F);
		bodymiddle.setTextureSize(64, 32);
		setRotation(bodymiddle, 0F, 0F, 0F);
		bodymiddlebutton = new RendererModel(this, 0, 25);
		bodymiddlebutton.addBox(-0.5F, 6F, -1.6F, 1, 2, 0);
		bodymiddlebutton.setRotationPoint(0F, 1F, 0F);
		bodymiddlebutton.setTextureSize(64, 32);
		setRotation(bodymiddlebutton, 0F, 0F, 0F);
		bodybottom = new RendererModel(this, 0, 30);
		bodybottom.addBox(-3F, 8F, -2.5F, 6, 3, 3);
		bodybottom.setRotationPoint(0F, 1F, 0F);
		bodybottom.setTextureSize(64, 32);
		setRotation(bodybottom, 0.0872665F, 0F, 0F);
		rightchest = new RendererModel(this, 0, 36);
		rightchest.addBox(-1F, -1F, -1F, 2, 2, 2);
		rightchest.setRotationPoint(-1.3F, 3F, -1.5F);
		rightchest.setTextureSize(64, 32);
		setRotation(rightchest, 0.7853982F, 0.1745329F, 0.0872665F);
		leftchest = new RendererModel(this, 0, 36);
		leftchest.mirror = true;
		leftchest.addBox(-1F, -1F, -1F, 2, 2, 2);
		leftchest.setRotationPoint(1.3F, 3F, -1.5F);
		leftchest.setTextureSize(64, 32);
		setRotation(leftchest, 0.7853982F, -0.1745329F, -0.0872665F);
		rightarm = new RendererModel(this, 16, 12);
		rightarm.addBox(-2F, -1F, -1F, 2, 12, 2);
		rightarm.setRotationPoint(-2.5F, 2.5F, 0F);
		rightarm.setTextureSize(64, 32);
		setRotation(rightarm, 0F, 0F, 0.1745329F);
		leftarm = new RendererModel(this, 16, 12);
		leftarm.addBox(0F, -1F, -1F, 2, 12, 2);
		leftarm.setRotationPoint(2.5F, 2.5F, 0F);
		leftarm.setTextureSize(64, 32);
		setRotation(leftarm, 0F, 0F, -0.1745329F);
		hair1 = new RendererModel(this, 36, 14);
		hair1.addBox(-4F, -6F, 1F, 8, 8, 3);
		hair1.setRotationPoint(0F, 1F, 0F);
		hair1.setTextureSize(64, 32);
		setRotation(hair1, 0F, 0F, 0F);
		hair2 = new RendererModel(this, 36, 25);
		hair2.addBox(-4.5F, -1F, 1.5F, 9, 9, 3);
		hair2.setRotationPoint(0F, 1F, 0F);
		hair2.setTextureSize(64, 32);
		setRotation(hair2, 0F, 0F, 0F);
		RendererModel hat1 = new RendererModel(this, 64, 0);
		hat1.addBox(-4F, -7.5F, -5F, 8, 3, 8);
		hat1.setRotationPoint(0F, 1F, 0F);
		hat1.setTextureSize(64, 32);
		setRotation(hat1, -0.1745329F, 0F, 0F);
		RendererModel hat2 = new RendererModel(this, 64, 11);
		hat2.addBox(-3F, -8.5F, -4F, 6, 1, 6);
		hat2.setRotationPoint(0F, 1F, 0F);
		hat2.setTextureSize(64, 32);
		setRotation(hat2, -0.1745329F, 0F, 0F);
		righthatear = new RendererModel(this, 64, 10);
		righthatear.addBox(-5F, -6F, -4F, 0, 18, 8);
		righthatear.setRotationPoint(0F, 1F, 0F);
		righthatear.setTextureSize(64, 32);
		setRotation(righthatear, 0F, 0F, 0.1745329F);
		lefthatear = new RendererModel(this, 64, 10);
		lefthatear.addBox(5F, -6F, -4F, 0, 18, 8);
		lefthatear.setRotationPoint(0F, 1F, 0F);
		lefthatear.setTextureSize(64, 32);
		setRotation(lefthatear, 0F, 0F, -0.1745329F);
		chestpiece = new RendererModel(this, 64, 36);
		chestpiece.addBox(-4F, -2F, -1F, 8, 6, 2);
		chestpiece.setRotationPoint(0F, 1F, 0F);
		chestpiece.setTextureSize(64, 32);
		setRotation(chestpiece, -0.7853982F, 0F, 0F);
		waist = new RendererModel(this, 96, 0);
		waist.addBox(-4F, 7.5F, -3F, 8, 3, 4);
		waist.setRotationPoint(0F, 1F, 0F);
		waist.setTextureSize(64, 32);
		setRotation(waist, 0.0872665F, undulationCycle[0][0], 0F);
		RendererModel zip = new RendererModel(this, 96, 7);
		zip.addBox(-1F, 0F, -3.5F, 2, 3, 1);
		zip.setRotationPoint(0F, 11F, 0F);
		zip.setTextureSize(64, 32);
		setRotation(zip, -0.6108652F, 0, 0F);
		fin1 = new RendererModel(this, 96, 11);
		fin1.addBox(-3.5F, -1F, -3F, 7, 6, 6);
		fin1.setRotationPoint(0F, 11F, 0F);
		fin1.setTextureSize(64, 32);
		setRotation(fin1, -0.2617994F, undulationCycle[0][1], 0F);
		fin2 = new RendererModel(this, 96, 23);
		fin2.addBox(-3F, 4F, -3.5F, 6, 5, 5);
		fin2.setRotationPoint(0F, 11F, 0F);
		fin2.setTextureSize(64, 32);
		setRotation(fin2, -0.0872665F, undulationCycle[0][2], 0F);
		fin3 = new RendererModel(this, 96, 33);
		fin3.addBox(-2.5F, 7F, -6F, 5, 4, 4);
		fin3.setRotationPoint(0F, 11F, 0F);
		fin3.setTextureSize(64, 32);
		setRotation(fin3, 0.2617994F, undulationCycle[0][3], 0F);
		fin4 = new RendererModel(this, 96, 41);
		fin4.addBox(-2F, 8F, -9F, 4, 3, 3);
		fin4.setRotationPoint(0F, 11F, 0F);
		fin4.setTextureSize(64, 32);
		setRotation(fin4, 0.6108652F, undulationCycle[0][4], 0F);
		fintail = new RendererModel(this, 96, 47);
		fintail.addBox(-4F, 12F, 1F, 8, 1, 4);
		fintail.setRotationPoint(0F, 11F, 0F);
		fintail.setTextureSize(64, 32);
		setRotation(fintail, -0.0872665F, undulationCycle[0][5], 0F);

		convertToChild(head, hat1);
		convertToChild(head, hat2);
		convertToChild(waist, zip);
		convertToChild(waist, fin1);
		convertToChild(waist, fin2);
		convertToChild(waist, fin3);
		convertToChild(waist, fin4);
		convertToChild(waist, fintail);
	}

	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		head.render(scale);
		headaccessory.render(scale);
		neck.render(scale);
		bodytop.render(scale);
		bodymiddle.render(scale);
		bodymiddlebutton.render(scale);
		bodybottom.render(scale);
		rightchest.render(scale);
		leftchest.render(scale);
		rightarm.render(scale);
		leftarm.render(scale);
		hair1.render(scale);
		hair2.render(scale);
		righthatear.render(scale);
		lefthatear.render(scale);
		chestpiece.render(scale);
		waist.render(scale);

		if (entityIn.ticksExisted % 60 == 0 && limbSwingAmount <= 0.1F) {
			headeyes.render(scale);
		}
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		// head
		head.rotateAngleY = netHeadYaw / 57.295776F;
		head.rotateAngleX = headPitch / 57.295776F;
		headeyes.rotateAngleY = head.rotateAngleY;
		headeyes.rotateAngleX = head.rotateAngleX;
		headaccessory.rotateAngleY = head.rotateAngleY;
		headaccessory.rotateAngleX = head.rotateAngleX;
		righthatear.rotateAngleY = head.rotateAngleY;
		lefthatear.rotateAngleY = head.rotateAngleY;
		hair1.rotateAngleY = head.rotateAngleY;
		hair2.rotateAngleY = (head.rotateAngleY) * 0.75F;

		// arms
		rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F;
		leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F;

		rightarm.rotateAngleZ = 0.0F;
		leftarm.rotateAngleZ = 0.0F;

		if (entityIn.isAggressive() && (entityIn.getHeldItemMainhand().getItem() == Items.BOW)) {
			holdingBow(ageInTicks);
		} else if (swingProgress > -9990.0F) {
			holdingMelee();
		}

		rightarm.rotateAngleZ += (MathHelper.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) + 0.1745329F;
		rightarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.025F;
		leftarm.rotateAngleZ -= (MathHelper.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) + 0.1745329F;
		leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.025F;

		// legs
		updateDistanceMovedTotal(entityIn);
		int cycleIndex = (int) ((getDistanceMovedTotal() * CYCLES_PER_BLOCK) % undulationCycle.length);

		waist.rotateAngleY = degToRad(undulationCycle[cycleIndex][0]);
		fin1.rotateAngleY = degToRad(undulationCycle[cycleIndex][1]);
		fin2.rotateAngleY = degToRad(undulationCycle[cycleIndex][2]);
		fin3.rotateAngleY = degToRad(undulationCycle[cycleIndex][3]);
		fin4.rotateAngleY = degToRad(undulationCycle[cycleIndex][4]);
		fintail.rotateAngleY = degToRad(undulationCycle[cycleIndex][5]);
	}

	private void holdingBow(float ageInTicks) {
		float f = MathHelper.sin(swingProgress * (float) Math.PI);
		float f1 = MathHelper.sin((1.0F - (1.0F - swingProgress) * (1.0F - swingProgress)) * (float) Math.PI);

		rightarm.rotateAngleZ = -0.3F;
		leftarm.rotateAngleZ = 0.3F;
		rightarm.rotateAngleY = -(0.1F - f * 0.6F);
		leftarm.rotateAngleY = 0.3F - f * 0.6F;
		rightarm.rotateAngleX = -((float) Math.PI / 2F);
		leftarm.rotateAngleX = -((float) Math.PI / 2F);
		rightarm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
		leftarm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
		rightarm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		leftarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		rightarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	}

	public void holdingMelee() {
		float f6;
		float f7;

		f6 = 1.0F - swingProgress;
		f6 *= f6;
		f6 *= f6;
		f6 = 1.0F - f6;
		f7 = MathHelper.sin(f6 * (float) Math.PI);
		float f8 = MathHelper.sin(swingProgress * (float) Math.PI) * -(head.rotateAngleX - 0.7F) * 0.75F;

		rightarm.rotateAngleX = (float) ((double) rightarm.rotateAngleX - ((double) f7 * 1.2D + (double) f8));
		rightarm.rotateAngleX += (bodytop.rotateAngleY * 2.0F);
		rightarm.rotateAngleZ = (MathHelper.sin(swingProgress * (float) Math.PI) * -0.4F);
	}

	public RendererModel getRightArm() {
		return rightarm;
	}

	public RendererModel getLeftArm() {
		return leftarm;
	}
}