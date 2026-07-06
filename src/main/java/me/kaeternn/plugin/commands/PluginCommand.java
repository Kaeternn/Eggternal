package me.kaeternn.plugin.commands;

import java.util.ArrayList;
import java.util.List;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;

import me.kaeternn.plugin.Plugin;

public class PluginCommand implements BasicCommand  {
    private Plugin plugin;

    public PluginCommand(Plugin plugin) { this.plugin = plugin; }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        if (args.length == 0) {
            source.getSender().sendMessage("Usage : /plugin [reload/version]");
            return;
        }

        switch (args[0]) {
            case "reload":
                plugin.loadConfig();
                source.getSender().sendMessage("plugin reloaded.");
                break;
            case "version":
                source.getSender().sendMessage("plugin version " + plugin.getPluginMeta().getVersion());
                break;
        }
    }

    @Override
    public List<String> suggest(CommandSourceStack source, String[] args) {
        List<String> options = List.of("reload", "version");

        switch (args.length) {
            case 0:
                return options;
            case 1:
                String prefix = args[0];
                List<String> fetchedOptions = new ArrayList<>();

                for (String option : options)
                    if (option.startsWith(prefix)) fetchedOptions.add(option);

                return fetchedOptions;
            default:
                return List.of();
        }
    }
    
    @Override
    public String permission() {
        return "plugin.admin";
    }
}
