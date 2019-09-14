package kun.nicko.tablist;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import kun.nicko.Main;
import kun.nicko.mysql.PrepareNick;

public class TabEvent {

	@SuppressWarnings({ "deprecation", "unused" })
	public static void onremoveTabList(Player paramplayer) {
		Player p = paramplayer;

		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();

		Team membro = board.getTeam("011-Membro");
		Team vip = board.getTeam("010-VIP");
		Team vipplus = board.getTeam("009-VIPplus");
		Team youtuber = board.getTeam("008-YouTuber");
		Team builder = board.getTeam("006-Builder");
		Team ajudante = board.getTeam("005-Ajudante");
		Team moderador = board.getTeam("004-Moderador");
		Team admin = board.getTeam("003-Admin");
		Team developer = board.getTeam("002-Developer");
		Team gerente = board.getTeam("001-Gerente");
		if(membro.getPlayers().contains(paramplayer)){
			membro.removePlayer(paramplayer);
		}
		if(vip.getPlayers().contains(paramplayer)){
			vip.removePlayer(paramplayer);
		}

		if(vipplus.getPlayers().contains(paramplayer)){
			vipplus.removePlayer(paramplayer);
		}
		if(youtuber.getPlayers().contains(paramplayer)){
			youtuber.removePlayer(paramplayer);
		}
		if(builder.getPlayers().contains(paramplayer)){
			builder.removePlayer(paramplayer);
		}
		if(developer.getPlayers().contains(paramplayer)){
			developer.removePlayer(paramplayer);
		}
		if(admin.getPlayers().contains(paramplayer)){
			admin.removePlayer(paramplayer);
		}
		if(moderador.getPlayers().contains(paramplayer)){
			moderador.removePlayer(paramplayer);
		}
		if(ajudante.getPlayers().contains(paramplayer)){
			ajudante.removePlayer(paramplayer);
		}
		if(gerente.getPlayers().contains(paramplayer)){
			gerente.removePlayer(paramplayer);
		}
	}

	@SuppressWarnings("deprecation")
	public static void onsetTabList(Player paramplayer) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.hidePlayer(all);
			all.hidePlayer(paramplayer);
			paramplayer.hidePlayer(all);
			paramplayer.showPlayer(all);
			all.showPlayer(all);
			all.showPlayer(paramplayer);

		}


		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team membro = board.getTeam("011-Membro");
		Team vip = board.getTeam("010-VIP");
		Team vipplus = board.getTeam("009-VIPplus");
		Team youtuber = board.getTeam("008-YouTuber");
		Team builder = board.getTeam("006-Builder");
		Team ajudante = board.getTeam("005-Ajudante");
		Team moderador = board.getTeam("004-Moderador");
		Team admin = board.getTeam("003-Admin");
		Team developer = board.getTeam("002-Developer");
		Team gerente = board.getTeam("001-Gerente");
		if (membro == null) {
			membro = board.registerNewTeam("011-Membro");
			membro.setPrefix("§7");
		}
		if (vip == null) {
			vip = board.registerNewTeam("010-VIP");
			vip.setPrefix("§6");
		}

		if (vipplus == null) {
			vipplus = board.registerNewTeam("009-VIPplus");
			vipplus.setPrefix("§6");
		}
		if (youtuber == null) {
			youtuber = board.registerNewTeam("008-YouTuber");
			youtuber.setPrefix("§5");
		}
		if (builder == null) {
			builder = board.registerNewTeam("006-Builder");
			builder.setPrefix("§eBuilder §8┃ §e");
		}
		if (developer == null) {
			developer = board.registerNewTeam("002-Developer");
			developer.setPrefix("§bDev §8┃ §b");
		}
		if (ajudante == null) {
			ajudante = board.registerNewTeam("005-Ajudante");
			ajudante.setPrefix("§eAjudante §8┃ §a");
		}
		if (moderador == null) {
			moderador = board.registerNewTeam("004-Moderador");
			moderador.setPrefix("§2Mod §8┃ §c");
		}
		if (admin == null) {
			admin = board.registerNewTeam("003-Admin");
			admin.setPrefix("§cAdmin §8┃ §c");
		}
		if (gerente == null) {
			gerente = board.registerNewTeam("001-Gerente");
			gerente.setPrefix("§4Gerente §8┃ §4");
		}
		String namenick = paramplayer.getName();
		Player p = paramplayer;
		String dname = "§7" + namenick;
		String name = "§7" + namenick;
		if (p.hasPermission("lobby.vip")) {
			dname = "§6" + namenick;
		}
		if (p.hasPermission("lobby.vipplus")) {
			dname = "§6" + namenick;
		}
		if (p.hasPermission("lobby.youtuber")) {
			dname = "§c" + namenick;
		}
		if (p.hasPermission("lobby.builder")) {
			dname = "§e" + namenick;
		}
		if (p.hasPermission("lobby.ajudante")) {
			dname = "§e" + namenick;
		}
		if (p.hasPermission("lobby.developer")) {
			dname = "§b" + namenick;
		}
		if ((p.hasPermission("lobby.moderador"))) {
			dname = "§2" + namenick;
		}
		if (p.hasPermission("lobby.admin")) {
			dname = "§c" + namenick;
		}
		if (p.hasPermission("lobby.gerente")) {
			dname = "§4" + namenick;
		}
		if (!paramplayer.hasPermission("lobby.membro") || !paramplayer.hasPermission("lobby.vip")) {
			if (Main.islobby() == false) {
				boolean noAI = true;
				if (PrepareNick.nws(p) == 1) {
					String namenicked = PrepareNick.getname(p);
					p.setDisplayName("§7" + namenicked);
					Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {

						@SuppressWarnings("static-access")
						@Override
						public void run() {
							Team membro = board.getTeam("011-Membro");
							if (membro == null) {
								membro = board.registerNewTeam("011-Membro");
								membro.setPrefix("§7");
							}
							membro.addEntry(p.getName());
//							//Skin s = new Skin(p.getUniqueId().toString().replace("-", ""));
////							NickPlugin.getPlugin().getAPI().setGameProfile(paramplayer, usednick);
////							NickPlugin.getPlugin().getAPI().nick(paramplayer, usednick, null, null);
//							//String u = NickPlugin.getPlugin().getAPI().getUUIDGetter().getUUIDAt(namenicked);
//							System.out.println(">>  "+UUIDFetcher.getUUID(usednick));
//						//	NickPlugin.getPlugin().getAPI().refreshPlayer(paramplayer);
//							iTag.getInstance().refreshPlayer(p);
//							try {
////								System.out.println("[UUID] > "+UUIDFetcher.getUUID(usednick));
////								NameUtils.setName(p, usednick);
////								SkinUtils.updateSkinFrom(p, UUIDFetcher.getUUID(PrepareNick.getname(p)), false, PrepareNick.getname(p));
////							//	SkinUtils.updateSkin(p, id);
////								GameProfile gp = Main.gameprofiles.get(p.getName());
////								
////								SkinUtils.updateGameProfile((GameProfile) gp, UUIDFetcher.getUUID(PrepareNick.getname(p)), PrepareNick.getname(p));
//
//								
//							} catch (Exception e) {
//							}
//
//							p.sendMessage(
//									Main.nickprefix + "§7");
						}
					}, 1L);
				} else {
					if (p.hasPermission("lobby.vip")) {
						name = "§6" + namenick;
					}
					if (p.hasPermission("lobby.vipplus")) {
						name = "§6" + namenick;
					}
					if (p.hasPermission("lobby.youtuber")) {
						name = "§c" + namenick;
					}
					if (p.hasPermission("lobby.builder")) {
						name = "§eBuilder §8┃ §e" + namenick;
					}
					if (p.hasPermission("lobby.ajudante")) {
						name = "§eAjudante §8┃ §a" + namenick;
					}
					if (p.hasPermission("lobby.developer")) {
						name = "§bDev §8┃ §b" + namenick;
					}
					if ((p.hasPermission("lobby.moderador"))) {
						name = "§2Mod §8┃ §c" + namenick;
					}
					if (p.hasPermission("lobby.admin")) {
						name = "§cAdmin §8┃ §c" + namenick;
					}
					if (p.hasPermission("lobby.gerente")) {
						name = "§4Gerente §8┃ §4" + namenick;
					}

					p.setDisplayName(dname);
					if (p.getDisplayName().startsWith("§7")) {
						membro.addPlayer(p);
					}
					if (p.getDisplayName().startsWith("§6")) {
						if (!p.hasPermission("lobby.vipplus")) {
							vip.addPlayer(p);
						} else {
							vipplus.addPlayer(p);
						}
					}
					if (p.getDisplayName().startsWith("§5")) {
						youtuber.addPlayer(p);
					}
					if (p.getDisplayName().startsWith("§e")) {
						builder.addPlayer(p);
					}
					if (p.getDisplayName().startsWith("§b")) {
						developer.addPlayer(p);
					}
					if (p.getDisplayName().startsWith("§a")) {
						ajudante.addPlayer(p);
					}
					if (p.getDisplayName().startsWith("§c")) {
						if (!p.hasPermission("lobby.admin")) {
							moderador.addPlayer(p);
						} else {
							admin.addPlayer(p);
						}
					}
					if (p.getDisplayName().startsWith("§4")) {
						gerente.addPlayer(p);
					}
					p.setPlayerListName(name);

					p.setScoreboard(board);

				}
			} else {
				if (p.hasPermission("lobby.vip")) {
					name = "§6" + namenick;
				}
				if (p.hasPermission("lobby.vipplus")) {
					name = "§6" + namenick;
				}
				if (p.hasPermission("lobby.youtuber")) {
					name = "§c" + namenick;
				}
				if (p.hasPermission("lobby.builder")) {
					name = "§eBuilder §8┃ §e" + namenick;
				}
				if (p.hasPermission("lobby.headbuilder")) {
					name = "§eBuilder §8┃ §e" + namenick;
				}
				if (p.hasPermission("lobby.ajudante")) {
					name = "§eAjudante §8┃ §a" + namenick;
				}
				if (p.hasPermission("lobby.developer")) {
					name = "§bDev §8┃ §b" + namenick;
				}
				if ((p.hasPermission("lobby.moderador"))) {
					name = "§2Mod §8┃ §c" + namenick;
				}
				if (p.hasPermission("lobby.admin")) {
					name = "§cAdmin §8┃ §c" + namenick;
				}
				if (p.hasPermission("lobby.gerente")) {
					name = "§4Gerente §8┃ §4" + namenick;
				}


				p.setDisplayName(dname);
				if (p.getDisplayName().startsWith("§7")) {
					membro.addPlayer(p);
				}
				if (p.getDisplayName().startsWith("§6")) {
					if (!p.hasPermission("lobby.vipplus")) {
						vip.addPlayer(p);
					} else {
						vipplus.addPlayer(p);
					}
				}
				if (p.getDisplayName().startsWith("§5")) {
					youtuber.addPlayer(p);
				}
				if (p.getDisplayName().startsWith("§e")) {
					builder.addPlayer(p);
				}
				if (p.getDisplayName().startsWith("§b")) {
					developer.addPlayer(p);
				}
				if (p.getDisplayName().startsWith("§a")) {
					ajudante.addPlayer(p);
				}
				if (p.getDisplayName().startsWith("§c")) {
					if (!p.hasPermission("lobby.admin")) {
						moderador.addPlayer(p);
					} else {
						admin.addPlayer(p);
					}
				}
				if (p.getDisplayName().startsWith("§4")) {
					gerente.addPlayer(p);
				}
				p.setPlayerListName(name);

				p.setScoreboard(board);

			}
		} else {
			if (PrepareNick.nws(p) == 1) {
				PrepareNick.setshouldnick(p, 0);
				p.kickPlayer("Noob");
			}
			if (p.hasPermission("lobby.vip")) {
				name = "§6" + namenick;
			}
			if (p.hasPermission("lobby.vipplus")) {
				name = "§6" + namenick;
			}
			if (p.hasPermission("lobby.youtuber")) {
				name = "§5" + namenick;
			}
			if (p.hasPermission("lobby.builder")) {
				name = "§eBuilder §8┃ §e" + namenick;
			}
			if (p.hasPermission("lobby.headbuilder")) {
				name = "§eBuilder §8┃ §e" + namenick;
			}
			if (p.hasPermission("lobby.ajudante")) {
				name = "§eAjudante §8┃ §a" + namenick;
			}
			if (p.hasPermission("lobby.developer")) {
				name = "§bDev §8┃ §b" + namenick;
			}
			if ((p.hasPermission("lobby.moderador"))) {
				name = "§2Mod §8┃ §c" + namenick;
			}
			if (p.hasPermission("lobby.admin")) {
				name = "§cAdmin §8┃ §c" + namenick;
			}
			if (p.hasPermission("lobby.gerente")) {
				name = "§4Gerente §8┃ §4" + namenick;
			}

		
			p.setDisplayName(dname);
			if (p.getDisplayName().startsWith("§7")) {
				membro.addPlayer(p);
			}
			if (p.getDisplayName().startsWith("§6")) {
				if (!p.hasPermission("lobby.vipplus")) {
					vip.addPlayer(p);
				} else {
					vipplus.addPlayer(p);
				}
			}
			if (p.getDisplayName().startsWith("§5")) {
				youtuber.addPlayer(p);
			}
			if (p.getDisplayName().startsWith("§e")) {
				builder.addPlayer(p);
			}
			if (p.getDisplayName().startsWith("§b")) {
				developer.addPlayer(p);
			}
			if (p.getDisplayName().startsWith("§a")) {
				ajudante.addPlayer(p);
			}
			if (p.getDisplayName().startsWith("§c")) {
				if (!p.hasPermission("lobby.admin")) {
					moderador.addPlayer(p);
				} else {
					admin.addPlayer(p);
				}
			}
			if (p.getDisplayName().startsWith("§4")) {
				gerente.addPlayer(p);
			}
			p.setPlayerListName(name);
			p.setScoreboard(board);
		}
	}

}
