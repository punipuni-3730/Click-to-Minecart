package prj.salmon.clickminecart;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClickMinecart extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location loc = event.getClickedBlock().getLocation();

        if (!player.isInsideVehicle()) {
            if (player.getItemInHand().getType() == Material.AIR) {
                if (loc.getBlock().getType().equals(Material.RAIL) ||loc.getBlock().getType().equals(Material.POWERED_RAIL)) {
                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        World world = Bukkit.getServer().getWorld("world");
                        Entity minecart = world.spawnEntity(loc, EntityType.MINECART);
                        minecart.addScoreboardTag("cminecart");
                        minecart.addPassenger(player);
                        }
                    }
                }
            }
        }
    @EventHandler(ignoreCancelled = true)
    public void onVehicleLeave (VehicleExitEvent e ) {
        Player player = (Player) e.getExited();
        Vehicle vehicle = e.getVehicle();
        if( vehicle.getScoreboardTags().contains("cminecart")){
            Entity p = player;
            Location loc = p.getLocation();
            loc.setY(loc.getY() + 0.5);
            vehicle.removePassenger(player);
            vehicle.remove();
            p.teleport(loc);
        }

    }
}
