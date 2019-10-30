package ucb.edu.kajoybot.bo.databasekajoy.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;

import java.util.ArrayList;
import java.util.List;

public class MainBot extends TelegramLongPollingBot {
    private static final String INGRESAR_CURSO = "Ingresar a un curso";
    private static final String REGISTRARSE = "Registrarse";
    private static final String SALIR = "SALIR";

    public MainBot() {

    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage()) {
                Message message = update.getMessage();
                if (message.hasText() || message.hasLocation()) {
                    long chat_id = update.getMessage().getChatId();
                    this.handleIncomingMessage(message, update);
                }
            }
        } catch (TelegramApiException var5) {
            BotLogger.error("LOGTAG", var5);
        }

    }

    private void handleIncomingMessage(Message message, Update update) throws TelegramApiException {
        SendMessage sendMessageGreeting = (new SendMessage()).setChatId(update.getMessage().getChatId());
        String var4 = message.getText();
        byte var5 = -1;
        switch(var4.hashCode()) {
            case -453901345:
                if (var4.equals("Registrarse")) {
                    var5 = 2;
                }
                break;
            case -338285543:
                if (var4.equals("/pregunta")) {
                    var5 = 1;
                }
                break;
            case 2255068:
                if (var4.equals("Hola")) {
                    var5 = 0;
                }
                break;
            case 78664039:
                if (var4.equals("SALIR")) {
                    var5 = 3;
                }
        }

        switch(var5) {
            case 0:
                System.out.println(message.getChat().getFirstName());
                sendMessageGreeting = (new SendMessage()).setChatId(update.getMessage().getChatId()).setText("Que tal " + message.getChat().getFirstName() + ", mi nombre es Kajoy, sere tu acompañante para navegar por las grandes aventuras de la apliaciòn Kajoy, elege una de las opciones que aparecen a continuación ");
                this.setButtons(sendMessageGreeting);
                break;
            case 1:
                sendMessageGreeting = (new SendMessage()).setChatId(update.getMessage().getChatId()).setText("Que tal " + message.getChat().getFirstName() + ", mi nombre es Kajoy, sere tu acompañante para navegar por las grandes aventuras de la apliaciòn Kajoy, elege una de las opciones que aparecen a continuación ");
                sendMessageGreeting.setReplyMarkup(this.etMenu(sendMessageGreeting, update.getMessage().getChatId()));
                SendMessage messageo = (new SendMessage()).setChatId(update.getMessage().getChatId()).setText("Menu");
                sendMessageGreeting = (new SendMessage()).setChatId(update.getMessage().getChatId()).setReplyMarkup(this.etMenu(sendMessageGreeting, update.getMessage().getChatId()));
                sendMessageGreeting.setText("¿Cuánto es dos mas dos?");
            case 2:
            case 3:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + message.getText());
        }

        this.execute(sendMessageGreeting);
    }

    public ReplyKeyboardMarkup etMenu(SendMessage message, long chat_id) throws TelegramApiException {
        message = (new SendMessage()).setChatId(chat_id).setChatId("Opciones");
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList();
        KeyboardRow row = new KeyboardRow();
        row.add("5");
        row.add("3");
        row.add("4");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("8");
        row.add("9");
        row.add("10");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public synchronized void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Registrarse"));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("Ingresar a un curso"));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("SALIR"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboard);




    }

    @Override
    public String getBotUsername() {
        return "Kajoybot";
    }

    @Override
    public String getBotToken() {
        return "883396045:AAFnccy-vbkbg7dxuqzs7XkvhjYbqw78n4o";    }
}
