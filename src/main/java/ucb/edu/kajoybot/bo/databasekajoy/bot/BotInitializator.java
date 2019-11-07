package ucb.edu.kajoybot.bo.databasekajoy.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ucb.edu.kajoybot.bo.databasekajoy.bl.CursoBL;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;

import javax.annotation.PostConstruct;

@Component
public class BotInitializator {

    EstudianteRespository estudianteRespository;
    CursoBL cursoBL;

    @Autowired
    public BotInitializator(EstudianteRespository estudianteRespository) {
        this.estudianteRespository = estudianteRespository;
    }

    public BotInitializator() {
    }

    @PostConstruct
    public void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MainBot(estudianteRespository, cursoBL));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}