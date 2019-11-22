package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class MensajesBL {

    private static final Logger LOGGER= LoggerFactory.getLogger(MensajesBL.class);
    private static int numero_de_pregunta=0;
    private static int numero_de_respuesta=0;


    public  String mensajesRegistroEstudiante(Update update)
    {
        String cadena=new String();
        switch (numero_de_pregunta){
            case 0:
                LOGGER.info("Ingresando apellido paterno");
                cadena="Ingrese su apellido paterno ";
                break;
            case 1:
                LOGGER.info("Ingresando apellido materno");
                cadena="Ingrese su apellido materno ";
                break;
            case 2:
                LOGGER.info("Ingresando institucion");
                cadena="Ingrese su institución ";
                break;
            case 3:
                LOGGER.info("Ingresando nombre de usuario");
                cadena="Ingrese su nombre de usuario";

                break;
        }
        return cadena;
    }


    public  String mensajesRegistroDocente(Update update)
    {
        String cadena=new String();
        switch (numero_de_pregunta){
            case 0:
                LOGGER.info("Ingresando apellido paterno");
                cadena="Ingrese apellido paterno ";
                break;
            case 1:
                LOGGER.info("Ingresando apellido materno");
                cadena="Ingrese su apellido materno ";
                break;
            case 2:
                LOGGER.info("Ingresando nombre de usuario");
                cadena="Ingrese su nombre de usuario";
                break;
        }
        return cadena;
    }


    public  String mensajesRegistroCurso(Update update)
    {
        String cadena=new String();
        switch (numero_de_pregunta){
           case 0:
                LOGGER.info("Ingresando tipo de curso");
                cadena="Ingrese el tipo del curso ";
                break;
            case 1:
                LOGGER.info("Ingresando clave del curso");
                cadena="Ingrese la clave del curso (si no desea el ingreso por clave, escriba '-' sin las comillas)";
                break;
        }
        return cadena;
    }

    public  String mensajesRegistroEstudianteCurso(Update update)
    {
        String cadena=new String();
        switch (numero_de_pregunta){
            case 0:
                LOGGER.info("Ingresando nombre curso");
                cadena="**Unirse a un curso**";
                break;
            case 1:
                LOGGER.info("Ingresando clave");
                cadena="El curso es privado\n Por favor, ingrese la clave del curso";
                break;
        }
        return cadena;
    }

    public  String mensajeRegistroTest(Update update){
        String caden=new String();
        switch (numero_de_pregunta){
            case 0:
                caden="¿Cuál sera la pregunta?";
                break;
/*            case 1:
                caden="¿Cúal sera la tercera pregunta";
                break;
            case 2:
                caden="¿Cúal sera la cuarta pregunta";
                break;
  */      }
        return caden;
    }

    public String mensajeRegistroRespuesta(Update update){
        String cadena=new String();
        switch (numero_de_respuesta)
        {
            case 0:
                cadena="Ingrese la primera respuesta";
                break;
            case 1:
                cadena="Ingrese la segunda respuesta";
                break;
            case 2:
                cadena="Ingrese la tercera respuesta";
                break;
            case 3:
                cadena="Ingrese la cuarta respuesta";
                break;
        }
        return cadena;
    }

    public static int getNumero_de_pregunta() {
        return numero_de_pregunta;
    }

    public static void setNumero_de_pregunta(int numero_de_pregunta) {
        MensajesBL.numero_de_pregunta = numero_de_pregunta;
    }

    public static int getNumero_de_respuesta() {
        return numero_de_respuesta;
    }

    public static void setNumero_de_respuesta(int numero_de_respuesta) {
        MensajesBL.numero_de_respuesta = numero_de_respuesta;
    }
}
