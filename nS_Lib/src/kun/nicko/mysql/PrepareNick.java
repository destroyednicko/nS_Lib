package kun.nicko.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;

import kun.nicko.utils.MySQL;

public class PrepareNick {

	public static boolean isregi(Player p) {
		String uuid = p.getUniqueId().toString();
		try {
			PreparedStatement ps = MySQL.getStatement("SELECT * FROM Nick WHERE UUID= ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			boolean user = rs.next();
			rs.close();
			rs.close();
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public static void setshouldnick(Player p, int truefalse) {
		String uuid = p.getUniqueId().toString();
		PreparedStatement ps = MySQL.getStatement("UPDATE Nick SET ShouldNick= ? WHERE UUID= ?");
		try {
			ps.setInt(1, truefalse);
			ps.setString(2, uuid);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean isregi(UUID uuid) {
		try {
			PreparedStatement ps = MySQL.getStatement("SELECT * FROM Nick WHERE UUID= ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			boolean user = rs.next();
			rs.close();
			rs.close();
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static int nws(Player p) {
		try {
			PreparedStatement ps = MySQL.getStatement("SELECT * FROM Nick WHERE UUID= ?");
			ps.setString(1, p.getUniqueId().toString());
			ResultSet rs = ps.executeQuery();
			rs.next();
			int ach = rs.getInt("ShouldNick");
			rs.close();
			ps.close();
			return ach;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	public static int nws(UUID uuid) {
		try {
			PreparedStatement ps = MySQL.getStatement("SELECT * FROM Nick WHERE UUID= ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			rs.next();
			int ach = rs.getInt("ShouldNick");
			rs.close();
			ps.close();
			return ach;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public static String getrealUUID(Player p) {
		try {
			PreparedStatement ps = MySQL.getStatement("SELECT * FROM Nick WHERE SpielerName= ?");
			ps.setString(1, p.getUniqueId().toString());
			ResultSet rs = ps.executeQuery();
			rs.next();
			String ach = rs.getString("UUID");
			rs.close();
			ps.close();
			return ach;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String getname(Player p) {
		try {
			PreparedStatement ps = MySQL.getStatement("SELECT * FROM Nick WHERE UUID= ?");
			ps.setString(1, p.getUniqueId().toString());
			ResultSet rs = ps.executeQuery();
			rs.next();
			String ach = rs.getString("NickName");
			rs.close();
			ps.close();
			return ach;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String getname(UUID uuid) {
		try {
			PreparedStatement ps = MySQL.getStatement("SELECT * FROM Nick WHERE UUID= ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			rs.next();
			String ach = rs.getString("NickName");
			rs.close();
			ps.close();
			return ach;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
