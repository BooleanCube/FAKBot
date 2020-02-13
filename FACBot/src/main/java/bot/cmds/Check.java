package bot.cmds;

import bot.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.io.*;

public class Check extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        boolean bruh = false;
        String[] words = Ask.notanswer.split(",");
        String w = event.getMessage().getContentRaw();
        for(String str : words) {
            if(str.equalsIgnoreCase(w)) {
                bruh = true;
            }
        }
        if(Ask.flag && event.getChannel().getIdLong() == 676142520807194641l) {
            String msg = event.getMessage().getContentRaw();
            if(Ask.answer.equalsIgnoreCase(msg) || bruh) {
                if(Ask.answer.equalsIgnoreCase(msg.toLowerCase()) && !event.getAuthor().isBot()) {
                    Ask.flag = false;
                    try{
                        File f = new File("C:\\Users\\samch\\Desktop\\currency\\"+event.getGuild().getId()+"\\users.txt");
                        StringBuilder sb = new StringBuilder();
                        BufferedReader br = new BufferedReader(new FileReader(f));
                        String input = "";
                        if(Ask.difficulty.equalsIgnoreCase("easy")) {
                            while((input = br.readLine()) != null){
                                if (input.substring(0, input.indexOf(' ')).equals(event.getAuthor().getId())){
                                    int x = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                                    x += 100;
                                    sb.append(input, 0, input.indexOf(' ') + 1).append(x).append('\n');
                                } else sb.append(input).append('\n');
                            }
                            EmbedBuilder e = new EmbedBuilder();
                            e.setColor(Color.GREEN);
                            e.setTitle("Correct!");
                            e.setDescription("`" + event.getAuthor().getName() + "` has been rewarded **100** coins.");
                            event.getChannel().sendMessage(e.build()).queue();
                        } else if(Ask.difficulty.equalsIgnoreCase("medium")) {
                            while((input = br.readLine()) != null){
                                if (input.substring(0, input.indexOf(' ')).equals(event.getAuthor().getId())){
                                    int x = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                                    x += 250;
                                    sb.append(input, 0, input.indexOf(' ') + 1).append(x).append('\n');
                                }else sb.append(input).append('\n');
                            }
                            EmbedBuilder e = new EmbedBuilder();
                            e.setColor(Color.GREEN);
                            e.setTitle("Correct!");
                            e.setDescription("`" + event.getAuthor().getName() + "` has been rewarded **250** coins.");
                            event.getChannel().sendMessage(e.build()).queue();
                        } else if(Ask.difficulty.equalsIgnoreCase("hard")) {
                            while((input = br.readLine()) != null){
                                if (input.substring(0, input.indexOf(' ')).equals(event.getAuthor().getId())){
                                    int x = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                                    x += 500;
                                    sb.append(input, 0, input.indexOf(' ') + 1).append(x).append('\n');
                                }else sb.append(input).append('\n');
                            }
                            EmbedBuilder e = new EmbedBuilder();
                            e.setColor(Color.GREEN);
                            e.setTitle("Correct!");
                            e.setDescription("`" + event.getAuthor().getName() + "` has been rewarded **500** coins.");
                            event.getChannel().sendMessage(e.build()).queue();
                        }
                        FileWriter fw = new FileWriter(f);
                        fw.write(sb.toString());
                        fw.flush();
                    }catch(IOException e){
                        /* do nothing with exception */
                    }
                } else if(event.getChannel().getIdLong() == 676142520807194641l && !event.getAuthor().isBot() && !event.getMessage().getContentRaw().startsWith(Constants.prefix)) {
                    EmbedBuilder e = new EmbedBuilder();
                    e.setColor(Color.RED);
                    e.setTitle("Wrong!");
                    try {
                        File f = new File("C:\\Users\\samch\\Desktop\\currency\\"+event.getGuild().getId()+"\\users.txt");
                        StringBuilder sb = new StringBuilder();
                        BufferedReader br = new BufferedReader(new FileReader(f));
                        String input = "";
                        if(Ask.difficulty.equalsIgnoreCase("easy")) {
                            while((input = br.readLine()) != null){
                                if (input.substring(0, input.indexOf(' ')).equals(event.getAuthor().getId())){
                                    int x = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                                    x -= 50;
                                    sb.append(input, 0, input.indexOf(' ') + 1).append(x).append('\n');
                                }else sb.append(input).append('\n');
                            }
                            e.setDescription("`" + event.getAuthor().getName() + "` lost **50** coins.");
                            event.getChannel().sendMessage(e.build()).queue();
                            FileWriter fw = new FileWriter(f);
                            fw.write(sb.toString());
                            fw.flush();
                        }
                        if(Ask.difficulty.equalsIgnoreCase("medium")) {
                            while((input = br.readLine()) != null){
                                if (input.substring(0, input.indexOf(' ')).equals(event.getAuthor().getId())){
                                    int x = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                                    x -= 100;
                                    sb.append(input, 0, input.indexOf(' ') + 1).append(x).append('\n');
                                }else sb.append(input).append('\n');
                            }
                            e.setDescription("`" + event.getAuthor().getName() + "` lost **100** coins.");
                            event.getChannel().sendMessage(e.build()).queue();
                            FileWriter fw = new FileWriter(f);
                            fw.write(sb.toString());
                            fw.flush();
                        }
                        if(Ask.difficulty.equalsIgnoreCase("hard")) {
                            while((input = br.readLine()) != null){
                                if (input.substring(0, input.indexOf(' ')).equals(event.getAuthor().getId())){
                                    int x = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                                    x -= 200;
                                    sb.append(input, 0, input.indexOf(' ') + 1).append(x).append('\n');
                                }else sb.append(input).append('\n');
                            }
                            e.setDescription("`" + event.getAuthor().getName() + "` lost **200** coins.");
                            event.getChannel().sendMessage(e.build()).queue();
                            FileWriter fw = new FileWriter(f);
                            fw.write(sb.toString());
                            fw.flush();
                        }
                    } catch(IOException ex) {
                        //nothing
                    }
                }
            }
        }
    }
}