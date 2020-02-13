package bot.cmds;

import bot.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DashBoard extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.equalsIgnoreCase(Constants.prefix + "lb") || msg.equalsIgnoreCase(Constants.prefix + "leaderboard")) {
            try {
                File f = new File("C:\\Users\\samch\\Desktop\\currency\\"+event.getGuild().getId()+"\\users.txt");
                StringBuilder sb = new StringBuilder();
                String input = "";
                EmbedBuilder e = new EmbedBuilder();
                e.setColor(Color.BLUE);
                e.setTitle(event.getGuild().getName() + " leaderboard");
                HashMap<Integer, Long> scoresId = new HashMap<Integer, Long>();
                String[] emojis = {":first_place:", ":second_place:", ":third_place:"};
                String body = "";
                BufferedReader br = new BufferedReader(new FileReader(f));
                while((input = br.readLine()) != null){
                    String[] in = input.split(" ");
                    int current = Integer.parseInt(in[1]);
                    String currentperson = in[0];
                    scoresId.put(current, Long.parseLong(currentperson));
                }
                br.close();
                ArrayList<Integer> scores = new ArrayList();
                for(Member m : event.getGuild().getMembers()) {
                    BufferedReader bf = new BufferedReader(new FileReader(f));
                    while((input = bf.readLine()) != null) {
                        String[] in = input.split(" ");
                        if(!scores.contains(Integer.parseInt(in[1]))) {
                            scores.add(Integer.parseInt(in[1]));
                        }
                    }
                    bf.close();
                }
                Collections.sort(scores);
                Collections.reverse(scores);
                int num;
                for(int i=0; i<3; i++) {
                    try {
                        body += emojis[i] + " " + FindUser.findUser(event.getGuild(), scoresId.get(scores.get(i))) + ": " + FindUser.getCoins(event.getGuild(), scoresId.get(scores.get(i))) + "\n";
                    } catch(Exception e234) {
                        event.getChannel().sendMessage("Leader Board not filled yet!").queue();
                        return;
                    }
                }
                outer:
                for(Member m : event.getGuild().getMembers()) {
                    BufferedReader br2 = new BufferedReader(new FileReader(f));
                    while((input = br2.readLine()) != null) {
                        String[] in = input.split(" ");
                        if(in[0].equalsIgnoreCase(event.getAuthor().getId())) {
                            num = FindUser.findIndexInt(scores, Integer.parseInt(in[1])) + 1;
                            body += "`#" + num + "` " + event.getAuthor().getName();
                            break outer;
                        }
                    }
                    br2.close();
                }
                e.setDescription(body);
                event.getChannel().sendMessage(e.build()).queue();
            } catch(IOException e) {

            }
        }
    }
}
