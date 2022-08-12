package club.Proxima.Fixer.prevents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.Chunk;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.world.ChunkUnloadEvent;

import club.Proxima.Fixer.Main;

@RequiredArgsConstructor
public class test implements Listener {
    private final Main plugin;
    
	@EventHandler
	public void preventUnload(ChunkUnloadEvent e){
		
	}
}