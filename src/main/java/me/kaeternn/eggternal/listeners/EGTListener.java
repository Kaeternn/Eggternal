package me.kaeternn.eggternal.listeners;

import org.bukkit.event.Listener;

import me.kaeternn.eggternal.Eggternal;

public class EGTListener implements Listener {
    private Eggternal plugin;
    
    public EGTListener(Eggternal plugin) {
        this.plugin = plugin;

        if (plugin.debug) plugin.getLogger().info("Listener registered.");
    }
}