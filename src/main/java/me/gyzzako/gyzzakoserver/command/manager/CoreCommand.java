package me.gyzzako.gyzzakoserver.command.manager;

import me.gyzzako.gyzzakoserver.utils.DataUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Core command that has subcommands within it. Like /[coreCommand] [subCommand]
 */
class CoreCommand extends Command {

    private final List<ISubCommand> subcommands;
    private final ICommandLister commandLister;

    public CoreCommand(final String name, final String description, final String usageMessage,
                       @Nullable final ICommandLister commandLister, final List<String> aliases,
                       final List<ISubCommand> subCommands) {
        super(name, description, usageMessage, aliases);

        this.subcommands = subCommands;
        this.commandLister = commandLister != null ? commandLister : DefaultCommandLister.INSTANCE;
    }

    public List<ISubCommand> getSubCommands() {
        return subcommands;
    }

    @Override
    public boolean execute(@NotNull final CommandSender sender, @NotNull final String commandLabel, final String[] args) {

        if (args.length > 0) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName()) ||
                        (getSubCommands().get(i).getAliases() != null && getSubCommands().get(i).getAliases().contains(args[0]))) {
                    getSubCommands().get(i).perform(sender, args);
                }
            }
        } else {
            commandLister.displayCommandList(sender, subcommands);
        }

        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull final CommandSender sender, @NotNull final String alias, final String[] args) throws IllegalArgumentException {
        if (args.length == 1) { // <coreCommand> <subCommand> <args>
            final List<String> subcommandsArguments = new ArrayList<>();

            // subcommand autocomplete
            for (int i = 0; i < getSubCommands().size(); i++) {
                subcommandsArguments.add(getSubCommands().get(i).getName());
            }

            return subcommandsArguments;
        } else if (args.length >= 2) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    final List<String> subCommandArgs = getSubCommands().get(i).getSubcommandArguments((Player) sender, args);

                    return DataUtils.isNotEmpty(subCommandArgs) ? subCommandArgs : Collections.emptyList();
                }
            }
        }

        return Collections.emptyList();
    }
}