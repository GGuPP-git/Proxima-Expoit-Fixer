package club.Proxima.Fixer;


//Other func
import club.Proxima.Fixer.fixes.*;

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
        pluginManager = getServer().getPluginManager();

        //here u must write name func from fixes folder (new namefunc(this))
        load();

        if (getConfig().getBoolean("DisableAllProtocolLib")) {
            getLogger().info("You specified to disable all ProtocolLib patches.");
        } else {
            if (getServer().getPluginManager().getPlugin("ProtocolLib") != null) {
                getLogger().info("Detected ProtocolLib!");
                Parash.protocolLibWrapper(this);
            } else {
                getLogger().warning("Did not detect ProtocolLib, disabling packet patches");
                Bukkit.getScheduler().runTaskTimer(this, () -> getLogger().warning("ProtocolLib plugin not detected, many gamebreaking exploits will not be patched. Download at https://www.spigotmc.org/resources/protocollib.1997/"), 200L, 1200L);
            }
        }
    }    


    private void load(Listener... list) {
        pluginManager.registerEvents(this, this);
        for (Listener listener : list) {
            pluginManager.registerEvents(listener, this);
            getLogger().info("Loaded class " + listener.getClass().getSimpleName());
        }
    }
}

