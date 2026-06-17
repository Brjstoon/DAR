package com.example.DAR.Controller;

import com.example.DAR.Api.ApiResponse;
import com.example.DAR.DTO.In.MaintenanceReminderDTOIn;
import com.example.DAR.Service.MaintenanceReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/maintenance-reminder")
@RequiredArgsConstructor
public class MaintenanceReminderController {

    private final MaintenanceReminderService maintenanceReminderService;

    @GetMapping("/get")
    public ResponseEntity<?> getMaintenanceReminders() {
        return ResponseEntity.status(200).body(maintenanceReminderService.getAll());
    }

    @PostMapping("/add/{home_id}/{homeItem_id}")
    public ResponseEntity<?> addMaintenanceReminder(@PathVariable Integer home_id, @PathVariable Integer homeItem_id, @RequestBody @Valid MaintenanceReminderDTOIn maintenanceReminderDTOIn) {
        maintenanceReminderService.addMaintenanceReminder(home_id, homeItem_id, maintenanceReminderDTOIn);
        return ResponseEntity.status(200).body(new ApiResponse("Maintenance reminder added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMaintenanceReminder(@PathVariable Integer id, @RequestBody @Valid MaintenanceReminderDTOIn maintenanceReminderDTOIn) {
        maintenanceReminderService.updateMaintenanceReminder(id, maintenanceReminderDTOIn);
        return ResponseEntity.status(200).body(new ApiResponse("Maintenance reminder updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMaintenanceReminder(@PathVariable Integer id) {
        maintenanceReminderService.deleteMaintenanceReminder(id);
        return ResponseEntity.status(200).body(new ApiResponse("Maintenance reminder deleted successfully"));
    }
}
