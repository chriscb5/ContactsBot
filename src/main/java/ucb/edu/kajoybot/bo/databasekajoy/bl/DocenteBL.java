package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.springframework.beans.factory.annotation.Autowired;
import ucb.edu.kajoybot.bo.databasekajoy.dao.DocenteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.DocenteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.DocenteDto;

import java.util.ArrayList;
import java.util.List;

public class DocenteBL {
    DocenteRespository docenteRespository;

    @Autowired
    public DocenteBL(DocenteRespository docenteRespository) {
        this.docenteRespository = docenteRespository;
    }

    public List<DocenteDto> findAllDocentes(){
        List<DocenteDto> docenteDtoList = new ArrayList<>();
        for(DocenteEntity docenteEntity:docenteRespository.findAll()){
            docenteDtoList.add(new DocenteDto(docenteEntity));
        }
        return docenteDtoList;
    }

}
