package gaia.model;

import gaia.entity.monster.EntityGaiaGoblinFeral;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Direct copy of ModelGaiaGoblin with the only difference being that under 'setRotationAngles' entity makes reference to EntityGaiaGoblinFeral.
 * 
 * @see ModelGaiaGoblin
 */
@OnlyIn(Dist.CLIENT)
public class ModelGaiaGoblinFeral extends ModelGaia {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;

	public ModelGaiaGoblinFeral() {
		textureWidth = 128;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 8F, 0F);
		head.setTextureSize(64, 32);
		setRotation(head, 0F, 0F, 0F);
		ModelRenderer headaccessory = new ModelRenderer(this, 64, 0);
		headaccessory.addBox(-4.5F, -8.5F, -4.5F, 9, 9, 9);
		headaccessory.setRotationPoint(0F, 8F, 0F);
		headaccessory.setTextureSize(64, 32);
		setRotation(headaccessory, 0F, 0F, 0F);
		ModelRenderer rightear = new ModelRenderer(this, 64, 10);
		rightear.addBox(-5.5F, -7F, 0F, 0, 6, 8);
		rightear.setRotationPoint(0F, 8F, 0F);
		rightear.setTextureSize(64, 32);
		setRotation(rightear, 0F, -0.7853982F, 0F);
		ModelRenderer leftear = new ModelRenderer(this, 64, 10);
		leftear.mirror = true;
		leftear.addBox(5.5F, -7F, 0F, 0, 6, 8);
		leftear.setRotationPoint(0F, 8F, 0F);
		leftear.setTextureSize(64, 32);
		setRotation(leftear, 0F, 0.7853982F, 0F);
		ModelRenderer rightearback = new ModelRenderer(this, 80, 10);
		rightearback.addBox(-5.4F, -7F, 0F, 0, 6, 8);
		rightearback.setRotationPoint(0F, 8F, 0F);
		rightearback.setTextureSize(64, 32);
		setRotation(rightearback, 0F, -0.7853982F, 0F);
		ModelRenderer leftearback = new ModelRenderer(this, 80, 10);
		leftearback.mirror = true;
		leftearback.addBox(5.4F, -7F, 0F, 0, 6, 8);
		leftearback.setRotationPoint(0F, 8F, 0F);
		leftearback.setTextureSize(64, 32);
		setRotation(leftearback, 0F, 0.7853982F, 0F);
		ModelRenderer headnose = new ModelRenderer(this, 64, 24);
		headnose.addBox(-1F, -3F, -7F, 2, 3, 3);
		headnose.setRotationPoint(0F, 8F, 0F);
		headnose.setTextureSize(64, 32);
		setRotation(headnose, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 16);
		body.addBox(-4F, 0F, -2F, 8, 8, 4);
		body.setRotationPoint(0F, 8F, 0F);
		body.setTextureSize(64, 32);
		setRotation(body, 0F, 0F, 0F);
		ModelRenderer rightarmpauldron = new ModelRenderer(this, 100, 0);
		rightarmpauldron.addBox(-3.5F, -2F, -2F, 4, 4, 4);
		rightarmpauldron.setRotationPoint(-4F, 10F, 0F);
		rightarmpauldron.setTextureSize(64, 32);
		setRotation(rightarmpauldron, 0F, 0F, 0F);
		ModelRenderer leftarmpauldron = new ModelRenderer(this, 100, 0);
		leftarmpauldron.mirror = true;
		leftarmpauldron.addBox(-0.5F, -2F, -2F, 4, 4, 4);
		leftarmpauldron.setRotationPoint(4F, 10F, 0F);
		leftarmpauldron.setTextureSize(64, 32);
		setRotation(leftarmpauldron, 0F, 0F, 0F);
		rightarm = new ModelRenderer(this, 24, 16);
		rightarm.addBox(-3F, -1.5F, -1.5F, 3, 8, 3);
		rightarm.setRotationPoint(-4F, 10F, 0F);
		rightarm.setTextureSize(64, 32);
		setRotation(rightarm, 0F, 0F, 0F);
		ModelRenderer rightarmgauntlet = new ModelRenderer(this, 100, 8);
		rightarmgauntlet.addBox(-3.5F, 3F, -2F, 4, 4, 4);
		rightarmgauntlet.setRotationPoint(-4F, 10F, 0F);
		rightarmgauntlet.setTextureSize(64, 32);
		setRotation(rightarmgauntlet, 0F, 0F, 0F);
		ModelRenderer leftarmgauntlet = new ModelRenderer(this, 100, 8);
		leftarmgauntlet.mirror = true;
		leftarmgauntlet.addBox(-0.5F, 3F, -2F, 4, 4, 4);
		leftarmgauntlet.setRotationPoint(4F, 10F, 0F);
		leftarmgauntlet.setTextureSize(64, 32);
		setRotation(leftarmgauntlet, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 24, 16);
		leftarm.mirror = true;
		leftarm.addBox(0F, -1.5F, -1.5F, 3, 8, 3);
		leftarm.setRotationPoint(4F, 10F, 0F);
		leftarm.setTextureSize(64, 32);
		setRotation(leftarm, 0F, 0F, 0F);
		ModelRenderer rightlegupper = new ModelRenderer(this, 100, 16);
		rightlegupper.addBox(-2F, 0F, -2F, 4, 4, 4);
		rightlegupper.setRotationPoint(-2F, 16F, 0F);
		rightlegupper.setTextureSize(64, 32);
		setRotation(rightlegupper, 0F, 0F, 0F);
		ModelRenderer leftlegupper = new ModelRenderer(this, 100, 16);
		leftlegupper.mirror = true;
		leftlegupper.addBox(-2F, 0F, -2F, 4, 4, 4);
		leftlegupper.setRotationPoint(2F, 16F, 0F);
		leftlegupper.setTextureSize(64, 32);
		setRotation(leftlegupper, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 36, 16);
		rightleg.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
		rightleg.setRotationPoint(-2F, 16F, 0F);
		rightleg.setTextureSize(64, 32);
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 36, 16);
		leftleg.mirror = true;
		leftleg.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
		leftleg.setRotationPoint(2F, 16F, 0F);
		leftleg.setTextureSize(64, 32);
		setRotation(leftleg, 0F, 0F, 0F);
		ModelRenderer rightlegboot = new ModelRenderer(this, 100, 24);
		rightlegboot.addBox(-2F, 4F, -2F, 4, 4, 4);
		rightlegboot.setRotationPoint(-2F, 16F, 0F);
		rightlegboot.setTextureSize(64, 32);
		setRotation(rightlegboot, 0F, 0F, 0F);
		ModelRenderer leftlegboot = new ModelRenderer(this, 100, 24);
		leftlegboot.mirror = true;
		leftlegboot.addBox(-2F, 4F, -2F, 4, 4, 4);
		leftlegboot.setRotationPoint(2F, 16F, 0F);
		leftlegboot.setTextureSize(64, 32);
		setRotation(leftlegboot, 0F, 0F, 0F);

		convertToChild(head, headaccessory);
		convertToChild(head, rightear);
		convertToChild(head, leftear);
		convertToChild(head, rightearback);
		convertToChild(head, leftearback);
		convertToChild(head, headnose);
		convertToChild(rightarm, rightarmpauldron);
		convertToChild(leftarm, leftarmpauldron);
		convertToChild(rightarm, rightarmgauntlet);
		convertToChild(leftarm, leftarmgauntlet);
		convertToChild(rightleg, rightlegupper);
		convertToChild(leftleg, leftlegupper);
		convertToChild(rightleg, rightlegboot);
		convertToChild(leftleg, leftlegboot);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		head.render(scale);
		body.render(scale);
		rightarm.render(scale);
		leftarm.render(scale);
		rightleg.render(scale);
		leftleg.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		// head
		head.rotateAngleY = netHeadYaw / 57.295776F;
		head.rotateAngleX = headPitch / 57.295776F;

		// arms
		rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F;
		leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F;

		rightarm.rotateAngleZ = 0.0F;
		leftarm.rotateAngleZ = 0.0F;

		ItemStack itemstack = ((EntityLivingBase) entityIn).getHeldItemMainhand();
		EntityGaiaGoblinFeral entity = (EntityGaiaGoblinFeral) entityIn;

		if (entity.isSwingingArms() && (itemstack.getItem() == Items.BOW)) {
			holdingBow(ageInTicks);
		} else if (swingProgress > -9990.0F) {
			holdingMelee();
		}

		rightarm.rotateAngleZ += (MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F) + 0.0872665F;
		rightarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		leftarm.rotateAngleZ -= (MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F) + 0.0872665F;
		leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

		// legs
		rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount;
		leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount;
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
		rightarm.rotateAngleX += (body.rotateAngleY * 2.0F);
		rightarm.rotateAngleZ = (MathHelper.sin(swingProgress * (float) Math.PI) * -0.4F);
	}

	public ModelRenderer getRightArm() {
		return rightarm;
	}

	public ModelRenderer getLeftArm() {
		return leftarm;
	}
}
