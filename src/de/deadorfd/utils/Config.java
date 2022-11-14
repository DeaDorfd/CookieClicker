package de.deadorfd.utils;

import org.bukkit.configuration.file.FileConfiguration;
import de.deadorfd.utils.mysql.MySQL;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.utils
 * @Date 12.11.2022
 * @Time 00:49:31
 */
public class Config {

	public static FileConfiguration cfg;

	public static void initMySQL() {
		if (!isMySQLActive()) return;
		MySQL.host = cfg.getString("MySQL.Host");
		MySQL.username = cfg.getString("MySQL.UserName");
		MySQL.password = cfg.getString("MySQL.Password");
		MySQL.database = cfg.getString("MySQL.Database");
	}

	public static String getMessage(String path) {
		return cfg.getString("Messages." + path).replaceAll("&", "§");
	}

	public static String getPermission(String path) {
		return cfg.getString("Permissions." + path);
	}

	public static String getInventory(String path) {
		return cfg.getString("Inventory." + path).replaceAll("&", "§");
	}

	public static String getString(String path) {
		return cfg.getString(path).replaceAll("&", "§");
	}

	public static int getCostConfig(String path) {
		return cfg.getInt("Upgrades." + path + "_Price");
	}

	public static int getCPSConfig(Upgrades upgrade) {
		return cfg.getInt("Upgrades." + upgrade.getUpgradeName() + "_CPS");
	}

	public static boolean getBoolean(String path) {
		return cfg.getBoolean(path);
	}

	public static boolean isMySQLActive() {
		return getBoolean("UseMySQL");
	}

}
