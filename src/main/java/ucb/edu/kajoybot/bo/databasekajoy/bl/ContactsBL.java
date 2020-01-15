package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
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
import ucb.edu.kajoybot.bo.databasekajoy.domain.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContactsBL {


    private static final Logger LOGGER= LoggerFactory.getLogger(ContactsBL.class);
    private static int numero_de_pregunta=0;
    private static boolean registrosllenos=false;
    private static List<String> registrollenadosList= new ArrayList<>();



    private static final String dirName = "S:\\Photos";
    private String fileID = "";
    private String filePath = "";
    private String photoURL = "";

    private static boolean entra_a_agregar_contactos=false;
    private static boolean entra_a_agregar_phonenumbers=false;
    private static boolean entra_a_buscar_contactos=false;
    private static boolean entra_a_modificar_contactos=false;

    private int iNumbers=0;
    private int numNumbers=0;
    private boolean isOpeningContact = false;

    private boolean isSearchingByName = false;
    private boolean isSearchingByPhone = false;
    private boolean searchInputReceived = false;

    private boolean isChoosingField = true;
    private boolean isDeletingContact = false;
    private boolean isModifyingPhoneNumber = false;
    private boolean isAddingPhoneNumber = false;
    private boolean isCreatingNewPhoneNumber = false;
    private boolean isShowingContactAfterList = false;
    private boolean modFirstName = false, modSecondName = false, modFirstSurname = false, modSecondSurname = false, modEmail = false, modBirthDate = false, modImage = false, modPhoneNumbers = false;
    private static boolean registroContactoExitoso = false;
    private ContactEntity recentlyAddedContact = new ContactEntity();
    private List<ContactEntity> contactEntities = new ArrayList<>();
    private List<PhoneNumberEntity> phoneNumberEntities = new ArrayList<>();
    private List<PhoneNumberEntity> receivedPhoneNumbers = new ArrayList<>();
    private int id = 0;
    private int pId = 0;


    private ContactRepository contactRepository;
    private PhoneNumberRepository phoneNumberRepository;
    private KjUserRepository kjUserRepository;

    @Autowired
    public ContactsBL(ContactRepository contactRepository, PhoneNumberRepository phoneNumberRepository, KjUserRepository kjUserRepository) {
        this.contactRepository = contactRepository;
        this.phoneNumberRepository = phoneNumberRepository;
        this.kjUserRepository = kjUserRepository;
    }

//-----------------------------------------------------------------------------------------------------------
    private static String mensajeAgregarContactos(){
        String cadena=new String();
        switch (numero_de_pregunta)
        {
            case 0:
                cadena="Ingrese el segundo nombre";
                break;
            case 1:
                cadena="Ingrese el primer apellido";
                break;
            case 2:
                cadena="Ingrese la segundo apellido";
                break;
            case 3:
                cadena="Ingrese el email";
                break;
            case 4:
                cadena="Ingrese la fecha de nacimiento en el siguiente formato (YYYY/MM/DD)";
                break;
            case 5:
                cadena="Suba una imagen para el perfil del contacto";
                break;
        }
        return cadena;
    }
//--------------------------------------------------------------------------------------------------------------

    public static int getNumero_de_pregunta() {
        return numero_de_pregunta;
    }

    public static void setNumero_de_pregunta(int numero_de_pregunta) {
        ContactsBL.numero_de_pregunta = numero_de_pregunta;
    }

//------------------------------------------------------------------------------------------------------------------------------------------------------

    public void entraAgregarContactos(String messageTextReceived, List<PhotoSize> photoReceived, SendMessage sendMessage, SendPhoto sendPhoto, Update update){
        LOGGER.info("Entra a agregar contactos");
        String message = "";
        KeyboardRow row= new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        List<KeyboardRow> keyboard= new ArrayList<>();
        sendMessage.setReplyMarkup(replyKeyboardRemove);

        if(registrollenadosList.size()<7) {
            LOGGER.info("Entra a registros no llenos");
            if (getNumero_de_pregunta()==4){
                if (isValidEmail(messageTextReceived)){
                    LOGGER.info("Email válido");
                    message = mensajeAgregarContactos();
                    registrollenadosList.add(messageTextReceived);
                    setNumero_de_pregunta(getNumero_de_pregunta()+1);
                }else {
                    LOGGER.info("Email NO válido");
                    message = "\nEmail NO válido\nPor favor, intente nuevamente\n";
                    setNumero_de_pregunta(4);
                }
            }else{
                if (getNumero_de_pregunta()==5){
                    if (verifyDatebirth(messageTextReceived)==true){
                        LOGGER.info("Fecha válida");
                        message = mensajeAgregarContactos();
                        registrollenadosList.add(messageTextReceived);
                        setNumero_de_pregunta(getNumero_de_pregunta()+1);
                    }else {
                        LOGGER.info("Fecha NO válida");
                        message = "\nFecha NO válida\nPor favor, intente nuevamente\nFormato de la fecha (YYYY/MM/DD)\n";
                        setNumero_de_pregunta(5);
                    }
                }else {
                    if (getNumero_de_pregunta()==6){
                        LOGGER.info("Entra a registro de photo");
                        receivePhotoContact(update,photoReceived,sendMessage,sendPhoto);

                        if (update.getMessage().getText().equals("SI")){
                            LOGGER.info("SI, imagen elegida");
                            setNumero_de_pregunta(getNumero_de_pregunta()+1);
//                            registrollenadosList.add("photo");
                            String fileDir = "";
                            try {
                                String url = "https://api.telegram.org/bot1062478290:AAG3C68x6eCwe0VSC2uyb4OR74_c15lWY4k/getFile?file_id="+fileID;
                                URL obj = new URL(url);
                                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                                int responseCode = con.getResponseCode();
                                LOGGER.info("Sending GET request to URL: "+url);
                                LOGGER.info("Response Code: "+responseCode);
                                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                String inputLine;
                                StringBuffer response = new StringBuffer();
                                while ((inputLine = in.readLine()) != null) {
                                    response.append(inputLine);
                                }
                                in.close();

//                                LOGGER.info("Received code: "+response.toString());

                                JSONObject jsonObject = new JSONObject(response.toString());
                                LOGGER.info("Received JSON: "+jsonObject);
                                filePath = jsonObject.getJSONObject("result").getString("file_path");
                                photoURL = "https://api.telegram.org/file/bot1062478290:AAG3C68x6eCwe0VSC2uyb4OR74_c15lWY4k/"+filePath;
                                LOGGER.info("File Path: "+filePath);
                                LOGGER.info("Photo URL: "+photoURL);
                                fileDir = dirName+"\\"+fileID+".jpg";

                            }catch (Exception e){
                                LOGGER.info("Error al encontrar la direccion de la imagen");
                                e.printStackTrace();
                            }

                            try {
                                saveFileFromUrlWithCommonsIO(fileDir, photoURL);
                                registrollenadosList.add(fileDir);
                            }catch (MalformedURLException e){
                                LOGGER.info("Error al dirreccionar la URL");
                                e.printStackTrace();
                            }catch (IOException e){
                                LOGGER.info("Error al guardar la imagen");
                                e.printStackTrace();
                            }

                        }else {
                            if (update.getMessage().getText().equals("NO")){
                                LOGGER.info("NO, volver a pedir imagen");
                                message = "\nVuelva a subir una imagen\n";
                                setNumero_de_pregunta(6);
                            }
                        }
                        sendMessage.setReplyMarkup(replyKeyboardRemove);

                    }else {
                        message = mensajeAgregarContactos();
                        registrollenadosList.add(messageTextReceived);
                        setNumero_de_pregunta(getNumero_de_pregunta()+1);
                    }
                }
            }
            if (registroContactoExitoso==true){
                LOGGER.info("Entra a registro de contacto exitoso");

                if (messageTextReceived.length()<3 && validateNumber(messageTextReceived)){
                    LOGGER.info("Es numero");
                    numNumbers = Integer.parseInt(messageTextReceived);
                    entra_a_agregar_contactos = false;
                    entra_a_agregar_phonenumbers = true;
                    registrollenadosList.clear();
                    setNumero_de_pregunta(0);
                }else {
                    LOGGER.info("No es numero");
                    message = "Error!\nIngrese la cantidad de números a registrar (1-99)";
                }

            }
            LOGGER.info("Numero de pregunta="+numero_de_pregunta);
        }
        if (registrollenadosList.size()==7) {
            LOGGER.info("Ingresa a registros llenos");
            message = guardarListaAgregarContactos(registrollenadosList, update.getMessage().getFrom());
            registrosllenos = false;
            registrollenadosList.clear();
            message += "\nIngrese la cantidad de números telefónicos a añadir";
            registroContactoExitoso = true;
        }
        LOGGER.info("Tamaño de array "+registrollenadosList.size());
        sendMessage.setText(message);
    }

    public void entraAgregarPhoneNumbers(String messageTextReceived, SendMessage sendMessage, Update update){
        LOGGER.info("Entra a agregar phone numbers");
        String message = "";
        KeyboardRow row= new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        List<KeyboardRow> keyboard= new ArrayList<>();
        LOGGER.info("INumbers>>"+iNumbers);
        if (iNumbers<=numNumbers){
            LOGGER.info("Entra if phone");
            if (validatePhoneNumber(messageTextReceived)){
                LOGGER.info("Entra add num");
                registrollenadosList.add(messageTextReceived);
            }else {
                if (!validateNumber(messageTextReceived)){
                    message = "Número de teléfono no válido.\nIntente nuevamente\n";
                    iNumbers--;
                }
            }
            LOGGER.info("i="+iNumbers+"; num="+numNumbers);
            message += "Ingresar el número de teléfono N°"+(iNumbers+1)+" con el formato (XXXXXXXX)";
        }
        if (registrollenadosList.size()==numNumbers){
            LOGGER.info("Ingresa a guardado numeros");
            message = guardarListaAgregarPhoneNumbers(registrollenadosList);
            registrosllenos = false;
            registrollenadosList.clear();
            entra_a_agregar_phonenumbers = false;
            entra_a_agregar_contactos = false;
            registroContactoExitoso = false;
            iNumbers = 0;
            numNumbers = 0;
            mostrarMenu(sendMessage,update.getMessage().getChatId());
        }else {
            iNumbers++;
        }
        LOGGER.info("Tamaño de array "+registrollenadosList.size());
        sendMessage.setText(message);

    }

    public void entraBuscarContactos(String messageTextReceived,List<PhotoSize> photoReceived, SendMessage sendMessage, SendPhoto sendPhoto, Update update){
        LOGGER.info("Entra a buscar contactos");
        String message = "";
        KeyboardRow row= new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        List<KeyboardRow> keyboard= new ArrayList<>();
        sendMessage.setReplyMarkup(replyKeyboardRemove);
        if (!isOpeningContact) {
            LOGGER.info("Entra a isOpneningContact = false");
            if (messageTextReceived.equals("Buscar por Nombre o Apellido") || isSearchingByName){
                isSearchingByName = true;
                if (searchInputReceived){
                    contactEntities = getContactsThatInclude(messageTextReceived,update.getMessage().getFrom());
                    LOGGER.info("Contacts Found >> "+contactEntities.toString());
                    if (contactEntities.isEmpty()){
                        //FIXME Arreglar esta seccion
                        message = "No se encontraron contactos con esa descripción\nIntente nuevamente";
                        isOpeningContact = false;
                    }else {
                        message = "*Contactos Encontrados*\n\n";
                        for (int i=0;i<contactEntities.size();i++){
                            //FIXME Agregar condicionales para status y userId
                            LOGGER.info("ID Contacto "+(i+1)+": "+contactEntities.get(i).getContactId());
                            message += "\n*Contacto "+(i+1)+"-.*\n*Nombres:* "+contactEntities.get(i).getFirstName()+" "+contactEntities.get(i).getSecondName()+"\n*Apellidos:* "+contactEntities.get(i).getFirstSurname()+" "+contactEntities.get(i).getSecondSurname()+"\n\n";
                            KeyboardRow keyboardRow = new KeyboardRow();
                            keyboardRow.add("Contacto "+(i+1));
                            keyboard.add(keyboardRow);
                        }
                        message += "\n\nSeleccione un contacto";
                        keyboardMarkup.setKeyboard(keyboard);
                        isOpeningContact = true;
                        sendMessage.setReplyMarkup(keyboardMarkup);
                    }
                }else {
                    message = "Ingrese el *nombre* o *apellido* del contacto que desea *buscar*";
                    searchInputReceived = true;
                }
            }else {
                if (messageTextReceived.equals("Buscar por Número de Teléfono") || isSearchingByPhone){
                    isSearchingByPhone = true;
                    if (searchInputReceived){
                        contactEntities = getContactsByPhoneNumber(messageTextReceived,update.getMessage().getFrom());
                        LOGGER.info("Contacts Found >> "+contactEntities.toString());
                        if (contactEntities.isEmpty()){
                            //FIXME Arreglar esta seccion
                            message = "No se encontraron contactos con esa descripción\nIntente nuevamente";
                            isOpeningContact = false;
                        }else {
//                            phoneNumberEntities = phoneNumberRepository.findByContactId(contactEntities.get(id));
//                            receivedPhoneNumbers = phoneNumberRepository.findByContactId()
                            message = "*Contactos Encontrados*\n\n";
                            for (int i=0;i<contactEntities.size();i++){
                                //FIXME Agregar condicionales para status y userId
                                LOGGER.info("ID Contacto "+(i+1)+": "+contactEntities.get(i).getContactId());
                                message += "\n*Contacto "+(i+1)+"-.*\n*Nombre Completo:* "+contactEntities.get(i).getFirstName()+" "+contactEntities.get(i).getSecondName()+" "+contactEntities.get(i).getFirstSurname()+" "+contactEntities.get(i).getSecondSurname()+"\n";
                                message += "*Números de teléfono coincidentes:*\n";
                                int cont = 1;
                                for (int j=0;j<receivedPhoneNumbers.size();j++){
                                    if (contactEntities.get(i).getContactId() == receivedPhoneNumbers.get(j).getContactId().getContactId()){
                                        message += "*Número "+cont+":* "+receivedPhoneNumbers.get(j).getNumber()+"\n";
                                        cont++;
                                    }
                                }
                                KeyboardRow keyboardRow = new KeyboardRow();
                                keyboardRow.add("Contacto "+(i+1));
                                keyboard.add(keyboardRow);
                            }
                            message += "\n\nSeleccione un contacto";
                            keyboardMarkup.setKeyboard(keyboard);
                            isOpeningContact = true;
                            sendMessage.setReplyMarkup(keyboardMarkup);
                        }
                    }else {
                        message = "Ingrese el *número de teléfono* del contacto que desea *buscar*";
                        searchInputReceived = true;
                    }
                }
            }

            sendMessage.setText(message).setParseMode("Markdown");
        }else {
            if (messageTextReceived.equals("Salir al Menú Principal")){
                LOGGER.info("Entra a salir al menu principal");
                isOpeningContact = false;
                isChoosingField = true;
                entra_a_modificar_contactos = false;
                isSearchingByName = false;
                isSearchingByPhone = false;
                searchInputReceived = false;
                setEntra_a_buscar_contactos(false);
                contactEntities.clear();
                phoneNumberEntities.clear();
                receivedPhoneNumbers.clear();
                mostrarMenu(sendMessage,update.getMessage().getChatId());
            }else {
                if (messageTextReceived.equals("Mostrar el Contacto Actualizado")){
                    LOGGER.info("Entra a mostrar el contacto actualizado");
                    isShowingContactAfterList = false;
                    isChoosingField = true;
                    entra_a_modificar_contactos = false;
                    mostrarContacto(messageTextReceived,message,sendMessage,sendPhoto,keyboard,keyboardMarkup);
                }else {
                    if (messageTextReceived.equals("Modificar Contacto") || entra_a_modificar_contactos){
                        LOGGER.info("Entra a modificar contactos");
                        entra_a_modificar_contactos = true;
                        if (isChoosingField){
                            switch (messageTextReceived){
                                case "Primer Nombre":
                                    message = "Ingrese el primer nombre";
                                    sendMessage.setText(message).setParseMode("Markdown");
                                    modFirstName = true;
                                    isChoosingField = false;
                                    break;
                                case "Segundo Nombre":
                                    message = "Ingrese el segundo nombre";
                                    sendMessage.setText(message).setParseMode("Markdown");
                                    modSecondName = true;
                                    isChoosingField = false;
                                    break;
                                case "Primer Apellido":
                                    message = "Ingrese el primer apellido";
                                    sendMessage.setText(message).setParseMode("Markdown");
                                    modFirstSurname = true;
                                    isChoosingField = false;
                                    break;
                                case "Segundo Apellido":
                                    message = "Ingrese el segundo apellido";
                                    sendMessage.setText(message).setParseMode("Markdown");
                                    modSecondSurname = true;
                                    isChoosingField = false;
                                    break;
                                case "Email":
                                    message = "Ingrese el email";
                                    sendMessage.setText(message).setParseMode("Markdown");
                                    modEmail = true;
                                    isChoosingField = false;
                                    break;
                                case "Fecha de Nacimiento":
                                    message = "Ingrese la fecha de nacimiento en el siguiente formato (YYYY/MM/DD)";
                                    sendMessage.setText(message).setParseMode("Markdown");
                                    modBirthDate = true;
                                    isChoosingField = false;
                                    break;
                                case "Imagen":
                                    message = "Suba una imagen para el perfil del contacto";
                                    sendMessage.setText(message).setParseMode("Markdown");
                                    modImage = true;
                                    isChoosingField = false;
                                    break;
                                case "Números de Teléfono":
                                    message = "Seleccione un número de teléfono a modificar";
                                    for (int k=0;k<phoneNumberEntities.size();k++){
                                        KeyboardRow keyboardRow = new KeyboardRow();
                                        keyboardRow.add("Número "+(k+1));
                                        keyboard.add(keyboardRow);
                                    }
                                    KeyboardRow agregar = new KeyboardRow();
                                    agregar.add("Agregar Nuevo Número de Teléfono");
                                    keyboard.add(agregar);
                                    keyboardMarkup.setKeyboard(keyboard);
                                    sendMessage.setText(message).setParseMode("Markdown").setReplyMarkup(keyboardMarkup);
                                    modPhoneNumbers = true;
                                    isChoosingField = false;
                                    break;
                                default:
                                    message = "Seleccione un campo a modificar";
                                    KeyboardRow first_name = new KeyboardRow();
                                    KeyboardRow second_name = new KeyboardRow();
                                    KeyboardRow first_surname = new KeyboardRow();
                                    KeyboardRow second_surname = new KeyboardRow();
                                    KeyboardRow email = new KeyboardRow();
                                    KeyboardRow birth_date = new KeyboardRow();
                                    KeyboardRow image = new KeyboardRow();
                                    KeyboardRow phone_numbers = new KeyboardRow();
                                    first_name.add("Primer Nombre");
                                    second_name.add("Segundo Nombre");
                                    first_surname.add("Primer Apellido");
                                    second_surname.add("Segundo Apellido");
                                    email.add("Email");
                                    birth_date.add("Fecha de Nacimiento");
                                    image.add("Imagen");
                                    phone_numbers.add("Números de Teléfono");
                                    keyboard.add(first_name);
                                    keyboard.add(second_name);
                                    keyboard.add(first_surname);
                                    keyboard.add(second_surname);
                                    keyboard.add(email);
                                    keyboard.add(birth_date);
                                    keyboard.add(image);
                                    keyboard.add(phone_numbers);
                                    keyboardMarkup.setKeyboard(keyboard);
                                    sendMessage.setText(message).setParseMode("Markdown").setReplyMarkup(keyboardMarkup);
                                    break;
                            }
                        }else {
                            if (modFirstName){
                                saveFirstName(contactEntities.get(id).getContactId(),messageTextReceived);
                                message = "Primer nombre actualizado\nSeleccione una opción:";
                                mostrarOpcionesDespuesModificar(sendMessage);
                                modFirstName = false;
                            }
                            if (modSecondName){
                                saveSecondName(contactEntities.get(id).getContactId(),messageTextReceived);
                                message = "Segundo nombre actualizado\nSeleccione una opción:";
                                mostrarOpcionesDespuesModificar(sendMessage);
                                modSecondName = false;
                            }
                            if (modFirstSurname){
                                saveFirstSurname(contactEntities.get(id).getContactId(),messageTextReceived);
                                message = "Primer apellido actualizado\nSeleccione una opción:";
                                mostrarOpcionesDespuesModificar(sendMessage);
                                modFirstSurname = false;
                            }
                            if (modSecondSurname){
                                saveSecondSurname(contactEntities.get(id).getContactId(),messageTextReceived);
                                message = "Segundo apellido actualizado\nSeleccione una opción:";
                                mostrarOpcionesDespuesModificar(sendMessage);
                                modSecondSurname = false;
                            }
                            if (modEmail){
                                if (isValidEmail(messageTextReceived)){
                                    LOGGER.info("Email válido");
                                    message = saveEmail(contactEntities.get(id).getContactId(),messageTextReceived);
                                    mostrarOpcionesDespuesModificar(sendMessage);
                                    modEmail = false;
                                }else {
                                    LOGGER.info("Email NO válido");
                                    message = "\nEmail NO válido\nPor favor, intente nuevamente\n";
                                }
                            }
                            if (modBirthDate){
                                if (verifyDatebirth(messageTextReceived)){
                                    LOGGER.info("Fecha válida");
                                    saveBirthDate(contactEntities.get(id).getContactId(),messageTextReceived);
                                    message = "Fecha de Nacimiento actualizada\nSeleccione una opción:";
                                    mostrarOpcionesDespuesModificar(sendMessage);
                                    modBirthDate = false;
                                }else {
                                    LOGGER.info("Fecha NO válida");
                                    message = "\nFecha NO válida\nPor favor, intente nuevamente\nFormato de la fecha (YYYY/MM/DD)\n";
                                }
                            }
                            if (modImage){
                                LOGGER.info("Entra a registro de photo");
                                receivePhotoContact(update,photoReceived,sendMessage,sendPhoto);

                                if (update.getMessage().getText().equals("SI")){
                                    LOGGER.info("SI, imagen elegida");
                                    String fileDir = "";
                                    try {
                                        String url = "https://api.telegram.org/bot1062478290:AAG3C68x6eCwe0VSC2uyb4OR74_c15lWY4k/getFile?file_id="+fileID;
                                        URL obj = new URL(url);
                                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                                        int responseCode = con.getResponseCode();
                                        LOGGER.info("Sending GET request to URL: "+url);
                                        LOGGER.info("Response Code: "+responseCode);
                                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                        String inputLine;
                                        StringBuffer response = new StringBuffer();
                                        while ((inputLine = in.readLine()) != null) {
                                            response.append(inputLine);
                                        }
                                        in.close();

//                                LOGGER.info("Received code: "+response.toString());

                                        JSONObject jsonObject = new JSONObject(response.toString());
                                        LOGGER.info("Received JSON: "+jsonObject);
                                        filePath = jsonObject.getJSONObject("result").getString("file_path");
                                        photoURL = "https://api.telegram.org/file/bot1062478290:AAG3C68x6eCwe0VSC2uyb4OR74_c15lWY4k/"+filePath;
                                        LOGGER.info("File Path: "+filePath);
                                        LOGGER.info("Photo URL: "+photoURL);
                                        fileDir = dirName+"\\"+fileID+".jpg";

                                    }catch (Exception e){
                                        LOGGER.info("Error al encontrar la direccion de la imagen");
                                        e.printStackTrace();
                                    }

                                    try {
                                        saveFileFromUrlWithCommonsIO(fileDir, photoURL);
                                        saveImage(contactEntities.get(id).getContactId(),fileDir);
                                        message = "Imagen actualizada\nSeleccione una opción:";
                                        mostrarOpcionesDespuesModificar(sendMessage);
                                        modImage = false;
                                    }catch (MalformedURLException e){
                                        LOGGER.info("Error al dirreccionar la URL");
                                        e.printStackTrace();
                                    }catch (IOException e){
                                        LOGGER.info("Error al guardar la imagen");
                                        e.printStackTrace();
                                    }

                                }else {
                                    if (update.getMessage().getText().equals("NO")){
                                        LOGGER.info("NO, volver a pedir imagen");
                                        message = "\nVuelva a subir una imagen\n";
                                    }
                                }
//                            sendMessage.setReplyMarkup(replyKeyboardRemove);
                            }
                            if (modPhoneNumbers){
                                LOGGER.info("Entra modPhoneNumbers");
                                if (messageTextReceived.equals("Agregar Nuevo Número de Teléfono") || isAddingPhoneNumber){
                                    LOGGER.info("Entra agregar nuevo num telefono");
                                    isAddingPhoneNumber = true;
                                    if (!isCreatingNewPhoneNumber){
                                        LOGGER.info("Entra isCreatingPhoneNumber");
                                        message = "Ingresar el nuevo número de teléfono con el formato (XXXXXXXX)";
                                        isCreatingNewPhoneNumber = true;
                                    }else {
                                        if (validatePhoneNumber(messageTextReceived)){
                                            guardarAgregarPhoneNumber(messageTextReceived,contactEntities.get(id));
                                            message = "Número de teléfono registrado\nSeleccione una opción:";
                                            mostrarOpcionesDespuesModificar(sendMessage);
                                            isAddingPhoneNumber = false;
                                            isCreatingNewPhoneNumber = false;
                                            modPhoneNumbers = false;
                                        }else {
                                            if (!validatePhoneNumber(messageTextReceived)){
                                                message = "Número de teléfono no válido.\nIntente nuevamente\n";;
                                            }
                                        }
                                    }
                                }else {
                                    if (!isModifyingPhoneNumber){
                                        int index = messageTextReceived.indexOf(" ");
                                        pId = Integer.parseInt(messageTextReceived.substring(index+1))-1;
                                        LOGGER.info("Phone Number ID>>>>"+pId+" ; PhoneNumberEntity>>>>"+phoneNumberEntities.get(pId).getPhoneId());
                                        message = "Ingresar el número de teléfono con el formato (XXXXXXXX)";
                                        isModifyingPhoneNumber = true;
                                    }else {
                                        if (validatePhoneNumber(messageTextReceived)){
                                            savePhoneNumber(phoneNumberEntities.get(pId).getPhoneId(),messageTextReceived);
                                            message = "Número de teléfono actualizado\nSeleccione una opción:";
                                            mostrarOpcionesDespuesModificar(sendMessage);
                                            isModifyingPhoneNumber = false;
                                            modPhoneNumbers = false;
                                        }else {
                                            if (!validatePhoneNumber(messageTextReceived)){
                                                message = "Número de teléfono no válido.\nIntente nuevamente\n";;
                                            }
                                        }
                                    }
                                }

                            }
                            sendMessage.setText(message).setParseMode("Markdown");
                        }


                    }else {
                        if (messageTextReceived.equals("Eliminar Contacto") || isDeletingContact){
                            isDeletingContact = true;
                            if (messageTextReceived.equals("SI")){
                                deleteContact(contactEntities.get(id).getContactId());
                                message = "¡Contacto Eliminado Exitosamente!\n\n*Seleccione una opción:*\nBuscar Contactos\nAgregar Contactos";
                                isDeletingContact = false;
                                isOpeningContact = false;
                                isChoosingField = true;
                                entra_a_modificar_contactos = false;
                                isSearchingByName = false;
                                isSearchingByPhone = false;
                                searchInputReceived = false;
                                setEntra_a_buscar_contactos(false);
                                contactEntities.clear();
                                phoneNumberEntities.clear();
                                receivedPhoneNumbers.clear();
                                mostrarMenu(sendMessage,update.getMessage().getChatId());
                            }else {
                                if (messageTextReceived.equals("NO")){
                                    message = "Contacto *NO* eliminado\nSeleccione una opción";
                                    isDeletingContact = false;
                                    mostrarOpcionesDespuesModificar(sendMessage);
                                }else {
                                    message = "¿Está seguro que quiere eliminar este contacto?";
                                    KeyboardRow keyboardRow = new KeyboardRow();
                                    keyboardRow.add("SI");
                                    keyboardRow.add("NO");
                                    keyboard.add(keyboardRow);
                                    keyboardMarkup.setKeyboard(keyboard);
                                    sendMessage.setReplyMarkup(keyboardMarkup);
                                }
                            }
                            sendMessage.setText(message).setParseMode("Markdown");
                        }else {
                            LOGGER.info("Entra a mostrar contacto");
                            isShowingContactAfterList = true;
                            mostrarContacto(messageTextReceived,message,sendMessage,sendPhoto,keyboard,keyboardMarkup);
                        }
                    }
                }

            }
//                    isOpeningContact = false;
//                    setEntra_a_buscar_contactos(false);
        }
//        sendMessage.setText(message).setParseMode("Markdown");
//        sendMessage.setReplyMarkup(keyboardMarkup);
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------
    public static boolean isRegistrosllenos() {
        return registrosllenos;
    }

    public static void setRegistrosllenos(boolean registrosllenos) {
        ContactsBL.registrosllenos = registrosllenos;
    }

//----------------------------------------------------------------------------------------------------------------

    public static boolean isEntra_a_agregar_contactos() {
        return entra_a_agregar_contactos;
    }

    public static void setEntra_a_agregar_contactos(boolean entra_a_agregar_contactos) {
        LOGGER.info("Entra a Paso 2 "+ entra_a_agregar_contactos+" "+ ContactsBL.entra_a_agregar_contactos);
        ContactsBL.entra_a_agregar_contactos = entra_a_agregar_contactos;
    }

    public static boolean isEntra_a_agregar_phonenumbers() {
        return  entra_a_agregar_phonenumbers;
    }

    public static void setEntra_a_agregar_phonenumbers(boolean entra_a_agregar_phonenumbers) {
        ContactsBL.entra_a_agregar_phonenumbers = entra_a_agregar_phonenumbers;
    }

    public static boolean isEntra_a_buscar_contactos() {
        return entra_a_buscar_contactos;
    }

    public static void setEntra_a_buscar_contactos(boolean entra_a_buscar_contactos) {
        ContactsBL.entra_a_buscar_contactos = entra_a_buscar_contactos;
    }


//-----------------------------------------------------------------------------------------------------------------------

    private String guardarListaAgregarContactos(List<String> listaderegistros, User user) {
        LOGGER.info("Llega al metodo con : ");

        for (String lag : listaderegistros) {
            LOGGER.info("Elemento : " + lag);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        try {
            java.util.Date utilDate = format.parse(listaderegistros.get(5));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            LOGGER.info("Fecha=" + sqlDate);
            LOGGER.info("BotUserId=" + user.getId());
            KjUserEntity kjUserEntity = kjUserRepository.findByBotUserId(Integer.toString(user.getId()));
            LOGGER.info("UserId="+kjUserEntity.getUserid());

            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setUserId(kjUserEntity);
            contactEntity.setFirstName(listaderegistros.get(0));
            contactEntity.setSecondName(listaderegistros.get(1));
            contactEntity.setFirstSurname(listaderegistros.get(2));
            contactEntity.setSecondSurname(listaderegistros.get(3));
            contactEntity.setEmail(listaderegistros.get(4));
            contactEntity.setBirthdate(sqlDate);
            contactEntity.setImage(listaderegistros.get(6));
            contactEntity.setStatus(1);
            LOGGER.info("Contact Entity: "+contactEntity.toString());
            contactRepository.save(contactEntity);
            recentlyAddedContact = contactEntity;
            return "¡Registro completado exitosamente!";
        } catch (ParseException e) {
            LOGGER.info("Conversion de fecha failed");
            e.printStackTrace();
            return "Registro fallido. Por favor, intente nuevamente";
        }
    }

    private String guardarListaAgregarPhoneNumbers(List<String> listaderegistros) {
        LOGGER.info("Llega al metodo con : ");

//        LOGGER.info("Tamanio = "+Integer.toString(listaderegistros.size()));
        for (String lag : listaderegistros) {
            LOGGER.info("Elemento : " + lag);
        }
        ContactEntity contactEntity = contactRepository.findByUserIdAndFirstNameAndSecondNameAndFirstSurnameAndSecondSurnameAndEmailAndBirthdate(recentlyAddedContact.getUserId(),recentlyAddedContact.getFirstName(),recentlyAddedContact.getSecondName(),recentlyAddedContact.getFirstSurname(),recentlyAddedContact.getSecondSurname(),recentlyAddedContact.getEmail(),recentlyAddedContact.getBirthdate());
        LOGGER.info("Found Contact Entity: "+contactEntity.toString());
//        ContactEntity contactEntity = contactRepository.findByContactId(2);
        for (int i=0;i<listaderegistros.size();i++){
            PhoneNumberEntity phoneNumberEntity = new PhoneNumberEntity();
            phoneNumberEntity.setNumber(listaderegistros.get(i));
            phoneNumberEntity.setContactId(contactEntity);
            phoneNumberEntity.setStatus(1);
            LOGGER.info("Phone Number Entity: "+phoneNumberEntity.toString());
            phoneNumberRepository.save(phoneNumberEntity);
        }
        return "¡Registro de números telefónicos completado exitosamente!";
    }

    private String guardarAgregarPhoneNumber(String number, ContactEntity contactEntity) {
        PhoneNumberEntity phoneNumberEntity = new PhoneNumberEntity();
        phoneNumberEntity.setNumber(number);
        phoneNumberEntity.setContactId(contactEntity);
        phoneNumberEntity.setStatus(1);
        LOGGER.info("Phone Number Entity: "+phoneNumberEntity.toString());
        phoneNumberRepository.save(phoneNumberEntity);
        return "¡Número de teléfono registrado exitosamente!";
    }

    private void deleteContact(int contactId){
        ContactEntity contactEntity = contactRepository.findByContactId(contactId);
        contactEntity.setStatus(0);
        contactRepository.save(contactEntity);
    }

    private void saveFirstName(int contactId, String message){
        ContactEntity contactEntity = contactRepository.findByContactId(contactId);
        contactEntity.setFirstName(message);
        contactRepository.save(contactEntity);
    }

    private void saveSecondName(int contactId, String message){
        ContactEntity contactEntity = contactRepository.findByContactId(contactId);
        contactEntity.setSecondName(message);
        contactRepository.save(contactEntity);
    }

    private void saveFirstSurname(int contactId, String message){
        ContactEntity contactEntity = contactRepository.findByContactId(contactId);
        contactEntity.setFirstSurname(message);
        contactRepository.save(contactEntity);
    }

    private void saveSecondSurname(int contactId, String message){
        ContactEntity contactEntity = contactRepository.findByContactId(contactId);
        contactEntity.setSecondSurname(message);
        contactRepository.save(contactEntity);
    }

    private String saveEmail(int contactId, String message){
        if (isValidEmail(message)){
            LOGGER.info("Email válido");
            ContactEntity contactEntity = contactRepository.findByContactId(contactId);
            contactEntity.setEmail(message);
            contactRepository.save(contactEntity);
            return "Email actualizado\nSeleccione una opción:";
        }else {
            LOGGER.info("Email NO válido");
            message = "\nEmail NO válido\nPor favor, intente nuevamente\n";
            return message;
        }
    }

    private void saveBirthDate(int contactId, String message){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date utilDate = format.parse(message);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ContactEntity contactEntity = contactRepository.findByContactId(contactId);
            contactEntity.setBirthdate(sqlDate);
            contactRepository.save(contactEntity);
        }catch (ParseException e){
            LOGGER.info("Error, formato de fecha no valida");
            e.printStackTrace();
        }
    }

    private void saveImage(int contactId, String message){
        ContactEntity contactEntity = contactRepository.findByContactId(contactId);
        contactEntity.setImage(message);
        contactRepository.save(contactEntity);
    }

    private void savePhoneNumber(int phoneNumberId, String message){
        PhoneNumberEntity phoneNumberEntity = phoneNumberRepository.findByPhoneId(phoneNumberId);
        phoneNumberEntity.setNumber(message);
        phoneNumberRepository.save(phoneNumberEntity);
    }

//-------------------------------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------------------------------

    private static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private static boolean verifyDatebirth(String input) {
        LOGGER.info("Longitud de la fecha >> "+input.length());
        if (input.length() == 10){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            if (input != null) {
                try {
                    Date ret = sdf.parse(input.trim());
                    if (sdf.format(ret).equals(input.trim())) {
                        return true;
                    }
                } catch (ParseException e) {
                    LOGGER.info("Fecha inválida");
                }
            }
        }
        return false;
    }

    private static boolean validatePhoneNumber(String phone) {
        Pattern pattern = Pattern.compile("\\d{8}");
        Matcher matcher = pattern.matcher(phone);

        if (phone.length() == 8){
            if (matcher.matches()) {
                return true;
            }
        }
        return false;

    }

    private static boolean validateNumber(String num) {
        Pattern pattern = Pattern.compile("\\d{1}");
        Pattern pattern2 = Pattern.compile("\\d{2}");
        Matcher matcher = pattern.matcher(num);
        Matcher matcher2 = pattern2.matcher(num);

        if (matcher.matches() || matcher2.matches()) {
            if (Integer.parseInt(num) != 0){
                return true;
            }else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void receivePhotoContact(Update update, List<PhotoSize> photoReceived, SendMessage sendMessage, SendPhoto sendPhoto){
        String message = "";
        KeyboardRow row = new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        LOGGER.info("Tamaño del array antes: "+registrollenadosList.size());
        if (isEntra_a_agregar_contactos() == true && numero_de_pregunta == 6 || isEntra_a_buscar_contactos() && modImage == true){
            LOGGER.info("Num preg 6 y esta agregando contactos");
            if (update.getMessage().hasPhoto()) {
                LOGGER.info("Imagen recibida");
                // Message contains photo
                // Set variables
//                            long chat_id = update.getMessage().getChatId();

                // Array with photo objects with different sizes
                // We will get the biggest photo from that array
//                            List<PhotoSize> photos = update.getMessage().getPhoto();
                // Know file_id
                String f_id = photoReceived.stream()
                        .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                        .findFirst()
                        .orElse(null).getFileId();
                // Know photo width
                int f_width = photoReceived.stream()
                        .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                        .findFirst()
                        .orElse(null).getWidth();
                // Know photo height
                int f_height = photoReceived.stream()
                        .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                        .findFirst()
                        .orElse(null).getHeight();
                // Set photo caption
                String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
                message = "\n*_Imagen recibida_*\n";

//                File file = (File) photoReceived;

//                String uploadedFileId = f_id;
//                GetFile uploadedFile = new GetFile();
//                uploadedFile.setFileId(uploadedFileId);



                fileID = f_id;


                message += "*Confimación*\nQuiere utilizar esta imagen para el contacto?";
                row.add("SI");
                row.add("NO");
                keyboard.add(row);
                keyboardMarkup.setKeyboard(keyboard);
                sendPhoto.setPhoto(f_id)
                        .setCaption(caption);
                sendMessage.setText(message).setParseMode("Markdown");
                sendMessage.setReplyMarkup(keyboardMarkup);

//                registrollenadosList.add("Photo");
            }else {
                if (update.getMessage().hasText()){
                    //FIXME Arreglar el sendText para cuando el usuario escribe texto cuando deberia mandar imagen
                    LOGGER.info("Imagen NO válida");
                    message = "\nImagen NO válida\nPor favor, intente nuevamente\n";
//                    String imageFile = "http://www.i2clipart.com/cliparts/9/0/f/a/clipart-x-icon-256x256-90fa.png";
//                    sendPhoto.setPhoto(imageFile);
                    sendMessage.setText(message);
                    setNumero_de_pregunta(6);
                }


            }
        }else {
            LOGGER.info("No esta en preg 6 y no esta agregando contactos");
            message = "No puede subir imagenes en este momento";
            sendMessage.setText(message);
        }

        LOGGER.info("Tamaño del array después: "+registrollenadosList.size());

    }

    private void mostrarMenu(SendMessage sendMessage, long chatId) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
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

    private void mostrarContacto(String messageTextReceived, String message, SendMessage sendMessage, SendPhoto sendPhoto, List<KeyboardRow> keyboard, ReplyKeyboardMarkup keyboardMarkup) {
        LOGGER.info("Open Contact");
        ContactEntity contactEntity = new ContactEntity();
        if (isShowingContactAfterList){
            LOGGER.info("Is showing after list");
            int index = messageTextReceived.indexOf(" ");
            id = Integer.parseInt(messageTextReceived.substring(index+1))-1;
            LOGGER.info(">>>>>>>>>>> "+(id+1));
            phoneNumberEntities = phoneNumberRepository.findByContactId(contactEntities.get(id));
            message = "*"+messageTextReceived+"*\n\n*Primer Nombre:* "+contactEntities.get(id).getFirstName()+"\n*Segundo Nombre:* "+contactEntities.get(id).getSecondName()+"\n*Primer Apellido:* "+contactEntities.get(id).getFirstSurname()+"\n*Segundo Apellido:* "+contactEntities.get(id).getSecondSurname()+"\n*Email:* "+contactEntities.get(id).getEmail()+"\n*Fecha de Nacimiento:* "+contactEntities.get(id).getBirthdate();
        }else {
            LOGGER.info("Is showing after modify");
            int newId = contactEntities.get(id).getContactId();
            contactEntity = contactRepository.findByContactId(newId);
            LOGGER.info(">>>>>>>>>>> "+(id+1));
            phoneNumberEntities = phoneNumberRepository.findByContactId(contactEntity);
            message = "*"+messageTextReceived+"*\n\n*Primer Nombre:* "+contactEntity.getFirstName()+"\n*Segundo Nombre:* "+contactEntity.getSecondName()+"\n*Primer Apellido:* "+contactEntity.getFirstSurname()+"\n*Segundo Apellido:* "+contactEntity.getSecondSurname()+"\n*Email:* "+contactEntity.getEmail()+"\n*Fecha de Nacimiento:* "+contactEntity.getBirthdate();
        }

        message += "\n\n*_Números de teléfono_*";
        for (int j=0;j<phoneNumberEntities.size();j++){
            message += "\n*Número "+(j+1)+": *"+phoneNumberEntities.get(j).getNumber();
        }
        message += "\n\n*Imagen: *";
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardRow keyboardRow2 = new KeyboardRow();
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow.add("Salir al Menú Principal");
        keyboardRow2.add("Modificar Contacto");
        keyboardRow3.add("Eliminar Contacto");
        keyboard.add(keyboardRow);
        keyboard.add(keyboardRow2);
        keyboard.add(keyboardRow3);
        sendMessage.setText(message).setParseMode("Markdown");
        if (isShowingContactAfterList){
            sendPhoto.setPhoto(FileUtils.getFile(contactEntities.get(id).getImage()));
        }else {
            sendPhoto.setPhoto(FileUtils.getFile(contactEntity.getImage()));
        }

        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);
    }

    private void mostrarOpcionesDespuesModificar(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow.add("Mostrar el Contacto Actualizado");
        keyboardRow2.add("Salir al Menú Principal");
        keyboard.add(keyboardRow);
        keyboard.add(keyboardRow2);
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);
    }

    private static void saveFileFromUrlWithCommonsIO(String fileName, String fileUrl) throws MalformedURLException, IOException {
        FileUtils.copyURLToFile(new URL(fileUrl), new File(fileName));
    }

    private List<ContactEntity> getContactsThatInclude(String input, User user) {
        KjUserEntity kjUserEntity = kjUserRepository.findByBotUserId(Integer.toString(user.getId()));
        LOGGER.info("User ID: "+kjUserEntity.getUserid());
        List<ContactEntity> receivedContactEntities = contactRepository.findByFirstNameContainingOrSecondNameContainingOrFirstSurnameContainingOrSecondSurnameContaining(input,input,input,input);
        List<ContactEntity> result = new ArrayList<>();
        LOGGER.info("Before >> "+receivedContactEntities.toString());
        for (int i = 0; i<receivedContactEntities.size(); i++){
            ContactEntity contactEntity = receivedContactEntities.get(i);
            LOGGER.info("Contacto "+(i+1)+">> "+contactEntity.toString());
            if (contactEntity.getStatus() == 0 || contactEntity.getUserId().getUserid() != kjUserEntity.getUserid()){
                LOGGER.info("Contacto "+(i+1)+" NO pasó");
            }else {
                LOGGER.info("Contacto "+(i+1)+" Pasó");
                result.add(contactEntity);
            }
            LOGGER.info("Status "+contactEntity.getStatus()+" (0)");
        }
        LOGGER.info("After >> "+result.toString());
        return result;
    }

    private List<ContactEntity> getContactsByPhoneNumber(String phone, User user) {
        KjUserEntity kjUserEntity = kjUserRepository.findByBotUserId(Integer.toString(user.getId()));
        LOGGER.info("User ID: "+kjUserEntity.getUserid());
        receivedPhoneNumbers = phoneNumberRepository.findByNumberContaining(phone);
        List<ContactEntity> result = new ArrayList<>();
        for (int i = 0; i<receivedPhoneNumbers.size(); i++){
            ContactEntity contactEntity = contactRepository.findByContactId(receivedPhoneNumbers.get(i).getContactId().getContactId());
            if (contactEntity.getStatus() == 0 || contactEntity.getUserId().getUserid() != kjUserEntity.getUserid() || receivedPhoneNumbers.get(i).getStatus() == 0){
                LOGGER.info("Contacto "+(i+1)+" NO pasó");
            }else {
                boolean alreadyExists = false;
                for (int k = 0; k<result.size(); k++){
                    if (result.get(k).getContactId() == contactEntity.getContactId()){
                        alreadyExists = true;
                    }
                }
                if (alreadyExists == false){
                    result.add(contactEntity);
                }
            }
        }
        return result;
    }

    public void restart(){
        numero_de_pregunta=0;
        registrosllenos=false;
        registrollenadosList= new ArrayList<>();


        fileID = "";
        filePath = "";
        photoURL = "";

        entra_a_agregar_contactos=false;
        entra_a_agregar_phonenumbers=false;
        entra_a_buscar_contactos=false;
        entra_a_modificar_contactos=false;

        iNumbers=0;
        numNumbers=0;
        isOpeningContact = false;

        isSearchingByName = false;
        isSearchingByPhone = false;
        searchInputReceived = false;

        isChoosingField = true;
        isDeletingContact = false;
        isModifyingPhoneNumber = false;
        isAddingPhoneNumber = false;
        isCreatingNewPhoneNumber = false;
        isShowingContactAfterList = false;
        modFirstName = false;
        modSecondName = false;
        modFirstSurname = false;
        modSecondSurname = false;
        modEmail = false;
        modBirthDate = false;
        modImage = false;
        modPhoneNumbers = false;
        registroContactoExitoso = false;
        recentlyAddedContact = new ContactEntity();
        contactEntities = new ArrayList<>();
        phoneNumberEntities = new ArrayList<>();
        receivedPhoneNumbers = new ArrayList<>();
        id = 0;
        pId = 0;
    }

}
