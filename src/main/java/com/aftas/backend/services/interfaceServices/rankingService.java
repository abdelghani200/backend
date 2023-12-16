package com.aftas.backend.services.interfaceServices;

import com.aftas.backend.models.dtos.ranking.RegistrationRequestDto;
import com.aftas.backend.models.dtos.ranking.RegistrationResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface rankingService {
    RegistrationRequestDto  registerMemberToCompetition(RegistrationRequestDto requestDto);

    Map<String, List<RegistrationResponseDto>> getCompetitionsWithMembers();

    List<RegistrationResponseDto> getRanksByCompetition(String competitionCode);

}
