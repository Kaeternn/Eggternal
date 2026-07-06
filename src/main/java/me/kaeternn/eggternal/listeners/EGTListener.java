package me.kaeternn.eggternal.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockGrowEvent;

import me.kaeternn.eggternal.Eggternal;

public class EGTListener implements Listener {
    private Eggternal plugin;
    private List<Material> eggList;
    
    public EGTListener(Eggternal plugin) {
        this.plugin = plugin;
        this.eggList = List.of(Material.FROGSPAWN, Material.SNIFFER_EGG);

        if (plugin.debug) plugin.getLogger().info("Listener registered.");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEggCrack(BlockGrowEvent event) {
        if (event.isCancelled()) return;
        if (!ConditionChecker(event.getBlock().getType(), 
            event.getBlock().getRelative(BlockFace.DOWN).getType()))
                return;

        if (plugin.debug) plugin.getLogger().info(event.getBlock().getType().name() + " prevented from cracking on " + 
            event.getBlock().getLocation().blockX() + ", " + 
            event.getBlock().getLocation().blockY() + ", " + 
            event.getBlock().getLocation().blockZ());

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEggHatch(BlockFadeEvent event) {
        if (event.isCancelled()) return;
        if (!ConditionChecker(event.getBlock().getType(), 
            event.getBlock().getRelative(BlockFace.DOWN).getType()))
                return;

        if (plugin.debug) plugin.getLogger().info(event.getBlock().getType().name() + " prevented from hatching on " + 
            event.getBlock().getLocation().blockX() + ", " + 
            event.getBlock().getLocation().blockY() + ", " + 
            event.getBlock().getLocation().blockZ());
            
        event.setCancelled(true);
    }

    private boolean ConditionChecker(Material egg, Material block) {
        if (!eggList.contains(egg)
            || !plugin.isBlockListed(block))
                return false;

        return true;
    }
}