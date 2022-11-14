package de.deadorfd.utils;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static de.deadorfd.utils.Config.*;
import static de.deadorfd.utils.API.*;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.utils
 * @Date 12.11.2022
 * @Time 23:42:22
 */
public class Inventorys {

	public static void openMainInventory(Player player) {
		Inventory inv = Bukkit.createInventory(null, 9 * 3, getInventory("MainInventoryName"));
		if (getBoolean("SetGlassInInventory")) {
			for (int i = 0; i < 27; i++)
				inv.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.WHITE.getData()));
		}
		inv.setItem(15, getConfigItem("Clicker"));
		inv.setItem(13, getConfigItem("Upgrades"));
		inv.setItem(11, getConfigItem("Shop"));
		player.openInventory(inv);
	}

	public static void openUpgradeInventory(Player player) {
		Inventory inv = Bukkit.createInventory(null, 9 * 3, getInventory("UpgradeInventoryName"));
		if (getBoolean("SetGlassInInventory")) {
			for (int i = 0; i < 27; i++)
				inv.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.WHITE.getData()));
		}
		inv.setItem(11, getConfigUpgradeItem(player, Upgrades.CURSOR));
		inv.setItem(13, getConfigUpgradeItem(player, Upgrades.GRANDMA));
		inv.setItem(15, getConfigUpgradeItem(player, Upgrades.FARM));
		player.openInventory(inv);
	}

}
