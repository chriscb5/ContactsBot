package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ucb.edu.kajoybot.bo.databasekajoy.dao.*;
import ucb.edu.kajoybot.bo.databasekajoy.domain.*;
import ucb.edu.kajoybot.bo.databasekajoy.dto.Status;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MensajesBL {


    private static final Logger LOGGER= LoggerFactory.getLogger(MensajesBL.class);
    private static int numero_de_pregunta=0;
    private static int numero_de_respuesta=0;
    private static int numero_de_pregunta_respondiendo=1;
    private static int nummero_de_respuesta_respondiendo=1;
    private static boolean registrosllenos=false;
    private static boolean entra_a_registro_estudiante=false;
    private static boolean entra_a_verificacion_estudiante = false;
    private static boolean entra_a_registro_docente=false;
    private static boolean entra_a_registro_curso=false;
    private static boolean entra_a_iniciar_estudiante=false;
    private static boolean entra_a_iniciar_docente=false;
    private static boolean entra_a_iniciar_docentenombre=false;
    private static boolean entra_a_registro_test=false;
    private static boolean entra_a_responder_test=false;
    private static boolean entra_a_registro_respuesta=true;
    private static boolean entra_a_registro_estudiante_curso=false;
    private static boolean entra_a_listado_estudiantes=false;
    private static boolean entra_a_listado_docentes=false;
    private static boolean entra_a_listado_cursos=false;
    private static boolean aniade_pregunta_nueva=false;
    private static boolean aniade_respuesta_nueva=false;
    private static boolean termina_test=false;
    private static boolean confirmation=false;
    private static boolean entra_a_menu_student=false;
    private static boolean entra_a_menu_curse_student=false;
    private static boolean entra_a_menu_testcurse_student=false;
    private static boolean entra_a_menu_testcurse_docent=false;
    private static boolean entra_a_menu_docent=false;
    private static boolean entra_a_menu_curse_docent=false;
    private static List<String> registrollenadosList= new ArrayList<>();
    private static List<String> registrorespuestalist=new ArrayList<>();

    private List<PhotoSize> photo = null;
    private String fileID = "";

    private boolean rcIsPublico = false;
    private boolean rcIsPrivado = false;

    private boolean isInMenuEC = true;
    private boolean isCursoPublico = false;
    private boolean isCursoPrivado = false;
    private boolean isRegisteringCursoPrivado = false;

    private static boolean entra_a_agregar_contactos=false;
    private static boolean entra_a_agregar_phonenumbers=false;
    private static boolean entra_a_eliminar_contactos=false;

    private int iNumbers=0;
    private int numNumbers=0;
    private static boolean registroContactoExitoso=false;
    private ContactEntity recentlyAddedContact = new ContactEntity();

    private EstudianteRespository estudianteRespository;
    private DocenteRespository  docenteRespository;
    private CursoRepository cursoRepository;
    private EstudianteCursoRepository estudianteCursoRepository;
    private KjEstudianteUserRepository kjEstudianteUserRepository;
    private TestRepository testRepository;
    private RespuestaRepository respuestaRepository;
    private PreguntaRepository preguntaRepository;
    private ChatRepository chatRepository;
    private EstudianteTestRepository estudianteTestRepository;
    private ContactRepository contactRepository;
    private PhoneNumberRepository phoneNumberRepository;
    private KjUserRepository kjUserRepository;
    private PersonBL personBL;

    @Autowired
    public MensajesBL(EstudianteRespository estudianteRespository, DocenteRespository docenteRespository, CursoRepository cursoRepository, KjEstudianteUserRepository kjEstudianteUserRepository, TestRepository testRepository, RespuestaRepository respuestaRepository, PreguntaRepository preguntaRepository, ChatRepository chatRepository, PersonBL personBL,EstudianteTestRepository estudianteTestRepository, EstudianteCursoRepository estudianteCursoRepository, ContactRepository contactRepository, PhoneNumberRepository phoneNumberRepository, KjUserRepository kjUserRepository) {
        this.estudianteRespository = estudianteRespository;
        this.docenteRespository = docenteRespository;
        this.cursoRepository = cursoRepository;
        this.kjEstudianteUserRepository = kjEstudianteUserRepository;
        this.testRepository = testRepository;
        this.respuestaRepository = respuestaRepository;
        this.preguntaRepository = preguntaRepository;
        this.chatRepository = chatRepository;
        this.personBL = personBL;
        this.estudianteTestRepository = estudianteTestRepository;
        this.estudianteCursoRepository = estudianteCursoRepository;
        this.contactRepository = contactRepository;
        this.phoneNumberRepository = phoneNumberRepository;
        this.kjUserRepository = kjUserRepository;
    }


    public String mensajesRegistroEstudiante()
    {
        String cadena=new String();
        switch (numero_de_pregunta){
            case 0:
                LOGGER.info("Ingresando apellido paterno");
                cadena="Ingrese su apellido paterno ";
                break;
            case 1:
                LOGGER.info("Ingresando apellido materno");
                cadena="Ingrese su apellido materno ";
                break;
            case 2:
                LOGGER.info("Ingresando institucion");
                cadena="Ingrese su institución ";
                break;
            case 3:
                LOGGER.info("Ingresando nombre de usuario");
                cadena="Ingrese su nombre de usuario";

                break;
        }
        return cadena;
    }


    public  String mensajesRegistroDocente()
    {
        String cadena=new String();
        switch (numero_de_pregunta){
            case 0:
                LOGGER.info("Ingresando apellido paterno");
                cadena="Ingrese apellido paterno ";
                break;
            case 1:
                LOGGER.info("Ingresando apellido materno");
                cadena="Ingrese su apellido materno ";
                break;
            case 2:
                LOGGER.info("Ingresando nombre de usuario");
                cadena="Ingrese su nombre de usuario";
                break;
        }
        return cadena;
    }


    public  String mensajesRegistroCurso()
    {
        String cadena=new String();
        switch (numero_de_pregunta){
            case 0:
                LOGGER.info("Ingresando tipo de curso");
                cadena="Ingrese el tipo del curso ";
                break;
            case 1:
                LOGGER.info("Ingresando clave del curso privado");
                cadena="Ingrese la clave del curso";
                break;
        }
        return cadena;
    }

    public  String mensajesRegistroEstudianteCurso()
    {
        String cadena=new String();
        switch (numero_de_pregunta){
            case 0:
                LOGGER.info("Curso publico");
                cadena="El curso es publico\nPuede ingresar sin necesidad de una clave";
                break;
            case 1:
                LOGGER.info("Curso privado. Ingresando clave");
                cadena="El curso es privado\nPor favor, ingrese la clave del curso";
                break;
        }
        return cadena;
    }


    public  String mensajeRegistroTest(){
        String caden=new String();
        switch (numero_de_pregunta){
            case 0:
                caden="¿Cuál sera la pregunta?";
                break;
      }
        return caden;
    }

    public String mensajeRegistroRespuesta(){
        String cadena=new String();
        switch (numero_de_respuesta)
        {
            case 0:
                cadena="Ingrese la primera respuesta";
                break;
            case 1:
                cadena="Ingrese la segunda respuesta";
                break;
            case 2:
                cadena="Ingrese la tercera respuesta";
                break;
            case 3:
                cadena="Ingrese la cuarta respuesta";
                break;
        }
        return cadena;
    }

    public String mensajeAgregarContactos(){
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


    public static int getNumero_de_pregunta() {
        return numero_de_pregunta;
    }

    public static void setNumero_de_pregunta(int numero_de_pregunta) {
        MensajesBL.numero_de_pregunta = numero_de_pregunta;
    }

    public static int getNumero_de_respuesta() {
        return numero_de_respuesta;
    }

    public static void setNumero_de_respuesta(int numero_de_respuesta) {
        MensajesBL.numero_de_respuesta = numero_de_respuesta;
    }
    public String iniciarEstudiante(String messageTextReceived){
        String response=personBL.existPasswordEstudianteByCurso("Lalo",messageTextReceived);
        entra_a_iniciar_estudiante=false;
        return response;
    }

    public String iniciarDocente(String messageTextReceived){
        LOGGER.info("ENTRO AQUIIIII SISISISI");
        String response=personBL.ExistDocenteByNombre(messageTextReceived);
        entra_a_iniciar_docentenombre=false;
        return response;
    }

    public String entraRegistroEstudiante(SendMessage sendMessage,String messageTextReceived){
        LOGGER.info("Entra a el registro estudiante oficial");
        String mensaje="";
        if(registrollenadosList.size()<5) {
            LOGGER.info("Entra al registros no llenos");
            if(getNumero_de_pregunta()<4){
                mensaje = mensajesRegistroEstudiante();
            }
            setNumero_de_pregunta(getNumero_de_pregunta()+1) ;//
            registrollenadosList.add(messageTextReceived);
            LOGGER.info("Tamaño de array "+registrollenadosList.size());
        }
        if (registrollenadosList.size()==5) {
            LOGGER.info("Ingresa a registros llenos");
            mensaje = guardarListaRegistros(registrollenadosList);
            registrosllenos = false;
            registrollenadosList.clear();
            entra_a_registro_estudiante = false;
            setNumero_de_pregunta(0) ;//
        }
        return mensaje;
    }

    public String entraverficarEstudiante(SendMessage sendMessage,String messageTextReceived){
        LOGGER.info("Entra a el verificar estudiante oficial");
        String mensaje="";
        sendMessage.getChatId();
        KjUserRepository kjUserRepository = null;
        String id =sendMessage.getChatId();
        //arregalra if
        Random rand = null;

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((2 - 0) + 1) + 0;

        if(randomNum == 0){
            entraRegistroDocente(sendMessage, messageTextReceived);
        }
        else{
            mensaje = "Que gusto tenerte de nuevo!";
        }

        return mensaje;
    }


    public String entraRegistroDocente(SendMessage sendMessage,String messageTextReceived){
        LOGGER.info("Entra a el registro docente oficial");
        String mensaje="";
        if(registrollenadosList.size()<4)
        {
            LOGGER.info("Entra al registros no llenos");
            if(getNumero_de_pregunta()<3){
                mensaje = mensajesRegistroDocente();
            }
            setNumero_de_pregunta(getNumero_de_pregunta()+1) ;//
            registrollenadosList.add(messageTextReceived);
        }
        if (registrollenadosList.size()==4) {
            LOGGER.info("Ingresa a registros llenos");
            mensaje = guardarListaRegistrosDocente(registrollenadosList);
            registrosllenos = false;
            registrollenadosList.clear();
            entra_a_registro_docente = false;
            setNumero_de_pregunta(0) ;//
        }
        return mensaje;
    }



    public void entraRegistroCurso(SendMessage sendMessage,String messageTextReceived){
        LOGGER.info("Entra a el registro curso oficial");
        String mensaje="";
        if(registrollenadosList.size()<3) {
            LOGGER.info("Entra al registros no llenos");
            if(getNumero_de_pregunta()<2){
                mensaje = mensajesRegistroCurso();
                if (getNumero_de_pregunta()==1 && messageTextReceived.equals("publico")){
//                    mensaje += "\nReceived curso publico\n";
                    rcIsPublico = true;
                    rcIsPrivado = false;
                }else {
                    if (getNumero_de_pregunta()==1 && messageTextReceived.equals("privado")){
//                        mensaje += "\nReceived curso privado\n";
                        rcIsPrivado = true;
                        rcIsPublico = false;
                    }else {
                        if (getNumero_de_pregunta()==1){
                            mensaje += "Error!\nIngrese un tipo de curso válido";
                            setNumero_de_pregunta(1);
                            registrollenadosList.remove(1);
                        }
                    }
                }
            }
            if (rcIsPublico==true){
                setNumero_de_pregunta(3);
                registrollenadosList.add(messageTextReceived);
                registrollenadosList.add(null);
            }
            if (rcIsPrivado==true || (rcIsPrivado==false && rcIsPublico==false) ){
                setNumero_de_pregunta(getNumero_de_pregunta()+1);
                registrollenadosList.add(messageTextReceived);
            }

            LOGGER.info("Tamaño de array "+registrollenadosList.size());
            sendMessage.setText(mensaje);
        }
        if (registrollenadosList.size()==3) {
            LOGGER.info("Ingresa a registros llenos");
            sendMessage.setText(guardarListaRegistrosCurso(registrollenadosList));
            registrosllenos = false;
            registrollenadosList.clear();
            entra_a_registro_curso = false;
            rcIsPublico = false;
            rcIsPrivado = false;
        }
    }

    public void entraRegistroEstudianteCurso(String messageTextReceived, SendMessage sendMessage){
        //FIXME agregar registro en la tabla EstudianteCurso
        LOGGER.info("Entra a el registro de estudiante en curso");
        String mensaje="";
        KeyboardRow row= new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        List<KeyboardRow> keyboard= new ArrayList<>();

//        mensaje += messageTextReceived;
//        sendMessage.setText(mensaje);

        if (messageTextReceived.equals("SI") && isCursoPublico==true || messageTextReceived.length()>2 && isCursoPrivado==true || messageTextReceived.equals("NO") && isCursoPublico==true){
            //FIXME al momento de crear nuevo curso, validar que la clave ingresada sea de mas de 3 caracteres
            isInMenuEC=false;
        }

        if (isInMenuEC==true){
            LOGGER.info("Entra a menu EC");
            String cursoID = messageTextReceived;
            if (existsCursoByIdCurso(cursoID)){
                registrollenadosList.add(cursoID);
                mensaje += "Curso encontrado con el Nombre = "+getNombreCurso(cursoID)+"\n";
                LOGGER.info(getNombreCurso(cursoID) +" ; "+ getTipoCurso(cursoID));
                if(getTipoCurso(cursoID).equals("publico")){
                    isCursoPublico = true;
                    isCursoPrivado = false;
                    setNumero_de_pregunta(0);
                    mensaje += mensajesRegistroEstudianteCurso()+"\n";
                    LOGGER.info("Registro curso publico exitoso");

                    mensaje += "Está seguro que quieres registrarte al curso \n'"+getNombreCurso(messageTextReceived)+"'?";

                    row.add("SI");
                    row.add("NO");
                    keyboard.add(row);
                    keyboardMarkup.setKeyboard(keyboard);
                    sendMessage.setReplyMarkup(keyboardMarkup);
                    sendMessage.setText(mensaje);
                }else{
                    if (getTipoCurso(cursoID).equals("privado")){
                        isCursoPrivado = true;
                        isCursoPublico = false;
                        setNumero_de_pregunta(1);
                        mensaje += mensajesRegistroEstudianteCurso();
                        sendMessage.setText(mensaje);
                    }
                }
            }else{
                mensaje += "No se encontró ningún curso con el código ingresado.\nPor favor intente nuevamente";
                sendMessage.setText(mensaje);
            }
        } else{
            LOGGER.info("Entra a registro EC");
            if (isCursoPublico){
                LOGGER.info("Entra registro curso publico");
                if (messageTextReceived.equals("SI")){
                    mensaje = guardarListaRegistrosEstudianteCurso(registrollenadosList);
                    sendMessage.setText(mensaje);
                    registrosllenos = false;
                    registrollenadosList.clear();
                    entra_a_registro_estudiante_curso = false;
                    isInMenuEC = true;
                    isCursoPublico = false;
                    isCursoPrivado = false;
                    isRegisteringCursoPrivado = false;
                }else {
                    if (messageTextReceived.equals("NO")){
                        mensaje = "Registro Cancelado";
                        sendMessage.setText(mensaje);
                        registrosllenos = false;
                        registrollenadosList.clear();
                        entra_a_registro_estudiante_curso = false;
                        isInMenuEC = true;
                        isCursoPublico = false;
                        isCursoPrivado = false;
                        isRegisteringCursoPrivado = false;
                    }
                }
            }
            if (isCursoPrivado){
                if (isRegisteringCursoPrivado==false){
                    LOGGER.info("Entra registro curso privado");
                    if (messageTextReceived.equals(getClaveCurso(registrollenadosList.get(0)))){
                        isRegisteringCursoPrivado=true;
                        registrollenadosList.add(messageTextReceived);
                        mensaje = "Clave correcta\n";

                        mensaje += "Está seguro que quieres registrarte al curso \n'"+getNombreCurso(registrollenadosList.get(0))+"'?";

                        row.add("SI");
                        row.add("NO");
                        keyboard.add(row);
                        keyboardMarkup.setKeyboard(keyboard);
                        sendMessage.setReplyMarkup(keyboardMarkup);
                        sendMessage.setText(mensaje);
                    }else{
                        isRegisteringCursoPrivado=false;
                        mensaje = "Error! Clave incorrecta.\nIntente nuevamente";
                        sendMessage.setText(mensaje);
                    }
                }else {
                    if (isRegisteringCursoPrivado==true && messageTextReceived.equals("SI")){
                        mensaje = guardarListaRegistrosEstudianteCurso(registrollenadosList);
                        sendMessage.setText(mensaje);
                        registrosllenos = false;
                        registrollenadosList.clear();
                        entra_a_registro_estudiante_curso = false;
                        isInMenuEC = true;
                        isCursoPublico = false;
                        isCursoPrivado = false;
                        isRegisteringCursoPrivado = false;
                    }else{
                        if (messageTextReceived.equals("NO")){
                            mensaje = "Registro Cancelado";
                            sendMessage.setText(mensaje);
                            registrosllenos = false;
                            registrollenadosList.clear();
                            entra_a_registro_estudiante_curso = false;
                            isInMenuEC = true;
                            isCursoPublico = false;
                            isCursoPrivado = false;
                            isRegisteringCursoPrivado = false;
                        }
                    }
                }

            }
        }
    }


    public void entraListadoEstudiantes(SendMessage sendMessage){
        LOGGER.info("Entra al listado de estudiantes");
        //EstudianteEntity estudianteEntity = new EstudianteEntity();
        List<EstudianteEntity> estudianteEntities = estudianteRespository.findAllByStatuss(1);
        String message = "";
        message += "ID\t|\tNOMBRE\t|\t|APELLIDOP\t|\tAPELLIDOM\t|\tINSTITUCION\t|\tUSER\n";
        for (int i=0; i<estudianteEntities.size(); i++){
            message += estudianteEntities.get(i).getIdEstudiante()+"\t|\t"+estudianteEntities.get(i).getNombre()+"\t|\t"
                    +estudianteEntities.get(i).getApellidoPaterno()+"\t|\t"+estudianteEntities.get(i).getApellidoMaterno()+"\t|\t"
                    +estudianteEntities.get(i).getInstitucion()+"\t|\t"+estudianteEntities.get(i).getTxUser()+"\n";
        }
        sendMessage.setText(message);
//        return sendMessage;
    }

    public void entraListadoDocentes(SendMessage sendMessage){
        LOGGER.info("Entra al listado de estudiantes");
        //EstudianteEntity estudianteEntity = new EstudianteEntity();
        List<DocenteEntity> docenteEntities = docenteRespository.findAllByStatuss(1);
        String message = "";
        message += "ID\t|\tNOMBRE\t|\t|APELLIDOP\t|\tAPELLIDOM\t|\tUSER\n";
        for (int i=0; i<docenteEntities.size(); i++){
            message += docenteEntities.get(i).getIdDocente()+"\t|\t"+docenteEntities.get(i).getNombre()+"\t|\t"
                    +docenteEntities.get(i).getApellidoPaterno()+"\t|\t"+docenteEntities.get(i).getApellidoMaterno()+"\t|\t"
                    +docenteEntities.get(i).getTxUser()+"\n";
        }
        sendMessage.setText(message);
    }

    public void entraListadoCursos(SendMessage sendMessage){
        LOGGER.info("Entra al listado de estudiantes");
        //EstudianteEntity estudianteEntity = new EstudianteEntity();
        List<CursoEntity> cursoEntities = cursoRepository.findAll();
        String message = "";
        message += "ID\t|\tNOMBRE\t|\t|TIPO CURSO\n";
        for (int i=0; i<cursoEntities.size(); i++){
            message += cursoEntities.get(i).getIdCurso()+"\t|\t"+cursoEntities.get(i).getNombre()+"\t|\t"
                    +cursoEntities.get(i).getTipoCurso()+"\n";
        }
        sendMessage.setText(message);
//        return sendMessage;
    }

    public void entraAgregarContactos(String messageTextReceived, List<PhotoSize> photoReceived, SendMessage sendMessage, SendPhoto sendPhoto, Update update){
        LOGGER.info("Entra a agregar contactos");
        String message = "";
        KeyboardRow row= new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        List<KeyboardRow> keyboard= new ArrayList<>();
        sendMessage.setReplyMarkup(replyKeyboardRemove);

        if (update.getMessage().hasPhoto()){
            photo = photoReceived;
        }

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
                            registrollenadosList.add("photo");

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
                                LOGGER.info("File Path: "+jsonObject.getJSONObject("result").getString("file_path"));
//                                JSONObject jsonObject = new JSONObject("https://api.telegram.org/bot1062478290:AAG3C68x6eCwe0VSC2uyb4OR74_c15lWY4k/getFile?file_id="+fileID);
//                                LOGGER.info("JSONObject: "+jsonObject.toString());
//                                URL url = new URL("https://api.telegram.org/bot1062478290:AAG3C68x6eCwe0VSC2uyb4OR74_c15lWY4k/getFile?file_id="+fileID);
//                                LOGGER.info(url.toString());
//                  File archivo = new File("https://api.telegram.org/"+"1062478290:AAG3C68x6eCwe0VSC2uyb4OR74_c15lWY4k"+"/"+jsonObject.getString("file_path"));
//                                URL url = new URL("https://api.telegram.org/"+"1062478290:AAG3C68x6eCwe0VSC2uyb4OR74_c15lWY4k"+"/"+file.getFilePath());
//                                FileUtils.copyURLToFile(url,archivo);
//                                LOGGER.info("JSON Response >> "+jsonObject.toString());
//                                LOGGER.info("File path:: "+archivo.getPath());
                            }catch (Exception e){
                                LOGGER.info("Error al guardar imagen");
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

    public void entraEliminarContactos(String messageTextReceived, SendMessage sendMessage, Update update){
        //FIXME Completar eliminar contactos
        LOGGER.info("Entra eliminar contactos");
        String message = "";
        KeyboardRow row= new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        List<KeyboardRow> keyboard= new ArrayList<>();
        sendMessage.setReplyMarkup(replyKeyboardRemove);
        if (existsContactByIdContact(messageTextReceived)){
            message = "Id existente";
        }
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
            if (validatePhoneNumber(messageTextReceived)==true  && validateNumber(messageTextReceived)==false || registrollenadosList.size()==0 && validateNumber(messageTextReceived)==false){
                LOGGER.info("Entra add num");
                registrollenadosList.add(messageTextReceived);
            }else {
                if (validateNumber(messageTextReceived)==false){
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

    public String afirmacionAdicionarPregunta(){
        //SI
        confirmation=true;
        aniade_pregunta_nueva=true;
        entra_a_registro_test=true;
        entra_a_registro_respuesta=false;
        return "Añada nuevamente su pregunta";

    }

    public String afirmacionTerminarRegistroTest(){
        //NO
        String registrertest=  saveCompleteTest(registrollenadosList,registrorespuestalist);
        registrollenadosList.clear();
        registrorespuestalist.clear();
        entra_a_registro_test=false;
        return registrertest;
    }


    public void entraARegistroTest(SendMessage sendMessage,String messageTextReceived){
        String mensaje="";
        if (entra_a_registro_test){
            if(entra_a_registro_respuesta){
                if(getNumero_de_respuesta()==4) {
                    registrorespuestalist.add(messageTextReceived);
                    LOGGER.info("SIIIIIIII ENTRA A REGISTRO RESPUESTA TERMINADO");
                    LOGGER.info("ENTRO AL SI CON ARRAY "+registrorespuestalist.size());
                    int i=0;
                    int contresppropregunta=4;
                    String cade="CUESTIONARIO - TEST\n";
                    for(String preg:registrollenadosList){
                        cade+=preg+"\n";
                        int numero=1;
                        while(i<contresppropregunta){
                            cade+=numero+". "+registrorespuestalist.get(i)+"\n";
                            numero++;
                            i++;
                        }
                        contresppropregunta+=4;
                    }
                    setNumero_de_respuesta(0);
                    mensaje+=cade;
                    KeyboardRow row= new KeyboardRow();
                    ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
                    List<KeyboardRow> keyboard= new ArrayList<>();
                    mensaje+="\nDesea añadir una nueva pregunta?";
                    sendMessage.setText(mensaje);
                    row.add("Si");
                    row.add("No");
                    keyboard.add(row);
                    keyboardMarkup.setKeyboard(keyboard);
                    sendMessage.setReplyMarkup(keyboardMarkup);
                    entra_a_registro_respuesta=false;
                    aniade_respuesta_nueva=true;
                    aniade_pregunta_nueva=false;

                }//Terminate NUMERO DE RESPUESTAS= 4

                if(getNumero_de_respuesta()<4 && entra_a_registro_respuesta) {
                    //INGRESANDO A REGISTROS NO COMPLETOS
                    sendMessage.setText(mensajeRegistroRespuesta());
                    setNumero_de_respuesta(getNumero_de_respuesta()+1);
                    if(aniade_pregunta_nueva==false){
                        registrorespuestalist.add(messageTextReceived);
                    }
                    aniade_respuesta_nueva=false;
                }
            }

            if(entra_a_registro_respuesta==false && aniade_pregunta_nueva) {
                sendMessage.setText(mensajeRegistroTest());
                setNumero_de_pregunta(/*mensajesBL.getNumero_de_pregunta()+1*/0);
                entra_a_registro_respuesta=true;
            }
            if(aniade_pregunta_nueva && confirmation==false){
                registrollenadosList.add(messageTextReceived);
                aniade_pregunta_nueva=false;
            }
            if(confirmation==true){
                entra_a_registro_respuesta=true;
                confirmation=false;
            }

        }// TERMINA REGISTRO TEST

    }

    public static boolean isRegistrosllenos() {
        return registrosllenos;
    }

    public static void setRegistrosllenos(boolean registrosllenos) {
        MensajesBL.registrosllenos = registrosllenos;
    }

    public static boolean isEntra_a_registro_estudiante() {
        return entra_a_registro_estudiante;
    }
    public static boolean isEntra_a_verificacion_estudiante() {
        return entra_a_verificacion_estudiante;
    }

    public static void setEntra_a_registro_estudiante(boolean entra_a_registro_estudiante) {
        MensajesBL.entra_a_registro_estudiante = entra_a_registro_estudiante;
    }

    public static void setEntra_a_verificacion_estudiante(boolean entra_a_registro_estudiante) {
        MensajesBL.entra_a_verificacion_estudiante = entra_a_registro_estudiante;
    }

    public static boolean isEntra_a_registro_docente() {
        return entra_a_registro_docente;
    }

    public static void setEntra_a_registro_docente(boolean entra_a_registro_docente) {
        MensajesBL.entra_a_registro_docente = entra_a_registro_docente;
    }

    public static boolean isEntra_a_registro_curso() {
        return entra_a_registro_curso;
    }

    public static void setEntra_a_registro_curso(boolean entra_a_registro_curso) {
        MensajesBL.entra_a_registro_curso = entra_a_registro_curso;
    }

    public static boolean isEntra_a_iniciar_estudiante() {
        return entra_a_iniciar_estudiante;
    }

    public static void setEntra_a_iniciar_estudiante(boolean entra_a_iniciar_estudiante) {
        MensajesBL.entra_a_iniciar_estudiante = entra_a_iniciar_estudiante;
    }

    public static boolean isEntra_a_iniciar_docente() {
        return entra_a_iniciar_docente;
    }

    public static void setEntra_a_iniciar_docente(boolean entra_a_iniciar_docente) {
        MensajesBL.entra_a_iniciar_docente = entra_a_iniciar_docente;
    }

    public static boolean isEntra_a_iniciar_docentenombre() {
        return entra_a_iniciar_docentenombre;
    }

    public static void setEntra_a_iniciar_docentenombre(boolean entra_a_iniciar_docentenombre) {
        MensajesBL.entra_a_iniciar_docentenombre = entra_a_iniciar_docentenombre;
    }

    public static boolean isEntra_a_registro_test() {
        return entra_a_registro_test;
    }

    public static void setEntra_a_registro_test(boolean entra_a_registro_test) {
        MensajesBL.entra_a_registro_test = entra_a_registro_test;
    }

    public static boolean isEntra_a_registro_respuesta() {
        return entra_a_registro_respuesta;
    }

    public static void setEntra_a_registro_respuesta(boolean entra_a_registro_respuesta) {
        MensajesBL.entra_a_registro_respuesta = entra_a_registro_respuesta;
    }

    public static boolean isEntra_a_registro_estudiante_curso() {
        return entra_a_registro_estudiante_curso;
    }

    public static void setEntra_a_registro_estudiante_curso(boolean entra_a_registro_estudiante_curso) {
        MensajesBL.entra_a_registro_estudiante_curso = entra_a_registro_estudiante_curso;
    }

    public static boolean isAniade_pregunta_nueva() {
        return aniade_pregunta_nueva;
    }

    public static void setAniade_pregunta_nueva(boolean aniade_pregunta_nueva) {
        MensajesBL.aniade_pregunta_nueva = aniade_pregunta_nueva;
    }

    public static boolean isAniade_respuesta_nueva() {
        return aniade_respuesta_nueva;
    }

    public static void setAniade_respuesta_nueva(boolean aniade_respuesta_nueva) {
        MensajesBL.aniade_respuesta_nueva = aniade_respuesta_nueva;
    }

    public static boolean isTermina_test() {
        return termina_test;
    }

    public static void setTermina_test(boolean termina_test) {
        MensajesBL.termina_test = termina_test;
    }

    public static boolean isConfirmation() {
        return confirmation;
    }

    public static void setConfirmation(boolean confirmation) {
        MensajesBL.confirmation = confirmation;
    }

    public static List<String> getRegistrollenadosList() {
        return registrollenadosList;
    }

    public static void setRegistrollenadosList(List<String> registrollenadosList) {
        MensajesBL.registrollenadosList = registrollenadosList;
    }

    public static List<String> getRegistrorespuestalist() {
        return registrorespuestalist;
    }

    public static void setRegistrorespuestalist(List<String> registrorespuestalist) {
        MensajesBL.registrorespuestalist = registrorespuestalist;
    }

    public PersonBL getPersonBL() {
        return personBL;
    }

    public void setPersonBL(PersonBL personBL) {
        this.personBL = personBL;
    }

    public static boolean isEntra_a_responder_test() {
        return entra_a_responder_test;
    }

    public static void setEntra_a_responder_test(boolean entra_a_responder_test) {
        MensajesBL.entra_a_responder_test = entra_a_responder_test;
    }

    public static int getNumero_de_pregunta_respondiendo() {
        return numero_de_pregunta_respondiendo;
    }

    public static void setNumero_de_pregunta_respondiendo(int numero_de_pregunta_respondiendo) {
        MensajesBL.numero_de_pregunta_respondiendo = numero_de_pregunta_respondiendo;
    }

    public static boolean isEntra_a_listado_estudiantes(){
        return entra_a_listado_estudiantes;
    }

    public static void setEntra_a_listado_estudiantes(boolean entra_a_listado_estudiantes) {
        MensajesBL.entra_a_listado_estudiantes = entra_a_listado_estudiantes;
    }

    public static boolean isEntra_a_listado_docentes(){
        return entra_a_listado_docentes;
    }

    public static void setEntra_a_listado_docentes(boolean entra_a_listado_docentes) {
        MensajesBL.entra_a_listado_docentes = entra_a_listado_docentes;
    }

    public static boolean isEntra_a_listado_cursos(){
        return entra_a_listado_cursos;
    }

    public static void setEntra_a_listado_cursos(boolean entra_a_listado_cursos) {
        MensajesBL.entra_a_listado_cursos = entra_a_listado_cursos;
    }

    public static boolean isEntra_a_menu_student() {
        return entra_a_menu_student;
    }

    public static void setEntra_a_menu_student(boolean entra_a_menu_student) {
        MensajesBL.entra_a_menu_student = entra_a_menu_student;
    }

    public static boolean isEntra_a_menu_curse_student() {
        return entra_a_menu_curse_student;
    }

    public static void setEntra_a_menu_curse_student(boolean entra_a_menu_curse_student) {
        MensajesBL.entra_a_menu_curse_student = entra_a_menu_curse_student;
    }

    public static boolean isEntra_a_menu_docent() {
        return entra_a_menu_docent;
    }

    public static void setEntra_a_menu_docent(boolean entra_a_menu_docent) {
        MensajesBL.entra_a_menu_docent = entra_a_menu_docent;
    }

    public static boolean isEntra_a_menu_curse_docent() {
        return entra_a_menu_curse_docent;
    }

    public static void setEntra_a_menu_curse_docent(boolean entra_a_menu_curse_docent) {
        MensajesBL.entra_a_menu_curse_docent = entra_a_menu_curse_docent;
    }

    public static boolean isEntra_a_menu_testcurse_student() {
        return entra_a_menu_testcurse_student;
    }

    public static void setEntra_a_menu_testcurse_student(boolean entra_a_menu_testcurse_student) {
        MensajesBL.entra_a_menu_testcurse_student = entra_a_menu_testcurse_student;
    }

    public static boolean isEntra_a_menu_testcurse_docent() {
        return entra_a_menu_testcurse_docent;
    }

    public static void setEntra_a_menu_testcurse_docent(boolean entra_a_menu_testcurse_docent) {
        MensajesBL.entra_a_menu_testcurse_docent = entra_a_menu_testcurse_docent;
    }

    public static boolean isEntra_a_agregar_contactos() {
        return entra_a_agregar_contactos;
    }

    public static void setEntra_a_agregar_contactos(boolean entra_a_agregar_contactos) {
        LOGGER.info("Entra a Paso 2 "+ entra_a_agregar_contactos+" "+MensajesBL.entra_a_agregar_contactos);
        MensajesBL.entra_a_agregar_contactos = entra_a_agregar_contactos;
    }

    public static boolean isEntra_a_eliminar_contactos() {
        return entra_a_eliminar_contactos;
    }

    public static void setEntra_a_eliminar_contactos(boolean entra_a_eliminar_contactos) {
        MensajesBL.entra_a_eliminar_contactos = entra_a_eliminar_contactos;
    }

    public static boolean isEntra_a_agregar_phonenumbers() {
        return  entra_a_agregar_phonenumbers;
    }

    public static void setEntra_a_agregar_phonenumbers(boolean entra_a_agregar_phonenumbers) {
        MensajesBL.entra_a_agregar_phonenumbers = entra_a_agregar_phonenumbers;
    }



    /////////////////////////////////////// GUARDAR REGISTROS

    public  String guardarListaRegistros(List<String> listaderegistros){
        EstudianteEntity estudianteEntity=new EstudianteEntity();
        estudianteEntity.setNombre(listaderegistros.get(0));
        estudianteEntity.setApellidoPaterno(listaderegistros.get(1));
        estudianteEntity.setApellidoMaterno(listaderegistros.get(2));
        estudianteEntity.setInstitucion(listaderegistros.get(3));
        estudianteEntity.setStatuss(Status.ACTIVE.getStatus());
        estudianteEntity.setTxUser(listaderegistros.get(4));
        estudianteEntity.setTxDate(new Date());
        estudianteRespository.save(estudianteEntity);
        return "¡Registro completado exitosamente¡";
    }

    public  String guardarListaRegistrosDocente(List<String> listaderegistros){
        LOGGER.info("Llega al metodo con : ");

        for (String lag:listaderegistros){
            LOGGER.info("Elemento : "+lag);
        }
        DocenteEntity docenteEntity=new DocenteEntity();
        docenteEntity.setNombre(listaderegistros.get(0));
        docenteEntity.setApellidoPaterno(listaderegistros.get(1));
        docenteEntity.setApellidoMaterno(listaderegistros.get(2));
        docenteEntity.setStatuss(Status.ACTIVE.getStatus());
        docenteEntity.setTxUser(listaderegistros.get(3));
        docenteEntity.setTxDate(new Date());
        LOGGER.info("Entidad docente "+docenteEntity.toString());
        docenteRespository.save(docenteEntity);
        return "¡Registro completado exitosamente¡";
    }

    public  String guardarListaRegistrosEstudianteCurso(List<String> listaderegistros){
        LOGGER.info("Llega al metodo con : ");

        for (String lag:listaderegistros){
            LOGGER.info("Elemento : "+lag);
        }
        EstudianteCursoEntity estudianteCursoEntity = new EstudianteCursoEntity();
        EstudianteEntity estudianteEntity = estudianteRespository.findAllByIdEstudiante(1);
        CursoEntity cursoEntity= cursoRepository.findByIdCurso(Integer.parseInt(listaderegistros.get(0)));
        estudianteCursoEntity.setIdEstudiante(estudianteEntity);
        estudianteCursoEntity.setIdCurso(cursoEntity);
        //estudianteCursoEntity.setIdCurso(listaderegistros.get());
        LOGGER.info("Entidad estudiante_curso "+estudianteCursoEntity.toString());
        estudianteCursoRepository.save(estudianteCursoEntity);
        return "¡Registro completado exitosamente!";
    }

    public  String guardarListaRegistrosCurso(List<String> listaderegistros){
        LOGGER.info("Llega al metodo con : ");

        for (String lag:listaderegistros){
            LOGGER.info("Elemento : "+lag);
        }
        DocenteEntity docenteEntity = new DocenteEntity(1);
        CursoEntity cursoEntity=new CursoEntity();
        cursoEntity.setIdDocente(docenteEntity);
        cursoEntity.setNombre(listaderegistros.get(0));
        cursoEntity.setTipoCurso(listaderegistros.get(1));
        cursoEntity.setClave(listaderegistros.get(2));
        cursoEntity.setAudiencia(0);
        LOGGER.info("Entidad curso "+cursoEntity.toString());
        cursoRepository.save(cursoEntity);
        return "¡Registro completado exitosamente¡";
    }

    public String guardarListaAgregarContactos(List<String> listaderegistros, User user) {
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

    public String guardarListaAgregarPhoneNumbers(List<String> listaderegistros) {
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


//*********************************************
//*********************************************
    public String saveCompleteTest(List<String> questionsList,List<String> responseList ){
        saveTest();
        saveQuestionList(questionsList);
        saveResponseList(responseList,questionsList);
        saveAllStudentsInTheTest("FrutaTest");

        return "REGISTRO DE TEST COMPLETADO";
        // FIXME COMPLETAR EL REGISTRO DEL TEST A TODOS LOS ESTUDIANTES PERTENECIENTES A EL CURSO
    }

    private void saveTest(){
        TestEntity testEntity=new TestEntity();
        CursoEntity cursoEntity= cursoRepository.findByIdCurso(2);
        LOGGER.info("ENCONTRO en save test esto "+cursoEntity.getClave()+" "+cursoEntity.getNombre()+" "+cursoEntity.getTipoCurso());
        testEntity.setIdCurso(cursoEntity);
        DocenteEntity docenteEntity;
        docenteEntity=docenteRespository.findAllByIdDocente(1);
        testEntity.setIdDocente(docenteEntity);
        testEntity.setNombreTest("FrutaTest");
        testRepository.save(testEntity);
    }

    private void  saveQuestion(String question,int questionNumber){
        PreguntaEntity preguntaEntity= new PreguntaEntity();
        TestEntity testEntity=testRepository.findByNombreTest("FrutaTest");
        preguntaEntity.setIdTest(testEntity);
        preguntaEntity.setContenidoPregunta(question);
        preguntaEntity.setNumeroPregunta(questionNumber);
        preguntaRepository.save(preguntaEntity);
    }

    private void saveQuestionList(List<String> questionList){
        int count=1;
        for (String question:questionList){
            saveQuestion(question,count);
            count++;
        }

    }


    private void saveResponseList(List<String> responseList,List<String> questionList)
    {
        TestEntity testEntity=testRepository.findByNombreTest("FrutaTest");
        int numberResponse=1;
        int indexQuestionList=0;
        PreguntaEntity preguntaEntity=preguntaRepository.findByContenidoPreguntaAndIdTest(questionList.get(indexQuestionList),testEntity);
        for(int i=0;i<responseList.size();i++){
            if(numberResponse==5 && indexQuestionList<questionList.size()-1){
                indexQuestionList++;
                preguntaEntity=preguntaRepository.findByContenidoPreguntaAndIdTest(questionList.get(indexQuestionList),testEntity);
                numberResponse=1;
                saveResponse(responseList.get(i),preguntaEntity,numberResponse);
            }
            else {
                saveResponse(responseList.get(i),preguntaEntity,numberResponse);
            }
            numberResponse++;
        }
    }

    private void  saveResponse(String response,PreguntaEntity preguntaEntity,int numberResponse){
        RespuestaEntity respuestaEntity= new RespuestaEntity();
        respuestaEntity.setIdPregunta(preguntaEntity);
        respuestaEntity.setContenidoRespuesta(response);
        respuestaEntity.setEsCorrecta(0);
        respuestaEntity.setNumeroRespuesta(numberResponse);
        respuestaRepository.save(respuestaEntity);
    }
//*********************************************
//*********************************************

    public void entraResponderTest(String nombreTest,String nombreStudent,SendMessage sendMessage){
        TestEntity testEntity=findTestByTestNombre(nombreTest);
        KeyboardRow row= new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard= new ArrayList<>();
        List<PreguntaEntity> preguntaEntityList=preguntaEntityListByIdTest(testEntity);
        if(numero_de_pregunta_respondiendo<=preguntaEntityList.size()){
            PreguntaEntity preguntaEntity=findPreguntaByIdTestyNumeroPregunta(testEntity,numero_de_pregunta_respondiendo);
            sendMessage.setText(preguntaEntity.getContenidoPregunta());
            List<RespuestaEntity> respuestaEntityList=findRespuestasListByIdPregunta(preguntaEntity);
            for(int i=0;i<respuestaEntityList.size();i++){
                if(nummero_de_respuesta_respondiendo==3){
                    keyboard.add(row);
                    row= new KeyboardRow();
                }
                row.add(respuestaEntityList.get(i).getContenidoRespuesta());
                nummero_de_respuesta_respondiendo++;
            }
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);
            sendMessage.setReplyMarkup(keyboardMarkup);
            numero_de_pregunta_respondiendo++;
            nummero_de_respuesta_respondiendo=1;
        }
        else {
            numero_de_pregunta_respondiendo=1;
            entra_a_responder_test=false;
            //saveStudentTest(nombreStudent,nombreTest);
            sendMessage.setText("TEST RESPONDIDO Y ENTREGADO");
        }
    }


    private PreguntaEntity findPreguntaByIdTestyNumeroPregunta(TestEntity testEntity,int numero_de_pregunta){
        PreguntaEntity preguntaEntity=preguntaRepository.findByIdTestAndNumeroPregunta(testEntity,numero_de_pregunta);
        return  preguntaEntity;
    }

    private List<RespuestaEntity> findRespuestasListByIdPregunta(PreguntaEntity preguntaEntity){
        List<RespuestaEntity> respuestaEntityList=respuestaRepository.findByIdPregunta(preguntaEntity);
        return  respuestaEntityList;
    }

    public TestEntity findTestByTestNombre(String nameTest){
        TestEntity test= testRepository.findByNombreTest(nameTest);
        return test;
    }

    public   List<PreguntaEntity> preguntaEntityListByIdTest(TestEntity testEntity){
       List<PreguntaEntity> preguntaEntityList=preguntaRepository.findAllByIdTest(testEntity);
       return preguntaEntityList;
    }

    private void saveStudentTest(String nombreEstudiante, String nombreTest)
    {
        EstudianteEntity estudianteEntity=estudianteRespository.findByNombre(nombreEstudiante);
        TestEntity testEntity= testRepository.findByNombreTest(nombreTest);
        EstudianteTestEntity estudianteTestEntity=new EstudianteTestEntity();
        estudianteTestEntity.setIdEstudiante(estudianteEntity);
        estudianteTestEntity.setIdTest(testEntity);
        estudianteTestEntity.setPuntaje(60);
        estudianteTestRepository.save(estudianteTestEntity);
    }

    public boolean existsCursoByIdCurso(String id){
        boolean exists = false;
        CursoEntity cursoEntity = cursoRepository.findByIdCurso(Integer.parseInt(id));
        if (cursoEntity==null){
            LOGGER.info("Returns NULL");
            exists = false;
        }else {
            LOGGER.info(cursoEntity.getNombre());
            exists = true;
        }
        return exists;
    }

    private String getNombreCurso(String id){
        CursoEntity cursoEntity = cursoRepository.findByIdCurso(Integer.parseInt(id));
        return cursoEntity.getNombre();
    }

    private String getTipoCurso(String id){
        CursoEntity cursoEntity = cursoRepository.findByIdCurso(Integer.parseInt(id));
        return cursoEntity.getTipoCurso();
    }

    private String getClaveCurso(String id){
        CursoEntity cursoEntity = cursoRepository.findByIdCurso(Integer.parseInt(id));
        return cursoEntity.getClave();
    }

// FIND

    public  void saveAllStudentsInTheTest(String nameTest){
        TestEntity testEntity=testRepository.findByNombreTest(nameTest);
        saveTestforSendToAllStudentsInTheCurse(testEntity.getIdCurso(),testEntity);
    }

    public void saveTestforSendToAllStudentsInTheCurse(CursoEntity cursoEntity, TestEntity testEntity){
        List<EstudianteCursoEntity> estudianteCursoEntityList=estudianteCursoRepository.findAllByIdCurso(cursoEntity);
        for(EstudianteCursoEntity estudianteCursoEntity:estudianteCursoEntityList){
            EstudianteTestEntity estudianteTestEntity=new EstudianteTestEntity();
            estudianteTestEntity.setIdTest(testEntity);
            estudianteTestEntity.setIdEstudiante(estudianteCursoEntity.getIdEstudiante());
            estudianteTestEntity.setPuntaje(0);
            estudianteTestEntity.setRespondido(0);
            estudianteTestRepository.save(estudianteTestEntity);
        }
    }


    public void ListadoDeTest(SendMessage sendMessage,String studentName,String studentLastName){
        EstudianteEntity estudianteEntity=estudianteRespository.findByNombreAndAndApellidoPaterno(studentName,studentLastName);
        CursoEntity cursoEntity=cursoRepository.findByNombre("Metafisica");
        methodTestList(sendMessage,estudianteEntity,cursoEntity);
    }

    private void methodTestList(SendMessage sendMessage,EstudianteEntity estudianteEntity,CursoEntity cursoEntity){
        List<TestEntity> testEntityList=testRepository.findAllByIdCurso(cursoEntity);
        String cad="";
        int cont=1;
        for(TestEntity testEntity:testEntityList){
            EstudianteTestEntity estudianteTestEntity= estudianteTestRepository.findByIdTestAndIdEstudiante(testEntity,estudianteEntity);
            cad+=cont+". "+testEntity.getNombreTest()+"\t                ";
            if(estudianteTestEntity.getRespondido()==0){
                cad+="Nuevo\n";
            }
            else {
                cad+="Respondido\n";
            }
            cont++;
        }
        sendMessage.setText(cad);
    }


    public void processMainDocent(SendMessage sendMessage){
        sendMessage.setText("MENU DOCENTE\nIngrese una de las opciones");
        KeyboardRow row= new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard= new ArrayList<>();
        row.add("Crear nuevo curso");
        keyboard.add(row);
        row= new KeyboardRow();
        row.add("Lista de cursos");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);
    }

    public void processMainDocentInACurse(SendMessage sendMessage){
        sendMessage.setText("MENU DOCENTE\nIngrese una de las opciones");
        KeyboardRow row= new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard= new ArrayList<>();
        row.add("Crear nuevo test");
        keyboard.add(row);
        row= new KeyboardRow();
        row.add("Listado de test");
        keyboard.add(row);
        row= new KeyboardRow();
        row.add("Regresar a menu principal docente");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);
    }

    public void processMainStudent(SendMessage sendMessage){
        sendMessage.setText("MENU ESTUDIANTE\nIngrese una de las opciones");
        KeyboardRow row= new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard= new ArrayList<>();
        row.add("Buscar nuevo curso");
        keyboard.add(row);
        row= new KeyboardRow();
        row.add("Lista de cursos inscrito");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);
    }

    public void processMainStudentInACurse(SendMessage sendMessage){
        sendMessage.setText("MENU DOCENTE\nIngrese una de las opciones");
        KeyboardRow row= new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard= new ArrayList<>();
        row.add("Listado de test en el curso");
        keyboard.add(row);
        row= new KeyboardRow();
        row.add("Regresar a menu principal estudiante");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);
    }

    public void processMainStudentInATestCurse(SendMessage sendMessage){
        sendMessage.setText("MENU DOCENTE\nIngrese una de las opciones");
        KeyboardRow row= new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard= new ArrayList<>();
        row.add("Responder test");
        keyboard.add(row);
        row= new KeyboardRow();
        row.add("Regresar a menu principal del curso de estudiante");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);
    }

    public static boolean isValidEmail(String email)
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

    public static boolean verifyDatebirth(String input) {
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

    public static boolean validatePhoneNumber(String phone) {
        Pattern pattern = Pattern.compile("\\d{8}");
        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateNumber(String num) {
        Pattern pattern = Pattern.compile("\\d{1}");
        Pattern pattern2 = Pattern.compile("\\d{2}");
        Matcher matcher = pattern.matcher(num);
        Matcher matcher2 = pattern2.matcher(num);

        if (matcher.matches() || matcher2.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean existsContactByIdContact(String id){
        boolean exists = false;
        ContactEntity contactEntity = contactRepository.findByContactId(Integer.parseInt(id));
        if (contactEntity==null){
            LOGGER.info("Returns NULL");
            exists = false;
        }else {
            LOGGER.info(contactEntity.getFirstName());
            exists = true;
        }
        return exists;
    }

    public void receivePhotoContact(Update update, List<PhotoSize> photoReceived, SendMessage sendMessage, SendPhoto sendPhoto){
        String message = "";
        KeyboardRow row = new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        LOGGER.info("Tamaño del array antes: "+registrollenadosList.size());
        if (isEntra_a_agregar_contactos() == true && numero_de_pregunta == 6){
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

    public void mostrarMenu(SendMessage sendMessage, long chatId) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        sendMessage.setChatId(chatId)
                .setText("*Seleccione una opción:*\nBuscar Contactos\nAgregar Contactos\nModificar Contactos\nEliminar Contactos").setParseMode("Markdown");
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardRow keyboardRow2 = new KeyboardRow();
        KeyboardRow keyboardRow3 = new KeyboardRow();
        KeyboardRow keyboardRow4 = new KeyboardRow();
        keyboardRow.add("Buscar Contactos");
        keyboardRow2.add("Agregar Contactos");
        keyboardRow3.add("Modificar Contactos");
        keyboardRow4.add("Eliminar Contactos");
        keyboard.add(keyboardRow);
        keyboard.add(keyboardRow2);
        keyboard.add(keyboardRow3);
        keyboard.add(keyboardRow4);
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);
    }

}
