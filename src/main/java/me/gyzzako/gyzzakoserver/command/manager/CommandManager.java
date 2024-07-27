package me.gyzzako.gyzzakoserver.command.manager;

import me.gyzzako.gyzzakoserver.GyzzakoServer;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Command manager to handle the creation and registration of commands.
 */
public class CommandManager {

    /**
     * @param plugin             The plugin instance
     * @param commandName        The name of the command
     * @param commandDescription Description of the command. This replaces putting it in plugin.yml
     * @param commandUsage       Usage of the command. This replaces putting it in plugin.yml
     * @param commandLister      The implementation of a command lister with a custom command listing
     * @param aliases            A String list of aliases
     * @param subCommandClasses  Class set of SubCommand for this core command
     */
    public static void createCoreCommand(final JavaPlugin plugin, final String commandName,
                                         final String commandDescription,
                                         final String commandUsage,
                                         @Nullable final ICommandLister commandLister,
                                         final List<String> aliases,
                                         final Set<Class<? extends ISubCommand>> subCommandClasses) throws NoSuchFieldException, IllegalAccessException {

        final List<ISubCommand> subCommands = new ArrayList<>();

        subCommandClasses.stream().map(subcommand -> {
            try {
                final Constructor<? extends ISubCommand> constructor = subcommand.getConstructor();
                return constructor.newInstance();
            } catch (final InstantiationException | IllegalAccessException | NoSuchMethodException |
                           InvocationTargetException e) {
                GyzzakoServer.getDefaultLogger().severe(
                        String.format("Error while trying to create %s sub command.", subcommand.getName())
                );
                e.printStackTrace();
            }
            return null;
        }).forEach(subCommand -> {
            if (subCommand != null) {
                subCommands.add(subCommand);
            }
        });

        final Field commandMapField = plugin.getServer().getClass().getDeclaredField("commandMap");
        commandMapField.setAccessible(true);
        final CommandMap commandMap = (CommandMap) commandMapField.get(plugin.getServer());
        commandMap.register(commandName, new CoreCommand(commandName, commandDescription, commandUsage, commandLister, aliases, subCommands));
    }


    /**
     * @param plugin             The plugin instance
     * @param commandName        The name of the command
     * @param commandDescription Description of the command. This replaces putting it in plugin.yml
     * @param commandUsage       Usage of the command. This replaces putting it in plugin.yml
     * @param commandLister      The implementation of a command lister with a custom command listing
     * @param subCommandClasses  Class set of SubCommand for this core command
     */
    public static void createCoreCommand(final JavaPlugin plugin, final String commandName,
                                         final String commandDescription,
                                         final String commandUsage,
                                         @Nullable final ICommandLister commandLister,
                                         final List<Class<? extends ISubCommand>> subCommandClasses) throws NoSuchFieldException, IllegalAccessException {
        createCoreCommand(plugin, commandName, commandDescription, commandUsage, commandLister, Collections.singletonList(""), new HashSet<>(subCommandClasses));
    }
}
