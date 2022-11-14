package de.deadorfd.api;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import static de.deadorfd.utils.Config.*;
import static de.deadorfd.api.CookiesAPI.*;

import de.deadorfd.utils.API;
import de.deadorfd.utils.Upgrades;
import static de.deadorfd.utils.config.CookieUpgrades.*;
import static de.deadorfd.utils.mysql.CookieUpgradeSQL.*;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.api
 * @Date 12.11.2022
 * @Time 02:56:24
 */
public class CookiesUpgradeAPI {

	public static int getUpgrade(Player player, Upgrades upgrade) {
		if (isMySQLActive()) return getUpgradeSQL(player, upgrade);
		return getUpgradeConfig(player, upgrade);
	}

	public static void buyUpgrade(Player player, Upgrades upgrade, InventoryClickEvent event) {
		if (isMySQLActive()) {
			int cost = getUpgradeCost(player, upgrade);
			if (!hasEnoughCookies(player, cost)) {
				player.sendMessage(getString("Prefix") + getMessage("NotEnoughCookies"));
				player.closeInventory();
				return;
			}
			addUpgradeSQL(player, 1, upgrade);
			removeCookies(player, cost);
			player.sendMessage(getString("Prefix")
					+ getMessage("BoughtUpgrade").replaceAll("%upgrade%", upgrade.getUpgradeName()));
			event.getInventory().setItem(event.getSlot(), API.getConfigUpgradeItem(player, upgrade));
			return;
		}
		int cost = getUpgradeCost(player, upgrade);
		if (!hasEnoughCookies(player, cost)) {
			player.sendMessage(getString("Prefix") + getMessage("NotEnoughCookies"));
			player.closeInventory();
			return;
		}
		addUpgradeConfig(player, 1, upgrade);
		removeCookies(player, cost);
		player.sendMessage(
				getString("Prefix") + getMessage("BoughtUpgrade").replaceAll("%upgrade%", upgrade.getUpgradeName()));
		event.getInventory().setItem(event.getSlot(), API.getConfigUpgradeItem(player, upgrade));
		return;
	}

	public static int getUpgradeCost(Player player, Upgrades upgrade) {
		if (getUpgrade(player, upgrade) == 0) return upgrade.getCost();
		return upgrade.getCost() * getUpgrade(player, upgrade);
	}

	public static void removeUpgrade(Player player, Upgrades upgrade, int amount) {
		if (isMySQLActive()) {
			removeUpgradeSQL(player, amount, upgrade);
			return;
		}
		removeUpgradeConfig(player, amount, upgrade);
		return;
	}

}
