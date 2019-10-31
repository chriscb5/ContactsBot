package ucb.edu.kajoybot.bo.databasekajoy.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;

@Component
public class BotInitializator {

    EstudianteRespository estudianteRespository;

    @Autowired
    public BotInitializator(EstudianteRespository estudianteRespository) {
        this.estudianteRespository = estudianteRespository;
    }

    public BotInitializator() {
    }



}