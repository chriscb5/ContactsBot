package ucb.edu.kajoybot.bo.databasekajoy.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;

import javax.annotation.PostConstruct;

public class BotInitializator{

    EstudianteRespository estudianteRespository;

    public BotInitializator(EstudianteRespository estudianteRespository) {
        this.estudianteRespository = estudianteRespository;
    }

    @PostConstruct
    public void init(){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi=new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MainBot(estudianteRespository));
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
