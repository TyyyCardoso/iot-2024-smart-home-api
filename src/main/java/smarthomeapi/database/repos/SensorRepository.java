package smarthomeapi.database.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smarthomeapi.database.entities.Sensor;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    @Override
    List<Sensor> findAll();

    Sensor findSensorByName(String name);
}
