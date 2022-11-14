package de.deadorfd.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static de.deadorfd.api.CookiesUpgradeAPI.*;
import static de.deadorfd.utils.Config.*;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.utils
 * @Date 12.11.2022
 * @Time 01:29:10
 */
public class API {

	public static String prefix = getString("Prefix");
	public static String spigotlink = "https://www.spigotmc.org/resources/cookieclicker.75140/";

	public static void saveFile(YamlConfiguration cfg, File file) {
		try {
			cfg.save(file);
		} catch (IOException e) {}
	}

	public static String wrongCommand(String command) {
		return prefix + getMessage("WrongCommand").replace("%command%", command);
	}

	public static String noPermission() {
		return prefix + getMessage("NoPermission");
	}

	public static boolean hasPermission(Player player, String permission) {
		return player.hasPermission(getPermission("Admin")) || player.hasPermission(getPermission(permission));
	}

	public static String mustPlayer() {
		return getMessage("MustPlayer");
	}

	public static ItemStack getConfigItem(String itemconfig) {
		Material mat = Material.valueOf(getInventory("Material_" + itemconfig));
		if (mat == null) mat = Material.BARRIER;

		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();

		ArrayList lores = new ArrayList<>();
		String lore = getInventory("Lore_" + itemconfig);
		for (String newlore : lore.split("\n")) lores.add(newlore);

		meta.setLore(lores);
		meta.setDisplayName(getInventory("ItemName_" + itemconfig));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getConfigUpgradeItem(Player player, Upgrades upgrade) {
		Material mat = Material.valueOf(getInventory("Material_" + upgrade.getUpgradeName()));
		if (mat == null) mat = Material.BARRIER;

		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();

		ArrayList lores = new ArrayList<>();
		String lore = getInventory("Lore_" + upgrade.getUpgradeName())
				.replaceAll("%price%", getTransformed(getUpgradeCost(player, upgrade)))
				.replaceAll("%cps%", getCPSConfig(upgrade) + "");
		for (String newlore : lore.split("\n")) lores.add(newlore);

		meta.setLore(lores);
		String displayname = getInventory("ItemName_" + upgrade.getUpgradeName()).replaceAll("%upgrades%",
				getUpgrade(player, upgrade) + "");
		meta.setDisplayName(displayname);
		item.setItemMeta(meta);
		return item;
	}

	public static String getTransformed(int money) {
		String coins = String.valueOf(money);
		char[] coinsarray = coins.toCharArray();
		StringBuilder newCoin = new StringBuilder("");
		if (coinsarray.length == 4) {
			for (int i = 0; i < coinsarray.length; i++) {
				if (i == 1) {
					newCoin.append("." + coinsarray[i]);
				} else
					newCoin.append(coinsarray[i]);
			}
		} else if (coinsarray.length == 5) {
			for (int i = 0; i < coinsarray.length; i++) {
				if (i == 2) {
					newCoin.append("." + coinsarray[i]);
				} else
					newCoin.append(coinsarray[i]);
			}
		} else if (coinsarray.length == 6) {
			for (int i = 0; i < coinsarray.length; i++) {
				if (i == 3) {
					newCoin.append("." + coinsarray[i]);
				} else
					newCoin.append(coinsarray[i]);
			}
		} else if (coinsarray.length == 7) {
			for (int i = 0; i < coinsarray.length; i++) {
				if (i == 1) {
					newCoin.append("." + coinsarray[i]);
				} else if (i == 4) {
					newCoin.append("." + coinsarray[i]);
				} else
					newCoin.append(coinsarray[i]);
			}
		} else if (coinsarray.length == 8) {
			for (int i = 0; i < coinsarray.length; i++) {
				if (i == 2) {
					newCoin.append("." + coinsarray[i]);
				} else if (i == 5) {
					newCoin.append("." + coinsarray[i]);
				} else
					newCoin.append(coinsarray[i]);
			}
		} else if (coinsarray.length == 9) {
			for (int i = 0; i < coinsarray.length; i++) {
				if (i == 3) {
					newCoin.append("." + coinsarray[i]);
				} else if (i == 6) {
					newCoin.append("." + coinsarray[i]);
				} else
					newCoin.append(coinsarray[i]);
			}
		} else if (coinsarray.length == 10) {
			for (int i = 0; i < coinsarray.length; i++) {
				if (i == 1) {
					newCoin.append("." + coinsarray[i]);
				} else if (i == 4) {
					newCoin.append("." + coinsarray[i]);
				} else if (i == 7) {
					newCoin.append("." + coinsarray[i]);
				} else
					newCoin.append(coinsarray[i]);
			}
		} else if (coinsarray.length == 3) {
			for (int i = 0; i < coinsarray.length; i++) newCoin.append(coinsarray[i]);
		} else if (coinsarray.length == 2) {
			for (int i = 0; i < coinsarray.length; i++) newCoin.append(coinsarray[i]);
		} else if (coinsarray.length == 1) {
			for (int i = 0; i < coinsarray.length; i++) newCoin.append(coinsarray[i]);
		}
		return newCoin.toString();
	}

}
