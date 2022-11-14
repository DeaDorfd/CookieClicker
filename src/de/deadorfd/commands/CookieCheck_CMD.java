package de.deadorfd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.deadorfd.utils.Check;

import static de.deadorfd.utils.API.*;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.commands
 * @Date 14.11.2022
 * @Time 03:39:34
 */
public class CookieCheck_CMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (!hasPermission(player, "CookieCheck")) {
			player.sendMessage(noPermission());
			return true;
		}
		if (args.length == 0) {
			Check.check();
			if (!Check.isUpdatet()) {
				player.sendMessage(prefix + "A new version of the plugin is available.");
				player.sendMessage(prefix + "Download it here: §6" + spigotlink);
				return true;
			}
			player.sendMessage(prefix + "The plugin is up to date.");
		} else {
			wrongCommand("CookieCheck");
		}
		return true;
	}
}