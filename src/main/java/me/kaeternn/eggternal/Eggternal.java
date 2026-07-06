package me.kaeternn.eggternal;

import org.bukkit.plugin.java.JavaPlugin;

import me.kaeternn.eggternal.commands.EGTCommand;

public class Eggternal extends JavaPlugin {
    public static Eggternal plugin;
    public boolean debug;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;

        updateConfig();
        loadConfig();

        //getServer().getPluginManager().registerEvents(new Listener(plugin), plugin);
        registerCommand("egt", new EGTCommand(plugin));
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
