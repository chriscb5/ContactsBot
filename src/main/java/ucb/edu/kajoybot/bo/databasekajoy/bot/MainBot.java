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
    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);
    private static  List<String> registrollenadosList= new ArrayList<>();
    public static int indicador=0;



    @Autowired
    public  MainBot(BotBl botBl){
        this.botBl=botBl;
    }

//    PersonBL personBL;

  //  public MainBot(PersonBL personBL) {
    //    this.personBL = personBL;
    //}

    @Override
    public void onUpdateReceived(Update update) {
        final String messageTextReceived = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();

        LOGGER.info("Comenzando conversacion");

        LOGGER.info("Tamaño de array"+registrollenadosList.size());
        update.getMessage().getFrom().getId();

        if (update.hasMessage() && update.getMessage().hasText()) {

      /*      EstudianteEntity estudianteEntity= personBL.findEstudianteByPk(Status.ACTIVE.getStatus());
            SendMessage message1=new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Estudiante BBDD "+estudianteEntity);
            try {
                this.execute(message1);
            }
            catch (TelegramApiException e){
                e.printStackTrace();
            }

            try {
                this.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
*/
/*        if (messageTextReceived.equals("/testBDD")) {
=======
        if (messageTextReceived.equals("/testBDD")) {
>>>>>>> 72d848194d3747fe8e175f86849e7c69083b1557
            Message message = update.getMessage();
            EstudianteEntity estudianteEntity= personBL.findEstudianteByPk(Status.ACTIVE.getStatus());
            SendMessage message1=new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Estudiante BBDD "+estudianteEntity);
            try {
                this.execute(message1);
            }
            catch (TelegramApiException e){
                e.printStackTrace();
            }
<<<<<<< HEAD
        }*/

            if (messageTextReceived.equals("/start")) {
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
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (messageTextReceived.equals("Información")) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("Hola, somos una plataforma para crear test interactivos! \n Los docentes pueden crear test para enviarlos a sus alumnos y ver la puntuación de cada alumnos \n ");
        }
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
            row.add("https://i0.pngocean.com/files/953/498/553/%E3%83%81%E3%83%A3%E3%83%BC%E3%83%88%E5%BC%8F-%E6%95%B0%E5%AD%A6-middle-school-juku-educational-entrance-examination-teacher-man.jpg");
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

        if(messageTextReceived.equals("Información")) {
            SendMessage message = new SendMessage()
                    .setChatId(chatId)
                    .setText("Hola, somos una plataforma para crear test interactivos! \n Los docentes pueden crear test para enviarlos a sus alumnos y ver la puntuación de cada alumnos \n ");

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (messageTextReceived.equals("Comenzar")) {
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


            if (messageTextReceived.equals("Biología") || messageTextReceived.equals("Matematica") || messageTextReceived.equals("Lenguaje") || messageTextReceived.equals("Musica") || messageTextReceived.equals("Quimica")) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("Ingrese clave del curso");
                try {
                    execute(message); // Sending our message object to user
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
            if (entra_a_registro_estudiante) {
                if (BotBl.numero_de_preguna == 5) {
                    BotBl.numero_de_preguna = 0;
                    registrosllenos = true;
                }
                if (registrosllenos) {
                    String mensajecomp = botBl.guardarListaRegistros(registrollenadosList);
                    SendMessage message2 = new SendMessage() // Create a SendMessage object with mandatory fields
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
                    indicador=0;
                } else
                    {
                        if(indicador>0){

                                String mensaje = botBl.MensajesDeRegistro(update);
                                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
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
            }
            if (entra_a_registro_docente) {
                if (BotBl.numero_de_preguna == 4) {
                    BotBl.numero_de_preguna = 0;
                    registrosllenos = true;
                }
                if (registrosllenos) {
                    String mensajecomp = botBl.guardarListaRegistrosDocente(registrollenadosList);
                    SendMessage message3 = new SendMessage() // Create a SendMessage object with mandatory fields
                            .setChatId(update.getMessage().getChatId())
                            .setText(mensajecomp);
                    try {
                        this.execute(message3);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    registrosllenos = false;
                    registrollenadosList.clear();
                    entra_a_registro_docente = false;
                    indicador=0;
                } else
                {
                    if(indicador>0){

                        String mensaje = botBl.MensajesDeRegistroDocente(update);
                        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
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

/*                    for (String lag:registrollenadosList){
                        LOGGER.info("Elemento : "+lag);
                    }
*/
                }

            }
        }


    }

    @Override
    public String getBotUsername() {
        return "chemobot";
    }

    @Override
    public String getBotToken() {
        return "953510535:AAGxU_5R9PdOQUmz6lRI-fWZsUzkYPfHCIA";
    }



    @Override
    public void clearWebhook() throws TelegramApiRequestException {
        System.out.println("Se invoco clearWebhook");
    }

}