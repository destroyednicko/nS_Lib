package kun.nicko.utils;

import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import kun.nicko.Main;



public class FileManager {

	public static void saveconfigs() {
		try {
			Main.cfg.save(Main.file);
		} catch (Exception e) {
			Bukkit.getLogger().log(Level.SEVERE, "[Salvar] Não foi possivel salvar as configs.!");
		}
	}

	public static void setMySQL() {
		FileConfiguration cfg = Main.cfg;
		cfg.options().copyDefaults(true);
		cfg.options().header(
				"############################################################\n# +------------------------------------------------------+ #\n# |                      MySQL Config                    | #\n# +------------------------------------------------------+ #\n############################################################");

		cfg.addDefault("MySQL.Host", "MySQL Host");
		cfg.addDefault("MySQL.Database", "MySQL Database");
		cfg.addDefault("MySQL.Username", "MySQL User");
		cfg.addDefault("MySQL.Password", "MySQL Password");
		try {
			cfg.save(Main.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getMySQL() {
//		MySQL.user = Main.cfg.getString("MySQL.Username");
//		MySQL.database = Main.cfg.getString("MySQL.Database");
//		MySQL.passwd = Main.cfg.getString("MySQL.Password");
//		MySQL.host = Main.cfg.getString("MySQL.Host");
	}

}
