package bot.cmds;
import bot.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Balance extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        String[] mssg = msg.split(" ");
        if (mssg.length > 1 && mssg[0].equalsIgnoreCase(Constants.prefix + "bal")) {
            String id = "";
            id = FindUser.getanId((mssg[1]));
            try {
                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\samch\\Desktop\\currency\\" + event.getGuild().getId() + "\\users.txt"));
                String input;
                while ((input = br.readLine()) != null) {
                    if (input.substring(0, input.indexOf(' ')).equals(id)) {
                        String person = FindUser.findUser(event.getGuild(), Long.parseLong(input.substring(0, input.indexOf(' '))));
                        EmbedBuilder e = new EmbedBuilder();
                        e.setColor(Color.YELLOW);
                        e.setTitle(person + "'s Balance");
                        e.setDescription(person + " has **" + input.substring(input.indexOf(' ') + 1) + "** coins.");
                        event.getChannel().sendMessage(e.build()).queue();
                        break;
                    }
                }
            } catch (IOException ex) {
                /* Do absolutely nothing I swear */
            }
        } else if (msg.equalsIgnoreCase(Constants.prefix + "bal")) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\samch\\Desktop\\currency\\" + event.getGuild().getId() + "\\users.txt"));
                String input;
                while ((input = br.readLine()) != null) {
                    if (input.substring(0, input.indexOf(' ')).equals(event.getAuthor().getId())) {
                        String person = FindUser.findUser(event.getGuild(), Long.parseLong(input.substring(0, input.indexOf(' '))));
                        EmbedBuilder e = new EmbedBuilder();
                        e.setColor(Color.YELLOW);
                        e.setTitle(person + "'s Balance");
                        e.setDescription("You have **" + input.substring(input.indexOf(' ') + 1) + "** coins.");
                        event.getChannel().sendMessage(e.build()).queue();
                        break;
                    }
                }
            } catch (IOException ex) {
                /* Do absolutely nothing I swear */
            }
        }
    }
}
