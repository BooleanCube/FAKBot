package bot;

import bot.cmds.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

public class Main {

    public static long l = 0;

    private Main() throws LoginException, InterruptedException {
        System.out.println("Booting...");
        JDA jda = new JDABuilder(AccountType.BOT)
                .setToken("never gonna give you up")
                .setGame(Game.playing("Type fakhelp for help!"))
                .setAudioEnabled(false)
                .addEventListener(new Test())
                .addEventListener(new Ask())
                .addEventListener(new Check())
                .addEventListener(new createGuild())
                .addEventListener(new Balance())
                .addEventListener(new DashBoard())
                .addEventListener(new Help())
                .addEventListener(new Store())
                .addEventListener(new Shutdown())
                .addEventListener(new Meme())
                .addEventListener(new Give())
                .build().awaitReady();
        System.out.println("Running...");
    }

    public static void main(String[] args) throws LoginException, InterruptedException {
        new Main();
    }

}
