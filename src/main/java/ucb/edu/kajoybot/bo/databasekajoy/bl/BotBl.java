package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ucb.edu.kajoybot.bo.databasekajoy.dao.CursoRepository;
import ucb.edu.kajoybot.bo.databasekajoy.dao.DocenteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import ucb.edu.kajoybot.bo.databasekajoy.domain.DocenteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.Status;

@Service
public class BotBl {

    private static final Logger LOGGER= LoggerFactory.getLogger(BotBl.class);

    public static int numero_de_preguna=0;

    private EstudianteRespository estudianteRespository;
    private DocenteRespository docenteRespository;
    private CursoRepository cursoRepository;

    @Autowired
    public BotBl(EstudianteRespository estudianteRespository, DocenteRespository docenteRespository, CursoRepository cursoRepository) {
        this.estudianteRespository = estudianteRespository;
        this.docenteRespository = docenteRespository;
        this.cursoRepository = cursoRepository;
    }


    public  String MensajesDeRegistro(Update update)
    {
        String cadena=new String();
        switch (numero_de_preguna){
            case 0:
                LOGGER.info("Ingresando nombre");
                cadena="Ingrese su nombre ";
            case 1:
                LOGGER.info("Ingresando apellido paterno");
                cadena="Ingrese su apellido paterno ";
                break;
            case 2:
                LOGGER.info("Ingresando apellido materno");
                cadena="Ingrese su apellido materno ";
                break;
            case 3:
                LOGGER.info("Ingresando institucion");
                cadena="Ingrese su institución ";
                break;
            case 4:
                LOGGER.info("Ingresando nombre de usuario");
                cadena="Ingrese su nombre de usuario";

                break;
        }
        return cadena;
    }

    public  String guardarListaRegistros(List<String> listaderegistros){
        EstudianteEntity estudianteEntity=new EstudianteEntity();
        estudianteEntity.setNombre(listaderegistros.get(0));
        estudianteEntity.setApellidoPaterno(listaderegistros.get(1));
        estudianteEntity.setApellidoMaterno(listaderegistros.get(2));
        estudianteEntity.setInstitucion(listaderegistros.get(3));
        estudianteEntity.setStatuss(Status.ACTIVE.getStatus());
        estudianteEntity.setTxUser(listaderegistros.get(4));
        estudianteEntity.setTxDate(new Date());
        estudianteRespository.save(estudianteEntity);
        return "¡Registro completado exitosamente¡";
    }


    public  String MensajesDeRegistroDocente(Update update)
    {
        String cadena=new String();
        switch (numero_de_preguna){
            case 0:
                LOGGER.info("Ingresando nombre");
                cadena="Ingrese su nombre ";
            case 1:
                LOGGER.info("Ingresando apellido paterno");
                cadena="Ingrese apellido paterno ";
                break;
            case 2:
                LOGGER.info("Ingresando apellido materno");
                cadena="Ingrese su apellido materno ";
                break;
            case 3:
                LOGGER.info("Ingresando nombre de usuario");
                cadena="Ingrese su nombre de usuario";
                break;
        }
        return cadena;
    }


    public  String guardarListaRegistrosDocente(List<String> listaderegistros){
        LOGGER.info("Llega al metodo con : ");

        for (String lag:listaderegistros){
            LOGGER.info("Elemento : "+lag);
        }
        DocenteEntity docenteEntity=new DocenteEntity();
        docenteEntity.setNombre(listaderegistros.get(0));
        docenteEntity.setApellidoPaterno(listaderegistros.get(1));
        docenteEntity.setApellidoMaterno(listaderegistros.get(2));
        docenteEntity.setStatuss(Status.ACTIVE.getStatus());
        docenteEntity.setTxUser(listaderegistros.get(3));
        docenteEntity.setTxDate(new Date());
        LOGGER.info("Entidad docente "+docenteEntity.toString());
        docenteRespository.save(docenteEntity);
        return "¡Registro completado exitosamente¡";
    }


    /*
    public  String notificarRegistroCompleto(Update update)
    {
        LOGGER.info("Registro completado");
        return "Registro completado";
    }

/*
    public  String ingressInstitucion(Update update)
    {
        LOGGER.info("Ingresando institucion");
        return "Ingrese la institucion";
    }

    public String ingreseNombreUser(Update update){
        LOGGER.info("Ingresando nombre de usuario");
        return "Ingrese nombre de usuario";
    }

    public String formularioregistrollenado(Update update){
        LOGGER.info("Formulario llenado correctamente");

        return  "Formulario llenado correctamente";
    }
/*
    public List<String> processUpdate(Update update){
        LOGGER.info("Recibiendo primer nombre",update);
    }
 */




}
