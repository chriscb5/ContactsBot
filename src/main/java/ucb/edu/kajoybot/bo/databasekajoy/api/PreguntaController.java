package ucb.edu.kajoybot.bo.databasekajoy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ucb.edu.kajoybot.bo.databasekajoy.bl.CursoBL;
import ucb.edu.kajoybot.bo.databasekajoy.bl.MensajesBL;
import ucb.edu.kajoybot.bo.databasekajoy.dao.CursoRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.CursoEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.PreguntaEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.TestEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.CursoDto;
import ucb.edu.kajoybot.bo.databasekajoy.dto.PreguntaDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/d1/pregunta")
public class PreguntaController {

    private MensajesBL mensajesBL;

    @Autowired
    public PreguntaController(MensajesBL mensajesBL) {
        this.mensajesBL = mensajesBL;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<PreguntaDto> all(){
        List<PreguntaDto> preguntaDtoList= new ArrayList<>();
        TestEntity testEntity= mensajesBL.findTestByTestNombre("Educafis");
        for(PreguntaEntity preguntaEntity:mensajesBL.preguntaEntityListByIdTest(testEntity)){
            preguntaDtoList.add(new PreguntaDto(preguntaEntity));
        }
        return preguntaDtoList;
    }


}
