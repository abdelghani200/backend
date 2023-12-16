package com.aftas.backend.services.interfaceServices;

import com.aftas.backend.models.dtos.fish.FishDto;
import com.aftas.backend.models.dtos.fish.FishDtoRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface fishService {

    FishDto  saveFish(FishDto fishDto);
    List<FishDtoRes> getFish();

}
