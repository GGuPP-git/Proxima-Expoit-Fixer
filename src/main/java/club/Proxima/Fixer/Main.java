package club.Proxima.Fixer;


//Other func
import club.Proxima.Fixer.patches.*;
import club.Proxima.Fixer.prevents.*;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.ShulkerBox;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.meta.SpawnEggMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {
    public PluginManager pluginManager;

    @Override
    public void onEnable() {
        new Metrics(this, 15298);
        pluginManager = getServer().getPluginManager();
        saveDefaultConfig();
        //here u must write name func from fixes folder (new namefunc(this))
        load(new test(this));

        if (getServer().getPluginManager().getPlugin("ProtocolLib") != null) {
            Protocol.protocolLibWrapper(this);
        } else {
            Bukkit.getScheduler().runTaskTimer(this, () -> getLogger().warning("Дегенерат, добавь протоколлиб"), 200L, 1200L);
        }
    } 

    private void load(Listener... list) {
        pluginManager.registerEvents(this, this);
        for (Listener listener : list) {
            pluginManager.registerEvents(listener, this);
            getLogger().info("Class successfully loaded: " + listener.getClass().getSimpleName());
        }
    }
}

