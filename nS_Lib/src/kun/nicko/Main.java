package kun.nicko;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mojang.authlib.GameProfile;

import kun.nicko.utils.MySQL;


public class Main extends JavaPlugin {

	public static Main plugin;
	public HashMap<Player, String> REALNAMES = new HashMap();
	public HashMap<UUID, String[]> PLAYERDATAS = new HashMap();
	public ArrayList<Player> NODEATH = new ArrayList();
	public static ArrayList<String> nicks = new ArrayList<String>();
	public static HashMap<String, GameProfile> gameprofiles = new HashMap<>();
	public static HashMap<String, String> nickname = new HashMap<String, String>();
	public static String prefix = "§6 * ";
	public static String nickprefix = "§6 * nS: ";
	public static File file = new File("plugins/nsLib", "MySQL.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	private static MySQL sql;

	public static Main getInstance() {
		return plugin;
	}

	public static MySQL getSQL() {
		return sql;
	}

	public static boolean islobby() {
		if (Bukkit.getServer().getPort() == 37215) {
			return true;
		}
		if (Bukkit.getServer().getPort() == 35954) {
			return true;
		}
		if (Bukkit.getServer().getPort() == 38070) {
			return true;
		}
		if (Bukkit.getServer().getPort() == 38080) {
			return true;
		}
		return false;
	}


	
	public static String randomNick() {
		Random r = new Random();
		ArrayList<String> nicks = new ArrayList<String>();
		for (String nicknames : gameprofiles.keySet()) {
			nicks.add(nicknames);
		}
		String nick = nicks.get(r.nextInt(nicks.size()));
		if (isNickUsed(nick)) {
			return randomNick();
		}
		return nick;
	}



	public static boolean isNickUsed(String nick) {
		for (Player players : Bukkit.getOnlinePlayers()) {
			if (ChatColor.stripColor(players.getDisplayName()).equalsIgnoreCase(nick)) {
				return true;
			}
		}
		for (Player players : Bukkit.getOnlinePlayers()) {
			if (players.getName().equalsIgnoreCase(nick)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onEnable() {
		plugin = this;
		sql = new MySQL();
		saveDefaultConfig();
		reloadConfig();

		try {
			getCommand("api").setExecutor(new API_CMD());

		} catch (Exception e) {
			e.printStackTrace();
		}
		MySQL.connect();
		if (MySQL.isconnected()) {
			PreparedStatement ps = MySQL.getStatement(
					"CREATE TABLE IF NOT EXISTS Nick (UUID VARCHAR(255), ShouldNick TINYINT(1))");

			PreparedStatement ps3 = MySQL.getStatement(
					"CREATE TABLE IF NOT EXISTS Gadgets (UUID VARCHAR(255), Gadget_KC TINYINT(1), Gadget_FS TINYINT(1))");
			PreparedStatement ps4 = MySQL.getStatement(
					"CREATE TABLE IF NOT EXISTS Pets (UUID VARCHAR(255), Pet_1 TINYINT(1), Pet_2 TINYINT(1), Pet_3 TINYINT(1))");
			PreparedStatement ps5 = MySQL.getStatement(
					"CREATE TABLE IF NOT EXISTS Partikel (UUID VARCHAR(255), Par_1 TINYINT(1), Par_2 TINYINT(1), Par_3 TINYINT(1), Par_4 TINYINT(1), Par_5 TINYINT(1), Par_6 TINYINT(1), Par_7 TINYINT(1), Par_8 TINYINT(1))");
			PreparedStatement ps6 = MySQL
					.getStatement("CREATE TABLE IF NOT EXISTS Slient (UUID VARCHAR(255), ShouldSlient TINYINT(1))");
			try {
				ps.executeUpdate();
				ps3.executeUpdate();
				ps4.executeUpdate();
				ps5.executeUpdate();
				ps6.executeUpdate();
				//SKIN//

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		super.onEnable();
	}

	@Override
	public void onDisable() {
		MySQL.disconnect();
		super.onDisable();
	}

}
