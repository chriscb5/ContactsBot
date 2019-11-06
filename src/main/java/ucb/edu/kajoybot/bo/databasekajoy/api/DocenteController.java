package ucb.edu.kajoybot.bo.databasekajoy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ucb.edu.kajoybot.bo.databasekajoy.dao.DocenteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.DocenteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.DocenteDto;
<<<<<<< HEAD
import ucb.edu.kajoybot.bo.databasekajoy.dto.EstudianteDto;
=======
>>>>>>> bfeaef4699f2c56b7fc48cba3b2db6c99748e804

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/k1/docente")
public class DocenteController {

    private DocenteRespository docenteRespository;


    @Autowired
    public DocenteController(DocenteRespository docenteRespository){
        this.docenteRespository = docenteRespository;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
<<<<<<< HEAD
    List<DocenteEntity> all()
    {
=======
    List<DocenteDto> all(){
>>>>>>> bfeaef4699f2c56b7fc48cba3b2db6c99748e804
        List<DocenteDto> docenteDtoList=new ArrayList<>();
        for (DocenteEntity docenteEntity:docenteRespository.findAll()){
            docenteDtoList.add(new DocenteDto(docenteEntity));
        }
<<<<<<< HEAD
        return docenteRespository.findAll();
=======
        return docenteDtoList;

//        return docenteRespository.findAll();
>>>>>>> bfeaef4699f2c56b7fc48cba3b2db6c99748e804
    }



}