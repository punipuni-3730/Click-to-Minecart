package prj.salmon.clickminecart;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClickMinecart extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        Block block = event.getClickedBlock();
        if (block == null || (block.getType() != Material.RAIL && block.getType() != Material.POWERED_RAIL)) {
            return;
        }

        Player player = event.getPlayer();

        if (!player.isInsideVehicle() && player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            event.setCancelled(true);
            World world = player.getWorld();
            Entity minecart = world.spawnEntity(block.getLocation(), EntityType.MINECART);
            minecart.addScoreboardTag("cminecart");
            minecart.addPassenger(player);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onVehicleLeave (VehicleExitEvent e ) {
        Vehicle vehicle = e.getVehicle();
        if (e.getExited() instanceof Player player && vehicle.getScoreboardTags().contains("cminecart")) {
            Location loc = player.getLocation();
            loc.setY(loc.getY() + 0.5);
            // Wait until the exit has completed before removing the vehicle.
            getServer().getScheduler().runTask(this, () -> {
                if (vehicle.getPassengers().contains(player)) {
                    return;
                }
                vehicle.remove();
                if (player.isOnline() && !player.isInsideVehicle()) {
                    player.teleport(loc);
                }
            });
        }

    }
}
