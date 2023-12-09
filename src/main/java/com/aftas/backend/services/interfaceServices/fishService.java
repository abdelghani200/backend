package com.aftas.backend.services.interfaceServices;

import com.aftas.backend.models.dtos.fish.FishDto;
import org.springframework.stereotype.Service;

@Service
public interface fishService {

    FishDto  saveFish(FishDto fishDto);

}
