package kun.nicko.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class Reflection
{
  public static Class<?> getClass(String paramString)
  {
    try
    {
      String str1 = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
      String str2 = paramString.replace("{nms}", "net.minecraft.server." + str1)
        .replace("{nm}", "net.minecraft." + str1)
        .replace("{cb}", "org.bukkit.craftbukkit.." + str1);
      return Class.forName(str2);
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public static Object getNmsPlayer(Player paramPlayer)
  {
    try
    {
      Method localMethod = paramPlayer.getClass().getMethod("getHandle", new Class[0]);
      return localMethod.invoke(paramPlayer, new Object[0]);
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  
  

  
 
  
  
  public static void setValue(Object paramObject1, String paramString, Object paramObject2)
  {
    try
    {
      Field localField = paramObject1.getClass().getDeclaredField(paramString);
      localField.setAccessible(true);
      localField.set(paramObject1, paramObject2);
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
 
  
  public static void sendListPacket(List<String> paramList, Object paramObject)
  {
    try
    {
      for (String str : paramList)
      {
        Object localObject1 = getNmsPlayer(Bukkit.getPlayer(str));
        Object localObject2 = localObject1.getClass().getField("playerConnection").get(localObject1);
        localObject2.getClass().getMethod("sendPacket", new Class[] { getClass("{nms}.Packet") }).invoke(localObject2, new Object[] { paramObject });
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static void sendPlayerPacket(Player paramPlayer, Object paramObject)
  {
    Object localObject1 = getNmsPlayer(paramPlayer);
    Object localObject2 = null;
	try {
		localObject2 = localObject1.getClass().getField("playerConnection").get(localObject1);
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try {
		localObject2.getClass().getMethod("sendPacket", new Class[] { getClass("{nms}.Packet") }).invoke(localObject2, new Object[] { paramObject });
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
