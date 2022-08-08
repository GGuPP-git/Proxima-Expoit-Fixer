package club.Proxima.Fixer.misc;

import club.Proxima.Fixer.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public class test implements Listener {

	private final Main plugin;

    public test(Main plugin) {
        this.plugin = plugin;
    }

	@EventHandler
	public void onPlayerInteractEntity (PlayerInteractEntityEvent event) {
    Entity entity = event.getRightClicked();
    Player player = event.getPlayer();

    float pitch = player.getLocation().getPitch();
    float yaw = player.getLocation().getYaw();

    if (entity instanceof AbstractHorse) {
        event.setCancelled(true);

        Location loc = player.getLocation();
        loc.setPitch(pitch);
        loc.setYaw(yaw);
        player.teleport(loc);

        AbstractHorse horse = (AbstractHorse) event.getRightClicked();
        player.sendMessage(horse.getName());
    }
}
}