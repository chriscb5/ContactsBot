package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ucb.edu.kajoybot.bo.databasekajoy.dao.*;
import ucb.edu.kajoybot.bo.databasekajoy.domain.*;
import ucb.edu.kajoybot.bo.databasekajoy.dto.Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MensajesBL {


    private static final Logger LOGGER= LoggerFactory.getLogger(MensajesBL.class);
    private static int numero_de_pregunta=0;
    private static int numero_de_respuesta=0;
    private static boolean registrosllenos=false;
    private static boolean entra_a_registro_estudiante=false;
    private static boolean entra_a_registro_docente=false;
    private static boolean entra_a_registro_curso=false;
    private static boolean entra_a_iniciar_estudiante=false;
    private static boolean entra_a_iniciar_docente=false;
    private static boolean entra_a_iniciar_docentenombre=false;
    private static boolean entra_a_registro_test=false;
    private static boolean entra_a_registro_respuesta=true;
    private static boolean entra_a_registro_estudiante_curso=false;
    private static boolean aniade_pregunta_nueva=false;
    private static boolean aniade_respuesta_nueva=false;
    private static boolean termina_test=false;
    private static boolean confirmation=false;
    private static List<String> registrollenadosList= new ArrayList<>();
    private static List<String> registrorespuestalist=new ArrayList<>();


    private EstudianteRespository estudianteRespository;
    private DocenteRespository docenteRespository;
    private CursoRepository cursoRepository;
    private KjEstudianteUserRepository kjEstudianteUserRepository;
    private TestRepository testRepository;
    private RespuestaRepository respuestaRepository;
    private PreguntaRepository preguntaRepository;
    private ChatRepository chatRepository;
    private PersonBL personBL;

    @Autowired
    public MensajesBL(EstudianteRespository estudianteRespository, DocenteRespository docenteRespository, CursoRepository cursoRepository, KjEstudianteUserRepository kjEstudianteUserRepository, TestRepository testRepository, RespuestaRepository respuestaRepository, PreguntaRepository preguntaRepository, ChatRepository chatRepository, PersonBL personBL) {
        this.estudianteRespository = estudianteRespository;
        this.docenteRespository = docenteRespository;
        this.cursoRepository = cursoRepository;
        this.kjEstudianteUserRepository = kjEstudianteUserRepository;
        this.testRepository = testRepository;
        this.respuestaRepository = respuestaRepository;
        this.preguntaRepository = preguntaRepository;
        this.chatRepository = chatRepository;
        this.personBL = personBL;
    }





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
    public String iniciarEstudiante(String messageTextReceived){
            String response=personBL.existPasswordEstudianteByCurso("Lalo",messageTextReceived);
                entra_a_iniciar_estudiante=false;
                return response;
    }

    public String iniciarDocente(String messageTextReceived){
        LOGGER.info("ENTRO AQUIIIII SISISISI");
        String response=personBL.ExistDocenteByNombre(messageTextReceived);
        entra_a_iniciar_docentenombre=false;
        return response;
    }

    public String entraRegistroEstudiante(Update update,String messageTextReceived){
        LOGGER.info("Entra a el registro estudiante oficial");
        String mensaje="";
        if(registrollenadosList.size()<5) {
            LOGGER.info("Entra al registros no llenos");
            if(getNumero_de_pregunta()<4){
                mensaje = mensajesRegistroEstudiante(update);
                return mensaje;
            }
           setNumero_de_pregunta(getNumero_de_pregunta()+1) ;//
           registrollenadosList.add(messageTextReceived);
            LOGGER.info("Tamaño de array "+registrollenadosList.size());
        }
        if (registrollenadosList.size()==5) {
            LOGGER.info("Ingresa a registros llenos");
            String mensajecomp = guardarListaRegistros(registrollenadosList);
            registrosllenos = false;
            registrollenadosList.clear();
            entra_a_registro_estudiante = false;
            return mensajecomp;
        }
        return mensaje;
    }

    public String entraRegistroDocente(Update update,String messageTextReceived){
        LOGGER.info("Entra a el registro estudiante oficial");
        String mensaje="";
        if(registrollenadosList.size()<4)
        {
            LOGGER.info("Entra al registros no llenos");
            if(getNumero_de_pregunta()<3){
                mensaje = mensajesRegistroDocente(update);
            }
            setNumero_de_pregunta(getNumero_de_pregunta()+1) ;//
            registrollenadosList.add(messageTextReceived);
            LOGGER.info("Tamaño de array "+registrollenadosList.size());
        }
        if (registrollenadosList.size()==4) {
            LOGGER.info("Ingresa a registros llenos");
            String mensajecomp = guardarListaRegistrosDocente(registrollenadosList);
            registrosllenos = false;
            registrollenadosList.clear();
            entra_a_registro_docente = false;
        }
        return mensaje;
    }


    public String entraRegistroCurso(Update update,String messageTextReceived){

        LOGGER.info("Entra a el registro curso oficial");
        String mensaje="";
        if(registrollenadosList.size()<3) {
            LOGGER.info("Entra al registros no llenos");
            if(getNumero_de_pregunta()<2){
                mensaje = mensajesRegistroCurso(update);
            }
            setNumero_de_pregunta(getNumero_de_pregunta()+1) ;
            registrollenadosList.add(messageTextReceived);
            LOGGER.info("Tamaño de array "+registrollenadosList.size());
        }
        if (registrollenadosList.size()==3) {
            LOGGER.info("Ingresa a registros llenos");
            mensaje = guardarListaRegistrosCurso(registrollenadosList);
            registrosllenos = false;
            registrollenadosList.clear();
            entra_a_registro_curso = false;
        }
        return mensaje;
    }

    public String entraRegistroEstudianteCurso(Update update,String messageTextReceived){
        LOGGER.info("Entra a el registro de estudiante en curso");
        String mensaje="";
        if(registrollenadosList.size()<2) {
            LOGGER.info("Entra a registros no llenos");
            if(getNumero_de_pregunta()<1){
                mensaje = mensajesRegistroEstudianteCurso(update);
            }
            setNumero_de_pregunta(getNumero_de_pregunta()+1) ;//
            registrollenadosList.add(messageTextReceived);
            LOGGER.info("Tamaño de array "+registrollenadosList.size());
        }
        if (registrollenadosList.size()==3) {
            LOGGER.info("Ingresa a registros llenos");
            mensaje = guardarListaRegistrosCurso(registrollenadosList);
            registrosllenos = false;
            registrollenadosList.clear();
            entra_a_registro_curso = false;
        }
        return mensaje;
    }

    public String afirmacionAdicionarPregunta(){
        //SI
        confirmation=true;
        aniade_pregunta_nueva=true;
        entra_a_registro_test=true;
        entra_a_registro_respuesta=false;
        return "Añada nuevamente su pregunta";

    }

    public String afirmacionTerminarRegistroTest(){
        //NO
                String registrertest=  saveCompleteTest(registrollenadosList,registrorespuestalist);
                registrollenadosList.clear();
                registrorespuestalist.clear();
                entra_a_registro_test=false;
                return registrertest;
    }

    public String entraARegistroTest(Update update,String messageTextReceived){
        String mensaje="";
        if (entra_a_registro_test){
                if(entra_a_registro_respuesta){
                    if(getNumero_de_respuesta()==4) {
                        registrorespuestalist.add(messageTextReceived);
                        LOGGER.info("SIIIIIIII ENTRA A REGISTRO RESPUESTA TERMINADO");
                        LOGGER.info("ENTRO AL SI CON ARRAY "+registrorespuestalist.size());
                        int i=0;
                        int contresppropregunta=4;
                        String cade="CUESTIONARIO - TEST\n";
                       for(String preg:registrollenadosList){
                            cade+=preg+"\n";
                            int numero=1;
                            while(i<contresppropregunta){
                                    cade+=numero+". "+registrorespuestalist.get(i)+"\n";
                                    numero++;
                                    i++;
                            }
                            contresppropregunta+=4;
                        }
                        setNumero_de_respuesta(0);
                       mensaje+=cade;
                       mensaje+="\nDesea añadir una nueva pregunta?";
                        entra_a_registro_respuesta=false;
//                            entra_a_registro_test=false;
                        aniade_respuesta_nueva=true;
                        aniade_pregunta_nueva=false;

                    }//Terminate NUMERO DE RESPUESTAS= 4

                    if(getNumero_de_respuesta()<4 && entra_a_registro_respuesta) {
                        //INGRESANDO A REGISTROS NO COMPLETOS
                        mensaje=mensajeRegistroRespuesta(update);
                        setNumero_de_respuesta(getNumero_de_respuesta()+1);
                        if(aniade_pregunta_nueva==false){
                            registrorespuestalist.add(messageTextReceived);
                        }
                        aniade_respuesta_nueva=false;
                    }
                }

                if(entra_a_registro_respuesta==false && aniade_pregunta_nueva) {
                    mensaje=mensajeRegistroTest(update);
                    setNumero_de_pregunta(/*mensajesBL.getNumero_de_pregunta()+1*/0);
                    entra_a_registro_respuesta=true;
                }
                if(aniade_pregunta_nueva && confirmation==false){
                    registrollenadosList.add(messageTextReceived);
                    aniade_pregunta_nueva=false;
                }
                if(confirmation==true){
/*                    String mensaje=mensajesBL.mensajeRegistroTest(update);
                    SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                            .setChatId(update.getMessage().getChatId())
                            .setText(mensaje);
                    try {
/                        this.execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    mensajesBL.setNumero_de_pregunta(/*mensajesBL.getNumero_de_pregunta()+10);*/
                    entra_a_registro_respuesta=true;
                    confirmation=false;
                }

            }// TERMINA REGISTRO TEST
        return mensaje;
    }

    public static boolean isRegistrosllenos() {
        return registrosllenos;
    }

    public static void setRegistrosllenos(boolean registrosllenos) {
        MensajesBL.registrosllenos = registrosllenos;
    }

    public static boolean isEntra_a_registro_estudiante() {
        return entra_a_registro_estudiante;
    }

    public static void setEntra_a_registro_estudiante(boolean entra_a_registro_estudiante) {
        MensajesBL.entra_a_registro_estudiante = entra_a_registro_estudiante;
    }

    public static boolean isEntra_a_registro_docente() {
        return entra_a_registro_docente;
    }

    public static void setEntra_a_registro_docente(boolean entra_a_registro_docente) {
        MensajesBL.entra_a_registro_docente = entra_a_registro_docente;
    }

    public static boolean isEntra_a_registro_curso() {
        return entra_a_registro_curso;
    }

    public static void setEntra_a_registro_curso(boolean entra_a_registro_curso) {
        MensajesBL.entra_a_registro_curso = entra_a_registro_curso;
    }

    public static boolean isEntra_a_iniciar_estudiante() {
        return entra_a_iniciar_estudiante;
    }

    public static void setEntra_a_iniciar_estudiante(boolean entra_a_iniciar_estudiante) {
        MensajesBL.entra_a_iniciar_estudiante = entra_a_iniciar_estudiante;
    }

    public static boolean isEntra_a_iniciar_docente() {
        return entra_a_iniciar_docente;
    }

    public static void setEntra_a_iniciar_docente(boolean entra_a_iniciar_docente) {
        MensajesBL.entra_a_iniciar_docente = entra_a_iniciar_docente;
    }

    public static boolean isEntra_a_iniciar_docentenombre() {
        return entra_a_iniciar_docentenombre;
    }

    public static void setEntra_a_iniciar_docentenombre(boolean entra_a_iniciar_docentenombre) {
        MensajesBL.entra_a_iniciar_docentenombre = entra_a_iniciar_docentenombre;
    }

    public static boolean isEntra_a_registro_test() {
        return entra_a_registro_test;
    }

    public static void setEntra_a_registro_test(boolean entra_a_registro_test) {
        MensajesBL.entra_a_registro_test = entra_a_registro_test;
    }

    public static boolean isEntra_a_registro_respuesta() {
        return entra_a_registro_respuesta;
    }

    public static void setEntra_a_registro_respuesta(boolean entra_a_registro_respuesta) {
        MensajesBL.entra_a_registro_respuesta = entra_a_registro_respuesta;
    }

    public static boolean isEntra_a_registro_estudiante_curso() {
        return entra_a_registro_estudiante_curso;
    }

    public static void setEntra_a_registro_estudiante_curso(boolean entra_a_registro_estudiante_curso) {
        MensajesBL.entra_a_registro_estudiante_curso = entra_a_registro_estudiante_curso;
    }

    public static boolean isAniade_pregunta_nueva() {
        return aniade_pregunta_nueva;
    }

    public static void setAniade_pregunta_nueva(boolean aniade_pregunta_nueva) {
        MensajesBL.aniade_pregunta_nueva = aniade_pregunta_nueva;
    }

    public static boolean isAniade_respuesta_nueva() {
        return aniade_respuesta_nueva;
    }

    public static void setAniade_respuesta_nueva(boolean aniade_respuesta_nueva) {
        MensajesBL.aniade_respuesta_nueva = aniade_respuesta_nueva;
    }

    public static boolean isTermina_test() {
        return termina_test;
    }

    public static void setTermina_test(boolean termina_test) {
        MensajesBL.termina_test = termina_test;
    }

    public static boolean isConfirmation() {
        return confirmation;
    }

    public static void setConfirmation(boolean confirmation) {
        MensajesBL.confirmation = confirmation;
    }

    public static List<String> getRegistrollenadosList() {
        return registrollenadosList;
    }

    public static void setRegistrollenadosList(List<String> registrollenadosList) {
        MensajesBL.registrollenadosList = registrollenadosList;
    }

    public static List<String> getRegistrorespuestalist() {
        return registrorespuestalist;
    }

    public static void setRegistrorespuestalist(List<String> registrorespuestalist) {
        MensajesBL.registrorespuestalist = registrorespuestalist;
    }

    public PersonBL getPersonBL() {
        return personBL;
    }

    public void setPersonBL(PersonBL personBL) {
        this.personBL = personBL;
    }





    /////////////////////////////////////// GUARDAR REGISTROS

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

    public  String guardarListaRegistrosCurso(List<String> listaderegistros){
        LOGGER.info("Llega al metodo con : ");

        for (String lag:listaderegistros){
            LOGGER.info("Elemento : "+lag);
        }
        DocenteEntity docenteEntity = new DocenteEntity(1);
        CursoEntity cursoEntity=new CursoEntity();
        cursoEntity.setIdDocente(docenteEntity);
        cursoEntity.setNombre(listaderegistros.get(0));
        cursoEntity.setTipoCurso(listaderegistros.get(1));
        cursoEntity.setClave(listaderegistros.get(2));
        LOGGER.info("Entidad curso "+cursoEntity.toString());
        cursoRepository.save(cursoEntity);
        return "¡Registro completado exitosamente¡";
    }


    public String saveCompleteTest(List<String> questionsList,List<String> responseList ){
        saveTest();
        saveQuestionList(questionsList);
        saveResponseList(responseList,questionsList);
        return "REGISTRO DE TEST COMPLETADO";
    }

    public void saveTest(){
        TestEntity testEntity=new TestEntity();
        CursoEntity cursoEntity= cursoRepository.findByIdCurso(8);
        LOGGER.info("ENCONTRO en save test esto "+cursoEntity.getClave()+" "+cursoEntity.getNombre()+" "+cursoEntity.getTipoCurso());
        testEntity.setIdCurso(cursoEntity);
        DocenteEntity docenteEntity=new DocenteEntity();
        docenteEntity=docenteRespository.findAllByIdDocente(1);
        testEntity.setIdDocente(docenteEntity);
        testEntity.setNombreTest("Lagos");
        testRepository.save(testEntity);
    }

    public void  saveQuestion(String question,int questionNumber){
        PreguntaEntity preguntaEntity= new PreguntaEntity();
        TestEntity testEntity=testRepository.findByNombreTest("Lagos");
        preguntaEntity.setIdTest(testEntity);
        preguntaEntity.setContenidoPregunta(question);
        preguntaEntity.setNumeroPregunta(questionNumber);
        preguntaRepository.save(preguntaEntity);
    }

    public void saveQuestionList(List<String> questionList){
        int count=1;
        for (String question:questionList){
            saveQuestion(question,count);
            count++;
        }

    }

    public  void saveResponseList(List<String> responseList,List<String> questionList)
    {
        TestEntity testEntity=testRepository.findByNombreTest("Lagos");
        int numberResponse=1;
        int indexQuestionList=0;
        PreguntaEntity preguntaEntity=preguntaRepository.findByContenidoPreguntaAndIdTest(questionList.get(indexQuestionList),testEntity);
        for(int i=0;i<responseList.size();i++){
            if(numberResponse==5 && indexQuestionList<questionList.size()-1){
                indexQuestionList++;
                preguntaEntity=preguntaRepository.findByContenidoPreguntaAndIdTest(questionList.get(indexQuestionList),testEntity);
                numberResponse=1;
                saveResponse(responseList.get(i),preguntaEntity,numberResponse);
            }
            else {
                saveResponse(responseList.get(i),preguntaEntity,numberResponse);
            }
            numberResponse++;
        }
    }

    public void  saveResponse(String response,PreguntaEntity preguntaEntity,int numberResponse){
        RespuestaEntity respuestaEntity= new RespuestaEntity();
        respuestaEntity.setIdPregunta(preguntaEntity);
        respuestaEntity.setContenidoRespuesta(response);
        respuestaEntity.setEsCorrecta(0);
        respuestaEntity.setNumeroRespuesta(numberResponse);
        respuestaRepository.save(respuestaEntity);
    }


}
