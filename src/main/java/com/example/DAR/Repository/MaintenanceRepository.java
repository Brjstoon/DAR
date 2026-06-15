package com.example.DAR.Repository;

import com.example.DAR.Model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {

    Maintenance findMaintenanceById(Integer id);

    List<Maintenance> findMaintenancesByHomeId(Integer homeId);

    List<Maintenance> findMaintenancesByStatus(String status);

    List<Maintenance> findMaintenancesByPriority(String priority);
}
