package prj.salmon.clickminecart;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClickMinecart extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location loc = event.getClickedBlock().getLocation();
        if (player.isInsideVehicle() == !true) {
            if (player.getItemInHand().getType() == Material.AIR) {
                if (loc.getBlock().getType().equals(Material.RAIL)) {
                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        World world = Bukkit.getServer().getWorld("world");
                        Entity minecart = world.spawnEntity(loc, EntityType.MINECART);
                        minecart.addPassenger(player);
                        return;
                    }
                }
            }
        }
    }
}

