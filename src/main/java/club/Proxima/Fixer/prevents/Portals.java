package club.Proxima.Fixer.prevents;

import lombok.RequiredArgsConstructor;
import club.Proxima.Fixer.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

import org.bukkit.event.player.PlayerMoveEvent;

@RequiredArgsConstructor
public class Portals implements Listener {
    private final Main plugin;

    @EventHandler
    private void onPortal(PlayerPortalEvent evt) {
        if (plugin.getConfig().getBoolean("PreventPortalTraps")) {
            Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {
                Location l = evt.getPlayer().getLocation();
                if (l.getWorld().getBlockAt(l).getType() == Material.PORTAL) {
                    evt.getPlayer().teleport(new Location(evt.getPlayer().getWorld(), evt.getPlayer().getLocation().getX()+2, evt.getPlayer().getLocation().getY(), evt.getPlayer().getLocation().getZ()+2));
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        evt.getPlayer().teleport(new Location(evt.getPlayer().getWorld(), evt.getPlayer().getLocation().getX()-2, evt.getPlayer().getLocation().getY(), evt.getPlayer().getLocation().getZ()-2));
                    }
                }, 10L);    
                }
            }, 300L);
        }
    }
}
