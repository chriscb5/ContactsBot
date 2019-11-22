package ucb.edu.kajoybot.bo.databasekajoy.bl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ucb.edu.kajoybot.bo.databasekajoy.dao.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import ucb.edu.kajoybot.bo.databasekajoy.domain.*;
import ucb.edu.kajoybot.bo.databasekajoy.dto.Status;

@Service
public class BotBl {

    private static final Logger LOGGER= LoggerFactory.getLogger(BotBl.class);

    private EstudianteRespository estudianteRespository;
    private DocenteRespository docenteRespository;
    private CursoRepository cursoRepository;
    private KjEstudianteUserRepository kjEstudianteUserRepository;
    private TestRepository testRepository;
    private RespuestaRepository respuestaRepository;
    private PreguntaRepository preguntaRepository;
    private ChatRepository chatRepository;

    @Autowired
    public BotBl(EstudianteRespository estudianteRespository, DocenteRespository docenteRespository,
                 CursoRepository cursoRepository, KjEstudianteUserRepository kjEstudianteUserRepository,
                 TestRepository testRepository, RespuestaRepository respuestaRepository,
                 PreguntaRepository preguntaRepository, ChatRepository chatRepository) {
        this.estudianteRespository = estudianteRespository;
        this.docenteRespository = docenteRespository;
        this.cursoRepository = cursoRepository;
        this.kjEstudianteUserRepository = kjEstudianteUserRepository;
        this.testRepository = testRepository;
        this.respuestaRepository = respuestaRepository;
        this.preguntaRepository = preguntaRepository;
        this.chatRepository = chatRepository;
    }

/*    public BotBl(EstudianteRespository estudianteRespository, DocenteRespository docenteRespository, CursoRepository cursoRepository, KjEstudianteUserRepository kjEstudianteUserRepository) {
        this.estudianteRespository = estudianteRespository;
        this.docenteRespository = docenteRespository;
        this.cursoRepository = cursoRepository;
        this.kjEstudianteUserRepository = kjEstudianteUserRepository;
    }*/



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


//intento Multi Usuario
    public List<String> processUpdate(Update update) {
        LOGGER.info("Recibiendo update {} ", update);
        List<String> chatResponce = new ArrayList<>();
        KjEstudianteUserEntity kjEstudianteUserEntity = initUser(update.getMessage().getFrom());
        int m=0;
        continueChatWithUser(update,kjEstudianteUserEntity,chatResponce);

        // Si es la primera vez pedir una imagen para su perfil
//        if () {
//            LOGGER.info("Primer inicio de sesion para: {} ",update.getMessage().getFrom() );
//            result.add("Por favor ingrese una imagen para su foto de perfil");
//        } else { // Mostrar el menu de opciones
//            LOGGER.info("Dando bienvenida a: {} ",update.getMessage().getFrom() );
//            result.add("Bienvenido al Bot");
//        }
        return chatResponce;
    }

    private void continueChatWithUser(Update update, KjEstudianteUserEntity kjEstudianteUserEntity, List<String> chatResponce) {
        KjChatEntity lastMenssage = chatRepository.findLastChatByUserId(kjEstudianteUserEntity.getUserid());
        String response = null;

        if(lastMenssage == null){
            response = "1";
        }
        else {
            int lastMessageInt = 0;
            try {
                lastMessageInt = Integer.parseInt(lastMenssage.getOutMessage());
                response ="" + (lastMessageInt +1);
            } catch (NumberFormatException nfe){
                response ="1";
            }

        }
        KjChatEntity kjChatEntity = new KjChatEntity();
        kjChatEntity.setKjuserid(kjEstudianteUserEntity);
        kjChatEntity.setInMessage(update.getMessage().getText());
        kjChatEntity.setOutMessage(response);
        kjChatEntity.setMsgDate(new Date());//FIXME arreglar la fecha del campo
        kjChatEntity.setTxDate(new Date());
        kjChatEntity.setTxUser(kjEstudianteUserEntity.getUserid().toString());
        kjChatEntity.setTxHost(update.getMessage().getChatId().toString());
        chatRepository.save(kjChatEntity);
        chatResponce.add(response);
    }

/*Primer Version*/
//    public void processUsuario(Update update) {
//        LOGGER.info("Recibiendo update {} ", update);
//        initUser(update.getMessage().getFrom());
//        List<String> result = new ArrayList<>();
//        // Si es la primera vez pedir una imagen para su perfil
//        if () {
//            LOGGER.info("Primer inicio de sesion para: {} ",update.getMessage().getFrom() );
//            result.add("Por favor ingrese una imagen para su foto de perfil");
//        } else { // Mostrar el menu de opciones
//            LOGGER.info("Dando bienvenida a: {} ",update.getMessage().getFrom() );
//            result.add("Bienvenido al Bot");
//        }
//
//    }

    private KjEstudianteUserEntity initUser(User user) {

        KjEstudianteUserEntity kjEstudianteUserEntity = kjEstudianteUserRepository.findAllByBotUserId(user.getId().toString());
        if (kjEstudianteUserEntity == null) {
            EstudianteEntity estudianteEntity= new EstudianteEntity();
            estudianteEntity.setNombre(user.getFirstName());
            estudianteEntity.setApellidoPaterno(user.getLastName());
            estudianteEntity.setApellidoMaterno("Garza");
            estudianteEntity.setStatuss(Status.ACTIVE.getStatus());
            estudianteEntity.setInstitucion("Ave Experanzadora");
            estudianteEntity.setTxUser("admin");
            estudianteEntity.setTxDate(new Date());
            estudianteRespository.save(estudianteEntity);
            KjEstudianteUserEntity kjEstudianteUserEntity1 = new KjEstudianteUserEntity();
            kjEstudianteUserEntity1.setBotUserId(user.getId().toString());//(user.getId().toString());
            kjEstudianteUserEntity1.setIdEstudiante(estudianteEntity);
            kjEstudianteUserEntity1.setTxHost("localhost");
            kjEstudianteUserEntity1.setTxUser("admin");
            kjEstudianteUserEntity1.setTxDate(new Date());
            kjEstudianteUserRepository.save(kjEstudianteUserEntity1);

        }
        return kjEstudianteUserEntity;
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
        LOGGER.info("Entidad curso "+cursoEntity.toString());
        cursoRepository.save(cursoEntity);
        return "¡Registro completado exitosamente¡";
    }


    public String saveCompleteTest(List<String> questionsList,List<String> responseList ){
        saveTest();
        saveQuestionList(questionsList);
        saveResponseList(responseList,questionsList);
        return "REGISTRO DE TEST COMPLETADO";
    }

    public void saveTest(){
        TestEntity testEntity=new TestEntity();
        CursoEntity cursoEntity= cursoRepository.findByIdCurso(8);
        LOGGER.info("ENCONTRO en save test esto "+cursoEntity.getClave()+" "+cursoEntity.getNombre()+" "+cursoEntity.getTipoCurso());
        testEntity.setIdCurso(cursoEntity);
        DocenteEntity docenteEntity=new DocenteEntity();
        docenteEntity=docenteRespository.findAllByIdDocente(1);
        testEntity.setIdDocente(docenteEntity);
        testEntity.setNombreTest("Ciencias Sociales");
        testRepository.save(testEntity);
    }

    public void  saveQuestion(String question,int questionNumber){
        PreguntaEntity preguntaEntity= new PreguntaEntity();
        TestEntity testEntity=testRepository.findByNombreTest("Ciencias Sociales");
        preguntaEntity.setIdTest(testEntity);
        preguntaEntity.setContenidoPregunta(question);
        preguntaEntity.setNumeroPregunta(questionNumber);
        preguntaRepository.save(preguntaEntity);
    }

    public void saveQuestionList(List<String> questionList){
        int count=1;
        for (String question:questionList){
            saveQuestion(question,count);
            count++;
        }

    }

    public  void saveResponseList(List<String> responseList,List<String> questionList)
    {
        TestEntity testEntity=testRepository.findByNombreTest("Ciencias Sociales");
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

    public void  saveResponse(String response,PreguntaEntity preguntaEntity,int numberResponse){
        RespuestaEntity respuestaEntity= new RespuestaEntity();
        respuestaEntity.setIdPregunta(preguntaEntity);
        respuestaEntity.setContenidoRespuesta(response);
        respuestaEntity.setEsCorrecta(0);
        respuestaEntity.setNumeroRespuesta(numberResponse);
        respuestaRepository.save(respuestaEntity);
    }


}
