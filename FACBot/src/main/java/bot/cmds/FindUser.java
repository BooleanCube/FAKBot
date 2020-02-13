package bot.cmds;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindUser {

    public static String findUser(Guild g, long id) {
        List<Member> ppl = g.getMembers();
        for(Member m : ppl) {
            if(m.getUser().getIdLong() == id) {
                return m.getUser().getName();
            }
        }
        return "";
    }

    public static String getId(Guild g, String name) {
        List<Member> ppl = g.getMembers();
        for(Member m : ppl) {
            if(m.getUser().getName().equals(name)) {
                return m.getUser().getId();
            }
        }
        return "";
    }

    //gets username from ping message
    public static String getanId(String mention) {
        return mention.substring(mention.indexOf("!") + 1, mention.length()-1);
    }

    public static boolean isPerson(Guild g, String id) {
        List<Member> ppl = g.getMembers();
        for(Member m : ppl) {
            if(m.getUser().getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public static int findIndexInt(ArrayList<Integer> ar, int num) {
        for(int i=0; i<ar.size(); i++) {
            int a = ar.get(i);
            if(a == num) {
                return i;
            }
        }
        return -1;
    }

    public static int getCoins(Guild g, long id) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\samch\\Desktop\\currency\\" + g.getId() + "\\users.txt"));
            String input;
            while ((input = br.readLine()) != null) {
                if (input.substring(0, input.indexOf(' ')).equals(Long.toString(id))) {
                    int x = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                    return x;
                }
            }
        } catch(IOException e) {

        }
        return 0;
    }

    public static boolean hasRole(List<Role> roles, String name) {
        for(Role r : roles) {
            if(r.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
