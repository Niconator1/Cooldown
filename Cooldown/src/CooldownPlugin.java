import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CooldownPlugin extends JavaPlugin {
	private static ArrayList<Cooldown> clist = new ArrayList<Cooldown>();

	public void onEnable() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for (int i = 0; i < clist.size(); i++) {
					Cooldown c = clist.get(i);
					if (c.getRemainingTicks() > 0) {
						Player p = c.getPlayer();
						if (p.isOnline() && !p.isDead()) {
							p.setLevel((int) (c.getRemainingTicks() / 20.0 + 0.99));
						}
						c.setRemainingTicks(c.getRemainingTicks() - 1);
					} else {
						clist.remove(i);
					}
				}
			}
		}, 0, 1);
	}

	public static boolean isonCooldown(Player p) {
		for (int i = 0; i < clist.size(); i++) {
			Cooldown c = clist.get(i);
			if (c.getPlayer().getUniqueId().compareTo(p.getUniqueId()) == 0) {
				return true;
			}
		}
		return false;
	}

	public static Cooldown getCooldown(Player p) {
		for (int i = 0; i < clist.size(); i++) {
			Cooldown c = clist.get(i);
			if (c.getPlayer().getUniqueId().compareTo(p.getUniqueId()) == 0) {
				return c;
			}
		}
		return null;
	}

	public static void addCooldown(Player p, int ticks) {
		Cooldown c = new Cooldown(p, ticks);
		clist.add(c);
	}
}
