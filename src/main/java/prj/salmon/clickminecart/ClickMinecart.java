package prj.salmon.clickminecart;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.World;

import static org.bukkit.Bukkit.getPlayer;

public final class ClickMinecart extends JavaPlugin implements Listener {

    public void onEnable(){
        getServer().getPluginManager().registerEvents(this,this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        Location loc = e.getClickedBlock().getLocation();
        if(loc.getBlock().getType().equals(Material.RAIL)){
            World world = org.bukkit.Bukkit.getServer().getWorld("world");
            Entity minecart = world.spawnEntity(loc, EntityType.MINECART);

        }
    }


}
