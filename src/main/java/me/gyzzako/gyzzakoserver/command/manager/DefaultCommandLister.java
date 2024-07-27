package me.gyzzako.gyzzakoserver.command.manager;

import org.bukkit.command.CommandSender;

import java.util.List;

public class DefaultCommandLister implements ICommandLister {
    public static final DefaultCommandLister INSTANCE = new DefaultCommandLister();
    
    @Override
    public void displayCommandList(final CommandSender sender, final List<ISubCommand> subCommands) {
        sender.sendMessage("-------------------------------------");
        for (final ISubCommand subcommand : subCommands) {
            sender.sendMessage(subcommand.getSyntax() + " - " + subcommand.getDescription());
        }
        sender.sendMessage("-------------------------------------");
    }
}
