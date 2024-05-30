package smarthomeapi.database.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import smarthomeapi.database.entities.Info;

import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<Info, Integer> {
    @Override
    List<Info> findAll();

    @Query("SELECT i FROM Info i ORDER BY i.id DESC")
    List<Info> findLastInfo();
}
