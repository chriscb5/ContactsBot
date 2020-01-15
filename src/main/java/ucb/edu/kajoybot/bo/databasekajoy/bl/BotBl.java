package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
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

@Service
public class BotBl {

    private static final Logger LOGGER= LoggerFactory.getLogger(BotBl.class);

    private KjUserRepository kjUserRepository;
    private ChatRepository chatRepository;
    public Boolean firstMessage = true;
    ContactsBL contactsBL;

    @Autowired
    public BotBl(KjUserRepository kjUserRepository, ChatRepository chatRepository, ContactsBL contactsBL) {
        this.chatRepository = chatRepository;
        this.contactsBL = contactsBL;
        this.kjUserRepository=kjUserRepository;
    }


    //intento Multi Usuario
    //    public List<String> processUpdate(Update update) {
    //        LOGGER.info("Recibiendo update {} ", update);
    //        List<String> chatResponse = new ArrayList<>();
    //        KjEstudianteUserEntity kjEstudianteUserEntity = initUser(update.getMessage().getFrom());
    //        continueChatWithUser(update,kjEstudianteUserEntity,chatResponse);
    //        return chatResponse;
    //    }


    public void processUpdateMesage(Update update, SendMessage message, SendPhoto photo){
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
        List<PhotoSize> photoReceived = update.getMessage().getPhoto();
        LOGGER.info("Ultimo mensaje "+update.getMessage().getText());
        String imageFile = null;
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        sendMessage.setChatId(chatId);
        sendPhoto.setChatId(chatId);
        if (update.hasMessage() && update.getMessage().hasText() && !update.getMessage().hasPhoto()){
            if(lastMenssage == null){
                sendMessage.setChatId(chatId)
                        .setText("DEFAULT por null");
            }else {

                if (messageInput.equals("/start") || firstMessage==false){
                    firstMessage = false;
                    setModulesMessages(update,sendMessage,sendPhoto,messageTextReceived,photoReceived);
                    try {
                        switch(messageInput) {
                            case "Teclado":
                                sendMessage.setChatId(chatId)
                                        .setText("Teclado removido");
                                sendMessage.setReplyMarkup(replyKeyboardRemove);
                                break;

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

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
                                mostrarMenu(sendMessage,keyboardMarkup,keyboard,chatId);
                                break;

                            case "Agregar Contactos":
                                contactsBL.setEntra_a_agregar_contactos(true);
                                sendMessage.setChatId(chatId)
                                        .setText("*Agregar Contactos*\nIngrese el primer nombre").setParseMode("Markdown");
                                sendMessage.setReplyMarkup(replyKeyboardRemove);

                                break;

                            case "Buscar Contactos":
                                contactsBL.setEntra_a_buscar_contactos(true);
                                sendMessage.setChatId(chatId)
                                        .setText("*Buscar Contactos*\nSeleccione una opción").setParseMode("Markdown");
                                KeyboardRow keyboardRow = new KeyboardRow();
                                KeyboardRow keyboardRow1 = new KeyboardRow();
                                keyboardRow.add("Buscar por Nombre o Apellido");
                                keyboardRow1.add("Buscar por Número de Teléfono");
                                keyboard.add(keyboardRow);
                                keyboard.add(keyboardRow1);
                                keyboardMarkup.setKeyboard(keyboard);
                                sendMessage.setReplyMarkup(keyboardMarkup);

                                break;

                            case "Kajoy":
                                imageFile = "https://image.shutterstock.com/z/stock-vector-bienvenido-welcome-spanish-text-lettering-vector-illustration-1050015260.jpg";
                                sendPhoto.setChatId(chatId)
                                        .setPhoto(imageFile);
                                sendMessage.setChatId(chatId)
                                        .setText("Seleccione una opción por favor\nInformacion");
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
                                        .setText("Somos una plataforma para crear test interactivos! \nLos docentes pueden crear test para enviarlos a sus alumnos y ver la puntuación de cada alumno\nPara volver al inicio, presione /start");
                                break;

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

                        }
                    } catch (NumberFormatException nfe){
                        sendMessage.setChatId(chatId)
                                .setText("DEFAULT");
                    }
                }else {
                    sendMessage.setChatId(chatId)
                            .setText("Hola, para empezar el bot por favor escribe /start");
                }

            }
            KjChatEntity kjChatEntity = new KjChatEntity();
//        kjChatEntity.setKjuserid(kjEstudianteUserEntity);
            kjChatEntity.setKjuserid(kjUserEntity);
            kjChatEntity.setInMessage(update.getMessage().getText());
            kjChatEntity.setOutMessage("texto");
            kjChatEntity.setMsgDate(new Date());
            kjChatEntity.setTxDate(new Date());
//        kjChatEntity.setTxUser(kjEstudianteUserEntity.getUserid().toString());
            kjChatEntity.setTxUser(kjUserEntity.getUserid().toString());
            kjChatEntity.setTxHost(update.getMessage().getChatId().toString());
            chatRepository.save(kjChatEntity);
        }else {
            if (update.hasMessage() && !update.getMessage().hasText() && update.getMessage().hasPhoto()){
                if (contactsBL.isEntra_a_agregar_contactos()==true || contactsBL.isEntra_a_buscar_contactos()==true){
                    LOGGER.info("Entra a agregar contactos and it's a photo");
                    contactsBL.receivePhotoContact(update,photoReceived,sendMessage,sendPhoto);
                }else {
                    LOGGER.info("No esta en preg 6 y no esta agregando contactos");
                    sendMessage.setText( "No puede subir imagenes en este momento");
                }
            }
        }

    }

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

    private void mostrarMenu(SendMessage sendMessage, ReplyKeyboardMarkup keyboardMarkup, List<KeyboardRow> keyboard, long chatId) {
        sendMessage.setChatId(chatId)
                .setText("*Seleccione una opción:*\nBuscar Contactos\nAgregar Contactos").setParseMode("Markdown");
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow.add("Buscar Contactos");
        keyboardRow2.add("Agregar Contactos");
        keyboard.add(keyboardRow);
        keyboard.add(keyboardRow2);
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);
    }


    private void setModulesMessages(Update update,SendMessage sendMessage,SendPhoto sendPhoto,String messageTextReceived, List<PhotoSize> photoReceived){

//--------------------------------------------------------------------------

        if(contactsBL.isEntra_a_agregar_contactos()){
            contactsBL.entraAgregarContactos(messageTextReceived,photoReceived,sendMessage,sendPhoto,update);
        }
        if(contactsBL.isEntra_a_agregar_phonenumbers()){
            contactsBL.entraAgregarPhoneNumbers(messageTextReceived,sendMessage,update);
        }
        if(contactsBL.isEntra_a_buscar_contactos()){
            contactsBL.entraBuscarContactos(messageTextReceived,photoReceived,sendMessage,sendPhoto,update);
        }


//--------------------------------------------------------------------------
    }

}