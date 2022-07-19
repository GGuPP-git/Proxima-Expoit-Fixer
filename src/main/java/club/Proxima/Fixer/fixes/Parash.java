package club.Proxima.Fixer.fixes;

import com.comphenix.protocol.PacketType;
import org.bukkit.ChatColor;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.EnumWrappers;
import io.papermc.lib.PaperLib;
import club.Proxima.Fixer.Main;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Parash {
    public static void protocolLibWrapper(Main plugin) {
        final Map<UUID, Integer> levels = new HashMap<>();
        final Map<UUID, Integer> boatLevels = new HashMap<>();
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();


        if (plugin.getConfig().getBoolean("PreventPacketFly")) {
            protocolManager.addPacketListener(
                    new PacketAdapter(plugin, ListenerPriority.HIGHEST, PacketType.Play.Client.TELEPORT_ACCEPT) {
                        @Override
                        public void onPacketReceiving(PacketEvent event) {
                            if (event.isPlayerTemporary()) {
                                return;
                            }

                            Player e = event.getPlayer();
                            Location l = e.getLocation();
                            int relX = l.getBlockX() & 0xF; // keep the 4 least significant bits, range 0-15
                            int relY = l.getBlockY();
                            int relZ = l.getBlockZ() & 0xF;

                            Chunk chunk = PaperLib.getChunkAtAsync(l, false).join();
                            if (chunk != null
                                    && !e.isGliding()
                                    && !e.isInsideVehicle()) {
                                Integer level = levels.get(e.getUniqueId());
                                if (level != null) {
                                    if (level > plugin.getConfig().getInt("MaxTeleportPacketsPer10Seconds")) {
                                        event.setCancelled(true);
                                        if (true) {
                                            Bukkit.getScheduler().runTask(this.plugin, new Runnable() { 
                                                public void run() { 
                                                    e.kickPlayer(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("PacketFlyKikcMessage")));
                                                }
                                            });
                                        }
                                        if (plugin.getConfig().getBoolean("LogPacketFlyEvents")) {
                                            plugin.getLogger().warning(e.getName() + " prevented from packetflying");
                                        }
                                    } else {
                                        levels.merge(e.getUniqueId(), 1, Integer::sum);
                                        Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> levels.put(e.getUniqueId(), levels.get(e.getUniqueId()) - 1), 200L);
                                    }
                                } else {
                                    levels.put(e.getUniqueId(), 1);
                                    Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> levels.put(e.getUniqueId(), levels.get(e.getUniqueId()) - 1), 200L);
                                }
                            }
                        }
                    });
        }
    }
}
