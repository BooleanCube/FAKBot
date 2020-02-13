package bot.cmds;

import bot.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.annotation.Nonnull;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Meme extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] msg = event.getMessage().getContentRaw().split(" ");
        JSONParser parser = new JSONParser();
        String postlink = "";
        String title = "";
        String url = "";

        if(msg[0].equalsIgnoreCase(Constants.prefix + "meme")) {
            try {
                URL memeurl = new URL("https://meme-api.herokuapp.com/gimme");
                BufferedReader bf = new BufferedReader(new InputStreamReader(memeurl.openConnection().getInputStream()));
                String lines;
                while((lines = bf.readLine()) != null) {
                    JSONArray array = new JSONArray();
                    array.add(parser.parse(lines));
                    for(Object o : array) {
                        JSONObject obj = (JSONObject) o;
                        postlink = (String) obj.get("postLink");
                        title = (String) obj.get("title");
                        url = (String) obj.get("url");
                    }
                }
                bf.close();
                EmbedBuilder e = new EmbedBuilder();
                e.setColor(Color.red);
                e.setTitle(title);
                e.setImage(url);
                event.getChannel().sendMessage(e.build()).queue();
            } catch (Exception e) {
                event.getChannel().sendMessage(":no_entry: **Something went wrong!!!** Please try again later!").queue();
            }
        }
    }
}