package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.springframework.beans.factory.annotation.Autowired;
import ucb.edu.kajoybot.bo.databasekajoy.dao.DocenteRespository;

public class DocenteBL {
    DocenteRespository docenteRespository;

    @Autowired
    public DocenteBL(DocenteRespository docenteRespository) {
        this.docenteRespository = docenteRespository;
    }



}
