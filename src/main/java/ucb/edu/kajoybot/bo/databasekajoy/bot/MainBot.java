package ucb.edu.kajoybot.bo.databasekajoy.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ucb.edu.kajoybot.bo.databasekajoy.bl.BotBl;
import ucb.edu.kajoybot.bo.databasekajoy.bl.ContactsBL;

public class MainBot extends TelegramLongPollingBot {

    BotBl botBl;
    ContactsBL contactsBL;


    private static final Logger LOGGER= LoggerFactory.getLogger(MainBot.class);

    @Autowired
    public  MainBot(BotBl botBl, ContactsBL contactsBL){
        this.botBl=botBl;
        this.contactsBL = contactsBL;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        update.getMessage().getFrom().getId();
        if (update.hasMessage() && update.getMessage().hasText() || update.hasMessage() && update.getMessage().hasPhoto()) {
              SendMessage message=new SendMessage();
              SendPhoto photo = new SendPhoto();
              /*message =*/ botBl.processUpdateMesage(update,message,photo);
    /*            SendMessage messageChat = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(update.getMessage().getChatId())
                        .setText(message.getText());
     */
            try {
                if(message == null){
                    message.setText("No entiendo lo que me quieres decir");
                    this.execute(message);
                }else if(photo.getPhoto() == null && message != null){
                    LOGGER.info("SENDING MESSAGE");
                    this.execute(message);
                }else if (photo.getPhoto() != null && message == null){
                    LOGGER.info("SENDING PHOTO");
                    this.execute(photo);
                } else if (message != null && photo.getPhoto() != null){
                    LOGGER.info("SENDING MESSAGE AND PHOTO");
                    this.execute(message);
                    this.execute(photo);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            catch(NullPointerException e )
            {
                System.out.print("NullPointerException caught");
            }
        }
    }


    @Override
    public String getBotUsername() {
//        return "Kajoybot";
//        return "katariBot";
//        return "kajoy_bot";
        return "devKajoy";
    }

    @Override
    public String getBotToken()
    {
//        return "883396045:AAFnccy-vbkbg7dxuqzs7XkvhjYbqw78n4o";
//        return "953510535:AAGxU_5R9PdOQUmz6lRI-fWZsUzkYPfHCIA";
//        return "969248445:AAGzAETF0P9AXJk6W3EUDkGLWzJkrPgC_5A";
        return "1062478290:AAG3C68x6eCwe0VSC2uyb4OR74_c15lWY4k";
    }



    @Override
    public void clearWebhook() throws TelegramApiRequestException {
        System.out.println("Se invoco clearWebhook");
    }

}