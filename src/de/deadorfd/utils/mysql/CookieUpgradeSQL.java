package de.deadorfd.utils.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import de.deadorfd.utils.Upgrades;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.utils.mysql
 * @Date 12.11.2022
 * @Time 00:52:12
 */
public class CookieUpgradeSQL {

	private static boolean playerExists(Player player) {
		if (MySQL.isConnected()) {
			try {
				ResultSet rs = MySQL.getResult(
						"SELECT * FROM CookiesUpgrades WHERE UUID= '" + player.getUniqueId().toString() + "'");
				if (rs.next()) return rs.getString("UUID") != null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private static void createPlayer(Player player, int cursor, int grandma, int farm) {
		if (!playerExists(player)) {
			MySQL.update("INSERT INTO CookiesUpgrades(UUID, CursorUpgrade, GrandmaUpgrade, FarmUpgrade) VALUES ('"
					+ player.getUniqueId().toString() + "', '" + cursor + "', '" + grandma + "', '" + farm + "');");
		}
	}

	public static int getUpgradeSQL(Player player, Upgrades upgrade) {
		if (playerExists(player) && MySQL.isConnected()) {
			ResultSet rs = MySQL
					.getResult("Select * FROM CookiesUpgrades WHERE UUID='" + player.getUniqueId().toString() + "'");
			try {
				while (rs.next())
					return rs.getInt(upgrade.getUpgradeName() + "Upgrade");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static void setUpgradeSQL(Player player, int amount, Upgrades upgrade) {
		if (playerExists(player)) {
			MySQL.update("UPDATE CookiesUpgrades SET " + upgrade.getUpgradeName() + "Upgrade= '" + amount
					+ "' WHERE UUID= '" + player.getUniqueId().toString() + "'");
		}
	}

	public static void addUpgradeSQL(Player player, int amount, Upgrades upgrade) {
		if (playerExists(player)) {
			setUpgradeSQL(player, getUpgradeSQL(player, upgrade) + amount, upgrade);
		} else if (upgrade == Upgrades.CURSOR) {
			createPlayer(player, amount, 0, 0);
		} else if (upgrade == Upgrades.GRANDMA) {
			createPlayer(player, 0, amount, 0);
		} else if (upgrade == Upgrades.FARM) {
			createPlayer(player, 0, 0, amount);
		}
	}

	public static void removeUpgradeSQL(Player player, int amount, Upgrades upgrades) {
		setUpgradeSQL(player, getUpgradeSQL(player, upgrades) - amount, upgrades);
	}

}
