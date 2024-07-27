package me.gyzzako.gyzzakoserver.command.manager;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Interface for sub-command
 */
public interface ISubCommand {

    /**
     * @return The name of the subcommand
     */
    public String getName();

    /**
     * @return The aliases that can be used for this command
     */
    public List<String> getAliases();

    /**
     * @return A description of what the subcommand does
     */
    public String getDescription();

    /**
     * @return An example of how to use the subcommand
     */
    public String getSyntax();

    /**
     * @param sender The sender that ran the command
     * @param args   The args passed into the command when run
     */
    public void perform(CommandSender sender, String[] args);

    /**
     * @param player The player who ran the command
     * @param args   The args passed into the command when run
     * @return A list of arguments to be suggested for autocomplete
     */
    public List<String> getSubcommandArguments(Player player, String[] args);

}