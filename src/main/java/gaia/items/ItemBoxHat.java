package gaia.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import gaia.init.GaiaItems;
import gaia.init.GaiaSounds;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBoxHat extends ItemGaiaLootable {
	public ItemBoxHat() {
		super("box_hat");
		setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("text.grimoireofgaia.RightClickUse"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		final ItemStack stack = player.getHeldItem(handIn);

		player.playSound(GaiaSounds.BOX_OPEN_2, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		Random random = new Random();
		int i = random.nextInt(5);
		switch (i) {
		case 0:
			return loot(GaiaItems.ACCESSORY_HEADGEAR);
		case 1:
			return loot(GaiaItems.ACCESSORY_HEADGEAR_MOB);
		case 2:
			return loot(GaiaItems.ACCESSORY_HEADGEAR_BOLT);
		case 3:
			return loot(GaiaItems.ACCESSORY_HEADGEAR_ARROW);
		case 4:
			return loot(GaiaItems.ACCESSORY_HEADGEAR_DOLL);
		case 5:
			return loot(GaiaItems.ACCESSORY_HEADGEAR_EARS_ELF);
		default:
			return new ActionResult<>(EnumActionResult.SUCCESS, stack);
		}
	}
}
