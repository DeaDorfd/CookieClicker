package de.deadorfd.utils.config;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.deadorfd.utils.API;
import de.deadorfd.utils.Upgrades;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.utils.config
 * @Date 12.11.2022
 * @Time 00:51:46
 */
public class CookieUpgrades {

	// Types: Cursor, Grandma, Farm

	public static int getUpgradeConfig(Player player, Upgrades upgrade) {
		File file = new File("plugins//CookieClicker//Cookies.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int amount = cfg.getInt(player.getUniqueId().toString() + "." + upgrade.getUpgradeName());
		return amount;
	}

	public static void addUpgradeConfig(Player player, int amount, Upgrades upgrade) {
		File file = new File("plugins//CookieClicker//Cookies.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int upgradeamount = cfg.getInt(player.getUniqueId().toString() + "." + upgrade.getUpgradeName()) + amount;
		cfg.set(player.getUniqueId().toString() + "." + upgrade.getUpgradeName(), upgradeamount);
		API.saveFile(cfg, file);
	}

	public static void removeUpgradeConfig(Player player, int amount, Upgrades upgrade) {
		File file = new File("plugins//CookieClicker//Cookies.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int upgradeamount = cfg.getInt(player.getUniqueId().toString() + "." + upgrade.getUpgradeName()) - amount;
		cfg.set(player.getUniqueId().toString() + "." + upgrade.getUpgradeName(), upgradeamount);
		API.saveFile(cfg, file);
	}
} // 122