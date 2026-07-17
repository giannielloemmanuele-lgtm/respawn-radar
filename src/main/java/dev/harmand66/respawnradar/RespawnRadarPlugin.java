package dev.harmand66.respawnradar;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * RespawnRadar - your compass points to where you died.
 *
 * When a player dies, their death location is stored. On respawn,
 * their compass is pointed at that location so they can walk back
 * and recover their items.
 */
public final class RespawnRadarPlugin extends JavaPlugin {

    /** Last death location per player (kept in memory for this session). */
    private final Map<UUID, Location> deathLocations = new HashMap<>();

    @Override
    public void onEnable() {
        // Create config.yml with default values if it doesn't exist yet
        saveDefaultConfig();

        // Register the death/respawn listener
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);

        // Register the /deathpoint command
        getCommand("deathpoint").setExecutor(new DeathPointCommand(this));

        getLogger().info("RespawnRadar enabled. Compasses at the ready!");
    }

    @Override
    public void onDisable() {
        deathLocations.clear();
        getLogger().info("RespawnRadar disabled.");
    }

    /** Stores the location where a player just died. */
    public void setDeathLocation(UUID playerId, Location location) {
        deathLocations.put(playerId, location.clone());
    }

    /** Returns the last known death location of a player, or null if none. */
    public Location getDeathLocation(UUID playerId) {
        return deathLocations.get(playerId);
    }
}
