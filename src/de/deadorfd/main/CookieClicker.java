package de.deadorfd.main;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.deadorfd.commands.CookieCheck_CMD;
import de.deadorfd.commands.Cookies_CMD;
import de.deadorfd.listener.Inventory_Listener;
import static de.deadorfd.utils.Check.*;
import static de.deadorfd.utils.Config.*;
import static de.deadorfd.utils.API.*;
import de.deadorfd.utils.mysql.MySQL;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.main
 * @Date 12.11.2022
 * @Time 00:39:58
 */
public class CookieClicker extends JavaPlugin {

	private static CookieClicker instance;
	private static PluginManager plu = Bukkit.getPluginManager();
	private static ConsoleCommandSender sender = Bukkit.getConsoleSender();

	@Override
	public void onEnable() {
		instance = this;
		getDataFolder().mkdir();
		saveDefaultConfig();
		cfg = getConfig();
		sender.sendMessage(prefix + "§8§l§m-----------§8<< §6§lCookieClicker §8>>§8§l§m-----------");
		sender.sendMessage(prefix + "");
		sender.sendMessage(prefix + "§7The Plugin was §2activated");
		sender.sendMessage(prefix + "§7Developer: §cDeaDorfd");
		sender.sendMessage(prefix + "§7Plugin Verison: §6" + getDescription().getVersion());
		sender.sendMessage(prefix + "");
		sender.sendMessage(prefix + "§8§l§m-----------§8<< §6§lCookieClicker §8>>§8§l§m-----------");
		initMySQL();
		MySQL.connect();
		plu.registerEvents(new Inventory_Listener(), instance);
		getCommand("Cookies").setExecutor(new Cookies_CMD());
		getCommand("CookieCheck").setExecutor(new CookieCheck_CMD());
		if (getBoolean("CheckforUpdates")) {
			check();
			if (!isUpdatet()) {
				sender.sendMessage(prefix + " ");
				sender.sendMessage(prefix + "A new version of the plugin is available.");
				sender.sendMessage(prefix + "Download it here: §6" + spigotlink);
				sender.sendMessage(prefix + " ");
			}
		}
	}

	@Override
	public void onDisable() {
		MySQL.disconnect();
		sender.sendMessage(prefix + "§8§l§m-----------§8<< §6§lCookieClicker §8>>§8§l§m-----------");
		sender.sendMessage(prefix + "");
		sender.sendMessage(prefix + "§7The Plugin was §4deactivated");
		sender.sendMessage(prefix + "§7Developer: §cDeaDorfd");
		sender.sendMessage(prefix + "§7Plugin Verison: §6" + getDescription().getVersion());
		sender.sendMessage(prefix + "");
		sender.sendMessage(prefix + "§8§l§m-----------§8<< §6§lCookieClicker §8>>§8§l§m-----------");
	}

	public static CookieClicker getInstance() {
		return instance;
	}
}