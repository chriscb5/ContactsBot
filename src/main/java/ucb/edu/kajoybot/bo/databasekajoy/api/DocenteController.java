package ucb.edu.kajoybot.bo.databasekajoy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ucb.edu.kajoybot.bo.databasekajoy.bl.PersonBL;
import ucb.edu.kajoybot.bo.databasekajoy.dao.DocenteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.DocenteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.DocenteDto;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/k1/docente")
public class DocenteController {


    private PersonBL personBL;

    @Autowired
    public DocenteController(PersonBL personBL){
        this.personBL =personBL;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<DocenteDto> all(){
        List<DocenteDto> docenteDtoList=new ArrayList<>();
        for (DocenteDto docenteDto:personBL.findAllDocentes()){
            docenteDtoList.add(docenteDto);
        }
        return docenteDtoList;
    }


}