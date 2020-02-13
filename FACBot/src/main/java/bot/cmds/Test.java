package bot.cmds;

import bot.Constants;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.*;

public class Test extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.equalsIgnoreCase(Constants.prefix + "ping")) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Pong!").queue();
        }
    }
}
