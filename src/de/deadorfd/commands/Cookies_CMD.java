package de.deadorfd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.deadorfd.api.CookiesAPI.*;
import static de.deadorfd.utils.API.*;
import static de.deadorfd.utils.Config.*;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.commands
 * @Date 14.11.2022
 * @Time 03:24:00
 */
public class Cookies_CMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			String cookies = getTransformed(getCookies(player));
			String profit = getTransformed(getProfit(player));
			player.sendMessage(prefix + getMessage("CookiesInfo1").replaceAll("%cookies%", cookies));
			player.sendMessage(prefix + getMessage("CookiesInfo2").replaceAll("%profit%", profit));
		} else {
			wrongCommand("Cookies");
			return true;
		}
		return true;
	}
}