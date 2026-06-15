package com.example.DAR.Controller;

import com.example.DAR.Api.ApiResponse;
import com.example.DAR.DTO.In.MaintenanceDTOIn;
import com.example.DAR.Service.MaintenanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping("/get")
    public ResponseEntity<?> getMaintenances() {
        return ResponseEntity.status(200).body(maintenanceService.getAll());
    }

    @PostMapping("/add/{home_id}/{homeItem_id}")
    public ResponseEntity<?> addMaintenance(@PathVariable Integer home_id, @PathVariable Integer homeItem_id, @RequestBody @Valid MaintenanceDTOIn maintenanceDTOIn) {
        maintenanceService.addMaintenance(home_id, homeItem_id, maintenanceDTOIn);
        return ResponseEntity.status(200).body(new ApiResponse("Maintenance added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMaintenance(@PathVariable Integer id, @RequestBody @Valid MaintenanceDTOIn maintenanceDTOIn) {
        maintenanceService.updateMaintenance(id, maintenanceDTOIn);
        return ResponseEntity.status(200).body(new ApiResponse("Maintenance updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMaintenance(@PathVariable Integer id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.status(200).body(new ApiResponse("Maintenance deleted successfully"));
    }
}
