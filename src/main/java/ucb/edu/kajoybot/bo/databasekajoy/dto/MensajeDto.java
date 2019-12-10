package ucb.edu.kajoybot.bo.databasekajoy.dto;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

public class MensajeDto {
    private SendMessage sendMessage;
    private SendPhoto sendPhoto;

    public MensajeDto() {
    }

    public MensajeDto(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public MensajeDto(SendPhoto sendPhoto) {
        this.sendPhoto = sendPhoto;
    }

    public MensajeDto(SendMessage sendMessage, SendPhoto sendPhoto) {
        this.sendMessage = sendMessage;
        this.sendPhoto = sendPhoto;
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public SendPhoto getSendPhoto() {
        return sendPhoto;
    }

    public void setSendPhoto(SendPhoto sendPhoto) {
        this.sendPhoto = sendPhoto;
    }
}
