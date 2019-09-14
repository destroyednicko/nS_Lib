package kun.nicko.chat;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import kun.nicko.Main;
import kun.nicko.mysql.PrepareNick;

public class ChatEvent {
	public static void onChat(Player p, AsyncPlayerChatEvent e) {
		String message = e.getMessage().replaceAll("%", "Porcentagem");

		if (PrepareNick.nws(p) == 1) {
			if (Main.islobby() == true) {
				if (p.hasPermission("lobby.gerente") || p.hasPermission("lobby.dev") || p.hasPermission("lobby.admin") || p.hasPermission("lobby.moderador") || p.hasPermission("lobby.ajudante") || p.hasPermission("lobby.builder")) {
					message = ChatColor.translateAlternateColorCodes('&', message);
				}
				p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20.0F, 20.0F);
				e.setFormat("§7Membro §8┃§7 " + p.getDisplayName() + " §8» §7" + message);

				if (p.getDisplayName().startsWith("§a")) {
					e.setFormat("§aVIP §8┃§6 " + p.getDisplayName() + " §8» §7" + message);

				}
				if (p.getDisplayName().startsWith("§c")) {
					e.setFormat("§cYouTuber §8┃§5 " + p.getDisplayName() + " §8» §7" + message);
				}
				if (p.getDisplayName().startsWith("§b")) {
					e.setFormat("§bDev §8┃§b " + p.getDisplayName() + " §8» §7" + message);
				}
				if (p.getDisplayName().startsWith("§e")) {
					e.setFormat("§eBuilder §8┃§e " + p.getDisplayName() + " §8» §7" + message);
				}
				if (p.getDisplayName().startsWith("§e")) {
					e.setFormat("§eAjudante §8┃§a " + p.getDisplayName() + " §8» §7" + message);
				}

				if (p.getDisplayName().startsWith("§2")) {
					if (!p.hasPermission("lobby.moderador")) {
						e.setFormat("§2Moderador §8┃§c " + p.getDisplayName() + " §8» §7" + message);
					} 
					if (!p.hasPermission("lobby.admin")){
						e.setFormat("§cAdmin §8┃§c " + p.getDisplayName() + " §8» §7" + message);
					}

				}
				if (p.getDisplayName().startsWith("§4")) {
					e.setFormat("§4Gerente §8┃§4 " + p.getDisplayName() + " §8» §7" + message);
				}

			} else {
				e.setFormat("§7Membro §8┃§7 " + p.getDisplayName() + " §8» §7" + message);
			}
		} else {
			if (p.hasPermission("lobby.gerente") || p.hasPermission("lobby.dev") || p.hasPermission("lobby.admin") || p.hasPermission("lobby.moderador") || p.hasPermission("lobby.ajudante") || p.hasPermission("lobby.builder")) {
				message = ChatColor.translateAlternateColorCodes('&', message);
			}
			p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20.0F, 20.0F);
			e.setFormat("§7Membro §8┃§7 " + p.getDisplayName() + " §8» §7" + message);

			if (p.getDisplayName().startsWith("§a")) {
				e.setFormat("§aVIP §8┃§6 " + p.getDisplayName() + " §8» §7" + message);

			}
			if (p.getDisplayName().startsWith("§c")) {
				e.setFormat("§cYouTuber §8┃§5 " + p.getDisplayName() + " §8» §7" + message);
			}
			if (p.getDisplayName().startsWith("§b")) {
				e.setFormat("§bDev §8┃§b " + p.getDisplayName() + " §8» §7" + message);
			}

			if (p.getDisplayName().startsWith("§e")) {
				if(!p.hasPermission("lobby.builder")){
				e.setFormat("§eBuilder §8┃§e " + p.getDisplayName() + " §8» §7" + message);
				}
			}
			if (p.getDisplayName().startsWith("§e")) {
				e.setFormat("§eAjudante §8┃§a " + p.getDisplayName() + " §8» §7" + message);
			}

			if (p.getDisplayName().startsWith("§2")) {
				if (!p.hasPermission("lobby.moderador")) {
					e.setFormat("§2Moderador §8┃§c " + p.getDisplayName() + " §8» §7" + message);
				} 
				if (!p.hasPermission("lobby.admin")){
					e.setFormat("§cAdmin §8┃§c " + p.getDisplayName() + " §8» §7" + message);
				}
			}
			if (p.getDisplayName().startsWith("§4")) {
				e.setFormat("§4Gerente §8┃§4 " + p.getDisplayName() + " §8» §6" + message);
			}
		}

	}
}