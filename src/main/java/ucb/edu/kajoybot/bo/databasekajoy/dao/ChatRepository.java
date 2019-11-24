package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ucb.edu.kajoybot.bo.databasekajoy.domain.KjChatEntity;

public interface ChatRepository extends JpaRepository<KjChatEntity,Integer> {
    @Query(value = "SELECT * FROM kj_chat where kjuserid =?1 ORDER BY kjchat_id DESC LIMIT 1",nativeQuery =true)
    public KjChatEntity findLastChatByUserId(Integer userId);
}

