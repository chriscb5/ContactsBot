package ucb.edu.kajoybot.bo.databasekajoy.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ucb.edu.kajoybot.bo.databasekajoy.dao.EstudianteRespository;

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