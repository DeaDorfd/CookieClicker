package de.deadorfd.utils;

import static de.deadorfd.utils.Config.*;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.utils
 * @Date 12.11.2022
 * @Time 02:40:30
 */
public enum Upgrades {

	CURSOR("Cursor", getCostConfig("Cursor")), GRANDMA("Grandma", getCostConfig("Grandma")),
	FARM("Farm", getCostConfig("Farm"));

	private int cost;
	private String upgradename;

	Upgrades(String upgradename, int cost) {
		this.upgradename = upgradename;
		this.cost = cost;
	}

	public String getUpgradeName() {
		return upgradename;
	}

	public int getCost() {
		return cost;
	}
}