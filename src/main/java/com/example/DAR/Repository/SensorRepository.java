package com.example.DAR.Repository;

import com.example.DAR.Model.Home;
import com.example.DAR.Model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {
    List<Sensor> findSensorByHome(Home home);
    Sensor findSensorById(Integer id);
}
