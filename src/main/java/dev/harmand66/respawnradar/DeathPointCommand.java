package dev.harmand66.respawnradar;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * /deathpoint - shows your last death coordinates, the distance to them,
 * and re-points your compass at that location.
 */
public final class DeathPointCommand implements CommandExecutor {

    private final RespawnRadarPlugin plugin;

    public DeathPointCommand(RespawnRadarPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Only players can use this command.", NamedTextColor.RED));
            return true;
        }

        Location deathLocation = plugin.getDeathLocation(player.getUniqueId());
        if (deathLocation == null) {
            player.sendMessage(Component.text(
                    "You haven't died yet this session. Lucky you!", NamedTextColor.GREEN));
            return true;
        }

        player.sendMessage(Component.text("☠ Last death: ", NamedTextColor.GRAY)
                .append(Component.text(
                        "X: " + deathLocation.getBlockX()
                                + "  Y: " + deathLocation.getBlockY()
                                + "  Z: " + deathLocation.getBlockZ(),
                        NamedTextColor.GOLD))
                .append(Component.text(" (" + deathLocation.getWorld().getName() + ")",
                        NamedTextColor.GRAY)));

        // Distance only makes sense if the player is in the same world
        if (player.getWorld().equals(deathLocation.getWorld())) {
            int distance = (int) player.getLocation().distance(deathLocation);
            player.sendMessage(Component.text("📏 Distance: " + distance + " blocks",
                    NamedTextColor.AQUA));
            player.setCompassTarget(deathLocation);
            player.sendMessage(Component.text("🧭 Compass updated.", NamedTextColor.AQUA));
        } else {
            player.sendMessage(Component.text(
                    "You're in a different world right now.", NamedTextColor.YELLOW));
        }

        return true;
    }
}
