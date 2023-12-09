package com.aftas.backend.services.interfaceServices;

import com.aftas.backend.models.dtos.ranking.RegistrationRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface rankingService {
    RegistrationRequestDto  registerMemberToCompetition(RegistrationRequestDto requestDto);

}
