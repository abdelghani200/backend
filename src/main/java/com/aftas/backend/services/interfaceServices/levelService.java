package com.aftas.backend.services.interfaceServices;

import com.aftas.backend.models.dtos.level.LevelDto;
import org.springframework.stereotype.Service;

@Service
public interface levelService {

    LevelDto saveLevel(LevelDto levelDto);

}
