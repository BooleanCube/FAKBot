package bot.cmds;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.*;

public class Give extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] msg = event.getMessage().getContentRaw().split(" ");
        if(msg[0].equalsIgnoreCase("fakgive") && msg.length == 3) {
            int amount = -1;
            try {
                amount = Integer.parseInt(msg[1]);
            } catch(Exception e) {
                if(msg[1].equalsIgnoreCase("all")) {
                    amount = FindUser.getCoins(event.getGuild(), event.getAuthor().getIdLong());
                }
                event.getChannel().sendMessage("Wrong usage of command! Usage = `fakgive [amount] [person]` ***Note***: Person **MUST** be pinged!!!").queue();
            }
            String person = msg[2];
            String id1 = FindUser.getanId(person);
            String id2 = event.getAuthor().getId();
            if(amount >= FindUser.getCoins(event.getGuild(), Long.parseLong(id2))) {
                event.getChannel().sendMessage("You don't have that many coins in your balance! You are not as rich as you thought!").queue();
                return;
            }
            try {
                File f = new File("C:\\Users\\samch\\Desktop\\currency\\"+event.getGuild().getId()+"\\users.txt");
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new FileReader(f));
                String input = "";
                while((input = br.readLine()) != null){
                    if (input.substring(0, input.indexOf(' ')).equals(id2)){
                        int x = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                        x -= amount;
                        sb.append(input, 0, input.indexOf(' ') + 1).append(x).append('\n');
                    } else sb.append(input).append('\n');
                }
                FileWriter fw = new FileWriter(f);
                fw.write(sb.toString());
                fw.flush();
                br.close();
                sb = new StringBuilder();
                BufferedReader bf = new BufferedReader(new FileReader(f));
                input = "";
                while((input = bf.readLine()) != null){
                    if (input.substring(0, input.indexOf(' ')).equals(id1)){
                        int x = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                        x += amount;
                        sb.append(input, 0, input.indexOf(' ') + 1).append(x).append('\n');
                    } else sb.append(input).append('\n');
                }
                FileWriter fw2 = new FileWriter(f);
                fw2.write(sb.toString());
                fw2.flush();
                bf.close();
                event.getChannel().sendMessage("Successfully gave " + FindUser.findUser(event.getGuild(), Long.parseLong(id1)) + " **" + amount + "** coins!").queue();
            } catch(IOException e) {
                //nothing
            }
        } else if(msg[0].equalsIgnoreCase("fakgive") && msg.length != 3) {
            event.getChannel().sendMessage("Wrong usage of command! Usage = `fakgive [amount] [person]` ***Note***: Person **MUST** be pinged!!!").queue();
        }
    }
}
