package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ucb.edu.kajoybot.bo.databasekajoy.dao.DocenteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.CursoEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.DocenteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteCursoEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.CursoDto;
import ucb.edu.kajoybot.bo.databasekajoy.dto.DocenteDto;
import ucb.edu.kajoybot.bo.databasekajoy.dto.EstudianteDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonBL {
    private static final Logger LOGGER= LoggerFactory.getLogger(BotBl.class);

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


    public  String existPasswordEstudianteByCurso(String nombre, String clave)
    {
        EstudianteEntity estudianteEntity=getEstudianteByNombre(nombre);
        EstudianteDto estudianteDto=new EstudianteDto(estudianteEntity);
        List<CursoDto> cursoDtoList=new ArrayList<>();
        List<EstudianteCursoEntity> estudianteCursoEntityList= estudianteEntity.getEstudianteCursoList();
        for (EstudianteCursoEntity estudianteCursoEntity:estudianteCursoEntityList){
            CursoEntity cursoEntity=estudianteCursoEntity.getIdCurso();
            cursoDtoList.add(new CursoDto(cursoEntity));
        }
        estudianteDto.setCursosList(cursoDtoList);
        boolean existEstudiante=checkifExistEstudianteinCurse(estudianteDto,clave);
        if(existEstudiante){
            return "BIENVENIDO al CURSO ";
        }
        else {
            return  "Ups, lo sentimos, no es la clave en el curso";
        }
    }

    public boolean checkifExistEstudianteinCurse(EstudianteDto estudianteDto,String clave){
        List<CursoDto> cursoDtoList=estudianteDto.getCursosList();
        boolean isExist=false;
        for(CursoDto cursoDto:cursoDtoList){
            if(cursoDto.getClave().equals(clave)){
                isExist=true;
            }
        }
        return isExist;

    }


    public String ExistPasswordDocenteInCurse(String nombre,String clave){
        boolean isExiste=false;
        DocenteDto docenteDto= existDocenteInCursosByNombre(nombre);
        List<CursoDto> cursoDtoList=docenteDto.getCursosList();
        for (CursoDto cursoDto:cursoDtoList){
            LOGGER.info("Curso clave "+cursoDto.getClave());
            if(clave.equals(cursoDto.getClave())){
                isExiste=true;
            }
        }
        if (isExiste){
            return "Bienvenido al curso";
        }
        else
            return "Clave incorrecta";
    }


    public DocenteDto existDocenteInCursosByNombre(String nombre){
        DocenteEntity docenteEntity=findIdDocenteByNombre(nombre);
        LOGGER.info("El docente es "+docenteEntity.getIdDocente()+","+docenteEntity.getApellidoPaterno()+","+docenteEntity.getApellidoMaterno());
        DocenteDto docenteDto=new DocenteDto(docenteEntity);
        List<CursoDto> cursoDtoList=new ArrayList<>();
        List<CursoEntity> cursoEntityList=docenteEntity.getCursoList();
        for (CursoEntity cursoEntity:cursoEntityList){
            cursoDtoList.add(new CursoDto(cursoEntity));
        }
        docenteDto.setCursosList(cursoDtoList);
        return docenteDto;
    }

    /*    public List<DocenteDto> findAllDocenteWithCursos(){
        List<DocenteDto> docenteDtoList=new ArrayList<>();
        for(DocenteEntity docenteEntity:docenteRespository.findAll()){
            DocenteDto docenteDto= new DocenteDto(docenteEntity);
            List<CursoDto> cursoDtoList=new ArrayList<>();
            List<DocenteCur>
        }
    }
 */   public DocenteEntity findDocenteByPk(Integer pk){
        Optional<DocenteEntity> optional=this.docenteRespository.findById(pk);
        if (optional.isPresent()){
            return optional.get();
        }
        else {
            throw new RuntimeException("Registro inexistente de docento con ID "+pk);
        }
    }

    public DocenteEntity findDocenteByNombre(String pk){
        DocenteEntity docenteEntity=this.docenteRespository.findAllByNombre(pk);
        if (docenteEntity!=null){
            return docenteEntity;
        }
        else {
            throw new RuntimeException("Registro inexistente de docento con el nombre "+pk);
        }
    }


    public EstudianteEntity getEstudianteByNombre(String nombre){
        EstudianteEntity estudianteEntity=this.estudianteRespository.findAllByNombre(nombre);
        if(estudianteEntity!=null){
            return estudianteEntity;
        }
        else
        {
            throw new RuntimeException("Registro inexistente "+nombre);
        }

    }

    public String ExistDocenteByNombre(String pk){
        DocenteEntity docenteEntity=this.docenteRespository.findAllByNombre(pk);
        if (docenteEntity!=null){
            return "¡Bienvenido al curso!";
        }
        else {
            return "Ups, registro el registro es inexistente";
        }
    }


    public DocenteEntity findIdDocenteByNombre(String pk){
        List<DocenteEntity> docenteEntityList=docenteRespository.findAll();
        DocenteEntity variable=null;
        for(DocenteEntity docenteEntity:docenteEntityList){
            if(docenteEntity.getNombre().equals(pk)){
                variable=docenteEntity;
            }
        }
 /*       DocenteEntity docenteEntity=this.docenteRespository.findAllByNombre(pk);
        if (docenteEntity!=null){
            return docenteEntity;
        }
        else {
            throw new RuntimeException("Registro inexistente de docento con el nombre "+pk);
        }*/
        return variable;
    }

}
