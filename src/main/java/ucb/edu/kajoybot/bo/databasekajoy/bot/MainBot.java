package ucb.edu.kajoybot.bo.databasekajoy.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ucb.edu.kajoybot.bo.databasekajoy.bl.BotBl;
import ucb.edu.kajoybot.bo.databasekajoy.bl.MensajesBL;
import ucb.edu.kajoybot.bo.databasekajoy.bl.PersonBL;

import java.util.ArrayList;
import java.util.List;

public class MainBot extends TelegramLongPollingBot {

    PersonBL personBL;
    BotBl botBl;
    MensajesBL mensajesBL;




    @Autowired
    public  MainBot(BotBl botBl,PersonBL personBL,MensajesBL mensajesBL){
        this.botBl=botBl;
        this.personBL=personBL;
        this.mensajesBL=mensajesBL;
    }


//    @Override
//    public void onUpdateReceived(Update update) {
//        System.out.println(update);
//        update.getMessage().getFrom().getId();
//        if (update.hasMessage() && update.getMessage().hasText()) {
//
//            //Anterior código mensajes
//
//            List<String> messages = botBl.processUpdate(update);
//            for(String messageText: messages) {
//                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                        .setChatId(update.getMessage().getChatId())
//                        .setText(messageText);
//                try {
//                    this.execute(message);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        update.getMessage().getFrom().getId();
        if (update.hasMessage() && update.getMessage().hasText()) {
              SendMessage message=new SendMessage();
              SendPhoto photo = new SendPhoto();
              /*message =*/ botBl.processUpdateMesage(update,message,photo);
    /*            SendMessage messageChat = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(update.getMessage().getChatId())
                        .setText(message.getText());
     */
            try {
                if(message == null){
                    this.execute(photo);
                }
                if(photo.getPhoto() == null){
                    this.execute(message);
                }
                if (message != null && photo.getPhoto() != null){
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

    //@Override
    //  public void onUpdateReceived(Update update) {
    //      final String messageTextReceived = update.getMessage().getText();
//        final long chatId = update.getMessage().getChatId();
//        botBl.processUpdate(update);
    ///Test conexión a base de Datos

//        /*if (messageTextReceived.equals("/testCurso")) {
//            //Message message3 = update.getMessage();
//            CursoEntity cursoEntity=cursoBL.findByIdCurso(1);
//            SendMessage message4=new SendMessage()
//                    .setChatId(update.getMessage().getChatId())
//                    .setText("Curso in BBDD: "+cursoEntity);
//            try {
//                this.execute(message4);*/
//        LOGGER.info("Entro a registro es "+entra_a_registro_estudiante);
//
//        update.getMessage().getFrom().getId();
//
//        LOGGER.info("Se recibio "+messageTextReceived);
//        if (update.hasMessage() && update.getMessage().hasText()) {
//
//            if(entra_a_iniciar_estudiante){
//                String response=personBL.existPasswordEstudianteByCurso("Lalo",messageTextReceived);
//                SendMessage message=new SendMessage().setChatId(chatId).setText(response);
//                try {
//                    execute(message); // Sending our message object to user
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//                entra_a_iniciar_estudiante=false;
//            }
//            if(entra_a_iniciar_docentenombre){
//                String response=personBL.ExistDocenteByNombre(messageTextReceived);
//                SendMessage message=new SendMessage().setChatId(chatId).setText(response);
//                try {
//                    execute(message); // Sending our message object to user
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//                entra_a_iniciar_docentenombre=false;
//            }
//            if(entra_a_iniciar_docente){
//                String response=personBL.ExistPasswordDocenteInCurse("Katia",messageTextReceived);
//                SendMessage message=new SendMessage().setChatId(chatId).setText(response);
//                try {
//                    execute(message); // Sending our message object to user
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//                entra_a_iniciar_docente=false;
//            }
//
//
//            if (entra_a_registro_estudiante) {
//                LOGGER.info("Entra a el registro estudiante oficial");
//                if(registrollenadosList.size()<5) {
//                    LOGGER.info("Entra al registros no llenos");
//                    if(mensajesBL.getNumero_de_pregunta()<4){
//                        String mensaje = /*botBl*/mensajesBL.mensajesRegistroEstudiante(update);
//                        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                                .setChatId(update.getMessage().getChatId())
//                                .setText(mensaje);
//                        try {
//                            this.execute(message);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    mensajesBL.setNumero_de_pregunta(mensajesBL.getNumero_de_pregunta()+1) ;//
//                    registrollenadosList.add(messageTextReceived);
//                    LOGGER.info("Tamaño de array "+registrollenadosList.size());
//                }
//                if (registrollenadosList.size()==5) {
//                    LOGGER.info("Ingresa a registros llenos");
//                    String mensajecomp = botBl.guardarListaRegistros(registrollenadosList);
//                    SendMessage message2 = new SendMessage()
//                        .setChatId(update.getMessage().getChatId())
//                        .setText(mensajecomp);
//                    try {
//                        this.execute(message2);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    registrosllenos = false;
//                    registrollenadosList.clear();
//                    entra_a_registro_estudiante = false;
//                }
//            }
//            if (entra_a_registro_docente) {
//                LOGGER.info("Entra a el registro estudiante oficial");
//                if(registrollenadosList.size()<4)
//                {
//                    LOGGER.info("Entra al registros no llenos");
//                    if(mensajesBL.getNumero_de_pregunta()<3){
//                        String mensaje = mensajesBL.mensajesRegistroDocente(update);
//                        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                                .setChatId(update.getMessage().getChatId())
//                                .setText(mensaje);
//                        try {
//                            this.execute(message);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    mensajesBL.setNumero_de_pregunta(mensajesBL.getNumero_de_pregunta()+1) ;//
//                    registrollenadosList.add(messageTextReceived);
//                    LOGGER.info("Tamaño de array "+registrollenadosList.size());
//                }
//                if (registrollenadosList.size()==4) {
//                    LOGGER.info("Ingresa a registros llenos");
//                    String mensajecomp = botBl.guardarListaRegistrosDocente(registrollenadosList);
//                    SendMessage message2 = new SendMessage()
//                            .setChatId(update.getMessage().getChatId())
//                            .setText(mensajecomp);
//                    try {
//                        this.execute(message2);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    registrosllenos = false;
//                    registrollenadosList.clear();
//                    entra_a_registro_docente = false;
//                }
//            }
//            if (entra_a_registro_curso) {
//                LOGGER.info("Entra a el registro curso oficial");
//                if(registrollenadosList.size()<3) {
//                    LOGGER.info("Entra al registros no llenos");
//                    if(mensajesBL.getNumero_de_pregunta()<2){
//                        String mensaje = mensajesBL.mensajesRegistroCurso(update);
//                        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                                .setChatId(update.getMessage().getChatId())
//                                .setText(mensaje);
//                        try {
//                            this.execute(message);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    mensajesBL.setNumero_de_pregunta(mensajesBL.getNumero_de_pregunta()+1) ;
//                    registrollenadosList.add(messageTextReceived);
//                    LOGGER.info("Tamaño de array "+registrollenadosList.size());
//                }
//                if (registrollenadosList.size()==3) {
//                    LOGGER.info("Ingresa a registros llenos");
//                    String mensajecomp = botBl.guardarListaRegistrosCurso(registrollenadosList);
//                    SendMessage message2 = new SendMessage()
//                            .setChatId(update.getMessage().getChatId())
//                            .setText(mensajecomp);
//                    try {
//                        this.execute(message2);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    registrosllenos = false;
//                    registrollenadosList.clear();
//                    entra_a_registro_curso = false;
//                }
//            }
//
//            if (entra_a_registro_estudiante_curso) {
//                LOGGER.info("Entra a el registro de estudiante en curso");
//                if(registrollenadosList.size()<2) {
//                    LOGGER.info("Entra a registros no llenos");
//                    if(mensajesBL.getNumero_de_pregunta()<1){
//                        String mensaje = mensajesBL.mensajesRegistroEstudianteCurso(update);
//                        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                                .setChatId(update.getMessage().getChatId())
//                                .setText(mensaje);
//                        try {
//                            this.execute(message);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    mensajesBL.setNumero_de_pregunta(mensajesBL.getNumero_de_pregunta()+1) ;//
//                    registrollenadosList.add(messageTextReceived);
//                    LOGGER.info("Tamaño de array "+registrollenadosList.size());
//                }
//                if (registrollenadosList.size()==3) {
//                    LOGGER.info("Ingresa a registros llenos");
//                    String mensajecomp = botBl.guardarListaRegistrosCurso(registrollenadosList);
//                    SendMessage message2 = new SendMessage()
//                            .setChatId(update.getMessage().getChatId())
//                            .setText(mensajecomp);
//                    try {
//                        this.execute(message2);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    registrosllenos = false;
//                    registrollenadosList.clear();
//                    entra_a_registro_curso = false;
//                }
//            }
//
//            if(messageTextReceived.equals("Si")){
//                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                        .setChatId(update.getMessage().getChatId())
//                        .setText("Añada nuevamente su pregunta");
//                try {
//                    this.execute(message);
//                }
//                catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//                confirmation=true;
//                aniade_pregunta_nueva=true;
//                entra_a_registro_test=true;
//                entra_a_registro_respuesta=false;
//            }
//
//
//            if(messageTextReceived.equals("No")){
//                String registrertest=  botBl.saveCompleteTest(registrollenadosList,registrorespuestalist);
//                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                        .setChatId(update.getMessage().getChatId())
//                        .setText(registrertest);
//                try {
//                    this.execute(message);
//                }
//                catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//                registrollenadosList.clear();
//                registrorespuestalist.clear();
//                entra_a_registro_test=false;
////                termina_test=true;
//            }
//
//            if (entra_a_registro_test){
//                if(entra_a_registro_respuesta){
//                    if(mensajesBL.getNumero_de_respuesta()==4) {
//                        registrorespuestalist.add(messageTextReceived);
//                        LOGGER.info("SIIIIIIII ENTRA A REGISTRO RESPUESTA TERMINADO");
//                        LOGGER.info("ENTRO AL SI CON ARRAY "+registrorespuestalist.size());
//                        int i=0;
//                        int contresppropregunta=4;
//                        String cade="CUESTIONARIO - TEST\n";
//                        for(String preg:registrollenadosList){
//                            cade+=preg+"\n";
//                            int numero=1;
//                            while(i<contresppropregunta){
//                                    cade+=numero+". "+registrorespuestalist.get(i)+"\n";
//                                    numero++;
//                                    i++;
//                            }
//                            contresppropregunta+=4;
//                        }
//                        mensajesBL.setNumero_de_respuesta(0);
//                        SendMessage message=new SendMessage()
//                                .setChatId(update.getMessage().getChatId())
//                                .setText(cade+"\nDesea añadir una nueva pregunta?");
//
//                        entra_a_registro_respuesta=false;
////                            entra_a_registro_test=false;
//                        aniade_respuesta_nueva=true;
//                        aniade_pregunta_nueva=false;
//
//                        try {
//                            this.execute(message);
//                        }
//                        catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                    }//Terminate NUMERO DE RESPUESTAS= 4
//
//                    if(mensajesBL.getNumero_de_respuesta()<4 && entra_a_registro_respuesta) {
//                        //INGRESANDO A REGISTROS NO COMPLETOS
//                        String mensaje=mensajesBL.mensajeRegistroRespuesta(update);
//                        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                                .setChatId(update.getMessage().getChatId())
//                                .setText(mensaje);
//                        try {
//                            this.execute(message);
//                        }
//                        catch (TelegramApiException e) {
//                            e.printStackTrace();
//                         }
//                        mensajesBL.setNumero_de_respuesta(mensajesBL.getNumero_de_respuesta()+1);
//                        if(aniade_pregunta_nueva==false){
//                            registrorespuestalist.add(messageTextReceived);
//                        }
//                        aniade_respuesta_nueva=false;
//                    }
//                }
//
//                if(entra_a_registro_respuesta==false && aniade_pregunta_nueva) {
//                    String mensaje=mensajesBL.mensajeRegistroTest(update);
//                    SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                            .setChatId(update.getMessage().getChatId())
//                            .setText(mensaje);
//                    try {
//                        this.execute(message);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    mensajesBL.setNumero_de_pregunta(/*mensajesBL.getNumero_de_pregunta()+1*/0);
//                    entra_a_registro_respuesta=true;
//                }
//                if(aniade_pregunta_nueva && confirmation==false){
//                    registrollenadosList.add(messageTextReceived);
//                    aniade_pregunta_nueva=false;
//                }
//                if(confirmation==true){
///*                    String mensaje=mensajesBL.mensajeRegistroTest(update);
//                    SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                            .setChatId(update.getMessage().getChatId())
//                            .setText(mensaje);
//                    try {
//                        this.execute(message);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    mensajesBL.setNumero_de_pregunta(/*mensajesBL.getNumero_de_pregunta()+10);*/
//                    entra_a_registro_respuesta=true;
//                    confirmation=false;
//                }
//
//            }// TERMINA REGISTRO TEST
//
//            //##############################################################
//            //#############################################################
//            //##############################################################
//        }
//    }

    @Override
    public String getBotUsername() {
//        return "Kajoybot";
        return "katariBot";
//        return "kajoy_bot";
//        return "devKajoy";
    }

    @Override
    public String getBotToken()
    {
//        return "883396045:AAFnccy-vbkbg7dxuqzs7XkvhjYbqw78n4o";
        return "953510535:AAGxU_5R9PdOQUmz6lRI-fWZsUzkYPfHCIA";
//        return "969248445:AAGzAETF0P9AXJk6W3EUDkGLWzJkrPgC_5A";
//        return "1062478290:AAG3C68x6eCwe0VSC2uyb4OR74_c15lWY4k";
    }



    @Override
    public void clearWebhook() throws TelegramApiRequestException {
        System.out.println("Se invoco clearWebhook");
    }

}