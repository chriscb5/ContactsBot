package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ucb.edu.kajoybot.bo.databasekajoy.dao.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import ucb.edu.kajoybot.bo.databasekajoy.domain.*;
import ucb.edu.kajoybot.bo.databasekajoy.dto.MensajeDto;
import ucb.edu.kajoybot.bo.databasekajoy.dto.Status;

@Service
public class BotBl {

    private static final Logger LOGGER= LoggerFactory.getLogger(BotBl.class);

    private EstudianteRespository estudianteRespository;
    private DocenteRespository docenteRespository;
    private CursoRepository cursoRepository;
    private KjEstudianteUserRepository kjEstudianteUserRepository;
    private KjUserRepository kjUserRepository;
    private TestRepository testRepository;
    private RespuestaRepository respuestaRepository;
    private PreguntaRepository preguntaRepository;
    private ChatRepository chatRepository;
    private String nombreTest="";
    public  Boolean firstMessage = true;
    MensajesBL mensajesBL;
    ContactsBL contactsBL;

    @Autowired
    public BotBl(EstudianteRespository estudianteRespository, DocenteRespository docenteRespository,
                 CursoRepository cursoRepository, KjEstudianteUserRepository kjEstudianteUserRepository, KjUserRepository kjUserRepository,
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
        this.kjUserRepository=kjUserRepository;


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


    public void processUpdateMesage(Update update,SendMessage message, SendPhoto photo){
        MensajeDto mensajeDto;
        LOGGER.info("RECIBIENDO UPDATE en SEND MESSAGE",update);
//        KjEstudianteUserEntity kjEstudianteUserEntity = initUser(update.getMessage().getFrom());
        KjUserEntity kjEstudianteUserEntity = initUser(update.getMessage().getFrom());
        message.setChatId(update.getMessage().getChatId());
        photo.setChatId(update.getMessage().getChatId());
        continueChatWithUserMessage(update,kjEstudianteUserEntity,message,photo);

    }


    public void continueChatWithUserMessage(Update update, /*KjEstudianteUserEntity kjEstudianteUserEntity*/KjUserEntity kjUserEntity,SendMessage sendMessage,SendPhoto sendPhoto) {//        MensajeDto mensajeDto = new MensajeDto();
//        KjChatEntity lastMenssage = chatRepository.findLastChatByUserId(kjEstudianteUserEntity.getUserid());
        KjChatEntity lastMenssage = chatRepository.findLastChatByUserId(kjUserEntity.getUserid());
        String messageInput = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        String messageTextReceived = update.getMessage().getText();
        LOGGER.info("Ultimo mensaje "+update.getMessage().getText());
        String imageFile = null;
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        sendMessage.setChatId(chatId);
        if(lastMenssage == null){
            sendMessage.setChatId(chatId)
                    .setText("DEFAULT por null");
        }
        else {
            if (messageInput.equals("/start") || firstMessage==false){
                firstMessage = false;
                setModulesMessages(update,sendMessage,messageTextReceived);
                try {
                    switch(messageInput) {
                        case "Teclado":
                            sendMessage.setChatId(chatId)
                                    .setText("Teclado removido");
                            sendMessage.setReplyMarkup(replyKeyboardRemove);
                            break;
                        case "Men":
                            LOGGER.info("ENTRO A PRUEBA DE MULTIMENSAJE");
                            sendMessage.setChatId(chatId)
                                    .setText("MULTIMENSAJE");
                            row.add("Comenzar");
                            row.add("Información");
                            keyboard.add(row);
                            keyboardMarkup.setKeyboard(keyboard);
                            sendMessage.setReplyMarkup(keyboardMarkup);

                            imageFile = "https://image.shutterstock.com/z/stock-vector-bienvenido-welcome-spanish-text-lettering-vector-illustration-1050015260.jpg";
                            sendPhoto.setChatId(chatId)
                                    .setPhoto(imageFile);

                            break;

                        case "/start":
                            sendMessage.setChatId(chatId)
                                    .setText("Elija el una de la siguientes opciones:\nContacts\nKajoy");
                            row.add("Contacts");
                            row.add("Kajoy");
                            keyboard.add(row);
                            keyboardMarkup.setKeyboard(keyboard);
                            sendMessage.setReplyMarkup(keyboardMarkup);

                            break;

                        case "Contacts":
                            imageFile = "https://image.shutterstock.com/image-vector/welcome-poster-spectrum-brush-strokes-260nw-1146069941.jpg";
                            sendPhoto.setChatId(chatId)
                                    .setPhoto(imageFile);
                            sendMessage.setChatId(chatId)
                                    .setText("Seleccione una opción:\nBuscar Contactos\nAgregar Contactos\nModificar Contactos\nEliminar Contactos");
                            KeyboardRow keyboardRow = new KeyboardRow();
                            KeyboardRow keyboardRow2 = new KeyboardRow();
                            KeyboardRow keyboardRow3 = new KeyboardRow();
                            KeyboardRow keyboardRow4 = new KeyboardRow();
                            keyboardRow.add("Buscar Contactos");
                            keyboardRow2.add("Agregar Contactos");
                            keyboardRow3.add("Modificar Contactos");
                            keyboardRow4.add("Eliminar Contactos");
                            keyboard.add(keyboardRow);
                            keyboard.add(keyboardRow2);
                            keyboard.add(keyboardRow3);
                            keyboard.add(keyboardRow4);
                            keyboardMarkup.setKeyboard(keyboard);
                            sendMessage.setReplyMarkup(keyboardMarkup);

                            break;

                        case "Agregar Contactos":
                            mensajesBL.setEntra_a_agregar_contactos(true);
                            sendMessage.setChatId(chatId)
                                    .setText("*Agregar Contactos*\nIngrese el primer nombre").setParseMode("Markdown");

                            break;

                        case "Kajoy":
                            imageFile = "https://image.shutterstock.com/z/stock-vector-bienvenido-welcome-spanish-text-lettering-vector-illustration-1050015260.jpg";
                            sendPhoto.setChatId(chatId)
                                    .setPhoto(imageFile);
                            sendMessage.setChatId(chatId)
                                    .setText("Seleccione una opción por favor\nComenzar\nInformacion");
                            row.add("Comenzar");
                            row.add("Información");
                            keyboard.add(row);
                            keyboardMarkup.setKeyboard(keyboard);
                            sendMessage.setReplyMarkup(keyboardMarkup);

                            break;

                        case "Información":
                            imageFile = "https://pngimage.net/wp-content/uploads/2018/06/informaci%C3%B3n-png-1.png";
                            sendPhoto.setChatId(chatId)
                                    .setPhoto(imageFile);
                            sendMessage.setChatId(chatId)
                                    .setText("Somos una plataforma para crear test interactivos! \nLos docentes pueden crear test para enviarlos a sus alumnos y ver la puntuación de cada alumno \n ");
                            break;
                        case "Comenzar":
                            sendMessage.setChatId(chatId)
                                    .setText("Bienvenido!!\nEres Docente o Estudiante");
                            row.add("Soy Docente");
                            row.add("Soy Estudiante");
                            keyboard.add(row);
                            keyboardMarkup.setKeyboard(keyboard);
                            sendMessage.setReplyMarkup(keyboardMarkup);
                            break;

                        case "Soy Estudiante":
                            sendMessage.setChatId(chatId)
                                    .setText("Iniciar como Estudiante\nEl curso es privado, ingrese la clave correspodiente");

                            mensajesBL.setEntra_a_verificacion_estudiante(true);


                            //Identificar si el usuario existe
                            //si es nuevo pedir registro
                            //si es antiguo mostrar el listado de sus cursos
                            //.setText("Eres nuevo por aqui?\nPuedes Iniciar Sesión ó Registrarte!\n\nIniciar Sesion\nRegistro");

                            mensajesBL.setEntra_a_iniciar_estudiante(true);
                            break;



                        case "Soy Docente":
                            mensajesBL.setEntra_a_iniciar_docente(true);;
                            sendMessage.setChatId(chatId).
                                    setText("Iniciar como Docente\nLISTADO DE CURSOS\n");
                            //Identificar si el usuario existe
                            //si es nuevo pedir registro
                            //si es antiguo mostrar el listado de sus cursos
                            break;


                        case "Listado Estudiantes":
                            mensajesBL.setEntra_a_listado_estudiantes(true);
                            sendMessage.setChatId(chatId)
                                    .setText("LISTADO DE ESTUDIANTES REGISTRADOS\nIngrese cualquier tecla para continuar");
                            break;

                        case "Listado Cursos":
                            mensajesBL.setEntra_a_listado_cursos(true);
                            sendMessage.setChatId(chatId)
                                    .setText("LISTADO DE CURSOS REGISTRADOS\nIngrese cualquier tecla para continuar");
                            break;

                        case "Listado Docentes":
                            mensajesBL.setEntra_a_listado_docentes(true);
                            sendMessage.setChatId(chatId)
                                    .setText("LISTADO DE DOCENTES REGISTRADOS\nIngrese cualquier tecla para continuar");
                            break;


                        case "Registro":
                            sendMessage.setChatId(chatId)
                                    .setText("Seleccione una opción por favor\nRegistro Docente\nRegistro Estudiante");
                            row.add("Registro Docente");
                            row.add("Registro Estudiante");
                            keyboard.add(row);
                            keyboardMarkup.setKeyboard(keyboard);
                            sendMessage.setReplyMarkup(keyboardMarkup);
                            break;

                        case "Iniciar sesión":
                            sendMessage.setChatId(chatId)
                                    .setText("Genial! eres Docente o Estudiante?\nSoy Docente\nSoy Estudiante");
                            row.add("Soy Docente");
                            row.add("Soy Estudiante");
                            keyboard.add(row);
                            keyboardMarkup.setKeyboard(keyboard);
                            sendMessage.setReplyMarkup(keyboardMarkup);
                            break;

                        case "Registro Estudiante":
                            mensajesBL.setEntra_a_registro_estudiante(true);
                            sendMessage.setChatId(chatId)
                                    .setText("REGISTRO DE ESTUDIANTE\nPor favor ingrese sus datos personales\nIngrese su nombre");
                            break;
                        case "Registro Docente":
                            mensajesBL.setEntra_a_registro_docente(true);
                            sendMessage.setChatId(chatId)
                                    .setText("REGISTRO DE DOCENTE\nPor favor ingrese sus datos personales\nIngrese su nombre");
                            break;
                        case "Crear nuevo test":
                            mensajesBL.setEntra_a_registro_test(true);
                            mensajesBL.setConfirmation(false);
                            mensajesBL.setAniade_pregunta_nueva(true);
                            mensajesBL.setEntra_a_menu_curse_docent(false);
                            sendMessage.setChatId(chatId)
                                    .setText("INGRESO DE NUEVO TEST\nPor favor ingrese los datos correspondientes\nIngrese la primera pregunta");
                            break;
                        case "Crear Nuevo Curso":
                            mensajesBL.setEntra_a_registro_curso(true);
                            mensajesBL.setEntra_a_menu_docent(false);
                            sendMessage.setChatId(chatId)
                                    .setText("REGISTRO DE CURSO\nPor favor ingrese los datos del curso\nIngrese el nombre del curso");
                            break;
                        case "Registro Estudiante Curso":
                            mensajesBL.setEntra_a_registro_estudiante_curso(true);
                            sendMessage.setChatId(chatId)
                                    .setText("**Unirse a un curso**\nIngrese el codigo del curso");
                            break;
                        case "Responder test":
                            mensajesBL.setEntra_a_menu_testcurse_student(false);
                            mensajesBL.setEntra_a_menu_curse_docent(false);
                            mensajesBL.setEntra_a_responder_test(true);
                            mensajesBL.setEntra_a_menu_curse_student(false);
                            sendMessage.setChatId(chatId)
                                    .setText("RESPONDER TEST\nIngrese el nombre del Test");
                            break;
                        case "Listado de test en el curso":
//                            mensajesBL.ListadoDeTest(sendMessage,update.getMessage().getChat().getFirstName(),update.getMessage().getChat().getLastName());
                            mensajesBL.ListadoDeTest(sendMessage,"kevin","Cosner");
                            mensajesBL.setEntra_a_menu_curse_student(false);
                            break;
                        case "Menu estudiante":
                            mensajesBL.setEntra_a_menu_student(true);
                            break;
                        case "Lista de cursos inscrito":
                            mensajesBL.setEntra_a_menu_curse_student(true);
                            mensajesBL.setEntra_a_menu_student(false);
                            break;
                        case "Menu de docente":
                            mensajesBL.setEntra_a_menu_docent(true);
                            mensajesBL.processMainDocent(sendMessage);
                            break;
                        case "Lista de cursos":
                            mensajesBL.setEntra_a_menu_curse_docent(true);
                            mensajesBL.setEntra_a_menu_docent(false);
                            break;
                        case "Menu estudiante 2":
                            mensajesBL.setEntra_a_menu_curse_student(true);
                            mensajesBL.processMainStudentInACurse(sendMessage);
                            break;
                        case "Menu docente 2":
                            mensajesBL.setEntra_a_menu_curse_docent(true);
                            mensajesBL.processMainDocentInACurse(sendMessage);
                            break;
                        case "Menu estudiante 3":
                            mensajesBL.setEntra_a_menu_testcurse_student(true);
                            mensajesBL.processMainStudentInATestCurse(sendMessage);
                            break;
                            //                        default:

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
                }
            }
            else {
                sendMessage.setChatId(chatId)
                        .setText("Hola, para empezar el bot por favor escribe /start");
            }

        }
        KjChatEntity kjChatEntity = new KjChatEntity();
//        kjChatEntity.setKjuserid(kjEstudianteUserEntity);
        kjChatEntity.setKjuserid(kjUserEntity);
        kjChatEntity.setInMessage(update.getMessage().getText());
        kjChatEntity.setOutMessage("texto");
        kjChatEntity.setMsgDate(new Date());//FIXME arreglar la fecha del campo
        kjChatEntity.setTxDate(new Date());
//        kjChatEntity.setTxUser(kjEstudianteUserEntity.getUserid().toString());
        kjChatEntity.setTxUser(kjUserEntity.getUserid().toString());
        kjChatEntity.setTxHost(update.getMessage().getChatId().toString());
        chatRepository.save(kjChatEntity);
    }
/*
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
<<<<<<< HEAD
*/
    private KjUserEntity initUser(User user) {

        KjUserEntity kjUserEntity = kjUserRepository.findByBotUserId(user.getId().toString());
        if (kjUserEntity == null) {
            KjUserEntity kjUserEntity1 = new KjUserEntity();
            kjUserEntity1.setBotUserId(user.getId().toString());//(user.getId().toString());
            kjUserEntity1.setNombreUser(user.getFirstName());
            kjUserEntity1.setApellidoUser(user.getLastName());
            kjUserEntity1.setTxHost("localhost");
            kjUserEntity1.setTxDate(new Date());
            kjUserRepository.save(kjUserEntity1);
        }
        return kjUserEntity;
    }

    private boolean existsCursoByIdCurso(String id){
        Boolean exists = false;
        CursoEntity cursoEntity = cursoRepository.findByIdCurso(Integer.parseInt(id));
        if (cursoEntity==null){
            LOGGER.info("Returns NULL");
            exists = false;
        }else {
            LOGGER.info(cursoEntity.getNombre());
            exists = true;
        }
        return exists;
    }


    private String getNombreCurso(String id){
        CursoEntity cursoEntity = cursoRepository.findByIdCurso(Integer.parseInt(id));
        return cursoEntity.getNombre();
    }

    private String getTipoCurso(String id){
        CursoEntity cursoEntity = cursoRepository.findByIdCurso(Integer.parseInt(id));
        return cursoEntity.getTipoCurso();
    }


    private void setModulesMessages(Update update,SendMessage sendMessage,String messageTextReceived){
        if(messageTextReceived.equals("Si")){
            sendMessage.setText(mensajesBL.afirmacionAdicionarPregunta());
        }
        if(messageTextReceived.equals("No")){
            sendMessage.setText(mensajesBL.afirmacionTerminarRegistroTest());
        }
        if(mensajesBL.isEntra_a_iniciar_estudiante()){
            sendMessage.setText(mensajesBL.iniciarEstudiante(messageTextReceived));
        }
        if(mensajesBL.isEntra_a_iniciar_docente()){
            sendMessage.setText(mensajesBL.iniciarDocente(messageTextReceived));
        }
        if(mensajesBL.isEntra_a_registro_estudiante()){
            sendMessage.setText(mensajesBL.entraRegistroEstudiante(sendMessage,messageTextReceived));
        }
        if(mensajesBL.isEntra_a_verificacion_estudiante()){
            sendMessage.setText(mensajesBL.entraverficarEstudiante(sendMessage,messageTextReceived));
        }
        if(mensajesBL.isEntra_a_registro_docente()){
            sendMessage.setText(mensajesBL.entraRegistroDocente(sendMessage,messageTextReceived));
        }
        if(mensajesBL.isEntra_a_registro_curso()){
            mensajesBL.entraRegistroCurso(sendMessage,messageTextReceived);
        }
        if(mensajesBL.isEntra_a_registro_estudiante_curso()){
            mensajesBL.entraRegistroEstudianteCurso(messageTextReceived,sendMessage);
        }
        if(mensajesBL.isEntra_a_listado_estudiantes()){
            mensajesBL.entraListadoEstudiantes(sendMessage);
        }
        if(mensajesBL.isEntra_a_listado_docentes()){
            mensajesBL.entraListadoDocentes(sendMessage);
        }
        if(mensajesBL.isEntra_a_listado_cursos()){
            mensajesBL.entraListadoCursos(sendMessage);
        }
        if(mensajesBL.isEntra_a_agregar_contactos()){
            mensajesBL.entraAgregarContactos(messageTextReceived,sendMessage);
        }
        if(mensajesBL.isEntra_a_registro_test()){
            mensajesBL.entraARegistroTest(sendMessage,messageTextReceived);
        }
        if(mensajesBL.isEntra_a_responder_test()){
            if(mensajesBL.getNumero_de_pregunta_respondiendo()==1){
                nombreTest=messageTextReceived;
            }
            mensajesBL.entraResponderTest(nombreTest,update.getMessage().getChat().getFirstName(),sendMessage);
        }
        if (mensajesBL.isEntra_a_menu_student()) {
            mensajesBL.processMainStudent(sendMessage);
        }
        if(mensajesBL.isEntra_a_menu_curse_student()){
            mensajesBL.processMainStudentInACurse(sendMessage);
        }
        if(mensajesBL.isEntra_a_menu_docent()){
            mensajesBL.processMainDocent(sendMessage);
        }
        if(mensajesBL.isEntra_a_menu_curse_docent()){
            mensajesBL.processMainDocentInACurse(sendMessage);
        }
        if(mensajesBL.isEntra_a_menu_testcurse_student()){
            mensajesBL.processMainStudentInATestCurse(sendMessage);
        }
    }

}