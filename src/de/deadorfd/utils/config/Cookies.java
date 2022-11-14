package de.deadorfd.utils.config;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.deadorfd.utils.API;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.utils.config
 * @Date 12.11.2022
 * @Time 01:30:33
 */
public class Cookies {

	public static int getCookiesConfig(Player player) {
		File file = new File("plugins//CookieClicker//Cookies.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int cookies = cfg.getInt(player.getUniqueId().toString() + ".Cookies");
		return cookies;
	}

	public static void addCookiesConfig(Player player, int amount) {
		File file1 = new File("plugins//CookieClicker//Cookies.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file1);
		int cookies = cfg.getInt(player.getUniqueId().toString() + ".Cookies");
		cookies += amount;
		cfg.set(player.getUniqueId().toString() + ".Cookies", cookies);
		API.saveFile(cfg, file1);
	}

	public static void removeCookiesConfig(Player player, int amount) {
		File file = new File("plugins//CookieClicker//Cookies.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int cookies = cfg.getInt(player.getUniqueId().toString() + ".Cookies");
		cookies -= amount;
		cfg.set(player.getUniqueId().toString() + ".Cookies", cookies);
		API.saveFile(cfg, file);
	}

	public static void setCookiesConfig(Player player, int amount) {
		File file = new File("plugins//CookieClicker//Cookies.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		cfg.set(player.getUniqueId().toString() + ".Cookies", amount);
		API.saveFile(cfg, file);
	}

	public static boolean hasEnoughCookiesConfig(Player player, int amount) {
		File file = new File("plugins//CookieClicker//Cookies.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int cookies = cfg.getInt(player.getUniqueId().toString() + ".Cookies");
		if (cookies >= amount) return true;
		return false;
	}
}