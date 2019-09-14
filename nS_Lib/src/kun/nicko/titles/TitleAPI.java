package kun.nicko.titles;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import kun.nicko.utils.Reflection;
import kun.nicko.utils.TabTitleSendEvent;

public class TitleAPI {

	public static String replace(String paramString) {
		return paramString.replace("&", "§").replace("%", "Porcentagem").replace("[*]", "★").replace("[**]", "✹").replace("[p]", "♔")
				.replace("[v]", "✔").replace("[+]", "◆").replace("[++]", "✦").replace("[cross]", "✠")
				.replace("<3", "§4♥").replace("->", "➽").replace("=>", "➪").replace("[.]", "░").replace("[..]", "▒")
				.replace("[...]", "▓").replace("[X]", "█").replace(">>", "▶").replace("Euro", "€").replace("<<", "◀")
				.replace("[cops]", "✪").replace("[terr]", "☠");}
	
	

	public static void randomFireworks(Player paramPlayer) {
		Firework localFirework = (Firework) paramPlayer.getWorld().spawnEntity(paramPlayer.getLocation(),
				EntityType.FIREWORK);
		FireworkMeta localFireworkMeta = localFirework.getFireworkMeta();
		int i = new Random().nextInt(5) + 1;
		FireworkEffect.Type localType = FireworkEffect.Type.BALL;
		if (i == 1) {
			localType = FireworkEffect.Type.BALL;
		}
		if (i == 2) {
			localType = FireworkEffect.Type.BALL_LARGE;
		}
		if (i == 3) {
			localType = FireworkEffect.Type.BURST;
		}
		if (i == 4) {
			localType = FireworkEffect.Type.CREEPER;
		}
		if (i == 5) {
			localType = FireworkEffect.Type.STAR;
		}
		int j = new Random().nextInt(256);
		int k = new Random().nextInt(256);
		int m = new Random().nextInt(256);
		Color localColor1 = Color.fromRGB(j, m, k);
		j = new Random().nextInt(256);
		k = new Random().nextInt(256);
		m = new Random().nextInt(256);
		Color localColor2 = Color.fromRGB(j, m, k);
		FireworkEffect localFireworkEffect = FireworkEffect.builder().flicker(new Random().nextBoolean())
				.withColor(localColor1).withFade(localColor2).with(localType).trail(new Random().nextBoolean()).build();
		localFireworkMeta.addEffect(localFireworkEffect);
		int n = new Random().nextInt(2) + 1;
		localFireworkMeta.setPower(n);
		localFirework.setFireworkMeta(localFireworkMeta);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sendTitle(Player paramPlayer, String paramString1, String paramString2, int paramInt1,
			int paramInt2, int paramInt3) {
		try {
			Class localClass1 = Reflection.getClass("{nms}.PacketPlayOutTitle$EnumTitleAction");
			Class localClass2 = Reflection.getClass("{nms}.IChatBaseComponent$ChatSerializer");
			Class localClass3 = Reflection.getClass("{nms}.PacketPlayOutTitle");
			if (paramString1 != null) {
				Reflection.sendPlayerPacket(paramPlayer, localClass3
						.getConstructor(new Class[] { localClass1, Reflection.getClass("{nms}.IChatBaseComponent") })
						.newInstance(new Object[] {
								localClass1
										.getDeclaredMethod("valueOf", new Class[] { String.class })
										.invoke(null, new Object[] { "TITLE" }),
								localClass2.getDeclaredMethod("a", new Class[] { String.class }).invoke(null,
										new Object[] { "{\"text\": \"" + replace(paramString1) + "\"}" }) }));
			}
			if (paramString2 != null) {
				Reflection.sendPlayerPacket(paramPlayer, localClass3
						.getConstructor(new Class[] { localClass1, Reflection.getClass("{nms}.IChatBaseComponent") })
						.newInstance(new Object[] {
								localClass1
										.getDeclaredMethod("valueOf", new Class[] { String.class })
										.invoke(null, new Object[] { "SUBTITLE" }),
								localClass2.getDeclaredMethod("a", new Class[] { String.class }).invoke(null,
										new Object[] { "{\"text\": \"" + replace(paramString2) + "\"}" }) }));
			}
			Object localObject = Reflection.getClass("{nms}.PacketPlayOutTitle")
					.getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE }).newInstance(new Object[] {
							Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) });
			Reflection.sendPlayerPacket(paramPlayer, localObject);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public static void sendTabTitle(Player player, String header, String footer) {
		if (header == null) {
			header = "";
		}
		header = ChatColor.translateAlternateColorCodes('&', header);
		if (footer == null) {
			footer = "";
		}
		footer = ChatColor.translateAlternateColorCodes('&', footer);

		TabTitleSendEvent tabTitleSendEvent = new TabTitleSendEvent(player, header, footer);
		Bukkit.getPluginManager().callEvent(tabTitleSendEvent);
		if (tabTitleSendEvent.isCancelled()) {
			return;
		}
		header = header.replaceAll("%player%", player.getDisplayName());
		footer = footer.replaceAll("%player%", player.getDisplayName());
		try {
			Object tabHeader = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
					.getMethod("a", new Class[] { String.class })
					.invoke(null, new Object[] { "{\"text\":\"" + header + "\"}" });
			Object tabFooter = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
					.getMethod("a", new Class[] { String.class })
					.invoke(null, new Object[] { "{\"text\":\"" + footer + "\"}" });
			Constructor<?> titleConstructor = getNMSClass("PacketPlayOutPlayerListHeaderFooter")
					.getConstructor(new Class[] { getNMSClass("IChatBaseComponent") });
			Object packet = titleConstructor.newInstance(new Object[] { tabHeader });
			Field field = packet.getClass().getDeclaredField("b");
			field.setAccessible(true);
			field.set(packet, tabFooter);
			sendPacket(player, packet);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Class<?> getNMSClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			return Class.forName("net.minecraft.server." + version + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void sendPacket(Player player, Object packet) {
		try {
			Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") })
					.invoke(playerConnection, new Object[] { packet });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
