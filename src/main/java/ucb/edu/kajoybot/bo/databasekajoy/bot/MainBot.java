package ucb.edu.kajoybot.bo.databasekajoy.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ucb.edu.kajoybot.bo.databasekajoy.bl.PersonBL;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.dto.Status;

import java.util.ArrayList;
import java.util.List;

public class MainBot extends TelegramLongPollingBot {


    PersonBL personBL;

    public MainBot(PersonBL personBL) {
        this.personBL = personBL;
    }

    @Override
    public void onUpdateReceived(Update update) {
        final String messageTextReceived = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();


        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText("Hola Soy KajoyBot");
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

            try {
                this.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (messageTextReceived.equals("/testBDD")) {
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
        }

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