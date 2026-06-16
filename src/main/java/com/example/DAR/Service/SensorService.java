package com.example.DAR.Service;


import com.example.DAR.Repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;
    //private final HomeRepository homeRepository ;


}
