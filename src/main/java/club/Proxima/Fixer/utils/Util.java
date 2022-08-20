
package club.Proxima.Fixer.utils;

import io.papermc.lib.PaperLib;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import club.Proxima.Fixer.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class Util {
	private static Main plugin;

	public Util(Main plugin) {
		Util.plugin = plugin;
	}



	public static void crashPlayer(Player player) {
		for (int i = 0; i < 100; i++) {
			player.spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), Integer.MAX_VALUE, 1, 1, 1);
		}
	}



	public static int countBlockPerChunk(Chunk chunk, Material lookingFor) {
		int count = 0;
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 256; y++) {
					if (chunk.getBlock(x, y, z).getType() == lookingFor)
						count++;
				}
			}
		}
		return count;
	}

	public static void changeBlockInChunk(Chunk chunk, Material target, Material to) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 256; y++) {
					if (chunk.getBlock(x, y, z).getType() == target) {
						chunk.getBlock(x, y, z).setType(to);
					}
				}
			}
		}
	}
}