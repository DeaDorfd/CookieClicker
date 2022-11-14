package de.deadorfd.api;

import org.bukkit.entity.Player;

import static de.deadorfd.utils.API.*;
import static de.deadorfd.utils.Upgrades.*;
import static de.deadorfd.utils.Config.*;
import static de.deadorfd.utils.config.Cookies.*;
import static de.deadorfd.utils.config.CookieUpgrades.*;
import static de.deadorfd.utils.mysql.CookiesSQL.*;
import static de.deadorfd.utils.mysql.CookieUpgradeSQL.*;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.api
 * @Date 12.11.2022
 * @Time 02:56:07
 */
public class CookiesAPI {

	public static int getCookies(Player player) {
		if (isMySQLActive()) return getCookiesSQL(player);
		return getCookiesConfig(player);
	}

	public static void addCookie(Player player) {
		String info = getMessage("CookiesDisplayed1");
		String cookies = getMessage("CookiesDisplayed2").replaceAll("%cookies%", getTransformed(getCookies(player)));
		if (isMySQLActive()) {
			int amount = getProfit(player);
			addCookiesSQL(player, amount);
			player.sendTitle(info, cookies);
			return;
		}
		int amount = getProfit(player);
		addCookiesConfig(player, amount);
		player.sendTitle(info, cookies);
		return;
	}

	public static int getProfit(Player player) {
		if (isMySQLActive()) {
			int cursor = getUpgradeSQL(player, CURSOR) * getCPSConfig(CURSOR);
			int grandma = getUpgradeSQL(player, GRANDMA) * getCPSConfig(GRANDMA);
			int farm = getUpgradeSQL(player, FARM) * getCPSConfig(FARM);
			int amount = 1 + cursor + grandma + farm;
			return amount;
		}
		int cursor = getUpgradeConfig(player, CURSOR) * getCPSConfig(CURSOR);
		int grandma = getUpgradeConfig(player, GRANDMA) * getCPSConfig(GRANDMA);
		int farm = getUpgradeConfig(player, FARM) * getCPSConfig(FARM);
		int amount = 1 + cursor + grandma + farm;
		return amount;
	}

	public static void removeCookies(Player player, int amount) {
		if (isMySQLActive()) {
			removeCookiesSQL(player, amount);
			return;
		}
		removeCookiesConfig(player, amount);
		return;
	}

	public static boolean hasEnoughCookies(Player player, int amount) {
		if (isMySQLActive()) return hasEnoughCookiesSQL(player, amount);
		return hasEnoughCookiesConfig(player, amount);
	}

}
