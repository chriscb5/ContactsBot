package ucb.edu.kajoybot.bo.databasekajoy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ucb.edu.kajoybot.bo.databasekajoy.dao.CursoRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.CursoEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.CursoDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/k1/curso")
public class CursoController {

    private CursoRepository cursoRepository;

    @Autowired
    public CursoController(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<CursoDto> all(){
        List<CursoDto> cursoDtoList = new ArrayList<>();
        for (CursoEntity cursoEntity:cursoRepository.findAll()){
            cursoDtoList.add(new CursoDto(cursoEntity));
        }
        return cursoDtoList;
    }

}
