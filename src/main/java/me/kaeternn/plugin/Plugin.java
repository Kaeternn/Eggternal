package me.kaeternn.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
    public static Plugin plugin;
    public boolean debug;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;

        updateConfig();
        loadConfig();

        //getServer().getPluginManager().registerEvents(new Listener(plugin), plugin);
        //registerCommand("plugin", new PluginCommand(plugin));
    }

    private void updateConfig() {
        switch (getConfig().getString("version", "1.0.0")) {
            default:
                break;
        }
        
        saveConfig();
    }

    public void loadConfig() {
        reloadConfig();

        debug = getConfig().getBoolean("debug");
        if (debug) getLogger().info("Debug mode enabled.");

        saveConfig();
    }
}
