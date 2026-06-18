package com.example.DAR.Service;

import com.example.DAR.Api.ApiException;
import com.example.DAR.DTO.In.MaintenanceReminderDTOIn;
import com.example.DAR.DTO.Out.MaintenanceReminderSummaryDTOOut;
import com.example.DAR.DTO.Out.MaintenanceReminderDTOOut;
import com.example.DAR.Model.*;
import com.example.DAR.Repository.HomeItemRepository;
import com.example.DAR.Repository.HomeRepository;
import com.example.DAR.Repository.MaintenanceReminderRepository;
import com.example.DAR.Repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceReminderService {

    private final MaintenanceReminderRepository maintenanceReminderRepository;
    private final HomeRepository homeRepository;
    private final HomeItemRepository homeItemRepository;
    private final ModelMapper modelMapper;
    private final WeatherService weatherService;
    private final OpenAIService openAIService;
    private final MaintenanceRepository maintenanceRepository;

    public List<MaintenanceReminderDTOOut> getAll() {

        List<MaintenanceReminder> reminders = maintenanceReminderRepository.findAll();

        return reminders.stream().map(m -> modelMapper.map(m, MaintenanceReminderDTOOut.class)).toList();
    }

    public MaintenanceReminderDTOOut getMaintenanceReminder(Integer id) {
        MaintenanceReminder reminder = maintenanceReminderRepository.findMaintenanceReminderById(id);
        if (reminder == null) {
            throw new ApiException("Maintenance reminder not found");
        }
        return modelMapper.map(reminder, MaintenanceReminderDTOOut.class);
    }

    public List<MaintenanceReminderDTOOut> getRemindersByHome(Integer homeId) {
        Home home = homeRepository.findHomeById(homeId);
        if (home == null) {
            throw new ApiException("Home not found");
        }
        List<MaintenanceReminder> reminders = maintenanceReminderRepository.findMaintenanceRemindersByHomeId(homeId);

        return reminders.stream().map(m -> modelMapper.map(m, MaintenanceReminderDTOOut.class)).toList();
    }

    public List<MaintenanceReminderDTOOut> getUnsentReminders() {
        List<MaintenanceReminder> reminders = maintenanceReminderRepository.findMaintenanceRemindersByIsSent(false);

        return reminders.stream().map(m -> modelMapper.map(m, MaintenanceReminderDTOOut.class)).toList();
    }

    public List<MaintenanceReminderDTOOut> getRemindersBySeason(String season) {
        List<MaintenanceReminder> reminders = maintenanceReminderRepository.findMaintenanceRemindersBySeason(season);

        return reminders.stream().map(m -> modelMapper.map(m, MaintenanceReminderDTOOut.class)).toList();
    }

    public void addMaintenanceReminder(Integer maintenanceId, MaintenanceReminderDTOIn dto) {

        Maintenance maintenance = maintenanceRepository.findMaintenanceById(maintenanceId);

        if (maintenance == null) {
            throw new ApiException("Maintenance not found");
        }

        MaintenanceReminder reminder = new MaintenanceReminder();

        reminder.setMaintenance(maintenance);
        reminder.setHome(maintenance.getHome());
        reminder.setHomeItem(maintenance.getHomeItem());

        reminder.setTitle(dto.getTitle());
        reminder.setMessage(dto.getMessage());
        reminder.setReminderDate(dto.getReminderDate());
        reminder.setSeason(dto.getSeason());
        reminder.setWeatherCondition(dto.getWeatherCondition());

        reminder.setIsSent(false);

        maintenanceReminderRepository.save(reminder);
    }
    public void updateMaintenanceReminder(Integer id, Integer home_id, Integer homeItem_id, MaintenanceReminderDTOIn maintenanceReminderDTOIn) {
        MaintenanceReminder reminder = maintenanceReminderRepository.findMaintenanceReminderById(id);
        if (reminder == null) {
            throw new ApiException("Maintenance reminder not found");
        }
        Home home = homeRepository.findHomeById(home_id);
        if (home == null) {
            throw new ApiException("Home not found");
        }
        HomeItem homeItem = homeItemRepository.findHomeItemById(homeItem_id);
        if (homeItem == null) {
            throw new ApiException("Home item not found");
        }
        reminder.setTitle(maintenanceReminderDTOIn.getTitle());
        reminder.setMessage(maintenanceReminderDTOIn.getMessage());
        reminder.setReminderDate(maintenanceReminderDTOIn.getReminderDate());
        reminder.setSeason(maintenanceReminderDTOIn.getSeason());
        reminder.setWeatherCondition(maintenanceReminderDTOIn.getWeatherCondition());
        reminder.setHome(home);
        reminder.setHomeItem(homeItem);
        maintenanceReminderRepository.save(reminder);
    }

    public void markAsSent(Integer id) {
        MaintenanceReminder reminder = maintenanceReminderRepository.findMaintenanceReminderById(id);
        if (reminder == null) {
            throw new ApiException("Maintenance reminder not found");
        }
        reminder.setIsSent(true);
        maintenanceReminderRepository.save(reminder);
    }

    public void deleteMaintenanceReminder(Integer id) {
        MaintenanceReminder reminder = maintenanceReminderRepository.findMaintenanceReminderById(id);
        if (reminder == null) {
            throw new ApiException("Maintenance reminder not found");
        }
        maintenanceReminderRepository.deleteById(id);
    }


    public List<MaintenanceReminderDTOOut> getUpcomingReminders(Integer homeId) {

        Home home = homeRepository.findHomeById(homeId);

        if (home == null) {
            throw new ApiException("Home not found");
        }

        LocalDate today = LocalDate.now();

        List<MaintenanceReminder> reminders = maintenanceReminderRepository.findMaintenanceRemindersByHomeId(homeId);

        return reminders.stream()
                .filter(r -> !r.getIsSent())
                .filter(r -> r.getReminderDate().isEqual(today) || r.getReminderDate().isAfter(today))
                .map(r -> modelMapper.map(r, MaintenanceReminderDTOOut.class))
                .toList();
    }

    public List<MaintenanceReminderDTOOut> getTodayReminders(Integer homeId) {

        Home home = homeRepository.findHomeById(homeId);

        if (home == null) {
            throw new ApiException("Home not found");
        }

        LocalDate today = LocalDate.now();

        List<MaintenanceReminder> reminders = maintenanceReminderRepository.findMaintenanceRemindersByHomeId(homeId);

        return reminders.stream()
                .filter(r -> !r.getIsSent())
                .filter(r -> r.getReminderDate().isEqual(today))
                .map(r -> modelMapper.map(r, MaintenanceReminderDTOOut.class))
                .toList();
    }

    public void sendReminder(Integer reminderId) {

        MaintenanceReminder reminder = maintenanceReminderRepository.findMaintenanceReminderById(reminderId);

        if (reminder == null) {
            throw new ApiException("Maintenance reminder not found");
        }

        User user = reminder.getHome().getUser();

        if (user == null) {
            throw new ApiException("User not found");
        }

        if (reminder.getMaintenance().getPriority().equalsIgnoreCase("URGENT")) {
            callUserForUrgentReminder(user, reminder);
        }

        reminder.setIsSent(true);

        maintenanceReminderRepository.save(reminder);
    }
// helper
private void callUserForUrgentReminder(User user, MaintenanceReminder reminder) {

    if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()) {
        throw new ApiException("User phone number not found");
    }

    System.out.println("Calling user for urgent maintenance reminder");
    System.out.println("Phone number: " + user.getPhoneNumber());
    System.out.println("Maintenance title: " + reminder.getMaintenance().getTitle());
    System.out.println("Reminder message: " + reminder.getMessage());
}
    public void reactivateReminder(Integer reminderId) {

        MaintenanceReminder reminder = maintenanceReminderRepository.findMaintenanceReminderById(reminderId);

        if (reminder == null) {
            throw new ApiException("Maintenance reminder not found");
        }

        reminder.setIsSent(false);

        maintenanceReminderRepository.save(reminder);
    }

    public MaintenanceReminderSummaryDTOOut getReminderSummary(Integer homeId) {

        Home home = homeRepository.findHomeById(homeId);

        if (home == null) {
            throw new ApiException("Home not found");
        }

        LocalDate today = LocalDate.now();

        List<MaintenanceReminder> reminders = maintenanceReminderRepository.findMaintenanceRemindersByHomeId(homeId);

        int totalReminders = reminders.size();

        int sentReminders = (int) reminders.stream()
                .filter(MaintenanceReminder::getIsSent)
                .count();

        int unsentReminders = (int) reminders.stream()
                .filter(r -> !r.getIsSent())
                .count();

        int todayReminders = (int) reminders.stream()
                .filter(r -> !r.getIsSent())
                .filter(r -> r.getReminderDate().isEqual(today))
                .count();

        int upcomingReminders = (int) reminders.stream()
                .filter(r -> !r.getIsSent())
                .filter(r -> r.getReminderDate().isEqual(today) || r.getReminderDate().isAfter(today))
                .count();

        return new MaintenanceReminderSummaryDTOOut(
                totalReminders,
                upcomingReminders,
                todayReminders,
                sentReminders,
                unsentReminders
        );
    }

    // weather and ai
    // #1
    public String getAIWeatherMaintenanceAdvice(Integer homeId) {

        Home home = homeRepository.findHomeById(homeId);

        if (home == null) {
            throw new ApiException("Home not found");
        }

        String weatherDescription = weatherService.getWeatherDescription(home.getCity());

        String prompt = """
            You are an AI assistant for a smart Arabic home maintenance platform called DAR.

            The platform does not perform maintenance.
            It only gives reminders and suggestions to help the user take care of the home.

            Based on this weather information:
            %s

            Give a short Arabic maintenance advice for the homeowner.
            The advice should be related to home maintenance reminders only.
            Examples:
            - AC filter cleaning in hot weather
            - water heater check in cold weather
            - humidity or leakage check in rainy/humid weather

            Return the answer in Arabic only.
            Keep it short and user-friendly.
            """.formatted(weatherDescription);

        return openAIService.generateReaderAnalysis(prompt);
    }
    //#2
    public void createAIWeatherReminder(Integer homeId, Integer homeItemId) {

        Home home = homeRepository.findHomeById(homeId);

        if (home == null) {
            throw new ApiException("Home not found");
        }

        HomeItem homeItem = homeItemRepository.findHomeItemById(homeItemId);

        if (homeItem == null) {
            throw new ApiException("Home item not found");
        }

        String weatherDescription = weatherService.getWeatherDescription(home.getCity());

        String prompt = """
            You are an AI assistant for a smart Arabic home maintenance platform called DAR.

            The platform does not perform maintenance.
            It only creates reminders for the user.

            Home city and weather:
            %s

            Home item:
            %s

            Based on the weather and the home item, write a short Arabic reminder message.
            The reminder should be practical and related to this item.

            Return only the reminder message in Arabic.
            """.formatted(weatherDescription, homeItem.getCategory());

        String aiReminderMessage = openAIService.generateReaderAnalysis(prompt);

        MaintenanceReminder reminder = new MaintenanceReminder();

        reminder.setHome(home);
        reminder.setHomeItem(homeItem);
        reminder.setTitle("تذكير ذكي حسب الطقس");
        reminder.setMessage(aiReminderMessage);
        reminder.setReminderDate(LocalDate.now().plusDays(1));
        reminder.setSeason(getCurrentSeason());
        reminder.setWeatherCondition(weatherDescription);
        reminder.setIsSent(false);

        maintenanceReminderRepository.save(reminder);
    }
    private String getCurrentSeason() {

        int month = LocalDate.now().getMonthValue();

        if (month == 12 || month == 1 || month == 2) {
            return "WINTER";
        } else if (month >= 3 && month <= 5) {
            return "SPRING";
        } else if (month >= 6 && month <= 8) {
            return "SUMMER";
        } else {
            return "AUTUMN";
        }
    }
}
