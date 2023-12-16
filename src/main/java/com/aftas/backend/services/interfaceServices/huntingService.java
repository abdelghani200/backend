package com.aftas.backend.services.interfaceServices;

import com.aftas.backend.models.dtos.hunting.HuntingDto;
import com.aftas.backend.models.dtos.hunting.HuntingDtoRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface huntingService {

    HuntingDto save(HuntingDto huntingDto);

    List<HuntingDtoRes> getAllHunting();

}
