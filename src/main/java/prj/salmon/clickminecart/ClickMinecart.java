package prj.salmon.clickminecart;

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

import java.util.Objects;

public final class ClickMinecart extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location loc = Objects.requireNonNull(event.getClickedBlock()).getLocation();

        if (!player.isInsideVehicle() && player.getInventory().getItemInMainHand().getType() == Material.AIR
                && (loc.getBlock().getType().equals(Material.RAIL) || loc.getBlock().getType().equals(Material.POWERED_RAIL)) &&
                event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            event.setCancelled(true);
            World world = player.getWorld();
            Entity minecart = world.spawnEntity(loc, EntityType.MINECART);
            minecart.addScoreboardTag("cminecart");
            minecart.addPassenger(player);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onVehicleLeave (VehicleExitEvent e ) {
        Player player = (Player) e.getExited();
        Vehicle vehicle = e.getVehicle();
        if( vehicle.getScoreboardTags().contains("cminecart")){
            Location loc = player.getLocation();
            loc.setY(loc.getY() + 0.5);
            vehicle.removePassenger(player);
            vehicle.remove();
            player.teleport(loc);
        }

    }
}
