package ucb.edu.kajoybot.bo.databasekajoy.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ucb.edu.kajoybot.bo.databasekajoy.bl.CursoBL;
import ucb.edu.kajoybot.bo.databasekajoy.bl.BotBl;
import ucb.edu.kajoybot.bo.databasekajoy.bl.MensajesBL;
import ucb.edu.kajoybot.bo.databasekajoy.bl.PersonBL;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;

import javax.annotation.PostConstruct;

@Component
public class BotInitializator {

    BotBl botBl;
    PersonBL personBL;
    MensajesBL mensajesBL;

//    @Autowired
 //   public BotInitializator(PersonBL personBL) {
   //     this.personBL = personBL;
    //}
    @Autowired
    public BotInitializator(BotBl botBl,PersonBL personBL, MensajesBL mensajesBL){
        this.botBl=botBl;
        this.personBL=personBL;
        this.mensajesBL=mensajesBL;
    }

    public BotInitializator() {
    }

    @PostConstruct
    public void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MainBot(botBl,personBL,mensajesBL));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}