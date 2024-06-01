package smarthomeapi.database.repos;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import smarthomeapi.database.entities.Info;
import smarthomeapi.database.entities.Log;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface LogsRepository extends JpaRepository<Log, Integer> {
    @Query("SELECT l FROM Log l ORDER BY l.createdAt DESC")
    List<Log> findAllOrderByCreatedAt();


}
