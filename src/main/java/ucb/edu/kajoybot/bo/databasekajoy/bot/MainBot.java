package ucb.edu.kajoybot.bo.databasekajoy.bot;

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
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;

import java.util.ArrayList;
import java.util.List;

public class MainBot extends TelegramLongPollingBot {


    EstudianteRespository estudianteRespository;

    public MainBot(EstudianteRespository estudianteRespository) {
        this.estudianteRespository = estudianteRespository;
    }

    @Override
    public void onUpdateReceived(Update update) {
        final String messageTextReceived = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();

        if (messageTextReceived.equals("/testBDD")) {
            Message message = update.getMessage();
            EstudianteEntity estudianteEntity=estudianteRespository.findById(1).get();
            SendMessage message1=new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Estudiante BBDD "+estudianteEntity);
            try {
                this.execute(message1);
            }
            catch (TelegramApiException e){
                e.printStackTrace();
            }
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

        if(messageTextReceived.equals("Comenzar")) {
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

        if(messageTextReceived.equals("Soy Alumno")) {
            SendMessage message = new SendMessage()
                    .setChatId(chatId)
                    .setText("Ingrese nombre del curso");
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if(messageTextReceived.equals("Biología")||messageTextReceived.equals("Matematica")||messageTextReceived.equals("Lenguaje")||messageTextReceived.equals("Musica")||messageTextReceived.equals("Quimica")) {
            SendMessage message = new SendMessage()
                    .setChatId(chatId)
                    .setText("Ingrese clave del curso");
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if(messageTextReceived.equals("123456")) {
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