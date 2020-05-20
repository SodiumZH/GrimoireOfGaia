package gaia.item.loot;

import gaia.init.GaiaLootTables;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;

public class ItemBoxOverworld extends ItemBoxBase {
	public ItemBoxOverworld(Item.Properties builder) {
		super(builder);
	}

	@Override
	public Rarity getRarity(ItemStack stack) {
		return Rarity.RARE;
	}

	@Override
	public ResourceLocation getBoxLoot() {
		return GaiaLootTables.BOXES_OVERWORLD;
	}
}