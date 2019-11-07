package ucb.edu.kajoybot.bo.databasekajoy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ucb.edu.kajoybot.bo.databasekajoy.bl.PersonBL;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.EstudianteDto;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/estudiante")
public class   EstudianteController {

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
    //List<EstudianteDto> all()
    //{
/*      List<EstudianteDto> estudianteDtoList=new ArrayList<>();
      for (EstudianteEntity estudianteEntity:estudianteRespository.findAll()){
          estudianteDtoList.add(new EstudianteDto(estudianteEntity));
      }
*/  //    return personBL.findAllEstudiantes();
    //}
    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<EstudianteDto> all(@RequestParam(name = "includeCursos") boolean includeCursos) {
        if (includeCursos) {
            return personBL.findAllEstudiantesWithCursos();
        } else {
            return personBL.findAllEstudiantesWithCursos();
        }


    }
}
