package de.deadorfd.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import de.deadorfd.main.CookieClicker;

/**
 * @Author DeaDorfd
 * @Project CookieClicker
 * @Package de.deadorfd.utils
 * @Date 14.11.2022
 * @Time 02:42:56
 */
public class Check {

	private static boolean updatet = false;

	public static void check() {
		String check = readURL("75140").replace(".", "_");
		int version = Integer.valueOf(check.split("_")[0]);
		int minorversion = Integer.valueOf(check.split("_")[1]);
		String stringversion = CookieClicker.getInstance().getDescription().getVersion().replace(".", "_");
		int pversion = Integer.valueOf(stringversion.split("_")[0]);
		int pminorversion = Integer.valueOf(stringversion.split("_")[1]);
		if (version > pversion) {
			setUpdatet(false);
			return;
		}
		if (minorversion > pminorversion) {
			setUpdatet(false);
			return;
		}
		setUpdatet(true);
		return;
	}

	private static String readURL(String urlstring) {
		String re = "";
		try {
			URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + urlstring);
			Reader is = new InputStreamReader(url.openStream());
			BufferedReader in = new BufferedReader(is);
			String s;
			while ((s = in.readLine()) != null)
				re = re + s;
			in.close();
		} catch (Exception e) {
			setUpdatet(false);
		}
		return re;
	}

	public static boolean isUpdatet() {
		return updatet;
	}

	private static void setUpdatet(boolean state) {
		updatet = state;
	}

}
