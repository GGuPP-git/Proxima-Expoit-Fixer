package club.Proxima.Fixer.prevents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.Chunk;
import lombok.RequiredArgsConstructor;

import club.Proxima.Fixer.Main;

@RequiredArgsConstructor
public class test implements Listener {
    private final Main plugin;
    
	@EventHandler
    public void onLoad(ChunkLoadEvent e){
        if(e.isNewChunk()){
            e.getChunk().isCancelled(true);
            plugin.getLogger().warning("unloaded!!!");
        }
    }
}