package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ucb.edu.kajoybot.bo.databasekajoy.dao.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import ucb.edu.kajoybot.bo.databasekajoy.domain.*;
import ucb.edu.kajoybot.bo.databasekajoy.dto.Status;

@Service
public class BotBl {


    MensajesBL mensajesBL;

    private static final Logger LOGGER= LoggerFactory.getLogger(BotBl.class);

    private EstudianteRespository estudianteRespository;
    private DocenteRespository docenteRespository;
    private CursoRepository cursoRepository;
    private KjEstudianteUserRepository kjEstudianteUserRepository;
    private TestRepository testRepository;
    private RespuestaRepository respuestaRepository;
    private PreguntaRepository preguntaRepository;
    private ChatRepository chatRepository;
    private String nombreTest="";

    @Autowired
    public BotBl(EstudianteRespository estudianteRespository, DocenteRespository docenteRespository,
                 CursoRepository cursoRepository, KjEstudianteUserRepository kjEstudianteUserRepository,
                 TestRepository testRepository, RespuestaRepository respuestaRepository,
                 PreguntaRepository preguntaRepository, ChatRepository chatRepository,PersonBL personBL,MensajesBL mensajesBL) {
        this.estudianteRespository = estudianteRespository;
        this.docenteRespository = docenteRespository;
        this.cursoRepository = cursoRepository;
        this.kjEstudianteUserRepository = kjEstudianteUserRepository;
        this.testRepository = testRepository;
        this.respuestaRepository = respuestaRepository;
        this.preguntaRepository = preguntaRepository;
        this.chatRepository = chatRepository;
        this.mensajesBL = mensajesBL;


    }

/*    public BotBl(EstudianteRespository estudianteRespository, DocenteRespository docenteRespository, CursoRepository cursoRepository, KjEstudianteUserRepository kjEstudianteUserRepository) {
        this.estudianteRespository = estudianteRespository;
        this.docenteRespository = docenteRespository;
        this.cursoRepository = cursoRepository;
        this.kjEstudianteUserRepository = kjEstudianteUserRepository;
    }*/



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


    //intento Multi Usuario
//    public List<String> processUpdate(Update update) {
//        LOGGER.info("Recibiendo update {} ", update);
//        List<String> chatResponse = new ArrayList<>();
//        KjEstudianteUserEntity kjEstudianteUserEntity = initUser(update.getMessage().getFrom());
//        continueChatWithUser(update,kjEstudianteUserEntity,chatResponse);
//        return chatResponse;
//    }

    public /*SendMessage*/void processUpdateMesage(Update update,SendMessage message){
        LOGGER.info("RECIBIENDO UPDATE en SEND MESSAGE",update);
        KjEstudianteUserEntity kjEstudianteUserEntity = initUser(update.getMessage().getFrom());
        message.setChatId(update.getMessage().getChatId());
        //return continueChatWithUserMessage(update,kjEstudianteUserEntity,message);
        continueChatWithUserMessage(update,kjEstudianteUserEntity,message);
    }


    public /*SendMessage*/void continueChatWithUserMessage(Update update, KjEstudianteUserEntity kjEstudianteUserEntity,SendMessage sendMessage) {
        KjChatEntity lastMenssage = chatRepository.findLastChatByUserId(kjEstudianteUserEntity.getUserid());
        String messageInput = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        LOGGER.info("Ultimo mensaje "+update.getMessage().getText());
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText("DEFAULT");

        SendMessage responseMessage = new SendMessage();
        String messageTextReceived = update.getMessage().getText();
        LOGGER.info("Ultimo mensaje "+update.getMessage().getText());
        String response = "";
        String imageFile = null;
        SendPhoto sendPhoto = new SendPhoto();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();




        if(lastMenssage == null){
            message.setChatId(chatId)
                    .setText("DEFAULT por null");
        }
        else {
            int lastMessageInt = 0;
            if(messageTextReceived.equals("Si")){
                response=mensajesBL.afirmacionAdicionarPregunta();
                response+="\n\nlast mensaje received \n\n"+lastMenssage.getInMessage();
                sendMessage.setChatId(chatId).setText(response);

            }
            if(messageTextReceived.equals("No")){
                response=mensajesBL.afirmacionTerminarRegistroTest();
                response+="\n\nlast mensaje received: "+lastMenssage.getInMessage();
                sendMessage.setChatId(chatId).setText(response);
            }
            if(mensajesBL.isEntra_a_iniciar_estudiante()){
                response+=mensajesBL.iniciarEstudiante(messageTextReceived);
                response+="\n\nlast mensaje received: "+lastMenssage.getInMessage();
                sendMessage.setChatId(chatId).setText(response);
            }
            if(mensajesBL.isEntra_a_iniciar_docente()){
                response+=mensajesBL.iniciarDocente(messageTextReceived);
                response+="\n\nlast mensaje received: "+lastMenssage.getInMessage();
                sendMessage.setChatId(chatId).setText(response);
            }
            if(mensajesBL.isEntra_a_registro_estudiante()){
                response+=mensajesBL.entraRegistroEstudiante(update,messageTextReceived);
                response+="\n\nlast mensaje received: "+lastMenssage.getInMessage();
                sendMessage.setChatId(chatId).setText(response);
            }
            if(mensajesBL.isEntra_a_registro_docente()){
                response+=mensajesBL.entraRegistroDocente(update,messageTextReceived);
                response+="\n\nlast mensaje received: "+lastMenssage.getInMessage();
                sendMessage.setChatId(chatId).setText(response);
            }
            if(mensajesBL.isEntra_a_registro_curso()){
                response+=mensajesBL.entraRegistroCurso(update,messageTextReceived);
                response+="\n\nlast mensaje received: "+lastMenssage.getInMessage();
                sendMessage.setChatId(chatId).setText(response);
            }
            if(mensajesBL.isEntra_a_registro_estudiante_curso()){
//                LOGGER.info("Entra ini est curs");
//                response+=mensajesBL.entraRegistroEstudianteCurso(update,messageTextReceived,sendMessage);
                response+="\n\nlast mensaje received: "+lastMenssage.getInMessage();
//                sendMessage.setChatId(chatId);
                sendMessage=mensajesBL.entraRegistroEstudianteCurso(update,messageTextReceived,sendMessage);
            }
            if(mensajesBL.isEntra_a_listado_estudiantes()){
//                LOGGER.info("Entra inicializator listado");
                response+="\n\nlast mensaje received: "+lastMenssage.getInMessage();
                sendMessage=mensajesBL.entraListadoEstudiantes(update,messageTextReceived,sendMessage);
            }
            if(mensajesBL.isEntra_a_listado_docentes()){
//                LOGGER.info("Entra inicializator listado");
                response+="\n\nlast mensaje received: "+lastMenssage.getInMessage();
                sendMessage=mensajesBL.entraListadoDocentes(update,messageTextReceived,sendMessage);
            }
            if(mensajesBL.isEntra_a_listado_cursos()){
//                LOGGER.info("Entra inicializator listado");
                response+="\n\nlast mensaje received: "+lastMenssage.getInMessage();
                sendMessage=mensajesBL.entraListadoCursos(update,messageTextReceived,sendMessage);
            }
            if(mensajesBL.isEntra_a_registro_test()){
                response+=mensajesBL.entraARegistroTest(update,messageTextReceived);
                sendMessage.setChatId(chatId).setText(response);
            }
            if(mensajesBL.isEntra_a_responder_test()){
                if(mensajesBL.getNumero_de_pregunta_respondiendo()==1){
                    nombreTest=messageTextReceived;
                }
                sendMessage=mensajesBL.entraResponderTest(nombreTest,update.getMessage().getChat().getFirstName(),sendMessage);
            }

            try {
                switch(messageInput) {

                    case "/start":
//                        responseMessage.setChatId(chatId)
//                                .setText("Seleccione una opción por favor\nComenzar\nInformacion");
                        sendMessage.setChatId(chatId)
                                .setText("Seleccione una opción por favor\nComenzar\nInformacion");
                        row.add("Comenzar");
                        row.add("Información");
                        keyboard.add(row);
                        keyboardMarkup.setKeyboard(keyboard);
                        sendMessage.setReplyMarkup(keyboardMarkup);
//                        responseMessage.setReplyMarkup(keyboardMarkup);
//                        message = responseMessage;
                        // code block
                        break;
                    case "Información":
   //                     responseMessage.setChatId(chatId)
    //                            .setText("Seleccione una opción por favor\nComenzar\nInformacion");

                        imageFile = "https://pngimage.net/wp-content/uploads/2018/06/informaci%C3%B3n-png-1.png";
                        sendPhoto.setChatId(chatId)
                                .setPhoto(imageFile);
                        sendMessage.setChatId(chatId)
                                .setText("Somos una plataforma para crear test interactivos! \nLos docentes pueden crear test para enviarlos a sus alumnos y ver la puntuación de cada alumno \n ");

//                        responseMessage.setChatId(chatId)
//                                .setText("Somos una plataforma para crear test interactivos! \nLos docentes pueden crear test para enviarlos a sus alumnos y ver la puntuación de cada alumno \n ");

//                        message = responseMessage;
                        // code block
                        break;
                    case "Comenzar":
                        sendMessage.setChatId(chatId)
                            .setText("Eres nuevo por aqui?\nPuedes Iniciar Sesión ó Registrarte!\n\nIniciar Sesion\nRegistro");
/*                        responseMessage.setChatId(chatId)
                                .setText("Eres nuevo por aqui?\nPuedes Iniciar Sesión ó Registrarte!\n\nIniciar Sesion\nRegistro");
*/                        row.add("Iniciar sesión");
                        row.add("Registro");
                        keyboard.add(row);
                        keyboardMarkup.setKeyboard(keyboard);
                        sendMessage.setReplyMarkup(keyboardMarkup);
//                        responseMessage.setReplyMarkup(keyboardMarkup);
//                        message = responseMessage;
                        break;
                    case "Registro":
                        sendMessage.setChatId(chatId)
                                .setText("Seleccione una opción por favor\nRegistro Profesor\nRegistro Alumno");
//                        responseMessage.setChatId(chatId)
//                                .setText("Seleccione una opción por favor\nRegistro Profesor\nRegistro Alumno");
                        row.add("Registro Profesor");
                        row.add("Registro Alumno");
                        keyboard.add(row);

                        keyboardMarkup.setKeyboard(keyboard);
                        sendMessage.setReplyMarkup(keyboardMarkup);
//                        responseMessage.setReplyMarkup(keyboardMarkup);
//                        message = responseMessage;
                        break;
                    case "Iniciar sesión":
                        sendMessage.setChatId(chatId)
                                .setText("Genial! eres Docente o Estudiante?\nSoy Docente\nSoy Estudiante");
//                        responseMessage.setChatId(chatId)
//                                .setText("Genial! eres Docente o Estudiante?\nSoy Docente\nSoy Estudiante");
                        row.add("Soy Docente");
                        row.add("Soy Estudiante");
                        keyboard.add(row);
                        keyboardMarkup.setKeyboard(keyboard);
                        sendMessage.setReplyMarkup(keyboardMarkup);
//                        responseMessage.setReplyMarkup(keyboardMarkup);
//                        message = responseMessage;
                        break;
                    case "Soy Estudiante":
                        sendMessage.setChatId(chatId)
                                .setText("Iniciar como Estudiante\nEl curso es privado, ingrese la clave correspodiente");
//                        responseMessage.setChatId(chatId)
//                                .setText("Iniciar como Estudiante\nEl curso es privado, ingrese la clave correspodiente");
                        mensajesBL.setEntra_a_iniciar_estudiante(true);
//                        message = responseMessage;
                        break;
                    case "Soy Docente":
//                        response=mensajesBL.iniciarDocente(messageTextReceived);
                        mensajesBL.setEntra_a_iniciar_docente(true);;
                        sendMessage.setChatId(chatId).
                                setText("Iniciar como Docente\nEl curso es privado, ingrese la clave correspodiente");
//                        responseMessage.setChatId(chatId).
//                                setText("Iniciar como Docente\nEl curso es privado, ingrese la clave correspodiente");
//                        message = responseMessage;
                        break;
                    case "Registro Alumno":
                        mensajesBL.setEntra_a_registro_estudiante(true);
//                      entra_a_registro_estudiante = true;//FIXME celis poner la funcion
                        sendMessage.setChatId(chatId)
                                .setText("REGISTRO DE ESTUDIANTE\nPor favor ingrese sus datos personales\nIngrese su nombre");
//                        responseMessage.setChatId(chatId)
//                                .setText("REGISTRO DE ESTUDIANTE\nPor favor ingrese sus datos personales\nIngrese su nombre");
//                        message = responseMessage;
                        break;
                    case "Registro Docente":
                        mensajesBL.setEntra_a_registro_docente(true);
//                        entra_a_registro_docente = true;//FIXME celis poner la funcion
                        sendMessage.setChatId(chatId)
                                .setText("REGISTRO DE DOCENTE\nPor favor ingrese sus datos personales\nIngrese su nombre");
//                        responseMessage.setChatId(chatId)
//                                .setText("REGISTRO DE DOCENTE\nPor favor ingrese sus datos personales\nIngrese su nombre");
//                        message = responseMessage;
                        break;
                    case "Test":
                        mensajesBL.setEntra_a_registro_test(true);
                        mensajesBL.setConfirmation(false);//FIXME celis poner la funcion
                        mensajesBL.setAniade_pregunta_nueva(true);//FIXME celis poner la funcion
                        sendMessage.setChatId(chatId)
                                .setText("INGRESO DE NUEVO TEST\nPor favor ingrese los datos correspondientes\nIngrese la primera pregunta");
//                        responseMessage.setChatId(chatId)
//                                .setText("INGRESO DE NUEVO TEST\nPor favor ingrese los datos correspondientes\nIngrese la primera pregunta");
//                        message = responseMessage;
                        break;
                    case "Crear Nuevo Curso":
                        mensajesBL.setEntra_a_registro_curso(true);
//                        entra_a_registro_curso = true;//FIXME celis poner la funcion
                        sendMessage.setChatId(chatId)
                                .setText("REGISTRO DE CURSO\nPor favor ingrese los datos del curso\nIngrese el nombre del curso");
//                        responseMessage.setChatId(chatId)
//                                .setText("REGISTRO DE CURSO\nPor favor ingrese los datos del curso\nIngrese el nombre del curso");
//                        message = responseMessage;
                        break;
                    case "Registro Estudiante Curso":
                        mensajesBL.setEntra_a_registro_estudiante_curso(true);//FIXME celis poner la funcion
                        sendMessage.setChatId(chatId)
                                .setText("**Unirse a un curso**\nIngrese el codigo del curso");
//                        responseMessage.setChatId(chatId)
//                                .setText("Registro de estudiante a un curso\nIngrese el nombre del curso");
//                        message = responseMessage;
                        break;
                    case "TestR":
                        mensajesBL.setEntra_a_responder_test(true);
                        sendMessage.setChatId(chatId)
                                .setText("RESPONDER TEST\nIngrese el nombre del Test");
//                        responseMessage.setChatId(chatId)
//                                .setText("TEST RECIBIDO\nIngrese el nombre del Test");
//                        message = responseMessage;
                        break;
                    case "Listado Estudiantes":
                        mensajesBL.setEntra_a_listado_estudiantes(true);
                        sendMessage.setChatId(chatId)
                                .setText("LISTADO DE ESTUDIANTES REGISTRADOS\nIngrese cualquier tecla para continuar");
                        break;
                    case "Listado Docentes":
                        mensajesBL.setEntra_a_listado_docentes(true);
                        sendMessage.setChatId(chatId)
                                .setText("LISTADO DE DOCENTES REGISTRADOS\nIngrese cualquier tecla para continuar");
                        break;
                    case "Listado Cursos":
                        mensajesBL.setEntra_a_listado_cursos(true);
                        sendMessage.setChatId(chatId)
                                .setText("LISTADO DE CURSOS REGISTRADOS\nIngrese cualquier tecla para continuar");
                        break;
                    default:
/*                        if(sendMessage.setChatId(chatId).getText()==""){
                            sendMessage.setChatId(chatId)
                                    .setText("No logro entender lo que me pides");
                        }*/
//                        responseMessage.setChatId(chatId)
//                                .setText("TNo logro entender lo que me pides");
//                        message = responseMessage;
                }
            } catch (NumberFormatException nfe){
                sendMessage.setChatId(chatId)
                        .setText("DEFAULT");
//                message.setChatId(chatId)
//                        .setText("DEFAULT");
            }
        }

        KjChatEntity kjChatEntity = new KjChatEntity();
        kjChatEntity.setKjuserid(kjEstudianteUserEntity);
        kjChatEntity.setInMessage(update.getMessage().getText());
        kjChatEntity.setOutMessage("texto");
        kjChatEntity.setMsgDate(new Date());//FIXME arreglar la fecha del campo
        kjChatEntity.setTxDate(new Date());
        kjChatEntity.setTxUser(kjEstudianteUserEntity.getUserid().toString());
        kjChatEntity.setTxHost(update.getMessage().getChatId().toString());
        chatRepository.save(kjChatEntity);

//        return message;
    }

    private KjEstudianteUserEntity initUser(User user) {

        KjEstudianteUserEntity kjEstudianteUserEntity = kjEstudianteUserRepository.findByBotUserId(user.getId().toString());
        if (kjEstudianteUserEntity == null) {
            EstudianteEntity estudianteEntity= new EstudianteEntity();
            estudianteEntity.setNombre(user.getFirstName());
            estudianteEntity.setApellidoPaterno(user.getLastName());
            estudianteEntity.setApellidoMaterno("Garza");
            estudianteEntity.setStatuss(Status.ACTIVE.getStatus());
            estudianteEntity.setInstitucion("Ave Experanzadora");
            estudianteEntity.setTxUser("admin");
            estudianteEntity.setTxDate(new Date());
            estudianteRespository.save(estudianteEntity);
            KjEstudianteUserEntity kjEstudianteUserEntity1 = new KjEstudianteUserEntity();
            kjEstudianteUserEntity1.setBotUserId(user.getId().toString());//(user.getId().toString());
            kjEstudianteUserEntity1.setIdEstudiante(estudianteEntity);
            kjEstudianteUserEntity1.setTxHost("localhost");
            kjEstudianteUserEntity1.setTxUser("admin");
            kjEstudianteUserEntity1.setTxDate(new Date());
            kjEstudianteUserRepository.save(kjEstudianteUserEntity1);

        }
        return kjEstudianteUserEntity;
    }

    public SendMessage make2OptionsKeyboard(String a,String b,Update update,KjEstudianteUserEntity kjEstudianteUserEntity,SendMessage sendMessage){

        KjChatEntity lastMenssage = chatRepository.findLastChatByUserId(kjEstudianteUserEntity.getUserid());
        String messageInput = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        LOGGER.info("Ultimo mensaje "+update.getMessage().getText());
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText("DEFAULT");

        SendMessage responseMessage = new SendMessage();
        String messageTextReceived = update.getMessage().getText();
        LOGGER.info("Ultimo mensaje "+update.getMessage().getText());
        String response = "";

        KeyboardRow row = new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        row.add(a);
        row.add(b);
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);

        return sendMessage;
    }

/*
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
        testEntity.setNombreTest("Ciencias Sociales");
        testRepository.save(testEntity);
    }
    public void  saveQuestion(String question,int questionNumber){
        PreguntaEntity preguntaEntity= new PreguntaEntity();
        TestEntity testEntity=testRepository.findByNombreTest("Ciencias Sociales");
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
        TestEntity testEntity=testRepository.findByNombreTest("Ciencias Sociales");
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
*/

}