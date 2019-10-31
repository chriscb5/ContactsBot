package ucb.edu.kajoybot.bo.databasekajoy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;

import java.util.List;

@RestController
@RequestMapping("/v1/estudiante")
public class EstudianteController {

    private EstudianteRespository estudianteRespository;

    @Autowired
    public EstudianteController(EstudianteRespository estudianteRespository){
        this.estudianteRespository = estudianteRespository;

    }

    @RequestMapping(value = "/",method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<EstudianteEntity> all(){
        return estudianteRespository.findAll();
    }

}
