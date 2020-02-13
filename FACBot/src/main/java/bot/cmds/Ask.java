package bot.cmds;

import bot.Constants;
import bot.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class Ask extends ListenerAdapter {

    public static String answer = "";
    public static boolean flag = false;
    public static String difficulty = "";
    public static String notanswer = "";

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(FindUser.hasRole(event.getMember().getRoles(), "Euler's Award") || FindUser.hasRole(event.getMember().getRoles(), "Avogadro's Role") || FindUser.hasRole(event.getMember().getRoles(), "Trivia Master")) {
            int random = ((int) (Math.random() * 1000)) % 25;
            if (random == 15 || (event.getMessage().getContentRaw().equalsIgnoreCase(Constants.prefix + "ask") && event.getChannel().getIdLong() == 676142520807194641l)) {
                if (System.currentTimeMillis() - Main.l >= 30000) {
                    event.getMessage().delete();
                    try {
                        URL url = new URL("https://opentdb.com/api.php?amount=1");
                        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                        String input = br.readLine().toLowerCase();
                        while (input.contains("&quot;")) {
                            input = input.substring(0, input.indexOf("&quot;")) + "" + input.substring(input.indexOf("&quot;") + 6);
                        }
                        while (input.contains("&#039;")) {
                            input = input.substring(0, input.indexOf("&#039;")) + "'" + input.substring(input.indexOf("&#039;") + 6);
                        }
                        while (input.contains("&amp;")) {
                            input = input.substring(0, input.indexOf("&amp;")) + "'" + input.substring(input.indexOf("&amp;") + 5);
                        }
                        String question = input.substring(input.indexOf("\"question\"") + 12, input.indexOf('?') + 1);
                        String correctAnswers = input.substring(input.indexOf("\"correct_answer\"") + 17, input.indexOf("\"incorrect_answers\"") - 1);
                        correctAnswers = correctAnswers.substring(1, correctAnswers.length() - 1);
                        String incorrectAnswers = input.substring(input.indexOf("\"incorrect_answers\"") + 21);
                        incorrectAnswers = incorrectAnswers.substring(0, incorrectAnswers.indexOf(']'));
                        while (incorrectAnswers.contains("\"")) {
                            incorrectAnswers = incorrectAnswers.substring(0, incorrectAnswers.indexOf('\"')) + incorrectAnswers.substring(incorrectAnswers.indexOf('\"') + 1);
                        }
                        notanswer = incorrectAnswers;
                        System.out.println(incorrectAnswers);
                        String[] strings = incorrectAnswers.split(",");
                        ArrayList<String> ar = new ArrayList<String>();
                        String type = input.substring(input.indexOf("\"type\"") + 8);
                        type = type.substring(0, type.indexOf('\"'));
                        for (int i = 0; i < strings.length; i++) {
                            ar.add(strings[i]);
                        }
                        ar.add(correctAnswers);
                        Collections.shuffle(ar);
                        String difficulty = input.substring(input.indexOf("\"difficulty\"") + 14);
                        difficulty = difficulty.substring(0, difficulty.indexOf(',') - 1);
                        String category = input.substring(input.indexOf("\"category\"") + 12);
                        category = category.substring(0, category.indexOf('\"'));
                        if (event.getMessage().getContentRaw().equalsIgnoreCase(Constants.prefix + "ask") && event.getChannel().getIdLong() == 676142520807194641l) {
                            Main.l = System.currentTimeMillis();
                        }
                        EmbedBuilder e = new EmbedBuilder();
                        answer = correctAnswers;
                        this.difficulty = difficulty;
                        e.setColor(Color.BLUE);
                        e.setTitle("Trivia Question");
                        if (ar.size() > 2) {
                            e.addField("Question", String.format("%s\n· %s\n· %s\n· %s\n· %s", question, ar.get(0), ar.get(1), ar.get(2), ar.get(3)), true);
                            e.addField("Category", category, true);
                            e.addField("Difficulty", difficulty, true);
                        } else {
                            e.addField("Question", String.format("%s\n· %s\n· %s", question, ar.get(0), ar.get(1)), true);
                            e.addField("Category", category, true);
                            e.addField("Difficulty", difficulty, true);
                        }
                        TextChannel tc = event.getGuild().getTextChannelById(676142520807194641l);
                        tc.sendMessage(e.build()).queue();
                        flag = true;
                    } catch (IOException e) {
                        //nothing
                    }
                } else {
                    event.getChannel().sendMessage("**Patience!** You only just asked... *" + ((30000 - (System.currentTimeMillis() - Main.l)) / 1000) + "* seconds left.").queue();
                }
            }
        } else if(!(FindUser.hasRole(event.getMember().getRoles(), "Euler's Award") || FindUser.hasRole(event.getMember().getRoles(), "Avogadro's Role") || FindUser.hasRole(event.getMember().getRoles(), "Trivia Master")) && event.getMessage().getContentRaw().equalsIgnoreCase("fakask")) {
            event.getChannel().sendMessage("You don't have the perms to use this command. Try getting the Avogadro's Award Role from the store(Usage = `fakstore`) for more perms!").queue();
        }
    }
}