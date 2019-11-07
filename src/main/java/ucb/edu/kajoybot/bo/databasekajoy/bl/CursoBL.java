package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucb.edu.kajoybot.bo.databasekajoy.dao.CursoRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.CursoEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.CursoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoBL {
    CursoRepository cursoRepository;

    @Autowired
    public  CursoBL(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    public CursoEntity findByCursoId(Integer pk) {
        Optional<CursoEntity> optional = this.cursoRepository.findById(pk);
        if (optional.isPresent()){
            return  optional.get();
        }else {
            throw new RuntimeException("Record cannot be found for CursoEntity with ID: " + pk);
        }
    }

    public List<CursoDto> findAllCourses(){
        List<CursoDto> cursoDtoList = new ArrayList<>();
        CursoDto cursoDto = new CursoDto();
        for (CursoEntity cursoEntity:cursoRepository.findByIdCurso(cursoDto.getIdCurso())){
            cursoDtoList.add(new CursoDto(cursoEntity));
        }
        return cursoDtoList;
    }
}
