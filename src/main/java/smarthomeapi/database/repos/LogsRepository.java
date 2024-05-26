package smarthomeapi.database.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smarthomeapi.database.entities.Info;
import smarthomeapi.database.entities.Log;

import java.util.List;

@Repository
public interface LogsRepository extends JpaRepository<Log, Integer> {
    @Override
    List<Log> findAll();
}
