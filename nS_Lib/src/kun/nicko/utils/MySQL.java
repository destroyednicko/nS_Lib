package kun.nicko.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

	public static String host = "localhost";
	public static String database = "nSDB";
	public static String user = "root";
	public static String passwd = "ASxAYvPKgbw3UmNr";
	public static Connection con;

	public static void connect() {
		if (!isconnected()) {
			try {
				con = DriverManager.getConnection(
						"jdbc:mysql://" + host + ":" + "3306/" + database + "?autoReconnect=true", user, passwd);
			} catch (SQLException e) {
				System.out.println("[DB] Erro no DB!");
			}
		}
	}

	public static void disconnect() {
		if (isconnected()) {
			try {
				con.close();
				System.out.println("[DB] Conexão encerrada.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static boolean isconnected() {
		return (con == null ? false : true);

	}

	public static PreparedStatement getStatement(String sql) {
		if (isconnected()) {
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(sql);
				return ps;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static ResultSet getResult(String sql) {
		if (isconnected()) {
			PreparedStatement ps;
			ResultSet rs;
			try {
				ps = getStatement(sql);
				rs = ps.executeQuery();
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}