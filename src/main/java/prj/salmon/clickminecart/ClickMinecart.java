package prj.salmon.clickminecart;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClickMinecart extends JavaPlugin implements Listener {

    public void onEnable(){
        getServer().getPluginManager().registerEvents(this,this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteractEvent(PlayerInteractEvent event){
        Location loc = event.getClickedBlock().getLocation();
        if(loc.getBlock().getType().equals(Material.RAIL)){
            World world = org.bukkit.Bukkit.getServer().getWorld("world");
            Entity minecart = world.spawnEntity(loc, EntityType.MINECART);
            Player player = event.getPlayer();
            minecart.addPassenger(player);
        }
    }


}
