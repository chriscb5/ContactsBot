package ucb.edu.kajoybot.bo.databasekajoy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ucb.edu.kajoybot.bo.databasekajoy.bl.CursoBL;
import ucb.edu.kajoybot.bo.databasekajoy.dao.CursoRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.CursoEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.CursoDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/k1/curso")
public class CursoController {

    private CursoBL cursoBL;

    @Autowired
    public CursoController(CursoBL cursoBL){
        this.cursoBL = cursoBL;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<CursoDto> all(){
        List<CursoDto> cursoDtoList = new ArrayList<>();
        //for (CursoEntity cursoEntity:cursoBL.findAll()){
            cursoDtoList.add(new CursoDto(cursoBL.findCursoById(1)));
        //}
        return cursoDtoList;
    }

}
