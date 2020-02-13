package bot.cmds;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Shutdown extends ListenerAdapter {


    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.equalsIgnoreCase("fakshut") && event.getAuthor().getIdLong() == 525126007330570259l) {
            event.getMessage().delete().queue();
            event.getJDA().shutdown();
            System.exit(0);
        }
    }
}
