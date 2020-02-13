package bot.cmds;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class createGuild extends ListenerAdapter {
    public void onGuildJoin(GuildJoinEvent ex){
        String id = ex.getGuild().getId();
        File f = new File("C:\\Users\\samch\\Desktop\\currency\\"+id);
        f.mkdir();
        List<Member> members = ex.getGuild().getMembers();
        try {
            FileWriter fw = new FileWriter(new File("C:\\Users\\samch\\Desktop\\currency\\"+id+"\\users.txt"));
            for (Member m:members){
                if (!m.getUser().isBot()){
                    fw.write(m.getUser().getId());
                    fw.write(" ");
                    fw.write("0\n");
                }
            }
            fw.flush();
        } catch (IOException e) {/* emptiness uwu */} finally{
            System.out.println("Successfully created a server folder for new server.");
        }
    }
}