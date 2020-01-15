package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ucb.edu.kajoybot.bo.databasekajoy.dao.ChatRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.KjChatEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.KjUserEntity;

import static org.junit.jupiter.api.Assertions.*;

class BotBlTest {

    @Test
    void processUpdateMesage() {
        User user = Mockito.mock(User.class);
        Mockito.doReturn(2).when(user).getId();
        Mockito.doReturn("Ninotschka").when(user).getFirstName();
        Mockito.doReturn("Calderón de la Barca").when(user).getLastName();

        Long i = 1234567890L;
        Message message = Mockito.mock(Message.class);
        Mockito.doReturn(user).when(message).getFrom();
        Mockito.doReturn(i).when(message).getChatId();

        Update update = Mockito.mock(Update.class);
        Mockito.doReturn(message).when(update).getMessage();
    }

    @Test
    void continueChatWithUserMessage() {
        ChatRepository chatRepository = Mockito.mock(ChatRepository.class);
        User user = Mockito.mock(User.class);
        Mockito.doReturn(2).when(user).getId();
        Mockito.doReturn("Ninotschka").when(user).getFirstName();
        Mockito.doReturn("Calderón de la Barca").when(user).getLastName();

        Long i = 1234567890L;
        Message message = Mockito.mock(Message.class);
        Mockito.doReturn(user).when(message).getFrom();
        Mockito.doReturn(i).when(message).getChatId();

        Update update = Mockito.mock(Update.class);
        Mockito.doReturn(message).when(update).getMessage();

        KjUserEntity prueba = new KjUserEntity(2);
        KjChatEntity kjChatEntity = Mockito.mock(KjChatEntity.class);
        Mockito.doReturn(prueba).when(kjChatEntity).getKjuserid();
        Mockito.doReturn("Prueba").when(kjChatEntity).getInMessage();
        Mockito.doReturn(Mockito.doReturn("Prueba").when(kjChatEntity).getInMessage()).when(chatRepository).findLastChatByUserId(2);


    }
}