package me.gyzzako.gyzzakoserver.command.manager;

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Functional interface to define how the listing of the subcommands on a core command works.
 */
@FunctionalInterface
public interface ICommandLister {

    /**
     * @param sender      The sender of the command
     * @param subCommands A list of all the subcommands you can display
     */
    void displayCommandList(CommandSender sender, List<ISubCommand> subCommands);
}
