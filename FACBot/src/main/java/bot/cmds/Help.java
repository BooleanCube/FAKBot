package bot.cmds;

import bot.Constants;
import com.sun.deploy.util.BlackList;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class Help extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.equalsIgnoreCase(Constants.prefix + "help")) {
            EmbedBuilder info = new EmbedBuilder();
            info.setColor(Color.BLACK);
            info.setTitle("Info:");
            info.setDescription("Bot command prefix is `fak`\n" +
                    "FAK stands for __F__acts __A__nd __K__nowledge\n" +
                    "My main purpose is to ask questions in `#spit-faks` and to recieve answer in exchange for coins.\n" +
                    "All questions are multiple choice and there **is** a guessing penalty!!!");
            EmbedBuilder instructions = new EmbedBuilder();
            instructions.setColor(Color.BLACK);
            instructions.setTitle("Trivia Instructions:");
            instructions.setDescription("You will get questions in `#spit-faks` at random points in time. You can try answering to them but keep in mind there are guessing penalties.\n" +
                    "You answer by using one of the options from the multiple choice options given below the question.\n" +
                    "You will be rewarded and punished based on the difficuly for each question.\n" +
                    "You can also use `fakask`, one of my commands, in the `#spit-faks` channel alone to generate a question instead of waiting all day long.");
            EmbedBuilder commands = new EmbedBuilder();
            commands.setColor(Color.BLACK);
            commands.setTitle("Main Commands:");
            commands.setDescription("`fakbal`: check your coin balance\n" +
                    "`fakleaderboard`: check your server leaderboard\n" +
                    "`fakhelp`: diplays this message\n" +
                    "`fakask`: asks a random question\n" +
                    "`fakgive [amount] [person]`: gives *amount* coins to *person*\n" +
                    "`fakping`: to check whether the bot is online or not\n" +
                    "`fakmeme`: gives you a meme");
            EmbedBuilder cmds2 = new EmbedBuilder();
            cmds2.setColor(Color.BLACK);
            cmds2.setTitle("Store Commands:");
            cmds2.setDescription("`fakstore`: To see what is available to buy\n" +
                    "`fakbuy [item]`: To buy *item*\n");
            event.getChannel().sendMessage(info.build()).queue();
            event.getChannel().sendMessage(instructions.build()).queue();
            event.getChannel().sendMessage(commands.build()).queue();
            event.getChannel().sendMessage(cmds2.build()).queue();
        }
    }
}
