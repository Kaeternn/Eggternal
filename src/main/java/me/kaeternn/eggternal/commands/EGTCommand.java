package me.kaeternn.eggternal.commands;

import java.util.ArrayList;
import java.util.List;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.kaeternn.eggternal.Eggternal;

public class EGTCommand implements BasicCommand  {
    private Eggternal plugin;

    public EGTCommand(Eggternal plugin) { this.plugin = plugin; }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        if (args.length == 0) {
            source.getSender().sendMessage("Usage : /egt [reload/version]");
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
