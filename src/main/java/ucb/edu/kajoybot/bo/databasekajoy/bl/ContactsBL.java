package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ucb.edu.kajoybot.bo.databasekajoy.dao.ChatRepository;
import ucb.edu.kajoybot.bo.databasekajoy.dao.ContactRepository;
import ucb.edu.kajoybot.bo.databasekajoy.dao.KjUserRepository;
import ucb.edu.kajoybot.bo.databasekajoy.dao.PhoneNumberRepository;

import java.util.ArrayList;
import java.util.List;

public class ContactsBL {

    private static final Logger LOGGER= LoggerFactory.getLogger(ContactsBL.class);
    private static int numero_de_pregunta=0;
    private static int numero_de_respuesta=0;
    private static int numero_de_pregunta_respondiendo=1;
    private static int nummero_de_respuesta_respondiendo=1;
    private static boolean registrosllenos=false;
    private static boolean entra_a_buscar_contactos=false;
    private static boolean entra_a_agregar_contactos=false;
    private static boolean entra_a_modificar_contactos=false;
    private static boolean entra_a_eliminar_contactos=false;
    private static List<String> registrollenadosList= new ArrayList<>();

    private KjUserRepository kjUserRepository;
    private ChatRepository chatRepository;
    private ContactRepository contactRepository;
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public ContactsBL(KjUserRepository kjUserRepository, ChatRepository chatRepository, ContactRepository contactRepository, PhoneNumberRepository phoneNumberRepository) {
        this.kjUserRepository = kjUserRepository;
        this.chatRepository = chatRepository;
        this.contactRepository = contactRepository;
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public static int getNumero_de_pregunta() {
        return numero_de_pregunta;
    }

    public static void setNumero_de_pregunta(int numero_de_pregunta) {
        ContactsBL.numero_de_pregunta = numero_de_pregunta;
    }

    public void entraAgregarContactos(String messageTextReceived, SendMessage sendMessage) {

    }


    public static boolean isEntra_a_agregar_contactos() {
        return entra_a_agregar_contactos;
    }
    public static void setEntra_a_agregar_contactos(boolean entra_a_agregar_contactos) {
        ContactsBL.entra_a_agregar_contactos = entra_a_agregar_contactos;
    }


}
