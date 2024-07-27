package me.gyzzako.gyzzakoserver.command;

import me.gyzzako.gyzzakoserver.command.manager.ISubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class HelloCommand implements ISubCommand {
    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("hi");
    }

    @Override
    public String getDescription() {
        return "Says hello to the sender";
    }

    @Override
    public String getSyntax() {
        return "/gp hello <name>";
    }

    @Override
    public void perform(final CommandSender sender, final String[] args) {
        if (args.length > 1) {
            sender.sendMessage("Hello " + args[1]);
        } else {
            sender.sendMessage(ChatColor.RED + "You must provided the name");
            sender.sendMessage(ChatColor.YELLOW + "Example: " + ChatColor.RESET + "/gp hello arnaud");
        }
    }

    @Override
    public List<String> getSubcommandArguments(final Player player, final String[] args) {
        return Collections.emptyList();
    }
}
