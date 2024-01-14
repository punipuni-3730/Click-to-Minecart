package prj.salmon.clickminecart;

import org.bukkit.Bukkit;
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
import org.bukkit.event.block.Action;

public final class ClickMinecart extends JavaPlugin implements Listener {

    public void onEnable(){
        getServer().getPluginManager().registerEvents(this,this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Location loc = event.getClickedBlock().getLocation();
        if (loc.getBlock().getType().equals(Material.RAIL)) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                event.getClickedBlock().breakNaturally();
        } else {
                Player player = event.getPlayer();
                World world = Bukkit.getServer().getWorld("world");
                Entity minecart = world.spawnEntity(loc, EntityType.MINECART);
                minecart.addPassenger(player);
            }
        }
    }
}
