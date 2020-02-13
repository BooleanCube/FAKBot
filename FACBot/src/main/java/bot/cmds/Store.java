package bot.cmds;

import bot.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.io.*;

public class Store extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.equalsIgnoreCase(Constants.prefix + "store")) {
            EmbedBuilder store = new EmbedBuilder();
            store.setColor(Color.YELLOW);
            store.setTitle("Store:");
            String body = "Note: Usage to buy the items is `fakbuy [item]`. (Items being the bolded words towards the left)\n" +
                    "***Available Items:***\n\n";
            if(!event.getMember().getRoles().contains("R:Euler's Award(676906091183079446)")) {
                body += "-**Euler's Award Role**: `Price = 271828 coins`; The best role you can ever get. Great for flexing. Maybe some surprise perms???\n";
            }
            if(!event.getMember().getRoles().contains("R:Avogadro's Award(676905762735390762)")) {
                body += "-**Avogadro's Award Role**: `Price = 60221 coins`; Show that you are smart and flex your brain. This role could definitely help you!\n";
            }
            if(!event.getMember().getRoles().contains("R:Archimedes' Award(676905562776141857)")) {
                body += "-**Archimedes' Award Role**: `Price = 31416 coins`; On your way to become the smartest man known to the world! Buy this role to earn more honor!";
            }
            store.setDescription(body);
            event.getChannel().sendMessage(store.build()).queue();
        }
        if(msg.startsWith(Constants.prefix + "buy")) {
            String[] in = msg.split(" ");
            if(!msg.substring(7).equalsIgnoreCase("Euler's Award Role") && !msg.substring(7).equalsIgnoreCase("Archimedes' Award Role") && !msg.substring(7).equalsIgnoreCase("Avogadro's Award Role")) {
                event.getChannel().sendMessage("**" + msg.substring(7) + "** is not an item!").queue();
            }
            if(msg.substring(7).equalsIgnoreCase("Euler's Award Role")) {
                Role r = event.getGuild().getRolesByName("Euler's Award", true).get(0);
                if(event.getMember().getRoles().contains(r)) {
                    event.getChannel().sendMessage("You already have that role!").queue();
                    return;
                }
                if(FindUser.getCoins(event.getGuild(), event.getAuthor().getIdLong()) < 271828) {
                    event.getChannel().sendMessage("Not enough coins in your balance!").queue();
                } else {
                    try {
                        File f = new File("C:\\Users\\samch\\Desktop\\currency\\"+event.getGuild().getId()+"\\users.txt");
                        StringBuilder sb = new StringBuilder();
                        BufferedReader br = new BufferedReader(new FileReader(f));
                        String input = "";
                        while((input = br.readLine()) != null){
                            if (input.substring(0, input.indexOf(' ')).equals(event.getAuthor().getId())){
                                int x = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                                x -= 271828;
                                sb.append(input, 0, input.indexOf(' ') + 1).append(x).append('\n');
                            }else sb.append(input).append('\n');
                        }
                        FileWriter fw = new FileWriter(f);
                        fw.write(sb.toString());
                        fw.flush();
                    } catch(IOException e) {

                    }
                    event.getChannel().sendMessage("Congrats! You just received the **Euler's Award Role**!").queue();
                    event.getGuild().getController().addRolesToMember(event.getMember(), event.getJDA().getRoleById("676906091183079446")).queue();
                }
            }
            if(msg.substring(7).equalsIgnoreCase("Avogadro's Award Role")) {
                Role r = event.getGuild().getRolesByName("Avogadro's Award", true).get(0);
                if(event.getMember().getRoles().contains(r)) {
                    event.getChannel().sendMessage("You already have that role!").queue();
                    return;
                }
                if(FindUser.getCoins(event.getGuild(), event.getAuthor().getIdLong()) < 60221) {
                    event.getChannel().sendMessage("Not enough coins in your balance!").queue();
                } else {
                    try {
                        File f = new File("C:\\Users\\samch\\Desktop\\currency\\"+event.getGuild().getId()+"\\users.txt");
                        StringBuilder sb = new StringBuilder();
                        BufferedReader br = new BufferedReader(new FileReader(f));
                        String input = "";
                        while((input = br.readLine()) != null){
                            if (input.substring(0, input.indexOf(' ')).equals(event.getAuthor().getId())){
                                int x = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                                x -= 60221;
                                sb.append(input, 0, input.indexOf(' ') + 1).append(x).append('\n');
                            }else sb.append(input).append('\n');
                        }
                        FileWriter fw = new FileWriter(f);
                        fw.write(sb.toString());
                        fw.flush();
                    } catch(IOException e) {

                    }
                    event.getChannel().sendMessage("Congrats! You just received the **Avogadro's Award Role**!").queue();
                    event.getGuild().getController().addRolesToMember(event.getMember(), event.getJDA().getRoleById("676905762735390762")).queue();
                }
            }
            if(msg.substring(7).equalsIgnoreCase("Archimedes' Award Role")) {
                Role r = event.getGuild().getRolesByName("Archimedes' Award", true).get(0);
                if(event.getMember().getRoles().contains(r)) {
                    event.getChannel().sendMessage("You already have that role!").queue();
                    return;
                }
                if(FindUser.getCoins(event.getGuild(), event.getAuthor().getIdLong()) < 31415) {
                    event.getChannel().sendMessage("Not enough coins in your balance!").queue();
                } else {
                    try {
                        File f = new File("C:\\Users\\samch\\Desktop\\currency\\"+event.getGuild().getId()+"\\users.txt");
                        StringBuilder sb = new StringBuilder();
                        BufferedReader br = new BufferedReader(new FileReader(f));
                        String input = "";
                        while((input = br.readLine()) != null){
                            if (input.substring(0, input.indexOf(' ')).equals(event.getAuthor().getId())){
                                int x = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                                x -= 31416;
                                sb.append(input, 0, input.indexOf(' ') + 1).append(x).append('\n');
                            }else sb.append(input).append('\n');
                        }
                        FileWriter fw = new FileWriter(f);
                        fw.write(sb.toString());
                        fw.flush();
                    } catch(IOException e) {

                    }
                    event.getChannel().sendMessage("Congrats! You just received the **Archimedes' Award Role**!").queue();
                    event.getGuild().getController().addRolesToMember(event.getMember(), event.getJDA().getRoleById("676905562776141857")).queue();
                }
            }
        }
    }
}