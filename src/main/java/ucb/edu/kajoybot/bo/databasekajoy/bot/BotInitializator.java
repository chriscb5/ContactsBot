package ucb.edu.kajoybot.bo.databasekajoy.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ucb.edu.kajoybot.bo.databasekajoy.bl.*;

import javax.annotation.PostConstruct;

@Component
public class BotInitializator {

    BotBl botBl;
    ContactsBL contactsBL;

    @Autowired
    public BotInitializator(BotBl botBl, ContactsBL contactsBL){
        this.botBl=botBl;
        this.contactsBL = contactsBL;
    }

    public BotInitializator() {
    }

    @PostConstruct
    public void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MainBot(botBl, contactsBL));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}