package ucb.edu.kajoybot.bo.databasekajoy.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ucb.edu.kajoybot.bo.databasekajoy.bl.CursoBL;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.CursoEntity;
import ucb.edu.kajoybot.bo.databasekajoy.bl.BotBl;
import ucb.edu.kajoybot.bo.databasekajoy.bl.PersonBL;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.Status;

import java.util.ArrayList;
import java.util.List;

public class MainBot extends TelegramLongPollingBot {

    BotBl botBl;
    private static boolean registrosllenos=false;
    private static boolean entra_a_registro_estudiante=false;
    private static boolean entra_a_registro_docente=false;
    private static boolean entra_a_registro_curso=false;
    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);
    private static  List<String> registrollenadosList= new ArrayList<>();
    public static int indicador=0;



    @Autowired
    public  MainBot(BotBl botBl){
        this.botBl=botBl;
    }


    @Override
    public void onUpdateReceived(Update update) {
        final String messageTextReceived = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();


        ///Test conexión a base de Datos

        /*if (messageTextReceived.equals("/testCurso")) {
            //Message message3 = update.getMessage();
            CursoEntity cursoEntity=cursoBL.findByIdCurso(1);
            SendMessage message4=new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Curso in BBDD: "+cursoEntity);
            try {
                this.execute(message4);*/
        LOGGER.info("Entro a registro es "+entra_a_registro_estudiante);

        update.getMessage().getFrom().getId();

        LOGGER.info("Se recibio "+messageTextReceived);
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (entra_a_registro_estudiante) {
                LOGGER.info("Entra a el registro estudiante oficial");
                    if(registrollenadosList.size()<5)
                    {
                        LOGGER.info("Entra al registros no llenos");
                        if(BotBl.getNumero_de_preguna()<4){
                            String mensaje = botBl.MensajesDeRegistro(update);
                            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                                    .setChatId(update.getMessage().getChatId())
                                    .setText(mensaje);
                            try {
                                this.execute(message);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }
                        BotBl.setNumero_de_preguna(BotBl.getNumero_de_preguna()+1) ;//
                        registrollenadosList.add(messageTextReceived);
                        LOGGER.info("Tamaño de array "+registrollenadosList.size());
                    }
                     if (registrollenadosList.size()==5) {
                        LOGGER.info("Ingresa a registros llenos");
                        String mensajecomp = botBl.guardarListaRegistros(registrollenadosList);
                        SendMessage message2 = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText(mensajecomp);
                        try {
                            this.execute(message2);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        registrosllenos = false;
                        registrollenadosList.clear();
                        entra_a_registro_estudiante = false;
                    }
            }
            if (entra_a_registro_docente) {
                LOGGER.info("Entra a el registro estudiante oficial");
                if(registrollenadosList.size()<4)
                {
                    LOGGER.info("Entra al registros no llenos");
                    if(BotBl.getNumero_de_preguna()<3){
                        String mensaje = botBl.MensajesDeRegistroDocente(update);
                        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                                .setChatId(update.getMessage().getChatId())
                                .setText(mensaje);
                        try {
                            this.execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    BotBl.setNumero_de_preguna(BotBl.getNumero_de_preguna()+1) ;//
                    registrollenadosList.add(messageTextReceived);
                    LOGGER.info("Tamaño de array "+registrollenadosList.size());
                }
                if (registrollenadosList.size()==4) {
                    LOGGER.info("Ingresa a registros llenos");
                    String mensajecomp = botBl.guardarListaRegistrosDocente(registrollenadosList);
                    SendMessage message2 = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText(mensajecomp);
                    try {
                        this.execute(message2);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    registrosllenos = false;
                    registrollenadosList.clear();
                    entra_a_registro_docente = false;
                }
            }
            if (entra_a_registro_curso) {
                LOGGER.info("Entra a el registro curso oficial");
                if(registrollenadosList.size()<3)
                {
                    LOGGER.info("Entra al registros no llenos");
                    if(BotBl.getNumero_de_preguna()<2){
                        String mensaje = botBl.MensajesDeRegistroCurso(update);
                        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                                .setChatId(update.getMessage().getChatId())
                                .setText(mensaje);
                        try {
                            this.execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    BotBl.setNumero_de_preguna(BotBl.getNumero_de_preguna()+1) ;//
                    registrollenadosList.add(messageTextReceived);
                    LOGGER.info("Tamaño de array "+registrollenadosList.size());
                }
                if (registrollenadosList.size()==3) {
                    LOGGER.info("Ingresa a registros llenos");
                    String mensajecomp = botBl.guardarListaRegistrosCurso(registrollenadosList);
                    SendMessage message2 = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText(mensajecomp);
                    try {
                        this.execute(message2);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    registrosllenos = false;
                    registrollenadosList.clear();
                    entra_a_registro_curso = false;
                }
            }

            /*            if (entra_a_registro_curso){
                if (BotBl.numero_de_preguna == 3) {
                    BotBl.numero_de_preguna = 0;
                    registrosllenos = true;
                }
                if (registrosllenos) {
                    String mensajecomp = botBl.guardarListaRegistrosCurso(registrollenadosList);
                    SendMessage message = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText(mensajecomp);
                    try {
                        this.execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    registrosllenos = false;
                    registrollenadosList.clear();
                    entra_a_registro_curso = false;
                    indicador=0;
                } else
                {
                    if(indicador>0){

                        String mensaje = botBl.MensajesDeRegistroCurso(update);
                        SendMessage message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .setText(mensaje);
                        try {
                            this.execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        registrollenadosList.add(messageTextReceived);
                        BotBl.numero_de_preguna += 1;
                    }
                    indicador++;
                }
            }*/

            botBl.processUsuario(update);
//            LOGGER.info("Registro de usuario exitoso");

        if (messageTextReceived.equals("/start")) {
            String imageFile= "https://beeimg.com/images/r29284261002.png";

            SendPhoto sendPhoto = new SendPhoto()
                    .setChatId(chatId)
                    .setPhoto(imageFile);

            SendMessage message = new SendMessage()
                    .setChatId(chatId)
                    .setText("Seleccione una opción por favor");
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow row = new KeyboardRow();
            row.add("Comenzar");
            row.add("Información");
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);
            message.setReplyMarkup(keyboardMarkup);

            try {
                execute(sendPhoto);
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        //Información
        if(messageTextReceived.equals("Información")) {
            String imageFile = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Info_icon_002.svg/480px-Info_icon_002.svg.png";
            SendPhoto sendPhoto = new SendPhoto()
                    .setChatId(chatId)
                    .setPhoto(imageFile);

            SendMessage message = new SendMessage()
                    .setChatId(chatId)
                    .setText("Somos una plataforma para crear test interactivos! \n Los docentes pueden crear test para enviarlos a sus alumnos y ver la puntuación de cada alumno \n ");
            try {
                execute(sendPhoto);
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        //comenzar
        if(messageTextReceived.equals("Comenzar")) {
            SendMessage message = new SendMessage()
                    .setChatId(chatId)
                    .setText("Seleccione una opción por favor");

                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                row.add("Inicio de sesión");
                row.add("Registro");
                keyboard.add(row);

                keyboardMarkup.setKeyboard(keyboard);
                message.setReplyMarkup(keyboardMarkup);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
        }


        if (messageTextReceived.equals("Registro")) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("Seleccione una opción por favor");

                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                row.add("Soy Profesor");
                row.add("Soy Alumno");
                keyboard.add(row);

                keyboardMarkup.setKeyboard(keyboard);
                message.setReplyMarkup(keyboardMarkup);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


            if (messageTextReceived.equals("Soy Alumno")) {
                entra_a_registro_estudiante = true;
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("REGISTRO DE ESTUDIANTE\nPor favor ingrese sus datos personales\nIngrese su nombre");

                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            LOGGER.info("Entro a registro es "+entra_a_registro_estudiante);


            if (messageTextReceived.equals("Soy Profesor")) {
                entra_a_registro_docente = true;
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("REGISTRO DE DOCENTE\nPor favor ingrese sus datos personales\nIngrese su nombre");
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (messageTextReceived.equals("Crear Nuevo Curso")) {
                entra_a_registro_curso = true;
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("REGISTRO DE CURSO\nPor favor ingrese los datos del curso\nIngrese el nombre del curso");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


            if (messageTextReceived.equals("Biología") || messageTextReceived.equals("Matematica") || messageTextReceived.equals("Lenguaje") || messageTextReceived.equals("Musica") || messageTextReceived.equals("Quimica")) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("Ingrese clave del curso");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (messageTextReceived.equals("123456")) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("Bienvenido a Biologia");
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Override
    public String getBotUsername() {
        return "Kajoybot";
    }

    @Override
    public String getBotToken() {
        return "883396045:AAFnccy-vbkbg7dxuqzs7XkvhjYbqw78n4o";
    }



    @Override
    public void clearWebhook() throws TelegramApiRequestException {
        System.out.println("Se invoco clearWebhook");
    }

}