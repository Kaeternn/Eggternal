package me.kaeternn.eggternal;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import me.kaeternn.eggternal.commands.EGTCommand;
import me.kaeternn.eggternal.listeners.EGTListener;

public class Eggternal extends JavaPlugin {
    public static Eggternal plugin;
    public boolean debug;
    public boolean isSnifferEnabled = true;
    public boolean isTurtleEnabled = true;
    public boolean isFrogEnabled = true;
    private List<Material> blockList = new ArrayList<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;

        updateConfig();
        loadConfig();

        getServer().getPluginManager().registerEvents(new EGTListener(plugin), plugin);
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
        blockList.clear();

        debug = getConfig().getBoolean("debug");
        if (debug) getLogger().info("Debug mode enabled.");

        isFrogEnabled = getConfig().getBoolean("frog");
        if (debug) getLogger().info("Frogs eggs hatching prevent : " + isFrogEnabled);
        isSnifferEnabled = getConfig().getBoolean("sniffer");
        if (debug) getLogger().info("Sniffers eggs hatching prevent : " + isSnifferEnabled);

        for (String block : getConfig().getStringList("block-list")) {
            Material material = Material.getMaterial(block.toUpperCase());

            if (material == null) getLogger().warning("Invalid material in block-list: " + block + ", it was ignored.");
            else {
                blockList.add(material);
                if (debug) getLogger().info(material.name() + " registered in block list.");
            }
        }

        if (blockList.isEmpty()) getLogger().severe("block-list is empty, this plugin won't do anything, please add blocks in it and use /egt reload.");

        saveConfig();
    }

    public boolean isBlockListed(Material block) { return blockList.contains(block); }
}
