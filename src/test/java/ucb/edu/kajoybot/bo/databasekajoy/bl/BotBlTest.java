package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ucb.edu.kajoybot.bo.databasekajoy.dao.ChatRepository;
import ucb.edu.kajoybot.bo.databasekajoy.dao.KjUserRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.KjChatEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.KjUserEntity;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BotBlTest {

    @Test
    void processUpdateMesage() {

        ChatRepository chatRepository = Mockito.mock(ChatRepository.class);
        KjUserRepository kjUserRepository = Mockito.mock(KjUserRepository.class);
        ContactsBL contactsBL = Mockito.mock(ContactsBL.class);

        User user = Mockito.mock(User.class);
        Mockito.doReturn(2).when(user).getId();
        Mockito.doReturn("Ninotschka").when(user).getFirstName();
        Mockito.doReturn("Calderón de la Barca").when(user).getLastName();

        Long i = 1234567890L;
        Message message = Mockito.mock(Message.class);
        Mockito.doReturn(user).when(message).getFrom();
        Mockito.doReturn(i).when(message).getChatId();
        Mockito.doReturn("/start").when(message).getText();
        Mockito.doReturn(true).when(message).hasText();
        Mockito.doReturn(false).when(message).hasPhoto();

        Update update = Mockito.mock(Update.class);
        Mockito.doReturn(message).when(update).getMessage();

        KjUserEntity prueba = new KjUserEntity(2);
        KjChatEntity kjChatEntity = Mockito.mock(KjChatEntity.class);
        Mockito.doReturn(prueba).when(kjChatEntity).getKjuserid();
        Mockito.doReturn("Prueba").when(kjChatEntity).getInMessage();
        Mockito.doReturn(Mockito.doReturn("Prueba").when(kjChatEntity).getInMessage()).when(chatRepository).findLastChatByUserId(2);

//        Mockito.doReturn(true).when(update).hasMessage();
//        Mockito.doReturn(true).when(update.getMessage().hasText());
//        Mockito.doReturn(false).when(update.getMessage().hasPhoto());
//        Mockito.doReturn(2).when(chatRepository.findLastChatByUserId(user.getId()));

        SendMessage sendMessage = Mockito.mock(SendMessage.class);
        SendPhoto sendPhoto = Mockito.mock(SendPhoto.class);

//        sendMessage.setChatId(message.getChatId());

        BotBl botBl = new BotBl(kjUserRepository,chatRepository,contactsBL);
        botBl.continueChatWithUserMessage(update,prueba,sendMessage,sendPhoto);

    }

    @Test
    void continueChatWithUserMessage() {
        ChatRepository chatRepository = Mockito.mock(ChatRepository.class);
        KjUserRepository kjUserRepository = Mockito.mock(KjUserRepository.class);
        ContactsBL contactsBL = Mockito.mock(ContactsBL.class);
        User user = Mockito.mock(User.class);
        Mockito.doReturn(2).when(user).getId();
        Mockito.doReturn("Ninotschka").when(user).getFirstName();
        Mockito.doReturn("Calderón de la Barca").when(user).getLastName();

        Long i = 1234567890L;
        Message message = Mockito.mock(Message.class);
        Mockito.doReturn(user).when(message).getFrom();
        Mockito.doReturn(i).when(message).getChatId();
        Mockito.doReturn("/start").when(message).getText();

        Update update = Mockito.mock(Update.class);
        Mockito.doReturn(message).when(update).getMessage();

        KjUserEntity prueba = new KjUserEntity(2);
        KjChatEntity kjChatEntity = Mockito.mock(KjChatEntity.class);
        Mockito.doReturn(prueba).when(kjChatEntity).getKjuserid();
        Mockito.doReturn("Prueba").when(kjChatEntity).getInMessage();
        Mockito.doReturn(Mockito.doReturn("Prueba").when(kjChatEntity).getInMessage()).when(chatRepository).findLastChatByUserId(2);

    }
}