package de.deadorfd.utils.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.utils.mysql
 * @Date 12.11.2022
 * @Time 00:49:51
 */
public class MySQL {

	public static String host;
	public static String database;
	public static String username;
	public static String password;
	public static Connection con;

	public static void connect() {
		if (host == null) return;
		if (!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true",
						username, password);
				if (isConnected()) createTable();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void disconnect() {
		if (isConnected()) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isConnected() {
		return con != null;
	}

	public static void createTable() {
		try {
			con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Cookies(ID INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL, UUID VARCHAR(100), Cookies INT(100));")
					.executeUpdate();
			con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS CookiesUpgrades(ID INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL, UUID VARCHAR(100), CursorUpgrade INT(100), GrandmaUpgrade INT(100), FarmUpgrade INT(100));")
					.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void update(String qry) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate(qry);
			st.close();
		} catch (SQLException e) {
			connect();
			e.printStackTrace();
		}
	}

	public static ResultSet getResult(String qry) {
		if (isConnected()) {
			try {
				return con.createStatement().executeQuery(qry);
			} catch (SQLException e) {
				connect();
				e.printStackTrace();
			}
		}
		return null;
	}
}
