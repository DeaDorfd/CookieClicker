package de.deadorfd.utils.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.utils.mysql
 * @Date 12.11.2022
 * @Time 01:30:40
 */
public class CookiesSQL {

	private static boolean playerExists(Player player) {
		if (MySQL.isConnected()) {
			try {
				ResultSet rs = MySQL
						.getResult("SELECT * FROM Cookies WHERE UUID= '" + player.getUniqueId().toString() + "'");
				if (rs.next()) return rs.getString("UUID") != null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private static void createPlayer(Player player, int cookies) {
		if (!playerExists(player) && (MySQL.isConnected())) {
			MySQL.update("INSERT INTO Cookies(UUID, Cookies) VALUES ('" + player.getUniqueId().toString() + "', '"
					+ cookies + "');");
		}
	}

	public static int getCookiesSQL(Player player) {
		if (playerExists(player) && MySQL.isConnected()) {
			ResultSet rs = MySQL
					.getResult("Select Cookies FROM Cookies WHERE UUID='" + player.getUniqueId().toString() + "'");
			try {
				while (rs.next())
					return rs.getInt("Cookies");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static void setCookiesSQL(Player player, int cookies) {
		if (playerExists(player) && MySQL.isConnected()) {
			MySQL.update("UPDATE Cookies SET Cookies= '" + cookies + "' WHERE UUID= '" + player.getUniqueId().toString()
					+ "'");
		} else
			createPlayer(player, cookies);
	}

	public static void addCookiesSQL(Player player, int cookies) {
		if (MySQL.isConnected()) setCookiesSQL(player, getCookiesSQL(player) + cookies);
	}

	public static void removeCookiesSQL(Player player, int cookies) {
		if (playerExists(player) && MySQL.isConnected()) {
			if (getCookiesSQL(player) >= cookies) {
				setCookiesSQL(player, getCookiesSQL(player) - cookies);
			} else
				setCookiesSQL(player, 0);
		}
	}

	public static boolean hasEnoughCookiesSQL(Player player, int amount) {
		return getCookiesSQL(player) >= amount;
	}
} // 94