package de.deadorfd.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static de.deadorfd.api.CookiesUpgradeAPI.*;
import static de.deadorfd.utils.Upgrades.*;
import static de.deadorfd.utils.Config.*;
import static de.deadorfd.utils.Inventorys.*;
import static de.deadorfd.api.CookiesAPI.*;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.listener
 * @Date 12.11.2022
 * @Time 22:53:43
 */
public class Inventory_Listener implements Listener {

	@EventHandler
	private static void onInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getClickedBlock() == null) return;
		if (event.getClickedBlock().getType() != Material.GOLD_BLOCK) return;
		if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
			addCookie(player);
		} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			openMainInventory(player);
		}
	}

	@EventHandler
	private static void onInventoryClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		String invname = event.getView().getTitle();
		if (invname.equals(getInventory("MainInventoryName"))) {
			event.setCancelled(true);
			if (event.getCurrentItem() == null) return;
			if (!event.getCurrentItem().hasItemMeta()) return;
			String itemname = event.getCurrentItem().getItemMeta().getDisplayName();
			if (itemname.contains(getInventory("ItemName_Shop"))) {
				player.sendMessage(getString("Prefix") + getMessage("CommingSoon"));
				player.closeInventory();
				return;
			} else if (itemname.equals(getInventory("ItemName_Clicker"))) {
				addCookie(player);
			} else if (itemname.equals(getInventory("ItemName_Upgrades"))) {
				if (!getBoolean("CookieUpgrades")) {
					player.sendMessage(getString("Prefix") + getMessage("CookieUpgradesDisabled"));
					return;
				}
				openUpgradeInventory(player);
			}
		} else if (invname.equals(getInventory("UpgradeInventoryName"))) {
			event.setCancelled(true);
			if (event.getCurrentItem() == null) return;
			if (!event.getCurrentItem().hasItemMeta()) return;
			String itemname = event.getCurrentItem().getItemMeta().getDisplayName();
			// .replaceAll("%upgrades%", getUpgrade(player, CURSOR) + ""))
			if (itemname.contains(
					getInventory("ItemName_Cursor").replaceAll("%upgrades%", getUpgrade(player, CURSOR) + ""))) {
				buyUpgrade(player, CURSOR, event);
				return;
			} else if (itemname.contains(
					getInventory("ItemName_Grandma").replaceAll("%upgrades%", getUpgrade(player, GRANDMA) + ""))) {
				buyUpgrade(player, GRANDMA, event);
				return;
			} else if (itemname
					.contains(getInventory("ItemName_Farm").replaceAll("%upgrades%", getUpgrade(player, FARM) + ""))) {
				buyUpgrade(player, FARM, event);
				return;
			}
		}
	}

}
