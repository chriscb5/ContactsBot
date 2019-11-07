package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucb.edu.kajoybot.bo.databasekajoy.dao.DocenteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.CursoEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.DocenteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteCursoEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.CursoDto;
import ucb.edu.kajoybot.bo.databasekajoy.dto.DocenteDto;
import ucb.edu.kajoybot.bo.databasekajoy.dto.EstudianteDto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonBL {
    EstudianteRespository estudianteRespository;
    DocenteRespository docenteRespository;


    @Autowired
    public PersonBL(EstudianteRespository estudianteRespository, DocenteRespository docenteRespository) {
        this.estudianteRespository = estudianteRespository;
        this.docenteRespository = docenteRespository;
    }

/*    @Autowired
    public PersonBL(EstudianteRespository estudianteRespository) {
        this.estudianteRespository = estudianteRespository;
    }*/

    public EstudianteEntity findEstudianteByPk(Integer pk){
        Optional<EstudianteEntity> optional= this.estudianteRespository.findById(pk);
        if(optional.isPresent()){
            return optional.get();
        }
        else {
            throw new RuntimeException("Registro inexistente de estudiante con ID: "+pk);

        }

    }

    public List<EstudianteDto> findAllEstudiantes(){
        List<EstudianteDto> estudianteDtoList=new ArrayList<>();
        for (EstudianteEntity estudianteEntity:estudianteRespository.findAll()){
            estudianteDtoList.add(new EstudianteDto(estudianteEntity));
        }
        return estudianteDtoList;
    }

    public List<DocenteDto> findAllDocentes(){
        List<DocenteDto> estudianteDtoList=new ArrayList<>();
        for (DocenteEntity estudianteEntity:docenteRespository.findAll()){
            estudianteDtoList.add(new DocenteDto(estudianteEntity));
        }
        return estudianteDtoList;
    }
    public List<EstudianteDto> findAllEstudiantesWithCursos(){
        List<EstudianteDto> estudianteDtoList=new ArrayList<>();
        for (EstudianteEntity estudianteEntity:estudianteRespository.findAll()){
            EstudianteDto estudianteDto=new EstudianteDto(estudianteEntity);
            List<CursoDto> cursoDtoList=new ArrayList<>();
            List<EstudianteCursoEntity> cursoEntityList = estudianteEntity.getEstudianteCursoList();
            for (EstudianteCursoEntity estcur: cursoEntityList){
                CursoEntity cursos=estcur.getIdCurso();
                cursoDtoList.add(new CursoDto(cursos));
            }
            estudianteDto.setCursosList(cursoDtoList);
            //          estudianteDtoList.add(new EstudianteDto(estudianteEntity));
            estudianteDtoList.add(estudianteDto);
        }
        return estudianteDtoList;
    }


    public DocenteEntity findDocenteByPk(Integer pk){
        Optional<DocenteEntity> optional=this.docenteRespository.findById(pk);
        if (optional.isPresent()){
            return optional.get();
        }
        else {
            throw new RuntimeException("Registro inexistente de docento con ID "+pk);
        }
    }


}
