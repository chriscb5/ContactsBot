package ucb.edu.kajoybot.bo.databasekajoy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ucb.edu.kajoybot.bo.databasekajoy.bl.PersonBL;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.DocenteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.EstudianteDto;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/k1/estudiante")
public class EstudianteController {
    //    private EstudianteRespository estudianteRespository;
    private PersonBL personBL;


    @Autowired
    public EstudianteController(PersonBL personBL) {
        this.personBL = personBL;

    }

    //    @RequestMapping(value = "/",method = RequestMethod.GET,
    //   produces = MediaType.APPLICATION_JSON_VALUE)
  /*  List<EstudianteEntity> all(){
        return estudianteRespository.findAll();

    }*/

    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<EstudianteDto> all(@RequestParam(name = "includeCursos") boolean includeCursos) {
        if (includeCursos) {
            //return personBL.findDocenteByNombre("Lalo");
            return personBL.findAllEstudiantesWithCursos();
        } else {
//            return personBL.findDocenteByNombre("Lalo");
            return personBL.findAllEstudiantesWithCursos();
        }


    }

/*
    List<EstudianteDto> all()
    {
      List<EstudianteDto> estudianteDtoList=new ArrayList<>();
      for (EstudianteDto estudianteDto:personBL.findAllEstudiantes()){
          estudianteDtoList.add(estudianteDto);
      }
      return personBL.findAllEstudiantes();
    }
*/
}

