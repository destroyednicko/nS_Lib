package kun.nicko;

import org.bukkit.entity.Player;

public class Message {

	public static void sixinco(String inco, Player s) {
		s.sendMessage(Main.prefix + "§cSixtase incorreta,  §6/" + inco + "§c!");
	}

	public static void semperm(Player p) {
		p.sendMessage(Main.prefix + "§cVocê não tem permissão para usar o comando.");
	}

}
