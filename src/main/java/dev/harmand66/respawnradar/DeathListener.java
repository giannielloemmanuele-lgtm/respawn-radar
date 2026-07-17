package dev.harmand66.respawnradar;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Listens for player deaths and respawns.
 * On death: remember the location. On respawn: point the compass there.
 */
public final class DeathListener implements Listener {

    private final RespawnRadarPlugin plugin;

    public DeathListener(RespawnRadarPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        plugin.setDeathLocation(player.getUniqueId(), player.getLocation());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Location deathLocation = plugin.getDeathLocation(player.getUniqueId());
        if (deathLocation == null) {
            return;
        }

        // Point the player's compass at the death location
        player.setCompassTarget(deathLocation);

        if (plugin.getConfig().getBoolean("show-coordinates", true)) {
            player.sendMessage(Component.text("☠ You died at ", NamedTextColor.GRAY)
                    .append(Component.text(
                            "X: " + deathLocation.getBlockX()
                                    + "  Y: " + deathLocation.getBlockY()
                                    + "  Z: " + deathLocation.getBlockZ(),
                            NamedTextColor.GOLD))
                    .append(Component.text(" (" + deathLocation.getWorld().getName() + ")",
                            NamedTextColor.GRAY)));
            player.sendMessage(Component.text(
                    "🧭 Your compass now points to your death location. Type /deathpoint anytime.",
                    NamedTextColor.AQUA));
        }
    }
}
